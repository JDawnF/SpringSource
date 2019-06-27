package com.baichen.test;

import com.baichen.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Program: IOCTest_LifeCycle
 * @Author: baichen
 * @Description: ����IoC������������
 */
public class IOCTest_LifeCycle {
    @Test
    public void test01(){
        //1������IoC����
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("�����������...");
        //applicationContext.getBean("car");
        //�ر�����
        applicationContext.close();
    }
}
