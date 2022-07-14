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
        for (Chart chart : option.getCharts()) {
            CellReference title = chart.getTitle();
            ChartTypes chartType = chart.getChartType();
            AreaReference category = chart.getCategory();
            AreaReference data = chart.getData();

            if (ChartTypes.AREA == chartType) {
                if (ctPlotArea.getAreaChartArray().length > 0) {
                    CTAreaChart ctAreaChart = ctPlotArea.getAreaChartArray(0);

                    CTAreaSer[] serArray = ctAreaChart.getSerArray();
                    if(serArray.length > 0) {
                        setSeries(new CTAreaSeriesWrapper(sheetName, serArray[0]), chart);
                    }
                }
            } else if (ChartTypes.AREA3D == chartType) {
                if (ctPlotArea.getArea3DChartArray().length > 0) {
                    CTArea3DChart ctArea3DChart = ctPlotArea.getArea3DChartArray(0);
                    CTAreaSer[] serArray = ctArea3DChart.getSerArray();
                    if(serArray.length > 0) {
                        setSeries(new CTAreaSeriesWrapper(sheetName, serArray[0]), chart);
                    }
                }
            } else if (ChartTypes.BAR == chartType) {
                if (ctPlotArea.getBarChartArray().length > 0) {
                    CTBarChart ctBarChart = ctPlotArea.getBarChartArray(0);
                    CTBarSer[] ctBarSers = ctBarChart.getSerArray();
                    if(ctBarSers.length > 0) {
                        setSeries(new CTBarSeriesWrapper(sheetName, ctBarSers[0]), chart);
                    }
                }
            } else if (ChartTypes.BAR3D == chartType) {
                if (ctPlotArea.getBar3DChartArray().length > 0) {
                    CTBar3DChart ctBar3DChart = ctPlotArea.getBar3DChartArray(0);
                    CTBarSer[] ctBarSers = ctBar3DChart.getSerArray();
                    if(ctBarSers.length > 0) {
                        setSeries(new CTBarSeriesWrapper(sheetName, ctBarSers[0]), chart);
                    }
                }
            } else if (ChartTypes.DOUGHNUT == chartType) {
                if (ctPlotArea.getDoughnutChartArray().length > 0) {
                    CTDoughnutChart ctDoughnutChart = ctPlotArea.getDoughnutChartArray(0);
                    CTPieSer[] ctPieSers = ctDoughnutChart.getSerArray();
                    if(ctPieSers.length > 0) {
                        setSeries(new CTPieSeriesWrapper(sheetName, ctPieSers[0]), chart);
                    }
                }
            } else if (ChartTypes.LINE == chartType) {
                if (ctPlotArea.getLineChartArray().length > 0) {
                    CTLineChart ctLineChart = ctPlotArea.getLineChartArray(0);
                    CTLineSer[] ctLineSers = ctLineChart.getSerArray();
                    if(ctLineSers.length > 0) {
                        setSeries(new CTLineSeriesWrapper(sheetName, ctLineSers[0]), chart);
                    }
                }
            } else if (ChartTypes.LINE3D == chartType) {
                if (ctPlotArea.getLine3DChartArray().length > 0) {
                    CTLine3DChart ctLine3DChart = ctPlotArea.getLine3DChartArray(0);
                    CTLineSer[] ctLineSers = ctLine3DChart.getSerArray();
                    if(ctLineSers.length > 0) {
                        setSeries(new CTLineSeriesWrapper(sheetName, ctLineSers[0]), chart);
                    }
                }
            } else if (ChartTypes.PIE3D == chartType) {
                if (ctPlotArea.getPie3DChartArray().length > 0) {
                    CTPie3DChart chartArray = ctPlotArea.getPie3DChartArray(0);
                    CTPieSer[] ctPieSers = chartArray.getSerArray();
                    if(ctPieSers.length > 0) {
                        setSeries(new CTPieSeriesWrapper(sheetName, ctPieSers[0]), chart);
                    }
                }

            } else if (ChartTypes.PIE == chartType) {
                if (ctPlotArea.getPieChartArray().length > 0) {
                    CTPieChart chartArray = ctPlotArea.getPieChartArray(0);
                    CTPieSer[] ctPieSers = chartArray.getSerArray();
                    if(ctPieSers.length > 0) {
                        setSeries(new CTPieSeriesWrapper(sheetName, ctPieSers[0]), chart);
                    }
                }
            } else if (ChartTypes.RADAR == chartType) {
                if (ctPlotArea.getRadarChartArray().length > 0) {
                    CTRadarChart chartArray = ctPlotArea.getRadarChartArray(0);
                    CTRadarSer[] ctRadarSers = chartArray.getSerArray();
                    if(ctRadarSers.length > 0) {
                        setSeries(new CTRadarSeriesWrapper(sheetName, ctRadarSers[0]), chart);
                    }
                }
            } else if (ChartTypes.SCATTER == chartType) {
                if (ctPlotArea.getScatterChartArray().length > 0) {
                    CTScatterChart chartArray = ctPlotArea.getScatterChartArray(0);
                    CTScatterSer[] ctScatterSers = chartArray.getSerArray();
                    if(ctScatterSers.length > 0) {
                        setSeries(new CTScatterSeriesWrapper(sheetName, ctScatterSers[0]), chart);
                    }
                }
            } else if (ChartTypes.SURFACE == chartType) {
                if (ctPlotArea.getSurfaceChartArray().length > 0) {
                    CTSurfaceChart chartArray = ctPlotArea.getSurfaceChartArray(0);
                    CTSurfaceSer[] ctSurfaceSers = chartArray.getSerArray();
                    if(ctSurfaceSers.length > 0) {
                        setSeries(new CTSurfaceSeriesWrapper(sheetName, ctSurfaceSers[0]), chart);
                    }
                }
            } else if (ChartTypes.SURFACE3D == chartType) {
                if (ctPlotArea.getSurface3DChartArray().length > 0) {
                    CTSurface3DChart chartArray = ctPlotArea.getSurface3DChartArray(0);
                    CTSurfaceSer[] ctSurfaceSers = chartArray.getSerArray();
                    if(ctSurfaceSers.length > 0) {
                        setSeries(new CTSurfaceSeriesWrapper(sheetName, ctSurfaceSers[0]), chart);
                    }
                }
            }
        }
    }

    public void setSeries(CTSeriesWrapper seriesWrapper, Chart chart) {
        seriesWrapper.setTitle(chart.getTitle());
        seriesWrapper.setDataSource(chart.getCategory());
        seriesWrapper.setNumDataSource(chart.getData());
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
