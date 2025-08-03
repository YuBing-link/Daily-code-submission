package com.nvoe.intelligentmanagementsystem.utils.FilterUtils;

import com.nvoe.intelligentmanagementsystem.utils.JwtUtils.Jwt;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class JwtFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //请求对象
        HttpServletRequest req = (HttpServletRequest) request;
        //请求响应对象
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURL().toString();

        if (url.contains("/login") ){
            chain.doFilter(request, response);
            return;
        }
        String token = req.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("令牌无效");
            return;
        }
        token = token.substring(7);
        try {
            Claims claims = Jwt.parseJWT(token);
            req.setAttribute("claims", claims);
            chain.doFilter(request, response);

        }catch (Exception e){

            log.error("令牌解析失败: ", e);
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("令牌解析失败");
        }



    }



}
