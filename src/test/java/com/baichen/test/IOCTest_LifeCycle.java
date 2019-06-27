package com.baichen.test;

import com.baichen.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Program: IOCTest_LifeCycle
 * @Author: baichen
 * @Description: 测试IoC容器生命周期
 */
public class IOCTest_LifeCycle {
    @Test
    public void test01(){
        //1、创建IoC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("容器创建完成...");
        //applicationContext.getBean("car");
        //关闭容器
        applicationContext.close();
    }
}
