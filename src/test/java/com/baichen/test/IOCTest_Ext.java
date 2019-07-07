package com.baichen.test;

import com.baichen.ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Program: IOCTest_Ext
 * @Author: baichen
 * @Description: ������չԭ��
 */
public class IOCTest_Ext {
    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);
        //�����¼�,�������������:
        applicationContext.publishEvent(new ApplicationEvent(new String("�ҷ�����ʱ��")) {
        });
        applicationContext.close();
    }
}
