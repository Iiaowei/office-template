package org.example.template.core.sheet;

import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/17 19:35:07
 */
public class Test {
    //    public static void main(String[] args) throws IOException {
//        Workbook workbook = new XSSFWorkbook(Files.newInputStream(Paths.get("")));
//
//        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
//
//        sheetIterator.forEachRemaining(sheet -> {
//            for (int rowNum = sheet.getFirstRowNum(); rowNum <= sheet.getLastRowNum(); rowNum++) {
//                if (rowNum < 0) {
//                    continue;
//                }
//
//                Row row = sheet.getRow(rowNum);
//                if (row == null) {
//                    continue;
//                }
//                for (int columnNum = row.getFirstCellNum(); columnNum <= row.getLastCellNum(); columnNum++) {
//                    if (columnNum < 0) {
//                        continue;
//                    }
//                    Cell cell = row.getCell(columnNum);
//                    if (cell == null) {
//                        continue;
//                    }
//
//                    CellAddress address = cell.getAddress();
//                    String stringCellValue = cell.getStringCellValue();
//                }
//            }
//        });
//    }
//    public static void main(String[] args) {
//        try (Workbook wb = new XSSFWorkbook()) {
//            XSSFSheet sheet = (XSSFSheet) wb.createSheet();
//
//            // Set which area the table should be placed in
//            AreaReference reference = wb.getCreationHelper().createAreaReference(
//                    new CellReference(0, 0), new CellReference(2, 2));
//
//            // Create
//            XSSFTable table = sheet.createTable(reference); //creates a table having 3 columns as of area reference
//            // but all of those have id 1, so we need repairing
//            table.getCTTable().getTableColumns().getTableColumnArray(1).setId(2);
//            table.getCTTable().getTableColumns().getTableColumnArray(2).setId(3);
//
//            table.setName("Test");
//            table.setDisplayName("Test_Table");
//
//            // For now, create the initial style in a low-level way
//            table.getCTTable().addNewTableStyleInfo();
//            table.getCTTable().getTableStyleInfo().setName("TableStyleMedium2");
//
//            // Style the table
//            XSSFTableStyleInfo style = (XSSFTableStyleInfo) table.getStyle();
//            style.setName("TableStyleMedium2");
//            style.setShowColumnStripes(false);
//            style.setShowRowStripes(true);
//            style.setFirstColumn(false);
//            style.setLastColumn(false);
//            style.setShowRowStripes(true);
//            style.setShowColumnStripes(true);
//
//            // Set the values for the table
//            XSSFRow row;
//            XSSFCell cell;
//            for (int i = 0; i < 3; i++) {
//                // Create row
//                row = sheet.createRow(i);
//                for (int j = 0; j < 3; j++) {
//                    // Create cell
//                    cell = row.createCell(j);
//                    if (i == 0) {
//                        cell.setCellValue("Column" + (j + 1));
//                    } else {
//                        cell.setCellValue((i + 1.0) * (j + 1.0));
//                    }
//                }
//            }
//
//            // Save
//            try (FileOutputStream fileOut = new FileOutputStream("ooxml-table.xlsx")) {
//                wb.write(fileOut);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void main(String[] args) {
        try (XSSFWorkbook workbook = new XSSFWorkbook("D:\\IdeaProjects\\office-template\\ooxml-table.xlsx")) {
            XSSFSheet sheet0 = workbook.getSheet("Sheet0");
            XSSFRow row = sheet0.createRow(0);
            XSSFCell cell = row.createCell(0);

            CellType cellType = cell.getCellType();

            if (CellType.NUMERIC == cellType) {

            }

            for (XSSFTable table : sheet0.getTables()) {
                System.out.println(table.getStartRowIndex());
                System.out.println(table.getEndRowIndex());
                for (XSSFTableColumn column : table.getColumns()) {
                    System.out.println(column.getName());
                    System.out.println(column.getColumnIndex());
                }
            }


            workbook.write(new FileOutputStream("D:\\IdeaProjects\\office-template\\ooxml-table111.xlsx"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
