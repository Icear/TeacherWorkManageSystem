package com.filter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于对调用REST API的请求进行token验证，同时生成对应用户身份
 */
public class RESTAuthorizeFIlter implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        /*
         *  获得Token并进行比对
         *  如果Token有效则将对应Teacher信息和Administrator信息放入Session中
         *  如果Token无效则返回401代码要求登陆
         */

        return false;
    }
}
