package com.liang.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisStudyApplication.class, args);
        System.out.println("锁误删问题------以及缓存穿透、缓存击穿");
    }
}
