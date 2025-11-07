package com.hmdp.utils;

import cn.hutool.core.bean.BeanUtil;
import com.hmdp.annotation.RedisAutoConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.hmdp.utils.RedisConstants.CACHE_SHOP_KEY;
import static com.hmdp.utils.RedisConstants.LOCK_SHOP_KEY;

@Component
public class CacheUtils {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ThreadPool threadPool;
    /**
     * 获取互斥锁
     */
    private Boolean lockMutex(Long id){
        return redisTemplate.opsForValue().setIfAbsent(LOCK_SHOP_KEY + id, "mutex", 10, TimeUnit.SECONDS);
    }
    /**
     * 释放互斥锁
     */
    private void unlockMutex(Long id){
        redisTemplate.delete(LOCK_SHOP_KEY + id);
    }
    /**
     * 缓存普通数据
     */
    public void setKey(String key, Object value, Long time, TimeUnit unit){
        redisTemplate.opsForHash().putAll(key, BeanUtil.beanToMap(value));
        redisTemplate.expire(key, time, unit);
    }
    /**
     * 缓存热点数据
     */
    public void setHotKey(String key, Object value, LocalDateTime expireTime, TimeUnit unit){
        RedisData redisData = new RedisData();
        redisData.setExpireTime(expireTime);
        redisData.setData(value);
        redisTemplate.opsForHash().putAll(key, BeanUtil.beanToMap(redisData));
    }
    /**
     * 获取普通数据
     */

    @RedisAutoConvert
    public <T,R> T  getKey(String keyPrefix, R id, Function<R,T> dbFunc, Class<T> clazz){
        String key = keyPrefix + id;
        T t=null;
        Map<String, Object> map = redisTemplate.opsForHash().entries(key);
        if (map.isEmpty()) {
            try {
                if (!lockMutex((Long) id)) {
                    Thread.sleep(10);
                    return getKey(keyPrefix, id, dbFunc, clazz);
                }
                T db = dbFunc.apply(id);
                if (db == null) {
                    redisTemplate.opsForHash().put(key,""," ");
                    redisTemplate.expire(key, 2, TimeUnit.MINUTES);
                    return null;
                }
                redisTemplate.opsForHash().putAll(key, BeanUtil.beanToMap(db));
                map = redisTemplate.opsForHash().entries(key);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                unlockMutex((Long) id);
            }
        }
        BeanUtil.fillBeanWithMap(map, t,true);
        return t;
    }
    /**
     * 获取热点数据
     */
    @RedisAutoConvert
    public <T,R> T  getHotKey(String keyPrefix, R id, Function<R,T> dbFunc, Long expireTime, TimeUnit unit,Class<T> clazz){
        String key = keyPrefix + id;
        RedisData redisData = new RedisData();
        Map<String, Object> map = redisTemplate.opsForHash().entries(key);
        if (map.isEmpty()) {
            try {
                if (!lockMutex((Long) id)) {
                    Thread.sleep(10);
                    return getHotKey(keyPrefix, id, dbFunc,expireTime, unit,clazz);
                }
                T db = dbFunc.apply(id);
                if (db == null) {
                    redisTemplate.opsForHash().put(CACHE_SHOP_KEY + id,""," ");
                    redisTemplate.expire(CACHE_SHOP_KEY + id, 2, TimeUnit.MINUTES);
                }
                redisData.setData(db);
                redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(expireTime)));
                redisTemplate.opsForHash().putAll(CACHE_SHOP_KEY + id, BeanUtil.beanToMap(redisData));
                map = redisTemplate.opsForHash().entries(key);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                unlockMutex((Long) id);
            }
        }

        BeanUtil.fillBeanWithMap(map, redisData, true);
        if (redisData.getExpireTime().isBefore(LocalDateTime.now())) {
            if (lockMutex((Long) id)) {
                threadPool.threadPoolExecutor.submit(() -> {
                    try {
                        T db = dbFunc.apply(id);
                        redisData.setData(db);
                        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(expireTime)));
                        System.out.println(LocalDateTime.now().plusSeconds(unit.toSeconds(expireTime)));
                        redisTemplate.opsForHash().putAll(CACHE_SHOP_KEY + id, BeanUtil.beanToMap(redisData));
                    } finally {
                        unlockMutex((Long) id);
                    }
                });
            }
        }
        Object data = redisData.getData();
        return  (T) data;

    }


}
