package com.hy.demo.controller;

import com.hy.demo.domain.User;
import com.hy.demo.service.EsService;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/test")
@Controller
public class EsController {

    @Autowired
    private EsService testService;

    @RequestMapping("/dsl")
    @ResponseBody
    public ESDatas<User> DslTest() {

        Map<String, Object> params = new HashMap<String, Object>();
        //设置applicationName1和applicationName2两个变量的值
        params.put("name", "张三");

        ESDatas<User> esDatas = testService.dslOne(params);

        return esDatas;
    }
}