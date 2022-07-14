package org.example.template;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurfaceSer;

import static org.example.template.ReferenceFormat.areaRefFormat;
import static org.example.template.ReferenceFormat.cellRefFormat;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/7/13 10:44:23
 */
public class CTSurfaceSeriesWrapper implements CTSeriesWrapper {
    private final String sheetName;
    private final CTSurfaceSer ctSurfaceSer;

    public CTSurfaceSeriesWrapper(String sheetName, CTSurfaceSer ctSurfaceSer) {
        this.sheetName = sheetName;
        this.ctSurfaceSer = ctSurfaceSer;
    }

    @Override
    public void setTitle(CellReference cellReference) {
        CTSerTx ctSerTx = ctSurfaceSer.getTx();

        if (ctSerTx == null) {
            ctSerTx = ctSurfaceSer.addNewTx();
        }
        ctSerTx.getStrRef().setF(cellRefFormat(sheetName,cellReference));
    }

    @Override
    public void setDataSource(AreaReference areaReference) {
        CTAxDataSource cttAxDataSource = ctSurfaceSer.getCat();

        if (cttAxDataSource == null) {
            cttAxDataSource = ctSurfaceSer.addNewCat();
        }
        cttAxDataSource.getStrRef().setF(areaRefFormat(sheetName, areaReference));
    }

    @Override
    public void setNumDataSource(AreaReference areaReference) {
        CTNumDataSource ctNumDataSource = ctSurfaceSer.getVal();

        if (ctNumDataSource == null) {
            ctNumDataSource = ctSurfaceSer.addNewVal();
        }
        ctNumDataSource.getNumRef().setF(areaRefFormat(sheetName, areaReference));
    }
}
