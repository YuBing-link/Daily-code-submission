package com.hmdp.Aspect;

import com.hmdp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import com.hmdp.annotation.TimeLog;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class TimeAop {
    @Pointcut("@annotation(timeLog)")
    public void timeLogPointcut(TimeLog timeLog){}
    @Around("timeLogPointcut(timeLog)")
    public Object time(ProceedingJoinPoint joinPoint, TimeLog timeLog)  {

            Object result = null;
            try {
                log.info("{}", timeLog.value());
                result = joinPoint.proceed();
                // 如果需要对特定类型的对象进行处理，可以添加类型检查
                if (result instanceof User) {
                    User user = (User) result;
                    user.setUpdateTime(LocalDateTime.now());
                    user.setCreateTime(LocalDateTime.now());
                    return user;
                }
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            return result;

    }

}
