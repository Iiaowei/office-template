package org.example.template.core.annotation;

import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.util.HSSFColor;

import java.awt.*;
import java.util.List;

/**
 * @author liaowei
 * @version V1.0
 * @date 2021/10/22 15:47:00
 */
public class ExcelTemplate {
    private Class<?>  templateClazz;
    private final TemplateDefinition head;
    private final TemplateDefinition data;
    private final List<TemplateDefinition> properties;

    public ExcelTemplate(Class<?> templateClazz, TemplateDefinition head, TemplateDefinition data, List<TemplateDefinition> properties) {
        this.templateClazz = templateClazz;
        this.head = head;
        this.data = data;
        this.properties = properties;
    }

    public Class<?> getTemplateClazz() {
        return templateClazz;
    }

    public TemplateDefinition getHead() {
        return head;
    }

    public TemplateDefinition getData() {
        return data;
    }

    public List<TemplateDefinition> getProperties() {
        return properties;
    }

    public short getFontColor(HSSFPalette palette) {
        Color color = new Color(data.getColor());
        return getColor(palette, HSSFColor.HSSFColorPredefined.BLACK, color);
    }

    public short getForegroundColor(HSSFPalette palette) {
        Color color = new Color(head.getColor());
        return getColor(palette, HSSFColor.HSSFColorPredefined.WHITE, color);
    }

    private short getColor(HSSFPalette palette, HSSFColor.HSSFColorPredefined predefined, Color color) {
        HSSFColor hssfColor = null;
        try {
            hssfColor= palette.findColor((byte)color.getRed(), (byte)color.getGreen(), (byte)color.getBlue());
            if (hssfColor == null ){
                palette.setColorAtIndex(predefined.getIndex(), (byte)color.getRed(), (byte)color.getGreen(), (byte)color.getBlue());
                hssfColor = palette.getColor(predefined.getIndex());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hssfColor.getIndex();
    }
}
