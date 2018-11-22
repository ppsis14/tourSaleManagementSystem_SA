package databaseConnection;

import Table.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Sqlite_DB implements DatabaseManager {
    Connection connection;
    @Override
    public void insertData(Customer customer) throws SQLException {

        //insert data by SQLite
        connection = DbConnect.getConnection();
        String query = "insert into customer (TitlenameTH, FirstNameTH, LastNameTH, TitlenameENG, FirstNameENG, LastNameENG ," +
                "TitlenameOld, FirstnameOld, LastnameOld, Gender, Age, Date_of_birth, Passport_no, Expire_passport_date," +
                "Home_address,Cell_phone, Home_Tel, Fax, Email_address, Career ,Company_address,Work_Tel," +
                "Member_status, Disease, Food_allergy, Eat_beef, More_detail, Channel ) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = connection.prepareStatement(query);
            //clientProfile
            pst.setString(1, customer.getTitleNameTH());
            pst.setString(2, customer.getFirstNameTH());
            pst.setString(3, customer.getLastNameTH());
            pst.setString(4, customer.getTitleNameENG());
            pst.setString(5, customer.getFirstNameENG());
            pst.setString(6, customer.getLastNameENG());
            pst.setString(7, customer.getTitleNameOld());
            pst.setString(8, customer.getFirstNameOld());
            pst.setString(9, customer.getLastNameOld());
            pst.setString(10, customer.getGender());
            pst.setString(11, customer.getAge());
            pst.setString(12, customer.getDateOfBirth());
            pst.setString(13, customer.getPassport_no());
            pst.setString(14, customer.getExp_passport());
            //clientContact
            pst.setString(15, customer.getHomeAddress());
            pst.setString(16, customer.getCell_phone());
            pst.setString(17, customer.getHome_Tel());
            pst.setString(18, customer.getFax());
            pst.setString(19, customer.getEmail());
            pst.setString(20, customer.getCareer());
            pst.setString(21, customer.getCompanyAddress());
            pst.setString(22, customer.getWork_Tel());
            //more Info
            pst.setString(23, customer.getMemberStatus());
            pst.setString(24, customer.getDisease());
            pst.setString(25, customer.getFoodAllergy());
            pst.setString(26, customer.getEatBeef());
            pst.setString(27, customer.getMoreDetail());
            pst.setString(28, customer.getChannel());

            pst.executeUpdate();
            System.out.println("SQLite: Reservation data saved!");

            pst.close();
            connection.close();
            System.out.println("SQLite: closed!");

        } catch (SQLException e) {
            System.err.println("SQLite: Got an exception! ");
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void updateData (Customer customer){

    }

    @Override
    public void deleteData (Customer customer) {
 /*       //SQLite : Delete data
        connection = DbConnect.getConnection();
        PreparedStatement pst;
        String sql = "Delete from reserve_card_database where Reservation_code = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, reserv_codeDelete);
            pst.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
    }
        @Override
        public List<Customer> getAllCustomer () {
            return null;
        }
}

