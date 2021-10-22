package org.example.template.core.annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liaowei
 * @version V1.0
 * @date 2021/10/22 14:48:19
 */
public class PropertyProcessor {

    public ExcelTemplate init(Class<?> clazz) {
        HeadStyle headStyle = clazz.getAnnotation(HeadStyle.class);
        TemplateDefinition headDefinition = new TemplateDefinition.Builder()
                .color(1)
                .size(1)
                .build();

        DataStyle dataStyle = clazz.getAnnotation(DataStyle.class);
        TemplateDefinition dataDefinition = new TemplateDefinition.Builder()
                .color(1)
                .size(1)
                .build();

        Field[] declaredFields = clazz.getDeclaredFields();

        List<TemplateDefinition> properties = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            String propertyCode = declaredField.getName();
            Property property = declaredField.getAnnotation(Property.class);
            TemplateDefinition propertyDefinition = new TemplateDefinition.Builder()
                    .parameterCode(propertyCode)
                    .parameterName(property.value())
                    .build();
            properties.add(propertyDefinition);
        }

        return new ExcelTemplate(clazz, headDefinition, dataDefinition, properties);
    }
}
