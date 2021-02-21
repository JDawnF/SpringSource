package com.baichen.test;

import com.baichen.bean.Yellow;
import com.baichen.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @Program: IOCTest_Profile
 * @Author: baichen
 * @Description: 测试Profile
 */
public class IOCTest_Profile {
    //两种切换环境的方法：
    //1、使用命令行动态参数: 在虚拟机参数位置加载 -Dspring.profiles.active=test
    //2、代码的方式激活某种环境,但是AnnotationConfigApplicationContext不能使用有参构造器,否则会加载配置类并刷新，但是此时还未加载好对应的环境
   /**
   * public AnnotationConfigApplicationContext(DefaultListableBeanFactory beanFactory) {
		super(beanFactory);
		this.reader = new AnnotatedBeanDefinitionReader(this);
		this.scanner = new ClassPathBeanDefinitionScanner(this);
	}
   */
    @Test
    public void test01() {
        // 要用无参构造器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //1、创建一个applicationContext
        //2、设置需要激活的环境，可以激活多个环境，这里是激活dev和test环境
        applicationContext.getEnvironment().setActiveProfiles("dev", "test");
        //3、注册主配置类
        applicationContext.register(MainConfigOfProfile.class);
        //4、启动刷新容器
        applicationContext.refresh();

        String[] namesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String string : namesForType) {
            System.out.println(string);
        }

        Yellow bean = applicationContext.getBean(Yellow.class);
        System.out.println(bean);
        applicationContext.close();
    }
}
