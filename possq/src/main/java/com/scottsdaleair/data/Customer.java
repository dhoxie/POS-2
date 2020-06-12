package com.scottsdaleair.data;

import com.scottsdaleair.controller.DBController;
import com.scottsdaleair.utils.DataUtils;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents a customer. Private field {@link Customer#id}
 */
public class Customer implements DatabaseObject {
  private String id;
  private String fname;
  private String lname;
  private String email;
  private String address;
  private PhoneNumber[] phones;
  private String[] invoices;
  private String[] vehicleVins;

  /**
   * Class representing a customer.
   * 
   * @param id          Unique ID of this customer
   * @param fname       First name of this customer
   * @param lname       Last name of this customer
   * @param email       Email address associated with this customer
   * @param address     Physical address associated with this customer
   * @param phones      An array of phone numbers associated with this customer
   * @param history     An array of {@code Invoice} id's associated with this
   *                    customer
   * @param vehicleVins An array of VIN numbers for {@code Vehicle}s associated
   *                    with this customer
   */
  public Customer(final String id, final String fname,
      final String lname, final String email, final String address,
      final PhoneNumber[] phones, final String[] history, final String[] vehicleVins) {
    // super(id);
    this.id = id;
    this.fname = fname;
    this.lname = lname;
    this.email = email;
    this.address = address;
    this.phones = phones;
    this.invoices = history;
    this.vehicleVins = vehicleVins;
  }

  public String[] getHistID() {
    return this.invoices;
  }

  public void setHistID(final String[] history) {
    this.invoices = history;
  }

  public String[] getVehicleVins() {
    return this.vehicleVins;
  }

  public void setVehicleVins(final String[] vehicleVins) {
    this.vehicleVins = vehicleVins;
  }

  public PhoneNumber[] getPhones() {
    return this.phones;
  }

  public void setPhones(final PhoneNumber[] phones) {
    this.phones = phones;
  }

  public String getFname() {
    return this.fname;
  }

  public void setFname(final String fname) {
    this.fname = fname;
  }

  public String getLname() {
    return this.lname;
  }

  public void setLname(final String lname) {
    this.lname = lname;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public PhoneNumber[] getPhone() {
    return this.phones;
  }

  public void setPhone(final PhoneNumber[] phone) {
    this.phones = phone;
  }

  // Add+remove referenced data arrays

  /**
   * Add a referenced invoice to the Customer.
   * 
   * @param invoiceID The ID of the invoice to be added
   */
  public void addInvoice(String invoiceID) {
    this.invoices = DataUtils.addToArray(invoiceID, this.invoices);
  }

  /**
   * Remove a referenced invoice from the Customer.
   * 
   * @param invoiceID The ID of the invoice to be removed
   * @return The index of the removed ID or -1 if not found.
   */
  public int removeInvoice(String invoiceID) {
    int index = Arrays.asList(this.invoices).indexOf(invoiceID);
    if (index >= 0) {
      this.invoices = DataUtils.removeFromArray(index, this.invoices);
    }
    return index;
  }

  /**
   * Add a referenced vehicle to the Customer.
   * 
   * @param vehicleID The ID of the vehicle to be added
   */
  public void addVehicle(String vehicleID) {
    this.vehicleVins = DataUtils.addToArray(vehicleID, this.vehicleVins);
  }

  /**
   * Remove a referenced vehicle from the Customer.
   * 
   * @param vehicleID The ID of the vehicle to be removed
   * @return The index of the removed ID or -1 if not found.
   */
  public int removeVehicle(String vehicleID) {
    int index = Arrays.asList(this.vehicleVins).indexOf(vehicleID);
    if (index >= 0) {
      this.vehicleVins = DataUtils.removeFromArray(index, this.vehicleVins);
    }
    return index;
  }

  /**
   * Get the customer object from db by id.
   * 
   * @param customerId id of the customer
   * @return
   */
  public static Customer getFromDb(String customerId) {
    return DBController.queryDB("id", customerId, Customer.class)[0];
  }

  @Override
  public boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Customer)) {
      return false;
    }
    final Customer customer = (Customer) o;
    return this.getID() == customer.getID()
        && Objects.equals(fname, customer.fname)
        && Objects.equals(lname, customer.lname)
        && Objects.equals(email, customer.email)
        && Objects.equals(address, customer.address)
        && Objects.equals(phones, customer.phones);
  }

  @Override
  public final int hashCode() {
    return Objects.hash(getID(), fname, lname, email, address, phones);
  }

  @Override
  public final String toString() {
    return "{" + " id='" + getID()
        + "'" + ", fname='" + getFname()
        + "'" + ", lname='" + getLname()
        + "'" + ", email='" + getEmail()
        + "'" + ", address='" + getAddress()
        + "'" + ", phone='" + getPhone() + "'" + "}";
  }

  @Override
  public String getID() {
    return this.id;
  }
}
