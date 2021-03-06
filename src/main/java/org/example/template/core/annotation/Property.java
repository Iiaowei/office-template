package org.example.template.core.annotation;

import java.lang.annotation.*;

/**
 * @author liaowei
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Property {
    String value() default "";
    int color() default 0x000000;
}
