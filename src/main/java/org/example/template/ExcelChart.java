package org.example.template;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.openxmlformats.schemas.drawingml.x2006.chart.*;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/7/12 14:44:35
 */
public class ExcelChart {
    private String sheetName;
    private CTPlotArea ctPlotArea;

    public void setOption(Option option) {
        for (Option.Chart chart : option.getCharts()) {
            CellReference title = chart.getTitle();
            ChartTypes chartType = chart.getChartType();
            AreaReference category = chart.getCategory();
            AreaReference data = chart.getData();

            if (ChartTypes.AREA == chartType) {
                if (ctPlotArea.getAreaChartArray().length < 1) {
                    continue;
                }

                CTAreaChart ctAreaChart = ctPlotArea.getAreaChartArray(0);

                CTAreaSer[] serArray = ctAreaChart.getSerArray();
                if(serArray.length > 0) {
                    CTAreaSer ctAreaSer = serArray[0];
                    CTSerTx ctSerTx = ctAreaSer.getTx();

                    ctSerTx.getStrRef().setF(formatAsString(title)); //数据列标题

                    CTAxDataSource cttAxDataSource = ctAreaSer.getCat();
                    cttAxDataSource.getStrRef().setF(formatAsString(category));

                    CTNumDataSource ctNumDataSource = ctAreaSer.getVal();
                    ctNumDataSource.getNumRef().setF(formatAsString(data));
                }
            } else if (ChartTypes.AREA3D == chartType) {
                if (ctPlotArea.getArea3DChartArray().length < 1) {
                    continue;
                }
                CTArea3DChart ctArea3DChart = ctPlotArea.getArea3DChartArray(0);
                CTAreaSer[] serArray = ctArea3DChart.getSerArray();
                if(serArray.length > 0) {
                    CTAreaSer ctAreaSer = serArray[0];
                    CTSerTx ctSerTx = ctAreaSer.getTx();

                    ctSerTx.getStrRef().setF(formatAsString(title)); //数据列标题

                    CTAxDataSource cttAxDataSource = ctAreaSer.getCat();
                    cttAxDataSource.getStrRef().setF(formatAsString(category));

                    CTNumDataSource ctNumDataSource = ctAreaSer.getVal();
                    ctNumDataSource.getNumRef().setF(formatAsString(data));
                }
            } else if (ChartTypes.BAR == chartType) {
                if (ctPlotArea.getBarChartArray().length < 1) {
                    continue;
                }
                CTBarChart ctBarChart = ctPlotArea.getBarChartArray(0);
                CTBarSer[] ctBarSers = ctBarChart.getSerArray();
                if(ctBarSers.length > 0) {
                    CTBarSer ctBarSer = ctBarSers[0];
                    CTSerTx ctSerTx = ctBarSer.getTx();

                    ctSerTx.getStrRef().setF(formatAsString(title)); //数据列标题

                    CTAxDataSource cttAxDataSource = ctBarSer.getCat();
                    cttAxDataSource.getStrRef().setF(formatAsString(category));

                    CTNumDataSource ctNumDataSource = ctBarSer.getVal();
                    ctNumDataSource.getNumRef().setF(formatAsString(data));
                }
            } else if (ChartTypes.BAR3D == chartType) {
                if (ctPlotArea.getBar3DChartArray().length < 1) {
                    continue;
                }
                CTBar3DChart ctBar3DChart = ctPlotArea.getBar3DChartArray(0);
                CTBarSer[] ctBarSers = ctBar3DChart.getSerArray();
                if(ctBarSers.length > 0) {
                    CTBarSer ctBarSer = ctBarSers[0];
                    CTSerTx ctSerTx = ctBarSer.getTx();

                    ctSerTx.getStrRef().setF(formatAsString(title)); //数据列标题

                    CTAxDataSource cttAxDataSource = ctBarSer.getCat();
                    cttAxDataSource.getStrRef().setF(formatAsString(category));

                    CTNumDataSource ctNumDataSource = ctBarSer.getVal();
                    ctNumDataSource.getNumRef().setF(formatAsString(data));
                }
            } else if (ChartTypes.DOUGHNUT == chartType) {
                if (ctPlotArea.getDoughnutChartArray().length < 1) {
                    continue;
                }
                CTDoughnutChart ctDoughnutChart = ctPlotArea.getDoughnutChartArray(0);
                CTPieSer[] ctPieSers = ctDoughnutChart.getSerArray();
                if(ctPieSers.length > 0) {
                    CTPieSer ctPieSer = ctPieSers[0];
                    CTSerTx ctSerTx = ctPieSer.getTx();

                    ctSerTx.getStrRef().setF(formatAsString(title)); //数据列标题

                    CTAxDataSource cttAxDataSource = ctPieSer.getCat();
                    cttAxDataSource.getStrRef().setF(formatAsString(category));

                    CTNumDataSource ctNumDataSource = ctPieSer.getVal();
                    ctNumDataSource.getNumRef().setF(formatAsString(data));
                }
            } else if (ChartTypes.LINE == chartType) {
                if (ctPlotArea.getLineChartArray().length < 1) {
                    continue;
                }
                CTLineChart ctLineChart = ctPlotArea.getLineChartArray(0);
                CTLineSer[] ctLineSers = ctLineChart.getSerArray();
                if(ctLineSers.length > 0) {
                    CTLineSer ctLineSer = ctLineSers[0];
                    CTSerTx ctSerTx = ctLineSer.getTx();

                    ctSerTx.getStrRef().setF(formatAsString(title)); //数据列标题

                    CTAxDataSource cttAxDataSource = ctLineSer.getCat();
                    cttAxDataSource.getStrRef().setF(formatAsString(category));

                    CTNumDataSource ctNumDataSource = ctLineSer.getVal();
                    ctNumDataSource.getNumRef().setF(formatAsString(data));
                }
            } else if (ChartTypes.LINE3D == chartType) {
                if (ctPlotArea.getLine3DChartArray().length < 1) {
                    continue;
                }
                CTLine3DChart ctLine3DChart = ctPlotArea.getLine3DChartArray(0);
                CTLineSer[] ctLineSers = ctLine3DChart.getSerArray();
                if(ctLineSers.length > 0) {
                    CTLineSer ctLineSer = ctLineSers[0];
                    CTSerTx ctSerTx = ctLineSer.getTx();

                    ctSerTx.getStrRef().setF(formatAsString(title)); //数据列标题

                    CTAxDataSource cttAxDataSource = ctLineSer.getCat();
                    cttAxDataSource.getStrRef().setF(formatAsString(category));

                    CTNumDataSource ctNumDataSource = ctLineSer.getVal();
                    ctNumDataSource.getNumRef().setF(formatAsString(data));
                }
            }

            ctPlotArea.getPieChartArray();
            ctPlotArea.getPie3DChartList();
            ctPlotArea.getRadarChartList();
            ctPlotArea.getScatterChartList();
            ctPlotArea.getSerAxList();
            ctPlotArea.getStockChartList();
            ctPlotArea.getSurface3DChartList();
            ctPlotArea.getValAxList();
        }


    }

    public String formatAsString(CellReference cellReference) {
        cellReference = new CellReference(sheetName, cellReference.getRow(), cellReference.getCol(), true, true);
        String area1 = cellReference.formatAsString();
        return null;
    }

    public String formatAsString(AreaReference areaReference) {
        CellReference firstCell = areaReference.getFirstCell();
        CellReference lastCell = areaReference.getLastCell();
        CellReference cellReference = new CellReference(sheetName, firstCell.getRow(), firstCell.getCol(), true, true);
        String area1 = cellReference.formatAsString();

        cellReference = new CellReference(sheetName, lastCell.getRow(), lastCell.getCol(), true, true);
        String area2 = cellReference.formatAsString(false);

        System.out.println(area1 + ":" + area2);
        return null;
    }
}
