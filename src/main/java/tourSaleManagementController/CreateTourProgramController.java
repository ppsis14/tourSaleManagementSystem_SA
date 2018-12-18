package tourSaleManagementController;

import Table.TourPackage;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tourSaleManagementSystemUtil.DisplayGUIUtil;
import tourSaleManagementSystemUtil.FormatConverter;
import tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;

public class CreateTourProgramController implements Initializable {
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
    @FXML private JFXButton addTourProgram;
    TourPackage tourPackage = new TourPackage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SetTourSaleSystemDataUtil.setStatusTourProgram(statusChoice);
        SetTourSaleSystemDataUtil.setDatePickerFormat(departureDate);
        SetTourSaleSystemDataUtil.setDatePickerFormat(returnDate);
        SetTourSaleSystemDataUtil.setDatePickerFormat(depositIVDate);
        SetTourSaleSystemDataUtil.setDatePickerFormat(invoiceDate);
        String[] tourNumCode = manageableDatabase.getLastTourID().split("-");
        tourIDCode.setText(String.format("%06d",Integer.valueOf(tourNumCode[2])+1));
        tourIDCode.setDisable(true);

    }

    @FXML
    void handleAddTourProgramBtn(ActionEvent event) {
        Alert alertConfirmToDeleteTourProgram = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmToDeleteTourProgram.setTitle("Confirmation Dialog");
        alertConfirmToDeleteTourProgram.setHeaderText(null);
        alertConfirmToDeleteTourProgram.setContentText("Do you want to delete this tour Program?");
        Optional<ButtonType> action = alertConfirmToDeleteTourProgram.showAndWait();
        if (action.get() == ButtonType.OK) {
            setTourPackageFromGUI();
            manageableDatabase.insertData(tourPackage);
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.close();
            DisplayGUIUtil.loadWindowWithSetSize(getClass().getResource("/tourProgramManagementPage.fxml"), "Tour Program Management");
        }
    }
    public void setTourPackageFromGUI(){
        tourPackage.setTourID(tourIDCountry.getText()+"-"+tourIDDay.getText()+"-"+tourIDCode.getText());
        tourPackage.setStatus(statusChoice.getSelectionModel().getSelectedItem());
        tourPackage.setTourName(tourName.getText());
        tourPackage.setPrice(Integer.valueOf(tourPrice.getText()));
        tourPackage.setDepartureDate(departureDate.getEditor().getText());
        tourPackage.setReturnDate(returnDate.getEditor().getText());
        tourPackage.setDepositDate(depositIVDate.getEditor().getText());
        tourPackage.setArrearsDate(invoiceDate.getEditor().getText());
        tourPackage.setAmountSeat(Integer.valueOf(amountSeats.getText()));
        tourPackage.setAvailableSeat(Integer.valueOf(availableSeats.getText()));

    }

    public Boolean checkFillOutInformation() {

        if (validateFieldsIsEmpty()){
            return false;
        }
        else return true;
    }

    public void setValidateOnKeyRelease(){
       /* occupation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                occupation.setStyle("-fx-border-color: #2C3E50");
            }
        });*/



    }

    private boolean validateFirstNameTH(){
       /* Pattern pattern = Pattern.compile("^[ๅภถุึคตจขชๆไำพะัีรนยบลฃฟหกดเ้่าสวงผปแอิืทมใฝฎฑธํ๊ณฯญฐฅฤฆฏโฌ็๋ษศซฉฮฺ์ฒฬฦ]+$");
        Matcher matcher = pattern.matcher(firstNameTH.getText());
        if (matcher.find() && matcher.group().equals(firstNameTH.getText())){
            return true;
        }
        else {
            return false;
        }*/
       return true;
    }



    private boolean validateFieldsIsEmpty(){
        /*int count=0;
        if (firstNameTH.getText().isEmpty()){firstNameTH.setStyle("-fx-border-color: #C0392B");count++;}
        else {firstNameTH.setStyle("-fx-border-color: #2C3E50");}
        if (lastNameTH.getText().isEmpty()){lastNameTH.setStyle("-fx-border-color: #C0392B");count++;}
        else {lastNameTH.setStyle("-fx-border-color: #2C3E50");}
        if (firstNameEN.getText().isEmpty()){firstNameEN.setStyle("-fx-border-color: #C0392B");count++;}
        else {firstNameEN.setStyle("-fx-border-color: #2C3E50");}
        if (lastNameEN.getText().isEmpty()){lastNameEN.setStyle("-fx-border-color: #C0392B");count++;}
        else {lastNameEN.setStyle("-fx-border-color: #2C3E50");}
        if (address.getText().isEmpty()){address.setStyle("-fx-border-color: #C0392B");count++;}
        else {address.setStyle("-fx-border-color: #2C3E50");}
        if (age.getText().isEmpty()){age.setStyle("-fx-border-color: #C0392B");count++;}
        else {age.setStyle("-fx-border-color: #2C3E50");}
        if (dateOfBirth.getEditor().getText().isEmpty()){dateOfBirth.setStyle("-fx-border-color: #C0392B");count++;}
        else {dateOfBirth.setStyle("-fx-border-color: #2C3E50");}
        if (passportNo.getText().isEmpty()){passportNo.setStyle("-fx-border-color: #C0392B");count++;}
        else {passportNo.setStyle("-fx-border-color:  #2C3E50");}
        if (expPassportDate.getEditor().getText().isEmpty()){expPassportDate.setStyle("-fx-border-color: #C0392B");count++;}
        else {expPassportDate.setStyle("-fx-border-color: #2C3E50");}
        if (phoneNum.getText().isEmpty()){phoneNum.setStyle("-fx-border-color: #C0392B");count++;}
        else {phoneNum.setStyle("-fx-border-color: #2C3E50");}
        if (!eatBeefY.isSelected() && !eatBeefN.isSelected()){eatBeefY.setStyle("-fx-text-fill: #C0392B"); eatBeefN.setStyle("-fx-text-fill: #C0392B");count++;}
        else { eatBeefY.setStyle("-fx-text-fill: #5a5a5a"); eatBeefN.setStyle("-fx-text-fill: #5a5a5a");}
        if (count != 0){
            return true;
        }
        else{
            return false;
        }*/
        return true;
    }
}
