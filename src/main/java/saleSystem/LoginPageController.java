package saleSystem;

import com.jfoenix.controls.JFXButton;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCursor;
import databaseConnection.DbConnect;
import databaseConnection.MongoDBConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static databaseConnection.MongoDBConnect.employee;
import static databaseConnection.MongoDBConnect.member;


public class LoginPageController implements Initializable {

    @FXML private TextField username;
    @FXML private JFXButton cancelButton;
    @FXML private JFXButton loginButton;
    @FXML private PasswordField password;
    @FXML private Label showErrorLogin;

    @FXML
    public void handleCancelButton(ActionEvent event) {
        username.clear();
        password.clear();
        showErrorLogin.setText("");
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void handleLoginButton(ActionEvent event) throws IOException, SQLException {
            //MongoDB
            MongoDBConnect.getMongoClient();

            BasicDBObject andQuery = new BasicDBObject();
            List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
            obj.add(new BasicDBObject("id",username.getText()));
            obj.add(new BasicDBObject("password",password.getText()));
            andQuery.put("$and", obj);

            DBCursor cursor = employee.find(andQuery);

            if (cursor.hasNext()) {

                //if login success -> load home windows
                loginButton.getScene().getWindow().hide();
                SaleManagementUtil.loadWindow(getClass().getResource("/homePage.fxml"), "Onvacation - Home", null);
            }
            else{
                showErrorLogin.setText("Username or Password is not correct");
                username.clear();
                password.clear();
            }
/*      //SQLite
        Connection connection = DbConnect.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = null;


        if (connection != null){

            resultSet = statement.executeQuery("select * from employee_database where ID" + " = '" + username.getText() + "' and Password = '" + password.getText() +"'");

            if(resultSet.next()) {
                //if login success -> load home windows
                loginButton.getScene().getWindow().hide();
                SaleManagementUtil.loadWindow(getClass().getResource("/homePage.fxml"), "Onvacation - Home", null);
                connection.close();
            }
        else{
                showErrorLogin.setText("Username or Password is not correct");
                username.clear();
                password.clear();
            }*/



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //EX. setup id,password

        MongoDBConnect.getMongoClient();
        BasicDBObject doc1 = new BasicDBObject()
                .append("id","5910406451")
                .append("password","1234");

        BasicDBObject doc2 = new BasicDBObject()
                .append("id","5910401033")
                .append("password","1123456");
        employee.insert(doc1);
        employee.insert(doc2);

        Cursor cursor = employee.find();
        while (cursor.hasNext())
            System.out.println(cursor.next());
        username.setVisible(true);
        password.setVisible(true);
    }
}




