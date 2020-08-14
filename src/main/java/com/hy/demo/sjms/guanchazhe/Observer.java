package com.hy.demo.sjms.guanchazhe;

/**
 * @author hy
 * @description: 观察者接口
 * @date 2020/08/07
 */
public abstract class Observer {
     String name;

    public Observer(String name) {
        this.name = name;
    }

    public abstract void update(Subject subject);
}
