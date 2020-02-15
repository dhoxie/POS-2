package com.scottsdaleair.data.generators;

import com.scottsdaleair.data.Invoice;
import com.scottsdaleair.data.generators.PartGeneratorUtils;

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
    String invoiceNum = new Random().nextInt(10000) + "";
    String date = generateDate();
    int partCount = new Random().nextInt(10);
    String[] parts = new String[partCount];
    for (int x = 0; x < partCount; x++) {
      parts[x] = PartGeneratorUtils.createTestPart().getPartNum();
    }
    String pubNotes = "A very public note";
    String privNotes = "A super secret note";
    return new Invoice(invoiceNum, date, customerID, vin, parts, null, null, pubNotes, privNotes);
  }

  private static String generateDate() {
    return new Random().nextInt(12) + "-" + new Random().nextInt(30)
        + "-" + (new Random().nextInt(40) + 1980);
  }
}