package org.example.template.core.table;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/18 1:35:40
 */
public class ExcelTableHeader {
    private final List<Column> columns = new ArrayList<>();

    public void createColumn(String name, int index, String code, Class<?> typeClass) {
        Column column = new Column(index, name, code, typeClass);
        add(column);
    }

    public String getColumnName(int index) {
        return columns.get(index).getName();
    }

    public String getColumnCode(int index) {
        return columns.get(index).getCode();
    }

    public int length() {
        return columns.size();
    }

    private void add(Column column) {
        if (columns.size() == 0) {
            columns.add(column);
            return;
        }
        for (int i = columns.size() - 1; i >= 0; i--) {
            Column col = columns.get(i);

            if (i == 0 ||column.getIndex() >= col.getIndex()) {
                columns.add(i, column);
            }
        }
    }

    private static class Column {
        private final int index;
        private final String name;
        private final String code;
        private final Class<?> typeClass;
        public Column(int index, String name, String code, Class<?> typeClass) {
            this.index = index;
            this.name = name;
            this.code = code;
            this.typeClass = typeClass;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }

        public Class<?> getTypeClass() {
            return typeClass;
        }
    }
}
