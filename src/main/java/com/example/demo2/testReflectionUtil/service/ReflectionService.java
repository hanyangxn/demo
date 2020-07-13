package com.example.demo2.testReflectionUtil.service;

import com.example.demo2.testReflectionUtil.enums.DateType;

public interface ReflectionService {
    String sinceThisYear(DateType dateType);

    String otherType(DateType dateType);

    String sinceThis(DateType dateType);
}
