package org.example.template.excel.table.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {
    String sheet() default "";
    String name() default "";
    boolean totals() default false;
}
