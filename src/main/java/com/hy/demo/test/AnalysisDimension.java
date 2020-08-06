package com.hy.demo.test;

import java.util.ArrayList;
import java.util.List;

public enum AnalysisDimension {
    REGION("1", "区域"),
    PRODUCT("2", "产品"),
    AGENCY("3", "渠道");
    private String code;
    private String name;
    AnalysisDimension(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public static List<String> getCodeList() {
        List<String> list = new ArrayList<>();
        for(AnalysisDimension value: AnalysisDimension.values()) {
            list.add(value.getCode());
        }
        return list;
    }
    public static String getNameByCode(String code) {
        for(AnalysisDimension value: AnalysisDimension.values()) {
            if(code.equals(value.getCode())) {
                return value.getName();
            }
        }
        return null;
    }
}
