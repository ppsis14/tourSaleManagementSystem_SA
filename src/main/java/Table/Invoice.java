package Table;

public class Invoice {

    private String reservationCode;
    private String invoiceNo;
    private String tourID;
    private String tourName;
    private String customerID;
    private String customerName;
    private String employeeID;
    private String employeeName;
    private int amountCustomer;
    private Double totalPrice;
    private String invoiceType;
    private String createStatus = "Not created";


    public Invoice() {

    }

    public Invoice(String reservationCode, String invoiceNo, String tourID, String tourName, String customerID, String customerName, String employeeID, String employeeName, int amountCustomer, Double totalPrice, String invoiceType, String createStatus) {
        this.reservationCode = reservationCode;
        this.invoiceNo = invoiceNo;
        this.tourID = tourID;
        this.tourName = tourName;
        this.customerID = customerID;
        this.customerName = customerName;
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.amountCustomer = amountCustomer;
        this.totalPrice = totalPrice;
        this.invoiceType = invoiceType;
        this.createStatus = createStatus;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getAmountCustomer() {
        return amountCustomer;
    }

    public void setAmountCustomer(int amountCustomer) {
        this.amountCustomer = amountCustomer;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getCreateStatus() {
        return createStatus;
    }

    public void setCreateStatus(String createStatus) {
        this.createStatus = createStatus;
    }
}