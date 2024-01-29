package com.example.eventhub.dao;

import com.example.eventhub.domain.Review;

import java.util.List;

/**
 * Interface representing Data Access Object (DAO) for Review entities.
 */
public interface ReviewDAO {

    /**
     * Deletes the given review from the data store.
     *
     * @param review The review to be deleted.
     */
    void delete(Review review);

    /**
     * Retrieves a list of all reviews in the data store.
     *
     * @return A list of all reviews.
     */
    List<Review> findAll();

    /**
     * Saves the given review to the data store.
     *
     * @param review The review to be saved.
     */
    void save(Review review);

    /**
     * Finds a review by its ID in the data store.
     *
     * @param id The ID of the review to be found.
     * @return The found review or null if not found.
     */
    Review find(int id);

    /**
     * Generates the next ID to be used for a new review.
     *
     * @return The next available ID.
     */
    int nextId();
}
