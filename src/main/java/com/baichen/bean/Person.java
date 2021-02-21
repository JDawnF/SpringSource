package com.baichen.bean;

import org.springframework.beans.factory.annotation.Value;

public class Person {

    //使用@Value赋值(对应IOCTest_PropertyValue测试类):
    //1、基本数值
    //2、可以写SpEL； #{}
    //3、可以写${}；取出配置文件【properties】中的值（在运行环境变量environment里面的值）
    @Value("baichen")
    private String name;
    @Value("#{20-2}")
    private Integer age;
    @Value("${person.nickName}")    // 取出外部配置文件(这里是person.properties)中的值
    private String nickName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Person(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }

    public Person() {
        super();
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", nickName="+nickName+"]";
    }


}