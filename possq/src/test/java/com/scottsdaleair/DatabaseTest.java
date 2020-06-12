package com.scottsdaleair;

import static org.junit.Assert.assertTrue;

import com.scottsdaleair.controller.DatabaseGetter;
import com.scottsdaleair.data.Customer;
import com.scottsdaleair.data.Invoice;
import com.scottsdaleair.data.Kit;
import com.scottsdaleair.data.Part;
import com.scottsdaleair.data.Service;
import com.scottsdaleair.data.Vehicle;
import com.scottsdaleair.utils.DatabaseUtils;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseTest {
  /**
   * Sets up the database for testing.
   */
  @BeforeClass
  public static void initDB() {
    Customer[] someCustomers = DatabaseGetter.queryDB("id", "1234567", Customer.class);
    if (someCustomers.length < 1) {
      Customer cust = new Customer("1234567", "Kayla", "Testificate", "testemail@test.com",
          "1234 Test St", null, new String[]{"497658563"}, null);
      DatabaseUtils.addObjToCollection("customers", cust);
    }

    Vehicle[] someVehicles = DatabaseGetter.queryDB("vin", "2GTJK39U624XX9K5D", Vehicle.class);
    if (someVehicles.length < 1) {
      Vehicle veh = new Vehicle("TestMake", "TestModel", "-1", "TESTR", "101", "TESTMTR",
          "2GTJK39U624XX9K5D", "TSTCMT");
      DatabaseUtils.addObjToCollection("vehicles", veh);
    }

    Part[] someParts = DatabaseGetter.queryDB("partID", "433396766", Part.class);
    String[] parts = new String[1];
    if (someParts.length < 1) {
      Part prt = new Part("433396766", "Test Makers", 1, "$-1.99");
      DatabaseUtils.addObjToCollection("parts", prt);
      parts[0] = prt.getPartID();
    } else {
      parts[0] = someParts[0].getPartID();
    }

    Service[] someServices = DatabaseGetter.queryDB("id", "836891868", Service.class);
    String[] services = new String[1];
    if (someServices.length < 1) {
      Service srv =
          new Service("836891868", "Test Service", parts, "A service to test on", "$-1.99");
      DatabaseUtils.addObjToCollection("services", srv);
      services[0] = srv.getId();
    } else {
      services[0] = someServices[0].getId();
    }

    Kit[] someKits = DatabaseGetter.queryDB("id", "220839086", Kit.class);
    String[] kits = new String[1];
    if (someKits.length < 1) {
      Kit kit = new Kit("220839086", "Test Kit", parts, services, "A kit to test on", "$-1.99");
      DatabaseUtils.addObjToCollection("kits", kit);
      kits[0] = kit.getId();
    } else {
      kits[0] = someKits[0].getId();
    }

    Invoice[] someInvoices = DatabaseGetter.queryDB("id", "497658563", Invoice.class);
    if (someInvoices.length < 1) {
      Invoice inv = new Invoice("497658563", "somedate", "1234567", "2GTJK39U624XX9K5D", parts,
          services, kits, "PRIVTEST", "PUBTEST");
      DatabaseUtils.addObjToCollection("invoices", inv);
    }

  }

  @Test
  public void testGetAllCustomers() {
    Customer[] allCustomers = DatabaseGetter.getAll(Customer.class);
    assertTrue((long) allCustomers.length == DatabaseUtils.getCollectionSize("customers"));
  }

  @Test
  public void testQueryCustomers() {
    Customer[] someCustomers = DatabaseGetter.queryDB("id", "1234567", Customer.class);

    int isAccurate = -1;
    if(someCustomers != null) {
      if(someCustomers.length != 0) {
        isAccurate++;
        for (Customer c : someCustomers) {
          if (c.getFname().equals("Kayla")) {
            isAccurate++;
          }
        }
      }
    }

    assertTrue(isAccurate == someCustomers.length);
  }

  @Test
  public void testGetCustomerHistory(){
    Customer[] someCustomers = DatabaseGetter.queryDB("id", "1234567", Customer.class);
    boolean isAccurate = false;
    if(someCustomers != null) {
      String[] history = someCustomers[0].getHistID();
      if (history != null) {
        if (history[0].equals("497658563")) {
          isAccurate = true;
        }
      }
    }
    assertTrue(isAccurate);
  }

  @Test
  public void testGetAllInvoices() {
    Invoice[] allInvoices = DatabaseGetter.getAll(Invoice.class);
    assertTrue(
        (long) allInvoices.length == DatabaseUtils.getCollectionSize("invoices"));
  }

  @Test
  public void testQueryInvoices() {
    Invoice[] someInvoices = DatabaseGetter.queryDB("id", "497658563", Invoice.class);
    assertTrue(someInvoices.length == 1 && someInvoices[0].getId().equals("497658563"));
  }

  @Test
  public void testGetAllKits() {
    Kit[] allKits = DatabaseGetter.getAll(Kit.class);
    assertTrue((long) allKits.length == DatabaseUtils.getCollectionSize("kits"));
  }

  @Test
  public void testQueryKits() {
    Kit[] someKits = DatabaseGetter.queryDB("id", "220839086", Kit.class);
    assertTrue(someKits.length == 1 && someKits[0].getId().equals("220839086"));
  }

  @Test
  public void testGetAllParts() {
    Part[] allParts = DatabaseGetter.getAll(Part.class);
    assertTrue(
        (long) allParts.length == DatabaseUtils.getCollectionSize("parts"));
  }

  @Test
  public void testQueryParts() {
    Part[] someParts = DatabaseGetter.queryDB("partID", "433396766", Part.class);
    assertTrue(someParts.length == 1 && someParts[0].getPartID().equals("433396766"));
  }

  @Test
  public void testGetAllServices() {
    Service[] allServices = DatabaseGetter.getAll(Service.class);
    assertTrue(
        (long) allServices.length == DatabaseUtils.getCollectionSize("services"));
  }

  @Test
  public void testQueryServices() {
    Service[] someServices = DatabaseGetter.queryDB("id", "836891868", Service.class);

    assertTrue(someServices.length == 1 && someServices[0].getId().equals("836891868"));
  }

  @Test
  public void testGetAllVehicles() {
    Vehicle[] allVehicles = DatabaseGetter.getAll(Vehicle.class);
    assertTrue(
        (long) allVehicles.length == DatabaseUtils.getCollectionSize("vehicles"));
  }

  @Test
  public void testQueryVehicles() {
    Vehicle[] someVehicles = DatabaseGetter.queryDB("vin", "2GTJK39U624XX9K5D", Vehicle.class);

    assertTrue(someVehicles.length == 1 && someVehicles[0].getVin().equals("2GTJK39U624XX9K5D"));
  }

}
