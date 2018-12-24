package Table;

public class TourPackage {
    private  String tourID;
    private  String tourName;
    private  double price;
    private  String departureDate;
    private  String returnDate;
    private  String depositDate;
    private  String arrearsDate;
    private  int amountSeat;
    private  int availableSeat;
    private String status;

    public TourPackage(){}

    public TourPackage(String tourID, String tourName, double price, String departureDate, String returnDate, String depositDate, String arrearsDate, int amountSeat, int availableSeat, String status) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.price = price;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.depositDate = depositDate;
        this.arrearsDate = arrearsDate;
        this.amountSeat = amountSeat;
        this.availableSeat = availableSeat;
        this.status = status;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }

    public String getArrearsDate() {
        return arrearsDate;
    }

    public void setArrearsDate(String arrearsDate) {
        this.arrearsDate = arrearsDate;
    }

    public int getAmountSeat() {
        return amountSeat;
    }

    public void setAmountSeat(int amountSeat) {
        this.amountSeat = amountSeat;
    }

    public int getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(int availableSeat) {
        this.availableSeat = availableSeat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
