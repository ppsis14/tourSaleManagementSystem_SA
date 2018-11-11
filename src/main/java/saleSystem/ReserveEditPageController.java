package saleSystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static databaseConnection.MongoDBConnect.reserve_card;

public class ReserveEditPageController implements Initializable {
    @FXML private TextField ReserveCodeED;
    @FXML private ChoiceBox<?> tourCodeChioceED;
    @FXML private DatePicker departureDateED;
    @FXML private ChoiceBox<?> nameTitleTHClientED;
    @FXML private TextField firstNameTHClientED;
    @FXML private TextField lastNameTHClientED;
    @FXML private ChoiceBox<?> nameTitleENClientED;
    @FXML private TextField firstNameENClientED;
    @FXML private TextField lastNameENClientED;
    @FXML private ChoiceBox<?> oldNameTitleTHClientED;
    @FXML private TextField oldFirstNameClientED;
    @FXML private TextField oldLastNameClientED;
    @FXML private ChoiceBox<?> genderChoiceED;
    @FXML private TextField ageED;
    @FXML private DatePicker dateOfBirthClientED;
    @FXML private TextField passportClientED;
    @FXML private DatePicker expPassportDateED;
    @FXML private TextField homeAddClientED;
    @FXML private TextField countryClientED;
    @FXML private TextField homeZipCodeClientED;
    @FXML private TextField cellphoneClientED;
    @FXML private TextField homeTelClientED;
    @FXML private TextField homeFaxClientED;
    @FXML private TextField emailClientED;
    @FXML private TextField careerClientED;
    @FXML private TextField compNameClientED;
    @FXML private TextField compAddClientED;
    @FXML private TextField compCountryClientED;
    @FXML private TextField workTelClientED;
    @FXML private TextField compZipCodeClientED;
    @FXML private JFXCheckBox notMemberChioceED;
    @FXML private JFXCheckBox memberChioceED;
    @FXML private TextField underlyingDiseaseED;
    @FXML private TextField foodAllergyED;
    @FXML private JFXCheckBox eatBeefYED;
    @FXML private JFXCheckBox eatBeefNED;
    @FXML private TextField moreDetailED;
    @FXML private JFXCheckBox BangkokbizsnewsED;
    @FXML private JFXCheckBox DailynewsED;
    @FXML private JFXCheckBox KomchadluekED;
    @FXML private JFXCheckBox websiteED;
    @FXML private JFXCheckBox eNewsED;
    @FXML private JFXCheckBox smsED;
    @FXML private JFXCheckBox tvAdsED;
    @FXML private JFXCheckBox others;
    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;
    @FXML private JFXButton updateClientBtnED;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));

        ObservableList<String> genderChoicesED = FXCollections.observableArrayList("Female","Male");
        genderChoiceED.getSelectionModel().selectFirst();
        genderChoiceED.setValue("Female");
        genderChoiceED.getItems().addAll(genderChoicesED);

        ObservableList<String> nameENTitleChoicesED = FXCollections.observableArrayList("Miss", "Mrs.", "Mr.", "Master", "Professor", "Assistant Professor", "Associate Professor");
        nameTitleENClientED.getSelectionModel().selectFirst();
        nameTitleENClientED.setValue("Miss");
        nameTitleENClientED.getItems().addAll(nameENTitleChoicesED);

        ObservableList<String> nameTHTitleChoicesED = FXCollections.observableArrayList("นางสาว", "นาง", "นาย", "เด็กหญิง", "เด็กชาย", "ศาตราจารย์", "ผู้ช่วยศาสตราจารย์", "รองศาสตราจารย์");
        nameTitleTHClientED.getSelectionModel().selectFirst();
        nameTitleTHClientED.setValue("นางสาว");
        nameTitleTHClientED.getItems().addAll(nameTHTitleChoicesED);

        ObservableList<String> oldNameTHTitleChoicesED = FXCollections.observableArrayList("นางสาว", "นาง", "นาย", "เด็กหญิง", "เด็กชาย", "ศาตราจารย์", "ผู้ช่วยศาสตราจารย์", "รองศาสตราจารย์");
        oldNameTitleTHClientED.getSelectionModel().selectFirst();
        oldNameTitleTHClientED.setValue("นางสาว");
        oldNameTitleTHClientED.getItems().addAll(oldNameTHTitleChoicesED);

        // set user login by call from database
        //showUserLogin.setText("Thikamporn Simud");
        //homePane.toFront();
        //reservePane.setVisible(false);

    }

    @FXML
    public void handleAddClientBtnED(ActionEvent event)  throws SQLException {
        //record reservation


        // insert data by MongoDB
        MongoDBConnect.getMongoClient();

        BasicDBObject clientProfile = new BasicDBObject()
                //.append("Tour_ID",)
                .append("Reservation_code",ReserveCodeED.getText())
                .append("Departure_date",departureDateED.getEditor().getText())
                .append("TitlenameTH",nameTitleTHClientED.getSelectionModel().getSelectedItem())
                .append("FirstNameTH",firstNameTHClientED.getText())
                .append("LastNameTH",lastNameTHClientED.getText())
                .append("TitlenameENG",nameTitleENClientED.getSelectionModel().getSelectedItem())
                .append("FirstNameENG",firstNameENClientED.getText())
                .append("LastNameENG",lastNameENClientED.getText())
                .append("TitlenameOld",oldNameTitleTHClientED.getSelectionModel().getSelectedItem())
                .append("FirstnameOld",oldFirstNameClientED.getText())
                .append("LastnameOld",oldLastNameClientED.getText())
                .append("Gender",genderChoiceED.getSelectionModel().getSelectedItem())
                .append("Age",ageED.getText())
                .append("Date_of_birth",dateOfBirthClientED.getEditor().getText())
                .append("Passport_no",passportClientED.getText())
                .append("Expire_passport_date",expPassportDateED.getEditor().getText());

        BasicDBObject clientContact = new BasicDBObject()
                .append("Full_home_address",homeAddClientED.getText())
                .append("Home_country",countryClientED.getText())
                .append("Home_zip_code",homeZipCodeClientED.getText())
                .append("Cell_phone",cellphoneClientED.getText())
                .append("Home_Tel",homeTelClientED.getText())
                .append("Fax",homeFaxClientED.getText())
                .append("Email_address",emailClientED.getText())
                .append("Career",careerClientED.getText())
                .append("Company_name",compNameClientED.getText())
                .append("Company_address",compAddClientED.getText())
                .append("Company_country",compCountryClientED.getText())
                .append("Company_zip_code", compZipCodeClientED.getText())
                .append("Work_Tel",workTelClientED.getText());

        BasicDBObject moreInfo = new BasicDBObject()
                .append("Member_status",memberChioceED.getTypeSelector())
                .append("Disease",underlyingDiseaseED.getText())
                //.append("Food_allergy".foodAllergy.getText())
                //.append("Eat_beef",eatBeefN.getTypeSelector())
                .append("More_detail",moreDetailED.getText());
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
            pst.setString(1,ReserveCodeED.getText());
            pst.setString(2,departureDateED.getEditor().getText());
            pst.setString(3,nameTitleTHClientED.getSelectionModel().getSelectedItem());
            pst.setString(4,firstNameTHClientED.getText());
            pst.setString(5,lastNameTHClientED.getText());
            pst.setString(6,nameTitleENClientED.getSelectionModel().getSelectedItem());
            pst.setString(7,firstNameENClientED.getText());
            pst.setString(8,lastNameENClientED.getText());
            pst.setString(9,oldNameTitleTHClientED.getSelectionModel().getSelectedItem());
            pst.setString(10,oldFirstNameClientED.getText());
            pst.setString(11,oldLastNameClientED.getText());
            pst.setString(12,genderChoiceED.getSelectionModel().getSelectedItem());
            pst.setString(13,ageED.getText());
            pst.setString(14,dateOfBirthClientED.getEditor().getText());
            pst.setString(15,passportClientED.getText());
            pst.setString(16,expPassportDateED.getEditor().getText());
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
