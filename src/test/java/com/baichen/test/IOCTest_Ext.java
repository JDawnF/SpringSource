package com.baichen.test;

import com.baichen.ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Program: IOCTest_Ext
 * @Author: baichen
 * @Description: 测试扩展原理
 */
public class IOCTest_Ext {
    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);
        //发布事件,监听器会监听到:
        applicationContext.publishEvent(new ApplicationEvent(new String("我发布的时间")) {
        });
        applicationContext.close();
    }
}
