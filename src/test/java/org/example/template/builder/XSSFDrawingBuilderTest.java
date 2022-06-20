package org.example.template.builder;

import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/20 14:33:06
 */
class XSSFDrawingBuilderTest {
    @Test
    void test1 () {
        XSSFDrawingBuilder.builder()
                .legend(new String[]{"平多多", "天猫", "京东"})
                .values(new Integer[]{12321, 34345, 546546})
                .sheet("设备都发vv的v")
                .chartTitle("测试图标1111")
                .fileName("12321321.xlsx")
                .build();
    }
}