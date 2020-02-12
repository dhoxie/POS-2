package com.scottsdaleair.data;

import java.util.Objects;

public class Invoice {
    private String id;
    private String date;
    // private Customer customer;
    private String customerID;
    private String vehicleVin;
    private String[] parts;
    private String pubNotes;
    private String privNotes;

    public Invoice(String invoiceNum, String date, String customer, String vehicle, String[] parts, String pubNotes, String privNotes) {
        this.id = invoiceNum;
        this.date = date;
        this.customerID = customer;
        this.vehicleVin = vehicle;
        this.parts = parts;
        this.pubNotes = pubNotes;
        this.privNotes = privNotes;
    }

    public String getCustomer() {
        return this.customerID;
    }

    public void setCustomer(String customer) {
        this.customerID = customer;
    }

    public Invoice customer(String customer) {
        this.customerID = customer;
        return this;
    }

    public Invoice() {
    }

    // public Invoice(String invoiceNum, String date, Customer customer, Vehicle vehicle, String[] parts, String pubNotes, String privNotes) {
    //     this.invoiceNum = invoiceNum;
    //     this.date = date;
    //     this.customer = customer;
    //     this.vehicle = vehicle;
    //     this.parts = parts;
    //     this.pubNotes = pubNotes;
    //     this.privNotes = privNotes;
    // }

    public String getInvoiceNum() {
        return this.id;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.id = invoiceNum;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // public Customer getCustomer() {
    //     return this.customer;
    // }

    // public void setCustomer(Customer customer) {
    //     this.customer = customer;
    // }

    public String getVehicle() {
        return this.vehicleVin;
    }

    public void setVehicle(String vehicle) {
        this.vehicleVin = vehicle;
    }

    public String[] getParts() {
        return this.parts;
    }

    public void setParts(String[] parts) {
        this.parts = parts;
    }

    public String getPubNotes() {
        return this.pubNotes;
    }

    public void setPubNotes(String pubNotes) {
        this.pubNotes = pubNotes;
    }

    public String getPrivNotes() {
        return this.privNotes;
    }

    public void setPrivNotes(String privNotes) {
        this.privNotes = privNotes;
    }

    public Invoice invoiceNum(String invoiceNum) {
        this.id = invoiceNum;
        return this;
    }

    public Invoice date(String date) {
        this.date = date;
        return this;
    }

    // public Invoice customer(Customer customer) {
    //     this.customer = customer;
    //     return this;
    // }

    public Invoice vehicle(String vehicle) {
        this.vehicleVin = vehicle;
        return this;
    }

    public Invoice parts(String[] parts) {
        this.parts = parts;
        return this;
    }

    public Invoice pubNotes(String pubNotes) {
        this.pubNotes = pubNotes;
        return this;
    }

    public Invoice privNotes(String privNotes) {
        this.privNotes = privNotes;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Invoice)) {
            return false;
        }
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) && Objects.equals(date, invoice.date) && Objects.equals(customerID, invoice.customerID) && Objects.equals(vehicleVin, invoice.vehicleVin) && Objects.equals(parts, invoice.parts) && Objects.equals(pubNotes, invoice.pubNotes) && Objects.equals(privNotes, invoice.privNotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, customerID, vehicleVin, parts, pubNotes, privNotes);
    }

    @Override
    public String toString() {
        return "{" +
            " invoiceNum='" + getInvoiceNum() + "'" +
            ", date='" + getDate() + "'" +
            ", customer='" + getCustomer() + "'" +
            ", vehicle='" + getVehicle() + "'" +
            ", parts='" + getParts() + "'" +
            ", pubNotes='" + getPubNotes() + "'" +
            ", privNotes='" + getPrivNotes() + "'" +
            "}";
    }

    
}