package tourSaleManagementController;

import Table.TourPackage;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;

public class CreateTourPackageController implements Initializable {
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
    @FXML private DatePicker depositInvoiceDate;
    @FXML private DatePicker invoiceDate;
    @FXML private JFXButton addTourPackage;
    @FXML private JFXButton cancelBtn;

    TourPackage tourPackage = new TourPackage();
    private int countErr = 0 ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SetTourSaleSystemDataUtil.setStatusTourProgram(statusChoice);
        SetTourSaleSystemDataUtil.setDatePickerFormat(departureDate);
        SetTourSaleSystemDataUtil.setDatePickerFormat(returnDate);
        SetTourSaleSystemDataUtil.setDatePickerFormat(depositInvoiceDate);
        SetTourSaleSystemDataUtil.setDatePickerFormat(invoiceDate);
        String[] tourNumCode = manageableDatabase.getLastTourID().split("-");
        tourIDCode.setText(String.format("%06d",Integer.valueOf(tourNumCode[2])+1));
        tourIDCode.setDisable(true);
    }

    @FXML
    void handleAddTourPackageBtn(ActionEvent event) {
        countErr = 0;
        checkFillOutTourInformation();
    }

    @FXML
    void handleCancelBtn(ActionEvent event) {
        Alert alertConfirmToDeleteTourPackage = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmToDeleteTourPackage.setTitle("Confirmation Dialog");
        alertConfirmToDeleteTourPackage.setHeaderText(null);
        alertConfirmToDeleteTourPackage.setContentText("Do you want to cancel creating tour package?");
        Optional<ButtonType> action = alertConfirmToDeleteTourPackage.showAndWait();
        if (action.get() == ButtonType.OK) {
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.close();
        }
    }

    public void setTourPackageFromGUI(){
        tourPackage.setTourID(tourIDCountry.getText()+"-"+tourIDDay.getText()+"-"+tourIDCode.getText());
        tourPackage.setStatus(statusChoice.getSelectionModel().getSelectedItem());
        tourPackage.setTourName(tourName.getText());
        tourPackage.setPrice(Integer.valueOf(tourPrice.getText()));
        tourPackage.setDepartureDate(departureDate.getEditor().getText());
        tourPackage.setReturnDate(returnDate.getEditor().getText());
        tourPackage.setDepositDate(depositInvoiceDate.getEditor().getText());
        tourPackage.setArrearsDate(invoiceDate.getEditor().getText());
        tourPackage.setAmountSeat(Integer.valueOf(amountSeats.getText()));
        tourPackage.setAvailableSeat(Integer.valueOf(availableSeats.getText()));

    }

    public void checkFillOutTourInformation() {
        if (validateFieldsIsEmpty()){
            Alert alertCheckFillOutTourInformation = new Alert(Alert.AlertType.ERROR);
            alertCheckFillOutTourInformation.setTitle("Error Dialog");
            alertCheckFillOutTourInformation.setHeaderText("Addition tour program is error");
            alertCheckFillOutTourInformation.setContentText("Please completely fill out information follow (*)");
            Optional<ButtonType> checkFillOutTourAction = alertCheckFillOutTourInformation.showAndWait();
        }
        else if (!allFieldIsAccuracy()){
            Alert alertFillOutPatternError = new Alert(Alert.AlertType.ERROR);
            alertFillOutPatternError.setTitle("Error Dialog");
            alertFillOutPatternError.setHeaderText("Saving customer information is error!");
            alertFillOutPatternError.setContentText("Some fields are not yet accurate, please fill form again");
            Optional<ButtonType> checkPatternErrorAction = alertFillOutPatternError.showAndWait();
            if (checkPatternErrorAction.get() == ButtonType.OK){ countErr = 0; }
        }
        else{
            Alert alertConfirmToDeleteTourPackage = new Alert(Alert.AlertType.CONFIRMATION);
            alertConfirmToDeleteTourPackage.setTitle("Confirmation Dialog");
            alertConfirmToDeleteTourPackage.setHeaderText(null);
            alertConfirmToDeleteTourPackage.setContentText("Do you want to add this tour package?");
            Optional<ButtonType> action = alertConfirmToDeleteTourPackage.showAndWait();
            if (action.get() == ButtonType.OK) {
                setTourPackageFromGUI();
                manageableDatabase.insertData(tourPackage);
                Stage stage = (Stage) rootPane.getScene().getWindow();
                stage.close();
            }
        }
    }

    private boolean allFieldIsAccuracy() {
        if(validateTourIDCountry()){
            tourIDCountry.setStyle("-fx-border-color: #27AE60");
        }else{
            tourIDCountry.setStyle("-fx-border-color: #922B21");
            countErr++;
        }

        if(validateTourIDDay()){
            tourIDDay.setStyle("-fx-border-color: #27AE60");
        }else{
            tourIDDay.setStyle("-fx-border-color: #922B21");
            countErr++;
        }

        if(validateTourName()){
            tourName.setStyle("-fx-border-color: #27AE60");
        }else{
            tourName.setStyle("-fx-border-color: #922B21");
        }

        if(validateAmountSeat()){
            amountSeats.setStyle("-fx-border-color: #27AE60");
        }else{
            amountSeats.setStyle("-fx-border-color: #922B21");
        }

        if(validateAvailableSeat()){
            availableSeats.setStyle("-fx-border-color: #27AE60");
        }else{
            availableSeats.setStyle("-fx-border-color: #922B21");
        }

        if(validatePrice()){
            tourPrice.setStyle("-fx-border-color: #27AE60");
        }else{
            tourPrice.setStyle("-fx-border-color: #922B21");
        }
        if (validateDepartureDate()) departureDate.setStyle("-fx-border-color: #27AE60");
        else {
            departureDate.setStyle("-fx-border-color: #922B21");
            countErr++;
        }
        if (!departureDate.getEditor().getText().equals("")) {
            compareDepartureAndReturn();
        }
        if (!departureDate.getEditor().getText().equals("") && !returnDate.getEditor().getText().equals("")) {
            compareDepartureAndDepositIV();
        }

        if (!departureDate.getEditor().getText().equals("") && !returnDate.getEditor().getText().equals("") && !depositInvoiceDate.getEditor().getText().equalsIgnoreCase("")) {
            compareDepartureAndInvoice();
        }

        if (countErr == 0) return true;
        return false;

    }

    private void compareDepartureAndReturn(){
        if (validateReturnDate()) returnDate.setStyle("-fx-border-color: #27AE60");
        else {returnDate.setStyle("-fx-border-color: #922B21");countErr++;}
    }

    private void compareDepartureAndDepositIV(){
        if (validateDepositInvoiceDate()) depositInvoiceDate.setStyle("-fx-border-color: #27AE60");
        else {depositInvoiceDate.setStyle("-fx-border-color: #922B21");countErr++;}
    }

    private void compareDepartureAndInvoice(){
        if (validateInvoiceDate()) invoiceDate.setStyle("-fx-border-color: #27AE60");
        else {invoiceDate.setStyle("-fx-border-color: #922B21");countErr++;}
    }


    private boolean validateTourIDCountry(){
        Pattern pattern = Pattern.compile("^[A-Z]{3}$");
        Matcher matcher = pattern.matcher(tourIDCountry.getText());
        if (matcher.find() && matcher.group().equals(tourIDCountry.getText())){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean validateTourIDDay(){
        Pattern pattern = Pattern.compile("^[1-9][A-Z][1-9][A-Z]$");
        Matcher matcher = pattern.matcher(tourIDDay.getText());
        if (matcher.find() && matcher.group().equals(tourIDDay.getText())){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean validateTourName(){
        Pattern pattern = Pattern.compile("[A-Z0-9 ]+$");
        Matcher matcher = pattern.matcher(tourIDCountry.getText());
        if (matcher.find() && matcher.group().equals(tourIDCountry.getText())){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean validateAmountSeat(){
        Pattern pattern = Pattern.compile("^[1-9][0-9]?$");
        Matcher matcher = pattern.matcher(amountSeats.getText());
        if (matcher.find() && matcher.group().equals(amountSeats.getText()) && Integer.valueOf(amountSeats.getText()) >= 0){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean validateAvailableSeat(){
        Pattern pattern = Pattern.compile("^[0-9][0-9]?$");
        Matcher matcher = pattern.matcher(availableSeats.getText());
        if (matcher.find() && matcher.group().equals(availableSeats.getText()) && Integer.valueOf(availableSeats.getText()) <= Integer.valueOf(amountSeats.getText()) && Integer.valueOf(availableSeats.getText()) >= 0){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean validatePrice(){
        Pattern pattern = Pattern.compile("[1-9][0-9]+");
        Matcher matcher = pattern.matcher(tourPrice.getText());
        if (matcher.find() && matcher.group().equals(tourPrice.getText()) && Integer.valueOf(availableSeats.getText()) >= 0){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean validateDepartureDate(){
        boolean status = false;
        String departure = departureDate.getEditor().getText();
        Date today = new Date();
        try {
            Date departDate = new SimpleDateFormat("dd-MM-yyyy").parse(departure);
            if (departDate.compareTo(today) > 0) {
                status = true;
            }
            else status = false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return status;
    }

    private boolean validateReturnDate(){
        boolean status = false;
        String arrive = returnDate.getEditor().getText();
        String departure = departureDate.getEditor().getText();
        try {
            Date arriveDate = new SimpleDateFormat("dd-MM-yyyy").parse(arrive);
            Date departDate = new SimpleDateFormat("dd-MM-yyyy").parse(departure);
            if (arriveDate.compareTo(departDate) > 0) {
                status = true;
            }
            else status = false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return status;
    }
    private boolean validateDepositInvoiceDate(){
        boolean status = false;
        String depositIV = depositInvoiceDate.getEditor().getText();
        String departure = departureDate.getEditor().getText();
        Date today = new Date();
        try {
            Date depositIVDate = new SimpleDateFormat("dd-MM-yyyy").parse(depositIV);
            Date departDate = new SimpleDateFormat("dd-MM-yyyy").parse(departure);
            if (depositIVDate.compareTo(departDate) < 0 && depositIVDate.compareTo(today) > 0) {
                status = true;
            }
            else status = false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return status;
    }

    private boolean validateInvoiceDate(){
        boolean status = false;
        String invoice = invoiceDate.getEditor().getText();
        String departure = departureDate.getEditor().getText();
        String depositIV = depositInvoiceDate.getEditor().getText();
        Date today = new Date();
        try {
            Date finalIVDate = new SimpleDateFormat("dd-MM-yyyy").parse(invoice);
            Date depositIVDate = new SimpleDateFormat("dd-MM-yyyy").parse(depositIV);
            Date departDate = new SimpleDateFormat("dd-MM-yyyy").parse(departure);
            if (finalIVDate.compareTo(departDate) < 0 && finalIVDate.compareTo(depositIVDate) > 0 && finalIVDate.compareTo(today) > 0) {
                status = true;
            }
            else status = false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return status;
    }

    private boolean validateFieldsIsEmpty(){
        int count=0;
        if (tourIDCountry.getText().isEmpty()){tourIDCountry.setStyle("-fx-border-color: #C0392B");count++;}
        else {tourIDCountry.setStyle("-fx-border-color: #2C3E50");}
        if (tourIDDay.getText().isEmpty()){tourIDDay.setStyle("-fx-border-color: #C0392B");count++;}
        else {tourIDDay.setStyle("-fx-border-color: #2C3E50");}
        if (tourName.getText().isEmpty()){tourName.setStyle("-fx-border-color: #C0392B");count++;}
        else {tourName.setStyle("-fx-border-color: #2C3E50");}
        if (departureDate.getEditor().getText().isEmpty()){departureDate.setStyle("-fx-border-color: #C0392B");count++;}
        else {departureDate.setStyle("-fx-border-color: #2C3E50");}
        if (returnDate.getEditor().getText().isEmpty()){returnDate.setStyle("-fx-border-color: #C0392B");count++;}
        else {returnDate.setStyle("-fx-border-color: #2C3E50");}
        if (amountSeats.getText().isEmpty()){amountSeats.setStyle("-fx-border-color: #C0392B");count++;}
        else {amountSeats.setStyle("-fx-border-color: #2C3E50");}
        if (availableSeats.getText().isEmpty()){availableSeats.setStyle("-fx-border-color: #C0392B");count++;}
        else {availableSeats.setStyle("-fx-border-color: #2C3E50");}
        if (tourPrice.getText().isEmpty()){tourPrice.setStyle("-fx-border-color: #C0392B");count++;}
        else {tourPrice.setStyle("-fx-border-color: #2C3E50");}
        if (depositInvoiceDate.getEditor().getText().isEmpty()){
            depositInvoiceDate.setStyle("-fx-border-color: #C0392B");count++;}
        else {
            depositInvoiceDate.setStyle("-fx-border-color: #2C3E50");}
        if (invoiceDate.getEditor().getText().isEmpty()){invoiceDate.setStyle("-fx-border-color: #C0392B");count++;}
        else {invoiceDate.setStyle("-fx-border-color: #2C3E50");}
        if (count != 0){
            return true;
        }
        else{
            return false;
        }
    }
}
