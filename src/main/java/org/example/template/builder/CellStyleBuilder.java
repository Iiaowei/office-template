package org.example.template.builder;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.util.function.Function;

import static org.apache.poi.ss.usermodel.BorderStyle.THIN;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/21 9:52:30
 */
public class CellStyleBuilder {
    private final SXSSFWorkbook workbook;
    private  XSSFCellStyle cellStyle;


    private CellStyleBuilder(SXSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public CellStyleBuilder horizontalAlignment(HorizontalAlignment alignment) {
        this.cellStyle.setAlignment(alignment);
        return this;
    }

    public CellStyleBuilder border(Boolean border) {
        if (border) {
            this.cellStyle.setBorderBottom(THIN);
            this.cellStyle.setBorderLeft(THIN);
            this.cellStyle.setBorderRight(THIN);
            this.cellStyle.setBorderTop(THIN);
        }
        return this;
    }

    public CellStyleBuilder borderColor(short borderColor) {
        this.cellStyle.setBottomBorderColor(borderColor);
        this.cellStyle.setLeftBorderColor(borderColor);
        this.cellStyle.setRightBorderColor(borderColor);
        this.cellStyle.setTopBorderColor(borderColor);
        return this;
    }

    public CellStyleBuilder dataFormat(short dataFormat) {
        this.cellStyle.setDataFormat(dataFormat);
        return this;
    }

    public CellStyleBuilder fillBackgroundColor(short bgColor) {
        this.cellStyle.setFillBackgroundColor(bgColor);
        return this;
    }

    public CellStyleBuilder fillForegroundColor(short fgColor) {
        this.cellStyle.setFillForegroundColor(fgColor);
        return this;
    }

    public CellStyleBuilder fillPattern(FillPatternType fillPatternType) {
        this.cellStyle.setFillPattern(fillPatternType);
        return this;
    }

    public CellStyleBuilder hidden(Boolean hidden) {
        this.cellStyle.setHidden(hidden);
        return this;
    }

    public CellStyleBuilder indent(short indent) {
        this.cellStyle.setIndention(indent);
        return this;
    }

    public CellStyleBuilder locked(Boolean locked) {
        this.cellStyle.setLocked(locked);
        return this;
    }

    public CellStyleBuilder quotePrefixed(Boolean quotePrefix) {
        this.cellStyle.setQuotePrefixed(quotePrefix);
        return this;
    }

    public CellStyleBuilder rotation(short rotation) {
        this.cellStyle.setRotation(rotation);
        return this;
    }

    public CellStyleBuilder rotation(VerticalAlignment verticalAlignment) {
        this.cellStyle.setVerticalAlignment(verticalAlignment);
        return this;
    }

    public CellStyleBuilder wrapText(Boolean wrapText) {
        this.cellStyle.setWrapText(wrapText);
        return this;
    }

    private CellStyle getCellStyle() {
        return cellStyle;
    }

    public CellStyleBuilder newFont(Function<FontBuilder, Font> function) {
        Font font = workbook.createFont();

        font = function.apply(new FontBuilder(this, font));
        this.cellStyle.setFont(font);
        return this;
    }

    public XSSFCellStyle build() {
        return this.cellStyle;
    }


    public static CellStyleBuilder builder() {
        return new CellStyleBuilder(null);
    }
}
