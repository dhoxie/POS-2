package com.scottsdaleair.dataTests;

import com.scottsdaleair.data.Customer;
import com.scottsdaleair.data.PhoneNumber;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

  Customer customer;
  PhoneNumber [] phoneNumbers = {new PhoneNumber("name", "123")};
  PhoneNumber [] changedNumbers = {new PhoneNumber("changed", "111")};
  String [] arr = {"initial"};
  String [] changedArr = {"changed"};
  String changed;

  @Before
  public void setUp() throws Exception {
    customer = new Customer("id", "fname", "lname",
        "email", "address", phoneNumbers, arr, arr);
    changed = "changed";
  }

  @After
  public void tearDown() throws Exception {
    customer = null;
    assertNull(customer);
  }

  @Test
  public void testSetHistID() {
    customer.setHistID(changedArr);
    assertArrayEquals(changedArr, customer.getHistID());
  }

  @Test
  public void testSetVehicleVins() {
    customer.setVehicleVins(changedArr);
    assertArrayEquals(changedArr, customer.getVehicleVins());
  }

//  @Test
//  public void testSetPhones() {
//    customer.setPhone(changedNumbers);
//    assertArrayEquals(changedNumbers, customer.getPhones());
//  }

  @Test
  public void testSetFname(){
    customer.setFname(changed);
    assertEquals(changed, customer.getFname());
  }

  @Test
  public void testSetLname(){
    customer.setLname(changed);
    assertEquals(changed, customer.getLname());
  }

  @Test
  public void testSetEmail(){
    customer.setEmail(changed);
    assertEquals(changed, customer.getEmail());
  }

  @Test
  public void testSetAddress(){
    customer.setAddress(changed);
    assertEquals(changed, customer.getAddress());
  }

  @Test
  public void testSetPhone(){
    customer.setPhone(changedNumbers);
    assertArrayEquals(changedNumbers, customer.getPhone());
  }

  @Test
  public void testGetFromDb(){
    // TODO - get customer id to use from db
  }

  @Test
  public void testToString(){
    String expected = "{" + " id='" + customer.getId()
        + "'" + ", fname='" + customer.getFname()
        + "'" + ", lname='" + customer.getLname()
        + "'" + ", email='" + customer.getEmail()
        + "'" + ", address='" + customer.getAddress()
        + "'" + ", phone='" + customer.getPhone()
        + "'" + "}";
    assertEquals(expected, customer.toString());
  }
}