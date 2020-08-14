package com.hy.demo.sjms.guanchazhe;

import java.util.Vector;

/**
 * @author hy
 * @description:
 * @date 2020/08/07
 */
public class QSubject implements Subject {

    Vector<Observer> vector = new Vector<>();
    private String action;

    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObject() {
        for (Observer o : vector) {
            o.update(this);
        }
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String getAction() {
        return action;
    }
}
