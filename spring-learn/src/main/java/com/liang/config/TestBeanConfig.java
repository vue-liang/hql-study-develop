package com.liang.config;

import com.liang.bean.TestBean;
import com.liang.bean.UserContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestBeanConfig implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TestBean) {
            System.out.println("================后置处理器的前置处理===================");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TestBean) {
            System.out.println("================后置处理器的后置处理===================");
        }
        return bean;
    }

    @Bean
    public UserContext userContext() {
        UserContext userContext = new UserContext();
        userContext.setName("上下文测试Bean");
        return userContext;
    }
    @Bean(initMethod = "initMethod",destroyMethod = "destroyMethod")
    public TestBean testBean() {
        System.out.println("-----------注册Bean-----------------");
        return new TestBean();
    }
}
