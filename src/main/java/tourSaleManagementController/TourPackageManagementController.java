package tourSaleManagementController;

import Table.TourPackage;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tourSaleManagementSystemUtil.DisplayGUIUtil;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static java.lang.String.format;
import static tourSaleManagementSystemUtil.DisplayGUIUtil.loginEmployee;
import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;

public class TourPackageManagementController implements Initializable {
    @FXML private Label loginNameLabel;
    @FXML private TextField searchTourPackage;
    @FXML private JFXButton clearSearchBtn;
    @FXML private TableView<TourPackage> tourPackageTable;
    @FXML private TableColumn<TourPackage, String> tourIDColumn;
    @FXML private TableColumn<TourPackage, String> tourNameColumn;
    @FXML private TableColumn<TourPackage, Integer> tourPriceColumn;
    @FXML private TableColumn<TourPackage, String> departureDateColumn;
    @FXML private TableColumn<TourPackage, String> returnDateColumn;
    @FXML private TableColumn<TourPackage, String> depositPaidColumn;
    @FXML private TableColumn<TourPackage, String> paidFinalColumn;
    @FXML private TableColumn<TourPackage, Integer> amountSeatColumn;
    @FXML private TableColumn<TourPackage, Integer> availableSeatColumn;
    @FXML private TableColumn<TourPackage, String> tourStatusColumn;
    @FXML private JFXButton createTourProgramBtn;
    @FXML private JFXButton editTourProgramBtn;
    @FXML private JFXButton refreshTourProgramBtn;
    @FXML private JFXButton deleteTourProgramBtn;
    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;

    ObservableList<TourPackage> obListTourPackage = FXCollections.observableList(manageableDatabase.getAllTourPackage());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayGUIUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
        loginNameLabel.setText(loginEmployee.getFirstName()+" "+loginEmployee.getLastName()+" [ "+loginEmployee.getPosition().toUpperCase()+" ]");
        showTableView(obListTourPackage);  //show data on table view
        setSearchCustomer();
    }

    @FXML
    void handleClearSearchBtn(ActionEvent event) {
        searchTourPackage.clear();
    }

    @FXML
    void handleCreateTourPackageBtn(ActionEvent event) throws IOException {
        //createTourProgramBtn.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/createTourPackage.fxml"));
        Parent parent = (Parent) loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.show();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();

    }

    @FXML
    void handleDeleteTourPackageBtn(ActionEvent event) {
        TourPackage selectTourPackage = tourPackageTable.getSelectionModel().getSelectedItem(); //select item for delete
        if(selectTourPackage != null) {
            Alert alertConfirmToDeleteTourProgram = new Alert(Alert.AlertType.CONFIRMATION);
            alertConfirmToDeleteTourProgram.setTitle("Confirmation Dialog");
            alertConfirmToDeleteTourProgram.setHeaderText(null);
            alertConfirmToDeleteTourProgram.setContentText("Do you want to delete this tour package?");
            Optional<ButtonType> action = alertConfirmToDeleteTourProgram.showAndWait();
            if (action.get() == ButtonType.OK) {
                // code for delete reservation
                manageableDatabase.deleteData(selectTourPackage);   //delete tour package in DB
                obListTourPackage.remove(tourPackageTable.getSelectionModel().getSelectedItem()); //delete on table view

            }
        }
        else {
            Alert alertWarningBeforeDeleteTour = new Alert(Alert.AlertType.WARNING);
            alertWarningBeforeDeleteTour.setTitle("Warning Dialog");
            alertWarningBeforeDeleteTour.setHeaderText(null);
            alertWarningBeforeDeleteTour.setContentText("Please select item before delete");
            Optional<ButtonType> deleteTourAction = alertWarningBeforeDeleteTour.showAndWait();
        }

    }

    @FXML
    void handleEditTourPackageBtn(ActionEvent event) {
        TourPackage editTourProgram = tourPackageTable.getSelectionModel().getSelectedItem();

        if(editTourProgram != null) {
            //editTourProgramBtn.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/editTourPackage.fxml"));
                Parent parent = (Parent) loader.load();
                EditTourPackageController editTourProgramController = loader.getController();
                editTourProgramController.setTourPackage(editTourProgram);
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.show();
                stage.setScene(new Scene(parent));
                stage.centerOnScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alertWarningBeforeEditTour = new Alert(Alert.AlertType.WARNING);
            alertWarningBeforeEditTour.setTitle("Warning Dialog");
            alertWarningBeforeEditTour.setHeaderText(null);
            alertWarningBeforeEditTour.setContentText("Please select item before delete");
            Optional<ButtonType> editTourAction = alertWarningBeforeEditTour.showAndWait();
        }

    }

    @FXML
    void handleRefreshTourPackageBtn(ActionEvent event) {
        Alert alertShowInformationIsUpdate = new Alert(Alert.AlertType.INFORMATION);
        alertShowInformationIsUpdate.setTitle("Confirmation Dialog");
        alertShowInformationIsUpdate.setHeaderText(null);
        alertShowInformationIsUpdate.setContentText("Tour package information is update");
        Optional<ButtonType> action = alertShowInformationIsUpdate.showAndWait();
        if (action.get() == ButtonType.OK){
            // code for delete reservation
            obListTourPackage = FXCollections.observableList(manageableDatabase.getAllTourPackage());
            this.showTableView(obListTourPackage);
        }

    }

    public void showTableView(ObservableList<TourPackage> obListTourProgram){
        //find data base for show on table view.
        tourIDColumn.setCellValueFactory(new PropertyValueFactory<>("tourID"));
        tourNameColumn.setCellValueFactory(new PropertyValueFactory<>("tourName"));
        tourPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        departureDateColumn.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        depositPaidColumn.setCellValueFactory(new PropertyValueFactory<>("depositDate"));
        paidFinalColumn.setCellValueFactory(new PropertyValueFactory<>("arrearsDate"));
        amountSeatColumn.setCellValueFactory(new PropertyValueFactory<>("amountSeat"));
        availableSeatColumn.setCellValueFactory(new PropertyValueFactory<>("availableSeat"));
        tourStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        tourStatusColumn.setCellFactory(new Callback<TableColumn<TourPackage, String>, TableCell<TourPackage, String>>() {
            @Override
            public TableCell<TourPackage, String> call(TableColumn<TourPackage, String> param) {
                return new TableCell<TourPackage, String>(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty){
                            setText(format((item)));
                            if (item.equals("close")) setTextFill(Color.RED);
                            else if (item.equals("open")) setTextFill(Color.GREEN);

                        }else setText(null);
                    }
                };
            }
        });

        tourPackageTable.setItems(obListTourProgram);
    }

    public void setSearchCustomer(){
        FilteredList<TourPackage> filteredData = new FilteredList<>(obListTourPackage, e -> true);
        searchTourPackage.setOnKeyPressed(event -> {
            searchTourPackage.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super TourPackage>) tourPackage -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String upperCaseFilter = newValue.toUpperCase();
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (tourPackage.getTourID().contains(upperCaseFilter)){
                        return true;
                    }
                    else if (tourPackage.getTourName().contains(upperCaseFilter)){
                        return true;
                    }
                    else if (tourPackage.getStatus().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<TourPackage> sortData = new SortedList<>(filteredData);
            sortData.comparatorProperty().bind(tourPackageTable.comparatorProperty());
            tourPackageTable.setItems(sortData);
        });
    }


}
