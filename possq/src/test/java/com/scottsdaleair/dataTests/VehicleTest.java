package com.scottsdaleair.dataTests;

import com.scottsdaleair.data.Vehicle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VehicleTest {

  Vehicle vehicle;
  String changed;

  @Before
  public void setUp() throws Exception {
    vehicle = new Vehicle("make", "model", "year", "plate", "mileage", "motor", "vin", "comments");
    changed = "changed";
  }

  @After
  public void tearDown() throws Exception {
    vehicle = null;
    assertNull(vehicle);
  }

  @Test
  public void testSetMake() {
    vehicle.setMake(changed);
    assertEquals(changed, vehicle.getMake());
  }

  @Test
  public void testSetModel() {
    vehicle.setModel(changed);
    assertEquals(changed, vehicle.getModel());
  }

  @Test
  public void testSetYear() {
    vehicle.setYear(changed);
    assertEquals(changed, vehicle.getYear());
  }

  @Test
  public void testSetPlate() {
    vehicle.setPlate(changed);
    assertEquals(changed, vehicle.getPlate());
  }

  @Test
  public void testSetMileage() {
    vehicle.setMileage(changed);
    assertEquals(changed, vehicle.getMileage());
  }

  @Test
  public void testSetMotor() {
    vehicle.setMotor(changed);
    assertEquals(changed, vehicle.getMotor());
  }

  @Test
  public void testSetVin() {
    vehicle.setVin(changed);
    assertEquals(changed, vehicle.getVin());
  }

  @Test
  public void testSetComments() {
    vehicle.setComments(changed);
    assertEquals(changed, vehicle.getComments());
  }

  @Test
  public void testToString() {
    String expected = "{" + " make='" + vehicle.getMake() + "'" + ", model='" + vehicle.getModel()
        + "'" + ", year='" + vehicle.getYear() + "'" + ", plate='" + vehicle.getPlate()
        + "'" + ", mileage='" + vehicle.getMileage() + "'" + ", motor='" + vehicle.getMotor()
        + "'" + ", vin='" + vehicle.getVin() + "'" + ", comments='" + vehicle.getComments() + "'" + "}";
    assertEquals(expected, vehicle.toString());
  }
}