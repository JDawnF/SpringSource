package com.baichen.config;

import com.baichen.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Program: MainConfigOfPropertyValues
 * @Author: baichen
 * @Description: 赋值配置类
 */
//使用@PropertySource读取外部配置文件中的k/v属性(可以读取多个外部配置文件)，然后保存到运行的环境变量中;
// 加载完外部的配置文件以后使用${}取出配置文件中的值(参照Person.java)，也可以通过ConfigurableEnvironment.java读取(参照IOCTest_PropertyValue.java)
@PropertySource(value = {"classpath:/person.properties"})
@Configuration
public class MainConfigOfPropertyValues {
    @Bean
    public Person person() {
        return new Person();
    }
}
