package org.example.template;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/7/7 13:17:06
 */
public class ReportReader {
    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<>();

        try (XSSFWorkbook workbook = new XSSFWorkbook("D:\\IdeaProjects\\office-template\\src\\main\\resources\\生产URL集成.xlsx")) {
            XSSFSheet sheet = workbook.getSheet("SQL Results");
            sheet.iterator().forEachRemaining(row -> {
                Map<String, Object> record = new LinkedHashMap<>(16);
                String reportId;
                if (CellType.NUMERIC == row.getCell(0).getCellType()) {
                    reportId = BigDecimal.valueOf(row.getCell(0).getNumericCellValue()).toString();
                } else {
                    reportId = row.getCell(0).getStringCellValue();
                }

                String reportName = row.getCell(1).getStringCellValue();
                String downloadUrl = row.getCell(2).getStringCellValue();

                record.put("reportId", reportId);
                record.put("reportName", reportName);

                if (downloadUrl.contains("?")) {
                    int indexOf = downloadUrl.indexOf("?");
                    String url = downloadUrl.substring(0, indexOf);

                    record.put("url", url);
                    Map<String, String> params = new HashMap<>(16);

                    String[] paramsArray = downloadUrl.substring(indexOf + 1)
                            .split("&");

                    for (int i = 0; i < paramsArray.length; i++) {
                        String[] split = paramsArray[i].split("=");
                        params.put(split[0], split.length > 1 ? split[1] : null);
                    }
                    record.put("params", params);
                    list.add(record);
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet();
            int rowIndex = 0;
            for (Map<String, Object> map : list) {
                String reportId = (String) map.get("reportId");
                String reportName = (String) map.get("reportName");
                String url = (String) map.get("url");


                Map<String, String> params = (Map<String, String>) map.get("params");

                for (Map.Entry<String, String> entry : params.entrySet()) {
                    int columnIndex = 0;
                    XSSFRow row = sheet.createRow(rowIndex++);
                    row.createCell(columnIndex++).setCellValue(reportId);
                    row.createCell(columnIndex++).setCellValue(reportName);
                    row.createCell(columnIndex++).setCellValue(url);
                    row.createCell(columnIndex++).setCellValue(entry.getKey());
                    row.createCell(columnIndex).setCellValue(entry.getValue());
                }
            }
            workbook.write(new FileOutputStream("111.xlsx"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
