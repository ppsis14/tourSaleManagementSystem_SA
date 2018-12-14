package Table;

public class Reservation {
    private String reservationCode;
    private String tourID;
    private String tourName;
    private String customerID;
    private String customerName;
    private String employeeID;
    private String employeeName;


    public Reservation() {
        this.reservationCode = null;
        this.tourID = null;
        this.tourName = null;
        this.customerID = null;
        this.customerName = null;
        this.employeeID = null;
        this.employeeName = null;

    }
    public Reservation(String reservationCode, String tourID, String tourName, String customerID, String customerName, String employeeID, String employeeName) {
        this.reservationCode = reservationCode;
        this.tourID = tourID;
        this.tourName = tourName;
        this.customerID = customerID;
        this.customerName = customerName;
        this.employeeID = employeeID;
        this.employeeName = employeeName;

    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
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


}


