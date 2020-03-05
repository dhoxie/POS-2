package com.scottsdaleair.data.generators;

import com.scottsdaleair.data.Vehicle;
import static com.scottsdaleair.data.generators.GeneratorData.*;


import java.util.Random;

public class VehicleGeneratorUtils {

  /**
   * Creates a randomly generated vehicle.
   * @return  A {@code Vehicle}
   */
  public static Vehicle createTestVehicle() {
    final String[] makeModel = generateMakeModel();
    final String make = makeModel[0];
    final String model = makeModel[1];
    final String year = (new Random().nextInt(200) + 1920) + "";
    final String plate = LicensePlate.generateLicensePlate();
    final String mileage = (new Random().nextInt(400000) + 1000) + "";
    String motor = "";
    if (make == "Tesla") {
      motor = "Tesla Dual 438 kW electric";
    } else {
      motor = generateMotor();
    }
    final String vin = VinGeneratorUtils.getRandomVin();
    final String comments = VEHICLE_COMMENTS[new Random().nextInt(VEHICLE_COMMENTS.length)];

    return new Vehicle(make, model, year, plate, mileage, motor, vin, comments);
  }

  private static String[] generateMakeModel() {
    final int randMake = new Random().nextInt(MAKES.length);
    final int randModel = new Random().nextInt(MODELS[randMake].length);
    String[] makeModel = { MAKES[randMake], MODELS[randMake][randModel] };
    return makeModel;
  }

  private static String generateMotor() {
    final int randMotor = new Random().nextInt(MOTORS.length);
    return MOTORS[randMotor];
  }

}