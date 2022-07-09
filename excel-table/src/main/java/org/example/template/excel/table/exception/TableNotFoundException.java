package org.example.template.excel.table.exception;

public class TableNotFoundException extends IllegalArgumentException {

    public TableNotFoundException(String message) {
        super(message);
    }
}
