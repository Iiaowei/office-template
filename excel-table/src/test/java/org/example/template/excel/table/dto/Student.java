package org.example.template.excel.table.dto;

import org.example.template.constant.func.SubTotalFuncNum;
import org.example.template.excel.table.annotation.Column;
import org.example.template.excel.table.annotation.Table;

import java.math.BigDecimal;

@Table(sheet = "成绩", name = "table1", totals = true)
public class Student {
    @Column(name = "姓名")
    private String name;
    @Column(name = "语文")
    private BigDecimal chinese;
    @Column(name = "数学", subTotal = SubTotalFuncNum.AVERAGE)
    private BigDecimal math;
    @Column(name = "英语", subTotal = SubTotalFuncNum.COUNT)
    private BigDecimal english;

    public Student(String name, BigDecimal chinese, BigDecimal math, BigDecimal english) {
        this.name = name;
        this.chinese = chinese;
        this.math = math;
        this.english = english;
    }
}
