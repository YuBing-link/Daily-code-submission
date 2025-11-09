package com.hmdp.utils;

import cn.hutool.core.lang.UUID;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class SimpleRedisLock implements ILock{

    private RedisTemplate redisTemplate;
    private String name;
    private static final String KEY_PREFIX = "lock:";
    private static final String ID_PREFIX = UUID.randomUUID().toString(true);
    private static final Long id = Thread.currentThread().getId();

    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;
    static {
         UNLOCK_SCRIPT = new DefaultRedisScript<>();
         UNLOCK_SCRIPT.setLocation( new ClassPathResource("unLock.lua"));
         UNLOCK_SCRIPT.setResultType(Long.class);
    }

    public SimpleRedisLock( String name,RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.name = name;
    }

    @Override
    public boolean tryLock(Long timeoutSec) {

        Boolean absent = redisTemplate.opsForValue().setIfAbsent(KEY_PREFIX + name, ID_PREFIX + id,
                timeoutSec, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(absent);
    }

    @Override
    public void unlock() {
        redisTemplate.execute(UNLOCK_SCRIPT,
                Collections.singletonList(KEY_PREFIX + name),
                ID_PREFIX + id);
    }
//    @Override
//    public void unlock() {
//        boolean equals = redisTemplate.opsForValue()
//                                        .get(KEY_PREFIX + name)
//                                        .equals(ID_PREFIX + id);
//        if (equals) {
//            redisTemplate.delete(KEY_PREFIX + name);
//        }
//
//    }
}
