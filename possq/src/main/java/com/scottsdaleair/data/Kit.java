package com.scottsdaleair.data;

import com.scottsdaleair.utils.DatabaseUtils;
import java.util.Objects;

public class Kit {
  private String id;
  private String name;
  private String[] parts;
  private String[] services;
  private String description;
  private String price;

  public Kit() {
  }

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
    this.id = id;
    this.name = name;
    this.parts = parts;
    this.services = services;
    this.description = description;
    this.price = price;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
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

  public Kit id(String id) {
    this.id = id;
    return this;
  }

  public Kit name(String name) {
    this.name = name;
    return this;
  }

  public Kit parts(String[] parts) {
    this.parts = parts;
    return this;
  }

  public Kit services(String[] services) {
    this.services = services;
    return this;
  }

  public Kit description(String description) {
    this.description = description;
    return this;
  }

  public Kit price(String price) {
    this.price = price;
    return this;
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
    return Objects.equals(id, kit.id) && Objects.equals(name, kit.name)
        && Objects.equals(parts, kit.parts)
        && Objects.equals(services, kit.services) && Objects.equals(description, kit.description)
        && Objects.equals(price, kit.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, parts, services, description, price);
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
    // DatabaseUtils.addObjToCollection("userdat", "customers", obj);
    Object[] users = DatabaseUtils.getFromCollection("kits", "id", kitId, Kit.class);
    return (Kit) users[0];
  }

}