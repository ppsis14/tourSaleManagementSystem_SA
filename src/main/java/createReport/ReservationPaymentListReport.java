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

public class ReservationPaymentListReport {
    private static List<TourReportController.ReservationPaymentReport> reservationPaymentReports;

    public void createReservationPaymentReport(String tourName, List<TourReportController.ReservationPaymentReport> reservationPaymentReportList) {

        reservationPaymentReports = reservationPaymentReportList;
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

            PdfWriter.getInstance(document, new FileOutputStream("./Tour-Report/Reservation-Payment-Report.pdf"));
            document.open();
            PdfPTable compDetailTable = new PdfPTable(2);
            Paragraph titleName = new Paragraph("รายงานแสดงสถานะการชำระเงินลูกค้าของแพ็คเกจทัวร์ "+tourName+ " ( "+tourID+" )", angsanaNewFont18);
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
        String currentTime = FormatConverter.getLocalTimeFormat();

        PdfPTable printByTable = new PdfPTable(1);
        printByTable.setTotalWidth(200f);
        printByTable.setLockedWidth(true);
        printByTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        printByTable.addCell(PdfUtil.createCell("ผู้พิมพ์รายงาน/Printed By  :  "+saleName, font, 0f, 1, Element.ALIGN_LEFT));
        outerTable.addCell(printByTable);
        PdfPTable printDateTable = new PdfPTable(2);
        printDateTable.setTotalWidth(300f);
        printDateTable.setLockedWidth(true);
        printDateTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
        printDateTable.addCell(PdfUtil.createCell("วันที่พิมพ์/Printed Date  :  "+dueDate , font, 0f, 1, Element.ALIGN_RIGHT));
        printDateTable.addCell(PdfUtil.createCell("เวลา/Time  :  "+currentTime , font, 0f, 1, Element.ALIGN_RIGHT));
        outerTable.addCell(printDateTable);
    }

    private static void buildNestedTablesBody(PdfPTable outerTable, Font font) throws IOException, DocumentException {
        PdfPTable bodyTable = new PdfPTable(6);
        bodyTable.setTotalWidth(new float[]{130f, 170f, 75f, 110f, 130f, 130f});
        bodyTable.setLockedWidth(true);
        bodyTable.setHorizontalAlignment(Element.ALIGN_CENTER);
        bodyTable.addCell(PdfUtil.createCell("Reservation Code\nรหัสการจอง", font, 0.5f, 1, Element.ALIGN_CENTER));
        bodyTable.addCell(PdfUtil.createCell("Customer Name\nชื่อลูกค้า", font, 0.5f, 1, Element.ALIGN_CENTER));
        bodyTable.addCell(PdfUtil.createCell("Phone No.\nเบอร์โทรศัพท์", font, 0.5f, 1, Element.ALIGN_CENTER));
        bodyTable.addCell(PdfUtil.createCell("Deposit Payment Status\nสถานะการชำระเงินค่ามัดจำ", font, 0.5f, 1, Element.ALIGN_CENTER));
        bodyTable.addCell(PdfUtil.createCell("Arrears Payment Status\nสถานะการชำระเงินส่วนที่เหลือ", font, 0.5f, 1, Element.ALIGN_CENTER));
        bodyTable.addCell(PdfUtil.createCell("Sale Name\nชื่อพนักงานขาย", font, 0.5f, 1, Element.ALIGN_CENTER));
        for (int i = 0; i < reservationPaymentReports.size(); i++ ){
            bodyTable.addCell(PdfUtil.createCell(reservationPaymentReports.get(i).getReservationCode(), font, 0.5f, 1, Element.ALIGN_CENTER));
            bodyTable.addCell(PdfUtil.createCell(reservationPaymentReports.get(i).getCustomerName(), font, 0.5f, 1, Element.ALIGN_LEFT));
            bodyTable.addCell(PdfUtil.createCell(reservationPaymentReports.get(i).getPhoneNum(), font, 0.5f, 1, Element.ALIGN_CENTER));
            bodyTable.addCell(PdfUtil.createCell(reservationPaymentReports.get(i).getDepositPaymentStatus(), font, 0.5f, 1, Element.ALIGN_CENTER));
            bodyTable.addCell(PdfUtil.createCell(reservationPaymentReports.get(i).getArrearsPaymentStatus(), font, 0.5f, 1, Element.ALIGN_CENTER));
            bodyTable.addCell(PdfUtil.createCell(reservationPaymentReports.get(i).getSaleName(), font, 0.5f, 1, Element.ALIGN_LEFT));

        }
        outerTable.addCell(bodyTable);

    }

}
