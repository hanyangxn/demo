package com.example.demo2.testReflectionUtil.controller;

import com.example.demo2.testReflectionUtil.bo.RequestBo;
import com.example.demo2.testReflectionUtil.enums.DateType;
import com.example.demo2.testReflectionUtil.enums.ServiceType;
import com.example.demo2.testReflectionUtil.service.ReflectionService;
import com.example.demo2.util.ApplicationContextGetBeanHelper;
import com.example.demo2.util.ReflectionUtil;
import com.example.demo2.util.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ReflectionController {

    @Autowired
    private Map<String, ReflectionService> map;

    @RequestMapping("/reflectionTest")
    public String reflectionTest(@RequestBody RequestBo bo) {

        ServiceType type = bo.getType();
        DateType dateType = bo.getDateType();

        ReflectionService reflectionService = map.get(type.getName());
        String handleMethodName = dateType.getHandleMethodName();
        String s = (String) ReflectionUtil.invokeMethodByName(reflectionService, handleMethodName, new Object[]{dateType});
        return s;
    }

    @RequestMapping("/applicationTest")
    public String applicationTest(@RequestBody RequestBo bo) {
        DateType dateType = bo.getDateType();
        ReflectionService reflectionService1 = (ReflectionService) ApplicationContextGetBeanHelper.getBean("service1");
        String handleMethodName = dateType.getHandleMethodName();
        String s = (String) ReflectionUtil.invokeMethodByName(reflectionService1, handleMethodName, new Object[]{dateType});
        return s;

    }
    @RequestMapping("/springTest")
    public String springTest(@RequestBody RequestBo bo) {
        DateType dateType = bo.getDateType();
        ReflectionService reflectionService1 = (ReflectionService) SpringUtils.getBean("service1");
        String handleMethodName = dateType.getHandleMethodName();
        String s = (String) ReflectionUtil.invokeMethodByName(reflectionService1, handleMethodName, new Object[]{dateType});
        return s;

    }
}
