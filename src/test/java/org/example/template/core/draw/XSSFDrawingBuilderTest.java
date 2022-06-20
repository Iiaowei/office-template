package org.example.template.core.draw;

import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.example.template.builder.XSSFDrawingBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/20 11:13:18
 */
class XSSFDrawingBuilderTest {
    @Test
    void drawingTest() {
        OutputStream outputStream = XSSFDrawingBuilder.builder()
                .fileName("22222.xlsx")
                .size(10, 20, 30, 40)
                .legend(new String[]{"京东", "天猫", "拼多多"})
                .values(new Integer[]{123, 331, 435})
                .chartType(ChartTypes.PIE3D)
                .build();

        try {
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}