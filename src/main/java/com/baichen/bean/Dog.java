package com.baichen.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Program: Dog
 * @Author: baichen
 * @Description: 实现ApplicationContextAware中的setApplicationContext方法，这样在其他容器中就可以使用这个IoC容器
 * 具体是看ApplicationContextAwareProcessor.java
 */
@Component
public class Dog implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public Dog() {
        System.out.println("dog constructor...");
    }

    // 对象创建并赋值之后调用
    @PostConstruct
    public void init() {
        System.out.println("dog ...  @PostConstruct...");
    }

    // 容器移除对象之前调用这个方法
    @PreDestroy
    public void destroy() {
        System.out.println("dog ... @PreDestroy...");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
