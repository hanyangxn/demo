package com.hy.demo.test.fanxing;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hy
 * @description:
 * @date 2020/08/31
 */
class Demo{
    public <T> T fun(T t){            // 可以接收任意类型的数据
        return t ;                  // 直接把参数返回
    }

    public static void main(String[] args) {
        Demo d =new Demo();
        String tomo = d.fun("tomo");
        Integer fun = d.fun(111);
        System.out.println(tomo);
        System.out.println(fun);
        List<String> stringList =new ArrayList<>();
        stringList.add("tomi");
        List<Integer> integerList =new ArrayList<>();
        integerList.add(123);
        fun2(stringList);
        fun2(integerList);
    }

    public static <T> void fun2(List<T> temp){     // 可以接收任意的泛型对象
        System.out.println("内容：" + temp) ;
    }
    public static void fun3(List<?> temp){     // 可以接收任意的泛型对象
        System.out.println("内容：" + temp) ;
    }
};
