package tourSaleManagementController;

import Table.Customer;
import Table.TourPackage;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;

public class EditTourProgramController implements Initializable {
    @FXML private StackPane rootPane;
    @FXML private AnchorPane anchorPane;
    @FXML private TextField tourIDCountry;
    @FXML private TextField tourIDDay;
    @FXML private TextField tourIDCode;
    @FXML private ComboBox<?> statusChoice;
    @FXML private TextField tourName;
    @FXML private Label departureDate;
    @FXML private DatePicker dateOfBirth1;
    @FXML private DatePicker returnDate;
    @FXML private TextField amountSeats;
    @FXML private TextField availableSeats;
    @FXML private TextField tourPrice;
    @FXML private DatePicker depositIVDate;
    @FXML private DatePicker invoiceDate;
    @FXML private JFXButton saveDataBtn;

    private TourPackage tourPackage = new TourPackage();

    @FXML
    void handleSaveDataBtn(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to save?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK){
            setTourProgramFromGUI();
            //manageableDatabase.updateData(tourPackage);    //update data to database
            System.out.println("Update Successful");

            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.close();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setCustomer(TourPackage editTourPackage){
        this.tourPackage = editTourPackage;
        showDataForEditTourPackage();
    }

    public void showDataForEditTourPackage(){

    }
    public void setTourProgramFromGUI(){

    }
}
