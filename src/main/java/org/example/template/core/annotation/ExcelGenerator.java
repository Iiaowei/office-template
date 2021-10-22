package org.example.template.core.annotation;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author liaowei
 * @version V1.0
 * @date 2021/10/22 15:53:39
 */
public class ExcelGenerator {
    private ExcelTemplate templateStyle;
    private List<Object> data;
    private HSSFWorkbook workbook;
    private int row;

    public ExcelGenerator(ExcelTemplate templateStyle, List<Object> data) {
        this.templateStyle = templateStyle;
        this.data = data;
        workbook = new HSSFWorkbook();
        row = 0;
    }

    public void go(String fileName) {
        HSSFPalette palette = workbook.getCustomPalette();
        HSSFSheet sheet = writeHead(palette, fileName);
        write(fileName);
    }

    private HSSFSheet writeHead(HSSFPalette palette, String sheetName) {
        List<TemplateDefinition> properties = templateStyle.getProperties();
        HSSFSheet sheet = workbook.createSheet(sheetName);
        HSSFRow row = sheet.createRow(this.row++);
        int pos = 0;
        for (TemplateDefinition property : properties) {
            HSSFCell cell = row.createCell(pos++);
            cell.setCellValue(property.getParameterName());

            short fontColor = templateStyle.getFontColor(palette);
            HSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setColor(fontColor);

            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFont(font);
            short foregroundColor = templateStyle.getForegroundColor(palette);
            cellStyle.setFillForegroundColor(foregroundColor);
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(cellStyle);
        }
        return sheet;
    }

    private void writeData(HSSFSheet sheet, HSSFPalette palette) {
        HSSFRow row = sheet.createRow(this.row++);

        for (Object o : data) {
            if (o instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) o;
                Collection<Object> values = map.values();
                for (int i = 0; i < values.size(); i++) {
                    HSSFCell cell = row.createCell(i);
                    cell.setCellValue((String) values[i]);
                }
            }
        }
    }

    private void write(String filePath) {
        try {
            workbook.write(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
