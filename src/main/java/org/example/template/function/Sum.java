package org.example.template.function;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;

import java.util.ArrayList;
import java.util.List;

public class Sum implements Formula, Append {
    private final List<AreaReference> areaReferences;
    private final List<CellReference> cellReferences;
    public static String FORMULA = "SUM";

    public Sum() {
        this.areaReferences = new ArrayList<>();
        this.cellReferences = new ArrayList<>();
    }

    @Override
    public String getFormula() {
        int areaSize = areaReferences.size();
        int cellSize = cellReferences.size();
        List<String> params = new ArrayList<>(areaSize + cellSize);

        areaReferences.forEach(areaReference -> params.add(areaReference.formatAsString()));
        cellReferences.forEach(cellReference -> params.add(cellReference.formatAsString()));

        return FORMULA + open + String.join(",", params) + close;
    }

    @Override
    public Sum append(AreaReference reference) {
        if (reference != null) {
            areaReferences.add(reference);
        }
        return this;
    }

    @Override
    public Sum append(CellReference cellReference) {
        if (cellReference != null) {
            cellReferences.add(cellReference);
        }
        return this;
    }

    @Override
    public void clean() {
        areaReferences.clear();
        cellReferences.clear();
    }

    @Override
    public String toString() {
        return getFormula();
    }
}
