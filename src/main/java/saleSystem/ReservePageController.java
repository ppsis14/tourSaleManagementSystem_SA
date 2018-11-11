package saleSystem;
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
import java.util.ResourceBundle;

import static databaseConnection.MongoDBConnect.reserve_card;

public class ReservePageController implements Initializable {


    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;
    @FXML private ChoiceBox<?> tourCodeChoice;
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

    public void setText(String txt){
        reserveCode.setText(txt);
    }

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


        // insert data by MongoDB
        MongoDBConnect.getMongoClient();

        BasicDBObject clientProfile = new BasicDBObject()
                //.append("Tour_ID",)
                .append("Reservation_code",reserveCode.getText())
                .append("Departure_date",departureDate.getEditor().getText())
                .append("TitlenameTH",nameTitleTHClient.getSelectionModel().getSelectedItem())
                .append("FirstNameTH",firstNameTHClient.getText())
                .append("LastNameTH",lastNameTHClient.getText())
                .append("TitlenameENG",nameTitleENClient.getSelectionModel().getSelectedItem())
                .append("FirstNameENG",firstNameENClient.getText())
                .append("LastNameENG",lastNameENClient.getText())
                .append("TitlenameOld",oldNameTitleTHClient.getSelectionModel().getSelectedItem())
                .append("FirstnameOld",oldFirstNameClient.getText())
                .append("LastnameOld",oldLastNameClient.getText())
                .append("Gender",genderChoice.getSelectionModel().getSelectedItem())
                .append("Age",age.getText())
                .append("Date_of_birth",dateOfBirthClient.getEditor().getText())
                .append("Passport_no",passportClient.getText())
                .append("Expire_passport_date",expPassportDate.getEditor().getText());

        BasicDBObject clientContact = new BasicDBObject()
                .append("Full_home_address",homeAddClient.getText())
                .append("Home_country",countryClient.getText())
                .append("Home_zip_code",homeZipCodeClient.getText())
                .append("Cell_phone",cellphoneClient.getText())
                .append("Home_Tel",homeTelClient.getText())
                .append("Fax",homeFaxClient.getText())
                .append("Email_address",emailClient.getText())
                .append("Career",careerClient.getText())
                .append("Company_name",compNameClient.getText())
                .append("Company_address",compAddClient.getText())
                .append("Company_country",compCountryClient.getText())
                .append("Company_zip_code", compZipCodeClient.getText())
                .append("Work_Tel",workTelClient.getText());

        BasicDBObject moreInfo = new BasicDBObject()
                .append("Member_status",memberChioce.getTypeSelector())
                .append("Disease",underlyingDisease.getText())
                //.append("Food_allergy".foodAllergy.getText())
                //.append("Eat_beef",eatBeefN.getTypeSelector())
                .append("More_detail",moreDetail.getText());
                //.append("Channel",)

        reserve_card.insert(clientProfile);
        System.out.println(reserve_card);

        //show list inserted on display
        Cursor cursor = reserve_card.find();
        while (cursor.hasNext())
            System.out.println(cursor.next());
        System.out.println("MongoDB: Reservation saved!");



        //insert data by SQLite
        Connection connection = DbConnect.getConnection();
        String sql = "insert into reserve_card_database (Reservation_code, Departure_date, TitlenameTH, FirstNameTH, LastNameTH, TitlenameENG, FirstNameENG, LastNameENG ," +
                "TitlenameOld, FirstnameOld, LastnameOld, Gender, Age, Date_of_birth, Passport_no,Expire_passport_date" +
                ") values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?)";
        PreparedStatement pst;

        try {

            pst = connection.prepareStatement(sql);
            pst.setString(1,reserveCode.getText());
            pst.setString(2,departureDate.getEditor().getText());
            pst.setString(3,nameTitleTHClient.getSelectionModel().getSelectedItem());
            pst.setString(4,firstNameTHClient.getText());
            pst.setString(5,lastNameTHClient.getText());
            pst.setString(6,nameTitleENClient.getSelectionModel().getSelectedItem());
            pst.setString(7,firstNameENClient.getText());
            pst.setString(8,lastNameENClient.getText());
            pst.setString(9,oldNameTitleTHClient.getSelectionModel().getSelectedItem());
            pst.setString(10,oldFirstNameClient.getText());
            pst.setString(11,oldLastNameClient.getText());
            pst.setString(12,genderChoice.getSelectionModel().getSelectedItem());
            pst.setString(13,age.getText());
            pst.setString(14,dateOfBirthClient.getEditor().getText());
            pst.setString(15,passportClient.getText());
            pst.setString(16,expPassportDate.getEditor().getText());
            pst.executeUpdate();

            System.out.println("SQLite: Reservation data saved!");
        } catch (SQLException e) {
            System.err.println("SQLite: Got an exception! ");
            System.out.println(e.getMessage());
        }finally {
            connection.close();
        }

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
