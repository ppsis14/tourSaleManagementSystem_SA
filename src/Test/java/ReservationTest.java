import Table.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationTest {
    Reservation japanTourReservation;
    Reservation chinaTourReservation;
    @BeforeEach
    void setUp() {
        //public Reservation(String reservationCode, String tourID, String tourName, String customerID, String customerName, String employeeID, String employeeName)
        japanTourReservation = new Reservation("JPN-3D2N-000001-000001","JPN-3D2N-000001","JAPAN EXCLUSIVE TRIP",
                "CS000002","Issarapong Kanakhet","EMP000001","Thikamporn Simud");
        chinaTourReservation = new Reservation("CHN-5D4N-000001-000001","CHN-5D4N-000001","CHINA EXCLUSIVE TRIP",
                "CS000003","Thanakit Simsamran","EMP000002","Piyawad Namtachan");
    }

    @Test
    public void testGetterCustomerInformation(){
        assertEquals("Issarapong Kanakhet",japanTourReservation.getCustomerName());
        assertEquals("Thanakit Simsamran",chinaTourReservation.getCustomerName());
        assertEquals("CS000002",japanTourReservation.getCustomerID());
        assertEquals("CS000003",chinaTourReservation.getCustomerID());
        //set the new id and name for customer in the Japan Tour reservation.
        japanTourReservation.setCustomerID("CS000004");
        assertEquals("CS000004",japanTourReservation.getCustomerID());
        japanTourReservation.setCustomerName("Mali Glinsorn");
        assertEquals("Mali Glinsorn",japanTourReservation.getCustomerName());
    }

    @Test
    public void testGetterTourPackageInformation(){
        assertEquals("JPN-3D2N-000001",japanTourReservation.getTourID());
        assertEquals("CHN-5D4N-000001",chinaTourReservation.getTourID());
        assertEquals("JAPAN EXCLUSIVE TRIP",japanTourReservation.getTourName());
        assertEquals("CHINA EXCLUSIVE TRIP",chinaTourReservation.getTourName());
    }

    @Test
    public void testGetterReservationCode(){
        assertEquals("JPN-3D2N-000001-000001",japanTourReservation.getReservationCode());
        assertEquals("CHN-5D4N-000001-000001",chinaTourReservation.getReservationCode());
    }

    @Test
    public void testEmployeeSale(){
        assertEquals("Piyawad Namtachan",chinaTourReservation.getEmployeeName());
        assertEquals("Thikamporn Simud",japanTourReservation.getEmployeeName());
        assertEquals("EMP000002",chinaTourReservation.getEmployeeID());
        assertEquals("EMP000001",japanTourReservation.getEmployeeID());
        //set new value of the sale employee.
        japanTourReservation.setEmployeeName("Wipawadee Monkut");
        japanTourReservation.setEmployeeID("EMP0000004");
        assertEquals("Wipawadee Monkut",japanTourReservation.getEmployeeName());
        assertEquals("EMP0000004",japanTourReservation.getEmployeeID());
    }

}