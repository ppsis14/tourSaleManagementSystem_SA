package saleSystem;

import com.jfoenix.controls.JFXButton;
import databaseConnection.DbConnect;
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
import java.util.ResourceBundle;


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

        Connection connection = DbConnect.getInstance().getConnection();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from employee_database where ID" + " = '" + username.getText() + "' and Password = '" + password.getText() +"'");


        if(resultSet.next()){
            //load windows
            loginButton.getScene().getWindow().hide();
            SaleManagementUtil.loadWindow(getClass().getResource("/homePage.fxml"), "Onvacation - Home", null);

            //passing name login
            HomePageController homePageController = new HomePageController();
            //homePageController = loader.getController();
            homePageController.setLoginName(username.getText());
        }
        else{
            showErrorLogin.setText("Username or Password is not correct");
            username.clear();
            password.clear();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setVisible(true);
        password.setVisible(true);
    }
}




