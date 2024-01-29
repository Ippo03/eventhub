package com.example.eventhub.domain;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.util.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a customer in the event management system.
 */
public class Customer extends User implements Serializable {

    /** The set of interests associated with the customer. */
    private Set<Interest> interests;

    /** The set of purchases made by the customer. */
    private ArrayList<Purchase> purchases;

    public Customer() {
        super();
        this.interests = new HashSet<>();
        this.purchases = new ArrayList<>();
    }

    public Customer(String email) {
        super(email);
        this.interests = new HashSet<>();
        this.purchases = new ArrayList<>();
    }

    /**
     * Parameterized constructor that initializes the customer.
     *
     * @param name      The first name of the customer.
     * @param lastName  The last name of the customer.
     * @param email     The email address of the customer.
     * @param age       The age of the customer.
     * @param gender    The gender of the customer.
     * @param address   The address of the customer.
     * @param password  The password of the customer.
     * @param interests The set of interests associated with the customer.
     */
    public Customer(Integer id, String name, String lastName, String email, int age,
                    String gender, Address address, String password, Set<Interest> interests) {
        super(id, name, lastName, email, age, gender, address, password);
        this.interests = interests;
        this.purchases = new ArrayList<>();
    }

    /**
     * Returns the interests of the customer.
     *
     * @return The interests of the customer.
     */
    public Set<Interest> getInterests() {
        return new HashSet<>(interests);
    }

    /**
     * Sets the interests of the customer.
     *
     * @param interests The interests of the customer.
     */
    public void setInterests(Set<Interest> interests) {
        this.interests = interests;
    }

    /**
     * Returns the purchases made by the customer.
     *
     * @return The purchases made by the customer.
     */
    public ArrayList<Purchase> getPurchases() {
        return new ArrayList<>(purchases);
    }

    /**
     * Adds an interest to the customer's set of interests.
     *
     * @param interest The interest to be added.
     * @throws IllegalArgumentException if the interest is already in the set.
     */
    public void addInterest(Interest interest) {
        Util.validateAddingToSet(interest, this.getInterests(), "Interest");
        interests.add(interest);
    }

    /**
     * Removes an interest from the customer's set of interests.
     *
     * @param interest The interest to be removed.
     * @throws IllegalArgumentException if the interest is not in the set.
     */
    public void removeInterest(Interest interest) {
        Util.validateRemovingFromSet(interest, this.getInterests(), "Interest");
        this.interests.remove(interest);
    }

    /**
     * Adds a purchase to the customer's set of purchases.
     *
     * @param purchase The purchase to be added.
     * @throws IllegalArgumentException if the purchase is already in the set.
     */
    public void addPurchase(Purchase purchase) {
        Util.validateNotNull(purchase, "Purchase");
        purchases.add(purchase);
    }

    /**
     * Removes a purchase from the customer's set of purchases.
     *
     * @param purchase The purchase to be removed.
     * @throws IllegalArgumentException if the purchase is not in the set.
     */
    public void removePurchase(Purchase purchase) {
        Util.validateRemovingFromList(purchase, purchases, "Purchase");
        this.purchases.remove(purchase);
    }

    /**
     * Checks if the customer can review a specific event.
     *
     * @param event The event to check for review eligibility.
     * @return true if the customer can review the event, false otherwise.
     * @throws IllegalArgumentException if the event is null.
     */
    public boolean canReview(Event event) {
        Util.validateNotNull(event, "Event");

        for (Purchase purchase : purchases) {
            if (purchase.getEvent().equals(event)) {
                return true;
            }
        }
        return false;
    }

    public void changeCustomerSettings(String firstName, String lastName, Integer age, String gender, Address address,
                                      String email, String password, Set<Interest> interests) {
        super.changeUserSettings(firstName, lastName, age, gender, address, email, password);
        this.interests = interests;
    }
}
