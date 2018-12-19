import Table.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {
    Invoice invoice;

    @BeforeEach
    public void setUp(){
        //public Invoice(String reservationCode, String invoiceNo, String tourID, String tourName, String customerID,
        // String customerName, String employeeID, String employeeName, int amountCustomer, int totalPrice, String invoiceStatus) {
        //
        invoice = new Invoice("JPN-4D3N-000001-000001","JPN-4D3N-000001-000001-000001","JPN-4D3N-000001","Japan Exclusive Holidays",
                "CS000008","Bussaba Tarue","EMP000004","Wipawadee Monkut",
                100,35000,"DEPOSIT_INVOICE","Not created");
    }

    @Test
    public void testGetTourInformation(){
        assertEquals("JPN-4D3N-000001",invoice.getTourID());
        assertEquals("Japan Exclusive Holidays",invoice.getTourName());
        assertEquals("JPN-4D3N-000001-000001",invoice.getReservationCode());
        assertEquals("JPN-4D3N-000001-000001-000001",invoice.getInvoiceNo());
    }

    @Test
    public void testCustomerInformationOnInvoice(){
        assertEquals("Bussaba Tarue",invoice.getCustomerName());
        assertEquals("CS000008",invoice.getCustomerID());
    }

    @Test
    public void testGetEmployeeInformationOnInvoice(){
        assertEquals("Wipawadee Monkut", invoice.getEmployeeName());
        assertEquals("EMP000004",invoice.getEmployeeID());
    }

    @Test
    public void testPriceAndAmountOfCustomer(){
        assertEquals(35000,invoice.getTotalPrice());
        assertEquals(100,invoice.getAmountCustomer());
        assertEquals("Not created", invoice.getCreateStatus());
    }

}