package com.scottsdaleair.utils;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Configurator {
  private static Gson gson = new Gson();
  private static Config conf = new Config();
  private static String filePath = "";
  private static String fileName = "conf.posc";
  private static boolean isDirty = false;

  private static boolean isLoaded() {
    return conf != null;
  }

  public static void load() throws IOException {
    isDirty = false;
    String json = Files.readString(Paths.get(filePath + fileName));
    conf = gson.fromJson(json, Config.class);

  }

  public static void save() throws IOException {
    if (isDirty && isLoaded()) {
      String json = gson.toJson(conf);
      Files.writeString(Paths.get(filePath + fileName), json);
      isDirty = false;
    }
  }

  public static void setFilePath(String fp) {
    filePath = fp;
  }

  public static void setFileName(String fn) {
    fileName = fn;
  }

  public static String getDbIp() {
    return conf.dbIP;
  }

  public static void setDbIp(String ip) {
    conf.dbIP = ip;
    isDirty = true;
  }

  public static int getDbPort() {
    return conf.dbPort;
  }

  public static void setDbPort(int port) {
    conf.dbPort = port;
    isDirty = true;
  }

  public static String getBakIp() {
    return conf.dbBakIP;
  }

  public static void setBakIp(String ip) {
    conf.dbBakIP = ip;
    isDirty = true;
  }

  public static int getBakPort() {
    return conf.dbBakPort;
  }

  public static void setBakPort(int port) {
    conf.dbBakPort = port;
    isDirty = true;
  }

  public static String getEmailPassword() {
    return conf.emailPasswd;
  }

  public static void setEmailPassword(String passwd) {
    conf.emailPasswd = passwd;
    isDirty = true;
  }

  public static void generateDefault() {
    conf = new Config("73.42.132.222", 27017, "dbBakIP", 27027, "passwd");
    isDirty = true;
  }


  private static class Config {
    private String dbIP;
    private int dbPort;
    private String dbBakIP;
    private int dbBakPort;
    private String emailPasswd;

    Config() {
      this.dbIP = null;
      this.dbPort = 0;
      this.dbBakIP = null;
      this.dbBakPort = 0;
      this.emailPasswd = null;
    }


    Config(String dbIp, int dbPort, String dbBakIP, int dbBakPort,
        String emailPasswd) {
      this.dbIP = dbIp;
      this.dbPort = dbPort;
      this.dbBakIP = dbBakIP;
      this.dbBakPort = dbBakPort;
      this.emailPasswd = emailPasswd;
    }

  }
}
