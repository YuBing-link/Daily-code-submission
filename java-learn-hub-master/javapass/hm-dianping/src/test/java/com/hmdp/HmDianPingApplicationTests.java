package com.hmdp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmdp.annotation.RedisAutoConvert;
import com.hmdp.entity.ShopType;
import com.hmdp.utils.RedisIdWork;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HmDianPingApplicationTests {
    // 测试代码
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisIdWork redisIdWork;
    @Test
    void testRedis() {
        System.out.println(redisIdWork.nextId("shop:id"));
    }

}
