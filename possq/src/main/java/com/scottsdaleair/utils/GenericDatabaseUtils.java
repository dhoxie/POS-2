package com.scottsdaleair.utils;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;

public class GenericDatabaseUtils<T> {

  private Class<T> type;
  public final String dbName = "userdat";

  public GenericDatabaseUtils(Class<T> type) {
    this.type = type;
  }

  private T[] searchCollection(MongoCollection<Document> collection,
      HashMap<String, String> queryMap) {
    Gson gson = new Gson();
    BasicDBObject match = new BasicDBObject();
    for (Map.Entry<String, String> query : queryMap.entrySet()) {
      match.append(query.getKey(), query.getValue());
    }
    FindIterable<Document> results = collection.find(match);
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
    return getFromDb(this.dbName, collectionName, queryMap);
  }

  /**
   * Get every object in the given collection of the given type.
   * 
   * @param collectionName String name of collection
   * @return Object[] containing search results
   */
  public T[] getEntireCollection(String collectionName) {
    MongoCollection<Document> collection = DatabaseUtils.client
        .getDatabase(this.dbName).getCollection(collectionName);
    return retrieveCollection(collection);
  }

  private T[] getFromDb(String dbname, String collectionName, HashMap<String, String> queryMap) {
    MongoDatabase database = DatabaseUtils.client.getDatabase(dbname);
    MongoCollection<Document> collection = database.getCollection(collectionName);
    return searchCollection(collection, queryMap);
  }
}
