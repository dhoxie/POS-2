package com.scottsdaleair.data.generators;

import java.util.Random;

public class GeneratorUtils {

  public static String getPrice(int dollars, int cents) {
    return "$" + dollars + "." + cents;
  }

  public static boolean linearProb(int num, int tot) {
    return new Random().nextDouble() < (tot - num + 1.0) / (1.1 * tot + 1);
  }


  
}