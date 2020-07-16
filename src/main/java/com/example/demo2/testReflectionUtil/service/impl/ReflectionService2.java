package com.example.demo2.testReflectionUtil.service.impl;

import com.example.demo2.testReflectionUtil.enums.DateType;
import com.example.demo2.testReflectionUtil.service.ReflectionService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service("service2")
@Order(2)
public class ReflectionService2 implements ReflectionService {
    @Override
    public String sinceThisYear(DateType dateType) {
        return "service2---->sinceThisYear";
    }

    @Override
    public String otherType(DateType dateType) {
        return "service2---->otherType";
    }

    @Override
    public String sinceThis(DateType dateType) {
        return "service2---->sinceThis";
    }
}
