package com.nvoe.intelligentmanagementsystem.aop;


import com.alibaba.fastjson.JSONObject;
import com.nvoe.intelligentmanagementsystem.Mapper.OperateMapper;
import com.nvoe.intelligentmanagementsystem.POJO.OperateLog;
import com.nvoe.intelligentmanagementsystem.utils.JwtUtils.Jwt;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {
    @Autowired
    HttpServletRequest request;
    @Autowired
    OperateMapper operateMapper;
    @Around("@annotation(com.nvoe.intelligentmanagementsystem.anno.Log)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String token = request.getHeader("Authorization");
        Claims claims = Jwt.parseJWT(token);
        Integer operateUser = (Integer) claims.get("id");
        LocalDateTime operateTime = LocalDateTime.now();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);
        Long beginTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        String returnValue = JSONObject.toJSONString(result);
        Long costTime = endTime - beginTime;
        operateMapper.insert(new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime));
        log.info("operateUser:{},operateTime:{},className:{},methodName:{},methodParams:{},returnValue:{},costTime:{}",operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);

        return result;
    }




}

