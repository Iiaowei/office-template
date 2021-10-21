import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class FontTest {
    @Test
    public void test1() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();

//        font.setColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());

        HSSFPalette palette = workbook.getCustomPalette();
        HSSFColor hssfColor = null;
        try {
            java.awt.Color color = new java.awt.Color(0xE6E60E);
            hssfColor= palette.findColor((byte)color.getRed(), (byte)color.getGreen(), (byte)color.getBlue());
            if (hssfColor == null ){
                palette.setColorAtIndex(HSSFColor.HSSFColorPredefined.BLACK.getIndex(), (byte)color.getRed(), (byte)color.getGreen(), (byte)color.getBlue());
                hssfColor = palette.getColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
            }
        } catch (Exception e) {
        }

        Font font = workbook.createFont();
        font.setColor(hssfColor.getIndex());
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(111);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
        workbook.write(new File("G:\\IdeaProjects\\office-template\\src\\test\\java\\11.xlsx"));
    }
}
