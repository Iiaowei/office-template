package org.example.template;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;

import static org.example.template.ReferenceFormat.areaRefFormat;
import static org.example.template.ReferenceFormat.cellRefFormat;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/7/13 10:44:23
 */
public class CTPieSeriesWrapper implements CTSeriesWrapper {
    private final String sheetName;
    private final CTPieSer ctPieSer;

    public CTPieSeriesWrapper(String sheetName, CTPieSer ctPieSer) {
        this.sheetName = sheetName;
        this.ctPieSer = ctPieSer;
    }

    @Override
    public void setTitle(CellReference cellReference) {
        CTSerTx ctSerTx = ctPieSer.getTx();

        if (ctSerTx == null) {
            ctSerTx = ctPieSer.addNewTx();
        }
        ctSerTx.getStrRef().setF(cellRefFormat(sheetName,cellReference));
    }

    @Override
    public void setDataSource(AreaReference areaReference) {
        CTAxDataSource cttAxDataSource = ctPieSer.getCat();

        if (cttAxDataSource == null) {
            cttAxDataSource = ctPieSer.addNewCat();
        }
        cttAxDataSource.getStrRef().setF(areaRefFormat(sheetName, areaReference));
    }

    @Override
    public void setNumDataSource(AreaReference areaReference) {
        CTNumDataSource ctNumDataSource = ctPieSer.getVal();

        if (ctNumDataSource == null) {
            ctNumDataSource = ctPieSer.addNewVal();
        }
        ctNumDataSource.getNumRef().setF(areaRefFormat(sheetName, areaReference));
    }
}
