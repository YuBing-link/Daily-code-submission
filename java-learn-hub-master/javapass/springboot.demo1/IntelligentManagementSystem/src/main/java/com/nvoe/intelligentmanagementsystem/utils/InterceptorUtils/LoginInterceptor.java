package com.nvoe.intelligentmanagementsystem.utils.InterceptorUtils;

import com.nvoe.intelligentmanagementsystem.utils.JwtUtils.Jwt;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        if (url.contains("/login")) {
            return true;
        }
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"msg\":\"令牌无效\"}");
            return false;
        }
        token = token.substring(7); // 移除 Bearer 前缀

        // 2. 解析 JWT 令牌（调用 JwtUtils 工具类）
        try {
            Claims claims = Jwt.parseJWT(token); // 假设 Jwt.parseJWT 是解析方法
            // 可以将 claims 存入 request，供后续使用
            request.setAttribute("claims", claims);
            return true;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"msg\":\"令牌解析失败\"}");
            return false;
        }

    }

}
