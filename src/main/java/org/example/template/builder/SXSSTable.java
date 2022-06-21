package org.example.template.builder;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.example.template.core.table.Column;
import org.example.template.core.table.ColumnType;
import org.example.template.parse.TableClassParser;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class SXSSTable {
    private SXSSFSheet sheet;
    private final LinkedHashMap<String, Column> headersMap;
    private XSSFCellStyle headerStyle;
    private XSSFCellStyle dataStyle;
    private int startRowNum = 0;
    private int startColNum = 0;


    public SXSSTable(Class<?> templateClass) {
        this.headersMap = TableClassParser.parse(templateClass);
    }

    public void setPosition(int startRowNum, int startColNum) {
        this.startRowNum = startRowNum;
        this.startColNum = startColNum;
    }

    public SXSSTable addHeaderStyle(Function<CellStyleBuilder, XSSFCellStyle> function) {
        CellStyleBuilder builder = CellStyleBuilder.builder();
        this.headerStyle = function.apply(builder);
        return this;
    }

    public SXSSTable addDataStyle(Function<CellStyleBuilder, XSSFCellStyle> function) {
        CellStyleBuilder builder = CellStyleBuilder.builder();
        this.dataStyle = function.apply(builder);
        return this;
    }

    public void write(Collection<Map<String, Object>> data) {
        int curRowNum = startRowNum;
        int curColNum = startColNum;

        SXSSFRow row = sheet.createRow(curRowNum++);

        for (Map.Entry<String, Column> entry : this.headersMap.entrySet()) {
            SXSSFCell cell = row.createCell(curColNum++);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(entry.getKey());
        }

        for (Map<String, Object> objectMap : data) {
            row = sheet.createRow(curRowNum++);
            curColNum = startColNum;

            for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                Column column = headersMap.get(key);
                if (column != null) {
                    SXSSFCell cell = row.createCell(curColNum++);
                    cell.setCellStyle(dataStyle);

                    if (ColumnType.Decimal == column.getColumnType()) {
                        String numericFormat = column.getNumericFormat();
                        BigDecimal newValue = new BigDecimal(String.valueOf(value));

                        if (numericFormat != null && !numericFormat.isBlank()) {
                            DecimalFormat decimalFormat = new DecimalFormat(column.getNumericFormat());
                            newValue = new BigDecimal(decimalFormat.format(value));
                        }
                        cell.setCellValue(newValue.doubleValue());
                    } else if (ColumnType.Date == column.getColumnType()) {
                        String dateFormat = column.getDateFormat();

                        if (dateFormat != null && !dateFormat.isBlank()) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
                            String newValue = simpleDateFormat.format(value);
                            cell.setCellValue(newValue);
                        } else {
                            cell.setCellValue((Date) value);
                        }
                    } else if (ColumnType.Temporal == column.getColumnType()) {
                        String dateFormat = column.getDateFormat();

                        if (dateFormat != null && !dateFormat.isBlank()) {
                            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
                            String newValue = dateTimeFormatter.format((TemporalAccessor) value);
                            cell.setCellValue(newValue);
                        } else {
                            cell.setCellValue(value.toString());
                        }
                    } else {
                        cell.setCellValue(value.toString());
                    }
                }
            }
        }

    }
}
