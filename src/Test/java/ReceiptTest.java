import Table.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {
    Receipt receipt;

    @BeforeEach
    public void setUp(){
        //constructor-->
        //public Receipt(String reservationCode, String receiptNo, String tourID, String tourName,
        // String customerID, String customerName, String employeeID, String employeeName, int amountCustomer,
        // int totalPrice, String receiptStatus)
        receipt = new Receipt("JPN-4D3N-000001-000001","JPN-4D3N-000001-000001-000001","JPN-4D3N-000001","Japan Exclusive Holidays",
                 "CS000008","Bussaba Tarue","EMP000004","Wipawadee Monkut",
                100,35000,"DEPOSIT_RECEIPT","Not created");
    }

    @Test
    public void testGetTourInformation(){
        assertEquals("JPN-4D3N-000001",receipt.getTourID());
        assertEquals("Japan Exclusive Holidays",receipt.getTourName());
        assertEquals("JPN-4D3N-000001-000001",receipt.getReservationCode());
        assertEquals("JPN-4D3N-000001-000001-000001",receipt.getReceiptNo());
    }

    @Test
    public void testCustomerInformationOnInvoice(){
        assertEquals("Bussaba Tarue",receipt.getCustomerName());
        assertEquals("CS000008",receipt.getCustomerID());
    }

    @Test
    public void testGetEmployeeInformationOnInvoice(){
        assertEquals("Wipawadee Monkut", receipt.getEmployeeName());
        assertEquals("EMP000004",receipt.getEmployeeID());
    }

    @Test
    public void testPriceAndAmountOfCustomer(){
        assertEquals(35000,receipt.getTotalPrice());
        assertEquals(100,receipt.getAmountCustomer());
        assertEquals("Not created", receipt.getCreateStatus());
    }
}