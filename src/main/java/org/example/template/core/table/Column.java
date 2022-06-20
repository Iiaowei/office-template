package org.example.template.core.table;

public class Column implements Comparable<Column> {
    private String name;
    private String aliasName;
    private Integer index = -1;
    private String dateFormat;
    private String numericFormat;
    private ColumnType columnType;

    public void setName(String name) {
        this.name = name;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setNumericFormat(String numericFormat) {
        this.numericFormat = numericFormat;
    }

    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }

    public String getName() {

        if (aliasName != null && !aliasName.isBlank()) {
            return aliasName;
        }
        return name;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public String getNumericFormat() {
        return numericFormat;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    @Override
    public int compareTo(Column o) {
        if (o == null || index > o.index) {
            return 1;
        }
        if (index < o.index) {
            return -1;
        }
        return 0;
    }
}
