package com.example.eventhub.dao;

import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.EventType;
import com.example.eventhub.domain.Genre;
import com.example.eventhub.domain.Interest;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Interface representing Data Access Object (DAO) for Event entities.
 */
public interface EventDAO {

    /**
     * Deletes the given event from the data store.
     *
     * @param event The event to be deleted.
     */
    void delete(Event event);

    /**
     * Retrieves a list of all events in the data store.
     *
     * @return A list of all events.
     */
    ArrayList<Event> findAll();

    /**
     * Saves the given event to the data store.
     *
     * @param event The event to be saved.
     */
    void save(Event event);

    /**
     * Finds an event by its ID in the data store.
     *
     * @param id The ID of the event to be found.
     * @return The found event or null if not found.
     */
    Event find(int id);

    /**
     * Generates the next ID to be used for a new event.
     *
     * @return The next available ID.
     */
    int nextId();

    /**
     * Updates the given event in the data store.
     *
     * @param event             The event to be updated.
     * @param ticketCategories  The set of ticket categories associated with the event.
     * @param ticketDiscounts   The set of ticket discounts associated with the event.
     */
    void update(Event event, HashSet<TicketCategory> ticketCategories, HashSet<TicketDiscount> ticketDiscounts);

    /**
     * Retrieves a list of all active events in the data store.
     *
     * @return A list of all active events.
     */
    ArrayList<Event> findAllActive();

    /**
     * Finds an already existing event in the data store.
     *
     * @param event The event to be checked.
     * @return The found event or null if not found.
     */
    Event findAlreadyExisting(Event event);

    /**
     * Finds an event by its name in the data store.
     *
     * @param name The name of the event to be found.
     * @return The found event or null if not found.
     */
    Event findByName(String name);

    /**
     * Finds events by the specified text in their details.
     *
     * @param text The text to search for.
     * @return A set of events matching the specified text.
     */
    Set<Event> findByText(String text);

    /**
     * Finds events by the specified genres.
     *
     * @param genres The list of genres to search for.
     * @return A list of events matching the specified genres.
     */
    ArrayList<Event> findByGenre(ArrayList<Genre> genres);

    /**
     * Finds events by the specified genres (first match).
     *
     * @param genres The list of genres to search for.
     * @return A list of events matching the first specified genre.
     */
    ArrayList<Event> findByGenreFirst(ArrayList<Genre> genres);

    /**
     * Finds events by the specified event types.
     *
     * @param types The list of event types to search for.
     * @return A set of events matching the specified event types.
     */
    Set<Event> findByType(ArrayList<EventType> types);

    /**
     * Finds events within the specified date range.
     *
     * @param from The start date of the range.
     * @param to   The end date of the range.
     * @return A set of events within the specified date range.
     */
    Set<Event> findByDateRange(LocalDate from, LocalDate to);

    /**
     * Finds events based on the specified customer interests.
     *
     * @param interests The set of interests to search for.
     * @return A set of events matching the specified customer interests.
     */
    Set<Event> findByCustomerInterests(Set<Interest> interests);

    /**
     * Sorts events by capacity in ascending order.
     *
     * @param events The list of events to be sorted.
     * @return A sorted list of events by capacity.
     */
    ArrayList<Event> sortEventsByCapacity(ArrayList<Event> events);

    /**
     * Sorts events by the number of tickets sold (popularity).
     *
     * @param events The list of events to be sorted.
     * @return A sorted list of events by the number of tickets sold.
     */
    ArrayList<Event> sortEventsByTicketsSold(ArrayList<Event> events);

    /**
     * Sorts events by rating in descending order.
     *
     * @param events The list of events to be sorted.
     * @return A sorted list of events by rating.
     */
    ArrayList<Event> sortEventsByRating(ArrayList<Event> events);

    /**
     * Sorts events by date in ascending order (closer date first).
     *
     * @param events The list of events to be sorted.
     * @return A sorted list of events by date.
     */
    ArrayList<Event> sortEventsByCloserDate(ArrayList<Event> events);

    /**
     * Sorts events by date in descending order (further date first).
     *
     * @param events The list of events to be sorted.
     * @return A sorted list of events by date.
     */
    ArrayList<Event> sortEventsByFurtherDate(ArrayList<Event> events);
}
