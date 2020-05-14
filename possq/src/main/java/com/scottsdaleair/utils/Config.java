// current file structure for this. 
// primaryIp,primaryport 
// secondaryIp,secondaryPort
// emailPassword 

package com.scottsdaleair.utils;

import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;

public class Config {
  private static Config theConfig;
  private String primaryDatabaseIp;
  private String secondaryDatabaseIp;
  private int primaryDatabasePort;
  private int secondaryDatabasePort;
  private String emailPassword;
  private Boolean changed;
  private final String filename = "config.scottsdaleAirConfig";

  private Config() throws Exception {
    // open config file here
    this.changed = false;
    Scanner configFileScanner = new Scanner(new File(filename));
    String primary = configFileScanner.nextLine();
    String[] databaseTmp = primary.split(",");
    this.primaryDatabaseIp = databaseTmp[0];
    this.primaryDatabasePort = (int) Integer.parseInt(databaseTmp[1]);
    String secondary = configFileScanner.nextLine();
    this.emailPassword = configFileScanner.nextLine();
    databaseTmp = secondary.split(",");
    this.secondaryDatabaseIp = databaseTmp[0];
    this.secondaryDatabasePort = (int) Integer.parseInt(databaseTmp[1]);

  }

  // singleton
  public static Config getConfig() {
    if (theConfig == null) {
      try {
        return new Config();
      } catch (Exception e) {
        System.out.println("Error in creating config from file");
        return null;
      }
    } else {
      return theConfig;
    }
  }

  public void changePrimaryDatabase(String newIp, int newPort) {
    this.changed = true;
    this.primaryDatabaseIp = newIp;
    this.primaryDatabasePort = newPort;
  }

  public String getPrimaryDatabaseIp() {
    return this.primaryDatabaseIp;
  }

  public int getPrimaryDatabasePort() {
    return this.primaryDatabasePort;
  }

  public void changeSecondaryDatabase(String newIp, int newPort) {
    this.changed = true;
    this.secondaryDatabaseIp = newIp;
    this.secondaryDatabasePort = newPort;
  }

  public String getSecondaryDatabaseIp() {
    return this.secondaryDatabaseIp;
  }

  public int getSecondaryDatabasePort() {
    return this.secondaryDatabasePort;
  }

  // there is no change email password or change from email because that would
  // require rewiring all of the email class.
  // maybe will do that in the future but not now.
  // it is not as easy to change as the ip/port of the database
  public String getEmailPassword() {
    return this.emailPassword;
  }

  public void finalize() {
    System.out.println("finalized called ");
    if (changed) {
      try {
        FileWriter output = new FileWriter(filename);
        output.write(this.primaryDatabaseIp + "," + this.primaryDatabasePort + "\n" 
            + this.secondaryDatabaseIp + ","
            + this.secondaryDatabasePort + "\n" + this.emailPassword + "\n");
        output.close();
      } catch (Exception e) {
        System.out.println("did not write changes to the config file ");
      }
    }

  }
}
