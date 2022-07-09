package org.example.template.excel.table.annotation;

import org.example.template.constant.func.SubTotalFuncNum;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    String name() default "";

    SubTotalFuncNum subTotal() default SubTotalFuncNum.NULL;
}
