package com.scottsdaleair.dataTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.scottsdaleair.data.Part;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PartTest {

  private Part part;
  String changed;

  @Before
  public void setUp() {
    part = new Part("partID", "vendor", 1, "5.98");
    changed = "changed";
  }

  @After
  public void tearDown() {
    part = null;
    assertNull(part);
  }

  @Test
  public void testConstructor() {
    assertNotNull(part);
  }

  @Test
  public void testSetPartID() {
    part.setPartID(changed);
    assertEquals(part.getPartID(), changed);
  }

  @Test
  public void testSetVendor() {
    part.setVendor(changed);
    assertEquals(part.getVendor(), changed);
  }

  @Test
  public void testSetOnHand() {
    part.setOnHand(2);
    assertEquals(part.getOnHand(), 2);
  }

  @Test
  public void testSetPrice() {
    part.setPrice(changed);
    assertEquals(part.getPrice(), changed);
  }

   //did not pass. I think DB may not be running, or we may need to rewrite this test to pass -
   //Daylyn(Tester)
   @Test public void testGetFromDb() {
    Part db = part.getFromDb(part.getPartID());
    assertEquals(db, part);
  }

  @Test
  public void testEquals() {
    Part diff = new Part();
    assertNotEquals(diff, part);
  }

  @Test
  public void testToString() {
    String expected = "{" + " partID='" + part.getPartID() + "'" + ", vendor='" + part.getVendor()
        + "'" + ", onHand='" + part.getOnHand() + "'" + ", price='" + part.getPrice() + "'" + "}";
    assertEquals(expected, part.toString());
  }
}
