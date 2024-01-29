package com.example.eventhub.contacts;

import java.io.Serializable;

/**
 * Represents a physical address.
 */
public class Address implements Serializable {

    /** The street of the address. */
    private String street;

    /** The street number of the address. */
    private Integer number;

    /** The city of the address. */
    private String city;

    /** The ZIP code of the address. */
    private String zip;

    /** The country of the address (default is Greece). */
    private String country = "Greece";

    /** Default constructor. */
    public Address() { }

    /**
     * Copy constructor that creates a new Address instance with the same values as another Address.
     *
     * @param address The Address instance to copy.
     */
    public Address(Address address) {
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.city = address.getCity();
        this.zip = address.getZipCode();
        this.country = address.getCountry();
    }

    /**
     * Parameterized constructor that initializes the address.
     *
     * @param street  The street of the address.
     * @param number  The street number of the address.
     * @param city    The city of the address.
     * @param zip     The ZIP code of the address.
     * @param country The country of the address.
     */
    public Address(String street, Integer number, String city, String zip, String country) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }

    /**
     * Sets the street of the address.
     *
     * @param street The street of the address.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets the street of the address.
     *
     * @return The street of the address.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street number of the address.
     *
     * @param number The street number of the address.
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * Gets the street number of the address.
     *
     * @return The street number of the address.
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Sets the city of the address.
     *
     * @param city The city of the address.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the city of the address.
     *
     * @return The city of the address.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the ZIP code of the address.
     *
     * @param zipcode The ZIP code of the address.
     */
    public void setZipCode(String zipcode) {
        this.zip = zipcode;
    }

    /**
     * Gets the ZIP code of the address.
     *
     * @return The ZIP code of the address.
     */
    public String getZipCode() {
        return zip;
    }

    /**
     * Sets the country of the address.
     *
     * @param country The country of the address.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the country of the address.
     *
     * @return The country of the address.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns a string representation of the address.
     *
     * @return A string representation of the address.
     */
    public String toString() {
        return street + " " + number + ", " + city + " " + zip;
    }

    /**
     * Checks if two Address instances are equal.
     *
     * @param other The other object to compare.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof Address)) {
            return false;
        }

        Address theAddress = (Address) other;
        if (!(street == null ? theAddress.street
                == null : street.equals(theAddress.street))) {
            return false;
        }
        if (!(number == null ? theAddress.number
                == null : number.equals(theAddress.number))) {
            return false;
        }
        if (!(city == null ? theAddress.city
                == null : city.equals(theAddress.city))) {
            return false;
        }
        if (!(zip == null ? theAddress.zip
                == null : zip.equals(theAddress.zip))) {
            return false;
        }
        if (!(country == null ? theAddress.country
                == null : country.equals(theAddress.country))) {
            return false;
        }
        return true;
    }

    /**
     * Generates the hash code for the Address instance.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        if (street == null && number == null && city == null
                && zip == null && country == null) {
            return 0;
        }

        int result = 0;
        result = street == null ? result : 13 * result + street.hashCode();
        result = number == null ? result : 13 * result + number.hashCode();
        result = city == null ? result : 13 * result + city.hashCode();
        result = zip == null ? result : 13 * result + zip.hashCode();
        result = country == null ? result : 13 * result + country.hashCode();
        return result;
    }
}
