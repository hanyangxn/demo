package com.hy.demo.sjms.guanchazhe;

/**
 * @author hy
 * @description:
 * @date 2020/08/07
 */
public class Test {
    public static void main(String[] args) {
        BTest bTest = new BTest();
        ATest aTest =new ATest(bTest);
        aTest.update();
        bTest.test(aTest);
    }
}
