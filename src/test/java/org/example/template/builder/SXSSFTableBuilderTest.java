package org.example.template.builder;

import org.apache.poi.ss.usermodel.Font;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
                .headers(new LinkedHashMap<>() {
                    {
                        put("姓名", "name");
                        put("年龄", "age");
                        put("性别", "gender");
                    }
                })
                .data(new ArrayList<>(){
                    {
                        add(Map.of("name", "张三", "age", 17, "gender", "男"));
                        add(Map.of("name", "里斯", "age", 20, "gender", "男"));
                        add(Map.of("name", "王五", "age", 16, "gender", "男"));
                    }
                })
                .addSpecialColumn("age", Font.COLOR_RED, (age) -> age < 18)
                .build();
    }
}