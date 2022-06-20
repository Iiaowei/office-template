package org.example.template.core;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/17 19:59:17
 */
public class Table {
    List<Table> list = new ArrayList<>();

    public static void main(String[] args) throws NoSuchFieldException {


        Field stringListField = Table.class.getDeclaredField("list");
        ParameterizedType stringListType = (ParameterizedType) stringListField.getGenericType();
        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
        System.out.println(stringListClass); // class java.lang.String.
    }
}
