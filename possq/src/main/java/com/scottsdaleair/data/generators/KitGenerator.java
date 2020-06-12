package com.scottsdaleair.data.generators;

import com.scottsdaleair.controller.DBController;
import com.scottsdaleair.data.Kit;
import java.util.Random;


public class KitGenerator {

  private static final String[] kitNames = {
    "Kit-chen",
    "Nobody Knows"
  };

  /**
   * Creates a randomly generated Kit.
   * @return A {@code Kit}
   */
  public static Kit createTestKit() {
    Random rnd = GeneratorUtils.rand();
    final String id = rnd.nextInt(1000000000) + "";
    final String name =  GeneratorUtils.getRandValue(kitNames);
    String[] parts = PartGeneratorUtils.createTestParts(rnd.nextInt(5));
    String[] services = ServiceGenerator.createTestServices(rnd.nextInt(2));
    String description = "Well Described Kit";
    String price = GeneratorUtils.getRealisticPrice(500);
    Kit retKit = new Kit(id, name, parts, services, description, price);
    saveService(retKit);
    return retKit;
  }

  /**
   * Creates a specific number of kits.
   * @param kitCount The number of kits to create
   * @return A String array of kit ids created
   */
  public static String[] createTestKits(int kitCount) {
    String[] kits = new String[kitCount];
    for (int x = 0; x < kitCount; x++) {
      kits[x] = createTestKit().getId();
    }
    return kits;
  }

  /**
   * Saves a kit to the Mongo collection 'kits'.
   * @param k The kit to add
   */
  public static void saveService(Kit k) {
    DBController.addToDB(k, Kit.class);

  }
  
}