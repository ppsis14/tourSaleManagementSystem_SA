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
import tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;

public class EditTourPackageController implements Initializable {
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
    private String oldTourID ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        oldTourID = tourPackage.getTourID();
        System.out.println("old tour id" + oldTourID);
        //tourIDCode.setDisable(true);
        SetTourSaleSystemDataUtil.setStatusTourProgram(statusChoice);
        statusChoice.setValue(tourPackage.getStatus());
        SetTourSaleSystemDataUtil.setDatePickerFormat(departureDate);
        SetTourSaleSystemDataUtil.setDatePickerFormat(returnDate);
        SetTourSaleSystemDataUtil.setDatePickerFormat(depositIVDate);
        SetTourSaleSystemDataUtil.setDatePickerFormat(invoiceDate);
        tourIDCountry.setDisable(true);
        tourIDDay.setDisable(true);
        tourIDCode.setDisable(true);
        setValidateOnKeyRelease();
    }

    @FXML
    void handleSaveDataBtn(ActionEvent event) {
        if (checkFillOutTourInformation()){
            setTourPackageFromGUI();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to save?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK){
                setTourPackageFromGUI();
                System.out.println("old tour id" + oldTourID);
                manageableDatabase.updateData(tourPackage,oldTourID);    //update data to database
                System.out.println("Update Successful");

                Stage stage = (Stage) rootPane.getScene().getWindow();
                stage.close();
               // DisplayGUIUtil.loadWindowWithSetSize(getClass().getResource("/tourPackageManagementPage.fxml"), "Tour Program Management");
            }
        }
        else {
            Alert alertSaveTourInformation = new Alert(Alert.AlertType.ERROR);
            alertSaveTourInformation.setTitle("Error Dialog");
            alertSaveTourInformation.setHeaderText("Saving tour package is error");
            alertSaveTourInformation.setContentText("Please completely fill out information follow (*)");
            Optional<ButtonType> checkSaveTourAction = alertSaveTourInformation.showAndWait();
        }

    }



    public void setTourPackage(TourPackage editTourPackage){
        this.tourPackage = editTourPackage;
        showDataForEditTourPackage();
    }

    public void showDataForEditTourPackage(){
        System.out.println("tour id: " + tourPackage.getTourID());
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
    public void setTourPackageFromGUI(){
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
