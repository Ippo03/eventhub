package com.example.eventhub.dao;

import com.example.eventhub.domain.TicketDiscount;

import java.util.List;

/**
 * Interface representing Data Access Object (DAO) for TicketDiscount entities.
 */
public interface TicketDiscountDAO {

    /**
     * Deletes the given ticket discount from the data store.
     *
     * @param ticketDiscount The ticket discount to be deleted.
     */
    void delete(TicketDiscount ticketDiscount);

    /**
     * Retrieves a list of all ticket discounts in the data store.
     *
     * @return A list of all ticket discounts.
     */
    List<TicketDiscount> findAll();

    /**
     * Saves the given ticket discount to the data store.
     *
     * @param ticketDiscount The ticket discount to be saved.
     */
    void save(TicketDiscount ticketDiscount);

    /**
     * Finds a ticket discount by its ID in the data store.
     *
     * @param id The ID of the ticket discount to be found.
     * @return The found ticket discount or null if not found.
     */
    TicketDiscount find(int id);

    /**
     * Generates the next ID to be used for a new ticket discount.
     *
     * @return The next available ID.
     */
    int nextId();

    /**
     * Updates the given ticket discount in the data store.
     *
     * @param ticketDiscount The ticket discount to be updated.
     */
    void update(TicketDiscount ticketDiscount);
}
