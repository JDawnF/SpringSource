package com.baichen.bean;

import org.springframework.stereotype.Component;

/**
 * @Program: Car
 * @Author: baichen
 * @Description:
 */
@Component
public class Car {

    public Car(){
        System.out.println("car constructor...");
    }

    public void init(){
        System.out.println("car ... init...");
    }

    public void detory(){
        System.out.println("car ... detory...");
    }

}
