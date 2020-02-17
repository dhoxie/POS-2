package com.scottsdaleair.data;

import java.util.Objects;

public class Service {
  private String id;
  private String name;
  private String[] parts;
  private String price;

  public Service() {
  }

  /**
   * A Service.
   * @param id    The ID of this service
   * @param name  The name of this serice
   * @param parts The list of parts used in this service
   * @param price The cost of this service
   */
  public Service(String id, String name, String[] parts, String price) {
    this.id = id;
    this.name = name;
    this.parts = parts;
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

  public String getPrice() {
    return this.price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public Service id(String id) {
    this.id = id;
    return this;
  }

  public Service name(String name) {
    this.name = name;
    return this;
  }

  public Service parts(String[] parts) {
    this.parts = parts;
    return this;
  }

  public Service price(String price) {
    this.price = price;
    return this;
  }

  /**
   * Get the service object from db by id.
   * @param serviceId id of the service
   * @return
   */
  public static Service getFromDb(String serviceId) {
    // DatabaseUtils.addObjToCollection("userdat", "customers", obj);
    Object[] users = DatabaseUtils.getFromCollection("services", "id", serviceId,
        Service.class);
    return (Service)users[0];
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
    return Objects.equals(id, service.id)
        && Objects.equals(name, service.name)
        && Objects.equals(parts, service.parts)
        && Objects.equals(price, service.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, parts, price);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", name='" + getName()
        + "'" + ", parts='" + getParts() + "'" + ", price='"
        + getPrice() + "'" + "}";
  }

}