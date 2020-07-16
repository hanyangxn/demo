package com.example.demo2.testReflectionUtil.service.impl;

import com.example.demo2.testReflectionUtil.enums.DateType;
import com.example.demo2.testReflectionUtil.service.ReflectionService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;


@Service("service3")
@Order(3)
public class ReflectionService3 implements ReflectionService {
    @Override
    public String sinceThisYear(DateType dateType) {
        return "service3---->sinceThisYear";
    }

    @Override
    public String otherType(DateType dateType) {
        return "service3---->otherType";
    }

    @Override
    public String sinceThis(DateType dateType) {
        return "service3---->sinceThis";
}
}
