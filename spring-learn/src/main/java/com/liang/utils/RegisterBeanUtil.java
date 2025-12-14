package com.liang.utils;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RegisterBeanUtil {

    @Resource
    protected ApplicationContext applicationContext;

    public synchronized <T> void registerBean(String beanName, Class<T> beanClass, T beanInstance) {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();

        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(beanClass, () -> beanInstance)
                .setScope(BeanDefinition.SCOPE_SINGLETON)
                .getBeanDefinition();
        // 如果Bean已存在，先移除
        if (beanFactory.containsBeanDefinition(beanName)) {
            System.out.println(beanName+"已存在，现在更换新"+beanName);
            beanFactory.removeBeanDefinition(beanName);
        }

        beanFactory.registerBeanDefinition(beanName,beanDefinition);
    }

}
