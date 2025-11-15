package com.liang.bean;


import org.springframework.beans.factory.annotation.Autowired;

public class Bean2 {
    @Autowired
    private Bean1 bean1;
    public Bean2(){
        System.out.println("构造bean2");
    }
    public Bean1 getBean1(){
        return bean1;
    }
}
