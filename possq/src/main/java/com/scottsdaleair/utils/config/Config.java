package com.scottsdaleair.utils.config;

public class Config {

    private String filename;

    Config(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return this.filename;
    }

}