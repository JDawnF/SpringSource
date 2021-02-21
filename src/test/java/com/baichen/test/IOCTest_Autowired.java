package com.baichen.test;

import com.baichen.bean.Boss;
import com.baichen.bean.Car;
import com.baichen.bean.Color;
import com.baichen.config.MainConfigOfAutowired;
import com.baichen.dao.BookDao;
import com.baichen.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Program: IOCTest_Autowired
 * @Author: baichen
 * @Description: �Զ�װ�� ������
 */
public class IOCTest_Autowired {
    @Test
    public void test1() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        // �ó�����bean
        BookService bookService = applicationContext.getBean(BookService.class);
        System.out.println(bookService);

        BookDao bean = applicationContext.getBean(BookDao.class);
        System.out.println(bean);

        // �ڷ����ϱ�ע,ͬһ��car����
        Boss boss = applicationContext.getBean(Boss.class);
        System.out.println(boss);   // Boss [car=com.baichen.bean.Car@2aa3cd93]
        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);   //  com.baichen.bean.Car@2aa3cd93
        Color color = applicationContext.getBean(Color.class);
        System.out.println(color);
        System.out.println("���Ե�IOC������" + applicationContext); // ���Ե�IOC����
        applicationContext.close();
    }
}
