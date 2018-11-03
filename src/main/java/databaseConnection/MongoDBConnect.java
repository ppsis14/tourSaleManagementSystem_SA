package databaseConnection;

import com.mongodb.*;

import java.util.List;

public class MongoDBConnect {

    public static DBCollection member,employee,reserve_card;

    public static void getMongoClient() {

        //connecting with server
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        mongoClient.getDatabase("sale_system_database");
        System.out.println("MongoDB connection server successfully!");

        //Connecting with database
        DB sale_system_database = mongoClient.getDB("sale_system_database");     //create database
        DB test = mongoClient.getDB("testDrop");
        System.out.println("MongoDB connect to database successfully!");
        System.out.println("Database Name >> " + sale_system_database.getName());

        // To get all database names
        List<String> dbNames = mongoClient.getDatabaseNames();
        System.out.println("DB name >> "+dbNames);

        // Add collection (table) to database
        member = sale_system_database.getCollection("member_database");
        employee = sale_system_database.getCollection("employee_database");
        reserve_card = sale_system_database.getCollection("reserve_card_database");
        System.out.println("MongoDB create collection successfully!");

        //(1)drop database
        //mongoClient.dropDatabase("testDrop");

        //(2)delete document in collection
        /*
        BasicDBObject delete = new BasicDBObject("firstNameENG","wipa");
        reserve_card.remove(delete);
        */

        //(3)Update database
        /*BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("password", 22222222));

        BasicDBObject searchQuery = new BasicDBObject().append("id", "5910406451");

        collection.update(searchQuery, newDocument);
        */

    }

}
