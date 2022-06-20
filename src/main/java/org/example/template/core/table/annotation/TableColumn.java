package org.example.template.core.table.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableColumn {
    String name() default "";
    int index() default -1;

}
