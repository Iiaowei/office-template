package org.example.template.core.annotation;

/**
 * @author liaowei
 * @version V1.0
 * @date 2021/10/22 15:02:56
 */
public class TemplateDefinition {
    private String parameterCode;
    private String parameterName;
    private int fontColor;
    private int color;
    private int size;

    public TemplateDefinition(String parameterCode, String parameterName, int fontColor, int color, int size) {
        this.parameterCode = parameterCode;
        this.parameterName = parameterName;
        this.fontColor = fontColor;
        this.color = color;
        this.size = size;
    }

    public String getParameterCode() {
        return parameterCode;
    }

    public String getParameterName() {
        return parameterName;
    }

    public int getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    static class Builder {
        private String parameterCode;
        private String parameterName;
        private int fontColor;
        private int color;
        private int size;

        public Builder parameterCode(String parameterCode) {
            this.parameterCode = parameterCode;
            return this;
        }

        public Builder parameterName(String parameterName) {
            this.parameterName = parameterName;
            return this;
        }

        public Builder fontColor(int color) {
            this.fontColor = fontColor;
            return this;
        }

        public Builder color(int color) {
            this.color = color;
            return this;
        }

        public Builder size(int size) {
            this.size = size;
            return this;
        }

        public TemplateDefinition build() {
            return new TemplateDefinition(parameterCode,
                    parameterName,
                    fontColor,
                    color,
                    size);
        }
    }
}
