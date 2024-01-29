package com.example.eventhub.view.Event.CreateEventOverview;

import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.OrganizerDAO;
import com.example.eventhub.dao.TicketCategoryDAO;
import com.example.eventhub.dao.TicketDiscountDAO;
import com.example.eventhub.domain.CategoryName;
import com.example.eventhub.domain.DiscountType;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.EventType;
import com.example.eventhub.domain.Genre;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;
import com.example.eventhub.helper.AddressTestHelper;
import com.example.eventhub.helper.TicketCategoryTestHelper;
import com.example.eventhub.helper.TicketDiscountTestHelper;
import com.example.eventhub.memorydao.EventDAOMemory;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.memorydao.OrganizerDAOMemory;
import com.example.eventhub.memorydao.TicketCategoryDAOMemory;
import com.example.eventhub.memorydao.TicketDiscountDAOMemory;
import com.example.eventhub.util.Money;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;

/**
 * Unit tests for the CreateEventOverviewPresenter class, covering scenarios related to managing ticket categories,
 * ticket discounts, and event creation or editing.
 */
public class CreateEventOverviewPresenterTest {
    private CreateEventOverviewViewStub view;
    private CreateEventOverviewPresenter presenter;

    private EventDAO eventDAO;
    private TicketCategoryDAO ticketCategoryDAO;
    private TicketDiscountDAO ticketDiscountDAO;
    private OrganizerDAO organizerDAO;

    private Event event;

    @Before
    public void setUp() {
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        view = new CreateEventOverviewViewStub();

        eventDAO = new EventDAOMemory();
        ticketCategoryDAO = new TicketCategoryDAOMemory();
        ticketDiscountDAO = new TicketDiscountDAOMemory();
        organizerDAO = new OrganizerDAOMemory();

        view.setAttachedEvent(eventDAO.find(1));
        view.setAttachedTicketCategories(eventDAO.find(1).getTicketCategories());
        view.setAttachedTicketDiscounts(eventDAO.find(1).getTicketDiscounts());
        presenter = new CreateEventOverviewPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO, organizerDAO);
    }

    /**
     * Test case for retrieving ticket categories.
     * Validates that the presenter successfully retrieves the list of ticket categories.
     */
    @Test
    public void onGetTicketCategories() {
        Assertions.assertEquals(2, presenter.getTicketCategoryList().size());
    }

    /**
     * Test case for retrieving ticket discounts.
     * Validates that the presenter successfully retrieves the list of ticket discounts.
     */
    @Test
    public void onGetTicketDiscounts() {
        Assertions.assertEquals(2, presenter.getTicketDiscountList().size());
    }

    /**
     * Test case for saving an event with no ticket categories.
     * Validates that an error is displayed when attempting to save an event with no ticket categories.
     */
    @Test
    public void onSaveEventWithNoCategories() {
        view.setAttachedTicketCategories(new ArrayList<>());
        presenter = new CreateEventOverviewPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO, organizerDAO);
        presenter.onSaveEvent();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please add at least one ticket category.", view.getErrorMessage());
    }

    /**
     * Test case for saving an event with no ticket discounts.
     * Validates that an error is displayed when attempting to save an event with no ticket discounts.
     */
    @Test
    public void onSaveEventWithNoDiscounts() {
        view.setAttachedTicketDiscounts(new ArrayList<>());
        presenter = new CreateEventOverviewPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO, organizerDAO);
        presenter.onSaveEvent();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please add at least one ticket discount.", view.getErrorMessage());
    }

    /**
     * Test case for saving a new event.
     * Validates that a new event is successfully created with associated ticket categories and discounts.
     */
    @Test
    public void onSaveEventCreate() {
        Integer prevEventCount = eventDAO.findAll().size();
        Integer prevTicketCategoryCount = ticketCategoryDAO.findAll().size();
        Integer prevTicketDiscountCount = ticketDiscountDAO.findAll().size();
        Integer prevOrganizerCount = organizerDAO.find(1).getEvents().size();

        event = new Event();
        event.setName("Home Cinema");
        event.setLocation(AddressTestHelper.initAddress1());
        event.setDate(LocalDateTime.of(2025, 1, 1, 12, 0));
        event.setGenre(Genre.CINEMA);
        event.setType(EventType.OPEN);

        view.setAttachedEvent(event);

        ArrayList<TicketCategory> ticketCategoriesList = new ArrayList<>();

        TicketCategory ticketCategory = new TicketCategory();
        ticketCategory.setName(CategoryName.GENERAL);
        ticketCategory.setPrice(new Money(new BigDecimal(50), Currency.getInstance("EUR")));
        ticketCategory.setDescription("General Admission");
        ticketCategory.setQuantity(100);

        ticketCategoriesList.add(ticketCategory);
        view.setAttachedTicketCategories(ticketCategoriesList);

        ArrayList<TicketDiscount> ticketDiscountsList = new ArrayList<>();

        TicketDiscount ticketDiscount = new TicketDiscount();
        ticketDiscount.setType(DiscountType.GENERAL.GENERAL);
        ticketDiscount.setPercentage(10);

        ticketDiscountsList.add(ticketDiscount);
        view.setAttachedTicketDiscounts(ticketDiscountsList);

        view.setAttachedOrganizerId(1);

        presenter = new CreateEventOverviewPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO, organizerDAO);

        presenter.onSaveEvent();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(prevEventCount + 1, eventDAO.findAll().size());
        Assertions.assertEquals(prevTicketCategoryCount + 1 , ticketCategoryDAO.findAll().size());
        Assertions.assertEquals(prevTicketDiscountCount + 1, ticketDiscountDAO.findAll().size());
        Assertions.assertEquals(prevOrganizerCount + 1, organizerDAO.find(1).getEvents().size());
        Assertions.assertEquals("Your event has been created successfully!", view.getSuccessfulCreationMessage());
    }

    /**
     * Test case for saving an edited event.
     * Validates that an existing event is successfully updated with edited ticket categories and discounts.
     */
    @Test
    public void onSaveEventEdit() {
        Integer prevEventCount = eventDAO.findAll().size();
        Integer prevEventTicketCategoryCount = eventDAO.find(1).getTicketCategories().size();
        Integer prevEventTicketDiscountCount = eventDAO.find(1).getTicketDiscounts().size();
        Integer prevTicketCategoryCount = ticketCategoryDAO.findAll().size();
        Integer prevTicketDiscountCount = ticketDiscountDAO.findAll().size();
        Integer prevOrganizerCount = organizerDAO.find(1).getEvents().size();
        Event event = eventDAO.find(1);

        ArrayList<TicketCategory> ticketCategoriesList = new ArrayList<>();

        TicketCategory ticketCategory = TicketCategoryTestHelper.initTicketCategory1();

        ticketCategoriesList.add(ticketCategory);
        view.setAttachedTicketCategories(ticketCategoriesList);

        ArrayList<TicketDiscount> ticketDiscountsList = new ArrayList<>();

        TicketDiscount ticketDiscount = TicketDiscountTestHelper.initTicketDiscount1();

        ticketDiscountsList.add(ticketDiscount);
        view.setAttachedTicketDiscounts(ticketDiscountsList);

        presenter = new CreateEventOverviewPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO, organizerDAO);

        presenter.onSaveEvent();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(prevEventCount, eventDAO.findAll().size());
        Assertions.assertEquals(prevEventTicketCategoryCount - 1, eventDAO.find(1).getTicketCategories().size());
        Assertions.assertEquals(prevEventTicketDiscountCount - 1, eventDAO.find(1).getTicketDiscounts().size());
        Assertions.assertEquals(prevTicketCategoryCount, ticketCategoryDAO.findAll().size());
        Assertions.assertEquals(prevTicketDiscountCount, ticketDiscountDAO.findAll().size());
        Assertions.assertEquals(prevOrganizerCount, organizerDAO.find(1).getEvents().size());
        Assertions.assertEquals("Your event has been updated successfully!", view.getSuccessfulUpdateMessage());
    }

    /**
     * Test case for displaying ticket categories with no categories.
     * Validates that the presenter correctly handles displaying an empty list of ticket categories.
     */
    @Test
    public void onDisplayTicketCategoriesWithNoCategories() {
        view.setAttachedTicketCategories(new ArrayList<>());
        presenter = new CreateEventOverviewPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO, organizerDAO);
        presenter.onDisplayTicketCategories();

        Assertions.assertEquals(1, view.getShowEmptyTicketCategoriesCount());
        Assertions.assertEquals(0, view.getShowTicketCategoriesCount());
    }

    /**
     * Test case for displaying ticket categories with categories.
     * Validates that the presenter correctly handles displaying a list of ticket categories.
     */
    @Test
    public void onDisplayTicketCategoriesWithCategories() {
        presenter.onDisplayTicketCategories();

        Assertions.assertEquals(0, view.getShowEmptyTicketCategoriesCount());
        Assertions.assertEquals(1, view.getShowTicketCategoriesCount());
    }

    /**
     * Test case for displaying ticket discounts with no discounts.
     * Validates that the presenter correctly handles displaying an empty list of ticket discounts.
     */
    @Test
    public void onDisplayTicketDiscountsWithNoDiscounts() {
        view.setAttachedTicketDiscounts(new ArrayList<>());
        presenter = new CreateEventOverviewPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO, organizerDAO);
        presenter.onDisplayTicketDiscounts();

        Assertions.assertEquals(1, view.getShowEmptyTicketDiscountsCount());
        Assertions.assertEquals(0, view.getShowTicketDiscountsCount());
    }

    /**
     * Test case for displaying ticket discounts with discounts.
     * Validates that the presenter correctly handles displaying a list of ticket discounts.
     */
    @Test
    public void onDisplayTicketDiscountsWithDiscounts() {
        presenter.onDisplayTicketDiscounts();

        Assertions.assertEquals(0, view.getShowEmptyTicketDiscountsCount());
        Assertions.assertEquals(1, view.getShowTicketDiscountsCount());
    }

    /**
     * Test case for navigating to the ticket category selection view.
     * Validates that the presenter correctly navigates to the ticket category selection view.
     */
    @Test
    public void onMoveToSelectCategories() {
        presenter.onMoveToSelectCategories();

        Assertions.assertEquals(1, view.getMoveToSelectCategoriesCount());
    }

    /**
     * Test case for navigating to the ticket discount selection view.
     * Validates that the presenter correctly navigates to the ticket discount selection view.
     */
    @Test
    public void onMoveToSelectDiscounts() {
        presenter.onMoveToSelectDiscounts();

        Assertions.assertEquals(1, view.getMoveToSelectDiscountsCount());
    }

    /**
     * Test case for removing a ticket category of a free event.
     * Validates that the presenter correctly handles removing a ticket category of a free event.
     */
    @Test
    public void onRemoveTicketCategoryOfFreeEvent() {
        Event event = eventDAO.find(7);

        view.setAttachedEvent(event);
        presenter = new CreateEventOverviewPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO, organizerDAO);
        presenter.onRemoveTicketCategory(event.getTicketCategories().get(0));

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("You cannot edit the ticket category of a free event.", view.getErrorMessage());
    }

    /**
     * Test case for removing a ticket category during event editing with invalid ticket categories.
     * Validates that the presenter correctly handles attempting to remove the last ticket category when editing,
     * resulting in an error message.
     */
    @Test
    public void onRemoveTicketCategoryEditWithInvalidTicketCategories() {
        view.setAttachedEvent(eventDAO.find(1));
        presenter = new CreateEventOverviewPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO, organizerDAO);

        presenter.onRemoveTicketCategory(view.getAttachedEvent().getTicketCategories().get(0));
        presenter.onRemoveTicketCategory(view.getAttachedEvent().getTicketCategories().get(0));

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("You cannot remove the last ticket category when editing.", view.getErrorMessage());
    }

    /**
     * Test case for removing a ticket category during event editing.
     * Validates that the presenter correctly handles removing a ticket category during event editing.
     */
    @Test
    public void onRemoveTicketCategoryEdit() {
        view.setAttachedEvent(eventDAO.find(1));
        presenter = new CreateEventOverviewPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO, organizerDAO);

        presenter.onRemoveTicketCategory(view.getAttachedEvent().getTicketCategories().get(0));

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(1, view.getShowTicketCategoriesCount());
    }

    /**
     * Test case for removing a ticket category during event creation.
     * Validates that the presenter correctly handles removing a ticket category during event creation.
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

        presenter.onDisplayTicketCategories();

        presenter.onRemoveTicketCategory(ticketCategory);

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(2, view.getShowTicketCategoriesCount());
    }

    /**
     * Test case for removing a ticket discount of a free event.
     * Validates that the presenter correctly handles removing a ticket discount of a free event.
     */
    @Test
    public void onRemoveTicketDiscountOfFreeEvent() {
        Event event = eventDAO.find(7);

        view.setAttachedEvent(event);
        presenter = new CreateEventOverviewPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO, organizerDAO);
        presenter.onRemoveTicketDiscount(event.getTicketDiscounts().get(0));

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("You cannot edit the ticket discount of a free event.", view.getErrorMessage());
    }

    /**
     * Test case for removing a ticket discount during event editing with invalid ticket discounts.
     * Validates that the presenter correctly handles attempting to remove the last ticket discount when editing,
     * resulting in an error message.
     */
    @Test
    public void onRemoveTicketDiscountEditWithInvalidTicketDiscounts() {
        view.setAttachedEvent(eventDAO.find(1));
        presenter = new CreateEventOverviewPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO, organizerDAO);

        presenter.onRemoveTicketDiscount(view.getAttachedEvent().getTicketDiscounts().get(0));
        presenter.onRemoveTicketDiscount(view.getAttachedEvent().getTicketDiscounts().get(0));

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("You cannot remove the last ticket discount when editing.", view.getErrorMessage());
    }

    /**
     * Test case for removing a ticket discount during event editing.
     * Validates that the presenter correctly handles removing a ticket discount during event editing.
     */
    @Test
    public void onRemoveTicketDiscountEdit() {
        view.setAttachedEvent(eventDAO.find(1));
        presenter = new CreateEventOverviewPresenter(view, eventDAO, ticketCategoryDAO, ticketDiscountDAO, organizerDAO);

        presenter.onRemoveTicketDiscount(view.getAttachedEvent().getTicketDiscounts().get(0));

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(1, view.getShowTicketDiscountsCount());
    }

    /**
     * Test case for removing a ticket discount during event creation.
     * Validates that the presenter correctly handles removing a ticket discount during event creation.
     */
    @Test
    public void onRemoveTicketDiscountCreate() {
        TicketDiscount ticketDiscount = new TicketDiscount();
        ticketDiscount.setType(DiscountType.GENERAL.GENERAL);
        ticketDiscount.setPercentage(10);

        view.setTicketDiscountType("General");
        view.setTicketDiscountPercentage("10");

        presenter.onDisplayTicketDiscounts();

        presenter.onRemoveTicketDiscount(ticketDiscount);

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(2, view.getShowTicketDiscountsCount());
    }
}
