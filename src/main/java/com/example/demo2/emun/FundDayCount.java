package com.example.demo2.emun;

import java.util.Arrays;

/**
 * 基金筛选回报率类型
 */
public enum FundDayCount {
    ONE_WEEK("oneWeek","plusWeeks", -1, "otherType", "一周"),
    ONE_MONTH("oneMonth", "plusMonths", -1, "otherType", "一月"),
    THREE_MONTHS("threeMonths", "plusMonths", -3, "otherType", "三月"),
    SIX_MONTHS("sixMonths", "plusMonths", -6, "otherType", "六月"),
    SINCE_THIS_YEAR("sinceThisYear", "firstDayOfYear", 0, "sinceThisYear", "今年以来"),
    SINCE_THIS_ESTABLISH("sinceThisEstablish", "", 0, "sinceThisEstablish", "成立以来"),
    ONE_YEAR("oneYear", "plusYears", -1, "otherType", "一年"),
    TWO_YEARS("twoYears", "plusYears", -2, "otherType", "两年"),
    THREE_YEARS("threeYears", "plusYears", -3, "otherType", "三年"),
    FIVE_YEARS("fiveYears", "plusYears", -5, "otherType", "五年"),
    TEN_YEARS("tenYears", "plusYears", -10, "otherType", "十年"),
    /*NULL(null, null, 0, null, null),*/
    ;
    private final String id;
    private final String methodName;
    private final int count;
    private final String handlerMethodName;
    private final String desc;

    FundDayCount(String id, String methodName, int count, String handlerMethodName, String desc) {
        this.id = id;
        this.desc = desc;
        this.methodName = methodName;
        this.handlerMethodName = handlerMethodName;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public String getMethodName() {
        return methodName;
    }

    public int getCount() {
        return count;
    }

    public String getHandlerMethodName() {
        return handlerMethodName;
    }

    /*
     * 匹配操作码
     * */
    public static FundDayCount matchCode(String key) {
        return Arrays.stream(FundDayCount.values())
                .filter(it -> it.id.equals(key))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("非法的时间类型 = " + key));
    }
}
