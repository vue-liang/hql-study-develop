package com.liang.design;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DesignFrameWorkApplication {
    public static void main(String[] args) {
        SpringApplication.run(DesignFrameWorkApplication.class, args);
    }
    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(5,
                10,
                4L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(6));
    }
}
