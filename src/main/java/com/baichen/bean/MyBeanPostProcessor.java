package com.baichen.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Program: MyBeanPostProcessor
 * @Author: baichen
 * @Description: 后置处理器：初始化前后进行处理
 */
@Component      // 将后置处理器加入ioc容器
public class MyBeanPostProcessor implements BeanPostProcessor {
    // 在bean初始化之前工作,即init方法之前
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 打印加入到IoC容器中的组件
        System.out.println("postProcessBeforeInitialization..." + beanName + "=>" + bean);
        return bean;
    }

    // 在bean初始化之后工作
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization..." + beanName + "=>" + bean);
        return bean;
    }
}
