package com.example.demo2.dto;

import java.util.ArrayList;
import java.util.List;

public enum FundTypeForPeerPerformance {
    ACTIVE_EQUITY("010101", "主动权益", ChildClass.ActiveEquity.class),
    ACTIVE_FIXED_INCOME("010201", "主动固收", ChildClass.ActiveFixedIncome.class);
    public interface ChildClass {
        enum ActiveEquity implements ChildClass {
            STOCK("01", "股票型基金", "股票"),
            BALANCED("02", "混合型基金", "混合");
            private String code;
            private String name;
            private String shortName;
            ActiveEquity(String code, String name, String shortName) {
                this.code = code;
                this.name = name;
                this.shortName = shortName;
            }
            public String getCode() { return code; }
            public String getName() { return name; }
            public String getShortName() { return shortName; }
            public static List<String> getCodeList() {
                List<String> list = new ArrayList<>();
                for(ActiveEquity value: ActiveEquity.values()) {
                    list.add(value.getCode());
                }
                return list;
            }
            public static String getNameByCode(String code) {
                for(ActiveEquity value: ActiveEquity.values()) {
                    if(code.equals(value.getCode())) {
                        return value.getName();
                    }
                }
                return null;
            }
            public static String getShortNameByCode(String code) {
                for(ActiveEquity value: ActiveEquity.values()) {
                    if(code.equals(value.getCode())) {
                        return value.getShortName();
                    }
                }
                return null;
            }
        }
        enum ActiveFixedIncome implements ChildClass {
            BOND("03", "债券型基金", "债券");
            private String code;
            private String name;
            private String shortName;
            ActiveFixedIncome(String code, String name, String shortName) {
                this.code = code;
                this.name = name;
                this.shortName = shortName;
            }
            public String getCode() { return code; }
            public String getName() { return name; }
            public String getShortName() { return shortName; }
            public static List<String> getCodeList() {
                List<String> list = new ArrayList<>();
                for(ActiveFixedIncome value: ActiveFixedIncome.values()) {
                    list.add(value.getCode());
                }
                return list;
            }
            public static String getNameByCode(String code) {
                for(ActiveFixedIncome value: ActiveFixedIncome.values()) {
                    if(code.equals(value.getCode())) {
                        return value.getName();
                    }
                }
                return null;
            }
            public static String getShortNameByCode(String code) {
                for(ActiveFixedIncome value: ActiveFixedIncome.values()) {
                    if(code.equals(value.getCode())) {
                        return value.getShortName();
                    }
                }
                return null;
            }
        }
    }
    private String code;
    private String name;
    private Class<? extends ChildClass> childs;
    FundTypeForPeerPerformance(String code, String name, Class<? extends ChildClass> childs) {
        this.code = code;
        this.name = name;
        this.childs = childs;
    }
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public Class<? extends ChildClass> getChilds() { return childs; }
    public static List<String> getCodeList() {
        List<String> list = new ArrayList<>();
        for(FundTypeForPeerPerformance value: FundTypeForPeerPerformance.values()) {
            list.add(value.getCode());
        }
        return list;
    }
    public static String getNameByCode(String code) {
        for(FundTypeForPeerPerformance value: FundTypeForPeerPerformance.values()) {
            if(code.equals(value.getCode())) {
                return value.getName();
            }
        }
        return null;
    }
    public static Class<? extends ChildClass> getChildsByCode(String code) {
        for(FundTypeForPeerPerformance value: FundTypeForPeerPerformance.values()) {
            if(code.equals(value.getCode())) {
                return value.getChilds();
            }
        }
        return null;
    }
}
