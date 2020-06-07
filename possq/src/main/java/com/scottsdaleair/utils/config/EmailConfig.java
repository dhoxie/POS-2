package com.scottsdaleair.utils.config;

public class EmailConfig implements Config {

    private String email;
    private String password;

    public EmailConfig() {
        this("");
    }

    public EmailConfig(String password) {
        this.password = password;
        this.email = "";
    }

    public void setPassword(String pwd) {
        this.password = pwd;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String getFilename() {
        return EmailConfig.class.getSimpleName().toLowerCase();
    }

}