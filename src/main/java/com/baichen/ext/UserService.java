package com.baichen.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @Program: UserService
 * @Author: baichen
 * @Description: ����������
 */
@Service
public class UserService {
    // classes��ʾ�������¼�,����д���
    @EventListener(classes = {ApplicationEvent.class})
    public void listen(ApplicationEvent event) {
        // ��ȡ���¼�
        System.out.println("UserService�¼�:" + event);
    }
}
