package com.scottsdaleair.utils;

import static com.mongodb.client.model.Filters.eq;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Collation;
import com.mongodb.client.model.CollationStrength;
import com.scottsdaleair.data.DatabaseObject;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;

public class DatabaseUtils<T> {

  private Class<T> type;
  private static final String dbAddr = "73.42.132.222";
  private static final int dbPort = 27017;
  private static final String dbName = "userdat";
  private static final String backupDbAddress = "35.247.126.11";
  private static MongoClient client;
  private static final Collation collation = Collation.builder().locale("en")
      .collationStrength(CollationStrength.SECONDARY).build();

  static {
    try {
      client = new MongoClient(dbAddr, dbPort);
    } catch (Exception e) {
      client = null;
    }
    if (client == null) {
      try {
        client = null;
        client = new MongoClient(backupDbAddress, dbPort);
      } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Exception occured in creating singleton instance");
      }
    }
  }

  public DatabaseUtils(Class<T> type) {
    this.type = type;
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

  /**
   * Updates the specified object in the database by id.
   * 
   * @param collectionName  Name of the collection the object is in
   * @param toUpdate        The DatabaseObject to update
   */
  public static void updateObjInCollection(String collectionName, DatabaseObject toUpdate) {
    updateObjInDB(DatabaseUtils.dbName, collectionName, toUpdate);
  }

  private static void updateObjInCollection(MongoCollection<Document> collection,
      DatabaseObject obj) {
    Gson gson = new Gson();
    String obJson = gson.toJson(obj);
    Document toUpdate = Document.parse(obJson);
    collection.replaceOne(eq("id", obj.getId()), toUpdate);
  }

  private static void updateObjInDB(String dbname, String collectionName, DatabaseObject obj) {
    MongoDatabase database = DatabaseUtils.client.getDatabase(dbname);
    MongoCollection<Document> collection = database.getCollection(collectionName);
    updateObjInCollection(collection, obj);
  }

  public static long getCollectionSize(String collectionName) {
    MongoDatabase database = DatabaseUtils.client.getDatabase(DatabaseUtils.dbName);
    return database.getCollection(collectionName).count();
  }

  private T[] searchCollection(MongoCollection<Document> collection,
      HashMap<String, String> queryMap) {
    Gson gson = new Gson();
    BasicDBObject match = new BasicDBObject();
    for (Map.Entry<String, String> query : queryMap.entrySet()) {
      match.append(query.getKey(), query.getValue());
    }
    FindIterable<Document> results = collection.find(match).collation(DatabaseUtils.collation);
    ArrayList<T> retListAr = new ArrayList<>();
    for (Document d : results) {
      retListAr.add(gson.fromJson(d.toJson(), this.type));
      System.out.print(d + "");
    }
    @SuppressWarnings("unchecked")
    T[] a = (T[]) Array.newInstance(this.type, retListAr.size());
    return retListAr.toArray(a);
  }

  private T[] retrieveCollection(MongoCollection<Document> collection) {
    Gson gson = new Gson();
    FindIterable<Document> results = collection.find();
    ArrayList<Object> retListAr = new ArrayList<>();
    for (Document d : results) {
      retListAr.add(gson.fromJson(d.toJson(), this.type));
    }
    @SuppressWarnings("unchecked")
    T[] a = (T[]) Array.newInstance(this.type, retListAr.size());
    return retListAr.toArray(a);
  }

  /**
   * Returns an object array of results.
   * 
   * @param collectionName Name of collection to search
   * @param key            Key field to search
   * @param value          Value of key to search
   * @return Object[] containing search results
   */
  public T[] getFromCollection(String collectionName, String key, String value) {
    HashMap<String, String> singleQuery = new HashMap<>();
    singleQuery.put(key, value);
    return getFromCollection(collectionName, singleQuery);
  }

  /**
   * Returns an object array of results.
   * 
   * @param collectionName Name of collection to search
   * @param queryMap       Set of key/value pairs to search by.
   * @return Object[] containing search results
   */
  public T[] getFromCollection(String collectionName, HashMap<String, String> queryMap) {
    return getFromDb(DatabaseUtils.dbName, collectionName, queryMap);
  }

  /**
   * Get every object in the given collection of the given type.
   * 
   * @param collectionName String name of collection
   * @return Object[] containing search results
   */
  public T[] getEntireCollection(String collectionName) {
    MongoCollection<Document> collection = DatabaseUtils.client.getDatabase(DatabaseUtils.dbName)
        .getCollection(collectionName);
    return retrieveCollection(collection);
  }

  private T[] getFromDb(String dbname, String collectionName, HashMap<String, String> queryMap) {
    MongoDatabase database = DatabaseUtils.client.getDatabase(dbname);
    MongoCollection<Document> collection = database.getCollection(collectionName);
    return searchCollection(collection, queryMap);
  }
}
