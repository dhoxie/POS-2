package com.scottsdaleair.data;

import java.util.Objects;

public class Kit {
  // private String[] parts;
  private String[] services;
  private String description;
  private String price;

  public Kit() {
  }

  /**
   * A kit represents a commonly-grouped set of services.
   * @param services    An array of ID
   * @param description A description of this kit
   * @param price       Cost of this kit
   */
  public Kit(String[] services, String description, String price) {
    this.services = services;
    this.description = description;
    this.price = price;
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
    return Objects.equals(services, kit.services) && Objects.equals(description, kit.description)
        && Objects.equals(price, kit.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(services, description, price);
  }

  @Override
  public String toString() {
    return "{" + " services='" + getServices()
        + "'" + ", description='" + getDescription() + "'" + ", price='"
        + getPrice() + "'" + "}";
  }

}