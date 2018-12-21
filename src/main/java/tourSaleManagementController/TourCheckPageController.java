package tourSaleManagementController;

import Table.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
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
import java.util.Optional;
import java.util.ResourceBundle;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.loginEmployee;
import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;
import static tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil.*;

public class TourCheckPageController implements Initializable {
    @FXML private StackPane rootPane;
    @FXML private ComboBox<String> tourIDComboBox;
    @FXML private Label tourName;
    @FXML private Label tourPrice;
    @FXML private Label departureDate;
    @FXML private Label returnDate;
    @FXML private Label amountCus;
    @FXML private Label availableSeat;
    @FXML private Label showTourID;
    @FXML private Label depositPaymentDate;
    @FXML private Label finalPaymentDate;
    @FXML private TableView<ReservationPayment> paymentListTable;
    @FXML private TableColumn<ReservationPayment, String> reservationCodeColumnP;
    @FXML private TableColumn<ReservationPayment, String> nameColumnP;
    @FXML private TableColumn<ReservationPayment, String> depositPaymentStatusColumnP;
    @FXML private TableColumn<ReservationPayment, String> arrearsPaymentStatusColumnP;
    @FXML private TableColumn<ReservationPayment, String> employeeName_ColumnP;
    @FXML private TableView<DisplayReservationCustomer> reservationListTable;
    @FXML private TableColumn<DisplayReservationCustomer, String> reservationCodeColumnR;
    @FXML private TableColumn<DisplayReservationCustomer, String> nameColumnR;
    @FXML private TableColumn<DisplayReservationCustomer, String> customerAgeColumnR;
    @FXML private TableColumn<DisplayReservationCustomer, String> phoneNumCusColumnR;
    @FXML private JFXButton deleteReserveListBtn;
    @FXML private JFXButton confirmStatusBtn;
    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;
    @FXML private Label loginNameLabel;
    @FXML private JFXButton reportBtn;

    ObservableList<DisplayReservationCustomer> reservationObList = FXCollections.observableArrayList();
    ObservableList<ReservationPayment> reservePaymentObList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayGUIUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
        SetTourSaleSystemDataUtil.setTourProgram(tourIDComboBox);
        showTourID.setText(manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem()));
        showDetailTourPackage();
        setReservationListTable();
        loginNameLabel.setText(loginEmployee.getFirstName()+" "+loginEmployee.getLastName()+" [ "+loginEmployee.getPosition().toUpperCase()+" ]");
    }

    @FXML
    void handleConfirmStatusBtn(ActionEvent event) {

        ReservationPayment selectReservationPayment = paymentListTable.getSelectionModel().getSelectedItem();

        if(selectReservationPayment != null) {
            String[] options = {"Deposit Invoice Payment", "Invoice Payment"};

            ChoiceDialog<String> dialog = new ChoiceDialog<String>("Deposit Invoice Payment", options);
            dialog.setTitle("Confirmation Dialog");
            dialog.setHeaderText("Confirm Invoice Payment Status");
            dialog.setContentText("Choose Invoice Payment Type ");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                if (result.get().equals("Deposit Invoice Payment")) {
                    Invoice depositInvoice = manageableDatabase.getOneInvoice("invoice_deposit", selectReservationPayment.getReservationCode());
                    if (depositInvoice != null && depositInvoice.getCreateStatus().equals("Created")){
                        if(selectReservationPayment.getDepositStatus().equals(NOT_PAID)) {
                            selectReservationPayment.setDepositStatus(PAID);
                            manageableDatabase.updateStatusPayment(selectReservationPayment);                   //update status reservation payment
                            manageableDatabase.insertData(createReceiptData(DEPOSIT_RECEIPT), DEPOSIT_RECEIPT);  //create deposit receipt
                            manageableDatabase.insertData(createArrearsInvoice(), ARREARS_INVOICE);        //insert arrears invoice
                        }
                        else {
                            Alert alertCheckPaidDepositInvoice = new Alert(Alert.AlertType.WARNING);
                            alertCheckPaidDepositInvoice.setTitle("Warning Dialog");
                            alertCheckPaidDepositInvoice.setContentText("Sorry, deposit invoice is paid!");
                            Optional<ButtonType> checkPaidDepositInvoiceAction = alertCheckPaidDepositInvoice.showAndWait();

                        }
                    }
                    else {
                        Alert alertCheckCreatedDepositInvoice = new Alert(Alert.AlertType.ERROR);
                        alertCheckCreatedDepositInvoice.setTitle("Error Dialog");
                        alertCheckCreatedDepositInvoice.setHeaderText("Confirmation deposit invoice payment is error");
                        alertCheckCreatedDepositInvoice.setContentText("Please create deposit invoice!");
                        Optional<ButtonType> checkCreatedDepositInvoiceAction = alertCheckCreatedDepositInvoice.showAndWait();
                    }

                }
                else if (result.get().equals("Invoice Payment")){
                    Invoice arrearsInvoice = manageableDatabase.getOneInvoice("invoice_arrears", selectReservationPayment.getReservationCode());
                    if (arrearsInvoice != null && arrearsInvoice.getCreateStatus().equals("Created")){
                        if (selectReservationPayment.getArrearsStatus().equals(NOT_PAID)) {
                            selectReservationPayment.setArrearsStatus(PAID);
                            manageableDatabase.updateStatusPayment(selectReservationPayment);                   //update status reservation payment
                            manageableDatabase.insertData(createReceiptData(ARREARS_RECEIPT), ARREARS_RECEIPT);  //insert arrears receipt
                        }
                        else {
                            Alert alertCheckPaidInvoice = new Alert(Alert.AlertType.WARNING);
                            alertCheckPaidInvoice.setTitle("Warning Dialog");
                            alertCheckPaidInvoice.setContentText("Sorry, invoice is paid!");
                            Optional<ButtonType> checkPaidInvoiceAction = alertCheckPaidInvoice.showAndWait();
                        }
                    }
                    else {
                        Alert alertCheckCreatedInvoice = new Alert(Alert.AlertType.ERROR);
                        alertCheckCreatedInvoice.setTitle("Error Dialog");
                        alertCheckCreatedInvoice.setHeaderText("Confirmation invoice payment is error");
                        alertCheckCreatedInvoice.setContentText("Please create invoice!");
                        Optional<ButtonType> checkCreatedInvoiceAction = alertCheckCreatedInvoice.showAndWait();
                    }
                }
                setReservationListTable();
            }
        }
    }

    @FXML
    void handleDeleteReserveListBtn(ActionEvent event) {
        ReservationPayment deleteReservationPayment = paymentListTable.getSelectionModel().getSelectedItem();
        if (deleteReservationPayment != null){
            Alert alertConfirmToDeleteReservationList = new Alert(Alert.AlertType.CONFIRMATION);
            alertConfirmToDeleteReservationList.setTitle("Confirmation Dialog");
            alertConfirmToDeleteReservationList.setHeaderText(null);
            alertConfirmToDeleteReservationList.setContentText("Do you want to delete this reservation?");
            Optional<ButtonType> action = alertConfirmToDeleteReservationList.showAndWait();
            if (action.get() == ButtonType.OK) {
                String tourID = deleteReservationPayment.getTourID();
                if (deleteReservationPayment != null) {   //when user select data

                    String reserveCodeDelete = deleteReservationPayment.getReservationCode();
                    //delete observerList on table view

                    reservationObList = FXCollections.observableArrayList();
                    for (DisplayReservationCustomer dis: reservationObList) {
                        if (dis.getReservationCode().equals(deleteReservationPayment.getReservationCode()))
                            reservationObList.remove(dis);
                    }

                    reservePaymentObList.remove(paymentListTable.getSelectionModel().getSelectedItem());

                    //delete data in database
                    manageableDatabase.deleteData(deleteReservationPayment);                                    //delete reservation payment list
                    manageableDatabase.deleteDataByReserveCode(deleteReservationPayment.getReservationCode());   //delete data in reservation list

                    Invoice depositInvoice = manageableDatabase.getOneInvoice(DEPOSIT_INVOICE,reserveCodeDelete);
                    Invoice arrearsInvoice = manageableDatabase.getOneInvoice(ARREARS_INVOICE,reserveCodeDelete);
                    Receipt depositReceipt = manageableDatabase.getOneReceipt(DEPOSIT_RECEIPT,reserveCodeDelete);
                    Receipt arrearsReceipt = manageableDatabase.getOneReceipt(ARREARS_RECEIPT,reserveCodeDelete);

                    if (depositInvoice != null)
                        manageableDatabase.deleteData(depositInvoice);
                    if(arrearsInvoice != null)
                        manageableDatabase.deleteData(arrearsInvoice);
                    if(depositReceipt != null)
                        manageableDatabase.deleteData(depositReceipt);
                    if(arrearsReceipt != null)
                        manageableDatabase.deleteData(arrearsReceipt);

                    //update last data
                    TourPackage tourPackage = manageableDatabase.getOneTourPackage(tourID);
                    int availableSeat = tourPackage.getAvailableSeat() + deleteReservationPayment.getAmountCustomer();
                    manageableDatabase.updateAvailableData(tourID,availableSeat);   //update seat in tour package

                    showDetailTourPackage();
                    setReservationListTable();

                }
            }
        }
        else {
            Alert alertWarningBeforeDelete = new Alert(Alert.AlertType.WARNING);
            alertWarningBeforeDelete.setTitle("Warning Dialog");
            alertWarningBeforeDelete.setHeaderText(null);
            alertWarningBeforeDelete.setContentText("Please select item before delete");
            Optional<ButtonType> deleteAction = alertWarningBeforeDelete.showAndWait();
        }

    }

    @FXML
    void handleReportBtn(ActionEvent event) {
        reportBtn.getScene().getWindow().hide();
        DisplayGUIUtil.loadWindowWithSetSize(getClass().getResource("/tourReportPage.fxml"), "Tour Report");
    }

    @FXML
    void handleSelectTourIDCombobox(ActionEvent event){
        showTourID.setText(manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem()));
        showDetailTourPackage();
        setReservationListTable();
    }

    void showDetailTourPackage(){
        String tourID = manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem());
        TourPackage tourPackage = manageableDatabase.getOneTourPackage(tourID);

        tourName.setText(tourPackage.getTourName());
        tourPrice.setText(String.format("%,.2f",Double.valueOf(tourPackage.getPrice())));
        departureDate.setText(tourPackage.getDepartureDate());
        returnDate.setText(tourPackage.getReturnDate());
        depositPaymentDate.setText(tourPackage.getDepositDate());
        finalPaymentDate.setText(tourPackage.getArrearsDate());
        amountCus.setText(String.valueOf(tourPackage.getAmountSeat()));
        availableSeat.setText(String.valueOf(tourPackage.getAvailableSeat()));
    }

    void setReservationListTable(){

        String tourID = manageableDatabase.getTourID(String.valueOf(tourIDComboBox.getSelectionModel().getSelectedItem()));
        reservePaymentObList = FXCollections.observableArrayList(manageableDatabase.getAllReservationPaymentByTourID(tourID));
        reservationObList = FXCollections.observableArrayList();

        for (Reservation re:manageableDatabase.getAllReservationByTourID(tourID)) {
            Customer customer = manageableDatabase.getOneCustomer(re.getCustomerID());
                reservationObList.add(new DisplayReservationCustomer(
                    re.getReservationCode(),
                    re.getCustomerName(),
                    customer.getCell_phone()));
        }
        //find data base for show on table view.
        reservationCodeColumnP.setCellValueFactory(new PropertyValueFactory<>("reservationCode"));
        nameColumnP.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        depositPaymentStatusColumnP.setCellValueFactory(new PropertyValueFactory<>("depositStatus"));
        arrearsPaymentStatusColumnP.setCellValueFactory(new PropertyValueFactory<>("arrearsStatus"));
        employeeName_ColumnP.setCellValueFactory(new PropertyValueFactory<>("employeeName"));

        reservationCodeColumnR.setCellValueFactory(new PropertyValueFactory<>("reservationCode"));
        nameColumnR.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        phoneNumCusColumnR.setCellValueFactory(new PropertyValueFactory<>("phoneNumCustomer"));


        paymentListTable.setItems(reservePaymentObList);
        reservationListTable.setItems(reservationObList);
    }

    private Invoice createArrearsInvoice() {
        ReservationPayment selectReservationPayment = paymentListTable.getSelectionModel().getSelectedItem();
        Invoice invoice = manageableDatabase.getOneInvoice(DEPOSIT_INVOICE,selectReservationPayment.getReservationCode());
        invoice.setInvoiceNo(FormatConverter.generateInvoiceNo(ARREARS_INVOICE,selectReservationPayment.getReservationCode()));
        invoice.setInvoiceType(ARREARS_INVOICE);
        invoice.setCreateStatus(NOT_CREATED);
        return invoice;
    }


    private Receipt createReceiptData(String receiptType){
        String invoiceType = null;
        if (receiptType.equalsIgnoreCase(DEPOSIT_RECEIPT))
            invoiceType = DEPOSIT_INVOICE;
        else
            invoiceType = ARREARS_INVOICE;

        ReservationPayment selectReservationPayment = paymentListTable.getSelectionModel().getSelectedItem();
        Invoice selectInvoice = manageableDatabase.getOneInvoice(invoiceType,selectReservationPayment.getReservationCode());
        Receipt receipt = new Receipt(
                selectInvoice.getReservationCode(),
                selectInvoice.getInvoiceNo(),
                selectInvoice.getTourID(),
                selectInvoice.getTourName(),
                selectInvoice.getCustomerID(),
                selectInvoice.getCustomerName(),
                selectInvoice.getEmployeeID(),
                selectInvoice.getEmployeeName(),
                selectInvoice.getAmountCustomer(),
                selectInvoice.getTotalPrice(),
                receiptType,
                NOT_CREATED
        );

    return receipt;
    }

    protected class DisplayReservationCustomer{

        private String reservationCode;
        private String customerName;
        private String phoneNumCustomer;

        public DisplayReservationCustomer(String reservationCode, String customerName,String phoneNumCustomer) {
            this.reservationCode = reservationCode;
            this.customerName = customerName;
            this.phoneNumCustomer = phoneNumCustomer;
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

        public String getPhoneNumCustomer() {
            return phoneNumCustomer;
        }

        public void setPhoneNumCustomer(String phoneNumCustomer) {
            this.phoneNumCustomer = phoneNumCustomer;
        }
    }


}

