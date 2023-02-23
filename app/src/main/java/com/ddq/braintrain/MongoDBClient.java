package com.ddq.braintrain;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;


public class MongoDBClient {

    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDBClient() {
        // Create a MongoClient instance using a connection string.
        ConnectionString connString = new ConnectionString("mongodb+srv://hongdangcseiu:<hongdango4qifa>@cluster0.axcgxrc.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connString).build();
        this.mongoClient = MongoClients.create(settings);

        // Get a handle to the "test" database.
        this.database = mongoClient.getDatabase("test");
    }

    // Methods for interacting with the database go here
    public boolean addUser(String username, String password) {
        // Get a handle to the "users" collection in the database.
        MongoCollection<Document> collection = database.getCollection("users");

        // Check if a user with the same username already exists.
        if (collection.find(new Document("username", username)).first() != null) {
            return false;  // User already exists, return false to indicate failure.
        }

        // Create a new user document and insert it into the "users" collection.
        Document newUser = new Document("username", username).append("password", password);
        collection.insertOne(newUser);

        return true;  // User added successfully, return true to indicate success.
    }
}

