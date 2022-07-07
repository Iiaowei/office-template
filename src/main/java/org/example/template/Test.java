package org.example.template;

import org.apache.poi.xssf.usermodel.*;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlString;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.w3c.dom.Node;

import java.io.IOException;
import java.util.List;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/7/1 15:01:44
 */
public class Test {
    public static void main(String[] args) throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook("D:\\IdeaProjects\\office-template\\src\\main\\resources\\中行受托-产品要素表.xlsx");
        XSSFVMLDrawing vmlDrawing = xssfWorkbook.getSheet("长江养老-债权投资计划").getVMLDrawing(true);
        XSSFDrawing drawing = xssfWorkbook.getSheet("长江养老-债权投资计划").getDrawingPatriarch();

        List<XSSFShape> shapes = drawing.getShapes();

        for (XSSFShape shape : shapes) {
            if (shape instanceof XSSFPicture) {
                XSSFPicture picture = (XSSFPicture) shape;
                System.out.println(picture);

                String shapeName = picture.getShapeName();
                CTPicture ctPicture = picture.getCTPicture();

                XmlBoolean xmlBoolean = ctPicture.xgetFPublished();
                XmlString xmlString = ctPicture.xgetMacro();
                CTBlipFillProperties blipFill = ctPicture.getBlipFill();
                CTShapeProperties spPr = ctPicture.getSpPr();
                boolean fPublished = ctPicture.getFPublished();
                String macro = ctPicture.getMacro();

                Node domNode = ctPicture.getDomNode();
                String nodeName = domNode.getNodeName();
                String textContent = domNode.getTextContent();
                System.out.println(shapeName);
            }

        }
        System.out.println(drawing);
    }
}
