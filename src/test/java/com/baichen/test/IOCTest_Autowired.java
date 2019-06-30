package com.baichen.test;

import com.baichen.config.MainConfigOfAutowired;
import com.baichen.dao.BookDao;
import com.baichen.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Program: IOCTest_Autowired
 * @Author: baichen
 * @Description: 自动装配 测试类
 */
public class IOCTest_Autowired {
    @Test
    public void test1(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        BookService bookService = applicationContext.getBean(BookService.class);
        System.out.println(bookService);

        BookDao bean = applicationContext.getBean(BookDao.class);
        System.out.println(bean);

//        Boss boss = applicationContext.getBean(Boss.class);
//        System.out.println(boss);
//        Car car = applicationContext.getBean(Car.class);
//        System.out.println(car);
//
//        Color color = applicationContext.getBean(Color.class);
//        System.out.println(color);
//        System.out.println(applicationContext);
        applicationContext.close();
    }
}
