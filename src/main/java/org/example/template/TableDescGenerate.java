package org.example.template;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/22 11:01:16
 */
public class TableDescGenerate {
    private static List<String> functions = new ArrayList<>();

    static {
        functions.add("current_timestamp");
    }
//    id varchar(100) primary key comment '主键',
    public static void main(String[] args) {
        try (XSSFWorkbook xssfWorkbook = new XSSFWorkbook("D:\\IdeaProjects\\office-template\\src\\main\\resources\\投资明细页面.xlsx")) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            int rowNum = 0;
            int colNum = 0;
            AtomicBoolean isHead = new AtomicBoolean(true);

            List<String> createTableDesc = new ArrayList<>();

            List<String> tableDesc = null;
            List<String> tableColumnDef = null;

            for (Row row : xssfSheet) {
                if (isHead.get()) {
                    isHead.set(false);
                    continue;
                }
                StringBuilder columnBuilder = new StringBuilder();

                String tableName = row.getCell(0).getStringCellValue();
                String tableComment = row.getCell(1).getStringCellValue();
                if (!tableName.isBlank()) {
                    if (tableColumnDef != null) {
                        tableDesc.add(3,String.join(",\n", tableColumnDef));
                    }
                    if (tableDesc != null) {
                        System.out.println(String.join("", tableDesc));
                        System.out.println();
                    }
                    tableDesc = new ArrayList<>();

                    tableColumnDef = new ArrayList<>();
                    tableDesc.add("drop table if exists `" + tableName + "`;\n");
                    tableDesc.add("create table `" + tableName + "`\n");
                    tableDesc.add("(\n");

                    StringBuilder endBuilder = new StringBuilder();
                    endBuilder.append("\n)");
                    if (!tableComment.isBlank()) {
                        endBuilder.append("comment ").append('\'').append(tableComment).append('\'');
                    }
                    endBuilder.append(";\n");
                    tableDesc.add(endBuilder.toString());
                }

                Cell columnCell = row.getCell(2);
                Cell commentCell = row.getCell(3);
                Cell typeCell = row.getCell(4);
                Cell lengthCell = row.getCell(5);
                Cell defaultCell = row.getCell(6);
                Cell constraintCell = row.getCell(7);


                String columnName = columnCell.getStringCellValue();
                String comment = commentCell.getStringCellValue();
                String type = typeCell.getStringCellValue();
                String defaultValue = defaultCell.getStringCellValue();
                String constraint = constraintCell.getStringCellValue();
                String length = lengthCell.getStringCellValue();

                columnBuilder.append(columnName);
                columnBuilder.append(' ');
                columnBuilder.append(type);
                if (length != null && !length.isBlank()) {
                    columnBuilder.append('(');
                    columnBuilder.append(length);
                    columnBuilder.append(')');
                }

                if (defaultValue != null && !defaultValue.isBlank()) {
                    columnBuilder.append(' ');
                    columnBuilder.append("default");
                    columnBuilder.append(' ');
                    if (functions.contains(defaultValue.trim())) {
                        columnBuilder.append(defaultValue.trim());
                    } else {
                        columnBuilder.append('\'');
                        columnBuilder.append(defaultValue.trim());
                        columnBuilder.append('\'');
                    }

                }
                if (!constraint.isBlank()) {
                    columnBuilder.append(' ');
                    columnBuilder.append(constraint);
                }
                if (!comment.isBlank()) {
                    columnBuilder.append(' ');
                    columnBuilder.append("comment");
                    columnBuilder.append(' ');
                    columnBuilder.append('\'').append(comment).append('\'');
                }
                tableColumnDef.add(columnBuilder.toString());
            }
            if (tableColumnDef != null) {
                tableDesc.add(3,String.join(",\n", tableColumnDef));
            }
            if (tableDesc != null) {
                System.out.println(String.join("", tableDesc));
                System.out.println();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
