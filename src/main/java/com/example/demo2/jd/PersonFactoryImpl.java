package com.example.demo2.jd;

import com.example.demo2.domain.User;
import com.example.demo2.util.DateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class PersonFactoryImpl {
    public static void main(String[] args) {
        PersonFactory<User> userPersonFactory =User::new;
        User user = userPersonFactory.create(1, "777", "8888");
        System.out.println(user);
        List<String> listUp =new ArrayList<>();
        List<String> listDn =new ArrayList<>();
        method("abcd",s ->listUp.add(s.toUpperCase()),s -> listDn.add(s.toLowerCase()));
        System.out.println(listDn);
        System.out.println(listUp);
        String ymd = DateUtil.now("yyyyMMdd");
        System.out.println(ymd);
        String lastSeason = DateUtil.lastSeason(ymd);
        System.out.println(lastSeason);
        String s = dateTime(lastSeason);
        System.out.println(s);

        List<String> item =new ArrayList<>();
        item.add("12309");
        item.add("12109");
        String fundCode ="2020";
        item.forEach(s1 -> System.out.println(s1.concat(",").concat(fundCode)));

        User user1 = new User();
        List<User> list=new ArrayList<>();
        list.add(user1);
        user1.setId(1);
        user1.setPassword("123");
        System.out.println(list);


    }
    public static void method(String str, Consumer<String> custermer1, Consumer<String> custermer2){
        custermer1.accept(str);
        custermer2.accept(str);
    }

    public static String dateTime(String date){
        String sub = date.substring(4, 8);
        if ("0331".equals(sub)){
            return "Q1";
        }else if ("0630".equals(sub)){
            return "Q2";
        }else if ("0930".equals(sub)){
            return "Q3";
        }else {
            return "Q4";
        }


    }


}

