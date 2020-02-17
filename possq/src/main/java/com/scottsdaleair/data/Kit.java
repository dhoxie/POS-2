package com.scottsdaleair.data;

import java.util.Objects;

public class Kit {
  // private String[] parts;
  private String id;
  private String[] services;
  private String description;
  private String price;


  public Kit() {
  }

  /**
   * A kit represents a set of services provided.
   * @param id          Unique ID of this kit
   * @param services    Array of service ID's associated
   * @param description Description of the kit
   * @param price       Price of this kit
   */
  public Kit(String id, String[] services, String description, String price) {
    this.id = id;
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

  /**
   * Get the object in the DB by id.
   * @param kitId  Id of the kit
   * @return
   */
  public static Kit getFromDb(String kitId) {
    // DatabaseUtils.addObjToCollection("userdat", "customers", obj);
    Object[] users = DatabaseUtils.getFromCollection("kits", "id", kitId,
        Kit.class);
    return (Kit)users[0];
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
    return Objects.equals(id, kit.id)
        && Objects.equals(services, kit.services)
        && Objects.equals(description, kit.description)
        && Objects.equals(price, kit.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, services, description, price);
  }

  @Override
  public String toString() {
    return "{"
      + " id='" + getId()
      + "'" + ", services='" + getServices()
      + "'" + ", description='" + getDescription()
      + "'" + ", price='" + getPrice() + "'" + "}";
  }


}