package org.example.template.excel.table;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TableWriter implements AutoCloseable {
    private final XSSFWorkbook xssfWorkbook;
    private final List<ExcelTable> excelTables;
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    private TableWriter() {
        this.xssfWorkbook = new XSSFWorkbook();
        this.excelTables = new ArrayList<>();
    }
    private TableWriter(InputStream inputStream) throws IOException {
        this.xssfWorkbook = new XSSFWorkbook(inputStream);
        this.excelTables = new ArrayList<>();
    }

    public static TableWriter newTableWriter() {
        return new TableWriter();
    }

    public static TableWriter newTableWriter(InputStream templateInputStream) throws IOException {
        return new TableWriter(templateInputStream);
    }

    public <T> void write(Class<T> templateClass, List<T> data) {
        excelTables.add(new ExcelTable(templateClass, data));
    }

    public void finish() throws IOException {
        excelTables.forEach(excelTable -> excelTable.write(xssfWorkbook));
        xssfWorkbook.write(byteArrayOutputStream);
    }

    public ByteArrayOutputStream getByteArrayOutputStream() {
        return byteArrayOutputStream;
    }

    @Deprecated
    public void save(String path) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
        }
    }

    @Override
    public void close() throws IOException {
        xssfWorkbook.close();
    }
}
