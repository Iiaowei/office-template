package org.example.template.parse;

import org.example.template.core.Table;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableClassParserTest {
    @Test
    void test1() {
        TableClassParser.parse(Table.class);
    }

}