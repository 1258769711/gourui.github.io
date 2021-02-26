package com.cy.pj.common.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.LocalTime;
import java.util.Map;

/**
 * 定义spring web 模块中的拦截器，通过此拦截器对handler中某些方法的
 * 进行时间访问限制。
 */
@Slf4j //lombok会在当前java文件编译成class文件时，会自动在类文件中添加一个log对象
public class TimeAccessInterceptor implements HandlerInterceptor {
   //private static final Logger log=
           // LoggerFactory.getLogger(TimeAccessInterceptor.class);

    /**preHandle方法会在目标handler方法执行之前进行访问拦截
     * 方法返回值为true时表示请求放行，false表示请求到此结束，
     * 不再去执行handler中的方法
     * */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
       //testRequestInfo(request,handler);
       LocalTime now=LocalTime.now();//JDK8中的时间对象
       int hour=now.getHour();//获取当前时间对应小时
       //System.out.println("hour="+hour);
       log.info("hour {}",hour);
       if(hour<=6||hour>=22)
           throw new RuntimeException("请在6~10点进行访问");
        return true;
    }
    //测试方法参数，理解方法中参数含义
    private void testRequestInfo(HttpServletRequest request, Object handler){
        //获取请求url
        String uri=request.getRequestURI();
        System.out.println("request.uri="+uri);
        //获取请求中的参数(客户端向服务端发送请求时所有参数都会封装到ParameterMap对象)
        Map<String,String[]>  map=request.getParameterMap();
        System.out.println("request.param.map.keys="+map.keySet());
        //获取封装了目标handler(@Controller)和method信息的HandlerMethod对象
        HandlerMethod handlerMethod=(HandlerMethod) handler;
        Method method=handlerMethod.getMethod();//获取了控制层handler对应的方法对象
        System.out.println("method.name->"+method.getName());
        System.out.println("==preHandle==");
    }
}
