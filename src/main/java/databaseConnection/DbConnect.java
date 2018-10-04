package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnect {
    public DbConnect() {
    }
    public static DbConnect getInstance(){
        return new DbConnect();
    }

    public Connection getConnection() {
        String connect_string = "jdbc:sqlite::resource:sale_system_database.db";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connect_string);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}


