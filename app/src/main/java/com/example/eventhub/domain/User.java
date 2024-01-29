package com.example.eventhub.domain;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.util.Util;

/**
 * Represents a user in the system.
 */
public class User {
    private Integer id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected int age;
    protected String gender;
    protected Address address;
    protected String password;

    public User() {}

    public User(String email) {
        this.email = email;
    }

    /**
     * Constructs a new User with the specified information.
     *
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param email     The email address of the user.
     * @param age       The age of the user.
     * @param gender       The gender/sex of the user.
     * @param address   The address of the user.
     * @param password  The password of the user.
     */
    public User(Integer id, String firstName, String lastName, String email, int age, String gender, Address address, String password) {
        this.id = id;
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setAge(age);
        setGender(gender);
        setAddress(address);
        setPassword(password);
    }

    public Integer getId() {
        return id;
    }

    /**
     * Gets the first name of the user.
     *
     * @return The first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the age of the user.
     *
     * @return The age of the user.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the gender of the user.
     *
     * @return The gender of the user.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets the address of the user.
     *
     * @return The address of the user.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName The first name to set.
     * @throws IllegalArgumentException if the first name is null, empty, or contains non-alphabetic characters.
     */
    public void setFirstName(String firstName) {
        Util.validateNonNullAndNonEmpty(firstName, "Name");
        Util.validateRegex(firstName, Util.ALPHABETIC_REGEX, "Name must contain only letters");
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName The last name to set.
     * @throws IllegalArgumentException if the last name is null, empty, or contains non-alphabetic characters.
     */
    public void setLastName(String lastName) {
        Util.validateNonNullAndNonEmpty(lastName, "Last name");
        Util.validateRegex(lastName, Util.ALPHABETIC_REGEX, "Last name must contain only letters");
        this.lastName = lastName;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The email address to set.
     * @throws IllegalArgumentException if the email is null, empty, or not a valid email address.
     */
    public void setEmail(String email) {
        Util.validateNonNullAndNonEmpty(email, "Email");
        Util.validateRegex(email, Util.EMAIL_REGEX, "Email must be valid");
        this.email = email;
    }

    /**
     * Sets the age of the user.
     *
     * @param age The age to set.
     * @throws IllegalArgumentException if the age is a negative integer.
     */
    public void setAge(int age) {
        Util.validateNonNegativeInteger(String.valueOf(age), "Age");
        this.age = age;
    }

    /**
     * Sets the gender of the user.
     *
     * @param gender The gender/sex to set.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Sets the address of the user.
     *
     * @param address The address to set.
     * @throws IllegalArgumentException if the address is null or has a non-positive street number.
     */
    public void setAddress(Address address) {
        Util.validateNonNullAndNonEmpty(address.getStreet(), "Address");
        Util.validateNonNegativeInteger(String.valueOf(address.getNumber()), "Street number");
        this.address = address;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password to set.
     * @throws IllegalArgumentException if the password is null or empty.
     */
    public void setPassword(String password) {
        Util.validateNonNullAndNonEmpty(password, "Password");
        this.password = password;
    }

    /**
     * Changes the user settings.
     *
     * @param firstName The new first name.
     * @param lastName  The new last name.
     * @param email     The new email address.
     * @param age       The new age.
     * @param address   The new address.
     */
    public void changeUserSettings(String firstName, String lastName,  int age, String gender, Address address, String email, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setGender(gender);
        setAddress(address);
        setEmail(email);
        setPassword(password);
    }
}
