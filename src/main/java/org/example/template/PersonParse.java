package org.example.template;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.example.template.dto.ESDto;
import org.example.template.dto.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/7/4 11:19:11
 */
public class PersonParse {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try (SXSSFWorkbook workbook = new SXSSFWorkbook();
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\IdeaProjects\\office-template\\src\\main\\resources\\shga_sample_750k\\person_info.json")))) {
            SXSSFSheet sheet = workbook.createSheet();
            AtomicInteger i = new AtomicInteger(1);
            List<String> errors = new ArrayList<>();
            br.lines().forEach(line -> {
                String json = null;
                try {
                    ESDto esDto = objectMapper.readValue(line, ESDto.class);
                    Map<String, Object> sourceMap = esDto.get_source();

                    SXSSFRow row = sheet.createRow(i.getAndIncrement());

                    String rname = (String) sourceMap.get("RNAME");
                    String sex = (String) sourceMap.get("SEX");
                    Integer age = Integer.valueOf(String.valueOf(sourceMap.get("AGE")));
                    String birthday = (String) sourceMap.get("BIRTHDAY");;
                    String bplace = (String) sourceMap.get("BPLACE");
                    String idtype = (String) sourceMap.get("IDTYPE");
                    String idno = (String) sourceMap.get("IDNO");


                    String query_string = (String) sourceMap.get("QUERY_STRING");


                    int columnIndex = 0;
                    row.createCell(columnIndex++).setCellValue(rname);
                    row.createCell(columnIndex++).setCellValue(sex);
                    row.createCell(columnIndex++).setCellValue(age == null? 0: age);
                    row.createCell(columnIndex++).setCellValue(birthday);
                    row.createCell(columnIndex++).setCellValue(bplace);
                    row.createCell(columnIndex++).setCellValue(idtype);
                    row.createCell(columnIndex++).setCellValue(idno);
                    row.createCell(columnIndex).setCellValue(query_string);
                } catch (IOException e) {
                    errors.add(json);
                }
            });
            SXSSFSheet sheet1 = workbook.createSheet("error");
            errors.forEach(error -> {
                SXSSFRow row = sheet1.createRow(0);
                SXSSFCell cell = row.createCell(0);
                cell.setCellValue(error);
            });
            workbook.write(new FileOutputStream("D:\\IdeaProjects\\office-template\\src\\main\\resources\\shga_sample_750k\\person_info.xlsx"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
