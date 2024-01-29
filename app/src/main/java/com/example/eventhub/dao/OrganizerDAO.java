package com.example.eventhub.dao;

import com.example.eventhub.domain.Organizer;

import java.util.List;

/**
 * Interface representing Data Access Object (DAO) for Organizer entities.
 */
public interface OrganizerDAO {

    /**
     * Deletes the given organizer from the data store.
     *
     * @param organizer The organizer to be deleted.
     */
    void delete(Organizer organizer);

    /**
     * Retrieves a list of all organizers in the data store.
     *
     * @return A list of all organizers.
     */
    List<Organizer> findAll();

    /**
     * Saves the given organizer to the data store.
     *
     * @param organizer The organizer to be saved.
     */
    void save(Organizer organizer);

    /**
     * Finds an organizer by its ID in the data store.
     *
     * @param id The ID of the organizer to be found.
     * @return The found organizer or null if not found.
     */
    Organizer find(int id);

    /**
     * Generates the next ID to be used for a new organizer.
     *
     * @return The next available ID.
     */
    int nextId();

    /**
     * Finds an organizer by their credentials (email and password) in the data store.
     *
     * @param email    The email of the organizer.
     * @param password The password of the organizer.
     * @return The found organizer or null if not found.
     */
    Organizer findByCredentials(String email, String password);

    /**
     * Updates the given organizer in the data store.
     *
     * @param organizer The organizer to be updated.
     */
    void update(Organizer organizer);

    /**
     * Finds an organizer with an already existing email in the data store.
     *
     * @param organizer The organizer to be checked.
     * @return The found organizer or null if not found.
     */
    Organizer findOrganizerWithAlreadyExistingEmail(Organizer organizer);
}
