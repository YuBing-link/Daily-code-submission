package com.hmdp.Aspect;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.util.LinkedHashMap;

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
        System.out.println("切面开始执行");
        Object result = joinPoint.proceed();
        System.out.println("切面捕获到的结果类型: " + (result != null ? result.getClass() : "null"));
        if(result == null){
            return null;
        }
        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        Class<?> targetType = null;
        // 查找最后一个参数是否为Class类型
        for (int i = args.length - 1; i >= 0; i--) {
            if (args[i] instanceof Class<?>) {
                targetType = (Class<?>) args[i];
                break;
            }
        }
        // 如果找到了目标类型且结果是LinkedHashMap，则进行转换
        if (targetType != null && result instanceof LinkedHashMap) {
            try {
                result = objectMapper.convertValue(result, targetType);
                System.out.println("成功转换为目标类型: " + targetType.getSimpleName());
            } catch (Exception e) {
                System.err.println("类型转换失败: " + e.getMessage());
                throw e;
            }
        }

        System.out.println("最终返回类型: " + (result != null ? result.getClass() : "null"));
        return result;
    }





}
