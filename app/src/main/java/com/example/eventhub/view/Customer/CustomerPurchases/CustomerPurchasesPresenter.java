package com.example.eventhub.view.Customer.CustomerPurchases;

import android.annotation.SuppressLint;

import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.dao.PurchaseDAO;
import com.example.eventhub.domain.Customer;
import com.example.eventhub.domain.Purchase;

import java.util.ArrayList;

/**
 * Presenter class for handling customer purchases-related functionality in the UI.
 * Manages the interaction between the view ({@link CustomerPurchasesView}) and the data access objects.
 */
public class CustomerPurchasesPresenter {
    private CustomerPurchasesView view;
    private CustomerDAO customerDAO;
    private PurchaseDAO purchaseDAO;
    private Customer customer;
    private ArrayList<Purchase> purchaseList;

    /**
     * Constructor to initialize the presenter with the required data access objects.
     *
     * @param customerDAO  The {@link CustomerDAO} for accessing customer data.
     * @param purchaseDAO  The {@link PurchaseDAO} for accessing purchase data.
     */
    public CustomerPurchasesPresenter(CustomerDAO customerDAO, PurchaseDAO purchaseDAO) {
        this.customerDAO = customerDAO;
        this.purchaseDAO = purchaseDAO;
    }

    /**
     * Sets the view associated with this presenter.
     *
     * @param view The {@link CustomerPurchasesView} to be associated with this presenter.
     */
    public void setView(CustomerPurchasesView view) {
        this.view = view;
    }

    /**
     * Gets the associated view.
     *
     * @return The associated {@link CustomerPurchasesView}.
     */
    public CustomerPurchasesView getView() {
        return view;
    }

    /**
     * Sets the customer for which purchases are being displayed.
     *
     * @param customerId The ID of the customer.
     */
    public void setCustomer(Integer customerId) {
        customer = customerDAO.find(customerId);
    }

    /**
     * Gets the current customer.
     *
     * @return The current {@link Customer}.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the list of customer purchases and sorts them by date in descending order.
     */
    @SuppressLint("NewApi")
    public void setPurchaseList() {
        purchaseList = getCustomer().getPurchases();
        purchaseList.sort((purchase1, purchase2) -> purchase2.getEvent().getDate().compareTo(purchase1.getEvent().getDate()));
    }

    /**
     * Gets the list of customer purchases.
     *
     * @return The list of {@link Purchase} objects.
     */
    public ArrayList<Purchase> getPurchaseList() {
        return purchaseList;
    }

    /**
     * Sets the count of customer purchases in the associated view.
     */
    public void setCountOfPurchases() {
        view.setCountOfPurchases(purchaseList.size());
    }

    /**
     * Notifies the view to show the list of purchases made by the customer.
     */
    public void onShowPurchasesMade() {
        view.showPurchasesMade();
    }
}
