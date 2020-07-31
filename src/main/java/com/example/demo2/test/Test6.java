package com.example.demo2.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test6 {
    public static void main(String[] args) throws ParseException {
//        String s = DateUtil.addWeeks(DateUtil.now("yyyyMMdd"), -1, "yyyyMMdd");
//        System.out.println(s);
        List<Map<String, ?>> thirdRankingList = new ArrayList<>();
        Map<String, String> thirdRankingMap = new HashMap<>();
        Map<String, BigDecimal> thirdRankingMap2 = new HashMap<>();
        thirdRankingMap.put("qqq","www");
        thirdRankingMap2.put("1111",new BigDecimal("222"));
        thirdRankingList.add(thirdRankingMap);
        thirdRankingList.add(thirdRankingMap2);
        String s = JSONObject.toJSONString(thirdRankingList);
        System.out.println(s);

    }
}
