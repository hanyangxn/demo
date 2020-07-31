package com.example.demo2.emun;


public enum Type {
    //  万份收益
    thousandsOfIncome ("thousandsOfIncome","thousandsOfIncomeimpl"),
    //  七日收益
    sevenDayYield("sevenDayYield",""),
    //  累计收益
    accumulatedIncome("accumulatedIncome",""),
    //  单位净值
    unitNet("unitNet",""),
    //  累计净值
    accumulatedNet("accumulatedNet",""),
    //  指数收益
    indexIncome("indexIncome",""),
    //  指数收益
    indexUpAndDownImpl("indexUpAndDownImpl",""),
    NULL(null,null)
    ;
    private  String name;

    private  String str;

    Type(String name, String str) {
        this.name = name;
        this.str = str;
    }

    public String getName() {
        return name;
    }
}
