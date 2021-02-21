package com.baichen.config;
import com.baichen.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Program: MainConfigOfLifeCycle
 * @Author: baichen
 * @Description: bean的生命周期配置类
 * bean的生命周期：bean创建---初始化----销毁的过程;容器可以管理bean的生命周期；
 * 我们可以自定义初始化和销毁方法:容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
 *
 * 构造（即对象创建）
 * 		单实例：在容器启动的时候创建对象(new AnnotationConfigApplicationContex)
 * 		多实例：在每次获取的时候创建对象
 * 顺序：
     * 1、BeanPostProcessor.postProcessBeforeInitialization
     * 2、初始化：
     * 		对象创建完成，并赋值好，调用初始化方法
     * 3、BeanPostProcessor.postProcessAfterInitialization
     * 4、销毁：
     * 		单实例：容器关闭的时候进行销毁
     * 		多实例：容器不会管理这个bean,即容器不会调用销毁方法；
 *
 * 遍历得到容器中所有的BeanPostProcessor；挨个执行beforeInitialization，
 * 一旦返回null，跳出for循环，不会执行后面的BeanPostProcessor.postProcessorsBeforeInitialization
 *
 * BeanPostProcessor原理:
 * populateBean(beanName, mbd, instanceWrapper);    给bean进行属性赋值
 * initializeBean(初始化方法，在populateBean方法之后执行)
 * {

     * applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
     * invokeInitMethods(beanName, wrappedBean, mbd);   执行自定义初始化
     * applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *}
 *
 * 有以下方式管理生命周期：
 * 1）、指定初始化和销毁方法；
 * 		通过@Bean指定init-method和destroy-method,即初始化(init())和销毁(destroy())方法,@Bean(initMethod="init",destroyMethod="destroy")
 * 2）、通过让Bean实现下面两个接口：
 *          InitializingBean（定义初始化逻辑，afterPropertiesSet()），
 * 			DisposableBean（定义销毁逻辑，destroy()）;
 * 3）、可以使用JSR250(JAVA规范的注解)；
 * 		@PostConstruct：在bean创建完成并且属性赋值完成；来执行初始化方法
 * 		@PreDestroy：在容器销毁bean之前通知我们进行清理工作
 * 4）、BeanPostProcessor【接口】：bean的后置处理器，在bean初始化前后进行一些处理工作；
 *      主要有以下两个方法：
 * 		postProcessBeforeInitialization:    在bean初始化之前工作,即init方法之前
 * 		postProcessAfterInitialization:     在bean初始化之后工作
 *
 * Spring底层对 BeanPostProcessor 的使用(参照Dog.java)，有多种不同的处理BeanProcessor:
 * 	bean赋值，注入其他组件，@Autowired({@link org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor})，生命周期注解功能，@Async,xxx BeanPostProcessor;
 */

@ComponentScan("com.baichen.bean")      // 扫描bean这个包
@Configuration
public class MainConfigOfLifeCycle {
    @Scope("prototype")      // 多实例
//    指定初始方法和销毁方法，即Car.java中定义的方法init和destroy,方法名可以自定义，对应的上即可
    @Bean(initMethod="init",destroyMethod="destroy")
    public Car car(){
        return new Car();
    }
}
