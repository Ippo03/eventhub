package com.example.eventhub.view.Ticket.TicketBooking;

import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.PurchaseDAO;
import com.example.eventhub.dao.TicketCategoryDAO;
import com.example.eventhub.dao.TicketDAO;
import com.example.eventhub.dao.TicketDiscountDAO;
import com.example.eventhub.domain.Ticket;
import com.example.eventhub.memorydao.CustomerDAOMemory;
import com.example.eventhub.memorydao.EventDAOMemory;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.memorydao.PurchaseDAOMemory;
import com.example.eventhub.memorydao.TicketCategoryDAOMemory;
import com.example.eventhub.memorydao.TicketDAOMemory;
import com.example.eventhub.memorydao.TicketDiscountDAOMemory;
import com.example.eventhub.view.TicketBooking.TicketBookingPresenter;
import com.example.eventhub.view.TicketBooking.TicketBookingView;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Unit tests for the TicketBookingPresenter class, focusing on the interaction
 * between the presenter and the view, as well as specific functionality related
 * to ticket booking for events.
 */
public class TicketBookingPresenterTest {
    TicketBookingViewStub view;
    TicketBookingPresenter presenter;

    private CustomerDAO customerDAO;
    private EventDAO eventDAO;
    private TicketDAO ticketDAO;
    private TicketCategoryDAO ticketCategoryDAO;
    private TicketDiscountDAO ticketDiscountDAO;
    private PurchaseDAO purchaseDAO;

    /**
     * Set up method for initializing the test environment.
     * Prepares test data using MemoryInitializer, initializes the view with a stub,
     * and sets up in-memory implementations for CustomerDAO, EventDAO, TicketDAO,
     * TicketCategoryDAO, TicketDiscountDAO, and PurchaseDAO.
     */
    @Before
    public void setUp() {
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        view = new TicketBookingViewStub();

        customerDAO = new CustomerDAOMemory();
        eventDAO = new EventDAOMemory();
        ticketDAO = new TicketDAOMemory();
        ticketCategoryDAO = new TicketCategoryDAOMemory();
        ticketDiscountDAO = new TicketDiscountDAOMemory();
        purchaseDAO = new PurchaseDAOMemory();

        presenter = new TicketBookingPresenter(
                customerDAO, eventDAO, ticketCategoryDAO, ticketDiscountDAO, ticketDAO, purchaseDAO
        );
        presenter.setView(view);

        presenter.setEvent(1);
    }

    /**
     * Test case for setting a new view for the presenter.
     * Validates that the view is successfully set in the presenter.
     */
    @Test
    public void onSetView() {
        TicketBookingView newView = new TicketBookingViewStub();
        presenter.setView(newView);
        Assertions.assertEquals(newView, presenter.getView());
    }

    /**
     * Test case for setting the name of the event in the view.
     * Validates that the correct event name is displayed in the view.
     */
    @Test
    public void onSetName() {
        presenter.setEvent(1);
        presenter.setName();
        Assertions.assertEquals("House Party", view.getEventName());
    }

    /**
     * Test case for setting an event in the presenter.
     * Validates that the correct event is set in the presenter.
     */
    @Test
    public void onSetEvent() {
        presenter.setEvent(1);
        Assertions.assertEquals(1, presenter.getEvent().getEventId());
    }

    /**
     * Test case for setting a customer in the presenter.
     * Validates that the correct customer is set in the presenter.
     */
    @Test
    public void onSetCustomer() {
        presenter.setCustomer(4);
        Assertions.assertEquals(4, presenter.getCustomer().getId());
    }

    /**
     * Test case for setting ticket categories in the view.
     * Validates that the correct ticket categories are displayed in the view.
     */
    @Test
    public void onSetTicketCategories() {
        String[] ticketCategories = presenter.getTicketCategories();
        Assertions.assertEquals(2, ticketCategories.length);
        Assertions.assertEquals("GENERAL - 20.00 EUR", ticketCategories[0]);
        Assertions.assertEquals("SIDE - 50.00 EUR", ticketCategories[1]);
    }

    /**
     * Test case for setting ticket discounts in the view.
     * Validates that the correct ticket discounts are displayed in the view.
     */
    @Test
    public void onSetTicketDiscounts() {
        String[] ticketDiscounts = presenter.getTicketDiscounts();
        Assertions.assertEquals(2, ticketDiscounts.length);
        Assertions.assertEquals("GENERAL - 20.0%", ticketDiscounts[0]);
        Assertions.assertEquals("STUDENT - 60.0%", ticketDiscounts[1]);
    }

    /**
     * Test case for retrieving a ticket category by its ID.
     * Validates that the correct ticket category is retrieved by ID.
     */
    @Test
    public void onGetTicketCategoryById() {
        Assertions.assertEquals(ticketCategoryDAO.find(1), presenter.getTicketCategoryById(1));
    }

    /**
     * Test case for retrieving a ticket discount by its ID.
     * Validates that the correct ticket discount is retrieved by ID.
     */
    @Test
    public void onGetTicketDiscountById() {
        Assertions.assertEquals(ticketDiscountDAO.find(1), presenter.getTicketDiscountById(1));
    }

    /**
     * Test case for creating a new ticket.
     * Validates that a ticket is correctly created with the selected category and discount.
     */
    @Test
    public void onCreateTicket() {
        view.setSelectedCategory(ticketCategoryDAO.find(1));
        view.setSelectedDiscount(ticketDiscountDAO.find(3));
        Ticket ticket = presenter.onUpdateTicketPrice();

        Assertions.assertEquals(1, ticket.getEvent().getEventId());
        Assertions.assertEquals(1, ticket.getTicketCategory().getTicketCategoryId());
        Assertions.assertEquals(3, ticket.getTicketDiscount().getTicketDiscountId());
    }

    /**
     * Test case for updating the ticket price based on the selected category and discount.
     * Validates that the correct ticket price is displayed in the view.
     */
    @Test
    public void onUpdateTicketPrice() {
        view.setSelectedCategory(ticketCategoryDAO.find(1));
        view.setSelectedDiscount(ticketDiscountDAO.find(3));
        presenter.onUpdateTicketPrice();

        Assertions.assertEquals("16.00 EUR", view.getTicketPrice());
    }

    /**
     * Test case for updating the total cost of the purchase.
     * Validates that the total cost is correctly updated when adding multiple tickets.
     */
    @Test
    public void onUpdateTotalCost() {
        view.setSelectedCategory(ticketCategoryDAO.find(1));
        view.setSelectedDiscount(ticketDiscountDAO.find(3));
        presenter.onAddTicket(presenter.onUpdateTicketPrice());

        view.setSelectedCategory(ticketCategoryDAO.find(1));
        view.setSelectedDiscount(ticketDiscountDAO.find(11));
        presenter.onAddTicket(presenter.onUpdateTicketPrice());

        view.setSelectedCategory(ticketCategoryDAO.find(1));
        view.setSelectedDiscount(ticketDiscountDAO.find(3));
        presenter.onAddTicket(presenter.onUpdateTicketPrice());

        Assertions.assertEquals("40.00 EUR", view.getPurchaseTotalCost());
    }

    /**
     * Test case for adding a ticket to the purchase.
     * Validates that a ticket is successfully added, and the aggregated ticket list is updated.
     */
    @Test
    public void onAddTicket() {
        view.setSelectedCategory(ticketCategoryDAO.find(1));
        view.setSelectedDiscount(ticketDiscountDAO.find(3));
        presenter.onAddTicket(presenter.onUpdateTicketPrice());

        Assertions.assertEquals(1, presenter.getTicketList().size());
        Assertions.assertEquals(1, presenter.getTicketCountMap().size());
        Assertions.assertEquals(1, presenter.getAggregatedTicketList().size());
        Assertions.assertEquals("16.00 EUR", view.getTicketPrice());
        Assertions.assertEquals(1, view.getShowTicketsCount());
    }

    /**
     * Test case for removing a ticket from the purchase.
     * Validates that a ticket is successfully removed, and the aggregated ticket list is updated.
     */
    @Test
    public void onRemoveTicket() {
        view.setSelectedCategory(ticketCategoryDAO.find(1));
        view.setSelectedDiscount(ticketDiscountDAO.find(3));
        presenter.onAddTicket(presenter.onUpdateTicketPrice());

        view.setSelectedCategory(ticketCategoryDAO.find(1));
        view.setSelectedDiscount(ticketDiscountDAO.find(11));
        presenter.onAddTicket(presenter.onUpdateTicketPrice());

        view.setSelectedCategory(ticketCategoryDAO.find(1));
        view.setSelectedDiscount(ticketDiscountDAO.find(3));
        presenter.onAddTicket(presenter.onUpdateTicketPrice());

        Assertions.assertEquals(3, presenter.getTicketList().size());
        Assertions.assertEquals(2, presenter.getTicketCountMap().size());
        Assertions.assertEquals(2, presenter.getAggregatedTicketList().size());
        Assertions.assertEquals("40.00 EUR", view.getPurchaseTotalCost());
        Assertions.assertEquals(3, view.getShowTicketsCount());

        presenter.onRemoveTicket(presenter.getTicketList().get(0));

        Assertions.assertEquals(2, presenter.getTicketList().size());
        Assertions.assertEquals(2, presenter.getAggregatedTicketList().size());
        Assertions.assertEquals("24.00 EUR", view.getPurchaseTotalCost());
        Assertions.assertEquals(4, view.getShowTicketsCount());

        presenter.onRemoveTicket(presenter.getTicketList().get(0));

        Assertions.assertEquals(1, presenter.getTicketList().size());
        Assertions.assertEquals(1, presenter.getAggregatedTicketList().size());
        Assertions.assertEquals("16.00 EUR", view.getPurchaseTotalCost());
        Assertions.assertEquals(5, view.getShowTicketsCount());
    }


    /**
     * Test case for starting the listener.
     * Validates that the appropriate listener methods are called when the presenter starts.
     */
    @Test
    public void onStartListener() {
        presenter.onStartListener();
        Assertions.assertEquals(1, view.getOnChangeCategoryCount());
        Assertions.assertEquals(1, view.getOnChangeDiscountCount());
    }

    /**
     * Test case for completing a purchase with no tickets added to the cart.
     * Validates that an error message is displayed when attempting to complete a purchase with an empty cart.
     */
    @Test
    public void onCompletePurchaseWithNoTickets() {
        presenter.onCompletePurchase();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Purchase Not Completed", view.getErrorTitle());
        Assertions.assertEquals("Your cart is empty !\n" +
                "Please add some tickets to make a purchase.", view.getErrorMessage());
    }

    /**
     * Test case for completing a purchase with more tickets selected than available.
     * Validates that an error message is displayed when attempting to purchase more tickets than available.
     */
    @Test
    public void onCompletePurchaseWithMoreTicketsThanAvailable() {
        presenter.setEvent(15);
        view.setSelectedCategory(ticketCategoryDAO.find(16));
        view.setSelectedDiscount(ticketDiscountDAO.find(29));

        for(int i = 0, n = 19; i < n; i++) {
            presenter.onAddTicket(presenter.onUpdateTicketPrice());
        }

        presenter.onCompletePurchase();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Purchase Not Completed", view.getErrorTitle());
        Assertions.assertEquals("There are not enough tickets available for the category SIDE.\n" +
                "This category has 18 tickets left.", view.getErrorMessage());
    }

    /**
     * Test case for successfully completing a purchase.
     * Validates that a purchase is successfully completed, and the appropriate messages and counts are updated.
     */
    @Test
    public void onCompletePurchase() {
        Integer prevTicketCount = ticketDAO.findAll().size();
        Integer prevPurchaseCount = purchaseDAO.findAll().size();

        presenter.setCustomer(4);

        view.setSelectedCategory(ticketCategoryDAO.find(1));
        view.setSelectedDiscount(ticketDiscountDAO.find(3));
        presenter.onAddTicket(presenter.onUpdateTicketPrice());

        view.setSelectedCategory(ticketCategoryDAO.find(1));
        view.setSelectedDiscount(ticketDiscountDAO.find(11));
        presenter.onAddTicket(presenter.onUpdateTicketPrice());

        presenter.onCompletePurchase();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals("Your purchase has been completed !\n" +
                " You will be redirected to your home page.", view.getSuccessfulPurchaseMessage());
        Assertions.assertEquals(prevTicketCount + 2, ticketDAO.findAll().size());
        Assertions.assertEquals(prevPurchaseCount + 1, purchaseDAO.findAll().size());
    }
}
