package com.cy.pj.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/***
 * 通过此工具类获取请求,响应等对象信息
 */
public class ServletUtil {

    /**获取请求对象*/
    public static HttpServletRequest getRequest(){
        return getServletRequestAttributes().getRequest();
    }
    /**通过RequestContextHolder类型获取请求属性*/
    public static ServletRequestAttributes getServletRequestAttributes(){
        return (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
    }
}
