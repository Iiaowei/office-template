package org.example.template;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/7/13 10:50:40
 */
public class ReferenceFormat {
    public static String cellRefFormat(String sheetName, CellReference cellReference) {
        cellReference = new CellReference(sheetName, cellReference.getRow(), cellReference.getCol(), true, true);
        return cellReference.formatAsString();
    }

    public static String areaRefFormat(String sheetName, AreaReference areaReference) {
        CellReference firstCell = areaReference.getFirstCell();
        CellReference lastCell = areaReference.getLastCell();
        CellReference cellReference = new CellReference(sheetName, firstCell.getRow(), firstCell.getCol(), true, true);
        String area1 = cellReference.formatAsString();

        cellReference = new CellReference(sheetName, lastCell.getRow(), lastCell.getCol(), true, true);
        String area2 = cellReference.formatAsString(false);

        return area1 + ":" + area2;
    }
}
