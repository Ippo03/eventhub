package com.example.eventhub.domain;

import com.example.eventhub.util.Money;
import com.example.eventhub.util.Util;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a ticket category for a specific event.
 */
public class TicketCategory implements Serializable {
    /** The id of the ticket category. */
    private Integer id;
    /** The price of the ticket category. */
    private Money price;
    /** The name of the ticket category. */
    private CategoryName name;
    /** The description of the ticket category. */
    private String description;
    /** The quantity of tickets available for the ticket category. */
    private int quantity;
    /** The number of tickets sold for this category. */
    protected int ticketsSold;

    /**
     * Default constructor that initializes the ticket category.
     */
    public TicketCategory() {}

    /**
     * Parameterized constructor that initializes the ticket category.
     *
     * @param name        The name of the ticket category.
     * @param price       The price of the ticket category.
     * @param description The description of the ticket category.
     * @param quantity    The quantity of tickets for the ticket category.
     */
    public TicketCategory(Integer id, CategoryName name, Money price, String description, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.ticketsSold = 0;
    }

    /**
     * Returns the id of the ticket category.
     *
     * @return the ticket category id
     */
    public int getTicketCategoryId() {
        return id;
    }

    /**
     * Sets the name of the ticket category.
     *
     * @param name The name of the category.
     */
    public void setName(CategoryName name) {
        this.name = name;
    }

    /**
     * Returns the name of the ticket category.
     *
     * @return The name of the ticket category.
     */
    public CategoryName getName() {
        return name;
    }

    /**
     * Returns the price of the ticket category.
     *
     * @return The price of the ticket category.
     */
    public Money getPrice() {
        return price;
    }

    /**
     * Sets the price of the ticket category.
     *
     * @param price The price of the category.
     */
    public void setPrice(Money price) {
        this.price = price;
    }

    /**
     * Sets the description of the ticket category.
     *
     * @param description The description of the category.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the ticket category.
     *
     * @return The description of the ticket category.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the quantity of tickets for the ticket category.
     *
     * @param quantity The quantity of tickets for the ticket category.
     */
    public void setQuantity(int quantity) {
        Util.validateNonNegativeInteger(String.valueOf(quantity), "Quantity");
        this.quantity = quantity;
    }

    /**
     * Returns the quantity of tickets for the ticket category.
     *
     * @return The quantity of tickets for the ticket category.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the number of tickets sold for this category.
     *
     * @return The number of tickets sold for this category.
     */
    public int getTicketsSold() {
        return ticketsSold;
    }

    /**
     * Checks if two TicketCategory instances are equal.
     *
     * @param o The other object to compare.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketCategory)) return false;
        TicketCategory that = (TicketCategory) o;
        return quantity == that.quantity &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price);
    }

    /**
     * Generates the hash code for the TicketCategory instance.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, description, quantity, price);
    }
}
