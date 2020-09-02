package com.hy.demo.test.fanxing;

/**
 * @author hy
 * @description:
 * @date 2020/08/31
 */
class Info<T>{
    private T var ;     // 定义泛型变量
    public void setVar(T var){
        this.var = var ;
    }
    public T getVar(){
        return this.var ;
    }

//    @Override
//    public String toString() {
//        return "Info{" +
//                "var=" + var +
//                '}';
//    }

    @Override
    public String toString() {
        return this.getVar().toString();
    }
};

