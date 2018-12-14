package tourSaleManagementSystemUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;
import static tourSaleManagementSystemUtil.setTourSaleSystemDataUtil.*;

public class FormatConverter {
    // date

    public static String getLocalDateFormat(String pattern){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate = LocalDate.now();
        return  dtf.format(localDate);
    }

    // reservation code (THA-3D2N-000001-000001)
    public static String generateReservationCode(String tourID){
        String tempTour[] = tourID.split("-");
        String lastReserveCode = manageableDatabase.getLastReservationPaymentCode();
        String temp[] = lastReserveCode.split("-");
        lastReserveCode = temp[3];
        lastReserveCode = String.valueOf(Integer.parseInt(lastReserveCode)+1);
        String currentReserveCode = tempTour[0]+"-"+tempTour[1]+"-"+tempTour[2]+"-"+String.format("%06d",Integer.valueOf(lastReserveCode));
        return currentReserveCode;
    }
    // tour id
//    public String generateTourID(String country, String durDays){
//        String lastTourID = manageableDatabase.getLastTourID();
//        String temp[] = lastTourID.split("-");
//        lastTourID = temp[2];
//        lastTourID = String.valueOf(Integer.parseInt(lastTourID)+1);
//        String tourID = country+"-"+durDays+"-"+String.format("%06d",lastTourID);
//        return tourID;
//    }
    // customer id
    public static String generateCustomerID(){
        String lastCustomerID = manageableDatabase.getLastCustomerID();
        lastCustomerID = lastCustomerID.substring(2);
        String currentCustomerID = "CS"+String.format("%08d",Integer.parseInt(lastCustomerID)+1);
        return currentCustomerID ;
    }
    // invoice
    public static String generateInvoiceNo(String type,String reserveCode){
        String invoiceNo = null;
        if(type.equalsIgnoreCase(DEPOSIT_INVOICE))
            invoiceNo =  "DI-"+reserveCode;
        else if (type.equalsIgnoreCase(ARREARS_INVOICE))
            invoiceNo =  "AI-"+reserveCode;
        return invoiceNo;
    }
}
