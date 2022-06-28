package com.example.demo.proxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectBean {

    @Pointcut("execution (* com.example.demo.proxy.ProxyBean.*(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        Object proceed = null;
        try {
            System.out.println("before");
            proceed = proceedingJoinPoint.proceed();
            System.out.println("after");
            return proceed;
        } catch (Throwable t) {
            System.out.println("afterThrow");
            t.printStackTrace();
        }finally {
            System.out.println("afterReturn");
        }
        return proceed;
    }
}
