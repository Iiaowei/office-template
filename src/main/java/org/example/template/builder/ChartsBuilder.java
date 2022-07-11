//package org.example.template.builder;
//
//import org.openxmlformats.schemas.drawingml.x2006.chart.*;
//
///**
// * @author liaowei
// * @version V1.0
// * @date 2022/7/11 10:36:07
// */
//public class ChartsBuilder {
//    {
//        CTChart ctChart = chart.getCTChart();
//        CTPlotArea ctPlotArea = ctChart.getPlotArea();
//
//        // the bar chart
//        CTBarChart ctBarChart = ctPlotArea.getBarChartArray(0);
//
//        CTBarSer[] serArray = ctBarChart.getSerArray();
//        // the bar series
//        CTBarSer ctBarSer = serArray[0];
//        CTSerTx ctSerTx = ctBarSer.getTx();
//        CTStrRef ctStrRef = ctSerTx.getStrRef();
//        ctStrRef.setF("养老金产品规模及数量排名!$B$1"); //数据列标题
//
//        CTAxDataSource cttAxDataSource = ctBarSer.getCat();
//        ctStrRef = cttAxDataSource.getStrRef();
//        ctStrRef.setF("养老金产品规模及数量排名!$A$2:$A$7");//x轴
//
//        CTNumDataSource ctNumDataSource = ctBarSer.getVal();
//        CTNumRef ctNumRef = ctNumDataSource.getNumRef();
//        ctNumRef.setF("养老金产品规模及数量排名!$B$2:$B$7");//y轴
//
//
//        CTScatterChart ctScatterChart = ctPlotArea.getScatterChartArray(0);
//
//        CTScatterSer[] ctScatterSers = ctScatterChart.getSerArray();
//        // the bar series
//        CTScatterSer ctScatterSer = ctScatterSers[0];
//        CTSerTx ctSerTx1 = ctScatterSer.getTx();
//        CTStrRef ctStrRef1 = ctSerTx1.getStrRef();
//        ctStrRef1.setF("养老金产品规模及数量排名!$C$1"); //数据列标题
//
//        CTAxDataSource ctAxDataSource = ctScatterSer.getXVal();
//        ctStrRef = ctAxDataSource.getStrRef();
//        ctStrRef.setF("养老金产品规模及数量排名!$A$2:$A$7");//x轴
//
//        CTNumDataSource ctNumDataSource1 = ctScatterSer.getYVal();
//        CTNumRef ctNumRef1 = ctNumDataSource1.getNumRef();
//        ctNumRef1.setF("养老金产品规模及数量排名!C$2:$C$7");//y轴
//    }
//
//    public static ChartsBuilder builder()
//}
