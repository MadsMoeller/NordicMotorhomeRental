package com.example.nordicmotorhomes1.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CityModel {

    @Id
    private String zipcode;
    private String city;

    public CityModel() {
    }

    public CityModel(String zipcode, String city) {
        this.zipcode = zipcode;
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "City{" +
                "zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
