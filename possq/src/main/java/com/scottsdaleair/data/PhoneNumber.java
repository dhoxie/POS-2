package com.scottsdaleair.data;

import java.util.Objects;

public class PhoneNumber {
    private String name;
    private String countryCode;
    private String areaCode;
    private String num;


    public PhoneNumber() {
    }

    public PhoneNumber(String name, String num) {
        this.name = name;
        this.num = num;
    }

    public PhoneNumber parsePhoneNumber(String number) {
        return null;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public PhoneNumber name(String name) {
        this.name = name;
        return this;
    }

    public PhoneNumber num(String num) {
        this.num = num;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PhoneNumber)) {
            return false;
        }
        PhoneNumber phoneNumber = (PhoneNumber) o;
        return Objects.equals(name, phoneNumber.name) && Objects.equals(num, phoneNumber.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, num);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", num='" + getNum() + "'" +
            "}";
    }

}