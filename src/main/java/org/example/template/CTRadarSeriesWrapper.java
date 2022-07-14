package org.example.template;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;

import static org.example.template.ReferenceFormat.areaRefFormat;
import static org.example.template.ReferenceFormat.cellRefFormat;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/7/13 10:44:23
 */
public class CTRadarSeriesWrapper implements CTSeriesWrapper {
    private final String sheetName;
    private final CTRadarSer ctRadarSer;

    public CTRadarSeriesWrapper(String sheetName, CTRadarSer ctRadarSer) {
        this.sheetName = sheetName;
        this.ctRadarSer = ctRadarSer;
    }

    @Override
    public void setTitle(CellReference cellReference) {
        CTSerTx ctSerTx = ctRadarSer.getTx();

        if (ctSerTx == null) {
            ctSerTx = ctRadarSer.addNewTx();
        }
        ctSerTx.getStrRef().setF(cellRefFormat(sheetName,cellReference));
    }

    @Override
    public void setDataSource(AreaReference areaReference) {
        CTAxDataSource cttAxDataSource = ctRadarSer.getCat();

        if (cttAxDataSource == null) {
            cttAxDataSource = ctRadarSer.addNewCat();
        }
        cttAxDataSource.getStrRef().setF(areaRefFormat(sheetName, areaReference));
    }

    @Override
    public void setNumDataSource(AreaReference areaReference) {
        CTNumDataSource ctNumDataSource = ctRadarSer.getVal();

        if (ctNumDataSource == null) {
            ctNumDataSource = ctRadarSer.addNewVal();
        }
        ctNumDataSource.getNumRef().setF(areaRefFormat(sheetName, areaReference));
    }
}
