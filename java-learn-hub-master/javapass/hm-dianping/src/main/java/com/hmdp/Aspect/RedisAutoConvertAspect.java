package com.hmdp.Aspect;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

@Aspect
@Component
public class RedisAutoConvertAspect {
    private final ObjectMapper objectMapper;
    public RedisAutoConvertAspect(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Pointcut("@annotation(com.hmdp.annotation.RedisAutoConvert)")
    public void redisAutoPointcut(){}

    @Around("redisAutoPointcut()")
    public Object redisAutoConvert(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        if(result==null){
            return null;
        }

        // 获取方法签名信息，包含方法名、参数类型、返回类型等
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 通过签名获取实际的方法对象，可以进一步获取方法上的注解、参数等信息
        Method method = methodSignature.getMethod();
        // 获取方法的泛型返回类型，用于支持复杂类型的反序列化
        Type returnType = method.getGenericReturnType();

        TypeReference<?> typeReference = new TypeReference<Object>() {
            @Override
            public Type getType() {
                return returnType;
            }
        };
        result = objectMapper.convertValue(result, typeReference);
        return result;
    }

}
