package org.example.template.builder;

import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class SXSSFWorkbookBuilder {
    private final SXSSFWorkbook workbook;

    private SXSSFWorkbookBuilder() {
        this.workbook = new SXSSFWorkbook();
    }

    public SXSSFWorkbookBuilder newSheet(String sheetName) {
        SXSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            workbook.createSheet(sheetName);
        }
        return this;
    }

    public static SXSSFWorkbookBuilder builder() {
        return new SXSSFWorkbookBuilder();
    }
}
