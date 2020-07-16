package com.example.demo2.testReflectionUtil.service.impl;

import com.example.demo2.testReflectionUtil.enums.DateType;
import com.example.demo2.testReflectionUtil.service.ReflectionService;
import com.example.demo2.util.DateUtil;
import com.example.demo2.util.ReflectionUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

@Service("service1")
@Order(1)
public class ReflectionService1 implements ReflectionService {
    @Override
    public String sinceThisYear(DateType dateType) {
        LocalDate sinceThisYear = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
        String s = DateUtil.localDateToString(sinceThisYear, DateUtil.DATE_FORMAT);
        return "service1---->" + s;
    }

    @Override
    public String otherType(DateType dateType) {
        String o = (String) ReflectionUtil.invokeMethodByName(new DateUtil(),
                dateType.getMethodName(), new Object[]{new Date(),
                        dateType.getCount(), DateUtil.DATE_FORMAT});
        return "service1---->" + o;
    }

    @Override
    public String sinceThis(DateType dateType) {
        return "service1---->sinceThis";
    }
}
