package com.baichen.condition;

import com.baichen.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Program: MyImportBeanDefinitionRegistrar
 * @Author: baichen
 * @Description: 手动注册bean到容器中
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * AnnotationMetadata：当前类的注解信息
     * BeanDefinitionRegistry:BeanDefinition注册类；把所有需要添加到容器中的bean；
     *      调用BeanDefinitionRegistry.registerBeanDefinition手工注册进来
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
//      判断是否有红色和蓝色,这里是用了全限定名，所以判断是是全限定名
        boolean definition = registry.containsBeanDefinition("com.baichen.bean.Red");
        boolean definition2 = registry.containsBeanDefinition("com.baichen.bean.Blue");
        if(definition && definition2){
            //指定Bean定义信息；（Bean的类型，Bean的scope等）
            RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
            //注册一个Bean，指定bean名,不用全限定名(即全类名)
            registry.registerBeanDefinition("rainBow", beanDefinition);
        }
    }
}
