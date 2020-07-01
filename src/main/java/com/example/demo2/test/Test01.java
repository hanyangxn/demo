package com.example.demo2.test;

import java.util.List;

public class Test01 {
    public static void main(String[] args) {
        List<String> codeList = AnalysisDimension.getCodeList();
        String nameByCode = AnalysisDimension.getNameByCode("1");
        System.out.println(nameByCode);
        System.out.println(codeList);
    }
}
