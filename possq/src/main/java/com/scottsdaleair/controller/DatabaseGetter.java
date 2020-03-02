package com.scottsdaleair.controller;

import com.scottsdaleair.data.*;
import com.scottsdaleair.utils.DatabaseUtils;

public class DatabaseGetter {

    public static Customer[] getAllCustomers() {
        return (Customer[])DatabaseUtils.getEntireCollection("customers", Customer.class);
    }

    public static Invoice[] getAllInvoices() {
        return (Invoice[])DatabaseUtils.getEntireCollection("invoices", Invoice.class);
    }

    public Customer[] queryCustomers(String key, String value) {
        return (Customer[]) DatabaseUtils.getFromCollection("customers", key, value, Customer.class);
        // return null;
    }

    public Invoice[] queryInvoices(String key, String value) {
        return (Invoice[]) DatabaseUtils.getFromCollection("invoices", key, value, Invoice.class);
    }
}