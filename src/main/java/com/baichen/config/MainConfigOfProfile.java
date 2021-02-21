package com.baichen.config;

import com.baichen.bean.Yellow;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/**
 * @Program: MainConfigOfProfile
 * @Author: baichen
 * @Description: Profile配置类
 * Spring为我们提供的可以根据当前环境，动态的激活和切换一系列组件的功能，如：
     * 不同环境：开发环境、测试环境、生产环境；
     * 数据源： (/A)      (/B)     (/C)；
 * @Profile： 指定组件在哪个环境的情况下才能被注册到容器中，不指定的话任何环境下都能注册这个组件

 * 1）、加了环境标识的bean，只有这个环境被激活的时候才能注册到容器中,默认是default环境:@Profile("default")
 * 2）、写在配置类上，只有是指定的环境的时候，整个配置类里面的所有配置才能开始生效:@Profile("prod")
 * 3）、没有标注环境标识的bean在任何环境下都是加载的(即在Yellow上去掉@Profile)；
 * 测试类：{@link com.baichen.test.IOCTest_Profile}
 */
//@Profile("prod")
@PropertySource("classpath:/db.properties")
@Configuration
// 实现值解析器接口，完成值的解析
public class MainConfigOfProfile implements EmbeddedValueResolverAware {
    @Value("${db.user}")
    private String user;

    private StringValueResolver valueResolver;
    private String driverClass;

    @Profile("test")
    @Bean
    public Yellow yellow() {
        return new Yellow();
    }

    // 多个数据源
    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/dbgirl");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/o2o");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    // EmbeddedValueResolverAware,用于解析类似 $,# 等特殊值
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.valueResolver = resolver;
        // db.properties文件中的变量名
        driverClass = valueResolver.resolveStringValue("${db.driverClass}");
    }
}
