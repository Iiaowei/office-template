package org.example.template.builder;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.example.template.core.table.Column;
import org.example.template.core.table.ColumnType;
import org.example.template.parse.TableClassParser;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class SXSSTable {
    private SXSSFSheet sheet;
    private LinkedHashMap<String, Column> headersMap;
    private DecimalFormat decimalFormat = new DecimalFormat();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("");
    private int startRowNum = 0;
    private int startColNum = 0;


    public SXSSTable(Class<?> templateClass) {
        this.headersMap = TableClassParser.parse(templateClass);
    }

    public void setPosition(int startRowNum, int startColNum) {
        this.startRowNum = startRowNum;
        this.startColNum = startColNum;
    }

    public void write(Collection<Map<String, Object>> data) {
        int curRowNum = startRowNum;

        for (Map<String, Object> objectMap : data) {
            SXSSFRow row = sheet.createRow(curRowNum++);
            int curColNum = startColNum;

            for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                Column column = headersMap.get(key);
                if (column != null) {
                    SXSSFCell cell = row.createCell(curColNum++);
                    if (ColumnType.Decimal == column.getColumnType()) {

                    }
                }
            }
        }

    }
}
