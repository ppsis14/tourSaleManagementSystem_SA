package saleSystem;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class ReservePageController implements Initializable {

    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;
    @FXML private ChoiceBox<?> tourCodeChioce;
    @FXML private TextField tourName;
    @FXML private DatePicker departureDate;
    @FXML private ChoiceBox<String> nameTitleTHClient;
    @FXML private TextField firstNameTHClient;
    @FXML private TextField lastNameTHClient;
    @FXML private ChoiceBox<String> nameTitleENClient;
    @FXML private TextField firstNameENClient;
    @FXML private ChoiceBox<String> oldNameTitleTHClient;
    @FXML private TextField oldFirstNameClient;
    @FXML private TextField oldLastNameClient;
    @FXML private ChoiceBox<String> genderChoice;
    @FXML private TextField age;
    @FXML private DatePicker dateOfBirthCilent;
    @FXML private TextField passportClient;
    @FXML private DatePicker expPassportDate;
    @FXML private TextField homeAddrCilent;
    @FXML private TextField countryClient;
    @FXML private TextField homeZipCodeCilent;
    @FXML private TextField cellphoneClient;
    @FXML private TextField homeTelClient;
    @FXML private TextField homeFaxClient;
    @FXML private TextField emailClient;
    @FXML private TextField careerClient;
    @FXML private TextField compNameClient;
    @FXML private TextField compAddrClient;
    @FXML private TextField compCountryClient;
    @FXML private TextField workTelClient;
    @FXML private TextField compZipCodeClirnt;
    @FXML private JFXCheckBox notMemberChioce;
    @FXML private JFXCheckBox memberChioce;
    @FXML private TextField foodAllergy;
    @FXML private TextField underlyingDisease;
    @FXML private JFXCheckBox eatBeefY;
    @FXML private JFXCheckBox eatBeefN;
    @FXML private TextField moreDetail;
    @FXML private JFXCheckBox Bangkokbizsnews;
    @FXML private JFXCheckBox Dailynews;
    @FXML private JFXCheckBox Komchadluek;
    @FXML private JFXCheckBox website;
    @FXML private JFXCheckBox eNews;
    @FXML private JFXCheckBox others;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));

        ObservableList<String> genderChoices = FXCollections.observableArrayList("Male", "Female");
        genderChoice.getSelectionModel().selectFirst();
        genderChoice.setValue("Male");
        genderChoice.getItems().addAll(genderChoices);

        ObservableList<String> nameENTitleChoices = FXCollections.observableArrayList("Miss", "Mrs.", "Mr.", "Master", "Professor", "Assistant Professor", "Associate Professor");
        nameTitleENClient.getSelectionModel().selectFirst();
        nameTitleENClient.setValue("Miss");
        nameTitleENClient.getItems().addAll(nameENTitleChoices);

        ObservableList<String> nameTHTitleChoices = FXCollections.observableArrayList("นางสาว", "นาง", "นาย", "เด็กหญิง", "เด็กชาย", "ศาตราจารย์", "ผู้ช่วยศาสตราจารย์", "รองศาสตราจารย์");
        nameTitleTHClient.getSelectionModel().selectFirst();
        nameTitleTHClient.setValue("นางสาว");
        nameTitleTHClient.getItems().addAll(nameTHTitleChoices);

        ObservableList<String> oldNameTHTitleChoices = FXCollections.observableArrayList("นางสาว", "นาง", "นาย", "เด็กหญิง", "เด็กชาย", "ศาตราจารย์", "ผู้ช่วยศาสตราจารย์", "รองศาสตราจารย์");
        oldNameTitleTHClient.getSelectionModel().selectFirst();
        oldNameTitleTHClient.setValue("นางสาว");
        oldNameTitleTHClient.getItems().addAll(oldNameTHTitleChoices);

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

    @FXML
    void handleAddClientBtn(ActionEvent event) {

    }

}
