package com.scottsdaleair;

import com.scottsdaleair.data.*;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;
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

		MongoDatabase database = mongoClient.getDatabase("testDB");
		MongoCollection<Document> customersCol = database.getCollection("customers");
		MongoCollection<Document> vehicleCol = database.getCollection("vehicles");
		MongoCollection<Document> partsCol = database.getCollection("parts");
		MongoCollection<Document> invoiceCol = database.getCollection("invoices");
		// System.out.println("Credentials ::" + credential);
		File f = new File("./user.json");
		Path p = f.toPath();
		String user;
		try {
			user = Files.readString(p, StandardCharsets.US_ASCII);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			user = "";
			e.printStackTrace();
		}

		Customer cust1 = createTestCustomer();
		String json1 = gson.toJson(cust1);
		Document userDoc = Document.parse(json1);
		printJSON(json1, "cust1");

		customersCol.insertOne(userDoc);

		Document retDoc = customersCol.find().first();
		printJSON(retDoc.toJson(), "cust1Ret");
		System.out.println("\n\n");
		System.out.println(retDoc.toJson());
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


	public static int[] bob() {
		return null;
	}
	public static Customer createTestCustomer() {
		int id = new Random().nextInt(10000000);
		String fname = "John";
		String lname = "Doe";
		String email = "john@doe.com";
		String address = "1234 Sesame St.";
		PhoneNumber[] phones = { new PhoneNumber("home", "509-123-4567") };
		Vehicle testVehicle = createTestVehicle();
		Vehicle[] vehicles = { testVehicle };
		Invoice[] history = {};
		Customer custRet = new Customer(id, fname, lname, email, address, phones, history, vehicles);
		custRet.addHistory(createTestInvoice(id + "", testVehicle));
		return custRet;
	}

	public static Invoice createTestInvoice(String customerID, Vehicle vehicle) {
		String invoiceNum = "1312412";
		String date = "03-02-2020";
		// Customer customer;
		// Vehicle vehicle ;
		Part testPart = createTestPart();
		Part[] parts = { testPart };

		String pubNotes = "A very public note";
		String privNotes = "A super secret note";
		return new Invoice(invoiceNum, date, customerID, vehicle, parts, pubNotes, privNotes);
	}

	public static Part createTestPart() {
		String partNum = "134423";
		String vendor = "Nappa";
		int onHand = 15;
		String price = "3.50";
		return new Part(partNum, vendor, onHand, price);
	}

	public static Vehicle createTestVehicle() {
		String make = "Cheverolet";
		String model = "Suburban";
		String year = "2000";
		String plate = "NULL";
		String mileage = "333333333";
		String motor = "hemi";
		String vin = "3GCEK23359G131595";
		String comments = "This vehicle SUCKS!!!";

		return new Vehicle(make, model, year, plate, mileage, motor, vin, comments);
	}
}
