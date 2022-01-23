package com.bik.smalldata.model;

import com.google.gson.annotations.SerializedName;

import java.time.OffsetDateTime;

/**
 * Note: Keep this Record object very light weight because based on the
 * size of input files you might have to create a lot of Record Objects.
 * One way to reduce the size of record object is to put any methods related
 * to it in separate Util class, here we have {@link com.bik.smalldata.util.RecordUtil}
 */
public class Record
{
    private Long id;
    private String firstName;
    private String lastName;
    private OffsetDateTime dateOfBirth;

    private String street;
    private String city;
    private String state;
    private Long zip;

    public Record(final RecordBuilder recordBuilder)
    {
        this.id = recordBuilder.id;
        this.firstName = recordBuilder.firstName;
        this.lastName = recordBuilder.lastName;
        this.dateOfBirth = recordBuilder.dateOfBirth;
        this.street = recordBuilder.street;
        this.city = recordBuilder.city;
        this.state = recordBuilder.state;
        this.zip = recordBuilder.zip;
    }

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

    public OffsetDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(OffsetDateTime dateOfBirth) {
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

    public static class RecordBuilder
    {
        private Long id;
        private final String firstName;
        private final String lastName;
        private OffsetDateTime dateOfBirth;
        private String street;
        private String city;
        private String state;
        private long zip;

        public RecordBuilder(final String firstName, final String lastName)
        {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public RecordBuilder id( Long id)
        {
            this.id = id;
            return this;
        }

        public RecordBuilder dateOfBirth(final OffsetDateTime dateOfBirth)
        {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public RecordBuilder street(final String street)
        {
            this.street = street;
            return this;
        }

        public RecordBuilder state(final String state)
        {
            this.state = state;
            return this;
        }

        public RecordBuilder zip(final Long zip)
        {
            this.zip = zip;
            return this;
        }

        public RecordBuilder city(final String city)
        {
            this.city = city;
            return this;
        }

        public Record build()
        {
            return new Record(this);
        }
    }
}
