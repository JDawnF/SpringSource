package com.baichen.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Program: Dog
 * @Author: baichen
 * @Description:
 */
@Component
public class Dog {

    public Dog(){
        System.out.println("dog constructor...");
    }

    // 对象创建并赋值之后调用
    @PostConstruct
    public void init(){
        System.out.println("dog ...  @PostConstruct...");
    }

    // 容器移除对象之前调用这个方法
    @PreDestroy
    public void destroy(){
        System.out.println("dog ... @PreDestroy...");
    }

}
