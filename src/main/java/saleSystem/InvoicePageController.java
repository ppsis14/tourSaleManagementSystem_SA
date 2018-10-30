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
    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;
    @FXML private TextField searchIdInvoice;
    @FXML private JFXButton searchBtnInvoice;
    @FXML private TableView<?> tableInvoice;
    @FXML private Button editBtnInvoice;
    @FXML private Button deleteBtnInvoice;
    @FXML private Button updateBtnInvoice;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
    }

    @FXML
    void handleDeleteInvoice(ActionEvent event) {

    }

    @FXML
    void handleEditBtnInvoice(ActionEvent event) {

    }

    @FXML
    void handleSearchBtnInvoice(ActionEvent event) {

    }

    @FXML
    void handleUpdateBtnInvoice(ActionEvent event) {

    }
}
