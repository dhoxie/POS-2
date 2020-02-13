package com.scottsdaleair.data;

import java.util.Objects;

public class Part {
  private String partID; // Part number
  private String vendor;
  private int onHand;
  private String price;

  public Part() {
  }

  /**
   * Represents a vehicle part.
   * @param partNum The ID of the part
   * @param vendor  The manufacturer of the part
   * @param onHand  The current inventory count of this part
   * @param price   The price of this part
   */
  public Part(String partNum, String vendor, int onHand, String price) {
    this.partID = partNum;
    this.vendor = vendor;
    this.onHand = onHand;
    this.price = price;
  }

  public String getPartNum() {
    return this.partID;
  }

  public void setPartNum(String partNum) {
    this.partID = partNum;
  }

  public String getVendor() {
    return this.vendor;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }

  public int getOnHand() {
    return this.onHand;
  }

  public void setOnHand(int onHand) {
    this.onHand = onHand;
  }

  public String getPrice() {
    return this.price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public Part partNum(String partNum) {
    this.partID = partNum;
    return this;
  }

  public Part vendor(String vendor) {
    this.vendor = vendor;
    return this;
  }

  public Part onHand(int onHand) {
    this.onHand = onHand;
    return this;
  }

  public Part price(String price) {
    this.price = price;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Part)) {
      return false;
    }
    Part part = (Part) o;
    return Objects.equals(partID, part.partID)
        && Objects.equals(vendor, part.vendor)
        && onHand == part.onHand
        && Objects.equals(price, part.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partID, vendor, onHand, price);
  }

  @Override
  public String toString() {
    return "{" + " partNum='" + getPartNum()
        + "'" + ", vendor='" + getVendor()
        + "'" + ", onHand='" + getOnHand() + "'"
        + ", price='" + getPrice() + "'" + "}";
  }

}