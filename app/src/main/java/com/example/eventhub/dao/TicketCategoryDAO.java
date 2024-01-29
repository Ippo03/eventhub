package com.example.eventhub.dao;

import com.example.eventhub.domain.TicketCategory;

import java.util.List;

/**
 * Interface representing Data Access Object (DAO) for TicketCategory entities.
 */
public interface TicketCategoryDAO {

    /**
     * Deletes the given ticket category from the data store.
     *
     * @param ticketCategory The ticket category to be deleted.
     */
    void delete(TicketCategory ticketCategory);

    /**
     * Retrieves a list of all ticket categories in the data store.
     *
     * @return A list of all ticket categories.
     */
    List<TicketCategory> findAll();

    /**
     * Saves the given ticket category to the data store.
     *
     * @param ticketCategory The ticket category to be saved.
     */
    void save(TicketCategory ticketCategory);

    /**
     * Finds a ticket category by its ID in the data store.
     *
     * @param id The ID of the ticket category to be found.
     * @return The found ticket category or null if not found.
     */
    TicketCategory find(int id);

    /**
     * Generates the next ID to be used for a new ticket category.
     *
     * @return The next available ID.
     */
    int nextId();

    /**
     * Updates the given ticket category in the data store.
     *
     * @param ticketCategory The ticket category to be updated.
     */
    void update(TicketCategory ticketCategory);
}
