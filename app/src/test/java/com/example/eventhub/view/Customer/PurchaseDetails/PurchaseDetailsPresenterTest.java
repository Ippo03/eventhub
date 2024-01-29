package com.example.eventhub.view.Customer.PurchaseDetails;

import com.example.eventhub.dao.PurchaseDAO;
import com.example.eventhub.domain.CategoryName;
import com.example.eventhub.domain.DiscountType;
import com.example.eventhub.domain.Ticket;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;
import com.example.eventhub.domain.TicketKey;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.memorydao.PurchaseDAOMemory;
import com.example.eventhub.util.Money;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;

/**
 * Unit tests for the PurchaseDetailsPresenter class, focusing on the interaction
 * between the presenter and the view, as well as specific functionality related to
 * setting up and displaying purchase details.
 */
public class PurchaseDetailsPresenterTest {
    private PurchaseDetailsViewStub view;
    private PurchaseDetailsPresenter presenter;

    private PurchaseDAO purchaseDAO;

    /**
     * Set up method for initializing the test environment.
     * Prepares test data using MemoryInitializer, initializes the view with a stub,
     * and sets up an in-memory implementation for PurchaseDAO.
     */
    @Before
    public void setUp() {
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        view = new PurchaseDetailsViewStub();

        purchaseDAO = new PurchaseDAOMemory();

        // Initialize the presenter with the initialized DAO
        presenter = new PurchaseDetailsPresenter(purchaseDAO);
        presenter.setView(view);
    }

    /**
     * Test case for setting a new view for the presenter.
     * Validates that the view is successfully set in the presenter.
     */
    @Test
    public void onSetView() {
        PurchaseDetailsView newView = new PurchaseDetailsViewStub();
        presenter.setView(newView);
        Assertions.assertEquals(newView, presenter.getView());
    }

    /**
     * Test case for setting an attached purchase in the presenter.
     * Validates that the attached purchase is correctly set.
     */
    @Test
    public void onSetPurchase() {
        presenter.setAttachedPurchase(2);
        Assertions.assertEquals(2, presenter.getAttachedPurchase().getPurchaseId());
    }

    /**
     * Test case for setting purchase details in the view.
     * Validates that the view displays the correct purchase details.
     */
    @Test
    public void onSetPurchaseDetails() {
        presenter.setAttachedPurchase(2);
        presenter.onSetPurchaseDetails();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

        Assertions.assertEquals("House Party", view.getPurchaseName());
        Assertions.assertEquals("Acharnon 40, Athens 11122", view.getPurchaseLocation());
        Assertions.assertEquals(now, view.getPurchaseDate());
        Assertions.assertEquals("40.00 EUR", view.getPurchaseTotalCost());
    }

    /**
     * Test case for setting the ticket count in the presenter.
     * Validates that the presenter correctly sets up tickets and aggregates them.
     */
    @Test
    public void onSetTicketCount() {
        presenter.setAttachedPurchase(10);

        TicketCategory ticketCategory = new TicketCategory();
        ticketCategory.setName(CategoryName.GENERAL);
        ticketCategory.setPrice(new Money(new BigDecimal(0.00), Currency.getInstance("EUR")));
        ticketCategory.setDescription("Free Admission");
        ticketCategory.setQuantity(150);

        TicketDiscount ticketDiscount = new TicketDiscount();
        ticketDiscount.setType(DiscountType.GENERAL);
        ticketDiscount.setPercentage(100.0);

        TicketKey key = new TicketKey(ticketDiscount, ticketCategory, new Money(new BigDecimal(0), Currency.getInstance("EUR")));
        HashMap<TicketKey, Integer> ticketCountMap = new HashMap<>();
        ticketCountMap.put(key, 2);

        ArrayList<Ticket> aggregatedList = new ArrayList<>();
        Ticket aggregatedTicket = new Ticket();
        aggregatedTicket.setEvent(presenter.getAttachedPurchase().getTickets().get(0).getEvent());
        aggregatedTicket.setTicketCategory(ticketCategory);
        aggregatedTicket.setTicketDiscount(ticketDiscount);
        aggregatedList.add(aggregatedTicket);

        presenter.onSetUpTickets();

        Assertions.assertEquals(1, presenter.getTicketCountMap().size());
        Assertions.assertEquals(ticketCountMap, presenter.getTicketCountMap());
        Assertions.assertEquals(1, presenter.getAggregatedTicketList().size());
        Assertions.assertEquals(aggregatedList, presenter.getAggregatedTicketList());
    }

    /**
     * Test case for showing purchased tickets in the view.
     * Validates that the presenter correctly triggers the view to display purchased tickets.
     */
    @Test
    public void onShowPurchasedTickets() {
        presenter.setAttachedPurchase(10);
        presenter.onSetUpTickets();
        presenter.onShowPurchasedTickets();
        Assertions.assertEquals(1, view.getShowPurchasedTicketsCount());
    }
}
