package org.example.template.excel.table;

import org.example.template.excel.table.dto.Student;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ExcelTableTest {
    @Test
    void noTableAnnotation() {
        try (TableWriter tableWriter = TableWriter.newTableWriter()) {
            tableWriter.write(Student.class, new ArrayList<>());
            tableWriter.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void test1() {
        try (TableWriter tableWriter = TableWriter.newTableWriter()) {
            Student student1 = new Student("张三", new BigDecimal("34"), new BigDecimal("56"), new BigDecimal("75"));
            Student student2 = new Student("李四", new BigDecimal("56"), new BigDecimal("78"), new BigDecimal("89"));
            Student student3 = new Student("王武", new BigDecimal("76"), new BigDecimal("98"), new BigDecimal("88"));
            tableWriter.write(Student.class, Arrays.asList(student1, student2, student3));
            tableWriter.finish();
            tableWriter.save("/Users/liaowei/IdeaProjects/office-template/excel-table/src/test/resources/111.xlsx");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}