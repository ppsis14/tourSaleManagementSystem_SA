package createReport;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import tourSaleManagementController.TourReportController;
import tourSaleManagementSystemUtil.FormatConverter;
import tourSaleManagementSystemUtil.PdfUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.loginEmployee;
import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;

public class CustomerListReport{
    private static List<TourReportController.ReservationCustomerReport> customerReports;

    public void createCustomerListReport(String tourName, List<TourReportController.ReservationCustomerReport> customerReportList) {

        //setup data
        customerReports = customerReportList;
        String tourID = manageableDatabase.getTourID(tourName);
        String saleName = loginEmployee.getTitleName()+ " " + loginEmployee.getFirstName()+" "+loginEmployee.getLastName();


        Document document = new Document();
        document.setPageSize(PageSize.A4.rotate());
        try {
            Font angsanaNewFont18Bold = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.BOLD, BaseColor.BLACK);
            Font angsanaNewFont18 = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.NORMAL, BaseColor.BLACK);
            Font angsanaNewFont16 = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16, Font.NORMAL, BaseColor.BLACK);
            Font angsanaNewFont16Bold = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16, Font.BOLD, BaseColor.BLACK);
            Font angsanaNewFont14 = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 14, Font.NORMAL, BaseColor.BLACK);

            PdfWriter.getInstance(document, new FileOutputStream("./Tour-Report/Customer-List-Report.pdf"));
            document.open();
            PdfPTable compDetailTable = new PdfPTable(2);
            Paragraph titleName = new Paragraph("รายงานแสดงรายชื่อลูกทัวร์ของแพ็คเกจทัวร์ "+tourName+ " ( "+tourID+" )", angsanaNewFont18);
            titleName.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(titleName);
            PdfPTable tableOut = new PdfPTable(2);
            tableOut.setWidthPercentage(80);
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

        String saleName = loginEmployee.getFirstName()+" "+loginEmployee.getLastName();
        String dueDate = FormatConverter.getLocalDateFormat("dd-MM-yyyy");

        PdfPTable printByTable = new PdfPTable(1);
        printByTable.setTotalWidth(150f);
        printByTable.setLockedWidth(true);
        printByTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        printByTable.addCell(PdfUtil.createCell("ผู้พิมพ์รายงาน  :  " + saleName, font, 0f, 1, Element.ALIGN_LEFT));
        outerTable.addCell(printByTable);
        PdfPTable printDateTable = new PdfPTable(2);
        printDateTable.setTotalWidth(200f);
        printDateTable.setLockedWidth(true);
        printDateTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
        printDateTable.addCell(PdfUtil.createCell("พิมพ์วันที่  :  "+ dueDate, font, 0f, 1, Element.ALIGN_RIGHT));
        printDateTable.addCell(PdfUtil.createCell("เวลา  :  "+FormatConverter.getLocalTimeFormat(), font, 0f, 1, Element.ALIGN_RIGHT));
        outerTable.addCell(printDateTable);
    }

    private static void buildNestedTablesBody(PdfPTable outerTable, Font font) throws IOException, DocumentException {

        PdfPTable bodyTable = new PdfPTable(5);
        bodyTable.setTotalWidth(new float[]{50f,110f,250f,50f,150f});
        bodyTable.setLockedWidth(true);
        bodyTable.setHorizontalAlignment(Element.ALIGN_CENTER);
        bodyTable.addCell(PdfUtil.createCell("No.\nลำดับ", font, 0.5f, 1, Element.ALIGN_CENTER));
        bodyTable.addCell(PdfUtil.createCell("Customer ID\nรหัสลูกค้า", font, 0.5f, 1, Element.ALIGN_CENTER));
        bodyTable.addCell(PdfUtil.createCell("Name\nชื่อ", font, 0.5f, 1, Element.ALIGN_CENTER));
        bodyTable.addCell(PdfUtil.createCell("Age\nอายุ", font, 0.5f, 1, Element.ALIGN_CENTER));
        bodyTable.addCell(PdfUtil.createCell("Phone Number\nเบอร์โทรศัพท์", font, 0.5f, 1, Element.ALIGN_CENTER));
        for (int i = 0; i < customerReports.size() ; i++ ){
            bodyTable.addCell(PdfUtil.createCell(String.valueOf(customerReports.get(i).getNumber()), font, 0.5f, 1, Element.ALIGN_CENTER));
            bodyTable.addCell(PdfUtil.createCell(customerReports.get(i).getCustomerID(), font, 0.5f, 1, Element.ALIGN_CENTER));
            bodyTable.addCell(PdfUtil.createCell(customerReports.get(i).getCustomerName(), font, 0.5f, 1, Element.ALIGN_LEFT));
            bodyTable.addCell(PdfUtil.createCell(String.valueOf(customerReports.get(i).getAge()), font, 0.5f, 1, Element.ALIGN_CENTER));
            bodyTable.addCell(PdfUtil.createCell(customerReports.get(i).getPhoneNum(), font, 0.5f, 1, Element.ALIGN_CENTER));
        }
        outerTable.addCell(bodyTable);
    }
}
