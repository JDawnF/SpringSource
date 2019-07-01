package com.baichen.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Program: Boss
 * @Author: baichen
 * @Description:
 */
//默认加在ioc容器中的组件，容器启动会调用无参构造器创建对象，再进行初始化赋值等操作
@Component
public class Boss {
    private Car car;

    //构造器要用的组件，都是从容器中获取
//如果组件只有一个有参构造器，这个有参构造器的@Autowired可以省略，参数位置的组件还是可以自动从容器中获取
//    @Autowired
//    public Boss(@Autowired Car car) {
    public Boss(Car car) {
        this.car = car;
        System.out.println("Boss...有参构造器");
    }

    public Car getCar() {
        return car;
    }

//    @Autowired
    //标注在方法，Spring容器创建当前对象时，就会调用方法，完成赋值；
    //方法使用的参数，自定义类型的值从ioc容器中获取
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss [car=" + car + "]";
    }
}
