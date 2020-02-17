package com.scottsdaleair.data.generators;

import com.scottsdaleair.data.DatabaseUtils;
import com.scottsdaleair.data.Part;

import java.util.Random;

public class PartGeneratorUtils {

  private static final String[] partVendors = {
    "Visteon",
    "Tenneco",
    "Lear",
    "BorgWarner",
    "Goodyear",
    "Honeywell",
    "Champion",
    "Autolite",
    "Dura",
    "Tenneco",
    "Hurst",
    "GM"
  };

  /**
   * Creates a randomly generated part.
   * @return  A {@code Part}
   */
  public static Part createTestPart() {
    String partNum = new Random().nextInt(10000) + "";
    String vendor = generateVendorID();
    int onHand = new Random().nextInt(50);
    String price = "$" + new Random().nextInt(100) + "." + new Random().nextInt(99);
    Part partRet = new Part(partNum, vendor, onHand, price);
    savePart(partRet);
    return partRet;
  }
  
  private static String generateVendorID() {
    int vendorID = new Random().nextInt(partVendors.length);
    return partVendors[vendorID];
  }

  /**
   * Saves a part to the Mongo collection 'parts'.
   * @param p The part to add
   */
  public static void savePart(Part p) {
    DatabaseUtils.addObjToCollection("parts", p);

  }
}