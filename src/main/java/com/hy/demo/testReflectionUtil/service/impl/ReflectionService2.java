package com.hy.demo.testReflectionUtil.service.impl;

import com.hy.demo.testReflectionUtil.service.ReflectionService;
import com.hy.demo.testReflectionUtil.enums.DateType;
import com.hy.demo.util.DateUtil;
import com.hy.demo.util.ReflectionUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

@Service("service2")
@Order(2)
public class ReflectionService2 implements ReflectionService {
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
        return "service2---->sinceThis";
    }
}
