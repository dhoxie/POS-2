package com.scottsdaleair.utils.config;


public class DBConfig implements Config {
    private String dbIP;
    private int dbPort;
    private String dbBakIP;
    private int dbBakPort;

    public DBConfig() {
        this("", 0, "", 0);
    }

    public DBConfig(String dbIp, int dbPort, String dbBakIP, int dbBakPort) {
        this.dbIP = dbIp;
        this.dbPort = dbPort;
        this.dbBakIP = dbBakIP;
        this.dbBakPort = dbBakPort;
    }

    public String getDbIP() {
        return this.dbIP;
    }

    public void setDbIP(String dbIP) {
        this.dbIP = dbIP;
    }

    public int getDbPort() {
        return this.dbPort;
    }

    public void setDbPort(int dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbBakIP() {
        return this.dbBakIP;
    }

    public void setDbBakIP(String dbBakIP) {
        this.dbBakIP = dbBakIP;
    }

    public int getDbBakPort() {
        return this.dbBakPort;
    }

    public void setDbBakPort(int dbBakPort) {
        this.dbBakPort = dbBakPort;
    }
}