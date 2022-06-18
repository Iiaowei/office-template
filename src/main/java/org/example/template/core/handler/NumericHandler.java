package org.example.template.core.handler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/18 2:34:03
 */
public class NumericHandler {
    private final List<Class<?>> numericTypeList = new ArrayList<>();

    {
        numericTypeList.add(Integer.class);
        numericTypeList.add(int.class);
        numericTypeList.add(Double.class);
        numericTypeList.add(double.class);
        numericTypeList.add(Float.class);
        numericTypeList.add(float.class);
        numericTypeList.add(Long.class);
        numericTypeList.add(long.class);
        numericTypeList.add(BigDecimal.class);
    }
}
