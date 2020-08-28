package com.hy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hy
 * @description:
 * @date 2020/08/14
 */
@RestController
public class KafkaController {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @RequestMapping("message/send")
    public String send(String msg){
        kafkaTemplate.send("list", msg); //使用kafka模板发送信息
        return "success";
    }

}
