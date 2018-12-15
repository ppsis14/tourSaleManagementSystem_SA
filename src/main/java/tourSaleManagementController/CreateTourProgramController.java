package tourSaleManagementController;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateTourProgramController implements Initializable {
    @FXML private StackPane rootPane;
    @FXML private AnchorPane anchorPane;
    @FXML private TextField tourIDCountry;
    @FXML private TextField tourIDDay;
    @FXML private TextField tourIDCode;
    @FXML private ComboBox<String> statusChoice;
    @FXML private TextField tourName;
    @FXML private Label departureDate;
    @FXML private DatePicker dateOfBirth1;
    @FXML private DatePicker returnDate;
    @FXML private TextField amountSeats;
    @FXML private TextField availableSeats;
    @FXML private TextField tourPrice;
    @FXML private DatePicker depositIVDate;
    @FXML private DatePicker invoiceDate;
    @FXML private JFXButton addTourProgram;

    @FXML
    void handleAddTourProgramBtn(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SetTourSaleSystemDataUtil.setStatusTourProgram(statusChoice);

    }
}
