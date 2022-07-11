package org.example.template.constant.func;

public enum SubTotalFuncNum {
    AVERAGE_INCLUDE(1),
    AVERAGE(101),
    COUNT_INCLUDE(2),
    COUNT(102),
    MAX_INCLUDE(4),
    MAX(104),
    MIN_INCLUDE(5),
    MIN(105),
    STDEV_INCLUDE(7),
    STDEV(107),
    SUM_INCLUDE(9),
    SUM(109),
    VAR_INCLUDE(10),
    VAR(110),
    VARP_INCLUDE(11),
    VARP(111),
    NULL(0);

    private final int value;

    SubTotalFuncNum(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}
