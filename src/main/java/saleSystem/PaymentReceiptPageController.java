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
    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;
    @FXML private TextField searchIdReceipt;
    @FXML private JFXButton searchBtnReceipt;
    @FXML private TableView<?> tableReceipt;
    @FXML private Button editBtnReceipt;
    @FXML private Button deleteBtnReceipt;
    @FXML private Button updateBtnReceipt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
    }

    @FXML
    void handleDeleteBtnReceipt(ActionEvent event) {

    }

    @FXML
    void handleEditBtnReceipt(ActionEvent event) {

    }

    @FXML
    void handleSearchBtnReceipt(ActionEvent event) {

    }

    @FXML
    void handleUpdateBtnReceipt(ActionEvent event) {

    }
}
