package com.liang.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

public class TestBean implements InitializingBean, DisposableBean {
    private UserContext userContext;
    public TestBean() {
        System.out.println("执行 Bean 的构造器");
    }
    @PostConstruct
    public void init() {
        System.out.println("执行 Bean 的@PostConstruct");
    }
    @PreDestroy
    public void preDestroy(){
        System.out.println("执行 Bean 的@PreDestroy");
    }
    public void initMethod(){
        System.out.println("执行 Bean 的@Bean的指定初始化方法");
    }
    public void destroyMethod(){
        System.out.println("执行 Bean 的@Bean的指定销毁方法");
    }
    @Resource
    public void setUserContext(UserContext userContext) {
        this.userContext = userContext;
        System.out.println("执行 Bean 的自动注入");
        System.out.println("自动注入的信息：" + userContext.getName());
    }
    @Override
    public void destroy() {
        System.out.println("执行 Bean 的destroy（DisposableBean）");
    }
    @Override
    public void afterPropertiesSet() {
        System.out.println("执行 Bean 的afterPropertiesSet（InitializingBean）");
    }
}
