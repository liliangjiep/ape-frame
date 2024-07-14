package com.ape.log;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @Author : 李良杰
 * @Description :
 * @Date : 2024/7/14 22:08
 * version :1.0
 **/
@Aspect
@Slf4j
@Component
@ConditionalOnProperty(name = {"log.aspect.enable"}, havingValue = "true", matchIfMissing = true)
public class LogAspect {

    // 对Controller和Service设置切点
    @Pointcut("execution(* com.ape.*.controller.*Controller.*(..)) || execution(* com.ape.*.service.*Service.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 1.获取请求参数
        Object[] reqArgs = pjp.getArgs();
        String req = new Gson().toJson(reqArgs);
        // 2.获取方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String methodName = methodSignature.getDeclaringType().getName() + "."
                + methodSignature.getName();
        log.info("method:{},req:{}", methodName, req);
        long startTime = System.currentTimeMillis();
        // 切面需要抛出异常，不能try-catch，不然相当于将业务方法所有异常给捕获了
        Object responseObj = pjp.proceed();
        String resp = new Gson().toJson(responseObj);
        long endTime = System.currentTimeMillis();
        log.info("method:{},resp:{}", methodName, resp);
        log.info("method:{},costTime:{}", methodName, endTime - startTime);
        // 之前无返回，导致获取完数据被日志切面截胡，导致返回为null
        return responseObj;
    }
}
