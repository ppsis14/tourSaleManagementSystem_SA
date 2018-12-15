package createReport;

import Table.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import tourSaleManagementSystemUtil.FormatConverter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;
import static tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil.*;

public class ReportPDF implements CreateReport {
    @Override
    public void createInvoice(String fileName, String titleInvoice ,String reservationCode) {
        //setup value

        ReservationPayment reservationPayment = manageableDatabase.getOneReservationPayment(reservationCode);
        Customer customer = manageableDatabase.getOneCustomer(reservationPayment.getCustomerID());
        TourPackage tourPackage = manageableDatabase.getOneTourPackage(reservationPayment.getTourID());
        Invoice invoiceDeposit = manageableDatabase.getOneInvoice(DEPOSIT_INVOICE,reservationCode);
        Invoice invoiceArrears = manageableDatabase.getOneInvoice(ARREARS_INVOICE,reservationCode);

        String reserveCode = reservationPayment.getReservationCode();
        String tourID = reservationPayment.getTourID();
        String customerName = reservationPayment.getCustomerName();
        String tel_or_fax = customer.getCell_phone();
        String customerID = reservationPayment.getCustomerID();
        String saleName = reservationPayment.getEmployeeName();
        String amountCustomer = String.valueOf(reservationPayment.getAmountCustomer());
        String price = String.format("%,.2f",Double.valueOf(tourPackage.getPrice()/2));
        String totalPrice = String.format("%,.2f",Double.valueOf(reservationPayment.getTotal_price()/2));
        String priceTH = "   Baht     :   ("+totalPrice+" บาทถ้วน)";
        String content = null;
        String dueDate = null;
        String invoiceNo = null;
        String currentDate = FormatConverter.getLocalDateFormat("dd-MM-yyyy");

        if(titleInvoice.equals("DEPOSIT INVOICE / ใบแจ้งหนี้-เงินมัดจำ")){
            content = "ยอดมัดจำค่าบริการ โปรแกรม "+tourPackage.getTourName()+" สำหรับ "+amountCustomer+" ท่าน";
            dueDate = tourPackage.getDepositDate();
            invoiceNo = invoiceDeposit.getInvoiceNo();
        }
        else if(titleInvoice.equals("INVOICE / ใบแจ้งหนี้")){
            dueDate = tourPackage.getArrearsDate();
            content = "ยอดชำระส่วนที่เหลือโปรแกรม "+tourPackage.getTourName()+" สำหรับ "+amountCustomer+" ท่าน";
            invoiceNo = invoiceArrears.getInvoiceNo();
        }

        Document document = new Document();
        document.setPageSize(PageSize.A4);

        try {
            //  create font
            Font angsanaNewFont18Bold = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.BOLD, BaseColor.BLACK);
            Font angsanaNewFont16 = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16, Font.NORMAL, BaseColor.BLACK);
            Font angsanaNewFont16Bold = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16, Font.BOLD, BaseColor.BLACK);
            Font angsanaNewFont14 = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 14, Font.NORMAL, BaseColor.BLACK);

            // file name pattern -->  invoice type(DI/AI)-reserve id-customer id ex. DI-21805-0003
            PdfWriter.getInstance(document, new FileOutputStream("./Invoice-report/"+fileName + ".pdf"));

            document.open();

            // create table
            PdfPTable compDetailTable = new PdfPTable(2);
            compDetailTable.setTotalWidth(500);
            compDetailTable.setLockedWidth(true);
            compDetailTable.setWidths(new float[]{20, 30});
            compDetailTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            compDetailTable.getDefaultCell().setBorderColor(new BaseColor(255,255,255));
            // the cell object
            PdfPCell compCell;
            // now we add a cell with rowSpan 2
            compCell = new PdfPCell(new Phrase("Cell with rowspan 2"));
            compCell.setRowspan(4);
            compCell.setPaddingLeft(10);
            compCell.setBorderColor(new BaseColor(255,255,255));
            Image image = Image.getInstance("./src/main/resources/images/logo2.png");
            compCell.addElement(image);
            compCell.setPaddingTop(9);
            compCell.setPaddingRight(8);
            compDetailTable.addCell(compCell);
            // we add the four remaining cells with addCell()
            compDetailTable.addCell(new Paragraph("ON VACATION TRAVELING CO., LTD.", angsanaNewFont16Bold));
            compDetailTable.addCell(new Paragraph("บริษัท ออนเวเคชั่น จำกัด", angsanaNewFont14));
            compDetailTable.addCell(new Paragraph("23/2 ชั้น 1 ถ.สุขุมวิท 23 (อโศก) แขวงคลองเตยเหนือ เขตวัฒนา กทม. 10110", angsanaNewFont14));
            compDetailTable.addCell(new Paragraph("TEL : (66)2 910-1043      FAX : (66)2 910-1044", angsanaNewFont14));
            document.add(compDetailTable);

            document.add(new Phrase("\n"));
            Paragraph titleName = new Paragraph(titleInvoice, angsanaNewFont16);
            titleName.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(titleName);
            document.add(new Phrase("\n"));

            // create table
            PdfPTable invoiceTable = new PdfPTable(2);
            invoiceTable.setWidthPercentage(40);
            invoiceTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
            invoiceTable.setWidths(new float[] {0.5f, 1.5f});
            invoiceTable.getDefaultCell().setPaddingTop(10);
            invoiceTable.addCell(createCell("No.", angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            invoiceTable.addCell(createCell(invoiceNo, angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            invoiceTable.addCell(createCell("Date", angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            invoiceTable.addCell(createCell(currentDate, angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            invoiceTable.addCell(createCell("Due Date", angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            invoiceTable.addCell(createCell(dueDate, angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            document.add(invoiceTable);

            PdfPTable detailTable = new PdfPTable(6);
            detailTable.setTotalWidth(500);
            detailTable.setLockedWidth(true);
            detailTable.setWidths(new float[]{3, 0.5f, 5, 2, 0.5f, 5});
            detailTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            detailTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            detailTable.getDefaultCell().setPaddingBottom(2);
            detailTable.addCell(createCell("Reservation ID", angsanaNewFont16, 0,1 , Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16, 0, 1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(reserveCode, angsanaNewFont16, 0, 1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("Tour ID", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16, 0, 1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(tourID, angsanaNewFont16, 0, 1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("Customer Name", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16,0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(customerName, angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("Tel./Fax", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(tel_or_fax, angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("Customer ID", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(customerID, angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("Salesman", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(saleName, angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            document.add(detailTable);
            document.add(new Phrase("\n"));

            PdfPTable descriptionTable = new PdfPTable(5);
            descriptionTable.setTotalWidth(500);
            descriptionTable.setLockedWidth(true);
            descriptionTable.setWidths(new float[]{2, 9, 3, 4, 4});
            descriptionTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            descriptionTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            descriptionTable.addCell(createCell("Item (s)\n ลำดับที่", angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell("Description (s)\nรายการสินค้า", angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell("Quantity\nจำนวน", angsanaNewFont16, 0.5f, 1 , Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell("Unit Price\nราคาต่อหน่วย", angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell("Amount (Baht)\nจำนวนเงิน (บาท)",angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell("1", angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCellFixHeight(content, angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT, 200));
            descriptionTable.addCell(createCell(amountCustomer, angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell(price, angsanaNewFont16, 0.5f, 1, Element.ALIGN_RIGHT));
            descriptionTable.addCell(createCell(totalPrice, angsanaNewFont16, 0.5f, 1, Element.ALIGN_RIGHT));
            descriptionTable.addCell(createCell(priceTH, angsanaNewFont16, 0.5f, 3, Element.ALIGN_LEFT));
            descriptionTable.addCell(createCell("   Total", angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            descriptionTable.addCell(createCell(totalPrice, angsanaNewFont16, 0.5f, 1, Element.ALIGN_RIGHT));
            document.add(descriptionTable);

            PdfPTable remarkTable = new PdfPTable(3);
            remarkTable.setTotalWidth(500);
            remarkTable.setLockedWidth(true);
            remarkTable.setWidths(new float[]{2, 1, 20});
            remarkTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            remarkTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            remarkTable.addCell(createCell("Remark", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            remarkTable.addCell(createCell(":", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            remarkTable.addCell(createCell("ราคาข้างต้นไม่รวมค่าภาษีมูลค่าเพิ่ม 7%", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            remarkTable.addCell(createCell(" ", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            remarkTable.addCell(createCell(" ", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            remarkTable.addCell(createCell("กรุณาโอนเงิน ชื่อบัญชี บริษัท อนนเวเคชั่น จำกัด\nธนาคารกสิกรไทย สาขาสุขุมวิท 23 บัญชีกระแสรายวัน เลขที่ xxx-x-xxxxx-x\nหลังการโอนกรูณาแฟกซ์ใบนำฝาก (PAY IN) มาที่เบอร์ 02-910-1998", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            document.add(remarkTable);
            document.add(new Phrase(" "));

            // create table for signatures
            PdfPTable signatureTable = new PdfPTable(3);
            signatureTable.setTotalWidth(500);
            signatureTable.setLockedWidth(true);
            signatureTable.setWidths(new float[]{20, 20, 20});
            signatureTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            signatureTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            signatureTable.addCell(createCell(saleName, angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            signatureTable.addCell(createCell("...............................", angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            signatureTable.addCell(createCell("...............................", angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            signatureTable.addCell(createCell("Sales By", angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            signatureTable.addCell(createCell("Account By", angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            signatureTable.addCell(createCell("Approved By\n(For Customer)", angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            document.add(signatureTable);

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

    @Override
    public void createReceipt(String fileName, String titleReceipt ,String reservationCode) {

        //setup value

        ReservationPayment reservationPayment = manageableDatabase.getOneReservationPayment(reservationCode);
        Customer customer = manageableDatabase.getOneCustomer(reservationPayment.getCustomerID());
        TourPackage tourPackage = manageableDatabase.getOneTourPackage(reservationPayment.getTourID());
        Receipt receiptDeposit = manageableDatabase.getOneReceipt(DEPOSIT_RECEIPT,reservationCode);
        Receipt receiptArrears = manageableDatabase.getOneReceipt(ARREARS_RECEIPT,reservationCode);

        String reserveCode = reservationPayment.getReservationCode();
        String tourID = reservationPayment.getTourID();
        String customerName = reservationPayment.getCustomerName();
        String tel_or_fax = customer.getCell_phone();
        String customerID = reservationPayment.getCustomerID();
        String saleName = reservationPayment.getEmployeeName();
        String amountCustomer = String.valueOf(reservationPayment.getAmountCustomer());
        String price = String.format("%,.2f",Double.valueOf(tourPackage.getPrice()/2));
        String totalPrice = String.format("%,.2f",Double.valueOf(reservationPayment.getTotal_price()/2));
        String priceTH = "   Baht     :   ("+totalPrice+" บาทถ้วน)";
        String content = null;
        String dueDate = null;
        String status = null;
        String receiptNo = null;
        String currentDate = FormatConverter.getLocalDateFormat("dd-MM-yyyy");

        if(titleReceipt.equals("DEPOSIT RECEIPT / ใบเสร็จรับเงิน")){
            content = "ยอดมัดจำค่าบริการ โปรแกรม "+tourPackage.getTourName()+" สำหรับ "+amountCustomer+" ท่าน";
            dueDate = tourPackage.getDepositDate();
            //status = "Deposit Payment Date";
            receiptNo = receiptDeposit.getReceiptNo();
        }
        else if(titleReceipt.equals("RECEIPT / ใบสำคัญรับเงิน")){
            dueDate = tourPackage.getArrearsDate();
            content = "ยอดชำระส่วนที่เหลือโปรแกรม "+tourPackage.getTourName()+" สำหรับ "+amountCustomer+" ท่าน";
            //status = "Payment Date";
            receiptNo = receiptArrears.getReceiptNo();
        }


        Document document = new Document();
        document.setPageSize(PageSize.A4);
        try {
            //  create font
            Font angsanaNewFont18Bold = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18, Font.BOLD, BaseColor.BLACK);
            Font angsanaNewFont16 = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16, Font.NORMAL, BaseColor.BLACK);
            Font angsanaNewFont16Bold = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16, Font.BOLD, BaseColor.BLACK);
            Font angsanaNewFont14 = new Font(BaseFont.createFont("fonts/Angsa.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 14, Font.NORMAL, BaseColor.BLACK);

            // file name pattern -->  invoice type(DI/AI)-reserve id-customer id ex. DI-21805-0003
            PdfWriter.getInstance(document, new FileOutputStream("./Receipt-report/"+fileName + ".pdf"));

            document.open();

            // create table
            PdfPTable compDetailTable = new PdfPTable(2);
            compDetailTable.setTotalWidth(500);
            compDetailTable.setLockedWidth(true);
            compDetailTable.setWidths(new float[]{20, 30});
            compDetailTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            compDetailTable.getDefaultCell().setBorderColor(new BaseColor(255,255,255));
            // the cell object
            PdfPCell compCell;
            // now we add a cell with rowSpan 2
            compCell = new PdfPCell(new Phrase("Cell with rowspan 2"));
            compCell.setRowspan(4);
            compCell.setPaddingLeft(10);
            compCell.setBorderColor(new BaseColor(255,255,255));
            Image image = Image.getInstance("./src/main/resources/images/logo2.png");
            compCell.addElement(image);
            compCell.setPaddingTop(9);
            compCell.setPaddingRight(8);
            compDetailTable.addCell(compCell);
            // we add the four remaining cells with addCell()
            compDetailTable.addCell(new Paragraph("ON VACATION TRAVELING CO., LTD.", angsanaNewFont16Bold));
            compDetailTable.addCell(new Paragraph("บริษัท ออนเวเคชั่น จำกัด", angsanaNewFont14));
            compDetailTable.addCell(new Paragraph("23/2 ชั้น 1 ถ.สุขุมวิท 23 (อโศก) แขวงคลองเตยเหนือ เขตวัฒนา กทม. 10110", angsanaNewFont14));
            compDetailTable.addCell(new Paragraph("TEL : (66)2 910-1043      FAX : (66)2 910-1044", angsanaNewFont14));
            document.add(compDetailTable);

            document.add(new Phrase("\n"));
            Paragraph titleName = new Paragraph(titleReceipt, angsanaNewFont16);
            titleName.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(titleName);
            document.add(new Phrase("\n"));

            // create table
            PdfPTable invoiceTable = new PdfPTable(2);
            invoiceTable.setWidthPercentage(40);
            invoiceTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
            invoiceTable.setWidths(new float[] {0.5f, 1.5f});
            invoiceTable.getDefaultCell().setPaddingTop(10);
            invoiceTable.addCell(createCell("No.", angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            invoiceTable.addCell(createCell(receiptNo, angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            invoiceTable.addCell(createCell("Date", angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            invoiceTable.addCell(createCell(currentDate, angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            document.add(invoiceTable);

            PdfPTable detailTable = new PdfPTable(6);
            detailTable.setTotalWidth(500);
            detailTable.setLockedWidth(true);
            detailTable.setWidths(new float[]{3, 0.5f, 5, 2, 0.5f, 5});
            detailTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            detailTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            detailTable.getDefaultCell().setPaddingBottom(2);
            detailTable.addCell(createCell("Reservation ID", angsanaNewFont16, 0,1 , Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16, 0, 1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(reserveCode, angsanaNewFont16, 0, 1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("Tour ID", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16, 0, 1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(tourID, angsanaNewFont16, 0, 1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("Customer Name", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16,0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(customerName, angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("Tel./Fax", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(tel_or_fax, angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("Customer ID", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(customerID, angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell("Salesman", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(":", angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            detailTable.addCell(createCell(saleName, angsanaNewFont16, 0 ,1, Element.ALIGN_LEFT));
            document.add(detailTable);
            document.add(new Phrase("\n"));

            PdfPTable descriptionTable = new PdfPTable(5);
            descriptionTable.setTotalWidth(500);
            descriptionTable.setLockedWidth(true);
            descriptionTable.setWidths(new float[]{2, 9, 3, 4, 4});
            descriptionTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            descriptionTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            descriptionTable.addCell(createCell("Item (s)\n ลำดับที่", angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell("Description (s)\nรายการสินค้า", angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell("Quantity\nจำนวน", angsanaNewFont16, 0.5f, 1 , Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell("Unit Price\nราคาต่อหน่วย", angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell("Amount (Baht)\nจำนวนเงิน (บาท)",angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell("1" , angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCellFixHeight(content, angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT, 200));
            descriptionTable.addCell(createCell(amountCustomer, angsanaNewFont16, 0.5f, 1, Element.ALIGN_CENTER));
            descriptionTable.addCell(createCell(price, angsanaNewFont16, 0.5f, 1, Element.ALIGN_RIGHT));
            descriptionTable.addCell(createCell(totalPrice, angsanaNewFont16, 0.5f, 1, Element.ALIGN_RIGHT));
            descriptionTable.addCell(createCell(priceTH, angsanaNewFont16, 0.5f, 3, Element.ALIGN_LEFT));
            descriptionTable.addCell(createCell("   Total", angsanaNewFont16, 0.5f, 1, Element.ALIGN_LEFT));
            descriptionTable.addCell(createCell(totalPrice, angsanaNewFont16, 0.5f, 1, Element.ALIGN_RIGHT));
            document.add(descriptionTable);

            PdfPTable remarkTable = new PdfPTable(3);
            remarkTable.setTotalWidth(500);
            remarkTable.setLockedWidth(true);
            remarkTable.setWidths(new float[]{2, 1, 20});
            remarkTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            remarkTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            remarkTable.addCell(createCell("Remark", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            remarkTable.addCell(createCell(":", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            remarkTable.addCell(createCell("ราคาข้างต้นไม่รวมค่าภาษีมูลค่าเพิ่ม 7%", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            remarkTable.addCell(createCell(" ", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            remarkTable.addCell(createCell(" ", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            remarkTable.addCell(createCell("กรุณาโอนเงิน ชื่อบัญชี บริษัท อนนเวเคชั่น จำกัด\nธนาคารกสิกรไทย สาขาสุขุมวิท 23 บัญชีกระแสรายวัน เลขที่ xxx-x-xxxxx-x\nหลังการโอนกรูณาแฟกซ์ใบนำฝาก (PAY IN) มาที่เบอร์ 02-910-1998", angsanaNewFont16, 0f, 1, Element.ALIGN_LEFT));
            document.add(remarkTable);
            document.add(new Phrase(" "));

            // create table for signatures
            PdfPTable signatureTable = new PdfPTable(3);
            signatureTable.setTotalWidth(500);
            signatureTable.setLockedWidth(true);
            signatureTable.setWidths(new float[]{20, 20, 20});
            signatureTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            signatureTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            signatureTable.addCell(createCell(saleName, angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            signatureTable.addCell(createCell("...............................", angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            signatureTable.addCell(createCell("...............................", angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            signatureTable.addCell(createCell("Sales By", angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            signatureTable.addCell(createCell("Account By", angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            signatureTable.addCell(createCell("Approved By\n(For Customer)", angsanaNewFont14, 0f, 1, Element.ALIGN_CENTER));
            document.add(signatureTable);

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

}
