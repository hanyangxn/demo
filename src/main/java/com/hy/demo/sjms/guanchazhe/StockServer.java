package com.hy.demo.sjms.guanchazhe;

/**
 * @author hy
 * @description: 具体观察者
 * @date 2020/08/07
 */
public class StockServer extends Observer {

    public StockServer(String name) {
        super(name);
    }

    @Override
    public void update(Subject subject) {
        System.out.println(name+"收到了"+subject.getAction());
    }
}
