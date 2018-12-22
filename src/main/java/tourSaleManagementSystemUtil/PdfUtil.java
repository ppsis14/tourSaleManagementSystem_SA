package tourSaleManagementSystemUtil;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;

import java.io.IOException;

public class PdfUtil {

    public static PdfPCell createCell(String content, Font font, float borderWidth, int colSpan, int alignment) {
        PdfPCell cell = new PdfPCell(new Paragraph(content, font));
        cell.setBorderWidth(borderWidth);
        cell.setColspan(colSpan);
        cell.setPaddingBottom(5);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }

    public static PdfPCell createCellFixHeight(String content,Font font, float borderWidth, int colspan, int alignment, float height) {
        PdfPCell cell = new PdfPCell(new Paragraph(content, font));
        cell.setBorderWidth(borderWidth);
        cell.setColspan(colspan);
        cell.setPaddingBottom(5);
        cell.setPaddingLeft(5);
        cell.setFixedHeight(height);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }

    public static Font fontSetUp(String property, int size) throws IOException, DocumentException {
        if (property.equalsIgnoreCase("B") && size == 18) {
            Font angsanaNewFont18Bold = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.BOLD, BaseColor.BLACK);
            return angsanaNewFont18Bold;
        }
        else if (property.equalsIgnoreCase("N") && size == 16){
            Font angsanaNewFont16 = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16, Font.NORMAL, BaseColor.BLACK);
            return angsanaNewFont16;
        }
        else if (property.equalsIgnoreCase("B") && size == 16){
            Font angsanaNewFont16Bold = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16, Font.BOLD, BaseColor.BLACK);
            return angsanaNewFont16Bold;
        }
        else{
            Font angsanaNewFont14 = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 14, Font.NORMAL, BaseColor.BLACK);
            return angsanaNewFont14;
        }

    }
}
