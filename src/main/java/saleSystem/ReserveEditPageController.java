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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static databaseConnection.MongoDBConnect.reserve_card;

public class ReserveEditPageController implements Initializable {
    @FXML private TextField reserveCodeED;
    @FXML private ChoiceBox<String> tourCodeChioceED;
    @FXML private DatePicker departureDateED;
    @FXML private ChoiceBox<String> nameTitleTHClientED;
    @FXML private TextField firstNameTHClientED;
    @FXML private TextField lastNameTHClientED;
    @FXML private ChoiceBox<String> nameTitleENClientED;
    @FXML private TextField firstNameENClientED;
    @FXML private TextField lastNameENClientED;
    @FXML private ChoiceBox<String> oldNameTitleTHClientED;
    @FXML private TextField oldFirstNameClientED;
    @FXML private TextField oldLastNameClientED;
    @FXML private ChoiceBox<String> genderChoiceED;
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

    private String reservCode;

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
    public void handleUpdateClientBtnED(ActionEvent event)  throws SQLException {
        updateDataByMongoDB();



    }

    public ArrayList<String> getChannelList(){
        ArrayList<String> channelList = new ArrayList<>();

        if (BangkokbizsnewsED.isSelected())
            channelList.add(BangkokbizsnewsED.getText());
        if (DailynewsED.isSelected())
            channelList.add(DailynewsED.getText());
        if (KomchadluekED.isSelected())
            channelList.add(KomchadluekED.getText());
        if (websiteED.isSelected())
            channelList.add(websiteED.getText());
        if (eNewsED.isSelected())
            channelList.add(eNewsED.getText());
        if (smsED.isSelected())
            channelList.add(smsED.getText());
        if (tvAdsED.isSelected())
            channelList.add(tvAdsED.getText());
        if (channelList==null)
            channelList.add(others.getText());

        return channelList;

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

    public void setReservCode(String code){
        this.reservCode = code;
        System.out.println(reservCode);
        setUpReservationPage();

    }

    public void setUpReservationPage(){
        Connection connection = DbConnect.getConnection();
        try {
            System.out.println(reservCode);
            String sql = "select * from reserve_card_database where Reservation_code = '"+ this.reservCode +"' ";
            ResultSet rs = connection.createStatement().executeQuery(sql);

            if(rs != null) {

                reserveCodeED.setText(rs.getString("Reservation_code"));
                departureDateED.setAccessibleText(rs.getString("Departure_date"));
                nameTitleTHClientED.setValue(rs.getString("TitlenameTH"));
                firstNameTHClientED.setText(rs.getString("FirstNameTH"));
                lastNameTHClientED.setText(rs.getString("LastNameTH"));
                nameTitleENClientED.setValue(rs.getString("TitlenameENG"));
                firstNameENClientED.setText(rs.getString("FirstNameENG"));
                lastNameENClientED.setText(rs.getString("LastNameENG"));
                oldNameTitleTHClientED.setValue(rs.getString("TitlenameOld"));
                oldFirstNameClientED.setText(rs.getString("FirstnameOld"));
                oldLastNameClientED.setText(rs.getString("LastnameOld"));
                genderChoiceED.setValue(rs.getString("Gender"));
                ageED.setText(rs.getString("Age"));
                dateOfBirthClientED.setAccessibleText(rs.getString("Date_of_birth"));
                passportClientED.setText(rs.getString("Passport_no"));
                expPassportDateED.getEditor().setAccessibleText(rs.getString("Expire_passport_date"));
                //clientContact
                homeAddClientED.setText(rs.getString("Full_home_address"));
                countryClientED.setText(rs.getString("Home_country"));
                homeZipCodeClientED.setText(rs.getString("Home_zip_code"));
                cellphoneClientED.setText(rs.getString("Cell_phone"));
                homeTelClientED.setText(rs.getString("Home_Tel"));
                homeFaxClientED.setText(rs.getString("Fax"));
                emailClientED.setText(rs.getString("Email_address"));
                careerClientED.setText(rs.getString("Career"));
                compNameClientED.setText(rs.getString("Company_name"));
                compAddClientED.setText(rs.getString("Company_address"));
                compCountryClientED.setText(rs.getString("Company_country"));
                compZipCodeClientED.setText(rs.getString("Company_zip_code"));
                workTelClientED.setText(rs.getString("Work_Tel"));
                //moreInfo
                if (rs.getString("Member_status") == "Not member") {
                    notMemberChioceED.setSelected(true);
                    memberChioceED.setSelected(false);
                } else {
                    memberChioceED.setSelected(true);
                    notMemberChioceED.setSelected(false);
                }
                underlyingDiseaseED.setText("Disease");
                foodAllergyED.setText(rs.getString("Food_allergy"));

                if (rs.getString("Eat_beef") == "No") {
                    eatBeefNED.setSelected(true);
                    eatBeefYED.setSelected(false);
                } else {
                    eatBeefYED.setSelected(true);
                    eatBeefNED.setSelected(false);
                }

                moreDetailED.setText(rs.getString("More_detail"));

                ArrayList<String> channel = (ArrayList<String>) rs.getArray("Channel");
                for (String c : channel) {

                    if (c.equals("Bangkokbizsnews"))
                        BangkokbizsnewsED.setSelected(true);
                    if (c.equals("Dailynews"))
                        DailynewsED.setSelected(true);
                    if (c.equals("Komchadluek"))
                        KomchadluekED.setSelected(true);
                    if (c.equals("website"))
                        websiteED.setSelected(true);
                    if (c.equals("eNews"))
                        eNewsED.setSelected(true);
                    if (c.equals("sms"))
                        smsED.setSelected(true);
                    if (c.equals("tvAds"))
                        tvAdsED.setSelected(true);
                    if (c.equals("Others"))
                        others.setSelected(true);
                }

            }connection.close();
            } catch (SQLException e) {
                System.err.println("SQLite: Got an exception! ");
                System.out.println(e.getMessage());
            }
     }

     public void updateDataByMongoDB(){
         // update data by MongoDB
         MongoDBConnect.getMongoClient();

         BasicDBObject oldDoc = new BasicDBObject().append("Reservation_code",reservCode);
         BasicDBObject newEdit = new BasicDBObject();

         newEdit.append("$set" , new BasicDBObject()
                 //.append("Tour_ID",)
                 .append("Reservation_code",reserveCodeED.getText())
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
                 .append("Expire_passport_date",expPassportDateED.getEditor().getText())
                 //clientContact
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
                 .append("Work_Tel",workTelClientED.getText())
                 //moreInfo
                 .append("Member_status",memberChioceED.isSelected() ? memberChioceED.getText() : notMemberChioceED.getText())
                 .append("Disease",underlyingDiseaseED.getText())
                 .append("Food_allergy",foodAllergyED.getText())
                 .append("Eat_beef",eatBeefYED.isSelected() ? eatBeefYED.getText() : eatBeefNED.getText())
                 .append("More_detail",moreDetailED.getText())
                 .append("Channel",getChannelList().toString()));

         reserve_card.update(oldDoc,newEdit);

         //show list update on display
         Cursor cursor = reserve_card.find();
         while (cursor.hasNext())
             System.out.println(cursor.next());
         System.out.println("MongoDB: Reservation update!");

     }

     public void updateDataBySqlite(){

     }
}
