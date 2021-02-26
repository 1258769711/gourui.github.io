package com.cy.pj.common.web;

import com.cy.pj.common.pojo.JsonResult;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 此注解描述的类为全局异常处理类
 */
@RestControllerAdvice  //@ControllerAdvice+@ResponseBody
public class GlobalExceptionHandler {
       private static final Logger log=
               LoggerFactory.getLogger(GlobalExceptionHandler.class);

       @ExceptionHandler(ShiroException.class)
       public JsonResult doShiroException(ShiroException e){
           e.printStackTrace();
           JsonResult r=new JsonResult();
           r.setState(0);
           if(e instanceof UnknownAccountException){
               r.setMessage("账户不存在");
           }else if(e instanceof IncorrectCredentialsException){
               r.setMessage("密码不正确");
           }else if(e instanceof LockedAccountException){
               r.setMessage("账户已被锁定");
           }else if(e instanceof AuthorizationException){
               r.setMessage("没有权限");
           }else{
               r.setMessage("认证或授权失败");
           }
           return r;
       }
      /**@ExceptionHandler注解描述的方法为异常处理方法,
       * 注解中定义的异常类型为方法可以处理的异常类型.*/
       @ExceptionHandler(RuntimeException.class)
       public JsonResult doHandleRuntimeException(RuntimeException e){
           e.printStackTrace();
           log.error("exception msg is {}",e.getMessage());
           return new JsonResult(e);
       }
       //......
}
