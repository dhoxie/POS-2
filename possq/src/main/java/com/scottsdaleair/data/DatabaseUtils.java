package com.scottsdaleair.data;

import com.google.gson.Gson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class DatabaseUtils {
  /**
   * Adds an object to the specified Mongo collection.
   * @param collection  Collection to add object to
   * @param obj         The object to be added
   */
  public static void addObjToCollection(MongoCollection<Document> collection, Object obj) {
    Gson gson = new Gson();
    String obJson = gson.toJson(obj);
    Document newDoc = Document.parse(obJson);
    collection.insertOne(newDoc);
  }

  /**
   * Adds an object to a string-specified database and collection.
   * @param dbname          The name of the database
   * @param collectionName  The name of the collection
   * @param obj             The object to be added
   */
  public static void addObjToCollection(String dbname, String collectionName, Object obj) {
    MongoClient mongoClient = new MongoClient();

    MongoDatabase database = mongoClient.getDatabase(dbname);
    MongoCollection<Document> collection = database.getCollection(collectionName);
    addObjToCollection(collection, obj);
    mongoClient.close();
  }
}