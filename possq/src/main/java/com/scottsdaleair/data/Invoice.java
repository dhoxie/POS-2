package com.scottsdaleair.data;

import com.scottsdaleair.utils.DatabaseUtils;
import java.util.Objects;

public class Invoice {
  private String id;
  private String date;
  private String customerID;
  private String vehicleVin;
  private String[] parts;
  private String[] services;
  private String[] kits;
  private String pubNotes;
  private String privNotes;


  public Invoice() {
  }

  /**
   * This class represents an Inovice.
   * 
   * @param id          The invoice ID
   * @param date        The date the invoice was created
   * @param customerID  The Customer ID for the transacted customer
   * @param vehicleVin  The VIN of the vehicle worked on
   * @param parts       The array of part ID's used
   * @param pubNotes    Notes included in customer invoice
   * @param privNotes   Notes seen only by staff
   */
  public Invoice(String id, String date, String customerID, String vehicleVin,
          String[] parts, String[] services, String[] kits, String pubNotes, String privNotes) {
    this.id = id;
    this.date = date;
    this.customerID = customerID;
    this.vehicleVin = vehicleVin;
    this.parts = parts;
    this.services = services;
    this.kits = kits;
    this.pubNotes = pubNotes;
    this.privNotes = privNotes;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getCustomerID() {
    return this.customerID;
  }

  public void setCustomerID(String customerID) {
    this.customerID = customerID;
  }

  public String getVehicleVin() {
    return this.vehicleVin;
  }

  public void setVehicleVin(String vehicleVin) {
    this.vehicleVin = vehicleVin;
  }

  public String[] getParts() {
    return this.parts;
  }

  public void setParts(String[] parts) {
    this.parts = parts;
  }

  public String[] getServices() {
    return this.services;
  }

  public void setServices(String[] services) {
    this.services = services;
  }

  public String[] getKits() {
    return this.kits;
  }

  public void setKits(String[] kits) {
    this.kits = kits;
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

  public Invoice id(String id) {
    this.id = id;
    return this;
  }

  public Invoice date(String date) {
    this.date = date;
    return this;
  }

  public Invoice customerID(String customerID) {
    this.customerID = customerID;
    return this;
  }

  public Invoice vehicleVin(String vehicleVin) {
    this.vehicleVin = vehicleVin;
    return this;
  }

  public Invoice parts(String[] parts) {
    this.parts = parts;
    return this;
  }

  public Invoice services(String[] services) {
    this.services = services;
    return this;
  }

  public Invoice kits(String[] kits) {
    this.kits = kits;
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

  /**
   * Get the invoice object from db by id.
   * @param invoiceId id of the invoice
   * @return
   */
  public static Invoice getFromDb(String invoiceId) {
    // DatabaseUtils.addObjToCollection("userdat", "customers", obj);
    Object[] invoices = DatabaseUtils.getFromCollection("invoices", "id", invoiceId,
        Invoice.class);
    return (Invoice) invoices[0];
  }

  @Override
    public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Invoice)) {
      return false;
    }
    Invoice invoice = (Invoice) o;
    return Objects.equals(id, invoice.id)
        && Objects.equals(date, invoice.date)
        && Objects.equals(customerID, invoice.customerID)
        && Objects.equals(vehicleVin, invoice.vehicleVin)
        && Objects.equals(parts, invoice.parts)
        && Objects.equals(services, invoice.services)
        && Objects.equals(kits, invoice.kits)
        && Objects.equals(pubNotes, invoice.pubNotes)
        && Objects.equals(privNotes, invoice.privNotes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, date, customerID, vehicleVin,
          parts, services, kits, pubNotes, privNotes);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId()
      + "'" + ", date='" + getDate()
      + "'" + ", customerID='" + getCustomerID()
      + "'" + ", vehicleVin='" + getVehicleVin()
      + "'" + ", parts='" + getParts()
      + "'" + ", services='" + getServices()
      + "'" + ", kits='" + getKits()
      + "'" + ", pubNotes='" + getPubNotes()
      + "'" + ", privNotes='" + getPrivNotes()
      + "'" + "}";
  }

}