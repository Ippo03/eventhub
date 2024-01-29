package com.example.eventhub.view.Customer.CustomerPurchases;

/**
 * Interface defining the contract for the view of customer purchases.
 * Contains methods to update the view with the count of purchases and show the list of purchases made by the customer.
 */
public interface CustomerPurchasesView {

    /**
     * Sets the count of purchases in the view.
     *
     * @param count The count of purchases made by the customer.
     */
    void setCountOfPurchases(Integer count);

    /**
     * Displays the list of purchases made by the customer in the view.
     */
    void showPurchasesMade();
}
