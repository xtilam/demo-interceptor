package com.example.demointerceptor.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("interceptor ..." + request.getSession().getAttribute("user"));
        if(null == request.getSession().getAttribute("user")){
            response.sendRedirect("/login");
            return false;
        }else{
            return true;
        }
    }

}