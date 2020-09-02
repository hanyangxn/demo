package com.hy.demo.test.fanxing;

import java.math.BigDecimal;

/**
 * @author hy
 * @description:
 * @date 2020/08/31
 */
public class GenericsDemo14 {
    public static void main(String args[]) {
        BigDecimal year = new BigDecimal("0.1");
        BigDecimal year2 = new BigDecimal("100000");
        BigDecimal add = new BigDecimal("0");
        BigDecimal year3 = null;
        for (int i = 0; i < 5; i++) {
            year3 = year2.multiply(year);
            add = add.add(year3);
            year2 = year3.add(year2);
        }
        System.out.println(add);


        Info<String> i = new Info<String>();       // 使用String为泛型类型
        i.setVar("it");                            // 设置内容
        fun(i);
        System.out.println(i.getVar());
    }

    public static void fun(Info<?> temp) {     // 可以接收任意的泛型对象
        System.out.println("内容：" + temp);
    }
};