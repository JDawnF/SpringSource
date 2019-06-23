package com.baichen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.ComponentScans;
import com.baichen.bean.Person;

//配置类==配置文件
@Configuration  //告诉Spring这是一个配置类
@ComponentScans(
        value = {
                @ComponentScan(value = "com.baichen", includeFilters = {
/*@Filter(type=FilterType.ANNOTATION,classes={Controller.class,Service,class}),按注解排除，这里表示排除controller注解的类,
  @Filter(type=FilterType.ASSIGNABLE_TYPE,classes={BookService.class}),按给定的类型排除
*/
                @Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})   // 使用自定义的过滤规则
                }, useDefaultFilters = false)
        }
)
//@ComponentScan  value:指定要扫描的包
    //  useDefaultFilters=false 表示禁用默认的扫描策略，默认的即全部扫描，如果是通过xml配置的话要在<context:component-scan>标签中修改use-default-filters="false"
//excludeFilters = Filter[] ：指定扫描的时候按照什么规则排除那些组件
//includeFilters = Filter[] ：指定扫描的时候只需要包含哪些组件
//FilterType.ANNOTATION：按照注解
//FilterType.ASSIGNABLE_TYPE：按照给定的类型；
//FilterType.ASPECTJ：使用ASPECTJ表达式
//FilterType.REGEX：使用正则指定
//FilterType.CUSTOM：使用自定义规则,要实现TypeFilter
public class MainConfig {
    //给容器中注册一个Bean;类型为返回值的类型，id默认是用方法名作为id,在Bean注解中指定方法名
    @Bean("person")
    public Person person01() {
//        如果配置类中定义了变量的属性值，如name的话，会覆盖这个主配置类
        return new Person("lisi", 20);
    }

}
