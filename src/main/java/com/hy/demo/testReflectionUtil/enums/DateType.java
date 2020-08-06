package com.hy.demo.testReflectionUtil.enums;

public enum DateType {
    ONE_MONTH("oneMonth","addMonths","otherType",-1,"一个月"),
    THREE_MONTH("threeMonth","addMonths","otherType",-3,"三个月"),
    SIX_MONTH("sixMonth","addMonths","otherType",-6,"六个月"),
    ONE_YEAR("oneYear","addYears","otherType",-1,"一年"),
    THREE_YEAR("threeYear","addYears","otherType",-3,"一年"),
    FIVE_YEAR("fiveYear","addYears","otherType",-5,"一年"),
    SINCE_THIS_YEAR("sinceThisYear","","sinceThisYear",0,"今年以来"),
    SINCE_THIS("sinceThis","","sinceThis",0,"成立以来");
    private String id;
    private String methodName;
    private String handleMethodName;
    private int count;
    private String desc;

    DateType(String id, String methodName, String handleMethodName, int count, String desc) {
        this.id = id;
        this.methodName = methodName;
        this.handleMethodName = handleMethodName;
        this.count = count;
        this.desc = desc;
    }

    public String getHandleMethodName() {
        return handleMethodName;
    }

    public int getCount() {
        return count;
    }

    public String getMethodName() {
        return methodName;
    }
}
