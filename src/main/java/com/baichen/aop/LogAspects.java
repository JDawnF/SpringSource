package com.baichen.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @Program: LogAspects
 * @Author: baichen
 * @Description: AOP��־��ӡ��
 */
@Aspect     // ��ʾ����һ��������
public class LogAspects {

    //��ȡ�������������ʽ,����Ҫ�� @Before ��ע�������ע��ͷ���
    //1����������
    //2����������������
    @Pointcut("execution(public int com.baichen.aop.MathCalculator.*(..))")
    public void pointCut(){};

    //@Before��Ŀ�귽��֮ǰ���룻�������ʽ��ָ�����ĸ��������룩
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();    // ��ȡ������
        // ��ӡ�˷�����
        System.out.println("" + joinPoint.getSignature().getName() + "���С�����@Before:�����б��ǣ�{" + Arrays.asList(args) + "}");
    }

    @After("com.baichen.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint){
        System.out.println(""+joinPoint.getSignature().getName()+"����������@After");
    }

    //JoinPointһ��Ҫ�����ڲ�����ĵ�һλ,returning��ʾ����ֵ�����ｫ����ֵ��ֵ��result
    @AfterReturning(value="pointCut()",returning="result")
    public void logReturn(JoinPoint joinPoint,Object result){
        System.out.println(""+joinPoint.getSignature().getName()+"�������ء�����@AfterReturning:���н����{"+result+"}");
    }

    // ͬ��throwing��ʾ�׳����쳣
    @AfterThrowing(value="pointCut()",throwing="exception")
    public void logException(JoinPoint joinPoint,Exception exception){
        System.out.println(""+joinPoint.getSignature().getName()+"�쳣�������쳣��Ϣ��{"+exception+"}");
    }
}
