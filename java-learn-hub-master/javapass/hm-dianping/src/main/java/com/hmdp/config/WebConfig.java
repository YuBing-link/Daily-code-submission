package com.hmdp.config;

import com.hmdp.Interceptor.loginInterceptor;
import com.hmdp.Interceptor.refleshInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private RedisTemplate redisTemplate;
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new loginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/user/code",
                        "/user/login",
                        "/blog/hot",
                        "/shop/**",
                        "/shop-type/list"
                ).order(1);
        registry.addInterceptor(new refleshInterceptor(redisTemplate))
                .addPathPatterns("/**")
                .order(0);
    }
}
