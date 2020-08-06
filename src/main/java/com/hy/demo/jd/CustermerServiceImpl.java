package com.hy.demo.jd;

public class CustermerServiceImpl {
    public static void main(String[] args) {
        CustermerService<String ,Integer> custermerService =(form) -> Integer.valueOf(form);
        Integer test = custermerService.test("12");
        System.out.println(test);

        CustermerService<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.test("123");
        System.out.println(converted);   // 123
    }
}
