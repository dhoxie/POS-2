package com.scottsdaleair.data.generators;

import com.scottsdaleair.data.Customer;
import com.scottsdaleair.data.PhoneNumber;
import static com.scottsdaleair.data.generators.GeneratorData.*;

import java.util.Random;

public class CustomerGeneratorUtils {

  /**
   * Creates a randomly generated customer.
   * @param customerID  The ID of the customer
   * @param invoices    The list of invoices for this customer
   * @param vehicles    The list of vehicles owned by this customer
   * @return
   */
  public static Customer createTestCustomer(final String customerID, final String[] invoices,
      final String[] vehicles) {
    final String id = customerID;
    final String fname = generateFirstName();
    final String lname = generateLastName();
    final String email = generateEmail(fname, lname);
    final String address = generateAddress();
    final PhoneNumber[] phones = generatePhoneNumbers();
    final Customer custRet = new Customer(id, fname, lname, email, address,
        phones, invoices, vehicles);
    return custRet;
  }

  private static String generateFirstName() {
    final int randFirstName = new Random().nextInt(FIRST_NAMES.length);
    return FIRST_NAMES[randFirstName];
  }

  private static String generateLastName() {
    final int randLastName = new Random().nextInt(LAST_NAMES.length);
    return LAST_NAMES[randLastName];
  }

  private static String generateEmail(final String firstname, final String lastname) {
    Random rand = new Random();
    final int randEmailHost = rand.nextInt(EMAIL_HOSTS.length);
    final boolean beginningNums = rand.nextInt(10) < 3;
    final boolean useFname = rand.nextInt(2) < 1;
    final boolean useLname = rand.nextInt(10) < 2;
    final boolean addNums = rand.nextInt(10) < 7;

    String email = "";

    if (beginningNums) {
      email += rand.nextInt(100) + "";
    }
    if (useFname) {
      boolean firstLetter = rand.nextInt(3) < 2;
      if (firstLetter) {
        email += firstname.toLowerCase().charAt(0) + "";
      } else {
        email += firstname.toLowerCase();
      }
    } else {
      int randUname = rand.nextInt(UNAMES.length);
      email += UNAMES[randUname];
      if (rand.nextInt(3) < 1) {
        int randUname2 = rand.nextInt(UNAMES.length);
        email += UNAMES[randUname2];
      }
    }
    if (useLname) {
      final boolean addPeriod = rand.nextInt(3) < 2;
      if (addPeriod) {
        email += ".";
      }
      email += lastname.toLowerCase();
    }
    if (addNums) {
      final int randNum = rand.nextInt(420);
      email += randNum + "";
    }


    email += "@" + EMAIL_HOSTS[randEmailHost];
    return email;
  }

  private static PhoneNumber generatePhoneNumber() {
    Random rand = new Random();
    int countryCode = rand.nextInt(5);
    int areaCode = rand.nextInt(800) + 100;
    int preNum = rand.nextInt(900) + 100;
    int postNum = rand.nextInt(9000) + 1000;

    String num = "";
    if (rand.nextInt(3) < 1) {
      num += "+" + countryCode + " ";
    }
    num = "(" + areaCode + ")";
    num += " " + preNum + "-" + postNum;
    String name = PHONE_NAMES[rand.nextInt(PHONE_NAMES.length)];
    return new PhoneNumber(name, num);
  }

  private static PhoneNumber[] generatePhoneNumbers() {
    int numberCount = new Random().nextInt(3) + 1;
    PhoneNumber[] nums = new PhoneNumber[numberCount];
    for (int i = 0; i < numberCount; i++) {
      nums[i] = generatePhoneNumber();
    }
    return nums;
  }

  private static String generateAddress() {
    Random rand = new Random();
    String addr = "";
    int addrNum = rand.nextInt(100000);
    int randPostCode = rand.nextInt(90000) + 10000;
    int randStreet = rand.nextInt(STREET_NAMES.length);
    int randStreetType = rand.nextInt(STREET_TYPES.length);
    int randState = rand.nextInt(STATES.length);
    int randCity = rand.nextInt(CITIES[randState].length);
    addr = addrNum + "";

    boolean streeHasDir = rand.nextInt(10) < 4;
    if (streeHasDir) {
      addr += " " + STREET_DIRS[rand.nextInt(STREET_DIRS.length)];
    }
    addr += " " + STREET_NAMES[randStreet]
        + " " + STREET_TYPES[randStreetType]
        + " " + CITIES[randState][randCity]
        + ", " + STATES[randState]
        + " " + randPostCode;
    return addr;
  }
}