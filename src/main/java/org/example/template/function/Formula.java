package org.example.template.function;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;

public interface Formula {
    String open = "(";
    String close = ")";

    String getFormula();
}
