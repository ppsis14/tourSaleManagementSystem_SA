package tourSaleManagementController;

import Table.Receipt;
import Table.TourPackage;
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
import tourSaleManagementSystemUtil.DisplayGUIUtil;
import tourSaleManagementSystemUtil.FormatConverter;
import tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.createReport;
import static tourSaleManagementSystemUtil.DisplayGUIUtil.loginEmployee;
import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;
import static tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil.*;

public class PaymentReceiptPageController implements Initializable {
    @FXML private Label loginNameLabel;
    @FXML private ComboBox<String> tourIDChoiceDR;
    @FXML private TableView<Receipt> depositReceiptTable;
    @FXML private TableColumn<Receipt, String> reservationCodeColumnDR;
    @FXML private TableColumn<Receipt, String> receipt_no_ColumnDR;
    @FXML private TableColumn<Receipt, Integer> amountCustomerColumnDR;
    @FXML private TableColumn<Receipt, String> employeeNameColumnDR;
    @FXML private TableColumn<Receipt, String> receiptStatusColumnDR;
    @FXML private TableView<Receipt> arrearsReceiptTable;
    @FXML private TableColumn<Receipt, String> reservationCodeColumnAR;
    @FXML private TableColumn<Receipt, String> receipt_no_ColumnAR;
    @FXML private TableColumn<Receipt, Integer> amountCustomerColumnAR;
    @FXML private TableColumn<Receipt, String> employeeNameColumnAR;
    @FXML private TableColumn<Receipt, String> receiptStatusColumnAR;
    @FXML private JFXButton createDepositReceiptBtn;
    @FXML private ComboBox<String> tourIDChoiceAR;
    @FXML private JFXButton createArrearsReceiptBtn;
    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;
    @FXML private Label currentDateReceipt;

    ObservableList<Receipt> obListReceiptDR = FXCollections.observableArrayList();
    ObservableList<Receipt> obListReceiptAR = FXCollections.observableArrayList();

    List<TourPackage> tourPackageList = manageableDatabase.getAllTourPackage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayGUIUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
        SetTourSaleSystemDataUtil.setTourProgram(tourIDChoiceDR);
        SetTourSaleSystemDataUtil.setTourProgram(tourIDChoiceAR);
        currentDateReceipt.setText(FormatConverter.getLocalDateFormat("dd-MM-yyyy"));
        loginNameLabel.setText(loginEmployee.getFirstName()+" "+loginEmployee.getLastName()+" [ "+loginEmployee.getPosition().toUpperCase()+" ]");
        showTableView();


    }

    @FXML
    void handleCreateDepositReceiptBtn(ActionEvent event) {

        Receipt selectReceipt = depositReceiptTable.getSelectionModel().getSelectedItem();
        if(selectReceipt != null ) {
            if (selectReceipt != null && selectReceipt.getCreateStatus().equals("Created")){
                Alert alertCheckCreateDepositReceipt = new Alert(Alert.AlertType.WARNING);
                alertCheckCreateDepositReceipt.setTitle("Warning Dialog");
                alertCheckCreateDepositReceipt.setContentText("Sorry, duplicate receipt are not allowed.");
                Optional<ButtonType> checkCreateDepositReceiptAction = alertCheckCreateDepositReceipt.showAndWait();

            }
            else {
                Alert alertCreateDepositReceipt = new Alert(Alert.AlertType.CONFIRMATION);
                alertCreateDepositReceipt.setTitle("Confirmation Dialog");
                alertCreateDepositReceipt.setHeaderText(null);
                alertCreateDepositReceipt.setContentText("Do you want to create deposit receipt?");
                Optional<ButtonType> createDepositReceiptAction = alertCreateDepositReceipt.showAndWait();
                if (createDepositReceiptAction.get() == ButtonType.OK) {
                    // create receipt
                    String receiptName = selectReceipt.getReservationCode()+"_"+manageableDatabase.getFirstNameCustomer(selectReceipt.getCustomerID())+"_DepositReceipt";
                    createReport.createReceipt(receiptName, "DEPOSIT RECEIPT / ใบเสร็จรับเงิน",selectReceipt.getReservationCode());
                    Alert alertShowCreateDepositReceipt = new Alert(Alert.AlertType.INFORMATION);
                    alertShowCreateDepositReceipt.setTitle("Information Dialog");
                    alertShowCreateDepositReceipt.setHeaderText(null);
                    alertShowCreateDepositReceipt.setContentText("Creating receipt is successfully!");
                    Optional<ButtonType> showCreateDepositReceiptAction = alertShowCreateDepositReceipt.showAndWait();
                    if (showCreateDepositReceiptAction.get() == ButtonType.OK) {
                        // update database and table code
                        selectReceipt.setCreateStatus(CREATED);
                        manageableDatabase.updateCreateReceiptStatus(selectReceipt);
                        showTableView();
                    }
                }
            }

        }
    }
    @FXML
    void handleCreateArrearsReceiptBtn(ActionEvent event) {
        Receipt selectReceipt = arrearsReceiptTable.getSelectionModel().getSelectedItem();
        if(selectReceipt != null ) {
            if (selectReceipt != null && selectReceipt.getCreateStatus().equals("Created")){
                Alert alertCheckCreateReceipt = new Alert(Alert.AlertType.WARNING);
                alertCheckCreateReceipt.setTitle("Warning Dialog");
                alertCheckCreateReceipt.setContentText("Sorry, duplicate receipt are not allowed.");
                Optional<ButtonType> checkCreateReceiptAction = alertCheckCreateReceipt.showAndWait();
            }
            else {
                Alert alertCreateArrearsReceipt = new Alert(Alert.AlertType.CONFIRMATION);
                alertCreateArrearsReceipt.setTitle("Confirmation Dialog");
                alertCreateArrearsReceipt.setHeaderText(null);
                alertCreateArrearsReceipt.setContentText("Do you want to create receipt?");
                Optional<ButtonType> createArrearsReceiptAction = alertCreateArrearsReceipt.showAndWait();

                if (createArrearsReceiptAction.get() == ButtonType.OK) {
                    // create receipt
                    String receiptName = selectReceipt.getReservationCode()+"_"+manageableDatabase.getFirstNameCustomer(selectReceipt.getCustomerID())+"_ArrearsReceipt";
                    createReport.createReceipt(receiptName, "RECEIPT / ใบสำคัญรับเงิน",selectReceipt.getReservationCode());
                    Alert alertShowCreateArrearsReceipt = new Alert(Alert.AlertType.INFORMATION);
                    alertShowCreateArrearsReceipt.setTitle("Information Dialog");
                    alertShowCreateArrearsReceipt.setHeaderText(null);
                    alertShowCreateArrearsReceipt.setContentText("Creating receipt is successfully!");
                    Optional<ButtonType> showCreateArrearsReceiptAction = alertShowCreateArrearsReceipt.showAndWait();
                    if (showCreateArrearsReceiptAction.get() == ButtonType.OK) {
                        // update database and table code
                        selectReceipt.setCreateStatus(CREATED);
                        manageableDatabase.updateCreateReceiptStatus(selectReceipt);
                        showTableView();
                    }

                }
            }

        }

    }

    @FXML
    void handleTourIDChoiceAR(ActionEvent event) {
        showTableView();
    }

    @FXML
    void handleTourIDChoiceDR(ActionEvent event) {
        showTableView();
    }


    public void showTableView(){

        String tourNameDR = tourIDChoiceDR.getSelectionModel().getSelectedItem();
        String tourNameAR = tourIDChoiceAR.getSelectionModel().getSelectedItem();
        obListReceiptDR = FXCollections.observableArrayList(manageableDatabase.getAllReceiptInTourName(DEPOSIT_RECEIPT,tourNameDR));
        obListReceiptAR = FXCollections.observableArrayList(manageableDatabase.getAllReceiptInTourName(ARREARS_RECEIPT,tourNameAR));

        //find data base for show on table view.
        reservationCodeColumnDR.setCellValueFactory(new PropertyValueFactory<>("reservationCode"));
        receipt_no_ColumnDR.setCellValueFactory(new PropertyValueFactory<>("receiptNo"));
        amountCustomerColumnDR.setCellValueFactory(new PropertyValueFactory<>("amountCustomer"));
        employeeNameColumnDR.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        receiptStatusColumnDR.setCellValueFactory(new PropertyValueFactory<>("createStatus"));

        reservationCodeColumnAR.setCellValueFactory(new PropertyValueFactory<>("reservationCode"));
        receipt_no_ColumnAR.setCellValueFactory(new PropertyValueFactory<>("receiptNo"));
        amountCustomerColumnAR.setCellValueFactory(new PropertyValueFactory<>("amountCustomer"));
        employeeNameColumnAR.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        receiptStatusColumnAR.setCellValueFactory(new PropertyValueFactory<>("createStatus"));

        depositReceiptTable.setItems(obListReceiptDR);
        arrearsReceiptTable.setItems(obListReceiptAR);
    }
}
