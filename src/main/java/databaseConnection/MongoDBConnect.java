package databaseConnection;

import com.mongodb.*;

import java.util.List;

public class MongoDBConnect {

    public static DBCollection member,employee,reserve_card;
    private MongoClient mongoClient = null;

    public static void getMongoClient() {

        //connecting with server
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        mongoClient.getDatabase("sale_system_database");
        System.out.println("MongoDB: Connection server successfully!");

        //Connecting with database
        DB sale_system_database = mongoClient.getDB("sale_system_database");     //create database
        //DB test = mongoClient.getDB("testDrop");
        System.out.println("MongoDB: Connect to database successfully!");
        System.out.println("MongoDB: Database Name >> " + sale_system_database.getName());

        // To get all database names
        List<String> dbNames = mongoClient.getDatabaseNames();
        System.out.println("MongoDB: DB name >> "+dbNames);

        // Add collection (table) to database
        member = sale_system_database.getCollection("member_database");
        employee = sale_system_database.getCollection("employee_database");
        reserve_card = sale_system_database.getCollection("reserve_card_database");
        System.out.println("MongoDB: Create collection successfully!");



    }

    public void dropDatabase(){
        //(drop database
        mongoClient.dropDatabase("testDrop");
    }
}
