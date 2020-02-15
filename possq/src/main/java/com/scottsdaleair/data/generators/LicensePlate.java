package com.scottsdaleair.data.generators;

public class LicensePlate {
  private static String[] INVALID_PLATE_LETTERS = { "FOR", "AXE", "JAM", "JAB",
      "ZIP", "ARE", "YOU", "JUG", "JAW", "JOY" };

  private static String generateLetters(int amount) {
    String letters = "";
    int n = 'Z' - 'A' + 1;
    for (int i = 0; i < amount; i++) {
      char c = (char) ('A' + Math.random() * n);
      letters += c;
    }
    return letters;
  }

  private static String generateDigits(int amount) {
    String digits = "";
    int n = '9' - '0' + 1;
    for (int i = 0; i < amount; i++) {
      char c = (char) ('0' + Math.random() * n);
      digits += c;
    }
    return digits;
  }

  private static boolean illegalWord(String letters) {
    for (String invaLtr : INVALID_PLATE_LETTERS) {
      if (letters.equals(invaLtr)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Generates a valid licence plate format.
   * @return {@code String} of a valid License Plate
   */
  public static String generateLicensePlate() {
    String licensePlate;
    String letters;
    do {
      letters = generateLetters(3);
    } while (illegalWord(letters));

    String digits = generateDigits(3);

    licensePlate = letters + "-" + digits;
    return licensePlate;
  }
}