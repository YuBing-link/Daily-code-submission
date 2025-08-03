package com.nvoe.intelligentmanagementsystem.utils.FilterUtils;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtAuthFilterRegistration() {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter());
        // 设置拦截路径，可多个
        registrationBean.addUrlPatterns("/*");
        // 设置过滤器顺序（值越小越先执行）
        registrationBean.setOrder(1);
        return registrationBean;
    }
}