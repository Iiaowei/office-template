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
import java.util.function.Consumer;
import java.util.function.Function;

import static org.apache.poi.ss.usermodel.BorderStyle.THIN;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/20 11:50:54
 */
public class SXSSFTableBuilder {
    private SXSSFWorkbook workbook;
    private LinkedHashMap<String, String> headersMap;
    private Collection<Map<String, Object>> data;
    private String sheetName = "表1";
    private CellStyle headStyle;
    private CellStyle dataStyle;
    private Map<String, SpecialColumn> specialColumnMap = new HashMap<>(16);

    private SXSSFTableBuilder() {
        workbook = new SXSSFWorkbook();
    }
    public SXSSFTableBuilder headers(Class<?> headerClass) {
//        this.headersMap = headers;
        return this;
    }

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

    public CellStyleBuilder addHeaderStyle() {
        CellStyleBuilder cellStyleBuilder = newCellStyleBuilder();
        this.headStyle = cellStyleBuilder.getCellStyle();
        return cellStyleBuilder;
    }

    public CellStyleBuilder addDataStyle() {
        CellStyleBuilder cellStyleBuilder = newCellStyleBuilder();
        this.dataStyle = cellStyleBuilder.getCellStyle();
        return cellStyleBuilder;
    }

    public CellStyleBuilder newCellStyleBuilder() {
        CellStyle cellStyle = workbook.createCellStyle();
        return new CellStyleBuilder(this.workbook, cellStyle, this);
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

        try  {
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
            workbook.close();
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

    static class CellStyleBuilder {
        private final SXSSFWorkbook workbook;
        private final CellStyle cellStyle;
        private final SXSSFTableBuilder tableBuilder;

        private CellStyleBuilder(SXSSFWorkbook workbook, CellStyle cellStyle, SXSSFTableBuilder tableBuilder) {
            this.workbook = workbook;
            this.cellStyle = cellStyle;
            this.tableBuilder = tableBuilder;
        }

        public CellStyleBuilder horizontalAlignment(HorizontalAlignment alignment) {
            this.cellStyle.setAlignment(alignment);
            return this;
        }

        public CellStyleBuilder border(Boolean border) {
            if (border) {
                this.cellStyle.setBorderBottom(THIN);
                this.cellStyle.setBorderLeft(THIN);
                this.cellStyle.setBorderRight(THIN);
                this.cellStyle.setBorderTop(THIN);
            }
            return this;
        }

        public CellStyleBuilder borderColor(short borderColor) {
            this.cellStyle.setBottomBorderColor(borderColor);
            this.cellStyle.setLeftBorderColor(borderColor);
            this.cellStyle.setRightBorderColor(borderColor);
            this.cellStyle.setTopBorderColor(borderColor);
            return this;
        }

        public CellStyleBuilder dataFormat(short dataFormat) {
            this.cellStyle.setDataFormat(dataFormat);
            return this;
        }

        public CellStyleBuilder fillBackgroundColor(short bgColor) {
            this.cellStyle.setFillBackgroundColor(bgColor);
            return this;
        }

        public CellStyleBuilder fillForegroundColor(short fgColor) {
            this.cellStyle.setFillForegroundColor(fgColor);
            return this;
        }

        public CellStyleBuilder fillPattern(FillPatternType fillPatternType) {
            this.cellStyle.setFillPattern(fillPatternType);
            return this;
        }

        public CellStyleBuilder hidden(Boolean hidden) {
            this.cellStyle.setHidden(hidden);
            return this;
        }

        public CellStyleBuilder indent(short indent) {
            this.cellStyle.setIndention(indent);
            return this;
        }

        public CellStyleBuilder locked(Boolean locked) {
            this.cellStyle.setLocked(locked);
            return this;
        }

        public CellStyleBuilder quotePrefixed(Boolean quotePrefix) {
            this.cellStyle.setQuotePrefixed(quotePrefix);
            return this;
        }

        public CellStyleBuilder rotation(short rotation) {
            this.cellStyle.setRotation(rotation);
            return this;
        }

        public CellStyleBuilder rotation(VerticalAlignment verticalAlignment) {
            this.cellStyle.setVerticalAlignment(verticalAlignment);
            return this;
        }

        public CellStyleBuilder wrapText(Boolean wrapText) {
            this.cellStyle.setWrapText(wrapText);
            return this;
        }

        private CellStyle getCellStyle() {
            return cellStyle;
        }

        public CellStyleBuilder newFont(Consumer<FontBuilder> consumer) {
            Font font = workbook.createFont();

            consumer.accept(new FontBuilder(this, font));
            this.cellStyle.setFont(font);
            return this;
        }

        public SXSSFTableBuilder end() {
            return tableBuilder;
        }

    }

    static class FontBuilder {
        private final CellStyleBuilder cellStyleBuilder;
        private final Font font;

        private FontBuilder(CellStyleBuilder cellStyleBuilder, Font font) {
            this.cellStyleBuilder = cellStyleBuilder;
            this.font = font;
        }

        public FontBuilder name(String name) {
            font.setFontName(name);
            return this;
        }

        public FontBuilder fontHeight(short height) {
            font.setFontHeight(height);
            return this;
        }

        public FontBuilder color(short color) {
            font.setColor(color);
            return this;
        }

        public FontBuilder charSet(byte charset) {
            font.setCharSet(charset);
            return this;
        }

        public FontBuilder charSet(int charset) {
            font.setCharSet(charset);
            return this;
        }

        public FontBuilder bold(Boolean bold) {
            font.setBold(bold);
            return this;
        }

        public FontBuilder italic(Boolean italic) {
            font.setItalic(italic);
            return this;
        }

        public FontBuilder strikeout(Boolean strikeout) {
            font.setStrikeout(strikeout);
            return this;
        }

        public FontBuilder typeOffset(short offset) {
            font.setTypeOffset(offset);
            return this;
        }

        public FontBuilder underline(byte underline) {
            font.setUnderline(underline);
            return this;
        }

        public CellStyleBuilder end() {
            return cellStyleBuilder;
        }
    }
}
