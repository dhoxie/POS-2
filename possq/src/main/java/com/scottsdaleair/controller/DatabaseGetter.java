package com.scottsdaleair.controller;

import com.scottsdaleair.data.*;
import com.scottsdaleair.utils.DatabaseUtils;
import com.scottsdaleair.utils.DataConverter;

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
     * @param key
     * @param value
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
     * @param key
     * @param value
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
     * @param key
     * @param value
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
     * @param key
     * @param value
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
     * @param key
     * @param value
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
     * @param key
     * @param value
     * @return {@link Vehicle}[]
     */
    public static Vehicle[] queryVehicles(String key, String value) {
        Object[] results = DatabaseUtils.getFromCollection("vehicles", key, value, Vehicle.class);
        return DataConverter.objToVehc(results);
    }

}