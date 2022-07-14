package org.example.template;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAreaSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;

import static org.example.template.ReferenceFormat.areaRefFormat;
import static org.example.template.ReferenceFormat.cellRefFormat;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/7/13 10:44:23
 */
public class CTAreaSeriesWrapper implements CTSeriesWrapper {
    private final String sheetName;
    private final CTAreaSer ctAreaSer;

    public CTAreaSeriesWrapper(String sheetName, CTAreaSer ctAreaSer) {
        this.sheetName = sheetName;
        this.ctAreaSer = ctAreaSer;
    }

    @Override
    public void setTitle(CellReference cellReference) {
        CTSerTx ctSerTx = ctAreaSer.getTx();

        if (ctSerTx == null) {
            ctSerTx = ctAreaSer.addNewTx();
        }
        ctSerTx.getStrRef().setF(cellRefFormat(sheetName,cellReference));
    }

    @Override
    public void setDataSource(AreaReference areaReference) {
        CTAxDataSource cttAxDataSource = ctAreaSer.getCat();

        if (cttAxDataSource == null) {
            cttAxDataSource = ctAreaSer.addNewCat();
        }
        cttAxDataSource.getStrRef().setF(areaRefFormat(sheetName, areaReference));
    }

    @Override
    public void setNumDataSource(AreaReference areaReference) {
        CTNumDataSource ctNumDataSource = ctAreaSer.getVal();

        if (ctNumDataSource == null) {
            ctNumDataSource = ctAreaSer.addNewVal();
        }
        ctNumDataSource.getNumRef().setF(areaRefFormat(sheetName, areaReference));
    }
}
