package com.liang.config;

import com.liang.bean.Bean1;
import com.liang.bean.Bean2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class Config {
    @Bean
    public Bean1 bean1(){
        return new Bean1();
    }
    @Bean
    public Bean2 bean2(){
        return new Bean2();
    }
}
