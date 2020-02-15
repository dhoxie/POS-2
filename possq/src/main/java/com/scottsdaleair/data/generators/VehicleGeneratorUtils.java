package com.scottsdaleair.data.generators;

import com.scottsdaleair.data.Vehicle;

import java.util.Random;

public class VehicleGeneratorUtils {

  static final String[] makes = {
    "Cheverolet",
    "Honda",
    "Toyota",
    "Ford",
    "BMW",
    "Volkswagen",
    "GMC",
    "Hyundai",
    "Suzuki",
    "Nissan",
    "Tesla"
  };

  static final String[][] models = {
    {
      // Cheverolet Models
      "Corvette",
      "Camero",
      "Cruze",
      "Tahoe",
      "Impala",
      "Suburban",
      "Spark",
      "Malibu",
      "Traverse"
    },
    {
      // Honda models
      "Civic",
      "Fit",
      "CR-V",
      "Accord",
      "HR-V",
      "Odyssey",
      "Insight",
      "Pilot"
    },
    {
      // Toyota Models
      "Corolla",
      "RAV4",
      "C-HR",
      "Camry",
      "Land Cruiser",
      "Prius",
      "Yaris",
      "Supra",
      "Tacoma",
      "4Runner"
    },
    {
      // Ford Models
      "Mustang",
      "Fiesta",
      "Explorer",
      "Ranger",
      "F-150",
      "F-250",
      "F-350",
      "F-420", // The best Ford pickup
      "Escape",
      "Expedition"
    },
    {
      // BMW Models
      "3 Series",
      "5 Series",
      "i3",
      "i8",
      "M4",
      "M5",
      "X4",
      "X6",
      "X7",
      "Z4"  
    },
    {
      // Volkswagen Models
      "Golf",
      "Passat",
      "Tiguan",
      "Jetta",
      "Arteon",
      "Beetle",
      "Atlas",
      "Golf GTI"
    },
    {
      // GMC Models
      "Yukon",
      "Yukon XL",
      "Sierra 1500",
      "Sierra 2500HD",
      "Sierra 3500HD",
      "Acadia",
      "Terrain",
      "Canyon",
      "Savana"
    },
    {
      // Hyundai Models
      "Tucson",
      "Elantra",
      "Accent",
      "Kona",
      "Santa Fe",
      "Sonata",
      "Sonata Hybrid"
    },
    {
      // Suzuki Models
      "Swift",
      "SX4",
      "Vitara",
      "Sidekick",
      "Kizashi",
      "Equator"
    },
    {
      // Nissan Models
      "GT-R",
      "LEAF",
      "Frontier",
      "Altima",
      "Pathfinder",
      "Murano",
      "Maxima",
      "Titan",
      "NV"
    },
    {
      // Tesla Models
      "Roadster",
      "Roadster 2",
      "Cybertruck",
      "Model S",
      "Model 3",
      "Model X",
      "Model Y"
    }
  };

  static final String[] motors = {
    "Toyota 1.0-litre",
    "Mazda Renisis Rotary",
    "BMW 3.0-litre twin-turbo",
    "BMW 1.5-litre petrol-electric",
    "Toyota 1.5-litre Hybrid Synergy Drive",
    "Volkswagen 1.4-litre TSI twin-charger",
    "BMW M 3.2-litre straight-six",
    "Ford 1.0-litre EcoBoost",
    "Ferrari 3.9-litre twin-turbo V8",
    "BMW 3.0L (B58) DOHC Turbocharged I-6",
    "Chevrolet 6.2L OHV V-8 with DFM",
    "Ford 5.0L DOHC V-8",
    "Ram 3.6L DOHC Pentastar eTorque V-6",
    "Honda 2.0L DOHC Atkinson i-VTEC 4-Cyl./HEV"
  };

  /**
   * Creates a randomly generated vehicle.
   * @return  A {@code Vehicle}
   */
  public static Vehicle createTestVehicle() {
    final String[] makeModel = generateMakeModel();
    final String make = makeModel[0];
    final String model = makeModel[1];
    final String year = (new Random().nextInt(100) + 1920) + "";
    final String plate = LicensePlate.generateLicensePlate();
    final String mileage = (new Random().nextInt(400000) + 1000) + "";
    String motor = "";
    if (make == "Tesla") {
      motor = "Tesla Dual 438 kW electric";
    } else {
      motor = generateMotor();
    }
    final String vin = VinGeneratorUtils.getRandomVin();
    final String comments = "Vehicular commentary";

    return new Vehicle(make, model, year, plate, mileage, motor, vin, comments);
  }

  private static String[] generateMakeModel() {
    final int randMake = new Random().nextInt(makes.length);
    final int randModel = new Random().nextInt(models[randMake].length);
    String[] makeModel = { makes[randMake], models[randMake][randModel] };
    return makeModel;
  }

  private static String generateMotor() {
    final int randMotor = new Random().nextInt(motors.length);
    return motors[randMotor];
  }

}