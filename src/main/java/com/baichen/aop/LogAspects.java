package com.baichen.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @Program: LogAspects
 * @Author: baichen
 * @Description: AOP日志打印类
 */
@Aspect     // 表示这是一个切面类
public class LogAspects {

    //抽取公共的切入点表达式,否则要在 @Before 等注解上面标注类和方法
    //1、本类引用
    //2、其他的切面引用
    @Pointcut("execution(public int com.baichen.aop.MathCalculator.*(..))")
    public void pointCut(){};

    //@Before在目标方法之前切入；切入点表达式（指定在哪个方法切入）
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();    // 获取参数表
        // 打印了方法名
        System.out.println("" + joinPoint.getSignature().getName() + "运行。。。@Before:参数列表是：{" + Arrays.asList(args) + "}");
    }

    @After("com.baichen.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint){
        System.out.println(""+joinPoint.getSignature().getName()+"结束。。。@After");
    }

    //JoinPoint一定要出现在参数表的第一位,returning表示返回值，这里将返回值赋值给result
    @AfterReturning(value="pointCut()",returning="result")
    public void logReturn(JoinPoint joinPoint,Object result){
        System.out.println(""+joinPoint.getSignature().getName()+"正常返回。。。@AfterReturning:运行结果：{"+result+"}");
    }

    // 同理，throwing表示抛出的异常
    @AfterThrowing(value="pointCut()",throwing="exception")
    public void logException(JoinPoint joinPoint,Exception exception){
        System.out.println(""+joinPoint.getSignature().getName()+"异常。。。异常信息：{"+exception+"}");
    }
}
