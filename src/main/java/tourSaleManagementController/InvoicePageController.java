package tourSaleManagementController;

import Table.Invoice;
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
import tourSaleManagementSystemUtil.setTourSaleSystemDataUtil;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.*;
import static tourSaleManagementSystemUtil.setTourSaleSystemDataUtil.ARREARS_INVOICE;
import static tourSaleManagementSystemUtil.setTourSaleSystemDataUtil.CREATED;
import static tourSaleManagementSystemUtil.setTourSaleSystemDataUtil.DEPOSIT_INVOICE;

public class InvoicePageController implements Initializable {

    @FXML
    private ComboBox<String> tourIDChoiceDI;
    @FXML private JFXButton createDepositInvoiceBtn;
    @FXML private ComboBox<String> tourIDChoiceAI;
    @FXML private TableView<Invoice> depositInvoiceTable;
    @FXML private TableColumn<Invoice, String> reservationCodeColumnDI;
    @FXML private TableColumn<Invoice, String> invoice_No_ColumnDI;
    @FXML private TableColumn<Invoice, Integer> amountColumnDI;
    @FXML private TableColumn<Invoice, String> employeeColumnDI;
    @FXML private TableColumn<Invoice, String> invoiceStatusColumnDI;
    @FXML private TableView<Invoice> arrearsInvoiceTable;
    @FXML private TableColumn<Invoice, String> reservationCodeColumnAI;
    @FXML private TableColumn<Invoice, String> invoice_No_ColumnAI;
    @FXML private TableColumn<Invoice, Integer> amountColumnAI;
    @FXML private TableColumn<Invoice, String> employeeColumnAI;
    @FXML private TableColumn<Invoice, String> invoiceStatusColumnAI;
    @FXML private JFXButton createArrearsInvoiceBtn;
    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;
    @FXML private Label currentDateInvoice;


    ObservableList<Invoice> obListInvoiceDI = FXCollections.observableArrayList();
    ObservableList<Invoice> obListInvoiceAI = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayGUIUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
        setTourSaleSystemDataUtil.setTourProgram(tourIDChoiceDI);
        setTourSaleSystemDataUtil.setTourProgram(tourIDChoiceAI);
        currentDateInvoice.setText(FormatConverter.getLocalDateFormat("dd-MM-yyyy"));
        showTableView();
    }

    @FXML
    void handleCreateDepositInvoiceBtn(ActionEvent event) {

        Invoice selectInvoice = depositInvoiceTable.getSelectionModel().getSelectedItem();
        if(selectInvoice != null ) {
            Alert alertCreateDepositInvoice = new Alert(Alert.AlertType.CONFIRMATION);
            alertCreateDepositInvoice.setTitle("Confirmation Dialog");
            alertCreateDepositInvoice.setHeaderText(null);
            alertCreateDepositInvoice.setContentText("Do you want to create deposit invoice?");
            Optional<ButtonType> createDepositInvoiceAction = alertCreateDepositInvoice.showAndWait();
            if (createDepositInvoiceAction.get() == ButtonType.OK) {
                // create deposit invoice
                String invoiceName = selectInvoice.getReservationCode()+"_"+manageableDatabase.getFirstNameCustomer(selectInvoice.getCustomerID())+"_DepositInvoice";
                createReport.createInvoice(invoiceName, "DEPOSIT INVOICE / ใบแจ้งหนี้-เงินมัดจำ",selectInvoice.getReservationCode());

                Alert alertShowCreateDepositInvoice = new Alert(Alert.AlertType.INFORMATION);
                alertShowCreateDepositInvoice.setTitle("Information Dialog");
                alertShowCreateDepositInvoice.setHeaderText(null);
                alertShowCreateDepositInvoice.setContentText("Creating invoice is successfully!");
                Optional<ButtonType> showCreateDepositInvoiceAction = alertShowCreateDepositInvoice.showAndWait();
                if (showCreateDepositInvoiceAction.get() == ButtonType.OK) {
                    // update database and table code
                    selectInvoice.setInvoiceStatus(CREATED);
                    manageableDatabase.updateCreateInvoiceStatus(selectInvoice,DEPOSIT_INVOICE);
                    showTableView();
                }

            }
        }

    }
    @FXML
    void handleCreateArrearsInvoiceBtn(ActionEvent event) {

        Invoice selectInvoice = arrearsInvoiceTable.getSelectionModel().getSelectedItem();
        if(selectInvoice != null ) {
            Alert alertCreateArrearsInvoice = new Alert(Alert.AlertType.CONFIRMATION);
            alertCreateArrearsInvoice.setTitle("Confirmation Dialog");
            alertCreateArrearsInvoice.setHeaderText(null);
            alertCreateArrearsInvoice.setContentText("Do you want to create invoice?");
            Optional<ButtonType> createArrearsInvoiceAction = alertCreateArrearsInvoice.showAndWait();
            if (createArrearsInvoiceAction.get() == ButtonType.OK) {
                // create invoice
                String invoiceName = selectInvoice.getReservationCode()+"_"+manageableDatabase.getFirstNameCustomer(selectInvoice.getCustomerID())+"_ArrearsInvoice";
                createReport.createInvoice(invoiceName, "INVOICE / ใบแจ้งหนี้",selectInvoice.getReservationCode());
                Alert alertShowCreateArrearsInvoice = new Alert(Alert.AlertType.INFORMATION);
                alertShowCreateArrearsInvoice.setTitle("Information Dialog");
                alertShowCreateArrearsInvoice.setHeaderText(null);
                alertShowCreateArrearsInvoice.setContentText("Creating invoice is successfully!");
                Optional<ButtonType> showCreateArrearsInvoiceAction = alertShowCreateArrearsInvoice.showAndWait();
                if (showCreateArrearsInvoiceAction.get() == ButtonType.OK) {
                    // update database and table code
                    selectInvoice.setInvoiceStatus(CREATED);
                    manageableDatabase.updateCreateInvoiceStatus(selectInvoice, ARREARS_INVOICE);
                    showTableView();
                }

            }
        }

    }



    @FXML
    void handleTourIDChoiceDI(ActionEvent event) { showTableView(); }

    @FXML
    void handleTourIDChoiceAI(ActionEvent event) {
        showTableView();
    }


    public void showTableView(){

        String tourNameDI = tourIDChoiceDI.getSelectionModel().getSelectedItem();
        String tourNameAI = tourIDChoiceDI.getSelectionModel().getSelectedItem();
        obListInvoiceDI = FXCollections.observableArrayList(manageableDatabase.getAllInvoiceInTourName(DEPOSIT_INVOICE,tourNameDI));
        obListInvoiceAI = FXCollections.observableArrayList(manageableDatabase.getAllInvoiceInTourName(ARREARS_INVOICE,tourNameAI));

        //find data base for show on table view.
        reservationCodeColumnDI.setCellValueFactory(new PropertyValueFactory<>("reservationCode"));
        invoice_No_ColumnDI.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        amountColumnDI.setCellValueFactory(new PropertyValueFactory<>("amountCustomer"));
        employeeColumnDI.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        invoiceStatusColumnDI.setCellValueFactory(new PropertyValueFactory<>("invoiceStatus"));

        reservationCodeColumnAI.setCellValueFactory(new PropertyValueFactory<>("reservationCode"));
        invoice_No_ColumnAI.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        amountColumnAI.setCellValueFactory(new PropertyValueFactory<>("amountCustomer"));
        employeeColumnAI.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        invoiceStatusColumnAI.setCellValueFactory(new PropertyValueFactory<>("invoiceStatus"));

        depositInvoiceTable.setItems(obListInvoiceDI);
        arrearsInvoiceTable.setItems(obListInvoiceAI);
    }

}
