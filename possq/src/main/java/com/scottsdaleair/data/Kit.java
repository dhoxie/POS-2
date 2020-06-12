package com.scottsdaleair.data;

import com.scottsdaleair.controller.DBController;
import com.scottsdaleair.utils.DataUtils;
import java.util.Arrays;
import java.util.Objects;

public class Kit implements DatabaseObject {
  private String id;
  private String name;
  private String[] parts;
  private String[] services;
  private String description;
  private String price;

  /**
   * A kit represents a set of services provided.
   * 
   * @param id          Unique ID of this kit
   * @param name        Name of the kit
   * @param services    Array of service ID's associated
   * @param parts       Array of part ID's associated
   * @param description Description of the kit
   * @param price       Price of this kit
   */
  public Kit(String id, String name, String[] parts, String[] services,
      String description, String price) {
    // super(id);
    this.id = id;
    this.name = name;
    this.parts = parts;
    this.services = services;
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

  public String[] getServices() {
    return this.services;
  }

  public void setServices(String[] services) {
    this.services = services;
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

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Kit)) {
      return false;
    }
    Kit kit = (Kit) o;
    return Objects.equals(getId(), kit.getId()) && Objects.equals(name, kit.name)
        && Objects.equals(parts, kit.parts)
        && Objects.equals(services, kit.services) && Objects.equals(description, kit.description)
        && Objects.equals(price, kit.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), name, parts, services, description, price);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", name='"
        + getName() + "'" + ", parts='" + getParts() + "'"
        + ", services='" + getServices() + "'" + ", description='"
        + getDescription() + "'" + ", price='" + getPrice()
        + "'" + "}";
  }

  /**
   * Get the object in the DB by id.
   * 
   * @param kitId Id of the kit
   * @return
   */
  public static Kit getFromDb(String kitId) {
    return DBController.queryDB("id", kitId, Kit.class)[0];
  }

  @Override
  public String getId() {
    return this.id;
  }

}