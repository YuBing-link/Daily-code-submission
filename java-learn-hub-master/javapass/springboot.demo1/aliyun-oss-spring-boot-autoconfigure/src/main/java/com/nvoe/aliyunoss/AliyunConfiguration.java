package com.nvoe.aliyunoss;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Configuration
@EnableConfigurationProperties(AliyunOssProperties.class)
public class AliyunConfiguration {

    @Bean
    public AliyunOSS aliyunOSS(AliyunOssProperties ali)
    {
        AliyunOSS aliyunOSS=new AliyunOSS();
        aliyunOSS.setA(ali);
        return aliyunOSS;
    }


}