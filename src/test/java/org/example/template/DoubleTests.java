package org.example.template;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/21 9:34:33
 */
public class DoubleTests {
    @Test
    void test1() {
        BigDecimal bigDecimal = new BigDecimal("12");
        aaa(bigDecimal.intValue());
    }

    void aaa(double a) {
        System.out.println(a);
    }
}
