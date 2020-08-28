package com.hy.demo.emun;

/**
 * @author hy
 * @description:
 * @date 2020/08/19
 */
public enum  PositionType {
    SHARES("SHARES","股票持仓"),
    BONDS("BONDS","债卷持仓"),
    FUND("FUND","基金持仓"),
    FUTURES("FUTURES","期货持仓"),
    OTHERS("OTHERS","其他");

    private String name;
    private String value;

    PositionType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
