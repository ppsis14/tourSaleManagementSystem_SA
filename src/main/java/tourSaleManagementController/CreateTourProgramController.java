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
    @FXML private JFXButton cancelBtn;


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
        setValidateOnKeyRelease();

    }

    @FXML
    void handleAddTourProgramBtn(ActionEvent event) {
        if (checkFillOutTourInformation()){
            Alert alertConfirmToDeleteTourProgram = new Alert(Alert.AlertType.CONFIRMATION);
            alertConfirmToDeleteTourProgram.setTitle("Confirmation Dialog");
            alertConfirmToDeleteTourProgram.setHeaderText(null);
            alertConfirmToDeleteTourProgram.setContentText("Do you want to delete this tour program?");
            Optional<ButtonType> action = alertConfirmToDeleteTourProgram.showAndWait();
            if (action.get() == ButtonType.OK) {
                setTourPackageFromGUI();
                manageableDatabase.insertData(tourPackage);
                Stage stage = (Stage) rootPane.getScene().getWindow();
                stage.close();
                //DisplayGUIUtil.loadWindowWithSetSize(getClass().getResource("/tourProgramManagementPage.fxml"), "Tour Program Management");
            }
        }
        else {
            Alert alertCheckFillOutTourInformation = new Alert(Alert.AlertType.ERROR);
            alertCheckFillOutTourInformation.setTitle("Error Dialog");
            alertCheckFillOutTourInformation.setHeaderText("Addition tour program is error");
            alertCheckFillOutTourInformation.setContentText("Please completely fill out information follow (*)");
            Optional<ButtonType> checkFillOutTourAction = alertCheckFillOutTourInformation.showAndWait();
        }

    }

    @FXML
    void handleCancelBtn(ActionEvent event) {
        Alert alertConfirmToDeleteTourProgram = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirmToDeleteTourProgram.setTitle("Confirmation Dialog");
        alertConfirmToDeleteTourProgram.setHeaderText(null);
        alertConfirmToDeleteTourProgram.setContentText("Do you want to cancel creating tour program?");
        Optional<ButtonType> action = alertConfirmToDeleteTourProgram.showAndWait();
        if (action.get() == ButtonType.OK) {
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

    public Boolean checkFillOutTourInformation() {

        if (validateFieldsIsEmpty()){
            return false;
        }
        else return true;
    }

    public void setValidateOnKeyRelease(){
        departureDate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                departureDate.setStyle("-fx-border-color: #2C3E50");
            }
        });
        returnDate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                returnDate.setStyle("-fx-border-color: #2C3E50");
            }
        });
        depositIVDate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                depositIVDate.setStyle("-fx-border-color: #2C3E50");
            }
        });
        invoiceDate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                invoiceDate.setStyle("-fx-border-color: #2C3E50");
            }
        });
        tourIDCountry.setOnKeyReleased(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent event) {
                if(validateTourIDCountry()){
                    tourIDCountry.setStyle("-fx-border-color: #27AE60");
                }else{
                    tourIDCountry.setStyle("-fx-border-color: #922B21");
                }

            }
        });

        tourIDDay.setOnKeyReleased(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent event) {
                if(validateTourIDDay()){
                    tourIDDay.setStyle("-fx-border-color: #27AE60");
                }else{
                    tourIDDay.setStyle("-fx-border-color: #922B21");
                }

            }
        });

        tourName.setOnKeyReleased(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent event) {
                if(validateTourName()){
                    tourName.setStyle("-fx-border-color: #27AE60");
                }else{
                    tourName.setStyle("-fx-border-color: #922B21");
                }

            }
        });

        amountSeats.setOnKeyReleased(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent event) {
                if(validateAmountSeat()){
                    amountSeats.setStyle("-fx-border-color: #27AE60");
                }else{
                    amountSeats.setStyle("-fx-border-color: #922B21");
                }

            }
        });

        availableSeats.setOnKeyReleased(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent event) {
                if(validateAvailableSeat()){
                    availableSeats.setStyle("-fx-border-color: #27AE60");
                }else{
                    availableSeats.setStyle("-fx-border-color: #922B21");
                }

            }
        });

        tourPrice.setOnKeyReleased(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent event) {
                if(validatePrice()){
                    tourPrice.setStyle("-fx-border-color: #27AE60");
                }else{
                    tourPrice.setStyle("-fx-border-color: #922B21");
                }
            }
        });
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
        if (matcher.find() && matcher.group().equals(amountSeats.getText())){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean validateAvailableSeat(){
        Pattern pattern = Pattern.compile("^[1-9][0-9]?$");
        Matcher matcher = pattern.matcher(availableSeats.getText());
        if (matcher.find() && matcher.group().equals(availableSeats.getText())){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean validatePrice(){
        Pattern pattern = Pattern.compile("[1-9][0-9]+");
        Matcher matcher = pattern.matcher(tourPrice.getText());
        if (matcher.find() && matcher.group().equals(tourPrice.getText())){
            return true;
        }
        else {
            return false;
        }
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
        if (depositIVDate.getEditor().getText().isEmpty()){depositIVDate.setStyle("-fx-border-color: #C0392B");count++;}
        else {depositIVDate.setStyle("-fx-border-color: #2C3E50");}
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
