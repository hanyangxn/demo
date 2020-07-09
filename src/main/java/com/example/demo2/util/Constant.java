package com.example.demo2.util;

import java.math.BigDecimal;

/**
 * @author xlqi
 * 常量类
 * */
public class Constant {

    public static final BigDecimal DEFAULT_CALC_BIG_ONE = BigDecimal.ONE;

    public static final Integer DEFAULT_CALC_ONE = 1;

    public static final Integer DEFAULT_YEAR_CALC_VALUE = 365;

    public static final Integer NON_WORKING_DAY = 250;

    //
    public static final Integer DAY_COUNT_BENCHMARK = 300;

    // 平方
    public static final Integer SQUARE = 2;

    // 索引计数初始值
    public static final Integer INDEX_INITIAL_VALUE = -1;

    // 除法保留小数 4 位
    public static final Integer DIVIDE_KEEP_DECIMALS = 6;

    // 除法保留小数 4 位
    public static final Integer ZERO = 0;

    // 年化
    public static final String INTERVAL_YEAR = "INTERVAL_YEAR";
    // 日
    public static final String INTERVAL_DAY = "INTERVAL_DAY";

    // 区间收益
    public static final String SECTION_PROFIT = "SECTION_PROFIT";
    // 年度收益
    public static final String YEAR_PROFIT = "YEAR_PROFIT";
    // 月度收益
    public static final String MONTHLY_PROFIT = "MONTHLY_PROFIT";

    // 无风险收益率默认
    public static final BigDecimal RISK_FREE_DEFAULT =  new BigDecimal(String.valueOf("0.015"));

    // 保留小数
    public static final Integer SCALE =  4;

    // 保留小数 2
    public static final Integer STAGE_UP_DOWN_SCALE =  2;

}
