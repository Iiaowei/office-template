package org.example.template.core;

import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

import static org.example.template.core.ExportData.FILE_NAME;
import static org.example.template.core.ExportData.FILE_SAVE_LOCATION;

public class DrawTests {
    public static void main(String[] args) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("图标");

        XSSFDrawing drawing = sheet.createDrawingPatriarch();

        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 10, 20, 30, 40);
        XSSFChart chart = drawing.createChart(anchor);
        chart.setTitleText("Test results");
        chart.setTitleOverlay(false);

        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);
        String[] legendData = { "passed", "skipped", "failed" };
        XDDFDataSource<String> testOutcomes = XDDFDataSourcesFactory.fromArray(legendData);
        Integer[] numericData = { 10, 12, 30 };
        XDDFNumericalDataSource<Integer> values = XDDFDataSourcesFactory.fromArray(numericData);

        XDDFCategoryAxis categoryAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        XDDFValueAxis valueAxis = chart.createValueAxis(AxisPosition.LEFT);
        XDDFChartData data = chart.createData(ChartTypes.SURFACE, categoryAxis, valueAxis);// for simple pie chart you can use

        chart.displayBlanksAs(null);
        data.setVaryColors(true);
        data.addSeries(testOutcomes, values);

        chart.plot(data);

        try (FileOutputStream outputStream = new FileOutputStream(FILE_SAVE_LOCATION + FILE_NAME)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // don't forget to close workbook to prevent memory leaks
            workbook.close();
        }
    }
}
