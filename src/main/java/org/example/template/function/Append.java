package org.example.template.function;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;

public interface Append {
    Append append(AreaReference reference);
    Append append(CellReference cellReference);
    void clean();
}
