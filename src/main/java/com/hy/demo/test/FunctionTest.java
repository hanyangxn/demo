package com.hy.demo.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionTest {

    private static List<Function<Integer,Integer>> list = new ArrayList<>();
    private static Function<Integer,Integer> a = value -> value + 10 ;
    private static Function<Integer,Integer> b = value -> value + 20 ;
    private static Function<Integer,Integer> c = value -> value + 30 ;
    private static Function<Integer,Integer> d = value -> value * 2 ;


    static {
        list = Arrays.asList(a,b,c,d);
    }

    public static void main(String[] args) {
        FunctionTest functionTest = new FunctionTest();
        System.out.println(functionTest.computeByDESC(2, list));// 64
        System.out.println(functionTest.computeByASC(2, list)); // 124


    }

    /**
     *  倒叙执行
     */
    public int computeByDESC(int a, List<Function<Integer,Integer>> list){
        Function<Integer,Integer> result = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if(i+1<list.size()){
                result =  result.compose(list.get(i+1));
            }
        }
        return result.apply(a);
    }

    /**
     *  正序执行
     */
    public int computeByASC(int a, List<Function<Integer,Integer>> list){
        Function<Integer,Integer> result = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if(i+1<list.size()){
                result =  result.andThen(list.get(i+1));
            }
        }
        return result.apply(a);
    }
}
