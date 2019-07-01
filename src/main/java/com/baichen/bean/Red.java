package com.baichen.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @Program: Yellow
 * @Author: baichen
 * @Description:
 */
@Component
public class Red implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("�����IOC��" + applicationContext);
        this.applicationContext=applicationContext;
    }

    public void setBeanName(String name) {
        System.out.println("��ǰbean�����֣�"+name);
    }

//    ���ڽ������� $,# ������ֵ
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String resolveStringValue = resolver.resolveStringValue("���${os.name} ����#{20*11}");
        System.out.println("�������ַ����ǣ�"+resolveStringValue);
    }
}
