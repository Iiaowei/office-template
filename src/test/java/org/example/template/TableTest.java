package org.example.template;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;
import org.junit.jupiter.api.Test;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STTotalsRowFunction;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class TableTest {
    @Test
    void test() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row;
        XSSFCell cell;
        String[] tableHeadings = new String[]{"Heading1", "Heading2", "Heading3", "Heading4"};
        row  = sheet.createRow(0);
        for (int j = 0; j < tableHeadings.length; j++) {
            cell = row.createCell(j);
            cell.setCellValue(tableHeadings[j]);
        }
        for (int i = 1; i < 6; i++) {
            row = sheet.createRow(i);

            for (int j = 0; j < 4; j++) {
                cell = row.createCell(j);
                cell.setCellValue(i * j);
            }
        }

        AreaReference reference = workbook.getCreationHelper().createAreaReference(
                new CellReference(0, 0), new CellReference(6, 3));

        System.out.println(reference.formatAsString());

        XSSFTable table = sheet.createTable(reference);

        String tableName = "name";
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
        table.getCTTable().setTotalsRowCount(1);

        XSSFRow totalsRow = sheet.createRow(table.getEndRowIndex());
        List<CTTableColumn> tableColumnList = table.getCTTable().getTableColumns().getTableColumnList();
        for (int c = 0; c < table.getCTTable().getTableColumns().getTableColumnList().size(); c++) {
            switch (c) {
                case 0:
                    table.getCTTable().getTableColumns().getTableColumnList().get(c).setTotalsRowLabel("Totals: ");
                    totalsRow.createCell(reference.getFirstCell().getCol() + c).setCellValue("Totals: ");
                    break;
                case 1:
                    table.getCTTable().getTableColumns().getTableColumnList().get(c).setTotalsRowFunction(org.openxmlformats.schemas.spreadsheetml.x2006.main.STTotalsRowFunction.SUM);
                    totalsRow.createCell(reference.getFirstCell().getCol() + c).setCellFormula("SUBTOTAL(109," + tableName + "[" + tableHeadings[c] + "])");
                    break;
                case 2:
                    table.getCTTable().getTableColumns().getTableColumnList().get(c).setTotalsRowFunction(org.openxmlformats.schemas.spreadsheetml.x2006.main.STTotalsRowFunction.SUM);
                    totalsRow.createCell(reference.getFirstCell().getCol() + c).setCellFormula("SUBTOTAL(109," + tableName + "[" + tableHeadings[c] + "])");
                    break;
                case 3:
                    /// Formila on a totil row ??
                    totalsRow.createCell(reference.getFirstCell().getCol() + c).setCellFormula("name[[#Totals],[Heading2]]+name[[#Totals],[Heading3]]");
                    break;
                default:
                    break;
            }
        }



//        table.getCTTable()

        // Save
        try (FileOutputStream fileOut = new FileOutputStream("/Users/liaowei/IdeaProjects/office-template/src/main/resources/name" + ".xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
