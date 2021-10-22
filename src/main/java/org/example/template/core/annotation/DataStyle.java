package org.example.template.core.annotation;

import java.lang.annotation.*;

/**
 * @author liaowei
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataStyle {
    Class font();
}
