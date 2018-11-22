package saleSystem;
import Table.Customer;
import com.jfoenix.controls.*;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import databaseConnection.DbConnect;
import databaseConnection.MongoDBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static databaseConnection.MongoDBConnect.reserve_card;
import static saleSystem.Controller.databaseManager;

public class ReservePageController implements Initializable {


    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;
    @FXML private ChoiceBox<?> tourCodeChioce;
    @FXML private TextField reserveCode;
    @FXML private DatePicker departureDate;
    @FXML private ChoiceBox<String> nameTitleTHClient;
    @FXML private TextField firstNameTHClient;
    @FXML private TextField lastNameTHClient;
    @FXML private ChoiceBox<String> nameTitleENClient;
    @FXML private TextField firstNameENClient;
    @FXML private TextField lastNameENClient;
    @FXML private ChoiceBox<String> oldNameTitleTHClient;
    @FXML private TextField oldFirstNameClient;
    @FXML private TextField oldLastNameClient;
    @FXML private ChoiceBox<String> genderChoice;
    @FXML private TextField age;
    @FXML private DatePicker dateOfBirthClient;
    @FXML private TextField passportClient;
    @FXML private DatePicker expPassportDate;
    @FXML private TextField homeAddClient;
    @FXML private TextField countryClient;
    @FXML private TextField homeZipCodeClient;
    @FXML private TextField cellphoneClient;
    @FXML private TextField homeTelClient;
    @FXML private TextField homeFaxClient;
    @FXML private TextField emailClient;
    @FXML private TextField careerClient;
    @FXML private TextField compNameClient;
    @FXML private TextField compAddClient;
    @FXML private TextField compCountryClient;
    @FXML private TextField workTelClient;
    @FXML private TextField compZipCodeClient;
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
    @FXML private JFXCheckBox sms;
    @FXML private JFXCheckBox tvAds;
    Customer customer = new Customer();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));

        ObservableList<String> genderChoices = FXCollections.observableArrayList("Female","Male");
        genderChoice.getSelectionModel().selectFirst();
        genderChoice.setValue("Female");
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

    }
    @FXML
    public void handleAddClientBtn(ActionEvent event) throws SQLException {
        //record reservation
        addCustomer();
        databaseManager.insertData(customer);


    }

    public void handleMemberCheckbox(ActionEvent event) { notMemberChioce.setSelected(false); }
    public void handleNotMemberCheckbox(ActionEvent event) { memberChioce.setSelected(false); }
    public void handleNotEatBeefCheckbox(ActionEvent event) { eatBeefY.setSelected(false); }
    public void handleEatBeefCheckbox(ActionEvent event) { eatBeefN.setSelected(false); }

    public ArrayList<String> getChannelList(){
        ArrayList<String> channelList = new ArrayList<>();

        if (Bangkokbizsnews.isSelected())
            channelList.add(Bangkokbizsnews.getText());
        if (Dailynews.isSelected())
            channelList.add(Dailynews.getText());
        if (Komchadluek.isSelected())
            channelList.add(Komchadluek.getText());
        if (website.isSelected())
            channelList.add(website.getText());
        if (eNews.isSelected())
            channelList.add(eNews.getText());
        if (sms.isSelected())
            channelList.add(sms.getText());
        if (tvAds.isSelected())
            channelList.add(tvAds.getText());
        if (channelList==null)
            channelList.add(others.getText());

        return channelList;

    }

    public void addCustomer(){

        customer.setTitleNameTH(nameTitleTHClient.getSelectionModel().getSelectedItem());
        customer.setFirstNameTH(firstNameTHClient.getText());
        customer.setLastNameTH(lastNameTHClient.getText());
        customer.setTitleNameENG(nameTitleENClient.getSelectionModel().getSelectedItem());
        customer.setFirstNameENG(firstNameENClient.getText());
        customer.setLastNameENG(lastNameENClient.getText());
        customer.setTitleNameOld(oldNameTitleTHClient.getSelectionModel().getSelectedItem());
        customer.setFirstNameOld(oldFirstNameClient.getText());
        customer.setLastNameOld(oldLastNameClient.getText());
        customer.setGender(genderChoice.getSelectionModel().getSelectedItem());
        customer.setAge(age.getText());
        customer.setDateOfBirth(dateOfBirthClient.getEditor().getText());
        customer.setPassport_no(passportClient.getText());
        customer.setExp_passport(expPassportDate.getEditor().getText());
        //clientContact
        customer.setHomeAddress(homeAddClient.getText()+" "+countryClient.getText()+" "+homeZipCodeClient.getText());
        customer.setCell_phone(cellphoneClient.getText());
        customer.setHome_Tel(homeTelClient.getText());
        customer.setFax(homeFaxClient.getText());
        customer.setEmail(emailClient.getText());
        customer.setCareer(careerClient.getText());
        customer.setCompanyAddress(compNameClient.getText()+" "+compAddClient.getText()+" "+compCountryClient.getText()+" "+compZipCodeClient.getText());
        customer.setWork_Tel(workTelClient.getText());
        //moreInfo
        customer.setMemberStatus(memberChioce.isSelected() ? memberChioce.getText() : notMemberChioce.getText());
        customer.setDisease(underlyingDisease.getText());
        customer.setFoodAllergy(foodAllergy.getText());
        customer.setEatBeef(eatBeefY.isSelected() ? eatBeefY.getText() : eatBeefN.getText());
        customer.setMoreDetail(moreDetail.getText());
        customer.setChannel(getChannelList().toString());
    }


}
