package com.example.eventhub.view.Event.TicketCategorySelection;

import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.TicketCategoryDAO;
import com.example.eventhub.domain.CategoryName;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.memorydao.EventDAOMemory;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.memorydao.TicketCategoryDAOMemory;
import com.example.eventhub.util.Money;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Unit tests for the TicketCategorySelectionPresenter class, focusing on various scenarios of ticket category handling.
 */
public class TicketCategorySelectionPresenterTest {
    private TicketCategorySelectionViewStub view;
    private TicketCategorySelectionPresenter presenter;

    private EventDAO eventDAO;
    private TicketCategoryDAO ticketCategoryDAO;

    private Event event;

    @Before
    public void setUp() {
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        view = new TicketCategorySelectionViewStub();

        eventDAO = new EventDAOMemory();
        ticketCategoryDAO = new TicketCategoryDAOMemory();

        event = new Event();
        view.setEventName("Home Cinema");
        view.setEventAddress("Ermou 17, Athens 12345");
        view.setEventType("Open");
        view.setEventDate("01-01-2025");
        view.setEventTime("12:00");
        view.setEventGenre(1);

        view.setAttachedEvent(event);
        presenter = new TicketCategorySelectionPresenter(view, ticketCategoryDAO, eventDAO);
    }

    /**
     * Test case for adding a ticket category with empty fields.
     * Validates that the presenter displays an error message when attempting to add a ticket category with empty fields.
     */
    @Test
    public void onAddTicketCategoryWithEmptyField() {
        view.setTicketCategoryPrice("50");
        view.setTicketCategoryDescription("General Admission");
        view.setTicketCategoryQuantity("100");

        presenter.onAddTicketCategory();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill all the fields.", view.getErrorMessage());
    }

    /**
     * Test case for adding a ticket category with an invalid price.
     * Validates that the presenter displays an error message when attempting to add a ticket category with an invalid price.
     */
    @Test
    public void onAddTicketCategoryWithInvalidPrice() {
        view.setTicketCategoryName("General");
        view.setTicketCategoryPrice("0");
        view.setTicketCategoryDescription("General Admission");
        view.setTicketCategoryQuantity("100");

        presenter.onAddTicketCategory();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Ticket category price must be a positive number.", view.getErrorMessage());
    }

    /**
     * Test case for adding a ticket category with an invalid quantity.
     * Validates that the presenter displays an error message when attempting to add a ticket category with an invalid quantity.
     */
    @Test
    public void onAddTicketCategoryWithInvalidQuantity() {
        view.setTicketCategoryName("General");
        view.setTicketCategoryPrice("50");
        view.setTicketCategoryDescription("General Admission");
        view.setTicketCategoryQuantity("0");

        presenter.onAddTicketCategory();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Ticket category quantity must be a positive number.", view.getErrorMessage());
    }

    /**
     * Test case for attempting to add an already existing ticket category.
     * Validates that the presenter displays an error message when attempting to add a ticket category that already exists.
     */
    @Test
    public void onAddTicketCategoryAlreadyExisting() {
        view.setTicketCategoryName("General");
        view.setTicketCategoryPrice("50");
        view.setTicketCategoryDescription("General Admission");
        view.setTicketCategoryQuantity("100");

        presenter.onAddTicketCategory();

        view.setTicketCategoryName("General");
        view.setTicketCategoryPrice("50");
        view.setTicketCategoryDescription("General Admission");
        view.setTicketCategoryQuantity("100");

        presenter.onAddTicketCategory();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Ticket category already exists.", view.getErrorMessage());
    }

    /**
     * Test case for successfully adding a ticket category.
     * Validates that the presenter adds a ticket category without errors and updates the view accordingly.
     */
    @Test
    public void onAddTicketCategory() {
        view.setTicketCategoryName("General");
        view.setTicketCategoryPrice("50");
        view.setTicketCategoryDescription("General Admission");
        view.setTicketCategoryQuantity("100");

        presenter.onAddTicketCategory();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(1, view.getResetInputFieldsCount());
        Assertions.assertEquals(1, view.getShowTicketCategoriesCount());
    }

    /**
     * Test case for editing a ticket category with empty fields.
     * Validates that the presenter displays an error message when attempting to edit a ticket category to an already existing one.
     */
    @Test
    public void onAddTicketCategoryEditAlreadyExisting() {
        view.setAttachedEvent(eventDAO.find(1));
        presenter = new TicketCategorySelectionPresenter(view, ticketCategoryDAO, eventDAO);

        view.setTicketCategoryName("Side");
        view.setTicketCategoryPrice("50");
        view.setTicketCategoryDescription("Side");
        view.setTicketCategoryQuantity("30");

        presenter.onAddTicketCategory();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Ticket category already exists.", view.getErrorMessage());
    }

    /**
     * Test case for attempting to add a ticket category with a quantity less than the bought tickets when editing.
     * Validates that the presenter displays an error message when attempting to add a ticket category
     * with a quantity less than the bought tickets when editing an event.
     */
    @Test
    public void onAddTicketCategoryEditLessThanBoughtTickets() {
        view.setAttachedEvent(eventDAO.find(2));
        presenter = new TicketCategorySelectionPresenter(view, ticketCategoryDAO, eventDAO);

        view.setTicketCategoryName("General");
        view.setTicketCategoryPrice("50");
        view.setTicketCategoryDescription("General Admission");
        view.setTicketCategoryQuantity("1");

        presenter.onAddTicketCategory();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("You cannot reduce the quantity of a ticket category that has already been bought.", view.getErrorMessage());
    }

    /**
     * Test case for successfully adding a ticket category when editing an existing event.
     * Validates that the presenter adds a ticket category without errors, updates the view, and provides the correct event details.
     */
    @Test
    public void onAddTicketCategoryEdit() {
        view.setAttachedEvent(eventDAO.find(1));
        presenter = new TicketCategorySelectionPresenter(view, ticketCategoryDAO, eventDAO);

        view.setTicketCategoryName("General");
        view.setTicketCategoryPrice("50");
        view.setTicketCategoryDescription("General Admission");
        view.setTicketCategoryQuantity("100");

        presenter.onAddTicketCategory();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(1, view.getShowTicketCategoriesCount());

        Assertions.assertEquals("Edit Existing Ticket Categories", view.getPageTitle());
        Assertions.assertEquals("Edit", view.getButtonLabel());
        Assertions.assertEquals("130", view.getEventCapacity());
    }

    /**
     * Test case for getting the list of ticket categories when editing an existing event.
     * Validates that the presenter retrieves the correct number of ticket categories for an existing event.
     */
    @Test
    public void onGetTicketCategories() {
        view.setAttachedEvent(eventDAO.find(1));
        presenter = new TicketCategorySelectionPresenter(view, ticketCategoryDAO, eventDAO);

        Assertions.assertEquals(2, presenter.getTicketCategoriesList().size());
    }

    /**
     * Test case for selecting a ticket category for editing.
     * Validates that the presenter correctly populates the view with details of the selected ticket category for editing.
     */
    @Test
    public void onSelectTicketCategoryOnEdit() {
        view.setAttachedEvent(eventDAO.find(1));
        presenter = new TicketCategorySelectionPresenter(view, ticketCategoryDAO, eventDAO);

        TicketCategory ticketCategory = eventDAO.find(1).getTicketCategories().iterator().next();

        presenter.onSelectTicketCategory(ticketCategory);

        Assertions.assertEquals("General", view.getTicketCategoryName());
        Assertions.assertEquals("20", view.getTicketCategoryPrice());
        Assertions.assertEquals("General Admission", view.getTicketCategoryDescription());
        Assertions.assertEquals("150", view.getTicketCategoryQuantity());
    }

    /**
     * Test case for attempting to remove a ticket category when editing with invalid ticket categories.
     * Validates that the presenter displays an error message when attempting to remove the last ticket category when editing.
     */
    @Test
    public void onRemoveTicketCategoryEditWithInvalidTicketCategories() {
        view.setAttachedEvent(eventDAO.find(10));
        presenter = new TicketCategorySelectionPresenter(view, ticketCategoryDAO, eventDAO);

        TicketCategory ticketCategory = view.getAttachedEvent().getTicketCategories().iterator().next();

        presenter.onRemoveTicketCategory(ticketCategory);

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("You cannot remove the last ticket category when editing.", view.getErrorMessage());
    }

    /**
     * Test case for successfully removing a ticket category when editing an existing event.
     * Validates that the presenter removes a ticket category without errors and updates the view.
     */
    @Test
    public void onRemoveTicketCategoryEdit() {
        view.setAttachedEvent(eventDAO.find(1));
        presenter = new TicketCategorySelectionPresenter(view, ticketCategoryDAO, eventDAO);

        TicketCategory ticketCategory = view.getAttachedEvent().getTicketCategories().iterator().next();

        presenter.onRemoveTicketCategory(ticketCategory);

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(1, view.getShowTicketCategoriesCount());
    }

    /**
     * Test case for successfully removing a ticket category when creating a new event.
     * Validates that the presenter removes a ticket category without errors and updates the view.
     */
    @Test
    public void onRemoveTicketCategoryCreate() {
        TicketCategory ticketCategory = new TicketCategory();
        ticketCategory.setName(CategoryName.GENERAL);
        ticketCategory.setPrice(new Money(new BigDecimal(50), Currency.getInstance("EUR")));
        ticketCategory.setDescription("General Admission");
        ticketCategory.setQuantity(100);

        view.setTicketCategoryName("General");
        view.setTicketCategoryPrice("50");
        view.setTicketCategoryDescription("General Admission");
        view.setTicketCategoryQuantity("100");

        presenter.onAddTicketCategory();

        presenter.onRemoveTicketCategory(ticketCategory);

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(2, view.getShowTicketCategoriesCount());
    }

    /**
     * Test case for moving to the ticket discount selection when no ticket categories are added.
     * Validates that the presenter displays an error message when attempting to move to ticket discount selection without adding any ticket categories.
     */
    @Test
    public void onMoveToTicketDiscountSelectionWithNoCategories() {
        presenter.onMoveToTicketDiscountSelection();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please add at least one ticket category.", view.getErrorMessage());
    }

    /**
     * Test case for successfully moving to the ticket discount selection.
     * Validates that the presenter initiates the process of moving to the ticket discount selection after adding at least one ticket category.
     */
    @Test
    public void onMoveToTicketDiscountSelection() {
        view.setTicketCategoryName("General");
        view.setTicketCategoryPrice("50");
        view.setTicketCategoryDescription("General Admission");
        view.setTicketCategoryQuantity("100");

        presenter.onAddTicketCategory();

        presenter.onMoveToTicketDiscountSelection();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(1, view.getOnMoveToTicketDiscountSelectionCount());
    }
}
