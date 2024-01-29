package com.example.eventhub.domain;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.eventhub.util.Money;
import com.example.eventhub.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;

/**
 * Represents a purchase of tickets for one event.
 */
public class Purchase implements Serializable {
    private Integer id;

    /** The date of the purchase. */
    private LocalDateTime date;
    /** The total cost of the purchase. */
    private Money totalCost;
    /** The list of tickets included in the purchase. */
    private ArrayList<Ticket> tickets;

    public Purchase() {}
    /**
     * Constructs a purchase with the given list of tickets.
     *
     * @param tickets The list of tickets to be included in the purchase.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Purchase(Integer id, ArrayList<Ticket> tickets) {
        Util.validateNotNull(tickets, "Tickets");
        this.id = id;
        this.date = LocalDateTime.now();
        for (Ticket ticket : tickets) {
            ticket.setTicketState(TicketState.BOUGHT);
        }
        this.tickets = tickets;
        this.calculateTotalCost();
    }

    public Integer getPurchaseId() {
        return id;
    }

    /**
     * Sets the date of the purchase.
     *
     * @param date The date of the purchase.
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Returns the date of the purchase.
     *
     * @return The date of the purchase.
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Returns a copy of the list of tickets included in the purchase.
     *
     * @return The list of tickets in the purchase.
     */
    public ArrayList<Ticket> getTickets() {
        return new ArrayList<>(tickets);
    }

    /**
     * Sets the list of tickets included in the purchase.
     *
     * @param tickets The list of tickets to be included in the purchase.
     */
    public void setTickets(ArrayList<Ticket> tickets) {
        Util.validateNotNull(tickets, "Tickets");
        this.tickets = tickets;
    }

    /**
     * Calculates and sets the total cost of the purchase based on the individual ticket prices.
     */
    public void calculateTotalCost() {
        Money tempCost = new Money(new BigDecimal(0), Currency.getInstance("EUR"));
        for (Ticket ticket : this.getTickets()) {
            tempCost = tempCost.plus(ticket.getTicketPrice());
        }
        tempCost.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
        this.totalCost = tempCost;
    }

    /**
     * Returns the total cost of the purchase.
     *
     * @return The total cost of the purchase.
     */
    public Money getCost() {
        return totalCost;
    }

    /**
     * Returns the event associated with the first ticket in the purchase.
     *
     * @return The event of the first ticket in the purchase.
     */
    public Event getEvent() {
        return this.getTickets().get(0).getEvent();
    }
}
