package org.example.template.builder;

import org.junit.jupiter.api.Test;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/20 14:33:06
 */
class XSSFDrawingSimpleBuilderTest {
    @Test
    void test1 () {
        XSSFDrawingSimpleBuilder.builder()
                .legend(new String[]{"平多多", "天猫", "京东"})
                .values(new Integer[]{12321, 34345, 546546})
                .sheet("设备都发vv的v")
                .chartTitle("测试图标1111")
                .fileName("12321321.xlsx")
                .build();
    }
}