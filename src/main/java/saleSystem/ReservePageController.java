package saleSystem;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservePageController implements Initializable {

    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;
    @FXML private JFXTextField th_Firstname;
    @FXML private JFXTextField th_Lastname;
    @FXML private JFXTextField en_Firstname;
    @FXML private JFXTextField en_Lastname;
    @FXML private JFXTextField passpotNumber;
    @FXML private JFXDatePicker expireDate;
    @FXML private JFXTextField mobileNumber;
    @FXML private JFXCheckBox showMember;
    @FXML private JFXTextArea address;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));

        // set user login by call from database
        //showUserLogin.setText("Thikamporn Simud");
        //homePane.toFront();
        //reservePane.setVisible(false);

//        //record reservation
//        Connection connection = DbConnect.getInstance().getConnection();
//        Statement statement = connection.createStatement();
//
//        ResultSet resultSet = statement.executeQuery("insert into reserve_card_database (FirstnameTH,LastnameTH,FirstnameENG,LastnameENG,Passport_no,Expire_passport_date,Present_address,Mobile_num)"+
//                "values ('"+th_Firstname.getText()+"','"+th_Lastname.getText()+"','"+en_Firstname.getText()+"','"+en_Lastname.getText()+"'," +
//                "'"+passpotNumber.getText()+"','"+expireDate.getPromptText()+"','"+address.getText()+"','"+mobileNumber.getText()+"')");


    }

    /*private void initDrawerToolBar(){
        try {
            VBox toolbar = FXMLLoader.load(getClass().getResource("/hamburgerMenu.fxml"));
            drawerMenu.setSidePane(toolbar);
            drawerMenu.setDefaultDrawerSize(100);
        } catch (IOException e) {
            Logger.getLogger(FXMLLoader.class.getName()).log(Level.SEVERE,null, e);
        }

        HamburgerNextArrowBasicTransition hamMenu = new HamburgerNextArrowBasicTransition(menu);
        hamMenu.setRate(-1);
        menu.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            hamMenu.setRate(hamMenu.getRate()*-1);
            hamMenu.play();
            if (drawerMenu.isHidden()) drawerMenu.open();
            else drawerMenu.close();

        });
    }*/


    /*public void setLoginName(String name){
        showUserLogin.setText(name);
    }*/
}
