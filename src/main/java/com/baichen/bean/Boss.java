package com.baichen.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Program: Boss
 * @Author: baichen
 * @Description:
 */
//Ĭ�ϼ���ioc�����е��������������������޲ι��������������ٽ��г�ʼ����ֵ�Ȳ���
@Component
public class Boss {
    private Car car;

    //������Ҫ�õ���������Ǵ������л�ȡ
//������ֻ��һ���вι�����������вι�������@Autowired����ʡ�ԣ�����λ�õ�������ǿ����Զ��������л�ȡ
//    @Autowired
//    public Boss(@Autowired Car car) {
    public Boss(Car car) {
        this.car = car;
        System.out.println("Boss...�вι�����");
    }

    public Car getCar() {
        return car;
    }

//    @Autowired
    //��ע�ڷ�����Spring����������ǰ����ʱ���ͻ���÷�������ɸ�ֵ��
    //����ʹ�õĲ������Զ������͵�ֵ��ioc�����л�ȡ
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss [car=" + car + "]";
    }
}
