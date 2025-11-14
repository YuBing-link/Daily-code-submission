package com.hmdp.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "redisson")
public class redisson {
    private String address;
    private String password;
    private int database;
    private int timeout;
}
