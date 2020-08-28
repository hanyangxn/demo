package com.hy.demo.annotation;

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

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author hy
 * @description:
 * @date 2020/08/18
 */
@Aspect
public class TestLogAspect {
    final static Logger log = LoggerFactory.getLogger(TestLogAspect.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut("@annotation(com.hy.demo.annotation.TestLog)")
    public void annotationPointcut() {

    }

    @Around("annotationPointcut()")
    public Object systemLogAround(ProceedingJoinPoint point) throws Throwable {
        Long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        Long time = System.currentTimeMillis() - beginTime;
        outputSysLog(point, time);
        return result;
    }

    public void outputSysLog(ProceedingJoinPoint joinPoint,Long time){
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

    @AfterReturning(returning = "result", pointcut = "annotationPointcut()")
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
