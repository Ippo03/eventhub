package com.example.eventhub.domain;

import android.util.Log;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents an event with details such as name, location, date, genre, and type.
 */
public class Event implements Serializable {
    private Integer id;
    /** The name of the event. */
    private String name;
    /** The location of the event. */
    private Address address;
    /** The date and time of the event. */
    private LocalDateTime dateTime;
    /** The genre of the event. */
    protected Genre genre;
    /** The type of the event. */
    protected EventType type;
    /** The set of reviews for the event. */
    private ArrayList<Review> reviews;
    /** The list of ticket categories for the event. */
    private ArrayList<TicketCategory> ticketCategories;
    /** The list of ticket discounts for the event. */
    private ArrayList<TicketDiscount> ticketDiscounts;
    /** The average rating of the event. */
    private double averageRating;
    /** The maximum number of tickets that can be sold for this event. */
    private int eventCapacity = 0;
    /** The number of tickets available for this event. */
    private int availableTickets = 0;
    /** The number of tickets sold for this event. */
    protected int ticketsSold = 0;
    /** The ticket category count map. */
    private HashMap<TicketCategory, Integer> ticketCategoryCountMap;

    /**
     * Default constructor that initializes the event.
     */
    public Event() {
        this.reviews = new ArrayList<>();
        this.ticketCategories = new ArrayList<>();
        this.ticketDiscounts = new ArrayList<>();
    }

    /**
     * Parameterized constructor that initializes the event.
     *
     * @param name      The name of the event.
     * @param address   The location of the event.
     * @param dateTime  The date and time of the event.
     * @param genre     The genre of the event.
     * @param type      The type of the event.
     */
    public Event(Integer id, String name, Address address, LocalDateTime dateTime, Genre genre, EventType type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateTime = dateTime;
        this.genre = genre;
        this.type = type;
        this.reviews = new ArrayList<>();
        this.ticketCategories = new ArrayList<>();
        this.ticketDiscounts = new ArrayList<>();
        this.ticketCategoryCountMap = new HashMap<>();
    }

    public Integer getEventId() {
        return id;
    }

    /**
     * Sets the name of the event.
     *
     * @param name The name of the event.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the event.
     *
     * @return The name of the event.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the location of the event.
     *
     * @param address The location of the event.
     */
    public void setLocation(Address address) {
        this.address = address;
    }

    /**
     * Retrieves the location of the event.
     *
     * @return The location of the event.
     */
    public Address getLocation() {
        return address;
    }

    /**
     * Sets the date and time of the event.
     *
     * @param dateTime The date and time of the event.
     */
    public void setDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Retrieves the date and time of the event.
     *
     * @return The date and time of the event.
     */
    public LocalDateTime getDate() {
        return dateTime;
    }

    /**
     * Sets the genre of the event.
     *
     * @param genre The genre of the event.
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Retrieves the genre of the event.
     *
     * @return The genre of the event.
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Sets the type of the event.
     *
     * @param type The type of the event.
     */
    public void setType(EventType type) {
        this.type = type;
    }

    /**
     * Retrieves the type of the event.
     *
     * @return The type of the event.
     */
    public EventType getType() {
        return type;
    }

    /**
     * Retrieves the reviews for the event.
     *
     * @return The list of reviews for the event.
     */
    public ArrayList<Review> getReviews() {
        return new ArrayList<>(this.reviews);
    }

    /**
     * Adds a review to the set of reviews for the event.
     *
     * @param review The review to be added.
     */
    public void addReview(Review review) {
        Util.validateNotNull(review, "Review");
        this.reviews.add(review);
    }

    /**
     * Removes a review from the set of reviews for the event.
     *
     * @param review The review to be removed.
     */
    public void removeReview(Review review) {
        Util.validateRemovingFromList(review, this.getReviews(), "Review");
        this.reviews.remove(review);
    }

    /**
     * Calculates and retrieves the average rating of the event.
     *
     * @return The average rating of the event.
     */
    public double getAverageRating() {
        if (reviews.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (Review review : reviews) {
            sum += review.getGrade();
        }
        double average = sum / reviews.size();
        // Using BigDecimal for precision control
        BigDecimal bigDecimalValue = new BigDecimal(average);
        BigDecimal roundedValue = bigDecimalValue.setScale(1, BigDecimal.ROUND_HALF_UP);

        // Convert the rounded BigDecimal back to a double
        double result = roundedValue.doubleValue();
        this.averageRating = result;
        return result;
    }

    /**
     * Checks if the event's Genre matches with the customer's interests.
     *
     * @param customer the customer
     * @return the boolean
     */
    public boolean isMatchingWithCustomer(Customer customer) {
        if (customer == null) {
            return false;
        }

        ArrayList<Genre> customerInterestsAsGenres = Util.mapInterestsToGenres(customer.getInterests());
        if (customerInterestsAsGenres.contains(this.getGenre())) {
            return true;
        }

        return false;
    }

    /**
     * Validate the list of ticket categories against the ticket category count map.
     *
     * @param tickets the tickets
     * @return the ticket category
     */
    public TicketCategory validateTicketsOfCategories(ArrayList<Ticket> tickets) {
        Integer count = 0;
        HashMap<TicketCategory, Integer> ticketCategoryInputCountMap = new HashMap<>();

        for (Ticket ticket : tickets) {
            if (ticketCategoryInputCountMap.containsKey(ticket.getTicketCategory())) {
                count = ticketCategoryInputCountMap.get(ticket.getTicketCategory());
                ticketCategoryInputCountMap.put(ticket.getTicketCategory(), count + 1);
            } else {
                ticketCategoryInputCountMap.put(ticket.getTicketCategory(), 1);
            }

            if (ticketCategoryCountMap.containsKey(ticket.getTicketCategory())) {
                if (ticketCategoryInputCountMap.get(ticket.getTicketCategory()) > ticketCategoryCountMap.get(ticket.getTicketCategory())) {
                    return ticket.getTicketCategory();
                }
            }
        }

        return null;
    }

    /**
     * Checks if the current event conflicts with another event based on date and location.
     *
     * @param event The other event to check for conflicts.
     * @return True if there is a conflict, false otherwise.
     */
    public boolean conflictWith(Event event) {
        if (this.dateTime.equals(event.getDate()) && this.address.equals(event.getLocation())) {
            return true;
        }
        return false;
    }

    /**
     * Retrieves the ticket category count map.
     *
     * @return the ticket category count map
     */
    public HashMap<TicketCategory, Integer> getTicketCategoryCountMap() {
        return ticketCategoryCountMap;
    }

    public void setTicketCategoryCountMap(HashMap<TicketCategory, Integer> ticketCategoryCountMap) {
        this.ticketCategoryCountMap = ticketCategoryCountMap;
    }

    /**
     * Sets the ticket category count map.
     *
     * @param ticketCategory the ticket category
     * @param quantity       the quantity
     */

    public void setSingleTicketCategoryMap(TicketCategory ticketCategory, Integer quantity) {
        this.ticketCategoryCountMap.put(ticketCategory, quantity);

    }

    /**
     * Sets up the ticket category count map.
     */
    public void setTicketCategoryCountMap() {
        for (TicketCategory ticketCategory : this.getTicketCategories()) {
            this.ticketCategoryCountMap.put(ticketCategory, ticketCategory.getQuantity());
        }
    }

    /**
     * Retrieves the set of ticket categories for the event.
     *
     * @return The set of ticket categories for the event.
     */
    public ArrayList<TicketCategory> getTicketCategories() {
        return new ArrayList<>(this.ticketCategories);
    }

    /**
     * Sets the set of ticket categories for the event.
     *
     * @param ticketCategories The set of ticket categories to be set.
     */
    public void setTicketCategories(ArrayList<TicketCategory> ticketCategories) {
        this.ticketCategories = ticketCategories;
    }

    /**
     * Adds the ticketCategory to the event's ticket categories.
     *
     * @param ticketCategory the ticket category
     */
    public void addTicketCategory(TicketCategory ticketCategory) {
        Util.validateNotNull(ticketCategory,"Ticket Category");
        this.ticketCategories.add(ticketCategory);
    }

    /**
     * Removes the ticketCategory from the ticket categories of the event.
     *
     * @param ticketCategory the ticket category
     */
    public void removeTicketCategory(TicketCategory ticketCategory) {
        Util.validateRemovingFromList(ticketCategory, this.getTicketCategories(), "Ticket Category");
        this.ticketCategories.remove(ticketCategory);
    }

    /**
     * Retrieves the set of ticket discounts for the event.
     *
     * @return The set of ticket discounts for the event.
     */
    public ArrayList<TicketDiscount> getTicketDiscounts() {
        return new ArrayList<>(this.ticketDiscounts);
    }

    /**
     * Sets the ticket discounts for this event.
     *
     * @param ticketDiscounts The set of ticket discounts to be set.
     */
    public void setTicketDiscounts(ArrayList<TicketDiscount> ticketDiscounts) {
        this.ticketDiscounts = ticketDiscounts;
    }

    /**
     * Adds the ticketDiscount to the ticket discounts for this event.
     *
     * @param ticketDiscount the ticket discount
     */
    public void addTicketDiscount(TicketDiscount ticketDiscount) {
        Util.validateNotNull(ticketDiscount,"Ticket Discount");
        this.ticketDiscounts.add(ticketDiscount);
    }

    /**
     * Removes the ticketDiscount from the ticket discounts for this event.
     *
     * @param ticketDiscount the ticket discount
     */
    public void removeTicketDiscount(TicketDiscount ticketDiscount) {
        Util.validateRemovingFromList(ticketDiscount, this.getTicketDiscounts(), "Ticket Discount");
        this.ticketDiscounts.remove(ticketDiscount);
    }

    /**
     * Calculates this event's total capacity based on available tickets in each category.
     */
    public void calculateEventCapacity() {
        this.eventCapacity = 0;
        for (TicketCategory ticketCategory : this.getTicketCategories()) {
            this.eventCapacity += ticketCategory.getQuantity();
        }
    }

    /**
     * Calculates the number of available tickets for this event.
     */
    public void calculateAvailableTickets() {
        this.availableTickets = this.getEventCapacity() - this.getTicketsSold();
    }

    /**
     * Retrieves the maximum number of tickets that can be sold for this event.
     *
     * @return The maximum number of tickets that can be sold for this event.
     */
    public Integer getEventCapacity() {
        calculateEventCapacity();
        return this.eventCapacity;
    }

    /**
     * Calculates the number of tickets sold for this event.
     */
    public void calculateTicketsSold() {
        this.ticketsSold = 0;
        for (TicketCategory ticketCategory : this.getTicketCategories()) {
            this.ticketsSold += ticketCategory.getQuantity() - this.getTicketCategoryCountMap().get(ticketCategory);
        }
    }

    /**
     * Retrieves the number of tickets sold for this event.
     *
     * @return The number of tickets sold for this event.
     */
    public Integer getTicketsSold() {
        calculateTicketsSold();
        return this.ticketsSold;
    }

    /**
     * Retrieves the number of available tickets for this event.
     *
     * @return The number of available tickets for this event.
     */
    public Integer getAvailableTickets() {
        calculateAvailableTickets();
        return this.availableTickets;
    }

    /**
     * Checks if two Event instances are equal.
     *
     * @param o The other object to compare.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event that = (Event) o;
        return this.name.equals(that.name) && this.dateTime.equals(that.dateTime) && this.address.equals(that.address);
    }

    /**
     * Generates the hash code for the Event instance.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return this.name.hashCode() + this.address.hashCode();
    }
}
