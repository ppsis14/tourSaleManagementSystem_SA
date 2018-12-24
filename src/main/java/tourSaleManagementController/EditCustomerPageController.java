package tourSaleManagementController;

import Table.Customer;
import Table.Reservation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tourSaleManagementSystemUtil.DisplayGUIUtil;
import tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;

public class EditCustomerPageController implements Initializable {
    @FXML private StackPane rootPane;
    @FXML private ChoiceBox<String> titleNameTH;
    @FXML private TextField firstNameTH;
    @FXML private TextField lastNameTH;
    @FXML private ChoiceBox<String> titleNameEN;
    @FXML private TextField firstNameEN;
    @FXML private TextField lastNameEN;
    @FXML private ChoiceBox<String> genderChoice;
    @FXML private TextField occupation;
    @FXML private DatePicker dateOfBirth;
    @FXML private TextField passportNo;
    @FXML private DatePicker expPassportDate;
    @FXML private TextField address;
    @FXML private TextField phoneNum;
    @FXML private TextField homeTelNum;
    @FXML private TextField faxNum;
    @FXML private TextField email;
    @FXML private TextField underlyingDisease;
    @FXML private TextField foodAllergy;
    @FXML private JFXCheckBox eatBeefY;
    @FXML private JFXCheckBox eatBeefN;
    @FXML private TextField moreDetail;
    @FXML private ComboBox<String> hearAboutUsChoices;
    @FXML private JFXButton saveDataBtn;


    private Customer customer = new Customer();
    private int countErr = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SetTourSaleSystemDataUtil.setTitleNameTH(titleNameTH);
        SetTourSaleSystemDataUtil.setTitleNameEN(titleNameEN);
        SetTourSaleSystemDataUtil.setGender(genderChoice);
        SetTourSaleSystemDataUtil.setHearAboutUs(hearAboutUsChoices);
        SetTourSaleSystemDataUtil.setDatePickerFormat(dateOfBirth);
        SetTourSaleSystemDataUtil.setDatePickerFormat(expPassportDate);
    }
    @FXML
    void handleSaveDataBtn(ActionEvent event) {
        countErr = 0;
        checkFillOutInformation();
    }
    @FXML public void handleNotEatBeefCheckbox(ActionEvent event) { eatBeefY.setSelected(false); }
    @FXML public void handleEatBeefCheckbox(ActionEvent event) { eatBeefN.setSelected(false); }

    public void setCustomer(Customer editCustomer){
        this.customer = editCustomer;
        showDataForEditCustomer();
    }

    public void showDataForEditCustomer(){

        //information
        titleNameTH.setValue(customer.getTitleNameTH());
        firstNameTH.setText(customer.getFirstNameTH());
        lastNameTH.setText(customer.getLastNameTH());
        titleNameEN.setValue(customer.getTitleNameENG());
        firstNameEN.setText(customer.getFirstNameENG());
        lastNameEN.setText(customer.getLastNameENG());
        genderChoice.setValue(customer.getGender());
        String[] dateCut = customer.getDateOfBirth().split("-");
        dateOfBirth.setValue(LocalDate.of(Integer.valueOf(dateCut[2]), Integer.valueOf(dateCut[1]), Integer.valueOf(dateCut[0])));
        passportNo.setText(customer.getPassport_no());
        String[] dateExpCut = customer.getExp_passport().split("-");
        expPassportDate.setValue(LocalDate.of(Integer.valueOf(dateExpCut[2]), Integer.valueOf(dateExpCut[1]), Integer.valueOf(dateExpCut[0])));
        occupation.setText(customer.getOccupation());
        //Contact
        address.setText(customer.getContactAddress());
        phoneNum.setText(customer.getCell_phone());
        homeTelNum.setText(customer.getHome_Tel());
        faxNum.setText(customer.getFax());
        email.setText(customer.getEmail());
        //moreInfo
        underlyingDisease.setText(customer.getDisease());
        foodAllergy.setText(customer.getFoodAllergy());

        if (customer.getEatBeef().equalsIgnoreCase("yes")) {
            eatBeefY.setSelected(true);
            eatBeefN.setSelected(false);
        } else {
            eatBeefY.setSelected(false);
            eatBeefN.setSelected(true);
        }

        moreDetail.setText(customer.getMoreDetail());
        hearAboutUsChoices.setValue(customer.getHearAboutUs());

    }
    public void setCustomerFromGUI(){
        //information
        customer.setTitleNameTH(titleNameTH.getSelectionModel().getSelectedItem());
        customer.setFirstNameTH(firstNameTH.getText());
        customer.setLastNameTH(lastNameTH.getText());
        customer.setTitleNameENG(titleNameEN.getSelectionModel().getSelectedItem());
        customer.setFirstNameENG(firstNameEN.getText());
        customer.setLastNameENG(lastNameEN.getText());
        customer.setGender(genderChoice.getSelectionModel().getSelectedItem());
        customer.setDateOfBirth(dateOfBirth.getEditor().getText());
        customer.setPassport_no(passportNo.getText());
        customer.setExp_passport(expPassportDate.getEditor().getText());
        customer.setOccupation(occupation.getText());
        //Contact
        customer.setContactAddress(address.getText());
        customer.setCell_phone(phoneNum.getText());
        customer.setHome_Tel(homeTelNum.getText());
        customer.setFax(faxNum.getText());
        customer.setEmail(email.getText());
        //moreInfo
        customer.setDisease(underlyingDisease.getText());
        customer.setFoodAllergy(foodAllergy.getText());
        customer.setEatBeef(eatBeefY.isSelected() ? eatBeefY.getText() : eatBeefN.getText());
        customer.setMoreDetail(moreDetail.getText());
        customer.setHearAboutUs(hearAboutUsChoices.getSelectionModel().getSelectedItem());
    }

    public void checkFillOutInformation() {
        if (validateFieldsIsEmpty()){
            Alert alertFillOutInformationError = new Alert(Alert.AlertType.ERROR);
            alertFillOutInformationError.setTitle("Error Dialog");
            alertFillOutInformationError.setHeaderText("Saving customer information is error!");
            alertFillOutInformationError.setContentText("Please completely fill out information follow (*)");
            Optional<ButtonType> checkFillOutInformationAction = alertFillOutInformationError.showAndWait();
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
            setCustomerFromGUI();
            manageableDatabase.updateData(customer);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to save?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK){

                setCustomerFromGUI();
                manageableDatabase.updateData(customer);    //update data to database
                System.out.println("Update Successful");

                Stage stage = (Stage) rootPane.getScene().getWindow();
                stage.close();
                //DisplayGUIUtil.loadWindowWithSetSize(getClass().getResource("/customerManagementPage.fxml"), "Customer Management");
            }
        }
    }

    private void compareDOBAndExp(){
        if (validateDateExpPassport()) expPassportDate.setStyle("-fx-border-color: #27AE60");
        else { expPassportDate.setStyle("-fx-border-color: #922B21");countErr++;}
    }
    private boolean validateFirstNameTH(){
        Pattern pattern = Pattern.compile("^[ๅภถุึคตจขชๆไำพะัีรนยบลฃฟหกดเ้่าสวงผปแอิืทมใฝฎฑธํ๊ณฯญฐฅฤฆฏโฌ็๋ษศซฉฮฺ์ฒฬฦ]+$");
        Matcher matcher = pattern.matcher(firstNameTH.getText());
        if (matcher.find() && matcher.group().equals(firstNameTH.getText())) return true;
        else return false;
    }
    private boolean validateLastNameTH(){
        Pattern pattern = Pattern.compile("^[ๅภถุึคตจขชๆไำพะัีรนยบลฃฟหกดเ้่าสวงผปแอิืทมใฝฎฑธํ๊ณฯญฐฅฤฆฏโฌ็๋ษศซฉฮฺ์ฒฬฦ]+$");
        Matcher matcher = pattern.matcher(lastNameTH.getText());
        if (matcher.find() && matcher.group().equals(lastNameTH.getText()))return true;
        else return false;
    }
    private boolean validateFirstNameEN(){
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(firstNameEN.getText());
        if (matcher.find() && matcher.group().equals(firstNameEN.getText()))return true;
        else return false;
    }
    private boolean validateLastNameEN(){
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(lastNameEN.getText());
        if (matcher.find() && matcher.group().equals(lastNameEN.getText()))return true;
        return false;
    }
    private boolean validateOccupation(){
        Pattern pattern = Pattern.compile("([ๅภถุึคตจขชๆไำพะัีรนยบลฃฟหกดเ้่าสวงผปแอิืทมใฝฎฑธํ๊ณฯญฐฅฤฆฏโฌ็๋ษศซฉฮฺ์ฒฬฦ]|[a-zA-z]|[-]|[ ])+$");
        Matcher matcher = pattern.matcher(occupation.getText());
        if (matcher.find() && matcher.group().equals(occupation.getText()))return true;
        else return false;

    }

    private boolean validateDateOfBirth() {
        boolean status = false;
        String dateOfBirth_ = dateOfBirth.getEditor().getText();
        Date today = new Date();
        Date dobDate = null;
        try {
            dobDate = new SimpleDateFormat("dd-MM-yyyy").parse(dateOfBirth_);
            if (dobDate.compareTo(today) < 0) {
                //before or equals today
                status = true;
            }
            else status = false;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return status;
    }

    private boolean validateDateExpPassport()  {
        boolean status = false;
        String passportDate = expPassportDate.getEditor().getText();
        String dateOfBirth_ = dateOfBirth.getEditor().getText();
        try {
            Date dobDate = new SimpleDateFormat("dd-MM-yyyy").parse(dateOfBirth_);
            Date expDate = new SimpleDateFormat("dd-MM-yyyy").parse(passportDate);
            if (dobDate.compareTo(expDate) < 0) status = true;
        }
        catch (ParseException e){ e.printStackTrace();}
        return status;
    }


    private boolean validatePassportNo(){
        Pattern pattern = Pattern.compile("^[A-Z0-9]+$");
        Matcher matcher = pattern.matcher(passportNo.getText());
        if (matcher.find() && matcher.group().equals(passportNo.getText()))return true;
        else return false;
    }

    private boolean validateAddress(){
        Pattern pattern = Pattern.compile("([ๅภถุึคตจขชๆไำพะัีรนยบลฃฟหกดเ้่าสวงผปแอิืทมใฝฎฑธํ๊ณฯญฐฅฤฆฏโฌ็๋ษศซฉฮฺ์ฒฬฦ]|[a-zA-Z]|[ ]|[0-9])+$");
        Matcher matcher = pattern.matcher(address.getText());
        if (matcher.find() && matcher.group().equals(address.getText()))return true;
        else return false;
    }
    private boolean validatePhoneNum(){
        Pattern p = Pattern.compile("[0-9][0-9]{9}");
        Matcher m = p.matcher(phoneNum.getText());
        if(m.find() && m.group().equals(phoneNum.getText())) return true;
        else return false;
    }
    private boolean validateHomeTelNum(){
        Pattern p = Pattern.compile("[0-9][0-9]{8}");
        Matcher m = p.matcher(homeTelNum.getText());
        if(m.find() && m.group().equals(homeTelNum.getText())) return true;
        else return false;
    }
    private boolean validateFaxNum(){
        Pattern p = Pattern.compile("[0-9][0-9]{8}");
        Matcher m = p.matcher(faxNum.getText());
        if(m.find() && m.group().equals(faxNum.getText())) return true;
        else return false;
    }
    private boolean validateEmail(){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9._\\-]+@[a-zA-Z0-9]+[.][a-zA-Z.]+");
        Matcher matcher = pattern.matcher(email.getText());
        if(matcher.find() && matcher.group().equals(email.getText())) return true;
        else return false;
    }
    private boolean validateUnderDisease(){
        Pattern pattern = Pattern.compile("([ๅภถุึคตจขชๆไำพะัีรนยบลฃฟหกดเ้่าสวงผปแอิืทมใฝฎฑธํ๊ณฯญฐฅฤฆฏโฌ็๋ษศซฉฮฺ์ฒฬฦ]|[a-zA-Z0-9]|[ ]|[-])+$");
        Matcher matcher = pattern.matcher(underlyingDisease.getText());
        if (matcher.find() && matcher.group().equals(underlyingDisease.getText())) return true;
        else return false;
    }
    private boolean validateFoodAllergy(){
        Pattern pattern = Pattern.compile("([ๅภถุึคตจขชๆไำพะัีรนยบลฃฟหกดเ้่าสวงผปแอิืทมใฝฎฑธํ๊ณฯญฐฅฤฆฏโฌ็๋ษศซฉฮฺ์ฒฬฦ]|[a-zA-Z0-9]|[ ]|[-])+$");
        Matcher matcher = pattern.matcher(foodAllergy.getText());
        if (matcher.find() && matcher.group().equals(foodAllergy.getText())) return true;
        else return false;
    }

    private boolean validateMoreDetail(){
        Pattern pattern = Pattern.compile("([ๅภถุึคตจขชๆไำพะัีรนยบลฃฟหกดเ้่าสวงผปแอิืทมใฝฎฑธํ๊ณฯญฐฅฤฆฏโฌ็๋ษศซฉฮฺ์ฒฬฦ]|[a-zA-Z0-9]|[ ]|[-])+$");
        Matcher matcher = pattern.matcher(moreDetail.getText());
        if (matcher.find() && matcher.group().equals(moreDetail.getText())) return true;
        else return false;
    }

    private boolean validateFieldsIsEmpty(){
        int count=0;
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
        }
    }
    private boolean allFieldIsAccuracy(){
        if(validateFirstNameTH()){
            firstNameTH.setStyle("-fx-border-color: #27AE60");
        }else{
            firstNameTH.setStyle("-fx-border-color: #922B21");
            countErr++;
        }

        if(validateLastNameTH()){
            lastNameTH.setStyle("-fx-border-color: #27AE60");
        }else{
            lastNameTH.setStyle("-fx-border-color: #922B21");
            countErr++;
        }

        if(validateFirstNameEN()){
            firstNameEN.setStyle("-fx-border-color: #27AE60");
        }else{
            firstNameEN.setStyle("-fx-border-color: #922B21");
            countErr++;
        }

        if(validateLastNameEN()){
            lastNameEN.setStyle("-fx-border-color: #27AE60");
        }else{
            lastNameEN.setStyle("-fx-border-color: #922B21");
            countErr++;
        }

        if(validatePassportNo()){
            passportNo.setStyle("-fx-border-color: #27AE60");
        }else{
            passportNo.setStyle("-fx-border-color: #922B21");
            countErr++;
        }

        if(validateAddress()){
            address.setStyle("-fx-border-color: #27AE60");
        }else{
            address.setStyle("-fx-border-color: #922B21");
            countErr++;
        }

        if(validatePhoneNum()){
            phoneNum.setStyle("-fx-border-color: #27AE60");
        }else{
            phoneNum.setStyle("-fx-border-color: #922B21");
            countErr++;
        }

        if (validateDateOfBirth()) dateOfBirth.setStyle("-fx-border-color: #27AE60");
        else {
            dateOfBirth.setStyle("-fx-border-color: #922B21");
            countErr++;
        }
        if (!expPassportDate.getEditor().getText().equals("")) {
            compareDOBAndExp();
        }
        if (!dateOfBirth.getEditor().getText().equals("")) {
            compareDOBAndExp();
        }

        if (!homeTelNum.getText().isEmpty()){
            if(validateHomeTelNum()){
                homeTelNum.setStyle("-fx-border-color: #27AE60");
            }else{
                homeTelNum.setStyle("-fx-border-color: #922B21");
                countErr++;
            }
        }

        if (!faxNum.getText().isEmpty()){
            if(validateFaxNum()){
                faxNum.setStyle("-fx-border-color: #27AE60");
            }else{
                faxNum.setStyle("-fx-border-color: #922B21");
                countErr++;
            }
        }

        if(!underlyingDisease.getText().isEmpty()){
            if(validateUnderDisease()){
                underlyingDisease.setStyle("-fx-border-color: #27AE60");
            }else{
                underlyingDisease.setStyle("-fx-border-color: #922B21");
                countErr++;
            }
        }

        if (!foodAllergy.getText().isEmpty()){
            if(validateFoodAllergy()){
                foodAllergy.setStyle("-fx-border-color: #27AE60");
            }else{
                foodAllergy.setStyle("-fx-border-color: #922B21");
                countErr++;
            }
        }

        if (!moreDetail.getText().isEmpty()){
            if(validateMoreDetail()){
                moreDetail.setStyle("-fx-border-color: #27AE60");
            }else{
                moreDetail.setStyle("-fx-border-color: #922B21");
                countErr++;
            }
        }

        if (!occupation.getText().isEmpty()){
            if (validateOccupation()){
                occupation.setStyle("-fx-border-color: #27AE60");
            }else{
                occupation.setStyle("-fx-border-color: #922B21");
                countErr++;
            }
        }

        if (!email.getText().isEmpty()){
            if(validateEmail()){
                email.setStyle("-fx-border-color: #27AE60");
            }else{
                email.setStyle("-fx-border-color: #922B21");
                countErr++;
            }
        }

        if (countErr == 0) return true;
        return false;
    }
}
