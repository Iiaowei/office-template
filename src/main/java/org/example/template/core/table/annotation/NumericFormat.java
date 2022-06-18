package org.example.template.core.table.annotation;

import java.lang.annotation.*;
import java.math.RoundingMode;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/18 2:46:44
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NumericFormat {
    String format() default "#.##";
    RoundingMode mode() default RoundingMode.HALF_UP;
}
