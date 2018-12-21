package tourSaleManagementController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import tourSaleManagementSystemUtil.DisplayGUIUtil;
import tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil;

import java.net.URL;
import java.util.ResourceBundle;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.loginEmployee;

public class TourReportController implements Initializable {
    @FXML private StackPane rootPane;
    @FXML private Label loginNameLabel;
    @FXML private ComboBox<?> tourIDComboBox;
    @FXML private Label reportDate;
    @FXML private ChoiceBox<String> depositStatusChoice;
    @FXML private ChoiceBox<String> arrearsStatusChoice;
    @FXML private TableView<?> reservationPaymentTable;
    @FXML private TableColumn<?, ?> reservationCodeColumn;
    @FXML private TableColumn<?, ?> customerNameColumn;
    @FXML private TableColumn<?, ?> cusPhoneNumColumn;
    @FXML private TableColumn<?, ?> depositPaymentStatusColumn;
    @FXML private TableColumn<?, ?> arrearsPaymentStatusColumn;
    @FXML private TableColumn<?, ?> saleNameColumn;
    @FXML private Label totalReservation;
    @FXML private Label totalPaid;
    @FXML private Label totalNotPaid;
    @FXML private Label quantity12;
    @FXML private JFXButton createPaymentReportBtn;
    @FXML private Label quantity;
    @FXML private TableView<?> customerListTable;
    @FXML private TableColumn<?, ?> numberColumn;
    @FXML private TableColumn<?, ?> customerIDColumn;
    @FXML private TableColumn<?, ?> nameColumn;
    @FXML private TableColumn<?, ?> ageColumn;
    @FXML private TableColumn<?, ?> phoneNumColumn;
    @FXML private JFXButton customerListReportBtn;
    @FXML private Label tourPrice;
    @FXML private TableView<?> SaleTable;
    @FXML private TableColumn<?, ?> reservationSRCodeColumn;
    @FXML private TableColumn<?, ?> customerNameSRColumn;
    @FXML private TableColumn<?, ?> quantityColumn;
    @FXML private TableColumn<?, ?> depositPaymentStatusSRColumn;
    @FXML private TableColumn<?, ?> arrearsPaymentStatusSRColumn;
    @FXML private TableColumn<?, ?> amount;
    @FXML private TableColumn<?, ?> saleNameSRColumn;
    @FXML private Label totalQuantity;
    @FXML private Label totalAmount;
    @FXML private JFXButton createSaleReportBtn;
    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;

    @FXML
    void handleCreateCustomerListReportBtn(ActionEvent event) {

    }

    @FXML
    void handleCreatePaymentReportBtn(ActionEvent event) {

    }

    @FXML
    void handleCreateSaleReportBtn(ActionEvent event) {

    }

    @FXML
    void handleSelectTourID(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayGUIUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
        loginNameLabel.setText(loginEmployee.getFirstName()+" "+loginEmployee.getLastName()+" [ "+loginEmployee.getPosition().toUpperCase()+" ]");
        SetTourSaleSystemDataUtil.setTourProgram(tourIDComboBox);
        SetTourSaleSystemDataUtil.setPaidStatus(depositStatusChoice);
        SetTourSaleSystemDataUtil.setPaidStatus(arrearsStatusChoice);
    }
}

