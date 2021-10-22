import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.example.template.core.annotation.HeadStyle;
import org.example.template.core.annotation.Property;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class FontTest {
    @Test
    public void test1() throws IOException {

//        font.setColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());
        HeadStyle annotation = City.class.getAnnotation(HeadStyle.class);
        int ccccc = annotation.color();
        System.out.println();
//        annotation.
        Field[] declaredFields = City.class.getDeclaredFields();

        int cc=0;
        for (int i = 0; i < declaredFields.length; i++) {
            Property annotation1 = declaredFields[i].getAnnotation(Property.class);
            cc = annotation1.color();
        }

        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFPalette palette = workbook.getCustomPalette();
        HSSFColor hssfColor = null;
        try {
            java.awt.Color color = new java.awt.Color(cc);
            hssfColor= palette.findColor((byte)color.getRed(), (byte)color.getGreen(), (byte)color.getBlue());
            if (hssfColor == null ){
                palette.setColorAtIndex(HSSFColor.HSSFColorPredefined.BLACK.getIndex(), (byte)color.getRed(), (byte)color.getGreen(), (byte)color.getBlue());
                hssfColor = palette.getColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
            }
        } catch (Exception e) {
        }

        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(hssfColor.getIndex());
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(111);
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);

        java.awt.Color color = new java.awt.Color(ccccc);
        HSSFColor hssfColor1 = null;
        try {
            hssfColor1= palette.findColor((byte)color.getRed(), (byte)color.getGreen(), (byte)color.getBlue());
            if (hssfColor1 == null ){
                palette.setColorAtIndex(HSSFColor.HSSFColorPredefined.BROWN.getIndex(), (byte)color.getRed(), (byte)color.getGreen(), (byte)color.getBlue());
                hssfColor1 = palette.getColor(HSSFColor.HSSFColorPredefined.BROWN.getIndex());
            }
        } catch (Exception e) {
        }
        cellStyle.setFillForegroundColor(hssfColor1.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(cellStyle);
        workbook.write(new File("D:\\IdeaProjects\\office-template\\src\\test\\java\\11.xlsx"));
    }
}
