package com.example.demo2.test;

import com.example.demo2.dto.FundTypeForPeerPerformance;
import com.example.demo2.exception.ManagementCockpitException;
import com.example.demo2.util.DateUtil;
import parsii.eval.Expression;
import parsii.eval.Parser;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

public class Test04 {
    public static void main(String[] args) throws Exception {

        Class<? extends FundTypeForPeerPerformance.ChildClass> childs = FundTypeForPeerPerformance.ACTIVE_EQUITY.getChilds();
        Method getCodeList1 = FundTypeForPeerPerformance.ACTIVE_EQUITY.getChilds().getMethod("getCodeList");
        List<String> getCodeList = (List<String>) FundTypeForPeerPerformance.ACTIVE_FIXED_INCOME.getChilds().getMethod("getCodeList").invoke(null);
        System.out.println(getCodeList);
        Class<? extends FundTypeForPeerPerformance.ChildClass> childs1 = FundTypeForPeerPerformance.ACTIVE_FIXED_INCOME.getChilds();
        List<String> getCodeList2 = (List<String>) childs1.getMethod("getCodeList").invoke(null);
        System.out.println(getCodeList2);
        String s = DateUtil.addDays(DateUtil.now("yyyyMMdd"), -2, "yyyyMMdd");
        System.out.println(s);
        BigDecimal one = new BigDecimal(1);
        BigDecimal two = new BigDecimal(0);
        int i = one.compareTo(two);
        System.out.println(i);
        try {
            throw new ManagementCockpitException("参数异常");
        }catch (ManagementCockpitException e){
            System.out.println(e.getMessage());
        }
        Expression expr = Parser.parse("146/492");
        System.out.println(new BigDecimal(expr.evaluate()).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP));

    }
}
