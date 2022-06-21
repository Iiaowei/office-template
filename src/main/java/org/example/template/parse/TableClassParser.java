package org.example.template.parse;

import org.example.template.core.table.Column;
import org.example.template.core.table.ColumnType;
import org.example.template.core.table.annotation.DateFormat;
import org.example.template.core.table.annotation.NumericFormat;
import org.example.template.core.table.annotation.TableColumn;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class TableClassParser {
    private static final List<Class<?>> dateClass = new ArrayList<>();
    private static final List<Class<?>> temporalClass = new ArrayList<>();
    private static final List<Class<?>> decimalClass = new ArrayList<>();

    static {
        dateClass.add(Date.class);

        temporalClass.add(LocalDate.class);
        temporalClass.add(LocalTime.class);
        temporalClass.add(LocalDateTime.class);

        decimalClass.add(int.class);
        decimalClass.add(byte.class);
        decimalClass.add(short.class);
        decimalClass.add(long.class);
        decimalClass.add(Integer.class);
        decimalClass.add(Byte.class);
        decimalClass.add(Short.class);
        decimalClass.add(Long.class);

        decimalClass.add(float.class);
        decimalClass.add(double.class);
        decimalClass.add(Float.class);
        decimalClass.add(Double.class);
        decimalClass.add(BigDecimal.class);
    }

    public static LinkedHashMap<String, Column> parse(Class<?> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        List<Column> columns = new ArrayList<>(declaredFields.length);

        for (Field declaredField : declaredFields) {
            Column column = new Column();

            declaredField.setAccessible(true);

            column.setName(declaredField.getName());
            Type type = declaredField.getGenericType();

            if (dateClass.contains(type)) {
                column.setColumnType(ColumnType.Date);
            } else if (temporalClass.contains(type)) {
                column.setColumnType(ColumnType.Temporal);
            } else if (decimalClass.contains(type)) {
                column.setColumnType(ColumnType.Decimal);
            } else {
                column.setColumnType(ColumnType.String);
            }

            TableColumn tableColumn = declaredField.getAnnotation(TableColumn.class);
            if (tableColumn != null) {
                column.setAliasName(tableColumn.name());
                column.setIndex(tableColumn.index());
            }

            DateFormat dateFormat = declaredField.getAnnotation(DateFormat.class);
            if (dateFormat != null) {
                column.setDateFormat(dateFormat.format());
            }

            NumericFormat numericFormat = declaredField.getAnnotation(NumericFormat.class);
            if (numericFormat != null) {
                column.setNumericFormat(numericFormat.format());
            }
            columns.add(column);
        }
        Collections.sort(columns);

        LinkedHashMap<String, Column> headersMap = new LinkedHashMap<>(16);
        for (Column column : columns) {
            headersMap.put(column.getName(), column);
        }
        return headersMap;
    }

}
