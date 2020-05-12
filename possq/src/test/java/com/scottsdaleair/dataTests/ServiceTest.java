package com.scottsdaleair.dataTests;

import com.scottsdaleair.data.Service;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {

  Service service;
  String changed;
  String[] arr = {"parts"};
  String[] changedArr = {"changed"};

  @Before
  public void setUp() throws Exception {
    service = new Service("id", "name", arr, "description", "price");
    changed = "changed";
  }

  @After
  public void tearDown() throws Exception {
    service = null;
    assertNull(service);
  }

  @Test
  public void testSetId() {
    service.setId(changed);
    assertEquals(changed, service.getId());
  }

  @Test
  public void testSetName() {
    service.setName(changed);
    assertEquals(changed, service.getName());
  }

  @Test
  public void testSetParts() {
    service.setParts(changedArr);
    assertArrayEquals(changedArr, service.getParts());
  }

  @Test
  public void testSetDescription() {
    service.setDescription(changed);
    assertEquals(changed, service.getDescription());
  }

  @Test
  public void testSetPrice() {
    service.setPrice(changed);
    assertEquals(changed, service.getPrice());
  }

  @Test
  public void testToString() {
    String expected = "{" + " id='" + service.getId() + "'"
        + ", name='" + service.getName() + "'" + ", parts='" + service.getParts() + "'"
        + ", description='" + service.getDescription() + "'" + ", price='" + service.getPrice() + "'" + "}";
  }
}