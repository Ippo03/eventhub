package com.example.eventhub.view.Event.CreateEditEvent;

import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.TicketCategoryDAO;
import com.example.eventhub.dao.TicketDiscountDAO;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.EventType;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.helper.EventTestHelper;
import com.example.eventhub.memorydao.EventDAOMemory;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.memorydao.TicketCategoryDAOMemory;
import com.example.eventhub.memorydao.TicketDiscountDAOMemory;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Unit tests for the CreateEditEventPresenter class, focusing on saving events with various conditions.
 */
public class CreateEditEventPresenterTest {
    private CreateEditEventViewStub view;
    private CreateEditEventPresenter presenter;

    private EventDAO eventDAO;
    private TicketCategoryDAO ticketCategoryDAO;
    private TicketDiscountDAO ticketDiscountDAO;

    @Before
    public void setUp() {
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        view = new CreateEditEventViewStub();

        eventDAO = new EventDAOMemory();
        ticketCategoryDAO = new TicketCategoryDAOMemory();
        ticketDiscountDAO = new TicketDiscountDAOMemory();

        presenter = new CreateEditEventPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO);
    }

    /**
     * Test case for saving an event with empty fields.
     * Validates that the presenter displays an error message when attempting to save an event with empty fields.
     */
    @Test
    public void onSaveEventWithEmptyField() {
        view.setEventAddress("Ermou 17, Athens 12345");
        view.setEventType("Open");
        view.setEventDate("01-01-2025");
        view.setEventTime("12:00");
        view.setEventGenre(1);

        presenter.onSaveEvent();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill all the fields.", view.getErrorMessage());
    }

    /**
     * Test case for saving an event with an invalid address.
     * Validates that the presenter displays an error message when attempting to save an event with an invalid address.
     */
    @Test
    public void onSaveEventWithInvalidAddress() {
        view.setEventName("Home Cinema");
        view.setEventAddress("Ermou 17, Athens");
        view.setEventType("Open");
        view.setEventDate("01-01-2025");
        view.setEventTime("12:00");
        view.setEventGenre(1);

        presenter.onSaveEvent();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please select an address in the format Street StreetNo, City Zip.", view.getErrorMessage());
    }

    /**
     * Test case for saving an event with an invalid date (wrong format).
     * Validates that the presenter displays an error message when attempting to save an event with an invalid date.
     */
    @Test
    public void onSaveEventWithInvalidDate1() {
        view.setEventName("Home Cinema");
        view.setEventAddress("Ermou 17, Athens 12345");
        view.setEventType("Open");
        view.setEventDate("01/01/2020");
        view.setEventTime("12:00");
        view.setEventGenre(1);

        presenter.onSaveEvent();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please select a date in the format dd-MM-yyyy.", view.getErrorMessage());
    }

    /**
     * Test case for saving an event with an invalid date (before today).
     * Validates that the presenter displays an error message when attempting to save an event with an invalid date.
     */
    @Test
    public void onSaveEventWithInvalidDate2() {
        view.setEventName("Home Cinema");
        view.setEventAddress("Ermou 17, Athens 12345");
        view.setEventType("Open");
        view.setEventDate("01-01-2020");
        view.setEventTime("12:00");
        view.setEventGenre(1);

        presenter.onSaveEvent();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please select a valid date.", view.getErrorMessage());
    }

    /**
     * Test case for saving an event with an invalid time (wrong format).
     * Validates that the presenter displays an error message when attempting to save an event with an invalid time.
     */
    @Test
    public void onSaveEventWithInvalidTime() {
        view.setEventName("Home Cinema");
        view.setEventAddress("Ermou 17, Athens 12345");
        view.setEventType("Open");
        view.setEventDate("01-01-2025");
        view.setEventTime("12:00:00");
        view.setEventGenre(1);

        presenter.onSaveEvent();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please select a time in the format HH:mm.", view.getErrorMessage());
    }

    /**
     * Test case for saving an event that already exists.
     * Validates that the presenter displays an error message when attempting to save an event with the same name,
     * address, and date as an existing event.
     */
    @Test
    public void onSaveEventThatAlreadyExists() {
        view.setEventName("Technology Symposium");
        view.setEventAddress("Plaka 8, Athens 11131");
        view.setEventType("Closed");
        view.setEventDate("20-06-2024");
        view.setEventTime("11:30");
        view.setEventGenre(4);

        presenter.onSaveEvent();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("An event with the same name, address and date already exists.", view.getErrorMessage());
    }

    /**
     * Test case for saving a free event.
     * Validates that the presenter initiates the process of selecting the quantity for the free ticket category
     * after successfully saving the event.
     */
    @Test
    public void onSaveFreeEvent() {
        view.setEventName("Home Cinema");
        view.setEventAddress("Ermou 17, Athens 12345");
        view.setEventType("Free");
        view.setEventDate("01-01-2025");
        view.setEventTime("12:00");
        view.setEventGenre(1);

        presenter.onSaveEvent();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(1, view.getOnSelectFreeTicketCategoryQuantityCount());
    }

    /**
     * Test case for saving an event.
     * Validates that the presenter initiates the process of selecting the ticket categories after successfully
     * saving the event.
     */
    @Test
    public void onSaveEvent() {
        view.setEventName("Home Cinema");
        view.setEventAddress("Ermou 17, Athens 12345");
        view.setEventType("Open");
        view.setEventDate("01-01-2025");
        view.setEventTime("12:00");
        view.setEventGenre(1);

        presenter.onSaveEvent();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(1, view.getOnMoveToTicketCategorySelectionCount());
    }

    /**
     * Test case for attempting to save an event with an invalid edit: changing from free to paid.
     * Validates that the presenter displays an error message when attempting to edit a free event and change its type to paid.
     */
    @Test
    public void onSaveEventEditFromFreeToPaid() {
        view.setAttachedEventId(7);
        presenter = new CreateEditEventPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO);

        view.setEventName("Home Cinema");
        view.setEventAddress("Ermou 17, Athens 12345");
        view.setEventType("Open");
        view.setEventDate("01-01-2025");
        view.setEventTime("12:00");
        view.setEventGenre(1);

        presenter.onSaveEvent();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("You cannot edit the type of a free event.", view.getErrorMessage());
    }

    /**
     * Test case for attempting to save an event with an invalid edit: changing from paid to free.
     * Validates that the presenter displays an error message when attempting to edit a paid event and change its type to free.
     */
    @Test
    public void onSaveEventEditFromPaidToFree() {
        view.setAttachedEventId(1);
        presenter = new CreateEditEventPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO);

        view.setEventName("Home Cinema");
        view.setEventAddress("Ermou 17, Athens 12345");
        view.setEventType("Free");
        view.setEventDate("01-01-2025");
        view.setEventTime("12:00");
        view.setEventGenre(1);

        presenter.onSaveEvent();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("You cannot edit the type of a paid event to free.", view.getErrorMessage());
    }

    /**
     * Test case for attempting to save an event with an edit that already exists.
     * Validates that the presenter displays an error message when attempting to edit an event with the same name, address, and date as an existing event.
     */
    @Test
    public void onSaveEventEditThatAlreadyExists() {
        view.setAttachedEventId(1);
        presenter = new CreateEditEventPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO);

        view.setEventName("Technology Symposium");
        view.setEventAddress("Plaka 8, Athens 11131");
        view.setEventType("Closed");
        view.setEventDate("20-06-2024");
        view.setEventTime("11:30");
        view.setEventGenre(4);

        presenter.onSaveEvent();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("An event with the same name, address and date already exists.", view.getErrorMessage());
    }

    /**
     * Test case for successfully saving an edited event.
     * Validates that the presenter initiates the process of moving to the ticket category selection after successfully saving the edited event.
     */
    @Test
    public void onSaveEventEdit() {
        view.setAttachedEventId(1);
        presenter = new CreateEditEventPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO);

        view.setEventName("Home Cinema");
        view.setEventAddress("Ermou 17, Athens 12345");
        view.setEventType("Open");
        view.setEventDate("01-01-2025");
        view.setEventTime("12:00");
        view.setEventGenre(1);

        presenter.onSaveEvent();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(1, view.getOnMoveToTicketCategorySelectionCount());
    }

    /**
     * Test case for editing a free event.
     * Validates that the presenter initiates the process of selecting the quantity for the free ticket category.
     */
    @Test
    public void onSaveFreeEventEdit() {
        view.setAttachedEventId(20);
        presenter = new CreateEditEventPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO);

        presenter.onSaveEvent();

        Assertions.assertEquals(1, view.getOnSelectFreeTicketCategoryQuantityCount());
    }

    /**
     * Test case for creating a free event with an invalid quantity.
     * Validates that the presenter displays an error message when attempting to create a free event with an invalid quantity.
     */
    @Test
    public void onCreateFreeEventWithInvalidQuantity() {
        Event event = EventTestHelper.initEvent();
        event.setType(EventType.FREE);

        presenter.onCreateFreeEvent("", event);

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please enter a valid quantity.", view.getErrorMessage());
    }


    /**
     * Test case for creating a free event with less quantity.
     * Validates that the presenter displays an error message when attempting to create a free event with an invalid quantity.
     */
    @Test
    public void onCreateFreeEventWithLessQuantity() {
        Event event = EventTestHelper.initEvent();
        event.setType(EventType.FREE);

        presenter.onCreateFreeEvent("50", event);

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("You can only increase the quantity of a free event.", view.getErrorMessage());
    }

    /**
     * Test case for creating a free event.
     * Validates that the presenter initiates the process of moving to the create event overview after successfully creating a free event.
     */
    @Test
    public void onCreateFreeEvent() {
        Event event = EventTestHelper.initEvent();
        event.setType(EventType.FREE);

        for (TicketCategory ticketCategory : event.getTicketCategories()) {
            event.getTicketCategoryCountMap().put(ticketCategory, ticketCategory.getQuantity());
        }

        presenter.onCreateFreeEvent("300", event);

        Assertions.assertEquals(1, view.getOnMoveToCreateEventOverviewCount());
    }
}
