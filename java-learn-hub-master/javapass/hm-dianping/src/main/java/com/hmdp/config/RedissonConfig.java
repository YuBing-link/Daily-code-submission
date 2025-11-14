package com.hmdp.config;

import com.hmdp.entity.redisson;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(redisson.class)
public class RedissonConfig {
    @Autowired
    private redisson redisson;
    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress(redisson.getAddress())
                                .setPassword(redisson.getPassword())
                                .setDatabase(redisson.getDatabase());
        return Redisson.create(config);
    }
}
