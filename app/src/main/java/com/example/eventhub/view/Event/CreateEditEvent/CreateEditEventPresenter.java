package com.example.eventhub.view.Event.CreateEditEvent;

import static com.example.eventhub.util.Util.checkAddressFormat;
import static com.example.eventhub.util.Util.checkDateFormat;
import static com.example.eventhub.util.Util.checkTimeFormat;

import android.annotation.SuppressLint;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.TicketCategoryDAO;
import com.example.eventhub.dao.TicketDiscountDAO;
import com.example.eventhub.domain.CategoryName;
import com.example.eventhub.domain.DiscountType;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.EventType;
import com.example.eventhub.domain.Genre;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;
import com.example.eventhub.util.Money;
import com.example.eventhub.util.Util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;

public class CreateEditEventPresenter {
    private CreateEditEventView view;
    private EventDAO eventDAO;
    private TicketCategoryDAO ticketCategoryDAO;
    private TicketDiscountDAO ticketDiscountDAO;
    private Event event;
    private Integer organizerId;

    @SuppressLint("NewApi")
    public CreateEditEventPresenter(CreateEditEventView view, EventDAO eventDAO, TicketCategoryDAO ticketCategoryDAO, TicketDiscountDAO ticketDiscountDAO){
        this.view = view;
        this.eventDAO = eventDAO;
        this.ticketCategoryDAO = ticketCategoryDAO;
        this.ticketDiscountDAO = ticketDiscountDAO;

        organizerId = view.getAttachedOrganizerId();
        Integer eventId = view.getAttachedEventId();
        event = eventId == null ? null : eventDAO.find(eventId);

        if(event != null) { // edit mode
            view.setEventName(event.getName());
            view.setEventAddress(event.getLocation().toString());
            view.setEventType(event.getType().toString());
            view.setEventDate(event.getDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            view.setEventTime(event.getDate().toLocalTime().toString());
            view.setEventGenre(Util.mapGenreToInt(event.getGenre()));
        }
    }

    @SuppressLint("NewApi")
    public void onSaveEvent() {
        HashMap<String, String> eventInfo = view.getEventInfo();

        for (HashMap.Entry<String, String> entry : eventInfo.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isEmpty()) {
                view.showErrorMessage("Error", "Please fill all the fields.");
                return;
            }
        }

        String eventName = eventInfo.get("name");
        String eventAddress = eventInfo.get("address");
        EventType eventType = Util.mapStringToEventType(eventInfo.get("type"));
        String eventDate = eventInfo.get("date");
        String eventTime = eventInfo.get("time");
        String eventGenre = eventInfo.get("genre");

        if (!checkAddressFormat(eventAddress)) {
            view.showErrorMessage("Error", "Please select an address in the format Street StreetNo, City Zip.");
        } else if (!checkDateFormat(eventDate)) {
            view.showErrorMessage("Error", "Please select a date in the format dd-MM-yyyy.");
        } else if (!Util.isDateValid(eventDate)) {
            view.showErrorMessage("Error", "Please select a valid date.");
        } else if (!checkTimeFormat(eventTime)) {
            view.showErrorMessage("Error", "Please select a time in the format HH:mm.");
        } else {
            // preprocessing
            LocalDateTime eventDateTime = Util.stringToDateTime(eventDate, eventTime);
            Address eventAddressObj = Util.stringToAddress(eventAddress);
            Genre eventGenreObj = Util.mapStringToGenre(eventGenre);

            if (event == null) {
                Event newEvent = new Event();
                newEvent.setName(eventName);
                newEvent.setLocation(eventAddressObj);
                newEvent.setType(eventType);
                newEvent.setDate(eventDateTime);
                newEvent.setGenre((eventGenreObj));

                if(eventDAO.findAlreadyExisting(newEvent) != null) {
                    view.showErrorMessage("Error", "An event with the same name, address and date already exists.");
                    return;
                }

                if(newEvent.getType() == EventType.FREE) {
                    view.selectFreeTicketCategoryQuantity(newEvent);
                    return;
                }

                view.moveToTicketCategorySelection(newEvent);
            } else {
                if (event.getType() == EventType.FREE && eventType != EventType.FREE) {
                    view.showErrorMessage("Error", "You cannot edit the type of a free event.");
                    return;
                }

                if(event.getType() != EventType.FREE && eventType == EventType.FREE) {
                    view.showErrorMessage("Error", "You cannot edit the type of a paid event to free.");
                    return;
                }

                event.setName(eventName);
                event.setLocation(eventAddressObj);
                event.setType(eventType);
                event.setDate(eventDateTime);
                event.setGenre(eventGenreObj);

                if(eventDAO.findAlreadyExisting(event) != null) {
                    view.showErrorMessage("Error", "An event with the same name, address and date already exists.");
                    return;
                }

                if(event.getType() == EventType.FREE) {
                    view.selectFreeTicketCategoryQuantity(event);
                    return;
                }

                view.moveToTicketCategorySelection(event);
            }
        }
    }

    public void onCreateFreeEvent(String quantity, Event event) {
        if (quantity.isEmpty() || Integer.parseInt(quantity) <= 0) {
            view.showErrorMessage("Error", "Please enter a valid quantity.");
            return;
        }

        if (event.getEventCapacity() > Integer.parseInt(quantity)) {
            view.showErrorMessage("Error", "You can only increase the quantity of a free event.");
            return;
        }

        Integer boughtTickets = 0;
        if (event.getTicketCategoryCountMap() != null) {
            boughtTickets = event.getEventCapacity() - event.getTicketCategoryCountMap().get(event.getTicketCategories().get(0));
        }

        event.setTicketCategories(new ArrayList<>());
        TicketCategory ticketCategory = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.GENERAL,
                new Money(new BigDecimal(0), Currency.getInstance("EUR")), "General admission", Integer.parseInt(quantity));
        ticketCategoryDAO.save(ticketCategory);
        event.addTicketCategory(ticketCategory);

        event.setTicketCategoryCountMap(new HashMap<>());
        event.getTicketCategoryCountMap().put(ticketCategory, boughtTickets != 0 ? ticketCategory.getQuantity() - boughtTickets : ticketCategory.getQuantity());

        event.setTicketDiscounts(new ArrayList<>());
        TicketDiscount ticketDiscount = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.GENERAL, 100.0);
        ticketDiscountDAO.save(ticketDiscount);
        event.addTicketDiscount(ticketDiscount);

        view.moveToCreateEventOverview(event);
    }
}
