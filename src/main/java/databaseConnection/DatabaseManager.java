package databaseConnection;

import Table.Customer;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseManager {
    void insertData(Customer customer) throws SQLException;
    void updateData(Customer customer);
    void deleteData(Customer customer);
    List<Customer> getAllCustomer();
}
