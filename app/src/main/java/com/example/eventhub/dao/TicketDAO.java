package com.example.eventhub.dao;

import com.example.eventhub.domain.Ticket;

import java.util.List;

/**
 * Interface representing Data Access Object (DAO) for Ticket entities.
 */
public interface TicketDAO {

    /**
     * Deletes the given ticket from the data store.
     *
     * @param ticket The ticket to be deleted.
     */
    void delete(Ticket ticket);

    /**
     * Retrieves a list of all tickets in the data store.
     *
     * @return A list of all tickets.
     */
    List<Ticket> findAll();

    /**
     * Saves the given ticket to the data store.
     *
     * @param ticket The ticket to be saved.
     */
    void save(Ticket ticket);

    /**
     * Finds a ticket by its ID in the data store.
     *
     * @param id The ID of the ticket to be found.
     * @return The found ticket or null if not found.
     */
    Ticket find(int id);

    /**
     * Generates the next ID to be used for a new ticket.
     *
     * @return The next available ID.
     */
    int nextId();
}
