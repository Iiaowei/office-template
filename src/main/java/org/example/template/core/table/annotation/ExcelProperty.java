package org.example.template.core.table.annotation;

import java.lang.annotation.*;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/18 1:25:35
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelProperty {
    String name() default "";
    int index() default Integer.MAX_VALUE;
}
