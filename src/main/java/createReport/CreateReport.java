package createReport;

public interface CreateReport {

    void createInvoice(String fileName, String titleInvoice, String reservationCode);
    void createReceipt(String fileName, String titleReceipt, String reservationCode);


}
