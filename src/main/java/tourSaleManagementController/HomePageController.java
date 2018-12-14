package tourSaleManagementController;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import tourSaleManagementSystemUtil.DisplayGUIUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.loginEmployee;

public class HomePageController implements Initializable {

    @FXML private JFXButton reserveBtn;
    @FXML private JFXButton customerManageBtn;
    @FXML private JFXButton tourCheckBtn;
    @FXML private JFXButton invoiceBtn;
    @FXML private JFXButton receiptBtn;
    @FXML private JFXButton logoutBtn;
    @FXML private Label loginNameLabel;


    @FXML
    public void handleInvoiceBtn(ActionEvent event) throws IOException {
        invoiceBtn.getScene().getWindow().hide();
        DisplayGUIUtil.loadWindow(getClass().getResource("/invoicePage.fxml"), "Invoice");
    }

    @FXML
    public void handleLogoutBtn(ActionEvent event) throws IOException {
        logoutBtn.getScene().getWindow().hide();
        DisplayGUIUtil.loadWindow(getClass().getResource("/loginPage.fxml"), "Log in");
    }

    @FXML
    public void handleCustomerManageBtn(ActionEvent event) throws IOException {
        customerManageBtn.getScene().getWindow().hide();
        DisplayGUIUtil.loadWindow(getClass().getResource("/customerManagePage.fxml"), "Customer Management");
    }

    @FXML
    public void handleReceiptBtn(ActionEvent event) throws IOException {
        receiptBtn.getScene().getWindow().hide();
        DisplayGUIUtil.loadWindow(getClass().getResource("/paymentReceiptPage.fxml"), "Payment Receipt");
    }

    @FXML
    public void handleReserveBtn(ActionEvent event) throws IOException {
        reserveBtn.getScene().getWindow().hide();
        DisplayGUIUtil.loadWindow(getClass().getResource("/reservationPage.fxml"), "Reservation");
    }

    @FXML
    public void handleTourCheckBtn(ActionEvent event) throws IOException {
        tourCheckBtn.getScene().getWindow().hide();
        DisplayGUIUtil.loadWindow(getClass().getResource("/tourCheckPage.fxml"), "Tour Checking");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // set user login
        loginNameLabel.setText(loginEmployee.getFirstName()+" "+loginEmployee.getLastName()+" [ "+loginEmployee.getPosition()+" ]");

    }


}