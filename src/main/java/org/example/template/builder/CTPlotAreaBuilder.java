package org.example.template.builder;

import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.STBarDir;

public class CTPlotAreaBuilder {
    private CTPlotArea ctPlotArea;
    private CTBarChart curCTBarChart;

    private CTPlotAreaBuilder(CTPlotArea ctPlotArea) {
        this.ctPlotArea = ctPlotArea;
    }

    public CTPlotAreaBuilder addNewBarChart() {
        curCTBarChart = ctPlotArea.addNewBarChart();
        return this;
    }

    public CTPlotAreaBuilder addNewVaryColors(Boolean val) {
        curCTBarChart.addNewVaryColors().setVal(val);
        return this;
    }
    public CTPlotAreaBuilder addNewBarDir(STBarDir.Enum val) {
        curCTBarChart.addNewBarDir().setVal(val);
        return this;
    }

}
