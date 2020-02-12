package com.scottsdaleair;

import com.scottsdaleair.data.*;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import org.bson.Document;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;
// import com.google.gson;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient();
		Gson gson = new Gson();

		MongoDatabase database = mongoClient.getDatabase("userdat");
		MongoCollection<Document> customersCol = database.getCollection("customers");
		MongoCollection<Document> vehicleCol = database.getCollection("vehicles");
		// MongoCollection<Document> partsCol = database.getCollection("parts");
		MongoCollection<Document> invoiceCol = database.getCollection("invoices");
		// MongoCollection<Document> partCol = database.getCollection("parts");
		// System.out.println("Credentials ::" + credential);

		int customerCount = new Random().nextInt(10);
		for (int i = 0; i < customerCount; i++) {

			int customerID = new Random().nextInt(10000);

			int vehicleCount = new Random().nextInt(3)+1;

			Vehicle[] vehicles = new Vehicle[vehicleCount];
			for (int x = 0; x < vehicleCount; x++) {
				vehicles[x] = createTestVehicle();
				addObjToCollection(vehicleCol, vehicles[x]);
			}

			int invoiceCount = new Random().nextInt(10);
			Invoice[] invoices = new Invoice[invoiceCount];
			for (int x = 0; x < invoiceCount; x++) {
				invoices[x] = createTestInvoice(customersCol + "",
						vehicles[new Random().nextInt(vehicleCount)].getVin());
				addObjToCollection(invoiceCol, invoices[x]);
			}

			String[] history = new String[invoiceCount];
			for (int x = 0; x < invoiceCount; x++) {
				history[x] = invoices[x].getInvoiceNum();
			}
			String[] vehicleVins = new String[vehicleCount];
			for (int x = 0; x < vehicleCount; x++) {
				vehicleVins[x] = vehicles[x].getVin();
			}
			Customer cust1 = createTestCustomer(customerID, history, vehicleVins);

			addObjToCollection(customersCol, cust1);
		}

		// Document retDoc = customersCol.find().first();
		// printJSON(retDoc.toJson(), "cust1Ret");
		// System.out.println("\n\n");
		// System.out.println(retDoc.toJson());
	}

	public static Document queryCollectionForId(MongoCollection<Document> collection, String id, String value) {
		// DBCursor cursor = collection.find(new BasicDBObject(id, value));
		return collection.find(new BasicDBObject(id, value)).first();
	}

	public static void addObjToCollection(MongoCollection<Document> collection, Object obj) {
		Gson gson = new Gson();
		String obJSON = gson.toJson(obj);
		Document newDoc = Document.parse(obJSON);
		collection.insertOne(newDoc);
	}

	public static void savePart(Part p) {
		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase("userdat");
		MongoCollection<Document> partCol = database.getCollection("parts");
		addObjToCollection(partCol, p);
		mongoClient.close();

	}

	public static void printJSON(String json, String name) {
		try {
			PrintWriter out = new PrintWriter(name + ".json");
			out.println(json);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Could not create file");
		}

	}

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

	public static Invoice createTestInvoice(String customerID, String vin) {
		String invoiceNum = new Random().nextInt(10000) + "";
		String date = new Random().nextInt(12) + "-" + new Random().nextInt(30) + "-"
				+ (new Random().nextInt(40) + 1980);
		int partCount = new Random().nextInt(10);
		String[] parts = new String[partCount];
		for (int x = 0; x < partCount; x++) {
			parts[x] = createTestPart().getPartNum();
		}
		String pubNotes = "A very public note";
		String privNotes = "A super secret note";
		return new Invoice(invoiceNum, date, customerID, vin, parts, pubNotes, privNotes);
	}

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
