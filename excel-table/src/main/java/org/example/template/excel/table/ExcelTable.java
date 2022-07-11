package org.example.template.excel.table;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;
import org.example.template.constant.func.SubTotalFuncNum;
import org.example.template.excel.table.annotation.Column;
import org.example.template.excel.table.annotation.Table;
import org.example.template.excel.table.exception.TableNotFoundException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STTotalsRowFunction;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

public class ExcelTable {
    private String sheetName;
    private String tableName;
    private boolean totals;
    private final List<Map<String, Object>> data;
    private final Map<String, SubTotalFuncNum> subTotalMap;
    private final List<String> headers = new ArrayList<>();

    public <T> ExcelTable(Class<T> templateClass, List<T> data) {
        this.data = new ArrayList<>(data.size());
        subTotalMap = new HashMap<>(16);
        parse(templateClass, data);
    }

    private <T> void parse(Class<T> templateClass, List<T> data) {
        Table table = templateClass.getAnnotation(Table.class);

        if (table == null) {
            throw new TableNotFoundException("模版类没有添加 Table 注解.");
        }
        this.sheetName = table.sheet();
        this.tableName = table.name();
        this.totals = table.totals();

        Map<String, String> codeAndNameMap = new HashMap<>(16);


        for (Field declaredField : templateClass.getDeclaredFields()) {
            declaredField.setAccessible(true);
            Column column = declaredField.getAnnotation(Column.class);

            if (column == null) {
                continue;
            }
            headers.add(column.name());
            codeAndNameMap.put(declaredField.getName(), column.name());

            if (this.totals) {
                subTotalMap.put(column.name(), column.subTotal());
            }
        }

        Set<String> keySet = codeAndNameMap.keySet();
        for (T e : data) {
            Map<String, Object> map = new HashMap<>(16);

            keySet.forEach(filedName -> {
                try {
                    Field field = templateClass.getDeclaredField(filedName);
                    field.setAccessible(true);

                    Object o = field.get(e);
                    map.put(codeAndNameMap.get(filedName), o);
                } catch (NoSuchFieldException | IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            });
            this.data.add(map);
        }
    }

    public void write(XSSFWorkbook workbook) {
        XSSFSheet sheet = createSheetIfAbsent(workbook);
        XSSFTable table = createTableIfAbsent(workbook);

        int dataStartRowIndex = table.getStartRowIndex() + 1;

        for (Map<String, Object> map : data) {
            XSSFRow row = sheet.createRow(dataStartRowIndex++);

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                int columnIndex = table.findColumnIndex(key);
                XSSFCell cell = row.createCell(columnIndex);

                if (value instanceof BigDecimal) {
                    BigDecimal newVal = (BigDecimal) value;
                    cell.setCellValue(newVal.doubleValue());
                } else {
                    cell.setCellValue((String) value);
                }
            }
        }
        setTotals(table);
    }

    public void setTotals(XSSFTable table) {
        if (!totals) {
            return;
        }

        table.getCTTable().setTotalsRowCount(1);
        XSSFRow totalsRow = table.getXSSFSheet().createRow(table.getEndRowIndex());
        int startColIndex = table.getStartColIndex();

        for (Map.Entry<String, SubTotalFuncNum> entry : subTotalMap.entrySet()) {
            String key = entry.getKey();
            SubTotalFuncNum funcNum = entry.getValue();

            int columnIndex = table.findColumnIndex(key);

            if (startColIndex == columnIndex) {
                table.getCTTable().getTableColumns().getTableColumnList().get(0).setTotalsRowLabel("总计");
                totalsRow.createCell(columnIndex).setCellValue("总计");
            } else {
                STTotalsRowFunction.Enum totalsRowFunction = getTotalsRowFunction(funcNum);
                if (totalsRowFunction != null) {
                    table.getCTTable().getTableColumns()
                            .getTableColumnList().get(columnIndex - startColIndex)
                            .setTotalsRowFunction(totalsRowFunction);

                    totalsRow.createCell(columnIndex).setCellFormula("SUBTOTAL(" + funcNum.getValue() +"," + tableName + "[" + key + "])");
                }

            }
//            totalsRow.createCell(reference.getFirstCell().getCol() + c).setCellFormula("name[[#Totals],[Heading2]]+name[[#Totals],[Heading3]]");
        }
    }

    private XSSFSheet createSheetIfAbsent(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }
        return sheet;
    }

    private XSSFTable createTableIfAbsent(XSSFWorkbook workbook) {
        XSSFTable xssfTable = null;
        XSSFSheet sheet = workbook.getSheet(sheetName);
        for (XSSFTable table : sheet.getTables()) {
            if (this.tableName.equals(table.getName())) {
                xssfTable = table;
                break;
            }
        }
        if (xssfTable == null) {
            xssfTable = create(workbook);
        }
        return xssfTable;
    }

    private XSSFTable create(XSSFWorkbook workbook) {
        int rowNum = data.size() == 0 ? 1: data.size();

        if (totals) {
            rowNum++;
        }
        AreaReference reference = workbook.getCreationHelper().createAreaReference(
                new CellReference(0, 0), new CellReference(rowNum, headers.size() - 1));

        XSSFSheet sheet = workbook.getSheet(sheetName);
        XSSFRow row = sheet.createRow(0);

        for (int i = 0; i < headers.size(); i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(headers.get(i));
        }

        XSSFTable table = sheet.createTable(reference);

        table.setName(tableName);
        table.setDisplayName(tableName);

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

        return table;
    }

    private STTotalsRowFunction.Enum getTotalsRowFunction(SubTotalFuncNum funcNum) {
        switch (funcNum) {
            case AVERAGE_INCLUDE:
            case AVERAGE:
                return STTotalsRowFunction.AVERAGE;
            case COUNT_INCLUDE:
            case COUNT:
                return STTotalsRowFunction.COUNT;
            case MAX_INCLUDE:
            case MAX:
                return STTotalsRowFunction.MAX;
            case MIN_INCLUDE:
            case MIN:
                return STTotalsRowFunction.MIN;
            case STDEV_INCLUDE:
            case STDEV:
                return STTotalsRowFunction.STD_DEV;
            case SUM:
            case SUM_INCLUDE:
                return STTotalsRowFunction.SUM;
            case VAR_INCLUDE:
            case VAR:
                return STTotalsRowFunction.VAR;
            default:
                return null;
        }
    }
}
