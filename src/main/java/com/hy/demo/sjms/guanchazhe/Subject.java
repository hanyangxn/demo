package com.hy.demo.sjms.guanchazhe;

/**
 * @author hy
 * @description: 通知者接口
 * @date 2020/08/07
 */
public interface Subject {
     void add(Observer observer);
     void del(Observer observer);
     void notifyObject();

     void setAction(String action);
     String getAction();

}
