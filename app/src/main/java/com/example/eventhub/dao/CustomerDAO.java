package com.example.eventhub.dao;

import com.example.eventhub.domain.Customer;

import java.util.List;

/**
 * Interface representing Data Access Object (DAO) for Customer entities.
 */
public interface CustomerDAO {

    /**
     * Deletes the given customer from the data store.
     *
     * @param customer The customer to be deleted.
     */
    void delete(Customer customer);

    /**
     * Retrieves a list of all customers in the data store.
     *
     * @return A list of all customers.
     */
    List<Customer> findAll();

    /**
     * Saves the given customer to the data store.
     *
     * @param customer The customer to be saved.
     */
    void save(Customer customer);

    /**
     * Finds a customer by their ID in the data store.
     *
     * @param id The ID of the customer to be found.
     * @return The found customer or null if not found.
     */
    Customer find(int id);

    /**
     * Finds a customer by their email and password credentials.
     *
     * @param email    The email of the customer.
     * @param password The password of the customer.
     * @return The found customer or null if not found.
     */
    Customer findByCredentials(String email, String password);

    /**
     * Generates the next ID to be used for a new customer.
     *
     * @return The next available ID.
     */
    int nextId();

    /**
     * Updates the given customer in the data store.
     *
     * @param customer The customer to be updated.
     */
    void update(Customer customer);

    /**
     * Finds a customer with an already existing email in the data store.
     *
     * @param customer The customer with the email to be checked.
     * @return The found customer or null if not found.
     */
    Customer findCustomerWithAlreadyExistingEmail(Customer customer);
}
