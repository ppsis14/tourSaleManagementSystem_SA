package saleSystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import com.mongodb.*;
import databaseConnection.MongoDBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static databaseConnection.MongoDBConnect.member;

public class MemberPageController implements Initializable {
    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;
    @FXML private TextField memberId;
    @FXML private JFXCheckBox diamondCheck;
    @FXML private JFXCheckBox goldCheck;
    @FXML private ChoiceBox<String> nameTitleTH;
    @FXML private TextField firstNameTH;
    @FXML private TextField lastNameTH;
    @FXML private ChoiceBox<String> nameTitleEN;
    @FXML private TextField firstNameEN;
    @FXML private TextField lastNameEN;
    @FXML private TextField idNumber;
    @FXML private TextField passportNumber;
    @FXML private DatePicker dateMember;
    @FXML private DatePicker dateApplyMember;
    @FXML private TextField timeToGoTravel;
    @FXML private TextField favoritePlace;
    @FXML private ComboBox<String> travelServiceChoice;
    @FXML private ComboBox<String> travelFocusingChoice;
    @FXML private TextField fullHomeAddr;
    @FXML private TextField homeCountry;
    @FXML private TextField homeZipCode;
    @FXML private TextField cellphone;
    @FXML private TextField homeTel;
    @FXML private TextField homeFax;
    @FXML private TextField emailAddr;
    @FXML private TextField career;
    @FXML private TextField compName;
    @FXML private TextField compAddr;
    @FXML private TextField compCountry;
    @FXML private TextField compZipCode;
    @FXML private TextField workTel;
    @FXML private TextField workFax;
    @FXML private JFXButton addMemberBtn;
    @FXML private TextField searchName;
    @FXML private TextField searchMemberId;
    @FXML private JFXButton searchBtnMember;
    @FXML private TableView<?> tableMember;
    @FXML private Button editBtnMember;
    @FXML private Button deleteBtnMember;
    @FXML private Button updateBtnMember;
    @FXML private Label showMemberDetail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));

        ObservableList<String> nameENTitleChoices = FXCollections.observableArrayList("Miss", "Mrs.", "Mr.", "Master", "Professor", "Assistant Professor", "Associate Professor");
        nameTitleEN.getSelectionModel().selectFirst();
        nameTitleEN.setValue("Miss");
        nameTitleEN.getItems().addAll(nameENTitleChoices);

        ObservableList<String> nameTHTitleChoices = FXCollections.observableArrayList("นางสาว", "นาง", "นาย", "เด็กหญิง", "เด็กชาย", "ศาตราจารย์", "ผู้ช่วยศาสตราจารย์", "รองศาสตราจารย์");
        nameTitleTH.getSelectionModel().selectFirst();
        nameTitleTH.setValue("นางสาว");
        nameTitleTH.getItems().addAll(nameTHTitleChoices);

        ObservableList<String> travelServices = FXCollections.observableArrayList("ชื่อเสียงของบริษัท", "โปรแกรมทัวร์", "ราคา", "การบริการของพนักงานขาย", "วันเวลาเดินทาง");
        travelServiceChoice.setValue("ชื่อเสียงของบริษัท");
        travelServiceChoice.setItems(travelServices);

        ObservableList<String> travelFocus = FXCollections.observableArrayList("ที่พัก", "ร้านอาหาร", "ยานพาหนะ", "ระยะเวลาชมสถานที่ท่องเที่ยว", "ไกด์/มัคคุเทศน์");
        travelFocusingChoice.setValue("ที่พัก");
        travelFocusingChoice.setItems(travelFocus);
    }

    @FXML
    void handleAddMember(ActionEvent event) {

        MongoDBConnect.getMongoClient();

        BasicDBObject doc = new BasicDBObject()
                .append("Member_ID",memberId.getText())
                .append("Name_titleTH",nameTitleTH.getSelectionModel().getSelectedItem())
                .append("FirstNameTH",firstNameTH.getText())
                .append("LastNameTH",lastNameTH.getText())
                .append("Name_titleENG",nameTitleEN.getSelectionModel().getSelectedItem())
                .append("FirstNameENG",firstNameEN.getText())
                .append("LastNameENG",lastNameEN.getText())
                .append("ID_number",idNumber.getText())
                .append("Passport_no",passportNumber.getText())
                .append("Date_of_birth",dateMember.getEditor().getText())
                .append("Date_of_apply",dateApplyMember.getEditor().getText());
        member.insert(doc);
        System.out.println(member);

        Cursor cursor = member.find();
        while (cursor.hasNext())
            System.out.println(cursor.next());
        System.out.println("inserted");

        /*
        Connection connection = DbConnect.getConnection();
        PreparedStatement preparedStatement;

        String sql = "INSERT INTO mem (a, b, c, d) values(?, ?, ?, ?)";

        try {

            System.out.println(firstNameTH.getText());
            System.out.println(lastNameTH.getText());
            System.out.println(firstNameEN.getText());
            System.out.println(lastNameEN.getText());

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,firstNameTH.getText());
            preparedStatement.setString(2,lastNameTH.getText());
            preparedStatement.setString(3,firstNameEN.getText());
            preparedStatement.setString(4,lastNameEN.getText());
            preparedStatement.executeUpdate();
            System.out.println("saved!");
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            System.err.println("Got an exception! ");
            System.out.println(e.getMessage());
        } finally {
        }*/
    }
    @FXML
    void handleDeleteBtnMember(ActionEvent event) {

    }

    @FXML
    void handleEditBtnMember(ActionEvent event) {

    }

    @FXML
    void handleSearchBtnMember(ActionEvent event) {

    }

    @FXML
    void handleUpdateBtnMember(ActionEvent event) {

    }
}
