package com.example.demo2.test;

import com.example.demo2.annotation.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test05 {
    public static void main(String[] args) {
//        int sum = 0;
//        List<Integer> list3 = Arrays.asList(1, 3, 4);
//        for (Integer item : list3) {
//            int b = item + 1;
//            System.out.println(sum + "-" + b);
//            sum = sum + b;
//        }
//        System.out.println(sum);


        System.out.println(Stream.of(1, 3, 4).collect(Collectors.reducing(0, x -> x + 1, (sum, b) -> {
            System.out.println(sum + "-" + b);
            return sum + b;
        })));

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 奇偶数分组：奇数分一组，偶数分一组
        // groupingBy(Function<? super T, ? extends K> classifier) 参数是Function类型，Function返回值可以是要分组的条件，也可以是要分组的字段
        // 返回的结果是Map，其中key的数据类型为Function体中计算类型，value是List<T>类型，为分组的结果
        Map<Boolean, List<Integer>> result = list.stream().collect(Collectors.groupingBy(item -> item % 2 == 0));
        // {false=[1, 3, 5, 7, 9], true=[2, 4, 6, 8, 10]}
        System.out.println(result.get(true));

        Long test = test(100L, x -> x + 200L);
        System.out.println(test);
        Function<BigDecimal, BigDecimal> fun = v -> v.multiply(new BigDecimal(100.0));
        Function<BigDecimal, BigDecimal> fun2 = v -> v.divide (new BigDecimal(1000.0));
        BigDecimal apply = fun.andThen(fun2).apply(new BigDecimal(20));
        System.out.println("apply==>>:"+apply);


    }


    public static Long test(Long l, Function<Long, Long> fun) {
        return fun.apply(l);
    }
}
