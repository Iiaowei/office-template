package org.example.template.builder;

import org.apache.poi.xssf.usermodel.XSSFChart;

public class XSSFChartBuilder {
    private XSSFChart xssfChart;

    private XSSFChartBuilder(XSSFChart xssfChart) {
        this.xssfChart = xssfChart;
    }

    public static XSSFChartBuilder builder(XSSFChart xssfChart) {
        return new XSSFChartBuilder(xssfChart);
    }
}
