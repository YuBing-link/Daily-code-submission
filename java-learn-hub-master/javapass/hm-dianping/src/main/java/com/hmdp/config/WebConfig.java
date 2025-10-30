package com.hmdp.config;

import com.hmdp.Interceptor.HlInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HlInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/user/code",
                        "/user/login",
                        "/blog/hot",
                        "/blog/detail/*",
                        "/blog/likes",
                        "/blog/search/*",
                        "/shop/*",
                        "/shoptype/*",
                        "/upload/**",
                        "/blog/follows/*",
                        "/upload/**",
                        "/blog/comments",
                        "/blog/comments/*"
                );
    }
}
