package com.baichen.test;

/**
 * @Program: IoCTest
 * @Author: baichen
 * @Description:
 */

import java.util.Map;

import com.baichen.bean.Blue;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

//import com.baichen.bean.Blue;
import com.baichen.bean.Person;
import com.baichen.config.MainConfig;
import com.baichen.config.MainConfig2;

public class IoCTest {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

    @Test
    public void testImport() {
        printBeans(applicationContext);     // 会进行过滤
        Blue bean = applicationContext.getBean(Blue.class);     // 拿到Blue对象
        System.out.println("Blue bean" + bean);
        //工厂Bean获取的是调用getObject创建的对象
        Object bean2 = applicationContext.getBean("colorFactoryBean");
        Object bean3 = applicationContext.getBean("colorFactoryBean");
        System.out.println("====the type of bean is：" + bean2.getClass() + "===="); // com.baichen.bean.Color
//        在ColorFactoryBean类中控制了为多实例，所以这里会返回false
        System.out.println(bean2 == bean3);
//        获取bean本身，而不是工厂bean调用getObject创建的对象,要用&+beanId
        Object bean4 = applicationContext.getBean("&colorFactoryBean");
        System.out.println("原来的bean本身："+bean4.getClass());  // com.baichen.bean.ColorFactoryBean
    }

    //  打印所有的definitionNames，即打印所有的组件名
    private void printBeans(AnnotationConfigApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println("definitionName：" + name);
        }
    }

    //    条件注册
    @Test
    public void test03() {
        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();  // IOC的运行环境
        //动态获取环境变量的值(这里是获取操作系统的名称)
        String property = environment.getProperty("os.name");
        System.out.println(property);
        for (String name : namesForType) {
            System.out.println(name);
        }
        // 找到所有Person定义的name，打印对象
        Map<String, Person> persons = applicationContext.getBeansOfType(Person.class);
        System.out.println(persons);
    }

    @Test
    public void test02() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
//		String[] definitionNames = applicationContext.getBeanDefinitionNames();
//		for (String name : definitionNames) {
//			System.out.println(name);
//		}
//
        System.out.println("====ioc init completed====");
//		定义两个bean,默认是单例类型，如果是prototype则会创建两个,获取的时候才创建对象;懒加载默认是单例的
        Object bean = applicationContext.getBean("person");
        Object bean2 = applicationContext.getBean("person");
        System.out.println(bean == bean2);
    }

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);   // 打印容器中定义的bean的名称，带有类似controller注解的都是作为一个bean
        }
    }
}
