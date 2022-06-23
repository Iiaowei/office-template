package org.example.template.builder;

import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;

import java.util.function.Function;

public class XSSFDrawingBuilder {
    private XSSFDrawing xssfDrawing;


    private XSSFDrawingBuilder(XSSFDrawing xssfDrawing) {
        this.xssfDrawing = xssfDrawing;
    }

    public XSSFDrawingBuilder newChart(int col1, int row1, int col2, int row2, Function<XSSFChartBuilder, XSSFDrawingBuilder> function) {
        XSSFClientAnchor anchor = xssfDrawing.createAnchor(0, 0,0,0,col1, row1, col2, row2);
        XSSFChart xssfChart = xssfDrawing.createChart(anchor);
        return function.apply(XSSFChartBuilder.builder(xssfChart));
    }

    public XSSFDrawingBuilder newCTPlotArea() {
//        xssfDrawing.
        return null;
    }

    public static XSSFDrawingBuilder builder(XSSFDrawing xssfDrawing) {
        return new XSSFDrawingBuilder(xssfDrawing);
    }
}
