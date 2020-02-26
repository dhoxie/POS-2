package com.scottsdaleair.data.generators;

import com.scottsdaleair.data.Customer;
import com.scottsdaleair.data.PhoneNumber;

import java.util.Random;

public class CustomerGeneratorUtils {

  static final String[] firstNames = {
    "James",
    "John",
    "Robert",
    "Michael",
    "William",
    "David",
    "Richard",
    "Joseph",
    "Thomas",
    "Charles",
    "Christopher",
    "Daniel",
    "Matthew",
    "Anthony",
    "Donald",
    "Spencer",
    "Luke",
    "Mary",
    "Patricia",
    "Jennifer",
    "Linda",
    "Elizabeth",
    "Barbara",
    "Susan",
    "Jessica",
    "Sarah",
    "Karen",
    "Nancy",
    "Margaret",
    "Lisa",
    "Betty",
    "Dorothy",
    "Daylyn",
    "Kayla"
  };

  static final String[] lastaNames = {
    "Smith",
    "Johnson",
    "Williams",
    "Jones",
    "Brown",
    "Davis",
    "Miller",
    "Wilson",
    "Moore",
    "Taylor",
    "Anderson",
    "Hall",
    "Allen",
    "Young",
    "Hernandez",
    "King",
    "Wright",
    "Lopez",
    "Hill",
    "Scott",
    "Green",
    "Adams",
    "Lambert",
    "Hoxie",
    "Curley",
    "Mattfeld"
  };

  private static final String[] emailHosts = {
    "gmail.com",
    "yahoo.com",
    "comcast.net",
    "msn.com",
    "att.net",
    "me.com",
    "mac.com",
    "verizon.net",
    "live.com",
    "hotmail.com",
    "outlook.com",
    "aol.com",
    "icloud.com",
    "yahoo.ca"
  };

  private static final String[] unames = {
    "2freeny",
    "insterts",
    "jeansom",
    "midnighttrust",
    "proudmusic",
    "termergy",
    "readerooks",
    "astati",
    "mjas",
    "mr.mister",
    "dollface",
    "atragent",
    "relaissareh"
  };

  private static final String[] phoneNumNames = {
    "home",
    "cel",
    "work"
  };

  public static final String[] streetNames = {
    "Dogwood",
    "Airport",
    "Park",
    "Maple",
    "Aspen",
    "Hickory",
    "Washington",
    "Country Line",
    "Walnut",
    "Kansas",
    "Sunset",
    "Spruce",
    "Highland",
    "Pleasent",
    "Hillside",
    "Country Homes"
  };

  public static final String[] streetTypes = {
    "Ave.",
    "St.",
    "Blvd.",
    "Ln.",
    "Ct.",
    "Dr."
  };

  public static final String[] streetDirs = {
    "N",
    "S",
    "E",
    "W",
    "NE",
    "NW",
    "SE",
    "SW"
  };

  public static final String[][] cities = {
    {
      // Washington Cities
      "Seattle",
      "Vancouver",
      "Spokane",
      "Tacoma",
      "Bellvue",
      "Olympia",
      "Everett",
      "Bellingham",
      "Yakima",
      "Redmond",
      "Puyallup",
      "Renton",
      "Kennewick",
      "Richland",
      "Auburn",
      "Wenatchee",
      "Tri-Cities",
      "Mead",
      "Cheney",
      "Spokane Valley",
      "Millwood",
      "Liberty Lake",
      "Spokane Valley",
      "Four Lakes"
    },
    {
      // Idaho cities
      "Boise",
      "Idaho Falls",
      "Coeur d'Alene",
      "Twin Falls",
      "Nampa",
      "Meridian",
      "Lewiston",
      "Moscow",
      "Sandpoint"
    },
    {
      // Montana Cities
      "Billings",
      "Bozeman",
      "Missoula",
      "Great Falls",
      "Kalispell",
      "Butte",
      "Whitefish"
    },
    {
      // Oregon Cities
      "Portland",
      "Salem",
      "Eugene",
      "Bend",
      "Medford",
      "Beaverton",
      "Corvallis",
      "Hillsboro",
      "Albany",
      "Ashland"
    }
  };

  public static final String[] states = {
    "WA",
    "ID",
    "MT",
    "OR"
  };


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
    final int randFirstName = new Random().nextInt(firstNames.length);
    return firstNames[randFirstName];
  }

  private static String generateLastName() {
    final int randLastName = new Random().nextInt(lastaNames.length);
    return lastaNames[randLastName];
  }

  private static String generateEmail(final String firstname, final String lastname) {
    Random rand = new Random();
    final int randEmailHost = rand.nextInt(emailHosts.length);
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
      int randUname = rand.nextInt(unames.length);
      email += unames[randUname];
      if (rand.nextInt(3) < 1) {
        int randUname2 = rand.nextInt(unames.length);
        email += unames[randUname2];
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


    email += "@" + emailHosts[randEmailHost];
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
    String name = phoneNumNames[rand.nextInt(phoneNumNames.length)];
    return new PhoneNumber(name, num);
  }

  private static PhoneNumber[] generatePhoneNumbers() {
    int numberCount = new Random().nextInt(4);
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
    int randStreet = rand.nextInt(streetNames.length);
    int randStreetType = rand.nextInt(streetTypes.length);
    int randState = rand.nextInt(states.length);
    int randCity = rand.nextInt(cities[randState].length);
    addr = addrNum + "";

    boolean streeHasDir = rand.nextInt(10) < 4;
    if (streeHasDir) {
      addr += " " + streetDirs[rand.nextInt(streetDirs.length)];
    }
    addr += " " + streetNames[randStreet]
        + " " + streetTypes[randStreetType]
        + " " + cities[randState][randCity]
        + ", " + states[randState]
        + " " + randPostCode;
    return addr;
  }
}