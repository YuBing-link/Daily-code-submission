package com.byc.ip_spring_boot_starter.Configuration;

import com.byc.ip_spring_boot_starter.Properties.ipProperties;
import com.byc.ip_spring_boot_starter.Server.ipServer;
import com.byc.ip_spring_boot_starter.interceptor.IpInterceptor;
import com.byc.ip_spring_boot_starter.interceptor.SpringMvc;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Import({ipProperties.class})
public class ipConfiguration {
    @Bean
    public ipServer ipServer(){
        return new ipServer();
    }

}
