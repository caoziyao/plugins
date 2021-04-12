package com.zel.market.app.aspect;

/**
 * AOP 用来干嘛：
 * 将公用业务剥离开。例如日志，安全。
 *
 * Spring的Aop就是将公共的业务 (日志 , 安全等) 和领域业务结合起来 , 当执行领域业务时 , 将会把公共业务加进来 .
 * 实现公共业务的重复利用 . 领域业务更纯粹 , 程序猿专注领域业务 , 其本质还是动态代理
 */
//@Aspect
//public class AnnotationPointcut {
//    @Before("execution(* Service.UserServiceImpl.*(..))")
//    public void before(){
//        System.out.println("---------方法执行前---------");
//    }
//    @After("execution(* Service.UserServiceImpl.*(..))")
//    public void after(){
//        System.out.println("---------方法执行后---------");
//    }
//    @Around("execution(* Service.UserServiceImpl.*(..))")
//    public void around(ProceedingJoinPoint jp) throws Throwable {
//        System.out.println("环绕前");
//        System.out.println("签名:"+jp.getSignature());
//        //执行目标方法proceed
//        Object proceed = jp.proceed();
//        System.out.println("环绕后");
//        System.out.println(proceed);
//    }
//}