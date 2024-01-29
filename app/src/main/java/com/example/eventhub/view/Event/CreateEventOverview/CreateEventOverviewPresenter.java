package com.example.eventhub.view.Event.CreateEventOverview;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.OrganizerDAO;
import com.example.eventhub.dao.TicketCategoryDAO;
import com.example.eventhub.dao.TicketDiscountDAO;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.EventType;
import com.example.eventhub.domain.Organizer;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

public class CreateEventOverviewPresenter {
    private CreateEventOverviewView view;

    private EventDAO eventDAO;
    private TicketCategoryDAO ticketCategoryDAO;
    private TicketDiscountDAO ticketDiscountDAO;
    private OrganizerDAO organizerDAO;

    private Organizer organizer;
    private Event event;
    private ArrayList<TicketCategory> ticketCategoriesList = new ArrayList<>();
    private ArrayList<TicketDiscount> ticketDiscountsList = new ArrayList<>();
    private boolean editMode = false;

    @SuppressLint("NewApi")
    public CreateEventOverviewPresenter(CreateEventOverviewView view, EventDAO eventDAO, TicketCategoryDAO ticketCategoryDAO, TicketDiscountDAO ticketDiscountDAO, OrganizerDAO organizerDAO) {
        this.view = view;
        this.eventDAO = eventDAO;
        this.ticketCategoryDAO = ticketCategoryDAO;
        this.ticketDiscountDAO = ticketDiscountDAO;
        this.organizerDAO = organizerDAO;

        Integer organizerId = view.getAttachedOrganizerId();
        organizer = organizerId == null ? null : organizerDAO.find(organizerId);

        event = view.getAttachedEvent();

        ticketCategoriesList = view.getAttachedTicketCategories();
        ticketDiscountsList = view.getAttachedTicketDiscounts();

        if(event.getEventId() != null) { editMode = true; }

        view.setEventName(event.getName());
        view.setEventAddress(event.getLocation().toString());
        view.setEventDate(event.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " " + event.getDate().toLocalTime().toString());
        view.setEventGenre(event.getGenre().toString());
        view.setEventType(event.getType().toString());
    }

    public void onSaveEvent() {
        if (ticketCategoriesList.isEmpty()) {
            view.showErrorMessage("Error", "Please add at least one ticket category.");
            return;
        }

        if (ticketDiscountsList.isEmpty()) {
            view.showErrorMessage("Error", "Please add at least one ticket discount.");
            return;
        }

        // init event
        if (editMode) {
            event.setTicketCategories(new ArrayList<>());
            for (TicketCategory ticketCategory : ticketCategoriesList) {
                ticketCategoryDAO.update(ticketCategory);
                event.addTicketCategory(ticketCategory);
            }

            event.setTicketDiscounts(new ArrayList<>());
            for (TicketDiscount ticketDiscount : ticketDiscountsList) {
                ticketDiscountDAO.update(ticketDiscount);
                event.addTicketDiscount(ticketDiscount);
            }

            eventDAO.update(event, new HashSet<>(ticketCategoriesList), new HashSet<>(ticketDiscountsList));

            view.showEventUpdatedMessage();
        } else {
            Event newEvent = new Event(eventDAO.nextId(), event.getName(), event.getLocation(), event.getDate(), event.getGenre(), event.getType());

            // init ticket categories
            for (TicketCategory ticketCategory : ticketCategoriesList) {
                TicketCategory ticketCat = new TicketCategory(ticketCategoryDAO.nextId(), ticketCategory.getName(), ticketCategory.getPrice(), ticketCategory.getDescription(), ticketCategory.getQuantity());
                ticketCategoryDAO.save(ticketCat);
                newEvent.addTicketCategory(ticketCat);
                newEvent.getTicketCategoryCountMap().put(ticketCat, ticketCat.getQuantity());
            }

            // init ticket discounts
            for (TicketDiscount ticketDiscount : ticketDiscountsList) {
                TicketDiscount ticketDisc = new TicketDiscount(ticketDiscountDAO.nextId(), ticketDiscount.getType(), ticketDiscount.getPercentage() * 100);
                ticketDiscountDAO.save(ticketDisc);
                newEvent.addTicketDiscount(ticketDisc);
            }

            eventDAO.save(newEvent);
            organizer.addEvent(newEvent);
            view.showEventCreatedMessage();
        }
    }

    public void onDisplayTicketCategories() {
        if (ticketCategoriesList.isEmpty()) {
            view.showEmptyTicketCategories();
        } else {
            view.showTicketCategories();
        }
    }

    public void onDisplayTicketDiscounts() {
        if (ticketDiscountsList.isEmpty()) {
            view.showEmptyTicketDiscounts();
        } else {
            view.showTicketDiscounts();
        }
    }

    public void onMoveToSelectCategories() {
        view.moveToSelectCategories();
    }

    public void onMoveToSelectDiscounts() {
        view.moveToSelectDiscounts();
    }

    public void onRemoveTicketCategory(TicketCategory ticketCategory) {
        if(event.getType() == EventType.FREE) {
            view.showErrorMessage("Error", "You cannot edit the ticket category of a free event.");
            return;
        }

        if(editMode && ticketCategoriesList.size() == 1) {
            view.showErrorMessage("Error", "You cannot remove the last ticket category when editing.");
            return;
        }

        ticketCategoriesList.remove(ticketCategory);
        onDisplayTicketCategories();
    }

    public void onRemoveTicketDiscount(TicketDiscount ticketDiscount) {
        if(event.getType() == EventType.FREE) {
            view.showErrorMessage("Error", "You cannot edit the ticket discount of a free event.");
            return;
        }

        if(editMode && ticketDiscountsList.size() == 1) {
            view.showErrorMessage("Error", "You cannot remove the last ticket discount when editing.");
            return;
        }

        ticketDiscountsList.remove(ticketDiscount);
        onDisplayTicketDiscounts();
    }

    public ArrayList<TicketCategory> getTicketCategoryList() {
        return ticketCategoriesList;
    }

    public ArrayList<TicketDiscount> getTicketDiscountList() {
        return ticketDiscountsList;
    }
}
