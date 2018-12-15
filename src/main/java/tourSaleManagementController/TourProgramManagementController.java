package tourSaleManagementController;

import Table.Customer;
import Table.Invoice;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;
import sun.tracing.dtrace.DTraceProviderFactory;
import tourSaleManagementSystemUtil.DisplayGUIUtil;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;

public class TourProgramManagementController implements Initializable {
    @FXML private Label loginNameLabel;
    @FXML private TextField searchTourProgram;
    @FXML private JFXButton clearSearchBtn;
    @FXML private TableView<TourPackage> tourProgramTable;
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
    @FXML private JFXButton createTourProgram;
    @FXML private JFXButton editTourProgram;
    @FXML private JFXButton updateToutProgram;
    @FXML private JFXButton deleteTourProgram;
    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;

    ObservableList<TourPackage> obListTourProgram = FXCollections.observableList(manageableDatabase.getAllTourPackage());
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayGUIUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));

        showTableView(obListTourProgram);  //show data on table view

        setSearchCustomer();
    }

    @FXML
    void handleClearSearchBtn(ActionEvent event) {
        searchTourProgram.clear();
    }

    @FXML
    void handleCreateTourProgramBtn(ActionEvent event) {
        createTourProgram.getScene().getWindow().hide();
        DisplayGUIUtil.loadWindow(getClass().getResource("/createTourProgram.fxml"), "Create Tour Program");

    }

    @FXML
    void handleDeleteTourProgramBtn(ActionEvent event) {
        Alert alertConfirmToDeleteTourProgram = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmToDeleteTourProgram.setTitle("Confirmation Dialog");
        alertConfirmToDeleteTourProgram.setHeaderText(null);
        alertConfirmToDeleteTourProgram.setContentText("Do you want to delete this tour Program?");
        Optional<ButtonType> action = alertConfirmToDeleteTourProgram.showAndWait();
        if (action.get() == ButtonType.OK){
            // code for delete reservation
            TourPackage deleteTourProgram = tourProgramTable.getSelectionModel().getSelectedItem();  //select item for delete

            /*if (deleteTourProgram != null) {   //when user select data

                manageableDatabase.deleteData(deleteTourProgram);  //delete in database
                obListTourProgram.remove(tourProgramTable.getSelectionModel().getSelectedItem()); //delete on table view
            }*/
        }

    }

    @FXML
    void handleEditTourProgramBtn(ActionEvent event) {
        /*TourPackage editTourProgram = tourProgramTable.getSelectionModel().getSelectedItem();

        if(editTourProgram != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/editTourProgram.fxml"));
                Parent parent = (Parent) loader.load();
                EditTourProgramController editTourProgramController = loader.getController();
                editTourProgram.setTourProgram(editTourProgram);
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.show();
                stage.setScene(new Scene(parent));
                stage.centerOnScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

    }

    @FXML
    void handleUpdateToutProgramBtn(ActionEvent event) {
        /*Alert alertShowInformationIsUpdate = new Alert(Alert.AlertType.INFORMATION);
        alertShowInformationIsUpdate.setTitle("Confirmation Dialog");
        alertShowInformationIsUpdate.setHeaderText(null);
        alertShowInformationIsUpdate.setContentText("Customer Information is update!");
        Optional<ButtonType> action = alertShowInformationIsUpdate.showAndWait();
        if (action.get() == ButtonType.OK){
            // code for delete reservation
            obListTourProgram = FXCollections.observableList(manageableDatabase.getAllTourPackage());
            this.showTableView(obListTourProgram);
        }*/

    }

    public void showTableView(ObservableList<TourPackage> obListTourProgram){

        //find data base for show on table view.

        tourIDColumn.setCellValueFactory(new PropertyValueFactory<>("tourID"));
        tourNameColumn.setCellValueFactory(new PropertyValueFactory<>("tourName"));
        tourPriceColumn.setCellValueFactory(new PropertyValueFactory<>("tourPrice"));
        departureDateColumn.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        depositPaidColumn.setCellValueFactory(new PropertyValueFactory<>("depositPaidDate"));
        paidFinalColumn.setCellValueFactory(new PropertyValueFactory<>("paidFinalDate"));
        amountSeatColumn.setCellValueFactory(new PropertyValueFactory<>("amountSeat"));
        availableSeatColumn.setCellValueFactory(new PropertyValueFactory<>("availableSeat"));
        tourStatusColumn.setCellValueFactory(new PropertyValueFactory<>("tourStatus"));

        tourProgramTable.setItems(obListTourProgram);
    }

    public void setSearchCustomer(){
        FilteredList<TourPackage> filteredData = new FilteredList<>(obListTourProgram, e -> true);
        searchTourProgram.setOnKeyPressed(event -> {
            searchTourProgram.textProperty().addListener((observable, oldValue, newValue) -> {
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
            sortData.comparatorProperty().bind(tourProgramTable.comparatorProperty());
            tourProgramTable.setItems(sortData);
        });
    }


}
