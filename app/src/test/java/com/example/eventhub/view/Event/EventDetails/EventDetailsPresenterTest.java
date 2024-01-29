package com.example.eventhub.view.Event.EventDetails;

import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.PurchaseDAO;
import com.example.eventhub.dao.TicketDAO;
import com.example.eventhub.domain.Event;
import com.example.eventhub.memorydao.CustomerDAOMemory;
import com.example.eventhub.memorydao.EventDAOMemory;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.memorydao.OrganizerDAOMemory;
import com.example.eventhub.memorydao.PurchaseDAOMemory;
import com.example.eventhub.memorydao.TicketDAOMemory;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Unit tests for the EventDetailsPresenter class, covering scenarios related to setting views, event details, reviews,
 * and booking tickets.
 */
public class EventDetailsPresenterTest {
    EventDetailsViewStub view;
    EventDetailsPresenter presenter;

    private EventDAO eventDAO;
    private CustomerDAO customerDAO;
    private OrganizerDAOMemory organizerDAO;
    private PurchaseDAO purchaseDAO;
    private TicketDAO ticketDAO;

    @Before
    public void setUp() {
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        view = new EventDetailsViewStub();

        eventDAO = new EventDAOMemory();
        customerDAO = new CustomerDAOMemory();
        organizerDAO = new OrganizerDAOMemory();
        purchaseDAO = new PurchaseDAOMemory();
        ticketDAO = new TicketDAOMemory();

        presenter = new EventDetailsPresenter(customerDAO, organizerDAO, eventDAO, purchaseDAO, ticketDAO);
        presenter.setView(view);
    }

    /**
     * Test case for setting a new view for the presenter.
     * Validates that the view is successfully set in the presenter.
     */
    @Test
    public void onSetView() {
        EventDetailsView newView = new EventDetailsViewStub();
        presenter.setView(newView);
        Assertions.assertEquals(newView, presenter.getView());
    }

    /**
     * Test case for setting the event.
     * Validates that the presenter successfully sets the event with the specified event ID.
     */
    @Test
    public void onSetEvent() {
        presenter.setEvent(1);
        Assertions.assertEquals(1, presenter.getEvent().getEventId());
    }

    /**
     * Test case for setting the customer.
     * Validates that the presenter successfully sets the customer with the specified customer ID.
     */
    @Test
    public void onSetCustomer() {
        presenter.setCustomer(4);
        Assertions.assertEquals(4, presenter.getCustomer().getId());
    }

    /**
     * Test case for setting the organizer.
     * Validates that the presenter successfully sets the organizer with the specified organizer ID.
     */
    @Test
    public void onSetOrganizer() {
        presenter.setOrganizer(1);
        Assertions.assertEquals(1, presenter.getOrganizer().getId());
    }

    /**
     * Test case for setting the review list.
     * Validates that the presenter successfully sets the review list based on the current event.
     */
    @Test
    public void onSetReviewList() {
        presenter.setEvent(1);
        presenter.setReviewList();
        Assertions.assertEquals(2, presenter.getReviewList().size());
    }

    /**
     * Test case for setting event details with no rating.
     * Validates that the presenter successfully sets event details, including handling cases with no ratings available.
     */
    @Test
    public void onSetEventDetailsWithNoRating() {
        presenter.setEvent(11);
        presenter.setEventDetails();

        // TEMP
        Event event = eventDAO.find(11);
        System.out.println(event.getEventCapacity());
        System.out.println(event.getTicketsSold());
        System.out.println(event.getAvailableTickets());

        Assertions.assertEquals("Classical Music Concert", view.getEventName());
        Assertions.assertEquals("Ermoupolis 65, Athens 11128", view.getEventAddress());
        Assertions.assertEquals("MUSIC", view.getEventGenre());
        Assertions.assertEquals("OPEN", view.getEventType());
        Assertions.assertEquals("10-03-2024 19:30", view.getEventTime());
        Assertions.assertEquals("N/A", view.getAverageRating());
        Assertions.assertEquals("40", view.getEventCapacity());
        Assertions.assertEquals("36", view.getAvailableTickets());
    }

    /**
     * Test case for setting event details with ratings.
     * Validates that the presenter successfully sets event details, including handling cases with ratings available.
     */
    @Test
    public void onSetEventDetails() {
        presenter.setEvent(1);
        presenter.setEventDetails();

        Assertions.assertEquals("House Party", view.getEventName());
        Assertions.assertEquals("Acharnon 40, Athens 11122", view.getEventAddress());
        Assertions.assertEquals("MUSIC", view.getEventGenre());
        Assertions.assertEquals("OPEN", view.getEventType());
        Assertions.assertEquals("04-06-2023 12:12", view.getEventTime());
        Assertions.assertEquals("9/10", view.getAverageRating());
        Assertions.assertEquals("180", view.getEventCapacity());
        Assertions.assertEquals("177", view.getAvailableTickets());
    }

    /**
     * Test case for hiding the book ticket button.
     * Validates that the presenter successfully hides the book ticket button when specified.
     */
    @Test
    public void onHideTicketButton() {
        presenter.setEvent(1);
        presenter.onDisplayBookTicketButton(1);

        Assertions.assertEquals(1, view.getOnHideBookTicketButtonCount());
        Assertions.assertEquals(0, view.getOnShowBookTicketButtonCount());
    }

    /**
     * Test case for displaying the book ticket button.
     * Validates that the presenter successfully displays the book ticket button when specified.
     */
    @Test
    public void onShowTicketButton() {
        presenter.setEvent(1);
        presenter.onDisplayBookTicketButton(0);

        Assertions.assertEquals(1, view.getOnShowBookTicketButtonCount());
        Assertions.assertEquals(0, view.getOnHideBookTicketButtonCount());
    }

    /**
     * Test case for displaying empty reviews.
     * Validates that the presenter successfully displays the appropriate message for empty reviews.
     */
    @Test
    public void onDisplayReviewsEmpty() {
        presenter.setEvent(11);
        presenter.onDisplayReviews();

        Assertions.assertEquals(1, view.getShowEmptyReviewsCount());
        Assertions.assertEquals(0, view.getShowReviewCount());
    }

    /**
     * Test case for displaying reviews.
     * Validates that the presenter successfully displays reviews when available.
     */
    @Test
    public void onDisplayReviews() {
        presenter.setEvent(1);
        presenter.onDisplayReviews();

        Assertions.assertEquals(1, view.getShowReviewCount());
        Assertions.assertEquals(0, view.getShowEmptyReviewsCount());
    }

    /**
     * Test case for booking a ticket with a null customer.
     * Validates that the presenter prompts the user to login or register when attempting to book a ticket with a null customer.
     */
    @Test
    public void onBookTicketWithNullCustomer() {
        presenter.setEvent(1);
        presenter.onBookTicket();

        Assertions.assertEquals(1, view.getOnLoginOrRegisterCount());
    }

    /**
     * Test case for booking 4 free tickets.
     * Validates that the presenter successfully books 4 free tickets, updates the view, and creates a purchase.
     */
    @Test
    public void onBookWith4FreeTickets() {
        Integer prevTicketCount = ticketDAO.findAll().size();
        Integer prevPurchaseCount = purchaseDAO.findAll().size();

        presenter.setEvent(7);
        presenter.setCustomer(4);
        presenter.onBookTicket();

        Assertions.assertEquals(1, view.getBookFreeTicketCount());

        presenter.onMakeFreePurchase("4");

        Assertions.assertEquals(1, view.getOnShowSuccessfulFreePurchaseCount());
        Assertions.assertEquals(prevTicketCount + 4, ticketDAO.findAll().size());
        Assertions.assertEquals(prevPurchaseCount + 1, purchaseDAO.findAll().size());
    }

    /**
     * Test case for booking a ticket.
     * Validates that the presenter successfully books a ticket, updates the view, and creates a purchase.
     */
    @Test
    public void onBookTicket() {
        presenter.setEvent(1);
        presenter.setCustomer(4);
        presenter.onBookTicket();

        Assertions.assertEquals(1, view.getBookTicketCount());
    }
}
