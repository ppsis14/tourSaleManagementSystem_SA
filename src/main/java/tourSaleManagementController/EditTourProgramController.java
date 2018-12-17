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
import tourSaleManagementSystemUtil.FormatConverter;
import tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil;

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
    @FXML private ComboBox<String> statusChoice;
    @FXML private TextField tourName;
    @FXML private Label departureDateLabel;
    @FXML private DatePicker departureDate;
    @FXML private DatePicker returnDate;
    @FXML private TextField amountSeats;
    @FXML private TextField availableSeats;
    @FXML private TextField tourPrice;
    @FXML private DatePicker depositIVDate;
    @FXML private DatePicker invoiceDate;
    @FXML private JFXButton saveDataBtn;

    private TourPackage tourPackage = new TourPackage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tourIDCode.setDisable(true);
        SetTourSaleSystemDataUtil.setStatusTourProgram(statusChoice);
        statusChoice.setValue(tourPackage.getStatus());
        SetTourSaleSystemDataUtil.setDatePickerFormat(departureDate);
        SetTourSaleSystemDataUtil.setDatePickerFormat(returnDate);
        SetTourSaleSystemDataUtil.setDatePickerFormat(depositIVDate);
        SetTourSaleSystemDataUtil.setDatePickerFormat(invoiceDate);
    }

    @FXML
    void handleSaveDataBtn(ActionEvent event) {
        setTourProgramFromGUI();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to save?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK){
            setTourProgramFromGUI();
            manageableDatabase.updateData(tourPackage);    //update data to database
            System.out.println("Update Successful");

            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.close();
        }


    }



    public void setTourProgram(TourPackage editTourPackage){
        this.tourPackage = editTourPackage;
        showDataForEditTourPackage();
    }

    public void showDataForEditTourPackage(){
        String[] tourID = tourPackage.getTourID().split("-");
        tourIDCountry.setText(tourID[0]);
        tourIDDay.setText(tourID[1]);
        tourIDCode.setText(tourID[2]);
        tourName.setText(tourPackage.getTourName());
        statusChoice.setValue(tourPackage.getStatus());

        String[] departureDateCut = tourPackage.getDepartureDate().split("-");
        departureDate.setValue(LocalDate.of(Integer.valueOf(departureDateCut[2]), Integer.valueOf(departureDateCut[1]), Integer.valueOf(departureDateCut[0])));

        String[] returnDateCut = tourPackage.getReturnDate().split("-");
        returnDate.setValue(LocalDate.of(Integer.valueOf(returnDateCut[2]), Integer.valueOf(returnDateCut[1]), Integer.valueOf(returnDateCut[0])));

        String[] depositDateCut = tourPackage.getDepositDate().split("-");
        depositIVDate.setValue(LocalDate.of(Integer.valueOf(depositDateCut[2]), Integer.valueOf(depositDateCut[1]), Integer.valueOf(depositDateCut[0])));

        String[] arrearsDateCut = tourPackage.getArrearsDate().split("-");
        invoiceDate.setValue(LocalDate.of(Integer.valueOf(arrearsDateCut[2]), Integer.valueOf(arrearsDateCut[1]), Integer.valueOf(arrearsDateCut[0])));

        amountSeats.setText(String.valueOf(tourPackage.getAmountSeat()));
        availableSeats.setText(String.valueOf(tourPackage.getAvailableSeat()));
        tourPrice.setText(String.valueOf(tourPackage.getPrice()));


    }
    public void setTourProgramFromGUI(){
        tourPackage.setTourID(tourIDCountry.getText()+"-"+tourIDDay.getText()+"-"+tourIDCode.getText());
        tourPackage.setTourName(tourName.getText());
        tourPackage.setPrice(Integer.valueOf(tourPrice.getText()));
        tourPackage.setDepartureDate(departureDate.getEditor().getText());
        tourPackage.setReturnDate(returnDate.getEditor().getText());
        tourPackage.setDepositDate(depositIVDate.getEditor().getText());
        tourPackage.setArrearsDate(invoiceDate.getEditor().getText());
        tourPackage.setAmountSeat(Integer.valueOf(amountSeats.getText()));
        tourPackage.setAvailableSeat(Integer.valueOf(availableSeats.getText()));
        tourPackage.setStatus(statusChoice.getSelectionModel().getSelectedItem());
    }
}
