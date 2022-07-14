package org.example.template;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;

import static org.example.template.ReferenceFormat.areaRefFormat;
import static org.example.template.ReferenceFormat.cellRefFormat;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/7/13 10:44:23
 */
public class CTScatterSeriesWrapper implements CTSeriesWrapper {
    private final String sheetName;
    private final CTScatterSer ctScatterSer;

    public CTScatterSeriesWrapper(String sheetName, CTScatterSer ctScatterSer) {
        this.sheetName = sheetName;
        this.ctScatterSer = ctScatterSer;
    }

    @Override
    public void setTitle(CellReference cellReference) {
        CTSerTx ctSerTx = ctScatterSer.getTx();

        if (ctSerTx == null) {
            ctSerTx = ctScatterSer.addNewTx();
        }
        ctSerTx.getStrRef().setF(cellRefFormat(sheetName,cellReference));
    }

    @Override
    public void setDataSource(AreaReference areaReference) {
        CTAxDataSource cttAxDataSource = ctScatterSer.getXVal();

        if (cttAxDataSource == null) {
            cttAxDataSource = ctScatterSer.addNewXVal();
        }
        cttAxDataSource.getStrRef().setF(areaRefFormat(sheetName, areaReference));
    }

    @Override
    public void setNumDataSource(AreaReference areaReference) {
        CTNumDataSource ctNumDataSource = ctScatterSer.getYVal();

        if (ctNumDataSource == null) {
            ctNumDataSource = ctScatterSer.addNewYVal();
        }
        ctNumDataSource.getNumRef().setF(areaRefFormat(sheetName, areaReference));
    }
}
