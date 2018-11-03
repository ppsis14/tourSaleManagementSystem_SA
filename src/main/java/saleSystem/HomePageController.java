package saleSystem;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private JFXButton reserveBtn;
    @FXML
    private JFXButton memberBtn;
    @FXML
    private JFXButton tourCheckBtn;
    @FXML
    private JFXButton invoiceBtn;
    @FXML
    private JFXButton receiptBtn;
    @FXML
    private JFXButton logoutBtn;
    @FXML
    private Label loginNameLabel;


    @FXML
    public void handleInvoiceBtn(ActionEvent event) throws IOException {
        invoiceBtn.getScene().getWindow().hide();
        SaleManagementUtil.loadWindow(getClass().getResource("/invoicePage.fxml"), "Onvacation - Home", null);
    }

    @FXML
    public void handleLogoutBtn(ActionEvent event) throws IOException {
        logoutBtn.getScene().getWindow().hide();
        SaleManagementUtil.loadWindow(getClass().getResource("/loginPage.fxml"), "Onvacation - Login", null);
    }

    @FXML
    public void handleMemberBtn(ActionEvent event) throws IOException {
        memberBtn.getScene().getWindow().hide();
        SaleManagementUtil.loadWindow(getClass().getResource("/memberPage.fxml"), "Onvacation - Member", null);

    }

    @FXML
    public void handleReceiptBtn(ActionEvent event) throws IOException {
        receiptBtn.getScene().getWindow().hide();
        SaleManagementUtil.loadWindow(getClass().getResource("/paymentReceiptPage.fxml"), "Onvacation - Receipt", null);

    }

    @FXML
    public void handleReserveBtn(ActionEvent event) throws IOException {
        reserveBtn.getScene().getWindow().hide();
        SaleManagementUtil.loadWindow(getClass().getResource("/reservationPage.fxml"), "Onvacation - Reservation", null);

    }

    @FXML
    public void handleTourCheckBtn(ActionEvent event) throws IOException {
        tourCheckBtn.getScene().getWindow().hide();
        SaleManagementUtil.loadWindow(getClass().getResource("/tourCheckPage.fxml"), "Onvacation - Tour Checking", null);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // set user login by call from database
        //showUserLogined.setText("Thikamporn Simud");
        //homePane.toFront();
        //reservePane.setVisible(false);

    }

    public void setLoginName(String txt){
        loginNameLabel.setText(txt);
    }

}
