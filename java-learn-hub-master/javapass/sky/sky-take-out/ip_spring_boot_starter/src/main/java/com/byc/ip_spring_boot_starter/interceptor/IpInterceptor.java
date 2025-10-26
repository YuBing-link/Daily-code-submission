package com.byc.ip_spring_boot_starter.interceptor;

import com.byc.ip_spring_boot_starter.Server.ipServer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IpInterceptor implements HandlerInterceptor {
    @Autowired
    private ipServer ipServer;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ipServer.ipCountServer();
        return true;
    }
}