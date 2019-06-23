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
        printBeans(applicationContext);     // ����й���
        Blue bean = applicationContext.getBean(Blue.class);
        System.out.println("Blue bean" + bean);
        //����Bean��ȡ���ǵ���getObject�����Ķ���
        Object bean2 = applicationContext.getBean("colorFactoryBean");
        Object bean3 = applicationContext.getBean("colorFactoryBean");
        System.out.println("====the type of bean is" + bean2.getClass() + "====");
//        ��ColorFactoryBean���п�����Ϊ��ʵ������������᷵��false
        System.out.println(bean2 == bean3);
//        ��ȡbean���������ǹ���bean����getObject�����Ķ���
        Object bean4 = applicationContext.getBean("&colorFactoryBean");
        System.out.println("ԭ����bean����"+bean4.getClass());
    }

    //  ��ӡ���е�definitionNames
    private void printBeans(AnnotationConfigApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println("definitionName   " + name);
        }
    }

    //    ����ע��
    @Test
    public void test03() {
        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        //��̬��ȡ����������ֵ(�����ǻ�ȡ����ϵͳ)
        String property = environment.getProperty("os.name");
        System.out.println(property);
        for (String name : namesForType) {
            System.out.println(name);
        }
        // ��ӡ����
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
//		��������bean,Ĭ���ǵ������ͣ������prototype��ᴴ������;������Ĭ���ǵ�����
        Object bean = applicationContext.getBean("person");
        Object bean2 = applicationContext.getBean("person");
        System.out.println(bean == bean2);
    }

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }
}
