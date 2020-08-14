package com.hy.demo.sjms.guanchazhe;

/**
 * @author hy
 * @description:
 * @date 2020/08/07
 */
public class Client {
    public static void main(String[] args) {
        Subject subject = new QSubject();
        StockServer observer = new StockServer("adam");
        StockServer opserver = new StockServer("sam");
        subject.add(observer);
        subject.add(opserver);
        subject.setAction("快跑啊");
        subject.notifyObject();
    }

}
