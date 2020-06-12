package com.scottsdaleair.data.generators;

import java.util.Random;

public class GeneratorUtils {

  private static final Random rand = new Random();

  public static Random rand() {
    return rand;
  }

  public static String getPrice(int dollarRange, int centRange) {
    return "$" + dollarRange + "." + centRange;
  }

  /**
   * Returns a semi-realistic price within a given dollar range.
   * @param dollarRange Dollar amount ceiling
   * @return String price $dollar.cents
   */
  public static String getRealisticPrice(int dollarRange) {
    int[] centVals = {99, 95, 49};
    int cents = centVals[rand.nextInt(centVals.length)];
    int roundBy = dollarRange / 5;
    int dollars = Integer.max((rand.nextInt(dollarRange) / roundBy) * roundBy - 1, 2);
    return getPrice(dollars, cents);

  }

  /**
   * Returns a random value from the given string array.
   * @param arr Array to pull from
   * @return random string from array
   */
  public static String getRandValue(String[] arr) {
    int randIndex = rand.nextInt(arr.length);
    return arr[randIndex];
  }

  public static boolean linearProb(int num, int tot) {
    return new Random().nextDouble() < (tot - num + 1.0) / (1.1 * tot + 1);
  }

}