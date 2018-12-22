package tourSaleManagementController;

import Table.Customer;
import Table.Reservation;
import Table.ReservationPayment;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import createReport.CustomerListReport;
import createReport.ReportCreator;
import createReport.ReservationPaymentReport;
import createReport.SaleReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import tourSaleManagementSystemUtil.DisplayGUIUtil;
import tourSaleManagementSystemUtil.FormatConverter;
import tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.loginEmployee;
import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;
import static tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil.NOT_PAID;
import static tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil.PAID;

public class TourReportController implements Initializable {
    @FXML private StackPane rootPane;
    @FXML private Label loginNameLabel;
    @FXML private ComboBox<String> tourIDComboBox;
    @FXML private Label reportDate;
    @FXML private ChoiceBox<String> depositStatusChoice;
    @FXML private ChoiceBox<String> arrearsStatusChoice;
    @FXML private TableView<ReservationPaymentReport> reservationPaymentTable;
    @FXML private TableColumn<ReservationPaymentReport, String> reservationCodeColumn;
    @FXML private TableColumn<ReservationPaymentReport, String> customerNameColumn;
    @FXML private TableColumn<ReservationPaymentReport, String> cusPhoneNumColumn;
    @FXML private TableColumn<ReservationPaymentReport, String> depositPaymentStatusColumn;
    @FXML private TableColumn<ReservationPaymentReport, String> arrearsPaymentStatusColumn;
    @FXML private TableColumn<ReservationPaymentReport, String> saleNameColumn;
    @FXML private Label totalReservation;
    @FXML private Label totalPaid;
    @FXML private Label totalNotPaid;
    @FXML private JFXButton createPaymentReportBtn;
    @FXML private Label quantity;
    @FXML private TableView<ReservationCustomerReport> customerListTable;
    @FXML private TableColumn<ReservationCustomerReport, Integer> numberColumn;
    @FXML private TableColumn<ReservationCustomerReport, String> customerIDColumn;
    @FXML private TableColumn<ReservationCustomerReport, String> nameColumn;
    @FXML private TableColumn<ReservationCustomerReport, Integer> ageColumn;
    @FXML private TableColumn<ReservationCustomerReport, String> phoneNumColumn;
    @FXML private JFXButton customerListReportBtn;
    @FXML private Label tourPrice;
    @FXML private TableView<SaleReport> SaleTable;
    @FXML private TableColumn<SaleReport, String> reservationSRCodeColumn;
    @FXML private TableColumn<SaleReport, String> customerNameSRColumn;
    @FXML private TableColumn<SaleReport, Integer> quantityColumn;
    @FXML private TableColumn<SaleReport, String> expectedAmount;
    @FXML private TableColumn<SaleReport, String> receivedAmount;
    @FXML private TableColumn<SaleReport, String> saleNameSRColumn;
    @FXML private Label totalQuantity;
    @FXML private Label totalAmount;
    @FXML private JFXButton createSaleReportBtn;
    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;

    ReportCreator reportCreator;
    String tourName;
    String depositComboBox = "None";
    String arrearsComboBox = "None";

    ObservableList<ReservationPaymentReport> obListReservationPaymentReport = FXCollections.observableArrayList() ;
    ObservableList<ReservationCustomerReport> obListReservationCustomerReport = FXCollections.observableArrayList();
    ObservableList<SaleReport> obListSaleReport = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayGUIUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
        loginNameLabel.setText(loginEmployee.getFirstName()+" "+loginEmployee.getLastName()+" [ "+loginEmployee.getPosition().toUpperCase()+" ]");
        SetTourSaleSystemDataUtil.setTourProgram(tourIDComboBox);
        SetTourSaleSystemDataUtil.setPaidStatus(depositStatusChoice);
        SetTourSaleSystemDataUtil.setPaidStatus(arrearsStatusChoice);
        reportDate.setText(FormatConverter.getLocalDateFormat("dd-MM-yyyy"));
        tourName = tourIDComboBox.getSelectionModel().getSelectedItem();
        depositComboBox = "None";
        arrearsComboBox = "None";
        setShowTableView();
    }
    @FXML
    void handleCreateCustomerListReportBtn(ActionEvent event) {
        reportCreator = new CustomerListReport();
        reportCreator.createReport();
    }

    @FXML
    void handleCreatePaymentReportBtn(ActionEvent event) {
        //reportCreator = new ReservationPaymentReport();
        reportCreator.createReport();
    }

    @FXML
    void handleCreateSaleReportBtn(ActionEvent event) {
        //reportCreator = new SaleReport();
        reportCreator.createReport();
    }

    @FXML
    void handleSelectTourID(ActionEvent event) {
        obListReservationPaymentReport = FXCollections.observableArrayList();
        obListReservationCustomerReport = FXCollections.observableArrayList();
        obListSaleReport = FXCollections.observableArrayList();
        tourName = tourIDComboBox.getSelectionModel().getSelectedItem();
        setShowTableView();

    }
    @FXML
    void handleSelectDeposit(ActionEvent event) {
        depositComboBox=depositStatusChoice.getSelectionModel().getSelectedItem();
        arrearsComboBox=arrearsStatusChoice.getSelectionModel().getSelectedItem();

        obListReservationPaymentReport = FXCollections.observableArrayList();
        obListReservationCustomerReport = FXCollections.observableArrayList();
        obListSaleReport = FXCollections.observableArrayList();

        //setShowTableView();

    }
    @FXML
    void handleSelectArrears(ActionEvent event) {
        depositComboBox=depositStatusChoice.getSelectionModel().getSelectedItem();
        arrearsComboBox=arrearsStatusChoice.getSelectionModel().getSelectedItem();

        obListReservationPaymentReport = FXCollections.observableArrayList();
        obListReservationCustomerReport = FXCollections.observableArrayList();
        obListSaleReport = FXCollections.observableArrayList();

        //setShowTableView();

    }


    protected void setReservationPaymentReport(){

        int totalReservation_ = 0;
        int totalPaid_ = 0;
        int totalNotPaid_ = 0 ;

        System.out.println(depositComboBox);
        System.out.println(arrearsComboBox);

        String tour_id = manageableDatabase.getTourID(tourName);
        List<ReservationPayment> reservationPayment = manageableDatabase.getAllReservationPaymentByTourID(tour_id);

        for (ReservationPayment re : reservationPayment) {
            Customer customer = manageableDatabase.getOneCustomer(re.getCustomerID());
            ReservationPaymentReport reservationPaymentReport = new ReservationPaymentReport(
                    re.getReservationCode(),
                    re.getCustomerName(),
                    customer.getCell_phone(),
                    re.getDepositStatus(),
                    re.getArrearsStatus(),
                    re.getEmployeeName()
            );

            if (depositComboBox == "None" && arrearsComboBox == "None") {

                if((re.getDepositStatus().equals(PAID)&& (re.getArrearsStatus().equals(PAID)))) {
                    totalPaid_++;
                }
                else
                    totalNotPaid_++;

                totalReservation_++;
                obListReservationPaymentReport.add(reservationPaymentReport);

            } else if (depositComboBox.equals(PAID)&& arrearsComboBox == "None"){

                if (re.getDepositStatus().equals(PAID) && re.getArrearsStatus().equals(PAID)) {
                    totalPaid_++;
                }
                else
                    totalNotPaid_++;

                totalReservation_++;
                obListReservationPaymentReport.add(reservationPaymentReport);

            } else if (depositComboBox.equals(NOT_PAID) && arrearsComboBox.equals("None")) {

                if (re.getDepositStatus().equals(NOT_PAID )|| re.getArrearsStatus().equals(NOT_PAID)) {
                    totalNotPaid_++;
                    totalReservation_++;
                    obListReservationPaymentReport.add(reservationPaymentReport);
                }

            } else if (depositComboBox.equals("None") && arrearsComboBox .equals(PAID)) {

                if (re.getArrearsStatus().equals(PAID) && re.getArrearsStatus().equals(PAID)) {
                    totalPaid_++;
                }
                else
                    totalNotPaid_++;

                totalReservation_++;
                obListReservationPaymentReport.add(reservationPaymentReport);

            }else if (depositComboBox.equals("None") && arrearsComboBox.equals(NOT_PAID)){

                if (re.getDepositStatus().equals(NOT_PAID) || re.getArrearsStatus().equals(NOT_PAID)){
                    totalNotPaid_++;
                    totalReservation_++;
                    obListReservationPaymentReport.add(reservationPaymentReport);
                }

            }else if (depositComboBox.equals(PAID) && arrearsComboBox.equals(PAID)) {
                if (re.getDepositStatus().equals(PAID) && re.getArrearsStatus().equals(PAID)) {
                    totalReservation_++;
                    totalPaid_++;
                    obListReservationPaymentReport.add(reservationPaymentReport);
                }
            }else if (depositComboBox.equals(PAID) && arrearsComboBox.equals(NOT_PAID)) {
                if (re.getDepositStatus().equals(PAID )&& re.getArrearsStatus().equals(NOT_PAID)) {
                    totalReservation_++;
                    totalNotPaid_++;
                    obListReservationPaymentReport.add(reservationPaymentReport);

                }
            }else if (depositComboBox.equals(NOT_PAID)&& arrearsComboBox.equals(NOT_PAID)) {
                if (re.getDepositStatus() .equals( NOT_PAID )&& re.getArrearsStatus().equals( NOT_PAID)) {
                    totalReservation_++;
                    totalNotPaid_++;
                    obListReservationPaymentReport.add(reservationPaymentReport);
                }
            }else if (depositComboBox .equals( NOT_PAID )&& arrearsComboBox .equals( PAID )) {
                if (re.getDepositStatus() .equals( NOT_PAID) && re.getArrearsStatus() .equals( PAID)) {
                    totalReservation_++;
                    totalNotPaid_++;
                    obListReservationPaymentReport.add(reservationPaymentReport);

                }
            }
        }
        totalReservation.setText(String.valueOf(totalReservation_));
        totalPaid.setText(String.valueOf(totalPaid_));
        totalNotPaid.setText(String.valueOf(totalNotPaid_));
    }

    protected void setReservationCustomerReport(){

        String tour_id = manageableDatabase.getTourID(tourName);
        List<Reservation> reservation = manageableDatabase.getAllReservationByTourID(tour_id);

        int number = 0;
        for (Reservation re : reservation) {
            number++;
            Customer customer = manageableDatabase.getOneCustomer(re.getCustomerID());
            ReservationCustomerReport reservationCustomerReport = new ReservationCustomerReport(
                    number,
                    re.getCustomerID(),
                    re.getCustomerName(),
                    FormatConverter.CalculateAge(customer.getCustomerID()),
                    customer.getCell_phone());
            obListReservationCustomerReport.add(reservationCustomerReport);
        }

        quantity.setText(String.valueOf(number));
    }

    protected void setSaleReport(){

        int quantity = 0 ;
        double expected = 0;
        double received = 0;

        String tour_id = manageableDatabase.getTourID(tourName);
        List<ReservationPayment> reservationPayments = manageableDatabase.getAllReservationPaymentByTourID(tour_id);

        for (ReservationPayment re : reservationPayments) {

            if(re.getDepositStatus().equals(PAID) && re.getArrearsStatus().equals(NOT_PAID)) {
                SaleReport saleReport = new SaleReport(
                        re.getReservationCode(),
                        re.getCustomerName(),
                        re.getAmountCustomer(),
                        String.format("%,.2f",Double.valueOf(re.getTotal_price())),
                        String.format("%,.2f",Double.valueOf(re.getTotal_price())/2),
                        re.getEmployeeName());
                obListSaleReport.add(saleReport);

                quantity += re.getAmountCustomer();
                expected += Double.valueOf(re.getTotal_price());
                received += Double.valueOf(re.getTotal_price()/2);
            }
            else if (re.getDepositStatus().equals(PAID) && re.getArrearsStatus().equals(PAID)){
                SaleReport saleReport = new SaleReport(
                        re.getReservationCode(),
                        re.getCustomerName(),
                        re.getAmountCustomer(),
                        String.format("%,.2f",Double.valueOf(re.getTotal_price())),
                        String.format("%,.2f",Double.valueOf(re.getTotal_price())),
                        re.getEmployeeName());
                obListSaleReport.add(saleReport);

                quantity += re.getAmountCustomer();
                expected += Double.valueOf(re.getTotal_price());
                received += Double.valueOf(re.getTotal_price());
            }
        }

        totalQuantity.setText(String.valueOf(quantity));
        totalAmount.setText(String.format("%,.2f Baht",received));
        tourName = tourIDComboBox.getSelectionModel().getSelectedItem();
        String tourID = manageableDatabase.getTourID(tourName);
        int tourPrice_ = manageableDatabase.getTourPrice(tourID);
        tourPrice.setText(String.format("%,.2f Baht",Double.valueOf(tourPrice_)));
    }

    protected void setShowTableView(){

        setReservationPaymentReport();
        setReservationCustomerReport();
        setSaleReport();

        //show reservation payment report table
        reservationCodeColumn.setCellValueFactory(new PropertyValueFactory<>("reservationCode"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        cusPhoneNumColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        depositPaymentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("depositPaymentStatus"));
        arrearsPaymentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("arrearsPaymentStatus"));
        saleNameColumn.setCellValueFactory(new PropertyValueFactory<>("saleName"));
        reservationPaymentTable.setItems(obListReservationPaymentReport);

        //show customer list report
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        phoneNumColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        customerListTable.setItems(obListReservationCustomerReport);

        //sale report

        reservationSRCodeColumn.setCellValueFactory(new PropertyValueFactory<>("reservationCode"));
        customerNameSRColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        expectedAmount.setCellValueFactory(new PropertyValueFactory<>("expectedAmount"));
        receivedAmount.setCellValueFactory(new PropertyValueFactory<>("receivedAmount"));
        saleNameSRColumn.setCellValueFactory(new PropertyValueFactory<>("saleName"));
        SaleTable.setItems(obListSaleReport);

    }


    protected class ReservationPaymentReport{
        private String reservationCode;
        private String customerName;
        private String phoneNum;
        private String depositPaymentStatus;
        private String arrearsPaymentStatus;
        private String saleName;

        public ReservationPaymentReport(String reservationCode, String customerName, String phoneNum, String depositPaymentStatus, String arrearsPaymentStatus, String saleName) {
            this.reservationCode = reservationCode;
            this.customerName = customerName;
            this.phoneNum = phoneNum;
            this.depositPaymentStatus = depositPaymentStatus;
            this.arrearsPaymentStatus = arrearsPaymentStatus;
            this.saleName = saleName;
        }

        public String getReservationCode() {
            return reservationCode;
        }

        public void setReservationCode(String reservationCode) {
            this.reservationCode = reservationCode;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getDepositPaymentStatus() {
            return depositPaymentStatus;
        }

        public void setDepositPaymentStatus(String depositPaymentStatus) {
            this.depositPaymentStatus = depositPaymentStatus;
        }

        public String getArrearsPaymentStatus() {
            return arrearsPaymentStatus;
        }

        public void setArrearsPaymentStatus(String arrearsPaymentStatus) {
            this.arrearsPaymentStatus = arrearsPaymentStatus;
        }

        public String getSaleName() {
            return saleName;
        }

        public void setSaleName(String saleName) {
            this.saleName = saleName;
        }
    }

    protected class ReservationCustomerReport{
        private int number;
        private String customerID;
        private String customerName;
        private int age;
        private String phoneNum;

        public ReservationCustomerReport(int number, String customerID, String customerName, int age, String phoneNum) {
            this.number = number;
            this.customerID = customerID;
            this.customerName = customerName;
            this.age = age;
            this.phoneNum = phoneNum;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }
    }

    protected class SaleReport {
        private String reservationCode;
        private String customerName;
        private int quantity;
        private String expectedAmount;
        private String receivedAmount;
        private String saleName;

        public SaleReport(String reservationCode, String customerName, int quantity, String expectedAmount, String receivedAmount, String saleName) {
            this.reservationCode = reservationCode;
            this.customerName = customerName;
            this.quantity = quantity;
            this.expectedAmount = expectedAmount;
            this.receivedAmount = receivedAmount;
            this.saleName = saleName;
        }

        public String getReservationCode() {
            return reservationCode;
        }

        public void setReservationCode(String reservationCode) {
            this.reservationCode = reservationCode;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getExpectedAmount() {
            return expectedAmount;
        }

        public void setExpectedAmount(String expectedAmount) {
            this.expectedAmount = expectedAmount;
        }

        public String getReceivedAmount() {
            return receivedAmount;
        }

        public void setReceivedAmount(String receivedAmount) {
            this.receivedAmount = receivedAmount;
        }

        public String getSaleName() {
            return saleName;
        }

        public void setSaleName(String saleName) {
            this.saleName = saleName;
        }
    }


}

