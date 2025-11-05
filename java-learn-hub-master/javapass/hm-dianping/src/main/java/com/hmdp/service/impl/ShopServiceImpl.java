package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmdp.entity.Shop;
import com.hmdp.mapper.ShopMapper;
import com.hmdp.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.RedisData;
import com.hmdp.utils.SystemConstants;
import com.hmdp.utils.ThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.CACHE_SHOP_KEY;
import static com.hmdp.utils.RedisConstants.LOCK_SHOP_KEY;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Slf4j
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ThreadPool threadPool;

    private Boolean lockMutex(Long id){
        Boolean mutex = redisTemplate.opsForValue().setIfAbsent(LOCK_SHOP_KEY + id, "mutex", 10, TimeUnit.SECONDS);
        return mutex;
    }
    private void unlockMutex(Long id){
        redisTemplate.delete(LOCK_SHOP_KEY + id);
    }


    /**
     * 根据id查询商铺信息
     * @param id 商铺id
     * @return 商铺详情数据
     */
    @Override
    public Shop getRedisById(Long id) {
        Map<String, Object> shopRedisDataMap = redisTemplate.opsForHash().entries(CACHE_SHOP_KEY + id);
        RedisData redisData = new RedisData();
        if (shopRedisDataMap.isEmpty()) {
            try {
                if (!lockMutex(id)) {
                    Thread.sleep(10);
                    return getRedisById(id);
                }
                Shop shop = this.getById(id);
                if (shop == null) {
                    redisTemplate.opsForHash().put(CACHE_SHOP_KEY + id,""," ");
                    redisTemplate.expire(CACHE_SHOP_KEY + id, 2, TimeUnit.MINUTES);
                    log.error("查询数据不存在");
                }
                redisData.setData(shop);
                redisData.setExpireTime(LocalDateTime.now().plusSeconds(30));
                redisTemplate.opsForHash().putAll(CACHE_SHOP_KEY + id, BeanUtil.beanToMap(redisData));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                unlockMutex(id);
            }
        }
        BeanUtil.fillBeanWithMap(shopRedisDataMap, redisData, true);
        if (redisData.getExpireTime().isBefore(LocalDateTime.now())) {
            log.error("缓存即将过期");
                if (lockMutex(id)) {
                    threadPool.threadPoolExecutor.submit(() -> {
                        try {
                            Shop shop = this.getById(id);
                            redisData.setData(shop);
                            redisData.setExpireTime(LocalDateTime.now().plusSeconds(30));
                            System.out.println(LocalDateTime.now().plusSeconds(30));
                            redisTemplate.opsForHash().putAll(CACHE_SHOP_KEY + id, BeanUtil.beanToMap(redisData));
                        } finally {
                            unlockMutex(id);
                        }
                    });
                 }
            }
        shopRedisDataMap = redisTemplate.opsForHash().entries(CACHE_SHOP_KEY + id);
        BeanUtil.fillBeanWithMap(shopRedisDataMap, redisData, true);
        return BeanUtil.toBean(redisData.getData(), Shop.class);
    }
    /**
     * 新增商铺信息
     * @param shop 商铺数据
     * @return 商铺id
     */
    @Override
    public Long redisSave(Shop shop) {
        if (shop== null){
            throw new RuntimeException("保存失败");
        }
        save(shop);
        redisTemplate.opsForHash().putAll(CACHE_SHOP_KEY + shop.getId(), BeanUtil.beanToMap(shop));
        redisTemplate.expire(CACHE_SHOP_KEY + shop.getId(), 30, TimeUnit.MINUTES);
        return shop.getId();
    }

    /**
     * 更新商铺信息
     * @param shop 商铺数据
     * @return 无
     */

    @Override
    public void updateRedisById(Shop shop) {
        if (shop== null){
            throw new RuntimeException("更新失败");
        }
        this.updateById(shop);
        redisTemplate.opsForHash().putAll(CACHE_SHOP_KEY + shop.getId(), BeanUtil.beanToMap(shop));
    }
    /**
     * 根据商铺类型分页查询商铺信息
     * @param typeId 商铺类型
     * @param current 页码
     * @return 商铺列表
     */
    @Override
    public List<Shop> queryPage(Integer typeId, Integer current) {
        Page<Shop> page = this.query()
                .eq("type_id", typeId)
                .page(new Page<>(current, SystemConstants.DEFAULT_PAGE_SIZE));
        return page.getRecords();
    }
    /**
     * 根据商铺名称关键字分页查询商铺信息
     * @param name 商铺名称关键字
     * @param current 页码
     * @return 商铺列表
     */
    @Override
    public List<Shop> queryPageByName(String name, Integer current) {
        Page<Shop> page = this.query()
                .like(StrUtil.isNotBlank(name), "name", name)
                .page(new Page<>(current, SystemConstants.MAX_PAGE_SIZE));
        return page.getRecords();
    }
}
