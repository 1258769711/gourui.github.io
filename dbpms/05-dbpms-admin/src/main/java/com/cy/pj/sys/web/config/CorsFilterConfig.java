package com.cy.pj.sys.web.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 在此类中基于过滤器做跨域配置
 */
@Configuration
public class CorsFilterConfig {
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean(){
        System.out.println("==corsFilterRegistrationBean()==");
        //1.基于此对象封装要过滤的url及配置
        UrlBasedCorsConfigurationSource source=
                new UrlBasedCorsConfigurationSource();
        //2.构建跨域配置对象
        CorsConfiguration config=new CorsConfiguration();
        //2.1设置允许跨域访问的uri,假如是"*"可以表示所有
        config.addAllowedOrigin("http://localhost:9000");
        //2.2设置允许哪些请求方式进行跨域访问(GET,DELETE,POST,PUT,PATCH,....)
        config.addAllowedMethod("*");
        //2.3设置允许跨域的请求头信息
        config.addAllowedHeader("*");
        //2.4设置允许携带凭证信息(例如已认证的url,下次再访问服务器时,可能要携带认证信息)
        config.setAllowCredentials(true);
        //2.3注册过滤器的配置
        source.registerCorsConfiguration("/**",config);
        //3.构建FilterRegistrationBean对象,通过此对象注册过滤器
        FilterRegistrationBean<CorsFilter> bean=
                new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);//设置过滤器的优先级
        return bean;
    }
}
