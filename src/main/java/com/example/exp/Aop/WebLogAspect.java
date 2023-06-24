package com.example.exp.Aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Description: WebLogAspect请求日志处理
 * @Author: zsp
 * @Date: 2023/6/24 20:53
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    // 切入点
    @Pointcut("execution(* com.example.exp.Controller..*.*(..))")
    public void pointcut() {}

    // 前置通知
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        // 记录请求开始时间
        startTime.set(System.currentTimeMillis());

        // 接收请求, 记录请求内容
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 记录请求内容
        log.info("URL: " + request.getRequestURL().toString());
        log.info("HTTP_METHOD: " + request.getMethod());
        log.info("IP: " + request.getRemoteAddr());
        log.info("CLASS_METHOD: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("Args: " + Arrays.toString(joinPoint.getArgs()));
    }


    @Around("pointcut()")
    public Object logRequest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around");
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopwatch.stop();
        log.info("{} consumed {}ms", proceedingJoinPoint, stopwatch.getTotalTimeMillis());
        return result;
    }

    // 返回通知
    @AfterReturning(returning = "result", pointcut = "pointcut()")
    public void doAfterReturning(Object result) {
        // 请求返回内容
        log.info("RESPONSE: " + result);
        log.info("SPEND TIME: " + (System.currentTimeMillis() - startTime.get()));

        // 用完之后移除, 避免内存泄漏
        startTime.remove();
    }

    // 异常通知
    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        // 获取类名加方法名
        String name = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        // 记录异常信息
        log.info("Exception_Class_Method: {}, Exception_Message: {}", name, e.getMessage());
    }
}
