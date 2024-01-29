package com.example.eventhub.view.Event.EventDetails;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.OrganizerDAO;
import com.example.eventhub.dao.PurchaseDAO;
import com.example.eventhub.dao.TicketDAO;
import com.example.eventhub.domain.Customer;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.EventType;
import com.example.eventhub.domain.Organizer;
import com.example.eventhub.domain.Purchase;
import com.example.eventhub.domain.Review;
import com.example.eventhub.domain.Ticket;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventDetailsPresenter {
    private EventDetailsView view;
    private CustomerDAO customerDAO;
    private OrganizerDAO organizerDAO;
    private EventDAO eventDAO;
    private TicketDAO ticketDAO;
    private PurchaseDAO purchaseDAO;
    private Event attachedEvent;
    private Customer attachedCustomer;
    private Organizer attachedOrganizer;
    private List<Review> reviews;

    public EventDetailsPresenter(CustomerDAO customerDAO, OrganizerDAO organizerDAO, EventDAO eventDAO, PurchaseDAO purchaseDAO, TicketDAO ticketDAO) {
        this.customerDAO = customerDAO;
        this.organizerDAO = organizerDAO;
        this.eventDAO = eventDAO;
        this.purchaseDAO = purchaseDAO;
        this.ticketDAO = ticketDAO;
    }

    public void setView(EventDetailsView view) {
        this.view = view;
    }

    public EventDetailsView getView() {
        return view;
    }

    public void setEvent(Integer eventId) {
        this.attachedEvent = eventDAO.find(eventId);
    }

    public Event getEvent() {
        return attachedEvent;
    }

    public void setCustomer(Integer customerId) {
        attachedCustomer = customerDAO.find(customerId);
    }

    public Customer getCustomer() {
        return attachedCustomer;
    }

    public void setOrganizer(Integer organizerId) {
        attachedOrganizer = organizerDAO.find(organizerId);
    }

    public Organizer getOrganizer() {
        return attachedOrganizer;
    }

    public void setReviewList() {
        this.reviews = attachedEvent.getReviews();
    }

    public List<Review> getReviewList() {
        return reviews;
    }

    @SuppressLint("NewApi")
    public void setEventDetails() {
        view.setEventName(attachedEvent.getName());
        view.setEventAddress(attachedEvent.getLocation().toString());
        view.setEventGenre(attachedEvent.getGenre().toString());
        view.setEventType(attachedEvent.getType().toString());
        view.setEventTime(attachedEvent.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " " + attachedEvent.getDate().toLocalTime().toString());
        if (attachedEvent.getAverageRating() == 0.0)
            view.setAverageRating("N/A");
        else {
            view.setAverageRating(String.valueOf(attachedEvent.getAverageRating()).split("\\.")[0] + "/10");
        }
        view.setEventCapacity(String.valueOf(attachedEvent.getEventCapacity()));
        view.setAvailableTickets(attachedEvent.getAvailableTickets() > 0 ? String.valueOf(attachedEvent.getAvailableTickets()) : "SOLD OUT");
    }

    public void onDisplayBookTicketButton(Integer organizerId) {
        if (organizerId == 0 && attachedEvent.getAvailableTickets() > 0) {
            view.showBookTicketButton();
        } else {
            view.hideBookTicketButton();
        }
    }

    public void onDisplayReviews() {
        if (attachedEvent.getReviews().isEmpty()) {
            view.showEmptyReviews();
        } else {
            view.showReviews();
        }
    }

    public void onBookTicket() {
        if (getCustomer() == null) {
            view.onLoginOrRegister();
        } else {
            if (attachedEvent.getType() == EventType.FREE)
                view.bookFreeTicket();
            else
                view.bookTicket(attachedEvent.getEventId());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onMakeFreePurchase(String numOfFreeTickets){
        ArrayList<Ticket> freeTickets = new ArrayList<Ticket>();
        for (int i = 0; i < Integer.parseInt(numOfFreeTickets); i++){
            attachedEvent.getTicketDiscounts().get(0).setPercentage(100.0);
            Ticket ticket = new Ticket(ticketDAO.nextId(), attachedEvent,
                    attachedEvent.getTicketCategories().get(0),
                    attachedEvent.getTicketDiscounts().get(0));

            freeTickets.add(ticket);
            ticketDAO.save(ticket);
        }

        Purchase freePurchase = new Purchase(purchaseDAO.nextId(), freeTickets);
        attachedCustomer.addPurchase(freePurchase);

        purchaseDAO.save(freePurchase);

        view.showSuccessfulFreePurchase();
    }
}
