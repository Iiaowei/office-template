package org.example.template;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;

import static org.example.template.ReferenceFormat.areaRefFormat;
import static org.example.template.ReferenceFormat.cellRefFormat;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/7/13 10:44:23
 */
public class CTLineSeriesWrapper implements CTSeriesWrapper {
    private final String sheetName;
    private final CTLineSer ctLineSer;

    public CTLineSeriesWrapper(String sheetName, CTLineSer ctLineSer) {
        this.sheetName = sheetName;
        this.ctLineSer = ctLineSer;
    }

    @Override
    public void setTitle(CellReference cellReference) {
        CTSerTx ctSerTx = ctLineSer.getTx();

        if (ctSerTx == null) {
            ctSerTx = ctLineSer.addNewTx();
        }
        ctSerTx.getStrRef().setF(cellRefFormat(sheetName,cellReference));
    }

    @Override
    public void setDataSource(AreaReference areaReference) {
        CTAxDataSource cttAxDataSource = ctLineSer.getCat();

        if (cttAxDataSource == null) {
            cttAxDataSource = ctLineSer.addNewCat();
        }
        cttAxDataSource.getStrRef().setF(areaRefFormat(sheetName, areaReference));
    }

    @Override
    public void setNumDataSource(AreaReference areaReference) {
        CTNumDataSource ctNumDataSource = ctLineSer.getVal();

        if (ctNumDataSource == null) {
            ctNumDataSource = ctLineSer.addNewVal();
        }
        ctNumDataSource.getNumRef().setF(areaRefFormat(sheetName, areaReference));
    }
}
