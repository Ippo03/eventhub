package com.example.eventhub.view.Customer.PurchaseDetails;

import com.example.eventhub.domain.Purchase;

/**
 * An interface defining the contract for the view displaying details of a purchase.
 */
public interface PurchaseDetailsView {

    /**
     * Sets the name of the purchase in the view.
     *
     * @param name The name of the purchase.
     */
    void setPurchaseName(String name);

    /**
     * Sets the location of the purchase in the view.
     *
     * @param location The location of the purchase.
     */
    void setPurchaseLocation(String location);

    /**
     * Sets the date of the purchase in the view.
     *
     * @param date The date of the purchase.
     */
    void setPurchaseDate(String date);

    /**
     * Sets the total cost of the purchase in the view.
     *
     * @param totalCost The total cost of the purchase.
     */
    void setPurchaseTotalCost(String totalCost);

    /**
     * Displays the purchased tickets associated with the given purchase in the view.
     *
     * @param purchase The purchase containing the tickets to be displayed.
     */
    void showPurchasedTickets(Purchase purchase);
}

