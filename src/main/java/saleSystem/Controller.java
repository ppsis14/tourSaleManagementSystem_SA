package saleSystem;

import databaseConnection.DatabaseManager;
import databaseConnection.SpringJDBC_DB;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Controller {
    static ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

    public static DatabaseManager databaseManager = context.getBean("SpringJDBC_DB", SpringJDBC_DB.class);
}
