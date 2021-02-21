package com.baichen.test;

import com.baichen.bean.Person;
import com.baichen.config.MainConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @Program: IOCTest_PropertyValue
 * @Author: baichen
 * @Description: 测试赋值配置类
 */
public class IOCTest_PropertyValue {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);

    @Test
    public void test01() {
        printBeans(applicationContext);
        System.out.println("====================================================");
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
        // 也可以通过ConfigurableEnvironment取出配置文件中配置的值
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("person.nickName");   // 对应配置文件中的key
        System.out.println("nickName是："+property);
        applicationContext.close();
    }

    // 打印容器中的bean
    private void printBeans(AnnotationConfigApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println("bean的名字是："+name);
        }
    }
}
