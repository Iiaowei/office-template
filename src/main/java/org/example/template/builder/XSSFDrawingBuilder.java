package org.example.template.builder;

import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class XSSFDrawingBuilder<V extends Number> {
    private String sheetName = "图表";
    private int startCol = 0;
    private int startRow = 0;
    private int endCol = 20;
    private int endRow = 20;

    private String title = "图表";

    private boolean titleOverlay = false;

    private String[] legend;
    private V[] numberValues;

    private LegendPosition legendPosition = LegendPosition.TOP_RIGHT;
    private AxisPosition categoryAxis = AxisPosition.BOTTOM;
    private AxisPosition valueAxis = AxisPosition.LEFT;
    private ChartTypes chartType = ChartTypes.SURFACE;
    private Boolean varyColors = true;

    private Boolean byteStream = true;
    private String fileName = "图表";

    public XSSFDrawingBuilder<V> sheet(String sheetName) {
        this.sheetName = sheetName;
        return this;
    }

    public XSSFDrawingBuilder<V> size(int startCol, int startRow, int endCol, int endRow) {
        this.startCol = startCol;
        this.startRow = startRow;
        this.endCol = endCol;
        this.endRow = endRow;
        return this;
    }

    public XSSFDrawingBuilder<V> chartTitle(String title) {
        this.title = title;
        return this;
    }

    public XSSFDrawingBuilder<V> titleOverlay(boolean titleOverlay) {
        this.titleOverlay = titleOverlay;
        return this;
    }

    public XSSFDrawingBuilder<V> legendPosition(LegendPosition legendPosition) {
        this.legendPosition = legendPosition;
        return this;
    }

    public XSSFDrawingBuilder<V> legend(String[] legend) {
        this.legend = legend;
        return this;
    }

    public XSSFDrawingBuilder<V> values(V[] values) {
        this.numberValues = values;
        return this;
    }

    public XSSFDrawingBuilder<V> categoryAxis(AxisPosition axisPosition) {
        this.categoryAxis = axisPosition;
        return this;
    }

    public XSSFDrawingBuilder<V> valueAxis(AxisPosition axisPosition) {
        this.valueAxis = axisPosition;
        return this;
    }

    public XSSFDrawingBuilder<V> chartType(ChartTypes chartType) {
        this.chartType = chartType;
        return this;
    }

    public XSSFDrawingBuilder<V> varyColors(Boolean varyColors) {
        this.varyColors = varyColors;
        return this;
    }

    public XSSFDrawingBuilder<V> fileName(String fileName) {
        if (fileName != null && !fileName.isBlank()) {
            this.fileName = fileName;
        }
        this.byteStream = false;
        return this;
    }

    public static <V extends Number> XSSFDrawingBuilder<V> builder() {
        return new XSSFDrawingBuilder<>();
    }

    public OutputStream build() {
        OutputStream outputStream;
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {

            XSSFSheet sheet = workbook.createSheet(sheetName);

            XSSFDrawing drawing = sheet.createDrawingPatriarch();

            XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, startCol, startRow, endCol, endRow);
            XSSFChart chart = drawing.createChart(anchor);
            chart.setTitleText(title);
            chart.setTitleOverlay(titleOverlay);

            XDDFChartLegend xddfChartLegend = chart.getOrAddLegend();
            xddfChartLegend.setPosition(legendPosition);

            XDDFDataSource<String> dataSource = XDDFDataSourcesFactory.fromArray(legend);

            XDDFNumericalDataSource<V> values = XDDFDataSourcesFactory.fromArray(numberValues);

            XDDFCategoryAxis categoryAxis = chart.createCategoryAxis(this.categoryAxis);
            XDDFValueAxis valueAxis = chart.createValueAxis(this.valueAxis);
            XDDFChartData data = chart.createData(this.chartType, categoryAxis, valueAxis);

            chart.displayBlanksAs(null);
            data.setVaryColors(varyColors);
            data.addSeries(dataSource, values);

            chart.plot(data);
            if (byteStream) {
                outputStream = new ByteArrayOutputStream();
            } else {
                outputStream = new FileOutputStream(fileName);
            }
            workbook.write(outputStream);
        } catch (IOException e) {
            return null;
        }
        return outputStream;
    }
}
