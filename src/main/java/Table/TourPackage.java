package Table;

public class TourPackage {
    private  String tourID;
    private  String tourName;
    private  int price;
    private  String departureDate;
    private  String returnDate;
    private  String depositDate;
    private  String arrearsDate;
    private  int amountOfCustomer;
    private  int available;
    private String status;

    public TourPackage(){}

    public TourPackage(String tourID, String tourName, int price, String departureDate, String returnDate, String depositDate, String arrearsDate, int amountOfCustomer, int available, String status) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.price = price;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.depositDate = depositDate;
        this.arrearsDate = arrearsDate;
        this.amountOfCustomer = amountOfCustomer;
        this.available = available;
        this.status = status;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    public int getAmountOfCustomer() {
        return amountOfCustomer;
    }

    public void setAmountOfCustomer(int amountOfCustomer) {
        this.amountOfCustomer = amountOfCustomer;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
