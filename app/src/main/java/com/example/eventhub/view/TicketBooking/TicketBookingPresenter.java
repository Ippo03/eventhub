package com.example.eventhub.view.TicketBooking;

import android.annotation.SuppressLint;

import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.PurchaseDAO;
import com.example.eventhub.dao.TicketCategoryDAO;
import com.example.eventhub.dao.TicketDAO;
import com.example.eventhub.dao.TicketDiscountDAO;
import com.example.eventhub.domain.Customer;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.Purchase;
import com.example.eventhub.domain.Ticket;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;
import com.example.eventhub.domain.TicketKey;

import java.util.ArrayList;
import java.util.HashMap;

public class TicketBookingPresenter {
    private TicketBookingView view;
    private CustomerDAO customerDAO;
    private EventDAO eventDAO;
    private TicketCategoryDAO ticketCategoryDAO;
    private TicketDiscountDAO ticketDiscountDAO;
    private PurchaseDAO purchaseDAO;
    private TicketDAO ticketDAO;
    private Event attachedEvent;
    private Customer attachedCustomer;
    // This list will store all the tickets that are added
    public ArrayList<Ticket> ticketList = new ArrayList<>();
    // This map will store the mapping from the ticket key to the count
    public HashMap<TicketKey, Integer> ticketCountMap = new HashMap<>();
    // This list will store all the unique tickets that are added
    public ArrayList<Ticket> aggregatedTicketList = new ArrayList<>();
    // These maps will store the mapping from the spinner string to the ID
    public HashMap<String, Integer> ticketCategoryMap = new HashMap<>();
    public HashMap<String, Integer> ticketDiscountMap = new HashMap<>();

    public TicketBookingPresenter(CustomerDAO customerDAO, EventDAO eventDAO, TicketCategoryDAO ticketCategoryDAO, TicketDiscountDAO ticketDiscountDAO, TicketDAO ticketDAO, PurchaseDAO purchaseDAO) {
        this.customerDAO = customerDAO;
        this.eventDAO = eventDAO;
        this.ticketCategoryDAO = ticketCategoryDAO;
        this.ticketDiscountDAO = ticketDiscountDAO;
        this.purchaseDAO = purchaseDAO;
        this.ticketDAO = ticketDAO;
    }

    public void setView(TicketBookingView view) {
        this.view = view;
    }

    public TicketBookingView getView() {
        return view;
    }

    public void setEvent(Integer eventId) {
        this.attachedEvent = eventDAO.find(eventId);
    }

    public Event getEvent() {
        return attachedEvent;
    }

    public void setCustomer(Integer customerId) { this.attachedCustomer = customerDAO.find(customerId);}

    public Customer getCustomer() { return attachedCustomer; }

    public void setName() {
        view.setEventName(attachedEvent.getName());
    }

    public String[] getTicketCategories() {
//        Log.d("CATEGORIES FROM DAO", "getTicketCategories: " + attachedEvent.getTicketCategories().size());
        ArrayList<TicketCategory> ticketCategories = attachedEvent.getTicketCategories();
        String[] ticketCategoriesString = new String[ticketCategories.size()];
        int i = 0;
        for (TicketCategory ticketCategory : ticketCategories) {
            String displayString = ticketCategory.getName() + " - " + ticketCategory.getPrice().toString();
            ticketCategoriesString[i] = displayString;
            // Store the mapping of the display string to the ID
            ticketCategoryMap.put(displayString, ticketCategory.getTicketCategoryId());
            i++;
        }
        return ticketCategoriesString;
    }

    public String[] getTicketDiscounts() {
        ArrayList<TicketDiscount> ticketDiscounts = attachedEvent.getTicketDiscounts();
        String[] ticketDiscountsString = new String[ticketDiscounts.size()];
        int i = 0;
        for (TicketDiscount ticketDiscount : ticketDiscounts) {
            String displayString = ticketDiscount.getType() + " - " + ticketDiscount.getPercentage() * 100 + "%";
            ticketDiscountsString[i] = displayString;
            // Store the mapping of the display string to the ID
            ticketDiscountMap.put(displayString, ticketDiscount.getTicketDiscountId());
            i++;
        }
        return ticketDiscountsString;
    }

    public TicketCategory getTicketCategoryById(Integer categoryId) {
        return ticketCategoryDAO.find(categoryId);
    }

    public TicketDiscount getTicketDiscountById(Integer discountId) {
        return ticketDiscountDAO.find(discountId);
    }

    public Ticket onUpdateTicketPrice() {
        Ticket ticket = new Ticket();
        ticket.setEvent(attachedEvent);
        ticket.setTicketCategory(view.getSelectedCategory());
        ticket.setTicketDiscount(view.getSelectedDiscount());
        view.setTicketPrice(ticket.getTicketPrice().toString());

        return ticket;
    }

    public void onUpdateTotalCost() {
        Purchase purchase = new Purchase();
        purchase.setTickets(ticketList);
        purchase.calculateTotalCost();
        view.setTotalCost(purchase.getCost().toString());
    }

    public void onAddTicket(Ticket ticket) {
        ticketList.add(ticket);
        onRefreshTicketCount(ticket, true);
        onUpdateTotalCost();
        onShowTickets();
    }

    public void onRemoveTicket(Ticket ticket) {
        ticketList.remove(ticket);
        onRefreshTicketCount(ticket, false);
        onUpdateTotalCost();
        onShowTickets();
    }

    public void onStartListener() {
        view.onChangeCategory();
        view.onChangeDiscount();
    }

    public ArrayList<Ticket> getTicketList() {
        return ticketList;
    }

    public HashMap<TicketKey, Integer> getTicketCountMap() {
        return ticketCountMap;
    }

    public ArrayList<Ticket> getAggregatedTicketList() {
        return aggregatedTicketList;
    }

    public void onShowTickets() {
        view.showTickets();
    }

    @SuppressLint("NewApi")
    public void onCompletePurchase() {
        if (getTicketList().isEmpty()){
            view.showErrorMessage("Purchase Not Completed", "Your cart is empty !\nPlease add some tickets to make a purchase.");
            return;
        }
        else {
            TicketCategory notAvailableTicketCategory = attachedEvent.validateTicketsOfCategories(getTicketList());
            if(notAvailableTicketCategory != null) {
                view.showErrorMessage("Purchase Not Completed", "There are not enough tickets available for the category " + notAvailableTicketCategory.getName().toString() + ".\n" +
                        "This category has " + attachedEvent.getTicketCategoryCountMap().get(notAvailableTicketCategory) + " tickets left.");
                return;
            }

            // update quantities of ticket categories
            for (Ticket ticket : getTicketList()) {
                attachedEvent.getTicketCategoryCountMap().put(ticket.getTicketCategory(), attachedEvent.getTicketCategoryCountMap().get(ticket.getTicketCategory()) - 1);
            }

            for (Ticket ticket : getTicketList()) {
                Ticket newTicket = new Ticket(ticketDAO.nextId(), attachedEvent, ticket.getTicketCategory(), ticket.getTicketDiscount());
                ticketDAO.save(newTicket);
            }

            Purchase purchase = new Purchase(purchaseDAO.nextId(), getTicketList());

            purchaseDAO.save(purchase);
            attachedCustomer.addPurchase(purchase);
            view.showPurchaseCompleteMessage();
        }
    }

    public void onRefreshTicketCount(Ticket newTicket, boolean isAdd) {
    TicketKey key = new TicketKey(newTicket.getTicketDiscount(), newTicket.getTicketCategory(), newTicket.getTicketPrice());

    if (isAdd) {
        if (!aggregatedTicketList.contains(newTicket)) {
            ticketCountMap.put(key, 1);
            aggregatedTicketList.add(newTicket);
        } else {
            int count = ticketCountMap.get(key);
            ticketCountMap.put(key, count + 1);
        }
    } else {
        int count = ticketCountMap.get(key);
        if (count > 1) {
            ticketCountMap.put(key, count - 1);
        } else { // If last occurrence, count == 1
            ticketCountMap.remove(key);
            aggregatedTicketList.remove(newTicket);
        }
    }
}
}
