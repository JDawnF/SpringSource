package com.baichen.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Program: UserService
 * @Author: baichen
 * @Description:
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Transactional      // 光有这个还不行
    public void insertUser(){
        userDao.insert();
        //otherDao.other();xxx
        System.out.println("插入完成");
        int i = 10/0;       // 如果没有事务注解,事务管理器和@EnableTransactionManagement注解，则还是可以插入，所以要在事务配置类中加入注解驱动
    }
}
