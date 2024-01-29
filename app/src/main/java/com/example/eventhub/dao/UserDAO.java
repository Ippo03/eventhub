package com.example.eventhub.dao;

import com.example.eventhub.domain.User;

import java.util.List;

/**
 * Interface representing Data Access Object (DAO) for User entities.
 */
public interface UserDAO {

    /**
     * Deletes the given user from the data store.
     *
     * @param user The user to be deleted.
     */
    void delete(User user);

    /**
     * Retrieves a list of all users in the data store.
     *
     * @return A list of all users.
     */
    List<User> findAll();

    /**
     * Saves the given user to the data store.
     *
     * @param user The user to be saved.
     */
    void save(User user);

    /**
     * Finds a user by its ID in the data store.
     *
     * @param id The ID of the user to be found.
     * @return The found user or null if not found.
     */
    User find(int id);

    /**
     * Finds a user by its email address in the data store.
     *
     * @param email The email address of the user to be found.
     * @return The found user or null if not found.
     */
    User findByEmail(String email);

    /**
     * Generates the next ID to be used for a new user.
     *
     * @return The next available ID.
     */
    int nextId();

    /**
     * Updates the given user in the data store.
     *
     * @param user The user to be updated.
     */
    void update(User user);
}
