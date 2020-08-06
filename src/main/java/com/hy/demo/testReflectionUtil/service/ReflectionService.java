package com.hy.demo.testReflectionUtil.service;

import com.hy.demo.testReflectionUtil.enums.DateType;

public interface ReflectionService {
    String sinceThisYear(DateType dateType);

    String otherType(DateType dateType);

    String sinceThis(DateType dateType);
}
