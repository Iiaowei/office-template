package org.example.template.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void test1() {
        String position = Position.getPosition(29, 26);
        System.out.println(position);
    }
}