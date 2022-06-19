package org.example.template.core.table;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/18 1:52:12
 */
public class TableWriter {
    private XSSFWorkbook workbook;
    private ExcelTableHeader tableHeader;

    public <T> void write(List<T> data, String name) {
        int columnNum = tableHeader.length();
        int rowNum = data.size() + 1;

        AreaReference reference = workbook.getCreationHelper().createAreaReference(
                new CellReference(0, 0), new CellReference(rowNum, columnNum));

        XSSFSheet sheet = workbook.createSheet(name);
        XSSFTable table = sheet.createTable(reference);

        table.setName(name);
        table.setDisplayName(name);

        table.getCTTable().addNewTableStyleInfo();
        table.getCTTable().getTableStyleInfo().setName("TableStyleMedium2");

        // Style the table
        XSSFTableStyleInfo style = (XSSFTableStyleInfo) table.getStyle();
        style.setName("TableStyleMedium2");
        style.setShowColumnStripes(false);
        style.setShowRowStripes(true);
        style.setFirstColumn(false);
        style.setLastColumn(false);
        style.setShowRowStripes(true);
        style.setShowColumnStripes(true);

        // Set the values for the table
        XSSFRow row;
        XSSFCell cell;

        int rowIndex = 0;
        row = sheet.createRow(rowIndex++);
        for (int j = 0; j < columnNum; j++) {
            cell = row.createCell(j);
            cell.setCellValue(tableHeader.getColumnName(j));
        }


        for (T obj : data) {
            row = sheet.createRow(rowIndex++);

            for (int j = 0; j < columnNum; j++) {
                cell = row.createCell(j);
                String columnCode = tableHeader.getColumnCode(j);
                Field declaredField;
                Object value;
                try {
                    declaredField = obj.getClass().getDeclaredField(columnCode);
                    declaredField.setAccessible(true);
                    value = declaredField.get(obj);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                cell.setCellValue((String) value);
            }
        }
        // Save
        try (FileOutputStream fileOut = new FileOutputStream(name + ".xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
