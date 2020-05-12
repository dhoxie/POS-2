package com.scottsdaleair.utils;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Collation;
import com.mongodb.client.model.CollationStrength;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.bson.Document;
import com.scottsdaleair.utils.Config; 
public class DatabaseUtils {

  private static final String dbAddr = Config.getConfig().getPrimaryDatabaseIp();
  private static final int dbPort = Integer.parseInt(Config.getConfig().getPrimaryDatabasePort());
  public static final String dbName = "userdat";
  public static final String backupDbAddress = Config.getConfig().getSecondaryDatabaseIp();
  public static final int backupDbPort = Integer.parseInt(Config.getConfig().getSecondaryDatabasePort());
  private static MongoClient client;
  private static final Collation collation =
      Collation.builder().locale("en").collationStrength(CollationStrength.SECONDARY).build();

  static {
    try {
      client = new MongoClient(dbAddr, dbPort);
    } catch (Exception e) {
      client = null;
    }
    if (client == null) {
      try {
        client = null;
        client = new MongoClient(backupDbAddress, backupDbPort);
      } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Exception occured in creating singleton instance");
      }
    }
  }

  /**
   * Adds an object to the specified Mongo collection.
   * 
   * @param collection Collection to add object to
   * @param obj        The object to be added
   */
  public static void addObjToCollection(MongoCollection<Document> collection, Object obj) {
    Gson gson = new Gson();
    String obJson = gson.toJson(obj);
    Document newDoc = Document.parse(obJson);
    collection.insertOne(newDoc);
  }

  /**
   * Adds an object to the default database and string-specified collection.
   * 
   * @param collectionName The name of the collection
   * @param obj            The object to be added
   */
  public static void addObjToCollection(String collectionName, Object obj) {
    addObjToDb(DatabaseUtils.dbName, collectionName, obj);
  }

  /**
   * Adds an object to a string-specified database and collection.
   * 
   * @param dbname         The name of the database
   * @param collectionName The name of the collection
   * @param obj            The object to be added
   */
  private static void addObjToDb(String dbname, String collectionName, Object obj) {
    MongoDatabase database = DatabaseUtils.client.getDatabase(dbname);
    MongoCollection<Document> collection = database.getCollection(collectionName);
    addObjToCollection(collection, obj);
  }

  private static Object[] searchCollection(MongoCollection<Document> collection, String key,
      String value, Type t) {
    Gson gson = new Gson();
    Document match = new Document(key, value);
    FindIterable<Document> results = collection.find(match).collation(collation);
    ArrayList<Object> retListAr = new ArrayList<>();
    for (Document d : results) {
      retListAr.add(gson.fromJson(d.toJson(), t));
    }
    return retListAr.toArray();
  }

  private static Object[] retrieveCollection(MongoCollection<Document> collection, Type t) {
    Gson gson = new Gson();
    FindIterable<Document> results = collection.find();
    ArrayList<Object> retListAr = new ArrayList<>();
    for (Document d : results) {
      retListAr.add(gson.fromJson(d.toJson(), t));
    }
    return retListAr.toArray();
  }

  /**
   * Returns an object array of results.
   * 
   * @param collectionName Name of collection to search
   * @param key            Key field to search
   * @param value          Value of key to search
   * @param t              Type of object returned
   * @return Object[] containing search results
   */
  public static Object[] getFromCollection(String collectionName, String key, String value,
      Type t) {

    return getFromDb(DatabaseUtils.dbName, collectionName, key, value, t);
  }

  /**
   * Get every object in the given collection of the given type.
   * 
   * @param collectionName String name of collection
   * @param t              Type of class to retrieve
   * @return Object[] containing search results
   */
  public static Object[] getEntireCollection(String collectionName, Type t) {
    MongoCollection<Document> collection =
        DatabaseUtils.client.getDatabase(DatabaseUtils.dbName).getCollection(collectionName);
    return retrieveCollection(collection, t);
  }

  private static Object[] getFromDb(String dbname, String collectionName, String key, String value,
      Type t) {
    MongoDatabase database = DatabaseUtils.client.getDatabase(dbname);
    MongoCollection<Document> collection = database.getCollection(collectionName);
    return searchCollection(collection, key, value, t);
  }
}
