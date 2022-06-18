package org.example.template.core.handler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/18 2:40:29
 */
public class DateHandler {
    private final List<Class<?>> dateTypeList = new ArrayList<>();

    {
        dateTypeList.add(Date.class);
        dateTypeList.add(LocalDate.class);
        dateTypeList.add(LocalDateTime.class);
    }
}
