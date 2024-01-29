package com.example.eventhub.domain;

import com.example.eventhub.util.Money;

import java.io.Serializable;

/**
 * Represents a ticket for an event. It has a price, a category, and a discount.
 */
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    /** The price of the ticket. */
    private Money ticketPrice;
    /** The discount applied to the ticket. */
    private TicketDiscount ticketDiscount;
    /** The category of the ticket. */
    private TicketCategory ticketCategory;
    /** The state of the ticket. */
    private TicketState ticketState;
    /** The event associated with the ticket. */
    private Event event;

    public Ticket() {}

    /**
     * Constructs a new Ticket object.
     *
     * @param event          The event for which the ticket is purchased.
     * @param ticketCategory The category of the ticket.
     * @param ticketDiscount The discount applied to the ticket.
     */
    public Ticket(Integer id, Event event, TicketCategory ticketCategory, TicketDiscount ticketDiscount) {
        this.id = id;
        this.event = event;
        event.ticketsSold++; // increase the number of tickets sold for the event
        setTicketCategory(ticketCategory);
        setTicketDiscount(ticketDiscount);
        this.ticketPrice = this.getTicketPrice();
        this.ticketState = TicketState.AVAILABLE;
    }

    public int getTicketId() {
        return id;
    }

    /**
     * Retrieves the state of the ticket.
     *
     * @return The state of the ticket.
     */
    public TicketState getTicketState() {
        return this.ticketState;
    }

    /**
     * Sets the state of the ticket.
     *
     * @param state The new state of the ticket.
     */
    public void setTicketState(TicketState state) {
        this.ticketState = state;
    }

    /**
     * Retrieves the event associated with the ticket.
     *
     * @return The event of the ticket.
     */
    public Event getEvent() {
        return this.event;
    }

    /**
     * Sets the event associated with the ticket.
     *
     * @param event The new event for the ticket.
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * Retrieves the category of the ticket.
     *
     * @return The category of the ticket.
     */
    public TicketCategory getTicketCategory() {
        return this.ticketCategory;
    }

    /**
     * Sets the category of the ticket.
     *
     * @param category The new category for the ticket.
     */
    public void setTicketCategory(TicketCategory category) {
//        this.categoryMatchWithEvent(category);
        this.ticketCategory = category;
    }

    /**
     * Retrieves the discount applied to the ticket.
     *
     * @return The discount of the ticket.
     */
    public TicketDiscount getTicketDiscount() {
        return this.ticketDiscount;
    }

    /**
     * Sets the discount for the ticket.
     *
     * @param discount The new discount for the ticket.
     */
    public void setTicketDiscount(TicketDiscount discount) {
//        this.discountMatchWithEvent(discount);
        this.ticketDiscount = discount;
    }


    /**
     * Calculates the final price of the ticket considering the discount.
     *
     * @return The price of the ticket after applying the discount.
     */
    public Money getTicketPrice() {
        Money startPrice = new Money(this.ticketCategory.getPrice().getAmount(), this.ticketCategory.getPrice().getCurrency());
        double discountPercentage = this.ticketDiscount.getPercentage();
        Money finalPrice = this.calculateNewPrice(startPrice, discountPercentage);
        return finalPrice;
    }

    /**
     * Calculates the new price after applying a discount percentage.
     *
     * @param startPrice         The original price of the ticket.
     * @param discountPercentage The percentage of the discount.
     * @return The new price after applying the discount.
     */
    private Money calculateNewPrice(Money startPrice, double discountPercentage) {
        Money discountAmount = startPrice.times(discountPercentage);
        Money newPrice = startPrice.minus(discountAmount);
        return newPrice;
    }

    /**
     * Ensures that the ticket category matches with the categories of the associated event.
     *
     * @param category The category to be checked.
     * @throws IllegalArgumentException If the category does not match with the event.
     */
    public boolean categoryMatchWithEvent(TicketCategory category) {
        if (this.getEvent().getTicketCategories().contains(category)) {
            return true;
        }
        throw new IllegalArgumentException("The category of the ticket does not match with the categories of the event.");
    }

    /**
     * Ensures that the ticket discount matches with the discounts of the associated event.
     *
     * @param discount The discount to be checked.
     * @throws IllegalArgumentException If the discount does not match with the event.
     */
    public boolean discountMatchWithEvent(TicketDiscount discount) {
        if (this.event.getTicketDiscounts().contains(discount)) {
            return true;
        }
        throw new IllegalArgumentException("The discount of the ticket does not match with the category.");
    }

    public void buyTicket() {
        if(this.ticketState == TicketState.AVAILABLE || this.ticketState == TicketState.RESERVED) {
            this.ticketState = TicketState.BOUGHT;
        }
        else {
            throw new IllegalArgumentException("The ticket is not available or reserved.");
        }
    }

    public void reserveTicket() {
        if(this.ticketState == TicketState.AVAILABLE) {
            this.ticketState = TicketState.RESERVED;
        }
        else {
            throw new IllegalArgumentException("The ticket is not available.");
        }
    }

    public void cancelTicket() {
        if(this.ticketState == TicketState.RESERVED || this.ticketState == TicketState.BOUGHT) {
            this.ticketState = TicketState.CANCELLED;
        }
        else {
            throw new IllegalArgumentException("The ticket is not reserved.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Ticket)) return false;
        Ticket ticket = (Ticket) obj;
        return ticket.getEvent().equals(this.getEvent()) &&
                ticket.getTicketCategory().equals(this.getTicketCategory()) &&
                ticket.getTicketDiscount().equals(this.getTicketDiscount());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.getEvent().hashCode();
        result = 31 * result + this.getTicketCategory().hashCode();
        result = 31 * result + this.getTicketDiscount().hashCode();
        return result;
    }

}
