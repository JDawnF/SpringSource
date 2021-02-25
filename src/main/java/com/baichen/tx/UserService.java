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

    @Transactional      // �������������
    public void insertUser(){
        userDao.insert();
        //otherDao.other();xxx
        System.out.println("�������");
        int i = 10/0;       // ���û������ע��,�����������@EnableTransactionManagementע�⣬���ǿ��Բ��룬����Ҫ�������������м���ע������
    }
}
