package saleSystem;
import com.mongodb.Cursor;
import databaseConnection.MongoDBConnect;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import com.mongodb.BasicDBObject;
import com.sun.net.httpserver.Authenticator;
import databaseConnection.DbConnect;
import databaseConnection.MongoDBConnect;
import databaseConnection.SQLiteJDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static databaseConnection.MongoDBConnect.employee;
import static databaseConnection.MongoDBConnect.member;
import static databaseConnection.MongoDBConnect.reserve_card;

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
    @FXML private TextField lastNameENClient;
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

    }
    @FXML
    public void handleAddClientBtn(ActionEvent event) throws SQLException {
        //record reservation
        MongoDBConnect.getMongoClient();

        BasicDBObject doc = new BasicDBObject()
                .append("Name_titleTH",nameTitleTHClient.getSelectionModel().getSelectedItem())
                .append("FirstNameTH",firstNameTHClient.getText())
                .append("LastNameTH",lastNameTHClient.getText())
                .append("Name_titleENG",nameTitleENClient.getSelectionModel().getSelectedItem())
                .append("FirstNameENG",firstNameENClient.getText())
                .append("LastNameENG",lastNameENClient.getText())
                .append("Old_name",oldNameTitleTHClient.getSelectionModel().getSelectedItem())
                .append("Old_FirstnameTH",oldFirstNameClient.getText())
                .append("Old_LastnameTH",oldLastNameClient.getText())
                .append("Gender",genderChoice.getSelectionModel().getSelectedItem())
                .append("Age",age.getText())
                .append("Date_of_birth",dateOfBirthCilent.getEditor().getText())
                .append("Expire_passport_date",expPassportDate.getEditor().getText())
                .append("Passport_no",passportClient.getText());

        reserve_card.insert(doc);
        System.out.println(reserve_card);

        Cursor cursor = reserve_card.find();
        while (cursor.hasNext())
            System.out.println(cursor.next());
        System.out.println("Reserve card inserted");


        /*Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite::resource:sale_system_database.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO test (fth, lth, fen, len, pass)" +
                    "VALUES ('"+firstNameTHClient.getText()+"','"+lastNameTHClient.getText()+"','"+firstNameENClient.getText()+"','"+lastNameENClient.getText()+"'," +
                    "'"+passportClient.getText()+"')";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
        */
        /*
        Connection connection = DbConnect.getInstance().getConnection();
        PreparedStatement preparedStatement;
//        String sql = "insert into test (fth, lth, fen, len, pass)"+
//                "values ('"+firstNameTHClient.getText()+"','"+lastNameTHClient.getText()+"','"+firstNameENClient.getText()+"','"+lastNameENClient.getText()+"'," +
//                "'"+passportClient.getText()+"')";

        String sql = "insert into test (fth, lth, fen, len, pass) values(?, ?, ?, ?, ?)";

        try {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,firstNameTHClient.getText());
            preparedStatement.setString(2,lastNameTHClient.getText());
            preparedStatement.setString(3,firstNameENClient.getText());
            preparedStatement.setString(4,lastNameENClient.getText());
            preparedStatement.setString(5,passportClient.getText());
            //preparedStatement.setString(6,expireDate.getEditor().getText());

            preparedStatement.executeUpdate();
            System.out.println("saved!");
        } catch (SQLException e) {
            System.err.println("Got an exception! ");
            System.out.println(e.getMessage());
        }finally {
            connection.close();
        }

        //        ResultSet resultSet = statement.executeQuery("insert into reserve_card_database (FirstnameTH,LastnameTH,FirstnameENG,LastnameENG,Passport_no)"+
//                "values ('"+firstNameTHClient.getText()+"','"+lastNameTHClient.getText()+"','"+firstNameENClient.getText()+"','"+lastNameENClient.getText()+"'," +
//                "'"+passportClient.getText()+"')");

*/

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
