package com.hmdp.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RedisData {
    private LocalDateTime expireTime;
    private Object data;
}
