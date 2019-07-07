package com.baichen.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @Program: UserService
 * @Author: baichen
 * @Description: 监听器子类
 */
@Service
public class UserService {
    // classes表示监听的事件,可以写多个
    @EventListener(classes = {ApplicationEvent.class})
    public void listen(ApplicationEvent event) {
        // 获取到事件
        System.out.println("UserService事件:" + event);
    }
}
