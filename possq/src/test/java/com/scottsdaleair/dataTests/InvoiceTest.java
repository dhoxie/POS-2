package com.scottsdaleair.dataTests;

import com.scottsdaleair.data.Invoice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InvoiceTest {

  Invoice invoice;
  String changed;
  String [] arr = {"initial"};
  String [] changedArr = {"changed"};

  @Before
  public void setUp() throws Exception {
    changed = "changed";
    invoice = new Invoice("id", "date", "customerID",
        "vehicleVin", arr, arr, arr, "pubNotes", "privNotes");
  }

  @After
  public void tearDown() throws Exception {
    invoice = null;
    assertNull(invoice);
  }

  @Test
  public void testSetId() {
    invoice.setId(changed);
    assertEquals(changed, invoice.getId());
  }

  @Test
  public void testSetDate() {
    invoice.setDate(changed);
    assertEquals(changed, invoice.getDate());
  }

  @Test
  public void testSetCustomerID() {
    invoice.setCustomerID(changed);
    assertEquals(changed, invoice.getCustomerID());
  }

  @Test
  public void testSetVehicleVin() {
    invoice.setVehicleVin(changed);
    assertEquals(changed, invoice.getVehicleVin());
  }

  @Test
  public void testSetParts() {
    invoice.setParts(changedArr);
    assertArrayEquals(changedArr, invoice.getParts());
  }

  @Test
  public void testSetServices() {
    invoice.setServices(changedArr);
    assertArrayEquals(changedArr, invoice.getServices());
  }

  @Test
  public void testSetKits() {
    invoice.setKits(changedArr);
    assertArrayEquals(changedArr, invoice.getKits());
  }

  @Test
  public void testSetPubNotes() {
    invoice.setPubNotes(changed);
    assertEquals(changed, invoice.getPubNotes());
  }

  @Test
  public void testSetPrivNotes() {
    invoice.setPrivNotes(changed);
    assertEquals(changed, invoice.getPrivNotes());
  }

  @Test
  public void testGetFromDb() {
    // TODO - get invoice id from database to use
  }

  @Test
  public void testToString() {
    String expected = "{" + " id='" + invoice.getId()
        + "'" + ", date='" + invoice.getDate()
        + "'" + ", customerID='" + invoice.getCustomerID()
        + "'" + ", vehicleVin='" + invoice.getVehicleVin()
        + "'" + ", parts='" + invoice.getParts()
        + "'" + ", services='" + invoice.getServices()
        + "'" + ", kits='" + invoice.getKits()
        + "'" + ", pubNotes='" + invoice.getPubNotes()
        + "'" + ", privNotes='" + invoice.getPrivNotes()
        + "'" + "}";
    assertEquals(expected, invoice.toString());
  }
}