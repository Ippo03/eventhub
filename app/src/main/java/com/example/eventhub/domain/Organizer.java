package com.example.eventhub.domain;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Represents an organizer in the event management system.
 */
public class Organizer extends User implements Serializable {

    /** The Social Security Number (SSN) of the organizer. */
    private int ssn;

    /** The rating of the organizer based on the events they organize. */
    private BigDecimal rating;

    /** The list of events organized by the organizer. */
    private ArrayList<Event> events;

    public Organizer() {
        super();
    }

    public Organizer(String email) {
        super(email);
    }

    /**
     * Parameterized constructor that initializes the organizer.
     *
     * @param firstName The first name of the organizer.
     * @param lastName  The last name of the organizer.
     * @param email     The email address of the organizer.
     * @param age       The age of the organizer.
     * @param gender    The gender of the organizer.
     * @param address   The address of the organizer.
     * @param password  The password of the organizer.
     * @param ssn       The Social Security Number (SSN) of the organizer.
     */
    public Organizer(int id, String firstName, String lastName, String email, int age,
                     String gender, Address address, String password, int ssn) {
        super(id, firstName, lastName, email, age, gender, address, password);
        this.ssn = ssn;
        this.events = new ArrayList<>();
        calculateRating();
    }

    /**
     * Sets the Social Security Number (SSN) of the organizer.
     *
     * @param ssn The Social Security Number to set.
     */
    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    /**
     * Returns the Social Security Number (SSN) of the organizer.
     *
     * @return The SSN of the organizer.
     */
    public int getSsn() {
        return ssn;
    }

    /**
     * Returns the rating of the organizer.
     *
     * @return The rating of the organizer.
     */
    public BigDecimal getRating() {
        return rating;
    }

    /**
     * Returns the events organized by the organizer.
     *
     * @return The list of events organized by the organizer.
     */
    public ArrayList<Event> getEvents() {
        return new ArrayList<>(events);
    }

    /**
     * Adds an event to the organizer's list of events.
     *
     * @param event The event to be added.
     * @throws IllegalArgumentException if the event is already in the set.
     */
    public void addEvent(Event event) {
        Util.validateNotNull(event, "Event");
        this.events.add(event);
    }

    /**
     * Removes an event from the organizer's list of events.
     *
     * @param event The event to be removed.
     * @throws IllegalArgumentException if the event is not in the set.
     */
    public void removeEvent(Event event) {
        Util.validateRemovingFromList(event, this.getEvents(), "Event");
        this.events.remove(event);
    }

    /**
     * Calculates the rating of the organizer based on the average ratings of the events they organized.
     * If no events are present, the rating is set to zero.
     */
    public void calculateRating() {
        if(events.size() == 0) {
            this.rating = BigDecimal.ZERO;
            return;
        }

        double sum = 0.00;
        for(Event event : events) {
            sum += event.getAverageRating();
        }
        double average = sum / events.size();
        this.rating = new BigDecimal(average)
                .setScale(1, BigDecimal.ROUND_HALF_UP);
    }

    public void changeOrganizerSettings(String firstName, String lastName, Integer age, String gender, Address address,
                                        String email, String password, Integer ssn) {
        super.changeUserSettings(firstName, lastName, age, gender, address, email, password);
        this.ssn = ssn;
    }
}