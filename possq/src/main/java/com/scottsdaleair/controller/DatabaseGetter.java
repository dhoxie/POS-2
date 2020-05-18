package com.scottsdaleair.controller;

import com.scottsdaleair.data.Customer;
import com.scottsdaleair.data.Invoice;
import com.scottsdaleair.data.Kit;
import com.scottsdaleair.data.Part;
import com.scottsdaleair.data.Service;
import com.scottsdaleair.data.Vehicle;
import com.scottsdaleair.utils.DataConverter;
import com.scottsdaleair.utils.DatabaseUtils;

public class DatabaseGetter {

  /**
   * Get all {@link Customer}s in the database.
   * 
   * @return {@link Customer}[]
   */
  public static Customer[] getAllCustomers() {
    Object[] results = DatabaseUtils.getEntireCollection("customers", Customer.class);
    return DataConverter.objToCust(results);
  }

  /**
   * Get all {@link Customer}s based on a (key,value) pair.
   * 
   * @param key Property of the customer to match
   * @param value Value of the property to match
   * @return {@link Customer}[]
   */
  public static Customer[] queryCustomers(String key, String value) {
    Object[] results = DatabaseUtils.getFromCollection("customers", key, value, Customer.class);
    return DataConverter.objToCust(results);
  }

  /**
   * Get all {@link Invoice}s in the database.
   * 
   * @return {@link Invoice}[]
   */
  public static Invoice[] getAllInvoices() {
    Object[] results = DatabaseUtils.getEntireCollection("invoices", Invoice.class);
    return DataConverter.objToInv(results);
  }

  /**
   * Get all {@link Invoice}s based on a (key,value) pair.
   * 
   * @param key Property of the Invoice to match
   * @param value Value of the property to match
   * @return {@link Invoice}[]
   */
  public static Invoice[] queryInvoices(String key, String value) {
    Object[] results = DatabaseUtils.getFromCollection("invoices", key, value, Invoice.class);
    return DataConverter.objToInv(results);
  }

  /**
   * Get all {@link Kit}s in the database.
   * 
   * @return {@link Kit}[]
   */
  public static Kit[] getAllKits() {
    Object[] results = DatabaseUtils.getEntireCollection("kits", Kit.class);
    return DataConverter.objToKit(results);
  }

  /**
   * Get all {@link Kit}s based on a (key,value) pair.
   * 
   * @param key Property of the Kit to match
   * @param value Value of the property to match
   * @return {@link Kit}[]
   */
  public static Kit[] queryKits(String key, String value) {
    Object[] results = DatabaseUtils.getFromCollection("kits", key, value, Kit.class);
    return DataConverter.objToKit(results);
  }

  /**
   * Get all {@link Part}s in the database.
   * 
   * @return {@link Part}[]
   */
  public static Part[] getAllParts() {
    Object[] results = DatabaseUtils.getEntireCollection("parts", Part.class);
    return DataConverter.objToPrt(results);
  }

  /**
   * Get all {@link Part}s based on a (key,value) pair.
   * 
   * @param key Property of the Part to match
   * @param value Value of the property to match
   * @return {@link Part}[]
   */
  public static Part[] queryParts(String key, String value) {
    Object[] results = DatabaseUtils.getFromCollection("parts", key, value, Part.class);
    return DataConverter.objToPrt(results);
  }

  /**
   * Get all {@link Service}s in the database.
   * 
   * @return {@link Service}[]
   */
  public static Service[] getAllServices() {
    Object[] results = DatabaseUtils.getEntireCollection("services", Service.class);
    return DataConverter.objToSvc(results);
  }

  /**
   * Get all {@link Service}s based on a (key,value) pair.
   * 
   * @param key Property of the Service to match
   * @param value Value of the property to match
   * @return {@link Service}[]
   */
  public static Service[] queryServices(String key, String value) {
    Object[] results = DatabaseUtils.getFromCollection("services", key, value, Service.class);
    return DataConverter.objToSvc(results);
  }

  /**
   * Get all {@link Vehicle}s in the database.
   * 
   * @return {@link Vehicle}[]
   */
  public static Vehicle[] getAllVehicles() {
    Object[] results = DatabaseUtils.getEntireCollection("vehicles", Vehicle.class);
    return DataConverter.objToVehc(results);
  }

  /**
   * Get all {@link Vehicle}s based on a (key,value) pair.
   * 
   * @param key Property of the Vehicle to match
   * @param value Value of the property to match
   * @return {@link Vehicle}[]
   */
  public static Vehicle[] queryVehicles(String key, String value) {
    Object[] results = DatabaseUtils.getFromCollection("vehicles", key, value, Vehicle.class);
    return DataConverter.objToVehc(results);
  }

}
