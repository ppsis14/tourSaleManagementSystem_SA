package saleSystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class TourCheckPageController implements Initializable {
    @FXML
    private JFXHamburger menu;
    @FXML
    private JFXDrawer drawerMenu;
    @FXML
    private ChoiceBox<?> findTourID;
    @FXML
    private JFXButton searchBtnTour;
    @FXML
    private Label showTourDetail;
    @FXML
    private TableView<?> tabletourCheck;
    @FXML
    private Button editBtnTour;
    @FXML
    private Button deleteBtnTour;
    @FXML
    private Button updateBtnTour;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
    }

    @FXML
    void handleDeleteBtnTour(ActionEvent event) {

    }

    @FXML
    void handleEditBtnTour(ActionEvent event) {

    }

    @FXML
    void handleSearchBtnTour(ActionEvent event) {

    }

    @FXML
    void handleUpdateBtnTour(ActionEvent event) {

    }

}
