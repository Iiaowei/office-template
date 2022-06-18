package org.example.template.core;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/17 19:46:09
 */
public class Placeholder {
    private String sheetName;
    private String prefix;
    private String name;
    private int rowIndex;
    private int columnIndex;
    private boolean isMerge;
    private CellRangeAddress cellAddresses;
    private CellStyle cellStyle;

    public String getSheetName() {
        return sheetName;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getName() {
        return name;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public boolean isMerge() {
        return isMerge;
    }

    public CellRangeAddress getCellAddresses() {
        return cellAddresses;
    }

    public CellStyle getCellStyle() {
        return cellStyle;
    }
}
