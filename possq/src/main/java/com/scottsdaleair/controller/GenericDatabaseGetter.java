package com.scottsdaleair.controller;

import com.scottsdaleair.utils.GenericDatabaseUtils;
import java.util.HashMap;

public class GenericDatabaseGetter<T> {


  /**
   * Query the database with a hasmap of key/value pairs.
   * 
   * @param <T>      Type of data object
   * @param queryMap HashMap of key/value query terms
   * @param type     Class of data type (DataType.class)
   * @return
   */
  public static <T> T[] queryDB(HashMap<String, String> queryMap, Class<T> type) {
    GenericDatabaseUtils<T> gdu = new GenericDatabaseUtils<T>(type);
    String collectionName = type.getSimpleName().toLowerCase() + "s";
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
    GenericDatabaseUtils<T> gdu = new GenericDatabaseUtils<T>(type);
    String collectionName = type.getSimpleName().toLowerCase() + "s";
    return gdu.getEntireCollection(collectionName);
  }



}
