package org.example.template.core;

import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportData {
    static final String FILE_SAVE_LOCATION = "D:\\IdeaProjects\\office-template\\src\\main\\resources\\";
    static final String FILE_NAME = "Test report.xlsx";

    public static void main(String[] args) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Report");

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

        XDDFChartData data = chart.createData(ChartTypes.PIE3D, null, null);// for simple pie chart you can use

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
