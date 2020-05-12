package com.scottsdaleair.dataTests;

import com.scottsdaleair.data.Kit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KitTest {

  private Kit kit;
  String changed;
  String [] arr = {"1", "2", "3"};
  String [] changedArr = {"changed"};

  @Before
  public void setUp() throws Exception {
    kit = new Kit("id", "name", arr, arr, "description", "$0.00");
    changed = "changed";
  }

  @After
  public void tearDown() throws Exception {
    kit = null;
    assertNull(kit);
  }

  @Test
  public void testConstructor(){ assertNotNull(kit);}

  @Test
  public void testSetId() {
    kit.setId(changed);
    assertEquals(kit.getId(), changed);
  }

  @Test
  public void testSetName() {
    kit.setName(changed);
    assertEquals(kit.getName(), changed);
  }

  @Test
  public void testSetParts() {
    kit.setParts(changedArr);
    assertArrayEquals(kit.getParts(), changedArr);
  }

  @Test
  public void testSetServices() {
    kit.setServices(changedArr);
    assertArrayEquals(kit.getServices(), changedArr);
  }

  @Test
  public void testSetDescription() {
    kit.setDescription(changed);
    assertEquals(kit.getDescription(), changed);
  }

  @Test
  public void testSetPrice() {
    kit.setPrice(changed);
    assertEquals(kit.getPrice(), changed);
  }

  @Test
  public void testEquals() {
    Kit diff = new Kit();
    assertNotEquals(kit, diff);
  }

  @Test
  public void testToString() {
    String expected = "{" + " id='" + kit.getId() + "'" + ", name='"
        + kit.getName() + "'" + ", parts='" + kit.getParts() + "'"
        + ", services='" + kit.getServices() + "'" + ", description='"
        + kit.getDescription() + "'" + ", price='" + kit.getPrice()
        + "'" + "}";
    assertEquals(expected, kit.toString());
  }
}