package tourSaleManagementController;

import Table.Customer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tourSaleManagementSystemUtil.DisplayGUIUtil;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.loginEmployee;
import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;

public class CustomerManagementController implements Initializable {
    @FXML private Label loginNameLabel;
    @FXML private TextField searchCustomer;
    @FXML private JFXButton clearSearchBtn;
    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, Integer> customerIDColumn;
    @FXML private TableColumn<Customer, String> titleNameColumn;
    @FXML private TableColumn<Customer, String> firstNameColumn;
    @FXML private TableColumn<Customer, String> lastNameColumn;
    @FXML private TableColumn<Customer, String> passportNoColumn;
    @FXML private TableColumn<Customer, String> dateOfBirthColumn;
    @FXML private TableColumn<Customer, String> phoneNumberColumn;
    @FXML private TableColumn<Customer, String> addressColumn;
    @FXML private JFXButton editCustomerBtn;
    @FXML private JFXButton refreshCustomerBtn;
    @FXML private JFXButton deleteCustomerBtn;
    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;

    ObservableList<Customer> obListCustomer = FXCollections.observableList(manageableDatabase.getAllCustomer());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayGUIUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
        loginNameLabel.setText(loginEmployee.getFirstName()+" "+loginEmployee.getLastName()+" [ "+loginEmployee.getPosition().toUpperCase()+" ]");
        showTableView(obListCustomer);  //show data on table view

        setSearchCustomer();


    }

    @FXML
    void handleDeleteCustomerBtn(ActionEvent event) {
        Customer deleteCustomer = customerTable.getSelectionModel().getSelectedItem();  //select item for delete
        if (deleteCustomer != null){
            Alert alertConfirmToDeleteCustomer = new Alert(Alert.AlertType.CONFIRMATION);
            alertConfirmToDeleteCustomer.setTitle("Confirmation Dialog");
            alertConfirmToDeleteCustomer.setHeaderText(null);
            alertConfirmToDeleteCustomer.setContentText("Do you want to delete this customer?");
            Optional<ButtonType> action = alertConfirmToDeleteCustomer.showAndWait();
            if (action.get() == ButtonType.OK){
                // code for delete reservation
                if (deleteCustomer != null) {   //when user select data

                    manageableDatabase.deleteData(deleteCustomer);  //delete in database
                    obListCustomer.remove(customerTable.getSelectionModel().getSelectedItem()); //delete on table view
                }
            }
        }
        else {
            Alert alertWarningBeforeDeleteCus = new Alert(Alert.AlertType.WARNING);
            alertWarningBeforeDeleteCus.setTitle("Warning Dialog");
            alertWarningBeforeDeleteCus.setHeaderText(null);
            alertWarningBeforeDeleteCus.setContentText("Please select item before delete");
            Optional<ButtonType> deleteCustomerAction = alertWarningBeforeDeleteCus.showAndWait();
        }

    }


    @FXML
    void handleEditCustomerBtn(ActionEvent event) {

        Customer editCustomer = customerTable.getSelectionModel().getSelectedItem();

        if(editCustomer != null) {
            editCustomerBtn.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/editCustomer.fxml"));
                Parent parent = (Parent) loader.load();
                EditCustomerPageController editCustomerPageController = loader.getController();
                editCustomerPageController.setCustomer(editCustomer);
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.show();
                stage.setScene(new Scene(parent));
                stage.centerOnScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alertWarningBeforeEdit = new Alert(Alert.AlertType.WARNING);
            alertWarningBeforeEdit.setTitle("Warning Dialog");
            alertWarningBeforeEdit.setHeaderText(null);
            alertWarningBeforeEdit.setContentText("Please select item before edit");
            Optional<ButtonType> editCustomerAction = alertWarningBeforeEdit.showAndWait();
        }
    }

    @FXML
    void handleClearSearchBtn(ActionEvent event) {
        searchCustomer.clear();

    }

    @FXML
    void handleRefreshCustomerBtn(ActionEvent event) {
        Alert alertShowInformationIsUpdate = new Alert(Alert.AlertType.INFORMATION);
        alertShowInformationIsUpdate.setTitle("Confirmation Dialog");
        alertShowInformationIsUpdate.setHeaderText(null);
        alertShowInformationIsUpdate.setContentText("Customer information is update!");
        Optional<ButtonType> action = alertShowInformationIsUpdate.showAndWait();
        if (action.get() == ButtonType.OK){
            // code for delete reservation
            obListCustomer = FXCollections.observableList(manageableDatabase.getAllCustomer());
            this.showTableView(obListCustomer);
        }

    }

    public void showTableView(ObservableList<Customer> obListCustomer){

        //find data base for show on table view.

        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        titleNameColumn.setCellValueFactory(new PropertyValueFactory<>("titleNameTH"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstNameTH"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastNameTH"));
        passportNoColumn.setCellValueFactory(new PropertyValueFactory<>("passport_no"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("cell_phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("contactAddress"));

        customerTable.setItems(obListCustomer);
    }

    public void setSearchCustomer(){
        FilteredList<Customer> filteredData = new FilteredList<>(obListCustomer, e -> true);
        searchCustomer.setOnKeyPressed(event -> {
            searchCustomer.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Customer>) customer -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (customer.getCustomerID().contains(newValue)){
                        return true;
                    }
                    else if (customer.getFirstNameENG().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if (customer.getLastNameENG().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if (customer.getFirstNameTH().contains(newValue)){
                        return true;
                    }
                    else if (customer.getLastNameTH().contains(newValue)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Customer> sortData = new SortedList<>(filteredData);
            sortData.comparatorProperty().bind(customerTable.comparatorProperty());
            customerTable.setItems(sortData);
        });
    }

}

