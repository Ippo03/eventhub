package com.example.eventhub.domain;

import com.example.eventhub.util.Util;

import java.io.Serializable;

/**
 * Represents a ticket discount for a specific event.
 */
public class TicketDiscount implements Serializable {
    private Integer id;

    /** The percentage of the ticket discount. */
    private double percentage;
    /** The type of the ticket discount. */
    private DiscountType type;

    /**
     * Default constructor that initializes the ticket discount.
     */
    public TicketDiscount() {}

    /**
     * Parameterized constructor that initializes the ticket discount.
     *
     * @param type       The type of the ticket discount.
     * @param percentage The percentage of the ticket discount.
     */
    public TicketDiscount(Integer id, DiscountType type, double percentage) {
        this.id = id;
        this.type = type;
        setPercentage(percentage);
    }

    public int getTicketDiscountId() {
        return id;
    }

    /**
     * Returns the type of the ticket discount.
     *
     * @return The discount type of the ticket discount.
     */
    public DiscountType getType() {
        return type;
    }

    /**
     * Sets the type of the ticket discount.
     *
     * @param type The discount type of the ticket discount.
     */
    public void setType(DiscountType type) {
        if (type != null) {
            this.type = type;
        }
    }

    /**
     * Returns the percentage of the ticket discount.
     *
     * @return The discount percentage of the ticket discount.
     */
    public double getPercentage() {
        return this.percentage;
    }

    /**
     * Sets the percentage of the ticket discount.
     *
     * @param percentage The discount percentage of the ticket discount.
     */
    public void setPercentage(double percentage) {
        Util.validateDoubleInRange(percentage, "Percentage", 0.0, 100.0);
        this.percentage = Math.round(percentage) / 100.0; // converts percentage to decimal
    }

    /**
     * Checks if two TicketDiscount instances are equal.
     *
     * @param o The other object to compare.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketDiscount)) return false;
        TicketDiscount that = (TicketDiscount) o;
        return percentage == that.percentage &&
                type == that.type;
    }

    /**
     * Generates the hash code for the TicketDiscount instance.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return type.hashCode();
    }
}
