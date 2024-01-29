package com.example.eventhub.view.Customer.CustomerPurchases;

import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.dao.PurchaseDAO;
import com.example.eventhub.memorydao.CustomerDAOMemory;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.memorydao.PurchaseDAOMemory;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Unit tests for the CustomerPurchasesPresenter class, focusing on the interaction
 * between the presenter and the view, as well as specific functionality related to
 * setting up customer information and handling purchases.
 */
public class CustomerPurchasesPresenterTest {
    private CustomerPurchasesViewStub view;
    private CustomerPurchasesPresenter presenter;

    private CustomerDAO customerDAO;
    private PurchaseDAO purchaseDAO;

    /**
     * Set up method for initializing the test environment.
     * Prepares test data using MemoryInitializer, initializes the view with a stub,
     * and sets up in-memory implementations for CustomerDAO and PurchaseDAO.
     */
    @Before
    public void setUp() {
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        view = new CustomerPurchasesViewStub();

        customerDAO = new CustomerDAOMemory();
        purchaseDAO = new PurchaseDAOMemory();

        // Initialize the presenter with the initialized DAOs
        presenter = new CustomerPurchasesPresenter(customerDAO, purchaseDAO);
        presenter.setView(view);

        // Set a customer for testing purposes
        presenter.setCustomer(4);
    }

    /**
     * Test case for setting a new view for the presenter.
     * Validates that the view is successfully set in the presenter.
     */
    @Test
    public void onSetView() {
        CustomerPurchasesView newView = new CustomerPurchasesViewStub();
        presenter.setView(newView);
        Assertions.assertEquals(newView, presenter.getView());
    }

    /**
     * Test case for setting a customer in the presenter.
     * Validates that the customer information is correctly set.
     */
    @Test
    public void onSetCustomer() {
        Assertions.assertEquals(4, presenter.getCustomer().getId());
    }

    /**
     * Test case for setting the purchase list in the presenter.
     * Validates that the purchase list is correctly set and contains the expected number of purchases.
     */
    @Test
    public void onSetPurchaseList() {
        presenter.setPurchaseList();
        Assertions.assertEquals(10, presenter.getPurchaseList().size());
    }

    /**
     * Test case for setting the count of purchases in the view.
     * Validates that the count of purchases in the view is correctly updated.
     */
    @Test
    public void onSetCountOfPurchases() {
        presenter.setPurchaseList();
        presenter.setCountOfPurchases();
        Assertions.assertEquals(10, view.getCountOfPurchases());
    }

    /**
     * Test case for showing purchases made by the customer.
     * Validates that the presenter correctly triggers the view to display the purchases made.
     */
    @Test
    public void onShowPurchasesMade() {
        presenter.setPurchaseList();
        presenter.onShowPurchasesMade();
        Assertions.assertEquals(1, view.getShowPurchasesMadeCount());
    }
}
