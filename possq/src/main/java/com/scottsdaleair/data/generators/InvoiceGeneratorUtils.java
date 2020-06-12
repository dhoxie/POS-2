package com.scottsdaleair.data.generators;

import com.scottsdaleair.data.Invoice;

public class InvoiceGeneratorUtils {
  /**
   * Creates a randomly generated invoice.
   * 
   * @param customerID The ID of the customer associated with this invoice
   * @param vin        The ID of the car associated with this invoice
   * @return
   */
  public static Invoice createTestInvoice(String customerID, String vin) {
    String invoiceNum = GeneratorUtils.rand().nextInt(1000000000) + "";
    String date = generateDate();
    String[] parts = PartGeneratorUtils.createTestParts(GeneratorUtils.rand().nextInt(10));
    String[] services = ServiceGenerator.createTestServices(GeneratorUtils.rand().nextInt(3));
    String[] kits = KitGenerator.createTestKits(GeneratorUtils.rand().nextInt(2));
    String pubNotes = "A very public note";
    String privNotes = "A super secret note";
    return new Invoice(invoiceNum, date, customerID, vin, parts, services,
        kits, pubNotes, privNotes);
  }

  private static String generateDate() {
    return GeneratorUtils.rand().nextInt(12) + "-" + GeneratorUtils.rand().nextInt(30)
        + "-" + (GeneratorUtils.rand().nextInt(40) + 1980);
  }
}