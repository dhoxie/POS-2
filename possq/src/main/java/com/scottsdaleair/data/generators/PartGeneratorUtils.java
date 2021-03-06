package com.scottsdaleair.data.generators;

import com.scottsdaleair.controller.DBController;
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
    Random rnd = GeneratorUtils.rand();
    String partNum = rnd.nextInt(1000000000) + "";
    String vendor = GeneratorUtils.getRandValue(partVendors);
    int onHand = rnd.nextInt(50);
    String price = GeneratorUtils.getRealisticPrice(200);
    Part partRet = new Part(partNum, vendor, onHand, price);
    savePart(partRet);
    return partRet;
  }

  /**
   * Creates a specific number of parts.
   * @param partCount The number of parts to create
   * @return A String array of part ids created
   */
  public static String[] createTestParts(int partCount) {
    String[] parts = new String[partCount];
    for (int x = 0; x < partCount; x++) {
      parts[x] = createTestPart().getId();
    }
    return parts;
  }

  /**
   * Saves a part to the Mongo collection 'parts'.
   * @param p The part to add
   */
  public static void savePart(Part p) {
    DBController.addToDB(p, Part.class);

  }
}