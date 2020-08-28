package com.hy.demo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hy
 * @description:
 * @date 2020/08/27
 */
public class Test8 {
    public static void main(String[] args) {
        List<String> list =new ArrayList<>();
        list.add("AA");
        list.add("BB");
        List<String> list1 =new ArrayList<>();
        list1.add("11");
        list1.add("22");
        List<List> list2 =new ArrayList<>();
        list2.add(list);
        list2.add(list1);
        test(list);
        test(list2);
    }

    public static void test(List<?> calList){
        System.out.println(calList);
    }
}
