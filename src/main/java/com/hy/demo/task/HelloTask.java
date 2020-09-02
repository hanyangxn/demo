package com.hy.demo.task;

import java.util.TimerTask;

/**
 * @author hy
 * @description:
 * @date 2020/09/02
 */
public class HelloTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("hello");
    }
}
