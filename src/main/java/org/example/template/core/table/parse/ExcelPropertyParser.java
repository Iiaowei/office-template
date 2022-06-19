package org.example.template.core.table.parse;

import org.example.template.core.table.ExcelTableHeader;
import org.example.template.core.table.annotation.ExcelProperty;

import java.lang.reflect.Field;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/18 1:28:06
 */
public class ExcelPropertyParser {

    public ExcelTableHeader parse(Class<?> templateClazz) {
        ExcelTableHeader tableHeader = new ExcelTableHeader();

        for (Field declaredField : templateClazz.getDeclaredFields()) {
            String name  = declaredField.getName();
            int index = Integer.MAX_VALUE;

            declaredField.setAccessible(true);
            ExcelProperty excelProperty = declaredField.getAnnotation(ExcelProperty.class);
            if (excelProperty != null) {
                name = excelProperty.name();
                index = excelProperty.index();
            }
            tableHeader.createColumn(name, index, declaredField.getName(), declaredField.getType());
        }
        return tableHeader;
    }
}
