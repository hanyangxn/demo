package com.hy.demo.annotation;

import com.alibaba.fastjson.JSON;
import com.hy.demo.dto.UserDto;
import com.hy.demo.util.ParamChekErrorException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAspect {

    final static Logger logger = LoggerFactory.getLogger(TestAspect.class);


    @Pointcut("@annotation(com.hy.demo.annotation.Test)")
    public void annotationPointcut() {

    }

    @Around("annotationPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] params = methodSignature.getParameterNames();// 获取参数名称
        Object[] args = joinPoint.getArgs();// 获取参数值
        if (null == params || params.length == 0){
            String mes = "Using Token annotation, the token parameter is not passed, and the parameter is not valid.";
            logger.info(mes);
            throw new Exception(mes);
        }
        if (null != args){
            String s = JSON.toJSONString(args);

            logger.info(s);
            for (Object arg:args) {
                UserDto userDto = (UserDto)arg;
                if (userDto.getAge()<10){
                    throw new ParamChekErrorException("年龄不能小于10");
                }
            }
        }
        return joinPoint.proceed();
    }

}
