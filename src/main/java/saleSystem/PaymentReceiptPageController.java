package saleSystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentReceiptPageController implements Initializable {
    @FXML private TextField ReserveCodeDPayment;
    @FXML private JFXButton searchBtnDPayment;
    @FXML private JFXButton createDPaymentBtn;
    @FXML private TableView<?> tableDepositPayment;
    @FXML private Button editBtnDPayment;
    @FXML private Button deleteBtnDPayment;
    @FXML private Button updateBtnDPayment;

    @FXML
    private TextField ReserveCodeAPayment;

    @FXML
    private JFXButton searchBtnAPayment;

    @FXML
    private JFXButton createAPaymentBtn;

    @FXML
    private TableView<?> tableArrearPayment;

    @FXML
    private Button editBtnAPayment;

    @FXML
    private Button deleteBtnAPayment;

    @FXML
    private Button updateBtnAPayment;

    @FXML
    private JFXHamburger menu;

    @FXML
    private JFXDrawer drawerMenu;

    @FXML
    void handleCreateArrearsPayment(ActionEvent event) {

    }

    @FXML
    void handleCreateDPayment(ActionEvent event) {

    }

    @FXML
    void handleDeleteAPayment(ActionEvent event) {

    }

    @FXML
    void handleDeleteDPayment(ActionEvent event) {

    }

    @FXML
    void handleEditBtnAPayment(ActionEvent event) {

    }

    @FXML
    void handleEditBtnDPayment(ActionEvent event) {

    }

    @FXML
    void handleRsvCodeAPayment(ActionEvent event) {

    }

    @FXML
    void handleRsvCodeDPayment(ActionEvent event) {

    }

    @FXML
    void handleSearchBtnAPayment(ActionEvent event) {

    }

    @FXML
    void handleSearchBtnDPayment(ActionEvent event) {

    }

    @FXML
    void handleUpdateBtnAPayment(ActionEvent event) {

    }

    @FXML
    void handleUpdateBtnDPayment(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
    }


}
