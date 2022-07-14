package org.example.template.core;

import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.XDDFNoFillProperties;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLblOffset;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.STOrientation;

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
        String[] legendData = {"passed", "skipped", "failed"};
        XDDFDataSource<String> testOutcomes = XDDFDataSourcesFactory.fromArray(legendData);
        Integer[] numericData = {10, 12, 30};
        Integer[] numericData1 = {1, 5, 10};
        XDDFNumericalDataSource<Integer> values = XDDFDataSourcesFactory.fromArray(numericData);


        XDDFCategoryAxis categoryAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);

        XDDFValueAxis valueAxis = chart.createValueAxis(AxisPosition.LEFT);
        valueAxis.setCrossBetween(AxisCrossBetween.BETWEEN);

        XDDFChartData data = chart.createData(ChartTypes.SURFACE, categoryAxis, valueAxis);// for simple pie chart you can use


        XDDFValueAxis valueAxis1 = chart.createValueAxis(AxisPosition.RIGHT);

        categoryAxis.crossAxis(valueAxis1);
        valueAxis1.crossAxis(categoryAxis);
        valueAxis1.setCrosses(AxisCrosses.MAX);
        valueAxis1.setCrossBetween(AxisCrossBetween.BETWEEN);
        XDDFNumericalDataSource<Integer> values1 = XDDFDataSourcesFactory.fromArray(numericData1);
        XDDFLineChartData data1 = (XDDFLineChartData) chart.createData(ChartTypes.LINE, categoryAxis, valueAxis1);// for simple pie chart you can use
        chart.displayBlanksAs(null);
        data.setVaryColors(true);
        data1.setVaryColors(true);
        data.addSeries(testOutcomes, values);

        XDDFLineChartData.Series series1 = (XDDFLineChartData.Series) data1.addSeries(testOutcomes, values1);
        series1.setSmooth(false); // https://stackoverflow.com/questions/39636138


        series1.setMarkerStyle(MarkerStyle.CIRCLE);
        series1.setMarkerSize((short)5);
        setLineNoFill(series1);

        chart.plot(data);
        chart.plot(data1);


        try (FileOutputStream outputStream = new FileOutputStream(FILE_SAVE_LOCATION + FILE_NAME)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // don't forget to close workbook to prevent memory leaks
            workbook.close();
        }
    }

    private static void setLineNoFill(XDDFLineChartData.Series series) {
        XDDFNoFillProperties noFillProperties = new XDDFNoFillProperties();
        XDDFLineProperties lineProperties = new XDDFLineProperties();
        lineProperties.setFillProperties(noFillProperties);
        XDDFShapeProperties shapeProperties = series.getShapeProperties();
        if (shapeProperties == null) shapeProperties = new XDDFShapeProperties();
        shapeProperties.setLineProperties(lineProperties);
        series.setShapeProperties(shapeProperties);
    }
}
