package com.example.eventhub.view.Customer.PurchaseDetails;

import android.annotation.SuppressLint;

import com.example.eventhub.dao.PurchaseDAO;
import com.example.eventhub.domain.Purchase;
import com.example.eventhub.domain.Ticket;
import com.example.eventhub.domain.TicketKey;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Presenter class responsible for managing the presentation logic of the PurchaseDetailsActivity.
 * Interacts with the associated PurchaseDetailsView and PurchaseDAO.
 */
public class PurchaseDetailsPresenter {

    private ArrayList<Ticket> aggregatedTicketList;
    private HashMap<TicketKey, Integer> ticketCountMap;
    private PurchaseDAO purchaseDAO;
    private PurchaseDetailsView view;
    private Purchase attachedPurchase;

    /**
     * Constructor for PurchaseDetailsPresenter.
     *
     * @param purchaseDAO The data access object for Purchase entities.
     */
    public PurchaseDetailsPresenter(PurchaseDAO purchaseDAO) {
        this.purchaseDAO = purchaseDAO;
        this.aggregatedTicketList = new ArrayList<>();
        this.ticketCountMap = new HashMap<>();
    }

    /**
     * Sets the associated view for the presenter.
     *
     * @param view The PurchaseDetailsView instance.
     */
    public void setView(PurchaseDetailsView view) {
        this.view = view;
    }

    /**
     * Retrieves the associated view.
     *
     * @return The PurchaseDetailsView instance.
     */
    public PurchaseDetailsView getView() {
        return view;
    }

    /**
     * Sets the attached purchase based on the provided purchase ID.
     *
     * @param purchaseId The ID of the purchase to be attached.
     */
    public void setAttachedPurchase(Integer purchaseId) {
        this.attachedPurchase = purchaseDAO.find(purchaseId);
    }

    /**
     * Retrieves the currently attached purchase.
     *
     * @return The attached Purchase instance.
     */
    public Purchase getAttachedPurchase() {
        return attachedPurchase;
    }

    /**
     * Sets the purchase details to be displayed in the view.
     */
    @SuppressLint("NewApi")
    public void onSetPurchaseDetails() {
        view.setPurchaseName(attachedPurchase.getEvent().getName());
        view.setPurchaseLocation(attachedPurchase.getEvent().getLocation().toString());
        view.setPurchaseDate(attachedPurchase.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        view.setPurchaseTotalCost(attachedPurchase.getCost().toString());
    }

    /**
     * Sets the aggregated list of tickets for the purchase.
     */
    public void setAggregatedTickets() {
        for (Ticket ticket : attachedPurchase.getTickets()) {
            TicketKey key = new TicketKey(ticket.getTicketDiscount(), ticket.getTicketCategory(), ticket.getTicketPrice());
            if (ticketCountMap.containsKey(key) && !aggregatedTicketList.contains(ticket)) {
                this.aggregatedTicketList.add(ticket);
            }
        }
    }

    /**
     * Retrieves the aggregated list of tickets.
     *
     * @return The aggregated list of tickets.
     */
    public ArrayList<Ticket> getAggregatedTicketList() {
        return this.aggregatedTicketList;
    }

    /**
     * Sets the ticket count map for each ticket category and price.
     */
    public void setTicketCountMap() {
        for (Ticket ticket : attachedPurchase.getTickets()) {
            TicketKey key = new TicketKey(ticket.getTicketDiscount(), ticket.getTicketCategory(), ticket.getTicketPrice());
            if (ticketCountMap.containsKey(key)) {
                ticketCountMap.put(key, ticketCountMap.get(key) + 1);
            } else {
                ticketCountMap.put(key, 1);
            }
        }
    }

    /**
     * Retrieves the ticket count map.
     *
     * @return The ticket count map.
     */
    public HashMap<TicketKey, Integer> getTicketCountMap() {
        return this.ticketCountMap;
    }

    /**
     * Sets up tickets by initializing the ticket count map and aggregated ticket list.
     */
    public void onSetUpTickets() {
        setTicketCountMap();
        setAggregatedTickets();
    }

    /**
     * Displays the purchased tickets in the associated view.
     */
    public void onShowPurchasedTickets() {
        view.showPurchasedTickets(attachedPurchase);
    }
}

