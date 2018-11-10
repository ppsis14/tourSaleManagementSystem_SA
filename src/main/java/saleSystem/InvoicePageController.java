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

public class InvoicePageController implements Initializable {
    @FXML
    private TextField ReserveCodeDepositInvoice;

    @FXML
    private JFXButton searchBtnDInvoice;

    @FXML
    private JFXButton createDInvoiceBtn;

    @FXML
    private TableView<?> tableDepositInvoice;

    @FXML
    private Button editBtnDInvoice;

    @FXML
    private Button deleteBtnDInvoice;

    @FXML
    private Button updateBtnDInvoice;

    @FXML
    private TextField ReserveCodeArrearInsvoice;

    @FXML
    private JFXButton searchBtnAInvoice;

    @FXML
    private JFXButton createAInvoiceBtn;

    @FXML
    private TableView<?> tableArrearsInvoice;

    @FXML
    private Button editBtnAInvoice;

    @FXML
    private Button deleteBtnAInvoice;

    @FXML
    private Button updateBtnAInvoice;

    @FXML
    private JFXHamburger menu;

    @FXML
    private JFXDrawer drawerMenu;

    @FXML
    void handleCreateArrearInvoice(ActionEvent event) {

    }

    @FXML
    void handleCreateDepositInvoice(ActionEvent event) {

    }

    @FXML
    void handleDeleteArrearsInvoice(ActionEvent event) {

    }

    @FXML
    void handleDeleteDepositInvoice(ActionEvent event) {

    }

    @FXML
    void handleEditBtnArrearsInvoice(ActionEvent event) {

    }

    @FXML
    void handleEditBtnDepositInvoice(ActionEvent event) {

    }

    @FXML
    void handleRsvCodeArrearsInvoice(ActionEvent event) {

    }

    @FXML
    void handleRsvCodeDInvoice(ActionEvent event) {

    }

    @FXML
    void handleSearchBtnArrearsInvoice(ActionEvent event) {

    }

    @FXML
    void handleSearchBtnDInvoice(ActionEvent event) {

    }

    @FXML
    void handleUpdateBtnArrearsInvoice(ActionEvent event) {

    }

    @FXML
    void handleUpdateBtnDepositInvoice(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));

    }
}
