package com.baichen.config;

import com.baichen.bean.Car;
import com.baichen.bean.Color;
import com.baichen.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Program: MainConifgOfAutowired
 * @Author: baichen
 * @Description: 自动装配配置类
 * 自动装配:
 * 		Spring利用依赖注入（DI），完成对IOC容器中中各个组件的依赖关系赋值；
 * 1）、@Autowired：自动注入：
 * 	1）、默认优先按照类型去容器中找对应的组件:applicationContext.getBean(BookDao.class);找到就赋值
 * 	2）、如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找applicationContext.getBean("bookDao")
 * 	3）、@Qualifier("bookDao")：使用@Qualifier指定需要装配的组件的id，而不是使用属性名
 * 	4）、自动装配默认一定要将属性赋值好，没有就会报错；可以使用@Autowired(required=false),作为非必须装配的bean;
 * 	5）、@Primary：让Spring进行自动装配的时候，默认使用首选这个bean；也可以继续使用@Qualifier指定需要装配的bean的名字
 * 		BookService{
 * 			@Autowired
 * 			BookDao  bookDao;
 * 		}
 *
 * 2）、Spring还支持使用@Resource(JSR250)和@Inject(JSR330)[java规范的注解]实现自动装配功能
 * 	@Resource:
 * 	可以和@Autowired一样，实现自动装配的功能；但是默认是按照组件名称进行装配的；可以通过name=xxx 实现bean名字的制定
 * 	没有能支持@Primary功能，也没有支持@Autowired（reqiured=false）;
 * 	@Inject:
 * 	需要导入javax.inject的包，和Autowired的功能一样，但是没有required=false的功能；
 *  @Autowired:Spring定义的； @Resource、@Inject都是java规范
 *  AutowiredAnnotationBeanPostProcessor:解析完成自动装配功能；
 *
 * 3）、@Autowired:可以在构造器，参数，方法，属性等上面标注；都是从容器中获取参数组件的值
 * 	1）、[标注在方法位置]：@Bean+方法参数；参数从容器中获取;默认不写@Autowired,效果是一样的,都能自动装配
 * 	2）、[标在构造器上]：如果组件只有一个有参构造器，这个有参构造器的@Autowired可以省略，参数位置的组件还是可以自动从容器中获取
 * 	3）、放在参数位置：public Boss(@Autowired Car car) {} 参照Boss.java
 *
 * 4）、自定义组件想要使用Spring容器底层的一些组件（ApplicationContext，BeanFactory等）；
 * 	自定义组件实现xxxAware；在创建对象的时候，会调用接口规定的方法注入相关组件(参照Dog.java)；
 * 	Aware接口(参照Red.java)：
 * 		把Spring底层一些组件注入到自定义的Bean中；
 * 		xxxAware：功能使用xxxProcessor；每个Aware都有对应的Processor，如下：
 * 			ApplicationContextAware ==》ApplicationContextAwareProcessor；
 * 先判断bean的类型，是否属于xxxAware，如果是则对AccessControlContext进行赋值；
 * 然后调用invokeAwareInterfaces方法，对对应的xxxAware类型的值进行赋值
 */
@Configuration
@ComponentScan({"com.baichen.service","com.baichen.dao",
        "com.baichen.controller","com.baichen.bean"})
public class MainConfigOfAutowired {
    @Primary    // 让Spring进行自动装配的时候，默认使用首选这个bean；
    @Bean("bookDao2")   // 设置另一个bookDao
    public BookDao bookDao(){
        BookDao bookDao = new BookDao();
        bookDao.setLabel("2");
        return bookDao;
    }
    /**
     * @Bean  标注的方法创建对象的时候，方法参数的值从容器中获取,通过构造器加入IoC容器
     * @param car
     * @return
     */
    @Bean
    public Color color(Car car){
        Color color = new Color();
        color.setCar(car);
        return color;
    }
}
