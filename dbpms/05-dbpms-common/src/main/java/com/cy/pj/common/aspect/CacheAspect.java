package com.cy.pj.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
/**
 * 自定义Cache切面
 */
@Aspect
@Component
public class CacheAspect {
    //找到关联Cache对象，后续基于此对象操作cache
    /**缓存数据切入点*/
    @Pointcut("@annotation(com.cy.pj.common.annotation.RequiredCache)")
    public void doCache(){}

    /**清缓存切入点*/
    @Pointcut("@annotation(com.cy.pj.common.annotation.ClearCache)")
    public void doClearCache(){}

    @AfterReturning("doClearCache()")
    public void doAfterReturning(){//目标方法成功结束以后执行
        //清cache
        System.out.println("Clear Cache");
    }

    @Around("doCache()")
    public Object doAround(ProceedingJoinPoint joinPoint)throws  Throwable{
        //1.从cache取数据,取到则直接返回(return)
        System.out.println("Get Data From Cache");
        //2.Cache中没有我们要取的数据，则访问数据库(proceed)
        Object result=joinPoint.proceed();//最终执行目标方法
        //3.将数据存储到cache，下次取时从cache取
        System.out.println("Put Data to Cache");
        return result;
    }

}
