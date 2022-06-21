package org.example.template.builder;

import org.apache.poi.ss.usermodel.Font;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/6/21 10:08:44
 */
public class FontBuilder {
    private final CellStyleBuilder cellStyleBuilder;
    private final Font font;

    FontBuilder(CellStyleBuilder cellStyleBuilder, Font font) {
        this.cellStyleBuilder = cellStyleBuilder;
        this.font = font;
    }

    public FontBuilder name(String name) {
        font.setFontName(name);
        return this;
    }

    public FontBuilder fontHeight(short height) {
        font.setFontHeight(height);
        return this;
    }

    public FontBuilder fontHeightInPoints(short height) {
        font.setFontHeightInPoints(height);
        return this;
    }

    public FontBuilder color(short color) {
        font.setColor(color);
        return this;
    }

    public FontBuilder charSet(byte charset) {
        font.setCharSet(charset);
        return this;
    }

    public FontBuilder charSet(int charset) {
        font.setCharSet(charset);
        return this;
    }

    public FontBuilder bold(Boolean bold) {
        font.setBold(bold);
        return this;
    }

    public FontBuilder italic(Boolean italic) {
        font.setItalic(italic);
        return this;
    }

    public FontBuilder strikeout(Boolean strikeout) {
        font.setStrikeout(strikeout);
        return this;
    }

    public FontBuilder typeOffset(short offset) {
        font.setTypeOffset(offset);
        return this;
    }

    public FontBuilder underline(byte underline) {
        font.setUnderline(underline);
        return this;
    }

    public Font end() {
        return font;
    }
}
