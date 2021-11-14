package com.bik.smalldata.model;

import java.time.OffsetDateTime;
import java.util.Date;


public class RecordJson
{
    private Long id;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String street;
    private String city;
    private String state;
    private Long zip;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getZip() {
        return zip;
    }

    public void setZip(long zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", firstName='" + (firstName == null ? "null" : firstName) + '\'' +
                ", lastName='" + (lastName == null ? "null" : lastName) + '\'' +
                ", dateOfBirth=" + (dateOfBirth == null ? "null" : dateOfBirth.toString()) +
                ", street='" + (street == null ? "null" : street )+ '\'' +
                ", city='" + (city == null ? "null" : city ) + '\'' +
                ", state='" + (state == null ? "null" : state) + '\'' +
                ", zip=" + zip +
                '}';
    }
}
