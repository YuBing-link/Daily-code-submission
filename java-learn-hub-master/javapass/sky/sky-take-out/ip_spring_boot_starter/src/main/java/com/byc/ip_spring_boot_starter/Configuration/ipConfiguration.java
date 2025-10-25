package com.byc.ip_spring_boot_starter.Configuration;

import com.byc.ip_spring_boot_starter.Server.ipServer;
import org.springframework.context.annotation.Bean;

public class ipConfiguration {
    @Bean
    public ipServer ipServer(){
        return new ipServer();
    }
}
