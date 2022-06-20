package org.example.template.builder;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static org.apache.poi.ss.usermodel.BorderStyle.THIN;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/20 11:50:54
 */
public class SXSSFTableBuilder {
    private LinkedHashMap<String, String> headersMap;
    private Collection<Map<String, Object>> data;
    private String sheetName = "表1";
    private CellStyle headStyle;
    private CellStyle dataStyle;
    private Map<String, SpecialColumn> specialColumnMap = new HashMap<>(16);

    public SXSSFTableBuilder headers(LinkedHashMap<String, String> headers) {
        this.headersMap = headers;
        return this;
    }

    public SXSSFTableBuilder data(Collection<Map<String, Object>> data) {
        this.data = data;
        return this;
    }
    public  SXSSFTableBuilder addSpecialColumn(String columnName, short color, Function<Integer, Boolean> function) {
        specialColumnMap.put(columnName, new SpecialColumn(columnName, color, function));
        return this;
    }

    private CellStyle createStyle(Workbook workbook, short color) {
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderTop(THIN);
        style.setBorderBottom(THIN);
        style.setBorderLeft(THIN);
        style.setBorderRight(THIN);
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName("黑体");
        font.setColor(color);
        style.setFont(font);
        style.setWrapText(true);
        return style;
    }

    public static SXSSFTableBuilder builder() {
        return new SXSSFTableBuilder();
    }


    public OutputStream build() {
        Map<String, Integer> columnIndexMap = new HashMap<>(16);
        AtomicInteger atomicIndex = new AtomicInteger(-1);

        try (SXSSFWorkbook workbook = new SXSSFWorkbook()) {
            SXSSFSheet sheet = workbook.createSheet(sheetName);
            headStyle = workbook.getCellStyleAt(0);
            dataStyle = workbook.getCellStyleAt(0);

            int rowIndex = 0;
            SXSSFRow headerRow = sheet.createRow(rowIndex++);

            headersMap.forEach((k, v) -> {
                columnIndexMap.put(v, atomicIndex.incrementAndGet());
                SXSSFCell cell = headerRow.createCell(atomicIndex.get());
                cell.setCellValue(k);
                cell.setCellStyle(headStyle);
            });

            for (Map<String, Object> map : data) {
                SXSSFRow dataRow = sheet.createRow(rowIndex++);

                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();

                    Integer columnIndex = columnIndexMap.get(key);
                    SpecialColumn specialColumn = specialColumnMap.get(key);

                    if (columnIndex != null) {
                        SXSSFCell cell = dataRow.createCell(columnIndex);
                        if (specialColumn != null) {
                            CellStyle specialColumnCellStyle = specialColumn.getCellStyle();
                            if (specialColumnCellStyle == null) {
                                specialColumnCellStyle = createStyle(workbook, specialColumn.getColor());
                                specialColumn.setCellStyle(specialColumnCellStyle);
                            }
                            if (specialColumn.getFunction().apply((Integer) value)) {
                                cell.setCellStyle(specialColumnCellStyle);
                            }
                        } else {
                            cell.setCellStyle(dataStyle);
                        }

                        if (value instanceof BigDecimal) {
                            cell.setCellValue(((Number) value).doubleValue());
                        } else if (value instanceof Number) {
                            cell.setCellValue(((Number) value).intValue());
                        } else if (value instanceof Date) {
                            cell.setCellValue((Date) value);
                        } else {
                            cell.setCellValue((String) value);
                        }
                    }
                }
            }
            workbook.write(new FileOutputStream("999999.xlsx"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return OutputStream.nullOutputStream();
    }


    static class SpecialColumn {
        private String columnName;
        private short color;
        private CellStyle cellStyle;
        private Function<Integer, Boolean> function;

        public SpecialColumn(String columnName, short color, Function<Integer, Boolean> function) {
            this.columnName = columnName;
            this.color = color;
            this.function = function;
        }

        public String getColumnName() {
            return columnName;
        }

        public short getColor() {
            return color;
        }

        public Function<Integer, Boolean> getFunction() {
            return function;
        }

        public CellStyle getCellStyle() {
            return cellStyle;
        }

        public void setCellStyle(CellStyle cellStyle) {
            this.cellStyle = cellStyle;
        }
    }
}
