package Table;

public class ReservationPayment extends Reservation {
    private int amountCustomer;
    private Double total_price;
    private String depositStatus;
    private String arrearsStatus;

    public ReservationPayment(){
        this.amountCustomer = 0;
        this.total_price = 0.0 ;
        this.depositStatus = "Not paid";
        this.arrearsStatus = "Not paid";
    }

    public ReservationPayment(String reservationCode, String tourID, String tourName, String customerID, String customerName, String employeeID, String employeeName, int amountCustomer, Double total_price, String depositStatus, String arrearsStatus) {
        super(reservationCode, tourID, tourName, customerID, customerName, employeeID, employeeName);
        this.amountCustomer = amountCustomer;
        this.total_price = total_price;
        this.depositStatus = depositStatus;
        this.arrearsStatus = arrearsStatus;
    }

    public int getAmountCustomer() {
        return amountCustomer;
    }

    public void setAmountCustomer(int amountCustomer) {
        this.amountCustomer = amountCustomer;
    }

    public String getDepositStatus() {
        return depositStatus;
    }

    public void setDepositStatus(String depositStatus) {
        this.depositStatus = depositStatus;
    }

    public String getArrearsStatus() {
        return arrearsStatus;
    }

    public void setArrearsStatus(String arrearsStatus) {
        this.arrearsStatus = arrearsStatus;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public Double calculateTotalPrice(Double tourPrice){
        return amountCustomer * tourPrice;
    }
}
