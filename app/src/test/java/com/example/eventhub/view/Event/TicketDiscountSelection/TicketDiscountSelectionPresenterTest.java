package com.example.eventhub.view.Event.TicketDiscountSelection;

import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.TicketDiscountDAO;
import com.example.eventhub.domain.DiscountType;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.TicketDiscount;
import com.example.eventhub.memorydao.EventDAOMemory;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.memorydao.TicketDiscountDAOMemory;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Unit tests for the TicketDiscountSelectionPresenter class, covering scenarios related to adding, editing, and removing ticket discounts.
 */
public class TicketDiscountSelectionPresenterTest {
    private TicketDiscountSelectionViewStub view;
    private TicketDiscountSelectionPresenter presenter;

    private EventDAO eventDAO;
    private TicketDiscountDAO ticketDiscountDAO;

    private Event event;

    @Before
    public void setUp() {
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        view = new TicketDiscountSelectionViewStub();

        eventDAO = new EventDAOMemory();
        ticketDiscountDAO = new TicketDiscountDAOMemory();

        event = new Event();
        view.setEventName("Home Cinema");
        view.setEventAddress("Ermou 17, Athens 12345");
        view.setEventType("Open");
        view.setEventDate("01-01-2025");
        view.setEventTime("12:00");
        view.setEventGenre(1);

        view.setAttachedEvent(event);
        presenter = new TicketDiscountSelectionPresenter(view, ticketDiscountDAO, eventDAO);
    }

    /**
     * Test case for attempting to add a ticket discount with empty fields.
     * Validates that the presenter displays an error message when attempting to add a ticket discount with empty fields.
     */
    @Test
    public void onAddTicketDiscountWithEmptyField() {
        view.setTicketDiscountPercentage("10");

        presenter.onAddTicketDiscount();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill all the fields.", view.getErrorMessage());
    }

    /**
     * Test case for attempting to add a ticket discount with an invalid percentage.
     * Validates that the presenter displays an error message when attempting to add a ticket discount with an invalid percentage.
     */
    @Test
    public void onAddTicketDiscountWithInvalidPercentage() {
        view.setTicketDiscountType("Student");
        view.setTicketDiscountPercentage("101");

        presenter.onAddTicketDiscount();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please enter a valid ticket discount percentage.", view.getErrorMessage());
    }

    /**
     * Test case for attempting to add a ticket discount that already exists.
     * Validates that the presenter displays an error message when attempting to add a ticket discount that already exists.
     */
    @Test
    public void onAddTicketDiscountAlreadyExisting() {
        view.setTicketDiscountType("Student");
        view.setTicketDiscountPercentage("10");

        presenter.onAddTicketDiscount();

        view.setTicketDiscountType("Student");
        view.setTicketDiscountPercentage("10");

        presenter.onAddTicketDiscount();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Ticket discount already exists.", view.getErrorMessage());
    }

    /**
     * Test case for successfully adding a ticket discount.
     * Validates that the presenter adds a ticket discount without errors, updates the view, and provides the correct details.
     */
    @Test
    public void onAddTicketDiscount() {
        view.setTicketDiscountType("Student");
        view.setTicketDiscountPercentage("10");

        presenter.onAddTicketDiscount();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(1, view.getResetInputFieldsCount());
        Assertions.assertEquals(1, view.getShowTicketDiscountsCount());
    }

    /**
     * Test case for attempting to add a ticket discount that already exists when editing an existing event.
     * Validates that the presenter displays an error message when attempting to add an existing ticket discount during editing.
     */
    @Test
    public void onAddTicketDiscountEditAlreadyExisting() {
        view.setAttachedEvent(eventDAO.find(2));
        presenter = new TicketDiscountSelectionPresenter(view, ticketDiscountDAO, eventDAO);

        view.setTicketDiscountType("Senior");
        view.setTicketDiscountPercentage("30");

        presenter.onAddTicketDiscount();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Ticket discount already exists.", view.getErrorMessage());
    }

    /**
     * Test case for successfully adding a ticket discount during editing an existing event.
     * Validates that the presenter adds a ticket discount without errors, updates the view, and provides the correct details.
     */
    @Test
    public void onAddTicketCategoryEdit() {
        view.setAttachedEvent(eventDAO.find(1));
        presenter = new TicketDiscountSelectionPresenter(view, ticketDiscountDAO, eventDAO);

        view.setTicketDiscountType("Student");
        view.setTicketDiscountPercentage("10");

        presenter.onAddTicketDiscount();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(1, view.getShowTicketDiscountsCount());

        Assertions.assertEquals("Edit Existing Ticket Discounts", view.getPageTitle());
        Assertions.assertEquals("Edit", view.getButtonLabel());
    }

    /**
     * Test case for getting the list of ticket discounts.
     * Validates that the presenter retrieves the correct number of ticket discounts.
     */
    @Test
    public void onGetTicketDiscountList() {
        presenter = new TicketDiscountSelectionPresenter(view, ticketDiscountDAO, eventDAO);

        view.setTicketDiscountType("Student");
        view.setTicketDiscountPercentage("10");

        presenter.onAddTicketDiscount();

        Assertions.assertEquals(1, presenter.getTicketDiscountList().size());
    }

    /**
     * Test case for selecting a ticket discount for editing.
     * Validates that the presenter correctly populates the view with details of the selected ticket discount for editing.
     */
    @Test
    public void onSelectTicketDiscountEdit() {
        view.setAttachedEvent(eventDAO.find(1));
        presenter = new TicketDiscountSelectionPresenter(view, ticketDiscountDAO, eventDAO);

        TicketDiscount ticketDiscount = view.getAttachedEvent().getTicketDiscounts().get(0);

        presenter.onSelectTicketDiscount(ticketDiscount);

        Assertions.assertEquals("General", view.getTicketDiscountType());
        Assertions.assertEquals("20", view.getTicketDiscountPercentage());
    }

    /**
     * Test case for attempting to remove a ticket discount with invalid ticket discounts when editing an existing event.
     * Validates that the presenter displays an error message when attempting to remove the last ticket discount during editing.
     */
    @Test
    public void onRemoveTicketDiscountEditWithInvalidTicketDiscounts() {
        view.setAttachedEvent(eventDAO.find(10));
        presenter = new TicketDiscountSelectionPresenter(view, ticketDiscountDAO, eventDAO);

        TicketDiscount ticketDiscount = view.getAttachedEvent().getTicketDiscounts().iterator().next();

        presenter.onRemoveTicketDiscount(ticketDiscount);

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("You cannot remove the last ticket discount when editing.", view.getErrorMessage());
    }

    /**
     * Test case for successfully removing a ticket discount during editing an existing event.
     * Validates that the presenter removes a ticket discount without errors and updates the view.
     */
    @Test
    public void onRemoveTicketDiscountEdit() {
        view.setAttachedEvent(eventDAO.find(2));
        presenter = new TicketDiscountSelectionPresenter(view, ticketDiscountDAO, eventDAO);

        TicketDiscount ticketDiscount = view.getAttachedEvent().getTicketDiscounts().iterator().next();

        presenter.onRemoveTicketDiscount(ticketDiscount);

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(1, view.getShowTicketDiscountsCount());
    }

    /**
     * Test case for successfully removing a ticket discount during creating a new event.
     * Validates that the presenter removes a ticket discount without errors and updates the view.
     */
    @Test
    public void onRemoveTicketDiscountCreate() {
        TicketDiscount ticketDiscount = new TicketDiscount();
        ticketDiscount.setType(DiscountType.GENERAL);
        ticketDiscount.setPercentage(50.0);

        view.setTicketDiscountType("General");
        view.setTicketDiscountPercentage("50");

        presenter.onAddTicketDiscount();

        presenter.onRemoveTicketDiscount(ticketDiscount);

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(2, view.getShowTicketDiscountsCount());
    }

    /**
     * Test case for attempting to move to create event overview with no discounts.
     * Validates that the presenter displays an error message when attempting to move to create event overview with no discounts.
     */
    @Test
    public void onMoveToCreateOverviewWithNoDiscounts() {
        presenter.onMoveToCreateEventOverview();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please add at least one ticket discount.", view.getErrorMessage());
    }

    /**
     * Test case for successfully moving to create event overview.
     * Validates that the presenter moves to create event overview without errors when there is at least one ticket discount.
     */
    @Test
    public void onMoveToCreateOverview() {
        view.setTicketDiscountType("General");
        view.setTicketDiscountPercentage("50");

        presenter.onAddTicketDiscount();

        presenter.onMoveToCreateEventOverview();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(1, view.getMoveToCreateOverviewCount());
    }
}
