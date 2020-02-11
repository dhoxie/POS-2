package com.scottsdaleair.data;

import java.util.Objects;

public class Invoice {
    private String invoiceID;
    private String date;
    // private Customer customer;
    private String customerID;
    private Vehicle vehicle;
    private Part[] parts;
    private String pubNotes;
    private String privNotes;

    public Invoice(String invoiceNum, String date, String customer, Vehicle vehicle, Part[] parts, String pubNotes, String privNotes) {
        this.invoiceID = invoiceNum;
        this.date = date;
        this.customerID = customer;
        this.vehicle = vehicle;
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

    // public Invoice(String invoiceNum, String date, Customer customer, Vehicle vehicle, Part[] parts, String pubNotes, String privNotes) {
    //     this.invoiceNum = invoiceNum;
    //     this.date = date;
    //     this.customer = customer;
    //     this.vehicle = vehicle;
    //     this.parts = parts;
    //     this.pubNotes = pubNotes;
    //     this.privNotes = privNotes;
    // }

    public String getInvoiceNum() {
        return this.invoiceID;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceID = invoiceNum;
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

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Part[] getParts() {
        return this.parts;
    }

    public void setParts(Part[] parts) {
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
        this.invoiceID = invoiceNum;
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

    public Invoice vehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public Invoice parts(Part[] parts) {
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
        return Objects.equals(invoiceID, invoice.invoiceID) && Objects.equals(date, invoice.date) && Objects.equals(customerID, invoice.customerID) && Objects.equals(vehicle, invoice.vehicle) && Objects.equals(parts, invoice.parts) && Objects.equals(pubNotes, invoice.pubNotes) && Objects.equals(privNotes, invoice.privNotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceID, date, customerID, vehicle, parts, pubNotes, privNotes);
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