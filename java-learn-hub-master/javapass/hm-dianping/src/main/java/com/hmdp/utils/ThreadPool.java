package com.hmdp.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
@Component
public class ThreadPool {
    public static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(
            5,
            10,
            25,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000),
            new ThreadPoolExecutor.DiscardPolicy()
    );
}
