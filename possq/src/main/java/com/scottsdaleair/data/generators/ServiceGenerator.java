package com.scottsdaleair.data.generators;

import com.scottsdaleair.data.Service;
import com.scottsdaleair.utils.DatabaseUtils;
import java.util.Random;

public class ServiceGenerator {

  private static final String[] serviceNames = {
    "Oil Change",
    "Tire Change",
    "Tune-Up",
    "Potato in the Tailpipe",
    "Fluids",
    "Flaps Repair",
    "Landing Gear Replacement",
    "Data Class Refactor"
  };

  /**
   * Creates a randomly generated kit.
   * @return A {@code Service}
   */
  public static Service createTestService() {
    Random rnd = GeneratorUtils.rand();
    final String id = rnd.nextInt(1000000000) + "";
    final String name = GeneratorUtils.getRandValue(serviceNames);
    String[] parts = PartGeneratorUtils.createTestParts(rnd.nextInt(5));
    String description = "Well Described Service";
    String price = GeneratorUtils.getRealisticPrice(350);
    final Service servRet = new Service(id, name, parts, description, price);
    saveService(servRet);
    return servRet;
  }

  /**
   * Creates a specific number of services.
   * @param serviceCount The number of services to create
   * @return A String array of service ids created
   */
  public static String[] createTestServices(int serviceCount) {
    String[] services = new String[serviceCount];
    for (int x = 0; x < serviceCount; x++) {
      services[x] = createTestService().getId();
    }
    return services;
  }

  /**
   * Saves a service to the Mongo collection 'services'.
   * @param s The service to add
   */
  public static void saveService(Service s) {
    DatabaseUtils.addObjToCollection("services", s);

  }
  
}