package org.example.template.builder;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/20 13:34:20
 */
class SXSSFTableBuilderTest {
    @Test
    void test1() {
        SXSSFTableBuilder.builder()
                .headers(getHeaders())
                .addHeaderStyle()
                .fillForegroundColor(IndexedColors.GREY_25_PERCENT.index)
                .fillPattern(FillPatternType.SOLID_FOREGROUND)
                .border(true)
                .newFont((b -> b.fontHeightInPoints((short) 12).fontHeightInPoints((short) 12)
                        .name("黑体")
                        .end()))
                .end()
                .data(getData())
                .addSpecialColumn("age", HSSFColor.HSSFColorPredefined.RED.getIndex(), (age) -> age < 18)
                .addDataStyle()
                .border(true)
                .newFont((b -> b.fontHeightInPoints((short) 12).fontHeightInPoints((short) 12)
                        .name("宋体")
                        .color(HSSFColor.HSSFColorPredefined.BLACK.getIndex())
                        .end()))
                .end()
                .build();
    }

    public LinkedHashMap<String, String> getHeaders() {
        return new LinkedHashMap<>() {
            {
                put("姓名", "name");
                put("年龄", "age");
                put("性别", "gender");
            }
        };
    }

    public List<Map<String, Object>> getData() {
        return new ArrayList<>() {
            {
                add(Map.of("name", "张三", "age", 17, "gender", "男"));
                add(Map.of("name", "里斯", "age", 20, "gender", "男"));
                add(Map.of("name", "王五", "age", 16, "gender", "男"));
            }
        };
    }
}