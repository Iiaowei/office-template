package org.example.template.function;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SumTest {
    @Test
    void areaReferenceTest() {
        try (HSSFWorkbook hssfWorkbook = new HSSFWorkbook()) {
            AreaReference areaReference1 = hssfWorkbook.getCreationHelper().createAreaReference(new CellReference(0, 0), new CellReference(0, 3));
            AreaReference areaReference2 = hssfWorkbook.getCreationHelper().createAreaReference(new CellReference(0, 4), new CellReference(0,6));
            AreaReference areaReference3 = hssfWorkbook.getCreationHelper().createAreaReference(new CellReference(0, 7), new CellReference(0,9));
            AreaReference areaReference4 = hssfWorkbook.getCreationHelper().createAreaReference(new CellReference(0, 10), new CellReference(0,12));

            Sum sum = new Sum();
            sum.append(areaReference1);
            sum.append(areaReference2);
            sum.append(areaReference3);
            sum.append(areaReference4);

            System.out.println(sum.getFormula());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void CellReferenceTest() {
        try (HSSFWorkbook hssfWorkbook = new HSSFWorkbook()) {
            CellReference cellReference1 = new CellReference(0, 0);
            CellReference cellReference2 = new CellReference(1, 0);
            CellReference cellReference3 = new CellReference(2, 0);
            CellReference cellReference4 = new CellReference(3, 0);

            Sum sum = new Sum();
            sum.append(cellReference1);
            sum.append(cellReference2);
            sum.append(cellReference3);
            sum.append(cellReference4);

            System.out.println(sum.getFormula());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void CellReferenceAndAreaReferenceTest() {
        try (HSSFWorkbook hssfWorkbook = new HSSFWorkbook()) {
            CellReference cellReference1 = new CellReference(0, 0);
            CellReference cellReference2 = new CellReference(1, 0);
            CellReference cellReference3 = new CellReference(2, 0);
            CellReference cellReference4 = new CellReference(3, 0);

            Sum sum = new Sum();
            sum.append(cellReference1);
            sum.append(cellReference2);
            sum.append(cellReference3);
            sum.append(cellReference4);

            AreaReference areaReference1 = hssfWorkbook.getCreationHelper().createAreaReference(new CellReference(0, 0), new CellReference(0, 3));
            AreaReference areaReference2 = hssfWorkbook.getCreationHelper().createAreaReference(new CellReference(0, 4), new CellReference(0,6));
            AreaReference areaReference3 = hssfWorkbook.getCreationHelper().createAreaReference(new CellReference(0, 7), new CellReference(0,9));
            AreaReference areaReference4 = hssfWorkbook.getCreationHelper().createAreaReference(new CellReference(0, 10), new CellReference(0,12));

            sum.append(areaReference1);
            sum.append(areaReference2);
            sum.append(areaReference3);
            sum.append(areaReference4);

            System.out.println(sum.getFormula());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}