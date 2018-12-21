package tourSaleManagementSystemUtil;

import Table.TourPackage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;

public class SetTourSaleSystemDataUtil {
    public static final String DEPOSIT_INVOICE = "invoice_deposit";
    public static final String ARREARS_INVOICE = "invoice_arrears";
    public static final String DEPOSIT_RECEIPT = "receipt_deposit";
    public static final String ARREARS_RECEIPT = "receipt_arrears";
    public static final String PAID = "Paid";
    public static final String NOT_PAID = "Not paid";
    public static final String CREATED = "Created";
    public static final String NOT_CREATED = "Not created";

    public static void setTitleNameTH(ChoiceBox choiceBox){
        ObservableList<String> titleNameTHChoices = FXCollections.observableArrayList("นางสาว", "นาง", "นาย", "เด็กหญิง", "เด็กชาย", "ศาตราจารย์", "ผู้ช่วยศาสตราจารย์", "รองศาสตราจารย์");
        choiceBox.getSelectionModel().selectFirst();
        choiceBox.setValue("นางสาว");
        choiceBox.getItems().addAll(titleNameTHChoices);
    }
    public static void setTitleNameEN(ChoiceBox choiceBox){
        ObservableList<String> titleNameENChoices = FXCollections.observableArrayList("Miss", "Mrs.", "Mr.", "Master", "Professor", "Assistant Professor", "Associate Professor");
        choiceBox.getSelectionModel().selectFirst();
        choiceBox.setValue("Miss");
        choiceBox.getItems().addAll(titleNameENChoices);
    }

    public static void setGender(ChoiceBox choiceBox){
        ObservableList<String> genderChoices = FXCollections.observableArrayList("Female", "Male");
        choiceBox.getSelectionModel().selectFirst();
        choiceBox.setValue("Female");
        choiceBox.getItems().addAll(genderChoices);
    }

    public static void setPaidStatus(ChoiceBox choiceBox){
        ObservableList<String> paidStatusChoice = FXCollections.observableArrayList("None", "Paid", "Not paid");
        choiceBox.getSelectionModel().selectFirst();
        choiceBox.setValue("None");
        choiceBox.getItems().addAll(paidStatusChoice);
    }

    public static void setHearAboutUs(ComboBox comboBox){
        ObservableList<String> hereAboutUsChoices = FXCollections.observableArrayList("Bangkokbizs News", "Daily News", "Komchadluek", "Website", "E-News", "SMS", "TV Ads", "Others");
        comboBox.getSelectionModel().selectFirst();
        comboBox.setValue("Bangkokbizs News");
        comboBox.getItems().addAll(hereAboutUsChoices);

    }

    public static void setTourProgram(ComboBox comboBox){

        List<TourPackage> tourPackageList = manageableDatabase.getAllTourPackage();
        ObservableList<String> tourNameList = FXCollections.observableArrayList();

        for (TourPackage tourPackage: tourPackageList) {
            if(tourPackage.getStatus().equalsIgnoreCase("open"))
                tourNameList.add(tourPackage.getTourName());
        }
        comboBox.getSelectionModel().selectFirst();
        comboBox.setValue(tourNameList.get(0));
        comboBox.getItems().addAll(tourNameList);

    }
    public static void setStatusTourProgram(ComboBox comboBox){
        ObservableList<String> statusTourChoices = FXCollections.observableArrayList("close", "open");
        comboBox.getSelectionModel().selectFirst();
        comboBox.setValue("close");
        comboBox.getItems().addAll(statusTourChoices);
    }

    public static void setDatePickerFormat(DatePicker datePicker){
        String pattern = "dd-MM-yyyy";

        datePicker.setPromptText(pattern.toLowerCase());

        datePicker.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }
}
