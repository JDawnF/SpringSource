package com.baichen;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baichen.bean.Person;
import com.baichen.config.MainConfig;

public class MainTest {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
//		配置XML的开发模式
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
//		Person bean = (Person) applicationContext.getBean("person");
//		System.out.println(bean);

//		通过注解获取配置类的信息
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		Person bean = (Person) applicationContext.getBean("person");
		System.out.println(bean);

//		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
//		Person bean = applicationContext.getBean(Person.class);
//		System.out.println(bean);

		// 通过注解的形式注入bean，然后可以通过类名获取
//		String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
//		for (String name : namesForType) {
//			System.out.println(name);		// 输出bean的名字
//		}
	
	}

}
