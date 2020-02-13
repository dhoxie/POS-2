package com.scottsdaleair;

import com.google.gson.Gson;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.scottsdaleair.data.Customer;
import com.scottsdaleair.data.Invoice;
import com.scottsdaleair.data.Part;
import com.scottsdaleair.data.PhoneNumber;
import com.scottsdaleair.data.Vehicle;
import com.scottsdaleair.data.generators.LicensePlate;
import com.scottsdaleair.data.generators.VinGeneratorUtils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.Random;

import org.bson.Document;

public class App {

  /**
   * Test mainster.
   * @param args  Currently unused list of args
   */
  public static void main(String[] args) {
    MongoClient mongoClient = new MongoClient();

    MongoDatabase database = mongoClient.getDatabase("userdat");
    MongoCollection<Document> customersCol = database.getCollection("customers");
    MongoCollection<Document> vehicleCol = database.getCollection("vehicles");
    MongoCollection<Document> invoiceCol = database.getCollection("invoices");

    int customerCount = new Random().nextInt(10);
    for (int i = 0; i < customerCount; i++) {

      int customerID = new Random().nextInt(10000);

      int vehicleCount = new Random().nextInt(3) + 1;

      Vehicle[] vehicles = new Vehicle[vehicleCount];
      for (int x = 0; x < vehicleCount; x++) {
        vehicles[x] = createTestVehicle();
        addObjToCollection(vehicleCol, vehicles[x]);
      }

      int invoiceCount = new Random().nextInt(10);
      Invoice[] invoices = new Invoice[invoiceCount];
      for (int x = 0; x < invoiceCount; x++) {
        invoices[x] = createTestInvoice(customerID + "",
            vehicles[new Random().nextInt(vehicleCount)].getVin());
        addObjToCollection(invoiceCol, invoices[x]);
      }

      String[] history = new String[invoiceCount];
      for (int x = 0; x < invoiceCount; x++) {
        history[x] = invoices[x].getId();
      }
      String[] vehicleVins = new String[vehicleCount];
      for (int x = 0; x < vehicleCount; x++) {
        vehicleVins[x] = vehicles[x].getVin();
      }
      Customer cust1 = createTestCustomer(customerID, history, vehicleVins);

      addObjToCollection(customersCol, cust1);
      mongoClient.close();
    }
  }

  public static Document queryCollectionForId(MongoCollection<Document> collection,
      String id, String value) {
    return collection.find(new BasicDBObject(id, value)).first();
  }

  /**
   * Adds an object to the specified Mongo collection.
   * @param collection  Collection to add object to
   * @param obj         The object to be added
   */
  public static void addObjToCollection(MongoCollection<Document> collection, Object obj) {
    Gson gson = new Gson();
    String obJson = gson.toJson(obj);
    Document newDoc = Document.parse(obJson);
    collection.insertOne(newDoc);
  }

  /**
   * Saves a part to the Mongo collection 'parts'.
   * @param p The part to add
   */
  public static void savePart(Part p) {
    MongoClient mongoClient = new MongoClient();
    MongoDatabase database = mongoClient.getDatabase("userdat");
    MongoCollection<Document> partCol = database.getCollection("parts");
    addObjToCollection(partCol, p);
    mongoClient.close();

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

  /**
   * Creates a randomly generated customer.
   * @param customerID  The ID of the customer
   * @param invoices    The list of invoices for this customer
   * @param vehicles    The list of vehicles owned by this customer
   * @return
   */
  public static Customer createTestCustomer(int customerID, String[] invoices, String[] vehicles) {
    int id = customerID;
    String fname = "John";
    String lname = "Doe";
    String email = "john@doe.com";
    String address = "1234 Sesame St.";
    PhoneNumber[] phones = { new PhoneNumber("home", "509-123-4567") };
    Customer custRet = new Customer(id, fname, lname, email, address, phones, invoices, vehicles);
    return custRet;
  }

  /**
   * Creates a randomly generated invoice.
   * @param customerID  The ID of the customer associated with this invoice
   * @param vin         The ID of the car associated with this invoice
   * @return
   */
  public static Invoice createTestInvoice(String customerID, String vin) {
    String invoiceNum = new Random().nextInt(10000) + "";
    String date = new Random().nextInt(12) + "-" + new Random().nextInt(30)
        + "-" + (new Random().nextInt(40) + 1980);
    int partCount = new Random().nextInt(10);
    String[] parts = new String[partCount];
    for (int x = 0; x < partCount; x++) {
      parts[x] = createTestPart().getPartNum();
    }
    String pubNotes = "A very public note";
    String privNotes = "A super secret note";
    return new Invoice(invoiceNum, date, customerID, vin, parts, null, null, pubNotes, privNotes);
  }

  /**
   * Creates a randomly generated part.
   * @return  A {@code Part}
   */
  public static Part createTestPart() {
    String partNum = new Random().nextInt(10000) + "";
    int vendorID = new Random().nextInt(5);
    String vendor = "";
    switch (vendorID) {
      case 1:
        vendor = "Visteon";
        break;
      case 2:
        vendor = "Tenneco";
        break;
      case 3:
        vendor = "Lear";
        break;
      case 4:
        vendor = "BorgWarner";
        break;
      default:
        vendor = "Goodyear";
        break;
    }
    int onHand = new Random().nextInt(50);
    String price = new Random().nextInt(100) + "." + new Random().nextInt(99);
    Part partRet = new Part(partNum, vendor, onHand, price);
    savePart(partRet);
    return partRet;
  }

  /**
   * Creates a randomly generated vehicle.
   * @return  A {@code Vehicle}
   */
  public static Vehicle createTestVehicle() {
    String make = "Cheverolet";
    String model = "Suburban";
    String year = (new Random().nextInt(100) + 1920) + "";
    String plate = LicensePlate.generateLicensePlate();
    String mileage = (new Random().nextInt(400000) + 1000) + "";
    String motor = "hemi";
    String vin = VinGeneratorUtils.getRandomVin();
    String comments = "Vehicular commentary";

    return new Vehicle(make, model, year, plate, mileage, motor, vin, comments);
  }
}
