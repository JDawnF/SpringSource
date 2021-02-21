package com.baichen.test;

import com.baichen.bean.Person;
import com.baichen.config.MainConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @Program: IOCTest_PropertyValue
 * @Author: baichen
 * @Description: ���Ը�ֵ������
 */
public class IOCTest_PropertyValue {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);

    @Test
    public void test01() {
        printBeans(applicationContext);
        System.out.println("====================================================");
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
        // Ҳ����ͨ��ConfigurableEnvironmentȡ�������ļ������õ�ֵ
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("person.nickName");   // ��Ӧ�����ļ��е�key
        System.out.println("nickName�ǣ�"+property);
        applicationContext.close();
    }

    // ��ӡ�����е�bean
    private void printBeans(AnnotationConfigApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println("bean�������ǣ�"+name);
        }
    }
}
