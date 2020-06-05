package com.scottsdaleair.data;

import com.scottsdaleair.controller.DBController;
import com.scottsdaleair.utils.DataUtils;
import java.util.Arrays;
import java.util.Objects;

public class Service extends DatabaseObject {
  private String name;
  private String[] parts;
  private String description;
  private String price;

  /**
   * A Service.
   * 
   * @param id          The ID of this service
   * @param name        The name of this service
   * @param parts       The list of parts used in this service
   * @param description Description of the service
   * @param price       The cost of this service
   */
  public Service(String id, String name, String[] parts, String description, String price) {
    super(id);
    this.name = name;
    this.parts = parts;
    this.description = description;
    this.price = price;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String[] getParts() {
    return this.parts;
  }

  public void setParts(String[] parts) {
    this.parts = parts;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPrice() {
    return this.price;
  }

  public void setPrice(String price) {
    this.price = price;
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

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Service)) {
      return false;
    }
    Service service = (Service) o;
    return Objects.equals(getID(), service.getID())
        && Objects.equals(name, service.name) && Objects.equals(parts, service.parts)
        && Objects.equals(description, service.description) && Objects.equals(price, service.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getID(), name, parts, description, price);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getID() + "'"
        + ", name='" + getName() + "'" + ", parts='" + getParts() + "'"
        + ", description='" + getDescription() + "'" + ", price='" + getPrice() + "'" + "}";
  }

  /**
   * Get the service object from db by id.
   * 
   * @param serviceId id of the service
   * @return
   */
  public static Service getFromDb(String serviceId) {
    return DBController.queryDB("id", serviceId, Service.class)[0];
  }

}