package org.example.template;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class TableTest {
    @Test
    void test() {
        XSSFWorkbook workbook = new XSSFWorkbook();

        AreaReference reference = workbook.getCreationHelper().createAreaReference(
                new CellReference(0, 0), new CellReference(5, 3));

        System.out.println(reference.formatAsString());
        XSSFSheet sheet = workbook.createSheet();
        XSSFTable table = sheet.createTable(reference);

        table.setName("name");
        table.setDisplayName("name");

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

        for (int i = 0; i < 6; i++) {
            row = sheet.createRow(i);

            for (int j = 0; j < 4; j++) {
                cell = row.createCell(j);
                cell.setCellValue(i * j);
            }
        }

        // Save
        try (FileOutputStream fileOut = new FileOutputStream("/Users/liaowei/IdeaProjects/office-template/src/main/resources/name" + ".xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
