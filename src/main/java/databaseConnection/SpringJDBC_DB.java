package databaseConnection;

import Table.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class SpringJDBC_DB implements DatabaseManager {
    private JdbcTemplate jdbcTemplate;

    public SpringJDBC_DB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertData(Customer customer) {

        String query = "insert into customer (TitlenameTH, FirstNameTH, LastNameTH, TitlenameENG, FirstNameENG, LastNameENG ," +
                "TitlenameOld, FirstnameOld, LastnameOld, Gender, Age, Date_of_birth, Passport_no, Expire_passport_date," +
                "Home_address,Cell_phone, Home_Tel, Fax, Email_address, Career ,Company_address,Work_Tel," +
                "Member_status, Disease, Food_allergy, Eat_beef, More_detail, Channel ) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] data = new Object[]{
                customer.getTitleNameTH(), customer.getFirstNameTH(), customer.getLastNameTH(),
                customer.getTitleNameENG(), customer.getFirstNameENG(), customer.getLastNameENG(),
                customer.getTitleNameOld(), customer.getFirstNameOld(), customer.getLastNameOld(),
                customer.getGender(), customer.getAge(), customer.getDateOfBirth(), customer.getPassport_no(),
                customer.getExp_passport(), customer.getHomeAddress(), customer.getCell_phone(),
                customer.getHome_Tel(), customer.getFax(), customer.getEmail(),
                customer.getCareer(), customer.getCompanyAddress(), customer.getWork_Tel(),
                customer.getMemberStatus(), customer.getDisease(), customer.getFoodAllergy(),
                customer.getEatBeef(), customer.getMoreDetail(), customer.getChannel()};
        jdbcTemplate.update(query, data);
    }

    @Override
    public void updateData(Customer customer) {
        String updateQuery = "update customer set TitlenameTH = ? , FirstNameTH = ?, LastNameTH = ?, TitlenameENG = ?, FirstNameENG = ?, LastNameENG = ?,TitlenameOld = ?, FirstnameOld = ?, LastnameOld = ?, " +
                "Gender = ?, Age = ?, Date_of_birth = ?, Passport_no = ?, Expire_passport_date = ?, Home_address = ?,Cell_phone = ?, Home_Tel = ?, Fax = ?, " +
                "Email_address = ?, Career = ? ,Company_address = ?,Work_Tel = ?,Member_status = ?, Disease = ?, Food_allergy = ?,Eat_beef = ?,More_detail = ?, Channel = ? where customer_id";
        Object[] data = new Object[]{
                customer.getTitleNameTH(), customer.getFirstNameTH(), customer.getLastNameTH(),
                customer.getTitleNameENG(), customer.getFirstNameENG(), customer.getLastNameENG(),
                customer.getTitleNameOld(), customer.getFirstNameOld(), customer.getLastNameOld(),
                customer.getGender(), customer.getAge(), customer.getDateOfBirth(), customer.getPassport_no(),
                customer.getExp_passport(), customer.getHomeAddress(), customer.getCell_phone(),
                customer.getHome_Tel(), customer.getFax(), customer.getEmail(),
                customer.getCareer(), customer.getCompanyAddress(), customer.getWork_Tel(),
                customer.getMemberStatus(), customer.getDisease(), customer.getFoodAllergy(),
                customer.getEatBeef(), customer.getMoreDetail(), customer.getChannel()};
        jdbcTemplate.update(updateQuery, data);
    }

    @Override
    public void deleteData(Customer customer) {
        String deleteQuery = "Delete From customer Where customer_id = ?";
        jdbcTemplate.update(deleteQuery, customer.getCustomerID());
    }

    @Override
    public List<Customer> getAllCustomer() {
        String query = "select * from customer";

        List<Customer> customerList = jdbcTemplate.query(query, new CustomerRowMapper());
        return customerList;
    }

    class CustomerRowMapper implements RowMapper<Customer> {
        public Customer mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            Customer customer = new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("TitlenameTH"),
                    rs.getString("FirstNameTH"),
                    rs.getString("LastNameTH"),
                    rs.getString("TitlenameENG"),
                    rs.getString("FirstNameENG"),
                    rs.getString("lastNameENG"),
                    rs.getString("TitlenameOld"),
                    rs.getString("FirstnameOld"),
                    rs.getString("LastnameOld"),
                    rs.getString("Gender"),
                    rs.getString("Age"),
                    rs.getString("Date_of_birth"),
                    rs.getString("Passport_nope"),
                    rs.getString("Expire_passport_date"),
                    rs.getString("HomeAddress"),
                    rs.getString("Cell_phone"),
                    rs.getString("Home_Tel"),
                    rs.getString("Fax"),
                    rs.getString("Email_address"),
                    rs.getString("Career"),
                    rs.getString("CompanyAddress"),
                    rs.getString("Work_Tel"),
                    rs.getString("Member_tatus"),
                    rs.getString("Disease"),
                    rs.getString("Food_allergy"),
                    rs.getString("Eat_beef"),
                    rs.getString("More_detail"),
                    rs.getString("Channel"));

            return customer;
        }
    }
}

