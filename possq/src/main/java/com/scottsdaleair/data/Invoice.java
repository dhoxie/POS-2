package com.scottsdaleair.data;

import com.scottsdaleair.controller.DBController;
import com.scottsdaleair.utils.DataUtils;
import java.util.Arrays;
import java.util.Objects;

public class Invoice implements DatabaseObject {
  private String id;
  private String date;
  private String customerID;
  private String vehicleVin;
  private String[] parts;
  private String[] services;
  private String[] kits;
  private String pubNotes;
  private String privNotes;

  /**
   * This class represents an Inovice.
   * 
   * @param id          The invoice ID
   * @param date        The date the invoice was created
   * @param customerID  The Customer ID for the transacted customer
   * @param vehicleVin  The VIN of the vehicle worked on
   * @param parts       The array of part ID's used
   * @param services    The array of service ID's used
   * @param kits        The array of kit ID's used
   * @param pubNotes    Notes included in customer invoice
   * @param privNotes   Notes seen only by staff
   */
  public Invoice(String id, String date, String customerID, String vehicleVin,
          String[] parts, String[] services, String[] kits, String pubNotes, String privNotes) {
    // super(id);
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

  public void addPart(String partID) {
    this.parts = DataUtils.addToArray(partID, this.parts);
  }

  /**
   * Remove a referenced part from the Invoice.
   * 
   * @param partID The ID of the part to be removed
   * @return The index of the removed ID or -1 if not found.
   */
  public int removePart(String partID) {
    int index = Arrays.asList(this.parts).indexOf(partID);
    if (index >= 0) {
      this.parts = DataUtils.removeFromArray(index, this.parts);
    }
    return index;
  }

  public void addService(String serviceID) {
    this.services = DataUtils.addToArray(serviceID, this.services);
  }

  /**
   * Remove a referenced service from the Invoice.
   * 
   * @param serviceID The ID of the service to be removed
   * @return The index of the removed ID or -1 if not found.
   */
  public int removeService(String serviceID) {
    int index = Arrays.asList(this.services).indexOf(serviceID);
    if (index >= 0) {
      this.services = DataUtils.removeFromArray(index, this.services);
    }
    return index;
  }

  public void addKit(String kitID) {
    this.kits = DataUtils.addToArray(kitID, this.kits);
  }

  /**
   * Remove a referenced kit from the Invoice.
   * 
   * @param kitID The ID of the kit to be removed
   * @return The index of the removed ID or -1 if not found.
   */
  public int removeKit(String kitID) {
    int index = Arrays.asList(this.kits).indexOf(kitID);
    if (index >= 0) {
      this.kits = DataUtils.removeFromArray(index, this.kits);
    }
    return index;
  }


  /**
   * Get the invoice object from db by id.
   * @param invoiceId id of the invoice
   * @return
   */
  public static Invoice getFromDb(String invoiceId) {
    return DBController.queryDB("id", invoiceId, Invoice.class)[0];
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
    return Objects.equals(getID(), invoice.getID())
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
    return Objects.hash(getID(), date, customerID, vehicleVin,
          parts, services, kits, pubNotes, privNotes);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getID()
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

  @Override
  public String getID() {
    return this.id;
  }

}