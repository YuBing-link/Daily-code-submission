package com.byc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest
class RedisLearnApplicationTests {
    @Test
    void JedisTest() {
        Jedis jedis = new Jedis("192.168.134.132", 6379);
        jedis.auth("123456");
        jedis.set("name", "jiejie");
        System.out.println(jedis.get("name"));
        jedis.close();
    }

}
