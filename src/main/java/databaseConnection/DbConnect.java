package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
    public static Connection getConnection() {
        Connection connection;
        String connect_string = "jdbc:sqlite::resource:sale_system_database.db";
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(connect_string);
            System.out.println("Opened database successfully");
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Can not open database");
            return null;
        }
        return connection;

    }

}


