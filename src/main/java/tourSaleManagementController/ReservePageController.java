package tourSaleManagementController;

import Table.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.textfield.TextFields;
import tourSaleManagementSystemUtil.DisplayGUIUtil;
import tourSaleManagementSystemUtil.FormatConverter;
import tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.*;
import static tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil.*;


public class ReservePageController implements Initializable {

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
    @FXML private JFXHamburger menu;
    @FXML private JFXDrawer drawerMenu;
    @FXML private JFXButton addCustomerBtn;
    @FXML private JFXButton submitBtn;
    @FXML private Label reserveCode;
    @FXML private Label customerNo;
    @FXML private Label reservedSeats;
    @FXML private ComboBox<String> tourIDComboBox;
    @FXML private JFXCheckBox oldCustomer;
    @FXML private JFXCheckBox newCustomer;
    @FXML private TextField searchByCustomerName;
    @FXML private Button searchCustomerBtn;
    @FXML private Label loginNameLabel;

    private ArrayList<String> newCustomerID_List = new ArrayList<>();
    private ArrayList<Reservation> reserveCustomer_List = new ArrayList<>();
    private ArrayList<Customer> customerList = new ArrayList<>();
    private ReservationPayment reservationPayment = new ReservationPayment();
    private Reservation reservationCustomer = new Reservation();
    private Customer customer = new Customer();
    private Invoice invoice = new Invoice();
    private int orderReserve;
    ObservableList<Customer> obListCustomer = FXCollections.observableList(manageableDatabase.getAllCustomer());
    private int countErr = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayGUIUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));
        SetTourSaleSystemDataUtil.setTitleNameTH(titleNameTH);
        SetTourSaleSystemDataUtil.setTitleNameEN(titleNameEN);
        SetTourSaleSystemDataUtil.setGender(genderChoice);
        SetTourSaleSystemDataUtil.setHearAboutUs(hearAboutUsChoices);
        newCustomer.setSelected(true);
        oldCustomer.setSelected(false);
        clearText();
        SetTourSaleSystemDataUtil.setTourProgram(tourIDComboBox);
        SetTourSaleSystemDataUtil.setDatePickerFormat(dateOfBirth);
        SetTourSaleSystemDataUtil.setDatePickerFormat(expPassportDate);
        searchByCustomerName.clear();
        searchByCustomerName.setDisable(true);
        searchCustomerBtn.setDisable(true);
        reserveCode.setText(FormatConverter.generateReservationCode(manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem())));
        String tmpOrder[] = reserveCode.getText().split("-");
        orderReserve = Integer.valueOf(tmpOrder[3]);
        loginNameLabel.setText(loginEmployee.getFirstName() + " " + loginEmployee.getLastName() + " [ " + loginEmployee.getPosition().toUpperCase() + " ]");
        submitBtn.setDisable(true);
        reservedSeats.setText(getAmountReservationSeatLabel());
        //setValidateOnEventHandler();
        System.out.println("countErr : " + countErr);
    }

    @FXML
    public void handleNotEatBeefCheckbox(ActionEvent event) {
        eatBeefY.setSelected(false);
    }

    @FXML
    public void handleEatBeefCheckbox(ActionEvent event) {
        eatBeefN.setSelected(false);
    }

    @FXML
    public void handleNewCustomerCheckbox(ActionEvent event) {
        oldCustomer.setSelected(false);
        newCustomer.setSelected(true);
        clearText();
        searchByCustomerName.clear();
        searchByCustomerName.setDisable(true);
        searchCustomerBtn.setDisable(true);

    }

    @FXML
    public void handleOldCustomerCheckbox(ActionEvent event) {
        newCustomer.setSelected(false);
        oldCustomer.setSelected(true);
        searchByCustomerName.setDisable(false);
        searchCustomerBtn.setDisable(false);
        setSearchCustomer();
    }

    @FXML
    public void handleTourIDComboBox(ActionEvent e) {
        String tourID = manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem());
        String tmp[] = tourID.split("-");
        tourID = tmp[0] + "-" + tmp[1] + "-" + tmp[2] + "-" + String.format("%06d", orderReserve);

        reservedSeats.setText(getAmountReservationSeatLabel());
        reserveCode.setText(tourID);
    }

    @FXML
    void handleSubmitBtn(ActionEvent event) {

        String tour_id = manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem());
        int availableSeat = manageableDatabase.getAvailableByTourID(manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem()));

        //insert customer to database
        for (Customer customer : customerList) {
            if(newCustomerID_List.contains(customer.getCustomerID()))
                manageableDatabase.insertData(customer);    //new customer
            else
                manageableDatabase.updateData(customer);    //old customer
        }

        //insert reservation customer to database
        for (Reservation reservation: reserveCustomer_List) {
            manageableDatabase.insertData(reservation);
        }

        //insert reservation payment and deposit invoice to database
        setReservationPaymentFromGUI();
        setDepositInvoice();
        manageableDatabase.insertData(reservationPayment);          //inset reservationPayment to database
        manageableDatabase.insertData(invoice, DEPOSIT_INVOICE);    // insert deposit invoice

        //update seat in tour package
        manageableDatabase.updateAvailableData(tour_id,availableSeat-Integer.valueOf(customerNo.getText()));

        //pop up warning
        Alert alertConfirmToSubmitCustomerData = new Alert(Alert.AlertType.INFORMATION);
        alertConfirmToSubmitCustomerData.setTitle("Confirmation Dialog");
        alertConfirmToSubmitCustomerData.setHeaderText(null);
        alertConfirmToSubmitCustomerData.setContentText("Submit reservation customer successfully!");
        Optional<ButtonType> action = alertConfirmToSubmitCustomerData.showAndWait();

        clearText();

        //setup value of reservation page
        searchByCustomerName.clear();
        searchByCustomerName.setDisable(true);
        searchCustomerBtn.setDisable(true);
        reserveCode.setText(FormatConverter.generateReservationCode(manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem())));
        String tmpOrder[] = reserveCode.getText().split("-");
        orderReserve = Integer.valueOf(tmpOrder[3]);
        //setUpValueReservationPage();
        addCustomerBtn.setDisable(false);
    }

    @FXML
    void handleAddCustomerBtn(ActionEvent event) {
        System.out.println("countErr : " + countErr);
        countErr = 0;
        checkFillOutInformation();
            /*
            String tour_id = manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem());
            int availableSeat = manageableDatabase.getAvailableByTourID(manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem()));

            if (availableSeat - Integer.valueOf(customerNo.getText()) >= 0) {
                //---------------------------------------------------------
                // case show "Do you want to save this customer information?" -> ok = continue/ cancel = add another customer
                Alert alertSaveCustomerInformation = new Alert(Alert.AlertType.CONFIRMATION);
                alertSaveCustomerInformation.setTitle("Confirmation Dialog");
                alertSaveCustomerInformation.setHeaderText(null);
                alertSaveCustomerInformation.setContentText("Do you want to save this customer information?");
                Optional<ButtonType> saveCustomerInfoAction = alertSaveCustomerInformation.showAndWait();

                if (saveCustomerInfoAction.get() == ButtonType.OK) {

                    setCustomerFromGUI();
                    //add new customer to list for check
                    if (newCustomer.isSelected()) {
                        newCustomerID_List.add(customer.getCustomerID());
                    }
                    //add customer id to list
                    customerList.add(customer);
                    setReservationCustomerFromGUI();
                    reserveCustomer_List.add(reservationCustomer);

                    Alert alertAddMoreCustomer = new Alert(Alert.AlertType.CONFIRMATION);
                    alertAddMoreCustomer.setTitle("Confirmation Dialog");
                    alertAddMoreCustomer.setHeaderText(null);
                    alertAddMoreCustomer.setContentText("Do you want to add another customer?");
                    Optional<ButtonType> addMoreCustomerAction = alertAddMoreCustomer.showAndWait();
                    if (addMoreCustomerAction.get() == ButtonType.OK) {

                        // add count amount customer
                        customerNo.setText(String.valueOf(Integer.valueOf(customerNo.getText()) + 1));
                        //set reserv seat
                        String[] reservText = reservedSeats.getText().split(" / ");
                        reservedSeats.setText(String.valueOf( Integer.valueOf(reservText[0]) + 1 ) + " / " + reservText[1]);

                    }

                    else if (addMoreCustomerAction.get() == ButtonType.CANCEL) {
                        //disable btn -----> after press submit
                        addCustomerBtn.setDisable(true);
                        submitBtn.setDisable(false);

                    }

                    //After choose btn OK/CANCEL
                    clearText();
                    searchByCustomerName.clear();
                    searchByCustomerName.setDisable(true);
                    searchCustomerBtn.setDisable(true);
                    customer = new Customer();
                    reservationCustomer = new Reservation();

                } else if (saveCustomerInfoAction.get() == ButtonType.CANCEL) {
                    // back to edit customer information
                }
            } else {
                //pop up warning
                Alert alertShowInformationIsUpdate = new Alert(Alert.AlertType.INFORMATION);
                alertShowInformationIsUpdate.setTitle("Information Dialog");
                alertShowInformationIsUpdate.setHeaderText(null);
                alertShowInformationIsUpdate.setContentText("Available seat are full.");
                Optional<ButtonType> action = alertShowInformationIsUpdate.showAndWait();
                setUpValueReservationPage();
                if (action.get() == ButtonType.OK) {
                    setUpValueReservationPage();
                }
            }

        } else {
            Alert alertFillOutInformationError = new Alert(Alert.AlertType.ERROR);
            alertFillOutInformationError.setTitle("Error Dialog");
            alertFillOutInformationError.setHeaderText("Saving customer information is error!");
            alertFillOutInformationError.setContentText("Please completely fill out information follow (*)");
            Optional<ButtonType> checkFillOutInformationAction = alertFillOutInformationError.showAndWait();

        }*/
    }

    public void setDepositInvoice(){
        invoice.setReservationCode(reservationPayment.getReservationCode());
        invoice.setInvoiceNo(FormatConverter.generateInvoiceNo(DEPOSIT_INVOICE,invoice.getReservationCode()));
        invoice.setTourID(reservationPayment.getTourID());
        invoice.setTourName(reservationPayment.getTourName());
        invoice.setCustomerID(reservationPayment.getCustomerID());
        invoice.setCustomerName(reservationPayment.getCustomerName());
        invoice.setEmployeeID(reservationPayment.getEmployeeID());
        invoice.setEmployeeName(reservationPayment.getEmployeeName());
        invoice.setAmountCustomer(reservationPayment.getAmountCustomer());
        invoice.setTotalPrice(reservationPayment.getTotal_price()/2);
        invoice.setInvoiceType(DEPOSIT_INVOICE);
        invoice.setCreateStatus(NOT_CREATED);

    }

    public void setReservationCustomerFromGUI(){

        reservationCustomer.setReservationCode(reserveCode.getText());
        reservationCustomer.setTourID(manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem()));
        reservationCustomer.setTourName(tourIDComboBox.getSelectionModel().getSelectedItem());
        reservationCustomer.setCustomerID(customer.getCustomerID());
        reservationCustomer.setCustomerName(customer.getTitleNameENG()+" "+customer.getFirstNameENG()+" "+customer.getLastNameENG());
        reservationCustomer.setEmployeeID(loginEmployee.getEmployee_ID());
        reservationCustomer.setEmployeeName(manageableDatabase.getNameEmployee(reservationCustomer.getEmployeeID()));

    }
    public void setReservationPaymentFromGUI(){

        reservationPayment.setReservationCode(reserveCode.getText());
        reservationPayment.setTourID(manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem()));
        reservationPayment.setTourName(tourIDComboBox.getSelectionModel().getSelectedItem());
        reservationPayment.setCustomerID(customerList.get(0).getCustomerID());
        reservationPayment.setCustomerName(manageableDatabase.getNameCustomer(reservationPayment.getCustomerID()));
        reservationPayment.setEmployeeID(loginEmployee.getEmployee_ID());
        reservationPayment.setEmployeeName(manageableDatabase.getNameEmployee(reservationPayment.getEmployeeID()));
        reservationPayment.setAmountCustomer(Integer.parseInt(customerNo.getText()));
        reservationPayment.setTotal_price(reservationPayment.calculateTotalPrice(manageableDatabase.getTourPrice(reservationPayment.getTourID())));
        reservationPayment.setDepositStatus(NOT_PAID);
        reservationPayment.setArrearsStatus(NOT_PAID);

    }

    public void setCustomerFromGUI(){

        if (newCustomer.isSelected()) {
            //increase customerID
            String newCustomerID = FormatConverter.generateCustomerID();
            customer.setCustomerID(newCustomerID);
        }

        //information
        customer.setTitleNameTH(titleNameTH.getSelectionModel().getSelectedItem());
        customer.setFirstNameTH(firstNameTH.getText());
        customer.setLastNameTH(lastNameTH.getText());
        customer.setTitleNameENG(titleNameEN.getSelectionModel().getSelectedItem());
        customer.setFirstNameENG(firstNameEN.getText());
        customer.setLastNameENG(lastNameEN.getText());
        customer.setGender(genderChoice.getSelectionModel().getSelectedItem());
        customer.setDateOfBirth(dateOfBirth.getEditor().getText());
        if (customer.getDateOfBirth().isEmpty()) customer.setDateOfBirth("dd-mm-yyyy");
        customer.setPassport_no(passportNo.getText());
        customer.setExp_passport(expPassportDate.getEditor().getText());
        if (customer.getExp_passport().isEmpty()) customer.setExp_passport("dd-mm-yyyy");
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

    public void setUpOldCustomerData() {
        String customerIDSearch = searchByCustomerName.getText();

        try {
            customerIDSearch = customerIDSearch.substring(6, customerIDSearch.indexOf("]"));
            System.out.println("select customer --> " + customerIDSearch);
            customer = manageableDatabase.getOneCustomer(customerIDSearch);
            if (customer != null) {
                newCustomer.setSelected(false);
                oldCustomer.setSelected(true);
                searchByCustomerName.clear();
                //searchByCustomerName.setDisable(true);
                //searchCustomerBtn.setDisable(true);
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
            else{
                //pop up warning
                Alert alertConfirmToAddCustomerData = new Alert(Alert.AlertType.CONFIRMATION);
                alertConfirmToAddCustomerData.setTitle("Confirmation Dialog");
                alertConfirmToAddCustomerData.setHeaderText(null);
                alertConfirmToAddCustomerData.setContentText("This name can not be found.");
                clearText();
            }
        }
        catch (RuntimeException e){
                System.err.println("Not found customer.");
        }

    }

    public void clearText(){
        newCustomer.setSelected(true);
        oldCustomer.setSelected(false);

        //information
        titleNameTH.getSelectionModel().clearSelection();
        titleNameTH.setValue("นางสาว");
        firstNameTH.clear();
        lastNameTH.clear();
        titleNameEN.getSelectionModel().clearSelection();
        titleNameEN.setValue("Miss");
        firstNameEN.clear();
        lastNameEN.clear();
        genderChoice.getSelectionModel().clearSelection();
        genderChoice.setValue("Female");
        dateOfBirth.getEditor().clear();
        passportNo.clear();
        expPassportDate.getEditor().clear();
        occupation.clear();
        //Contact
        address.clear();
        phoneNum.clear();
        homeTelNum.clear();
        faxNum.clear();
        email.clear();
        //moreInfo
        underlyingDisease.clear();
        foodAllergy.clear();
        eatBeefY.setSelected(false);
        eatBeefN.setSelected(false);
        moreDetail.clear();
        hearAboutUsChoices.getSelectionModel().clearSelection();
        hearAboutUsChoices.setValue("Bangkokbizs News");
        searchByCustomerName.setDisable(false);
        searchCustomerBtn.setDisable(false);

    }

    void setUpValueReservationPage(){
        //setup value of reservation page
        customerNo.setText("1");     // setup count amount customer
        clearText();
        customerList.clear();
        newCustomerID_List.clear();
        reserveCustomer_List.clear();
        customer = new Customer();
        reservationCustomer = new Reservation();
        reservationPayment = new ReservationPayment();
    }

    @FXML
    void handleSearchCustomerBtn(ActionEvent event){
        if(searchByCustomerName.getText() != null)
            setUpOldCustomerData();
    }

    public void setSearchCustomer(){

            List<Customer> customerListSearch = manageableDatabase.getAllCustomer();
            List<String> searchList = new ArrayList<>();
            for (Customer c : customerListSearch) {
                searchList.add("[ID : " + c.getCustomerID() + "] " + c.getFirstNameTH() + " " + c.getLastNameTH());
                System.out.println("[ID : " + c.getCustomerID() + "] " + c.getFirstNameTH() + " " + c.getLastNameTH());
            }
            TextFields.bindAutoCompletion(searchByCustomerName, searchList);


    }

    public String getAmountReservationSeatLabel(){

        String tour_id = manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem());
        TourPackage tourPackage = manageableDatabase.getOneTourPackage(tour_id);
        String amountReservationSeatLabel = String.valueOf(tourPackage.getAmountSeat()-tourPackage.getAvailableSeat()) + " / " + String.valueOf(tourPackage.getAmountSeat());
        return amountReservationSeatLabel;
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
            String tour_id = manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem());
            int availableSeat = manageableDatabase.getAvailableByTourID(manageableDatabase.getTourID(tourIDComboBox.getSelectionModel().getSelectedItem()));

            if (availableSeat - Integer.valueOf(customerNo.getText()) >= 0) {
                //---------------------------------------------------------
                // case show "Do you want to save this customer information?" -> ok = continue/ cancel = add another customer
                Alert alertSaveCustomerInformation = new Alert(Alert.AlertType.CONFIRMATION);
                alertSaveCustomerInformation.setTitle("Confirmation Dialog");
                alertSaveCustomerInformation.setHeaderText(null);
                alertSaveCustomerInformation.setContentText("Do you want to save this customer information?");
                Optional<ButtonType> saveCustomerInfoAction = alertSaveCustomerInformation.showAndWait();

                if (saveCustomerInfoAction.get() == ButtonType.OK) {

                    setCustomerFromGUI();
                    //add new customer to list for check
                    if (newCustomer.isSelected()) {
                        newCustomerID_List.add(customer.getCustomerID());
                    }
                    //add customer id to list
                    customerList.add(customer);
                    setReservationCustomerFromGUI();
                    reserveCustomer_List.add(reservationCustomer);

                    Alert alertAddMoreCustomer = new Alert(Alert.AlertType.CONFIRMATION);
                    alertAddMoreCustomer.setTitle("Confirmation Dialog");
                    alertAddMoreCustomer.setHeaderText(null);
                    alertAddMoreCustomer.setContentText("Do you want to add another customer?");
                    Optional<ButtonType> addMoreCustomerAction = alertAddMoreCustomer.showAndWait();
                    if (addMoreCustomerAction.get() == ButtonType.OK) {

                        // add count amount customer
                        customerNo.setText(String.valueOf(Integer.valueOf(customerNo.getText()) + 1));
                        //set reserv seat
                        String[] reservText = reservedSeats.getText().split(" / ");
                        reservedSeats.setText(String.valueOf( Integer.valueOf(reservText[0]) + 1 ) + " / " + reservText[1]);

                    }

                    else if (addMoreCustomerAction.get() == ButtonType.CANCEL) {
                        //disable btn -----> after press submit
                        addCustomerBtn.setDisable(true);
                        submitBtn.setDisable(false);

                    }

                    //After choose btn OK/CANCEL
                    clearText();
                    searchByCustomerName.clear();
                    searchByCustomerName.setDisable(true);
                    searchCustomerBtn.setDisable(true);
                    customer = new Customer();
                    reservationCustomer = new Reservation();

                } else if (saveCustomerInfoAction.get() == ButtonType.CANCEL) {
                    // back to edit customer information
                }
            } else {
                //pop up warning
                Alert alertShowInformationIsUpdate = new Alert(Alert.AlertType.INFORMATION);
                alertShowInformationIsUpdate.setTitle("Information Dialog");
                alertShowInformationIsUpdate.setHeaderText(null);
                alertShowInformationIsUpdate.setContentText("Available seat are full.");
                Optional<ButtonType> action = alertShowInformationIsUpdate.showAndWait();
                setUpValueReservationPage();
                if (action.get() == ButtonType.OK) {
                    setUpValueReservationPage();
                }
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

        if(validateHomeTelNum()){
            homeTelNum.setStyle("-fx-border-color: #27AE60");
        }else{
            homeTelNum.setStyle("-fx-border-color: #922B21");
            countErr++;
        }

        if(validateFaxNum()){
            faxNum.setStyle("-fx-border-color: #27AE60");
        }else{
            faxNum.setStyle("-fx-border-color: #922B21");
            countErr++;
        }

        if(validateUnderDisease()){
            underlyingDisease.setStyle("-fx-border-color: #27AE60");
        }else{
            underlyingDisease.setStyle("-fx-border-color: #922B21");
            countErr++;
        }

        if(validateFoodAllergy()){
            foodAllergy.setStyle("-fx-border-color: #27AE60");
        }else{
            foodAllergy.setStyle("-fx-border-color: #922B21");
            countErr++;
        }

        if(validateMoreDetail()){
            moreDetail.setStyle("-fx-border-color: #27AE60");
        }else{
            moreDetail.setStyle("-fx-border-color: #922B21");
            countErr++;
        }

        if (validateOccupation()){
            occupation.setStyle("-fx-border-color: #27AE60");
        }else{
            occupation.setStyle("-fx-border-color: #922B21");
            countErr++;
        }

        if(validateEmail()){
            email.setStyle("-fx-border-color: #27AE60");
        }else{
            email.setStyle("-fx-border-color: #922B21");
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

        if (countErr == 0) return true;
        return false;
    }
}
