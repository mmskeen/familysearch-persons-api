package com.example.api3.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Address implements Serializable {

    @Column(name="street", length = 100, nullable = true)
    private String street;

    @Column(name="city", length = 100, nullable = false)
    private String city;

    @Column(name="state", length = 100, nullable = false)
    private String state;

    @Column(name="zip", length = 20, nullable = true)
    private String zip;

    @Column(name="country", length = 100, nullable = false)
    private String country;

    public Address(String street, String city, String state, String zip, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public Address() {
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

}
