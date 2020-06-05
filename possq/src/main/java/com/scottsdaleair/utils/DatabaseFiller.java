package com.scottsdaleair.utils;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.scottsdaleair.controller.DBController;
import com.scottsdaleair.data.Customer;
import com.scottsdaleair.data.Invoice;
import com.scottsdaleair.data.Vehicle;
import com.scottsdaleair.data.generators.CustomerGeneratorUtils;
import com.scottsdaleair.data.generators.GeneratorUtils;
import com.scottsdaleair.data.generators.InvoiceGeneratorUtils;
import com.scottsdaleair.data.generators.VehicleGeneratorUtils;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.bson.Document;

public class DatabaseFiller {

  /**
   * Fills the database with data fitting a given number of customers.
   * @param customerCount  Number of customers to add
   */
  public static void fillDb(int customerCount) {

    for (int i = 0; i < customerCount; i++) {

      String customerID = GeneratorUtils.rand().nextInt(1000000000) + "";

      int vehicleCount = GeneratorUtils.rand().nextInt(3) + 1;

      Vehicle[] vehicles = new Vehicle[vehicleCount];
      for (int x = 0; x < vehicleCount; x++) {
        vehicles[x] = VehicleGeneratorUtils.createTestVehicle();
        DBController.addToDB(vehicles[x], Vehicle.class);
      }

      int invoiceCount = GeneratorUtils.rand().nextInt(10);
      Invoice[] invoices = new Invoice[invoiceCount];
      for (int x = 0; x < invoiceCount; x++) {
        invoices[x] = InvoiceGeneratorUtils.createTestInvoice(customerID + "",
            vehicles[GeneratorUtils.rand().nextInt(vehicleCount)].getVin());
        DBController.addToDB(invoices[x], Invoice.class);
      }

      String[] history = new String[invoiceCount];
      for (int x = 0; x < invoiceCount; x++) {
        history[x] = invoices[x].getID();
      }

      String[] vehicleVins = new String[vehicleCount];
      for (int x = 0; x < vehicleCount; x++) {
        vehicleVins[x] = vehicles[x].getVin();
      }

      Customer cust1 = CustomerGeneratorUtils.createTestCustomer(customerID, history, vehicleVins);
      DBController.addToDB(cust1, Customer.class);
    }
  }

  public static Document queryCollectionForId(MongoCollection<Document> collection,
      String id, String value) {
    return collection.find(new BasicDBObject(id, value)).first();
  }

  /**
   * Prints the JSON string to a file.
   * @param json  The JSON string to save
   * @param name  The name of the file to save into
   */
  public static void printJson(String json, String name) {
    try {
      PrintWriter out = new PrintWriter(name + ".json");
      out.println(json);
      out.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("Could not create file");
    }

  }

  public static void main(String[] args) {
    int fillCount = 1000;
    if (args.length == 1) {
      fillCount = Integer.parseInt(args[0]);
    }
    fillDb(fillCount);
  }
}
