package com.liang;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.util.Arrays;

@SpringBootTest
public class IocStudyTest {

    @Resource
    private ApplicationContext applicationContext;

    /**
     * -----------注册Bean-----------------
     * 执行 Bean 的构造器
     * 执行 Bean 的自动注入
     * 自动注入的信息：上下文测试Bean
     * ================后置处理器的前置处理===================
     * 执行 Bean 的@PostConstruct
     * 执行 Bean 的afterPropertiesSet（InitializingBean）
     * 执行 Bean 的@Bean的指定初始化方法
     * ================后置处理器的后置处理===================
     * com.liang.bean.TestBean@1edf71d9
     * 执行 Bean 的@PreDestroy
     * 执行 Bean 的destroy（DisposableBean）
     * 执行 Bean 的@Bean的指定销毁方法
     */

    @Test
    void test01(){
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
        Object testBean = applicationContext.getBean("testBean");
        System.out.println(testBean);
    }
}
