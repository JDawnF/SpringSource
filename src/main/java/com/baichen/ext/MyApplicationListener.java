package com.baichen.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Program: MyApplicationListener
 * @Author: baichen
 * @Description: �������ӿ�ʵ����
 */
@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
    //�������з������¼��Ժ�,��������,ApplicationEvent�Ǽ������¼�
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("�յ��¼���"+event);
    }
}
