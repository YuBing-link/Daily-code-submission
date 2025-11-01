package com.hmdp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmdp.annotation.RedisAutoConvert;
import com.hmdp.entity.ShopType;
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

    @Test
    public void test() {
        // 存储非final类对象（如自定义的ShopType）
        ShopType shop = new ShopType();
        shop.setId(1L);
        shop.setName("美食");
        redisTemplate.opsForValue().set("test:shop", shop);

        // 存储final类对象（如String）
        redisTemplate.opsForValue().set("test:string", "hello");

        // 存储混合类型集合
        List<Object> list = new ArrayList<>();
        list.add(shop);
        list.add("hello");
        redisTemplate.opsForValue().set("test:list", list);
    }


        @Autowired
        private ObjectMapper objectMapper; // 注入项目中自定义的ObjectMapper

        /**
         * 测试场景：不使用objectMapper.convertValue时，直接强转Redis中的对象会报错
         */
        @Test
        @RedisAutoConvert
        public void testWithoutConvertValueError() throws JsonProcessingException {
            System.out.println(redisTemplate.opsForList().range("type:list",0,-1));

        }

}
