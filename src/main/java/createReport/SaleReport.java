package createReport;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import tourSaleManagementSystemUtil.PdfUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaleReport implements ReportCreator {
    @Override
    public void createReport() {
        Document document = new Document();
        document.setPageSize(PageSize.A4.rotate());
        try {
            Font angsanaNewFont18Bold = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.BOLD, BaseColor.BLACK);
            Font angsanaNewFont18 = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.NORMAL, BaseColor.BLACK);
            Font angsanaNewFont16 = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16, Font.NORMAL, BaseColor.BLACK);
            Font angsanaNewFont16Bold = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16, Font.BOLD, BaseColor.BLACK);
            Font angsanaNewFont14 = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 14, Font.NORMAL, BaseColor.BLACK);

            PdfWriter.getInstance(document, new FileOutputStream("./Sale-Report.pdf"));
            document.open();
            PdfPTable compDetailTable = new PdfPTable(2);
            Paragraph titleName = new Paragraph("รายงานแสดงยอดขายของแพ็คเกจทัวร์ JAPAN NEW YEAR EXCLUSIVE (JPN-4D3N-000004)", angsanaNewFont18);
            titleName.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(titleName);
            PdfPTable tableOut = new PdfPTable(2);
            tableOut.setWidthPercentage(90);
            float[] columnOutWidth={200f, 200f};
            tableOut.setWidths(columnOutWidth);
            tableOut.setSpacingBefore(10f);
            tableOut.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            buildNestedTablesHeader(tableOut, angsanaNewFont14);
            document.add(tableOut);

            PdfPTable tableCus = new PdfPTable(1);
            tableCus.setWidthPercentage(100);
            tableCus.setSpacingBefore(10f);
            tableCus.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            buildNestedTablesBody(tableCus, angsanaNewFont14);
            document.add(tableCus);


        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            document.close();
        }
    }

    private static void buildNestedTablesHeader(PdfPTable outerTable, Font font) throws IOException, DocumentException {
        PdfPTable printByTable = new PdfPTable(1);
        printByTable.setTotalWidth(150f);
        printByTable.setLockedWidth(true);
        printByTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        printByTable.addCell(PdfUtil.createCell("ผู้พิมพ์รายงาน  :  ฑิฆัมพร สิมอุด", font, 0f, 1, Element.ALIGN_LEFT));
        outerTable.addCell(printByTable);
        PdfPTable printDateTable = new PdfPTable(2);
        printDateTable.setTotalWidth(200f);
        printDateTable.setLockedWidth(true);
        printDateTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
        printDateTable.addCell(PdfUtil.createCell("พิมพ์วันที่  :  22/12/2018", font, 0f, 1, Element.ALIGN_RIGHT));
        printDateTable.addCell(PdfUtil.createCell("เวลา  :  08.45 น.", font, 0f, 1, Element.ALIGN_RIGHT));
        outerTable.addCell(printDateTable);
    }

    private static void buildNestedTablesBody(PdfPTable outerTable, Font font) throws IOException, DocumentException {
        double totalExpectedAmount = 0;
        double totalReceivedAmount = 0;
        int totalQuantity = 0;
        PdfPTable bodyTable = new PdfPTable(6);
        bodyTable.setTotalWidth(new float[]{130f, 200f, 50f, 105f, 85f, 150f});
        bodyTable.setLockedWidth(true);
        bodyTable.setHorizontalAlignment(Element.ALIGN_CENTER);
        bodyTable.addCell(PdfUtil.createCell("Reservation Code\nรหัสการจอง", font, 0.5f, 1, Element.ALIGN_CENTER));
        bodyTable.addCell(PdfUtil.createCell("Customer Name\nชื่อลูกค้า", font, 0.5f, 1, Element.ALIGN_CENTER));
        bodyTable.addCell(PdfUtil.createCell("Quantity\nจำนวน", font, 0.5f, 1, Element.ALIGN_CENTER));
        bodyTable.addCell(PdfUtil.createCell("Expected Amount\nยอดเงินที่คาดว่าจะได้รับ", font, 0.5f, 1, Element.ALIGN_CENTER));
        bodyTable.addCell(PdfUtil.createCell("Received Amount\nยอดเงินที่ได้รับ", font, 0.5f, 1, Element.ALIGN_CENTER));
        bodyTable.addCell(PdfUtil.createCell("Sale Name\nชื่อพนักงานขาย", font, 0.5f, 1, Element.ALIGN_CENTER));
        for (int i = 0; i < 30; i++ ){

            bodyTable.addCell(PdfUtil.createCell("GER-5D4N-000001-000001", font, 0.5f, 1, Element.ALIGN_CENTER));
            bodyTable.addCell(PdfUtil.createCell("นางสาวฑิฆัมพร สิมอุด", font, 0.5f, 1, Element.ALIGN_LEFT));
            bodyTable.addCell(PdfUtil.createCell(String.valueOf(++i), font, 0.5f, 1, Element.ALIGN_CENTER));
            totalQuantity += i;
            bodyTable.addCell(PdfUtil.createCell(String.valueOf(12000.0*i), font, 0.5f, 1, Element.ALIGN_RIGHT));
            bodyTable.addCell(PdfUtil.createCell(String.valueOf(12000.0*i), font, 0.5f, 1, Element.ALIGN_RIGHT));
            bodyTable.addCell(PdfUtil.createCell("วิภาวดี ม่อนคุต", font, 0.5f, 1, Element.ALIGN_LEFT));
            totalReceivedAmount += (12000.0*i);
        }
        bodyTable.addCell(PdfUtil.createCell("รวม", font, 0.5f, 2, Element.ALIGN_CENTER));
        bodyTable.addCell(PdfUtil.createCell(String.valueOf(totalQuantity), font, 0.5f, 1, Element.ALIGN_CENTER));
        bodyTable.addCell(PdfUtil.createCell(String.valueOf(12000.0*totalQuantity), font, 0.5f, 1, Element.ALIGN_RIGHT));
        bodyTable.addCell(PdfUtil.createCell(String.valueOf(totalReceivedAmount), font, 0.5f, 1, Element.ALIGN_RIGHT));
        bodyTable.addCell(PdfUtil.createCell("บาท", font, 0.5f, 1, Element.ALIGN_LEFT));
        outerTable.addCell(bodyTable);

    }
}
