package saleSystem;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ToolBarController {
    @FXML private JFXButton homeWindow;
    @FXML private JFXButton reserveWindow;
    @FXML private JFXButton memberWindow;
    @FXML private JFXButton tourCheckWindow;
    @FXML private JFXButton invoiceWindow;
    @FXML private JFXButton receiptWindow;
    @FXML private JFXButton logoutWindow;

    @FXML
    private void loadHomeWindow(ActionEvent event) {
        homeWindow.getScene().getWindow().hide();
        SaleManagementUtil.loadWindow(getClass().getResource("/homePage.fxml"), "Onvacation - Home", null);
    }

    @FXML
    private void loadInvoiceWindow(ActionEvent event) {
        invoiceWindow.getScene().getWindow().hide();
        SaleManagementUtil.loadWindow(getClass().getResource("/invoicePage.fxml"), "Onvacation - Invoice", null);
    }

    @FXML
    private void loadLoginWindow(ActionEvent event) {
        logoutWindow.getScene().getWindow().hide();
        SaleManagementUtil.loadWindow(getClass().getResource("/loginPage.fxml"), "Onvacation - Login", null);
    }
    @FXML
    private void loadMemberWindow(ActionEvent event){
        memberWindow.getScene().getWindow().hide();
        SaleManagementUtil.loadWindow(getClass().getResource("/memberPage.fxml"), "Onvacation - Member", null);
    }

    @FXML
    private void loadReceiptWindow(ActionEvent event) {
        receiptWindow.getScene().getWindow().hide();
        SaleManagementUtil.loadWindow(getClass().getResource("/paymentReceiptPage.fxml"), "Onvacation - Receipt", null);
    }
    @FXML
    private void loadReserveWindow(ActionEvent event) {
        reserveWindow.getScene().getWindow().hide();
        SaleManagementUtil.loadWindow(getClass().getResource("/reservationPage.fxml"), "Onvacation - Reservation", null);
    }

    @FXML
    private void loadTourCheckWindow(ActionEvent event) {
        tourCheckWindow.getScene().getWindow().hide();
        SaleManagementUtil.loadWindow(getClass().getResource("/tourCheckPage.fxml"), "Onvacation - Tour Checking", null);
    }
}
