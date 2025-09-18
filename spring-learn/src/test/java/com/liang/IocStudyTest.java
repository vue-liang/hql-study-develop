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

    @Test
    void test01(){
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
        Object testBean = applicationContext.getBean("testBean");
        System.out.println(testBean);
    }
}
