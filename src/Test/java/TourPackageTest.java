import Table.TourPackage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TourPackageTest {

    TourPackage japanTour;
    TourPackage indiaTour;
    TourPackage koreaTour;

    @BeforeEach
    void setUp() {
        //public TourPackage(String tourID, String tourName, int price,String departureDate,String returnDate,
        //String depositDate, String arrearsDate, int amount, int available, String status)
        japanTour = new TourPackage("JPN-4D3N-000001","JAPAN TOKYO WINTER TOUR",35000,"23-11-2018","27-11-2018","03-11-2018","02-11-2018",50,50,"open");
        indiaTour = new TourPackage("IND-5D4N-000002","INDIA GREAT EXPERIENCE TOUR",30000,"29-05-2018","03-06-2018","09-05-2018","19-05-2018",70,70,"open");
        koreaTour = new TourPackage("KOR-10D9N-00004","KOREA CHEJU ISLAND TOUR",50000,"20-07-2018","30-07-2018","31-06-2018","10-07-2018",100,100,"open");
    }

    @Test
    public void testGetName(){
        assertEquals("JAPAN TOKYO WINTER TOUR",japanTour.getTourName());
        assertEquals("INDIA GREAT EXPERIENCE TOUR",indiaTour.getTourName());
        assertEquals("KOREA CHEJU ISLAND TOUR",koreaTour.getTourName());
        koreaTour.setTourName("Korea happy Cheju island honeymoon.");
        assertEquals("Korea happy Cheju island honeymoon.",koreaTour.getTourName());
    }

    @Test
    public void testGetTourID(){
        assertEquals("JPN-4D3N-000001",japanTour.getTourID());
        assertEquals("IND-5D4N-000002",indiaTour.getTourID());
        assertEquals("KOR-10D9N-00004",koreaTour.getTourID());
        indiaTour.setTourID("IND-6D5N-000002");
        assertEquals("IND-6D5N-000002",indiaTour.getTourID());
    }

    @Test
    public void testGetterOfDepartureDateAndReturnDate(){
        //departure date
        assertEquals("23-11-2018",japanTour.getDepartureDate());
        assertEquals("29-05-2018",indiaTour.getDepartureDate());
        assertEquals("20-07-2018",koreaTour.getDepartureDate());
        japanTour.setDepartureDate("20-11-2018");
        assertEquals("20-11-2018",japanTour.getDepartureDate());
        //return date
        assertEquals("27-11-2018",japanTour.getReturnDate());
        assertEquals("03-06-2018",indiaTour.getReturnDate());
        assertEquals("30-07-2018",koreaTour.getReturnDate());
        indiaTour.setReturnDate("28-07-2018");
        assertEquals("28-07-2018",indiaTour.getReturnDate());
    }

    @Test
    public void testGetterOfDepositPaymentDateAndArrearsPaymentDate(){
        //deposit payment date
        assertEquals("03-11-2018",japanTour.getDepositDate());
        assertEquals("09-05-2018",indiaTour.getDepositDate());
        assertEquals("31-06-2018",koreaTour.getDepositDate());
        japanTour.setDepositDate("02-11-2018");
        indiaTour.setDepositDate("07-05-2018");
        koreaTour.setDepositDate("25-06-2018");
        assertEquals("02-11-2018",japanTour.getDepositDate());
        assertEquals("07-05-2018",indiaTour.getDepositDate());
        assertEquals("25-06-2018",koreaTour.getDepositDate());
        //arrears payment date
        assertEquals("02-11-2018",japanTour.getArrearsDate());
        assertEquals("19-05-2018",indiaTour.getArrearsDate());
        assertEquals("10-07-2018",koreaTour.getArrearsDate());
        japanTour.setArrearsDate("01-11-2018");
        indiaTour.setArrearsDate("15-11-2018");
        koreaTour.setArrearsDate("09-11-2018");
        assertEquals("01-11-2018",japanTour.getArrearsDate());
        assertEquals("15-11-2018",indiaTour.getArrearsDate());
        assertEquals("09-11-2018",koreaTour.getArrearsDate());
    }

    @Test
    public void testGetterOfAmountOfCustomerAndAvailableSeat(){
        //Amount of the customer in each tour.
        assertEquals(50,japanTour.getAmountOfCustomer());
        assertEquals(70,indiaTour.getAmountOfCustomer());
        assertEquals(100,koreaTour.getAmountOfCustomer());
        //Amount of the available seat in each tour.
        assertEquals(50,japanTour.getAvailable());
        assertEquals(70,indiaTour.getAvailable());
        assertEquals(100,koreaTour.getAvailable());
        //set the available seat
        japanTour.setAvailable(10);
        indiaTour.setAvailable(30);
        koreaTour.setAvailable(3);
        assertEquals(10,japanTour.getAvailable());
        assertEquals(30,indiaTour.getAvailable());
        assertEquals(3,koreaTour.getAvailable());
    }
    @Test
    public void testStatusOfTourPackage(){
        assertEquals("open",japanTour.getStatus());
        assertEquals("open",indiaTour.getStatus());
        assertEquals("open",koreaTour.getStatus());
        //set new status.
        japanTour.setStatus("unavailable");
        indiaTour.setStatus("unavailable");
        koreaTour.setStatus("unavailable");
        assertEquals("unavailable",japanTour.getStatus());
        assertEquals("unavailable",indiaTour.getStatus());
        assertEquals("unavailable",koreaTour.getStatus());
    }
}