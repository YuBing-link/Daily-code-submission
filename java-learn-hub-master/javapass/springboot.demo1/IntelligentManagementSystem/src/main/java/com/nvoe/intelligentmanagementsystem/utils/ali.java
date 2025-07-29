package com.nvoe.intelligentmanagementsystem.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class ali {
    private String endpoint ;
    private String accessKeyId ;
    private String accessKeySecret;
    private String bucketName;
}
