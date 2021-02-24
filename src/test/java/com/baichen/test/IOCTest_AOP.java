package com.baichen.test;

import com.baichen.aop.MathCalculator;
import com.baichen.config.MainConfigOfAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Program: IOCTest_AOP
 * @Author: baichen
 * @Description: AOP测试类
 */
public class IOCTest_AOP {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

        //1、不要自己创建对象，要从容器中获取
//		MathCalculator mathCalculator = new MathCalculator();
//		mathCalculator.div(1, 1);
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);

        mathCalculator.div(1, 1);

        applicationContext.close();
    }
}
