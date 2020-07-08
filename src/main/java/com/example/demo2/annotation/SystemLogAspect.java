package com.example.demo2.annotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author
 * 记录日志-不影响性能下同步记录
 * 可异步
 */
@Aspect
@Component
public class SystemLogAspect {

    private static final Logger log = LoggerFactory.getLogger(SystemLogAspect.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut("@annotation(com.example.demo2.annotation.SystemLog)")
    public void systemLogPointCut(){}

    @Around("systemLogPointCut()")
    public Object systemLogAround(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        outputSysLog(point, time);
        return result;
    }

    public void outputSysLog(ProceedingJoinPoint joinPoint, long time) {
        LocalVariableTableParameterNameDiscoverer localVariable = new LocalVariableTableParameterNameDiscoverer();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        String[] paramNames = localVariable.getParameterNames(signature.getMethod());
        String methodLogExplain = getControllerMethodDescription(joinPoint);
        Object args[] = joinPoint.getArgs();
        Map<String, Object> paramMap = new LinkedHashMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            paramMap.put(paramNames[i], null != args[i] ? args[i].toString() : null);
        }
        log.info(" explain: " + methodLogExplain + " class: " + className +
                " method: " + methodName + " param： " + toJsonStringUnformat(paramMap) + " cost:" + time + "ms");
    }

    /**
     * 获取注解值
     */
    private static String getControllerMethodDescription(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(SystemLog.class).value();
    }

    @AfterReturning(returning = "result", pointcut = "systemLogPointCut()")
    public void doAfterReturning(Object result) throws Throwable {
        // 处理完请求，返回内容
        log.info("response result : " + toJsonStringUnformat(result));
    }

    /**
     * Object to Json
     * un format
     * */
    public static String toJsonStringUnformat (Object obj){
        if (obj == null){
            return null;
        }
        try {
            return obj instanceof String ? obj.toString() : objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.warn("Parse object to String error", e);
            return null;
        }
    }
}
