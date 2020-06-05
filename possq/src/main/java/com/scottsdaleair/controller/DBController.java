package com.scottsdaleair.controller;

import com.scottsdaleair.data.DatabaseObject;
import com.scottsdaleair.utils.DatabaseUtils;
import java.util.HashMap;

public class DBController<T> {


  /**
   * Query the database with a key/value pair.
   * 
   * @param <T>   Type of data object
   * @param key   Key to match to
   * @param value Value for key
   * @param type  Class of data type (DataType.class)
   * @return
   */
  public static <T> T[] queryDB(String key, String value, Class<T> type) {
    DatabaseUtils<T> gdu = new DatabaseUtils<T>(type);
    String collectionName = collFromType(type);
    T[] results = gdu.getFromCollection(collectionName, key, value);
    return results;
  }

  /**
   * Query the database with a hasmap of key/value pairs.
   * 
   * @param <T>      Type of data object
   * @param queryMap HashMap of key/value query terms
   * @param type     Class of data type (DataType.class)
   * @return
   */
  public static <T> T[] queryDB(HashMap<String, String> queryMap, Class<T> type) {
    DatabaseUtils<T> gdu = new DatabaseUtils<T>(type);
    String collectionName = collFromType(type);
    T[] results = gdu.getFromCollection(collectionName, queryMap);
    return results;
  }

  /**
   * Get all entries of a data type at once.
   * 
   * @param <T>  Type of the data being returned.
   * @param type Class of the data type (DataType.class)
   * @return
   */
  public static <T> T[] getAll(Class<T> type) {
    DatabaseUtils<T> gdu = new DatabaseUtils<T>(type);
    String collectionName = collFromType(type);
    return gdu.getEntireCollection(collectionName);
  }

  /**
   * Adds a given DatabaseObject to the database.
   * @param <T>   Type of data object
   * @param obj   Object to add to database
   * @param type  Class of the data type (DataType.class)
   */
  public static <T> void addToDB(DatabaseObject obj, Class<T> type) {
    String collectionName = collFromType(type);
    DatabaseUtils.addObjToCollection(collectionName, obj);
  }

  /**
   * Updates a given DatabaseObject in the database.
   * @param <T>   Type of data object
   * @param obj   Object to update in the database
   * @param type  Class of the data type (DataType.class)
   */
  public static <T> void updateInDB(DatabaseObject obj, Class<T> type) {
    String collectionName = collFromType(type);
    DatabaseUtils.updateObjInCollection(collectionName, obj);
  }

  /**
   * Get the number of elements in the database matching the given type.
   * 
   * @param <T>   Type of data object
   * @param type  Class of the data type (DataType.class)
   * @return
   */
  public static <T> long count(Class<T> type) {
    String collectionName = collFromType(type);
    return DatabaseUtils.getCollectionSize(collectionName);
  }

  private static <T> String collFromType(Class<T> type) {
    return type.getSimpleName().toLowerCase() + "s";
  }



}
