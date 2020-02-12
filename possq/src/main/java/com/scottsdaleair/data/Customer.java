package com.scottsdaleair.data;

import java.util.Objects;

public class Customer {
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String address;
    private PhoneNumber[] phones;

    private String[] history; // Contains an array of invoice ID's
    private String[] vehicleVINs;
    // private Invoice[] history;
    // private Vehicle[] vehicles;

    public Customer() {
    }

    public Customer(int id, String fname, String lname, String email, String address, PhoneNumber[] phones,
            String[] history, String[] vehicleVIN) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.address = address;
        this.phones = phones;
        this.history = history;
        this.vehicleVINs = vehicleVIN;
    }

    public String[] getHistID() {
        return this.history;
    }

    public void setHistID(String[] history) {
        this.history = history;
    }

    public String[] getVehicleVIN() {
        return this.vehicleVINs;
    }

    public void setVehicleVIN(String[] vehicleVIN) {
        this.vehicleVINs = vehicleVIN;
    }

    public Customer histID(String[] histID) {
        this.history = histID;
        return this;
    }

    public Customer vehicleVIN(String[] vehicleVIN) {
        this.vehicleVINs = vehicleVIN;
        return this;
    }

    public PhoneNumber[] getPhones() {
        return this.phones;
    }

    public void setPhones(PhoneNumber[] phones) {
        this.phones = phones;
    }

    // public Invoice[] getHistory() {
    // return this.history;
    // }

    // public void setHistory(Invoice[] history) {
    // this.history = history;
    // }

    // public Vehicle[] getVehicles() {
    // return this.vehicles;
    // }

    // public void setVehicles(Vehicle[] vehicles) {
    // this.vehicles = vehicles;
    // }

    public Customer phones(PhoneNumber[] phones) {
        this.phones = phones;
        return this;
    }

    // public Customer history(Invoice[] history) {
    // this.history = history;
    // return this;
    // }

    // public Customer vehicles(Vehicle[] vehicles) {
    // this.vehicles = vehicles;
    // return this;
    // }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PhoneNumber[] getPhone() {
        return this.phones;
    }

    public void setPhone(PhoneNumber[] phone) {
        this.phones = phone;
    }

    public Customer id(int id) {
        this.id = id;
        return this;
    }

    public Customer fname(String fname) {
        this.fname = fname;
        return this;
    }

    public Customer lname(String lname) {
        this.lname = lname;
        return this;
    }

    public Customer email(String email) {
        this.email = email;
        return this;
    }

    public Customer address(String address) {
        this.address = address;
        return this;
    }

    public Customer phone(PhoneNumber[] phone) {
        this.phones = phone;
        return this;
    }

    public void addHistory(Invoice invoice) {
    String[] newInvoiceArr = new String[this.history.length+1];
    newInvoiceArr[this.history.length] = invoice.getInvoiceNum();
    this.history = newInvoiceArr;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(fname, customer.fname) && Objects.equals(lname, customer.lname)
                && Objects.equals(email, customer.email) && Objects.equals(address, customer.address)
                && Objects.equals(phones, customer.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fname, lname, email, address, phones);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", fname='" + getFname() + "'" + ", lname='" + getLname() + "'"
                + ", email='" + getEmail() + "'" + ", address='" + getAddress() + "'" + ", phone='" + getPhone() + "'"
                + "}";
    }
}