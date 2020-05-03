package com.scottsdaleair.data.generators;

import com.scottsdaleair.data.Invoice;

import java.util.Random;

public class InvoiceGeneratorUtils {
  /**
   * Creates a randomly generated invoice.
   * 
   * @param customerID The ID of the customer associated with this invoice
   * @param vin        The ID of the car associated with this invoice
   * @return
   */
  public static Invoice createTestInvoice(String customerID, String vin) {
    Random rnd = new Random();
    String invoiceNum = rnd.nextInt(1000000000) + "";
    String date = generateDate();
    String[] parts = PartGeneratorUtils.createTestParts(rnd.nextInt(10));
    String[] services = ServiceGenerator.createTestServices(rnd.nextInt(3));
    String[] kits = KitGenerator.createTestServices(rnd.nextInt(1));
    String pubNotes = "A very public note";
    String privNotes = "A super secret note";
    return new Invoice(invoiceNum, date, customerID, vin, parts, services,
        kits, pubNotes, privNotes);
  }

  private static String generateDate() {
    return new Random().nextInt(12) + "-" + new Random().nextInt(30)
        + "-" + (new Random().nextInt(40) + 1980);
  }
}