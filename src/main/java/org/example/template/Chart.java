package org.example.template;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/7/13 12:38:41
 */
public class Chart {
    private ChartTypes chartType;
    private CellReference title;
    private AreaReference category;
    private AreaReference data;

    public ChartTypes getChartType() {
        return chartType;
    }

    public void setChartType(ChartTypes chartType) {
        this.chartType = chartType;
    }

    public CellReference getTitle() {
        return title;
    }

    public void setTitle(CellReference title) {
        this.title = title;
    }

    public AreaReference getCategory() {
        return category;
    }

    public void setCategory(AreaReference category) {
        this.category = category;
    }

    public AreaReference getData() {
        return data;
    }

    public void setData(AreaReference data) {
        this.data = data;
    }

}
