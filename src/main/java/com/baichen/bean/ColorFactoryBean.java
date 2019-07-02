package com.baichen.bean;

/**
 * @Program: ColorFactoryBean
 * @Author: baichen
 * @Description: 颜色工厂bean，在Spring中可以利用FactoryBean来产生一些自定义配置的bean。
 */
import org.springframework.beans.factory.FactoryBean;

//创建一个Spring定义的FactoryBean
public class ColorFactoryBean implements FactoryBean<Color> {
    //返回一个Color对象，这个对象会添加到容器中
    public Color getObject() throws Exception {
        System.out.println("ColorFactoryBean...getObject...");
        return new Color();
    }

    public Class<?> getObjectType() {
        return Color.class;
    }

    //判断是否为单例？
    //true：这个bean是单实例，在容器中保存一份
    //false：多实例，每次获取都会创建一个新的bean；
    public boolean isSingleton() {
        return false;
    }

}
