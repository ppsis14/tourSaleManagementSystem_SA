package databaseConnection;

import Table.Customer;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;

import java.util.List;

import static databaseConnection.MongoDBConnect.reserve_card;

public class Mongo_DB implements DatabaseManager {
    @Override
    public void insertData(Customer customer) {
        // insert data by MongoDB

        MongoDBConnect.getMongoClient();        //connect to MongoDB

        BasicDBObject clientProfile = new BasicDBObject()
                .append("TitlenameTH",customer.getTitleNameTH())
                .append("FirstNameTH",customer.getFirstNameTH())
                .append("LastNameTH",customer.getLastNameTH())
                .append("TitlenameENG",customer.getTitleNameENG())
                .append("FirstNameENG",customer.getFirstNameENG())
                .append("LastNameENG",customer.getLastNameENG())
                .append("TitlenameOld",customer.getTitleNameOld())
                .append("FirstnameOld",customer.getFirstNameOld())
                .append("LastnameOld",customer.getLastNameOld())
                .append("Gender",customer.getGender())
                .append("Age",customer.getAge())
                .append("Date_of_birth",customer.getDateOfBirth())
                .append("Passport_no",customer.getPassport_no())
                .append("Expire_passport_date",customer.getExp_passport())
                //client contact
                .append("Home_address",customer.getHomeAddress())
                .append("Cell_phone",customer.getCell_phone())
                .append("Home_Tel",customer.getHome_Tel())
                .append("Fax",customer.getFax())
                .append("Email_address",customer.getEmail())
                .append("Career",customer.getCareer())
                .append("Company_address",customer.getCompanyAddress())
                .append("Work_Tel",customer.getWork_Tel())
                //moreInfo
                .append("Member_status",customer.getMemberStatus())
                .append("Disease",customer.getDisease())
                .append("Food_allergy",customer.getFoodAllergy())
                .append("Eat_beef",customer.getEatBeef())
                .append("More_detail",customer.getMoreDetail())
                .append("Channel",customer.getChannel());


        reserve_card.insert(clientProfile);
        System.out.println(reserve_card);

        //show list inserted on display
        Cursor cursor = reserve_card.find();
        while (cursor.hasNext())
            System.out.println(cursor.next());
        System.out.println("MongoDB: Reservation saved!");

    }

    @Override
    public void updateData(Customer customer) {
//        // update data by MongoDB
//        MongoDBConnect.getMongoClient();
//
//        BasicDBObject oldDoc = new BasicDBObject().append("Reservation_code",reservCode);
//        BasicDBObject newEdit = new BasicDBObject();
//
//        newEdit.append("$set" , new BasicDBObject()
//                //.append("Tour_ID",)
//                .append("Reservation_code",reserveCodeED.getText())
//                .append("Departure_date",departureDateED.getEditor().getText())
//                .append("TitlenameTH",nameTitleTHClientED.getSelectionModel().getSelectedItem())
//                .append("FirstNameTH",firstNameTHClientED.getText())
//                .append("LastNameTH",lastNameTHClientED.getText())
//                .append("TitlenameENG",nameTitleENClientED.getSelectionModel().getSelectedItem())
//                .append("FirstNameENG",firstNameENClientED.getText())
//                .append("LastNameENG",lastNameENClientED.getText())
//                .append("TitlenameOld",oldNameTitleTHClientED.getSelectionModel().getSelectedItem())
//                .append("FirstnameOld",oldFirstNameClientED.getText())
//                .append("LastnameOld",oldLastNameClientED.getText())
//                .append("Gender",genderChoiceED.getSelectionModel().getSelectedItem())
//                .append("Age",ageED.getText())
//                .append("Date_of_birth",dateOfBirthClientED.getEditor().getText())
//                .append("Passport_no",passportClientED.getText())
//                .append("Expire_passport_date",expPassportDateED.getEditor().getText())
//                //clientContact
//                .append("Full_home_address",homeAddClientED.getText())
//                .append("Home_country",countryClientED.getText())
//                .append("Home_zip_code",homeZipCodeClientED.getText())
//                .append("Cell_phone",cellphoneClientED.getText())
//                .append("Home_Tel",homeTelClientED.getText())
//                .append("Fax",homeFaxClientED.getText())
//                .append("Email_address",emailClientED.getText())
//                .append("Career",careerClientED.getText())
//                .append("Company_name",compNameClientED.getText())
//                .append("Company_address",compAddClientED.getText())
//                .append("Company_country",compCountryClientED.getText())
//                .append("Company_zip_code", compZipCodeClientED.getText())
//                .append("Work_Tel",workTelClientED.getText())
//                //moreInfo
//                .append("Member_status",memberChioceED.isSelected() ? memberChioceED.getText() : notMemberChioceED.getText())
//                .append("Disease",underlyingDiseaseED.getText())
//                .append("Food_allergy",foodAllergyED.getText())
//                .append("Eat_beef",eatBeefYED.isSelected() ? eatBeefYED.getText() : eatBeefNED.getText())
//                .append("More_detail",moreDetailED.getText())
//                .append("Channel",getChannelList().toString()));
//
//        reserve_card.update(oldDoc,newEdit);
//
//        //show list update on display
//        Cursor cursor = reserve_card.find();
//        while (cursor.hasNext())
//            System.out.println(cursor.next());
//        System.out.println("MongoDB: Reservation update!");


    }

    @Override
    public void deleteData(Customer customer) {
//        //MongoDB : Delete data
//        String reserv_codeDelete = tableTourCheck.getSelectionModel().getSelectedItem().getReservCode();
//
//        BasicDBObject delete = new BasicDBObject("Reservation_code",reserv_codeDelete);
//        reserve_card.remove(delete);
//
//        Cursor cursor = reserve_card.find();
//        while (cursor.hasNext())
//            System.out.println(cursor.next());
    }

    @Override
    public List<Customer> getAllCustomer() {
        return null;
    }
}
