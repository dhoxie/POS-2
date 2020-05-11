package com.scottsdaleair.data;

import com.scottsdaleair.utils.DatabaseUtils;
import java.util.Objects;

public class Vehicle {
  private String make;
  private String model;
  private String year;
  private String plate;
  private String mileage;
  private String motor;
  private String vin; // Identifying value
  private String comments;

  public Vehicle() {
  }

  /**
   * Vehicle represents a vehicle.
   * @param make      The manufacturer of this vehicle
   * @param model     The model of vehicle
   * @param year      THe year this vehicle was made
   * @param plate     The license plate associated with this vehicle
   * @param mileage   The mileage of this vehicle
   * @param motor     The type of motor in this vehicle
   * @param vin       The VIN number of this vehicle - Used to identify the vehicle
   * @param comments  A list of comments associatid with this vehicle
   */
  public Vehicle(String make, String model, String year,
      String plate, String mileage, String motor, String vin,
      String comments) {
    this.make = make;
    this.model = model;
    this.year = year;
    this.plate = plate;
    this.mileage = mileage;
    this.motor = motor;
    this.vin = vin;
    this.comments = comments;
  }

  public String getMake() {
    return this.make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return this.model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getYear() {
    return this.year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getPlate() {
    return this.plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public String getMileage() {
    return this.mileage;
  }

  public void setMileage(String mileage) {
    this.mileage = mileage;
  }

  public String getMotor() {
    return this.motor;
  }

  public void setMotor(String motor) {
    this.motor = motor;
  }

  public String getVin() {
    return this.vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  public String getComments() {
    return this.comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public Vehicle make(String make) {
    this.make = make;
    return this;
  }

  public Vehicle model(String model) {
    this.model = model;
    return this;
  }

  public Vehicle year(String year) {
    this.year = year;
    return this;
  }

  public Vehicle plate(String plate) {
    this.plate = plate;
    return this;
  }

  public Vehicle mileage(String mileage) {
    this.mileage = mileage;
    return this;
  }

  public Vehicle motor(String motor) {
    this.motor = motor;
    return this;
  }

  public Vehicle vin(String vin) {
    this.vin = vin;
    return this;
  }

  public Vehicle comments(String comments) {
    this.comments = comments;
    return this;
  }

  /**
   * Get the vehicle object from db by vin.
   * @param vin vin of the vehicle
   * @return
   */
  public static Vehicle getFromDb(String vin) {
    // DatabaseUtils.addObjToCollection("userdat", "customers", obj);
    Object[] invoices = DatabaseUtils.getFromCollection("vehicles", "vin", vin,
        Vehicle.class);
    return (Vehicle) invoices[0];
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Vehicle)) {
      return false;
    }
    Vehicle vehicle = (Vehicle) o;
    return Objects.equals(make, vehicle.make) && Objects.equals(model, vehicle.model)
        && Objects.equals(year, vehicle.year) && Objects.equals(plate, vehicle.plate)
        && Objects.equals(mileage, vehicle.mileage) && Objects.equals(motor, vehicle.motor)
        && Objects.equals(vin, vehicle.vin) && Objects.equals(comments, vehicle.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(make, model, year, plate, mileage, motor, vin, comments);
  }

  @Override
  public String toString() {
    return "{" + " make='" + getMake() + "'" + ", model='" + getModel()
        + "'" + ", year='" + getYear() + "'" + ", plate='" + getPlate()
        + "'" + ", mileage='" + getMileage() + "'" + ", motor='" + getMotor()
        + "'" + ", vin='" + getVin() + "'" + ", comments='" + getComments() + "'" + "}";
  }

}