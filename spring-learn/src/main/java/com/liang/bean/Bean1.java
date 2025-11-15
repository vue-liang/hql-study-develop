package com.liang.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class Bean1 {
    @Autowired
    private Bean2 bean2;
    public Bean1(){
        System.out.println("构造bean1");
    }

    public Bean2 getBean2() {
        return bean2;
    }
}
