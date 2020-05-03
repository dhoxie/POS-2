package com.scottsdaleair.data.generators;

import com.scottsdaleair.data.Kit;
import com.scottsdaleair.utils.DatabaseUtils;
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
    Random rnd = new Random();
    final String id = rnd.nextInt(1000000000) + "";
    final String name = kitNames[rnd.nextInt(kitNames.length)];
    String[] parts = PartGeneratorUtils.createTestParts(rnd.nextInt(5));
    String[] services = ServiceGenerator.createTestServices(rnd.nextInt(2));
    String description = "Well Described Kit";
    String price = GeneratorUtils.getPrice(rnd.nextInt(75), rnd.nextInt(100));
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
    DatabaseUtils.addObjToCollection("services", k);

  }
  
}