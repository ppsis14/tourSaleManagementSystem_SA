package tourSaleManagementSystemUtil;

import Table.Customer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;
import static tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil.*;

public class FormatConverter {
    // date
    public static String getLocalDateFormat(String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }

    //time
    public static String getLocalTimeFormat() {

        //"hh" in pattern is for 12 hour time format and "aa" is for AM/PM
        SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("hh:mm aa");
        //Setting the time zone
        dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        return dateTimeInGMT.format(new Date());

    }


    // tour id (JPN-5D4N-00001)
    public static String generateTourID(String country, String durDays) {
        String lastTourID = manageableDatabase.getLastTourID();
        String temp[] = lastTourID.split("-");
        lastTourID = temp[2];
        lastTourID = String.valueOf(Integer.parseInt(lastTourID) + 1);
        String tourID = country + "-" + durDays + "-" + String.format("%06d", Integer.valueOf(lastTourID));
        return tourID;
    }

    // reservation code (THA-3D2N-000001-000001)
    public static String generateReservationCode(String tourID) {
        String tempTour[] = tourID.split("-");
        String lastReserveCode = manageableDatabase.getLastReservationPaymentCode();
        String temp[] = lastReserveCode.split("-");
        lastReserveCode = temp[3];
        lastReserveCode = String.valueOf(Integer.parseInt(lastReserveCode) + 1);
        String currentReserveCode = tempTour[0] + "-" + tempTour[1] + "-" + tempTour[2] + "-" + String.format("%06d", Integer.valueOf(lastReserveCode));
        return currentReserveCode;
    }

    public static String generateCustomerID() {
        String lastCustomerID = manageableDatabase.getLastCustomerID();
        lastCustomerID = lastCustomerID.substring(2);
        String currentCustomerID = "CS" + String.format("%08d", Integer.parseInt(lastCustomerID) + 1);
        return currentCustomerID;
    }

    // invoice
    public static String generateInvoiceNo(String type, String reserveCode) {
        String invoiceNo = null;
        if (type.equalsIgnoreCase(DEPOSIT_INVOICE))
            invoiceNo = "DI-" + reserveCode;
        else if (type.equalsIgnoreCase(ARREARS_INVOICE))
            invoiceNo = "AI-" + reserveCode;
        return invoiceNo;
    }

    //age
    public static int CalculateAge(String customerID) {

        int age = 0;

        Customer customer = manageableDatabase.getOneCustomer(customerID);
        if( customer != null ) {
            String[] dateOfBirth = customer.getDateOfBirth().split("-");
            LocalDate today = LocalDate.now(); //Today's date
            LocalDate birthday = LocalDate.of(Integer.valueOf(dateOfBirth[2]),Integer.valueOf(dateOfBirth[1]), Integer.valueOf(dateOfBirth[0]));  //Birth date

            Period p = Period.between(birthday, today);
            age = p.getYears();
        }
        return age;

    }
}
