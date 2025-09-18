package com.sky.interceptor;

import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.properties.JwtProperties;
import com.sky.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenUserInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getUserTokenName());

        //2、校验令牌
        try {
//            log.info("jwt校验:{}", token);
//            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
//            Long userIdStr = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
//
//            // 校验 userId 是否为空或非法格式
//            if (userIdStr == null || userIdStr.trim().isEmpty()) {
//                log.warn("JWT中USER_ID为空");
//                response.setStatus(401);
//                return false;
//            }
//
//            // 尝试解析为 Long
//            Long userId = null;
//            try {
//                userId = Long.valueOf(userIdStr);
//            } catch (NumberFormatException e) {
//                log.error("JWT中USER_ID格式不合法: {}", userIdStr, e);
//                response.setStatus(401);
//                return false;
//            }
//
//            BaseContext.setCurrentId(userId);
//            log.info("当前用户id: {}", userId);
//            return true;
//        } catch (Exception ex) {
//            log.error("JWT解析失败: {}", ex.getMessage(), ex);
//            response.setStatus(401);
//            return false;
//        }

                log.info("jwt校验:{}", token);
                // 解析 JWT，获取 Claims
                Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
                // 从 Claims 中获取用户 ID 并转换为 Long 类型
                Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
                // 将用户 ID 设置到 BaseContext 中，供后续业务使用
                BaseContext.setCurrentId(userId);
                log.info("当前用户id:{}", userId);
                // 校验通过，放行
                return true;
            } catch (Exception ex) {
                log.error("JWT 校验异常", ex);
                // 校验不通过，响应 401 状态码
                response.setStatus(401);
                return false;
            }

    }
}