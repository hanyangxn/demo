package com.hy.demo.controller;


import com.hy.demo.task.ChatTask;
import com.hy.demo.task.HelloTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledFuture;

/**
 * @author hy
 * @description:
 * @date 2020/09/02
 */
@RestController
@RequestMapping("/task")
public class ScheduleController {

    private static String DEFAULT_CRON = "0/5 * * * * ?";

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private final static HelloTask helloTask = new HelloTask();

    private final static ChatTask chatTask  = new ChatTask();

    private  ScheduledFuture futureHello;

    private  ScheduledFuture futureChat;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    @RequestMapping("/startHello")
    public String startCron() {
        futureHello = threadPoolTaskScheduler.schedule(helloTask, new CronTrigger(DEFAULT_CRON));
        System.out.println("start hello");
        return "startHello";
    }

    @RequestMapping("/stopHello")
    public String stopCron() {
        if (futureHello != null) {
            futureHello.cancel(true);
        }
        System.out.println("stop hello");
        return "stopHello";
    }

    @RequestMapping("/changeHello10")
    public String changeCron10() {
        stopCron();// 先停止，在开启.
        futureHello = threadPoolTaskScheduler.schedule(helloTask, new CronTrigger("*/10 * * * * *"));
        System.out.println("changeCron10");
        return "changeCron10";
    }
    @RequestMapping("/startChat")
    public String startChat() {
        futureChat = threadPoolTaskScheduler.schedule(chatTask, new CronTrigger(DEFAULT_CRON));
        System.out.println("start chat");
        return "startChat";
    }

    @RequestMapping("/stopChat")
    public String stopChat() {
        if (futureChat != null) {
            futureChat.cancel(true);
        }
        System.out.println("stop chat");
        return "stopChat";
    }

    @RequestMapping("/changeChat10")
    public String changeChat10() {
        stopChat();// 先停止，在开启.
        futureChat = threadPoolTaskScheduler.schedule(chatTask, new CronTrigger("*/10 * * * * *"));
        System.out.println("changeChat10");
        return "changeChat10";
    }
}

