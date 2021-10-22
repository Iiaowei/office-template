package org.example.template.core.annotation;

import java.lang.annotation.*;

/**
 * @author liaowei
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HeadStyle {
    int color() default 0x9999FF;
}
