package org.example.template.function;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rank implements Formula {
    private final AreaReference areaReference;
    private final CellReference cellReference;
    public static String FORMULA = "RANK";

    public Rank(CellReference cellReference, AreaReference areaReference) {
        if (Objects.isNull(cellReference) || Objects.isNull(areaReference)) {
            throw new IllegalArgumentException("param must not null");
        }
        this.cellReference = cellReference;
        this.areaReference = areaReference;
    }

    @Override
    public String getFormula() {
        List<String> params = new ArrayList<>();

        params.add(cellReference.formatAsString());
        params.add(areaReference.formatAsString());

        return FORMULA + open + String.join(",", params) + close;
    }

    @Override
    public String toString() {
        return getFormula();
    }
}
