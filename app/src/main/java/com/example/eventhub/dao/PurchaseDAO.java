package com.example.eventhub.dao;

import com.example.eventhub.domain.Purchase;

import java.util.List;

/**
 * Interface representing Data Access Object (DAO) for Purchase entities.
 */
public interface PurchaseDAO {

    /**
     * Deletes the given purchase from the data store.
     *
     * @param purchase The purchase to be deleted.
     */
    void delete(Purchase purchase);

    /**
     * Retrieves a list of all purchases in the data store.
     *
     * @return A list of all purchases.
     */
    List<Purchase> findAll();

    /**
     * Saves the given purchase to the data store.
     *
     * @param purchase The purchase to be saved.
     */
    void save(Purchase purchase);

    /**
     * Finds a purchase by its ID in the data store.
     *
     * @param id The ID of the purchase to be found.
     * @return The found purchase or null if not found.
     */
    Purchase find(int id);

    /**
     * Generates the next ID to be used for a new purchase.
     *
     * @return The next available ID.
     */
    int nextId();
}
