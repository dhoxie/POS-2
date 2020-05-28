package com.scottsdaleair.data;

import com.scottsdaleair.utils.DatabaseUtils;
import java.util.Objects;

/**
 * This class represents a customer. Private field {@link Customer#id}
 */
public class Customer {
  private String id;

  private String fname;
  private String lname;
  private String email;
  private String address;
  private PhoneNumber[] phones;
  private String[] history;
  private String[] vehicleVins;

  public Customer() {
  }

  /**
   * Class representing a customer.
   * @param id          Unique ID of this customer
   * @param fname       First name of this customer
   * @param lname       Last name of this customer
   * @param email       Email address associated with this customer
   * @param address     Physical address associated with this customer
   * @param phones      An array of phone numbers associated with this customer
   * @param history     An array of {@code Invoice} id's associated with this customer 
   * @param vehicleVins  An array of VIN numbers for {@code Vehicle}s associated with this customer
   */
  public Customer(final String id, final String fname, final String lname,
      final String email, final String address,
      final PhoneNumber[] phones, final String[] history,
      final String[] vehicleVins) {
    this.id = id;
    this.fname = fname;
    this.lname = lname;
    this.email = email;
    this.address = address;
    this.phones = phones;
    this.history = history;
    this.vehicleVins = vehicleVins;
  }

  public String[] getHistID() {
    return this.history;
  }

  public void setHistID(final String[] history) {
    this.history = history;
  }

  public String[] getVehicleVins() {
    return this.vehicleVins;
  }

  public void setVehicleVins(final String[] vehicleVins) {
    this.vehicleVins = vehicleVins;
  }

  public String getId() {
    return this.id;
  }

  void setId(final String id) {
    this.id = id;
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

  public Customer id(final String id) {
    this.id = id;
    return this;
  }

  /**
   * Get the customer object from db by id.
   * @param customerId  id of the customer
   * @return
   */
  public static Customer getFromDb(String customerId) {
    // DatabaseUtils.addObjToCollection("userdat", "customers", obj);
    Object[] users = DatabaseUtils.getFromCollection("customers", "id", customerId,
        Customer.class);
    return (Customer) users[0];
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
    return id == customer.id
        && Objects.equals(fname, customer.fname)
        && Objects.equals(lname, customer.lname)
        && Objects.equals(email, customer.email)
        && Objects.equals(address, customer.address)
        && Objects.equals(phones, customer.phones);
  }

  @Override
  public final int hashCode() {
    return Objects.hash(id, fname, lname, email, address, phones);
  }

  @Override
  public final String toString() {
    return "{" + " id='" + getId()
        + "'" + ", fname='" + getFname()
        + "'" + ", lname='" + getLname()
        + "'" + ", email='" + getEmail()
        + "'" + ", address='" + getAddress()
        + "'" + ", phone='" + getPhone()
        + "'" + "}";
  }
}
