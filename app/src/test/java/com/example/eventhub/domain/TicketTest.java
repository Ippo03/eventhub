package com.example.eventhub.domain;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.domain.CategoryName;
import com.example.eventhub.domain.Customer;
import com.example.eventhub.domain.DiscountType;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.Organizer;
import com.example.eventhub.domain.Ticket;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;
import com.example.eventhub.domain.TicketState;
import com.example.eventhub.helper.AddressTestHelper;
import com.example.eventhub.helper.CustomerTestHelper;
import com.example.eventhub.helper.EventTestHelper;
import com.example.eventhub.helper.TicketCategoryTestHelper;
import com.example.eventhub.helper.TicketDiscountTestHelper;
import com.example.eventhub.helper.TicketTestHelper;
import com.example.eventhub.util.Money;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Test class for the {@link Ticket} domain class.
 */
public class TicketTest {
    private Customer customer;
    private Organizer organizer;
    private Event event;
    private Ticket ticket1;
    private Ticket ticket2;
    private TicketCategory ticketCategory2;
    private TicketCategory ticketCategory3;
    private TicketDiscount ticketDiscount2;
    private TicketDiscount ticketDiscount3;

    /**
     * Set up initial objects before each test.
     */
    @Before
    public void setUp() {
        Address address3 = AddressTestHelper.initAddress3();
        customer = CustomerTestHelper.initCustomerWithTwoInterests();
        organizer = new Organizer(2, "Mr", "Bill", "email@example.com", 50, "Male", address3, "password", 123);
        event = EventTestHelper.initEvent();
        organizer.addEvent(event);
        event.setTicketCategories(TicketCategoryTestHelper.initArrayOfTicketCategories());
        event.setTicketDiscounts(TicketDiscountTestHelper.initArrayOfTicketDiscounts());
        ticket1 = TicketTestHelper.initTicket1(event);
        ticket2 = TicketTestHelper.initTicket2(event);
        ticketCategory2 = TicketCategoryTestHelper.initTicketCategory2();
        ticketDiscount2 = TicketDiscountTestHelper.initTicketDiscount2();
        ticketCategory3 = TicketCategoryTestHelper.initTicketCategory3();
        ticketDiscount3 = TicketDiscountTestHelper.initTicketDiscount3();
    }

    /**
     * Test case to get the category of the first ticket.
     */
    @Test
    public void getTicket1Category() {
        Assertions.assertEquals(CategoryName.GENERAL, ticket1.getTicketCategory().getName());
    }

    /**
     * Test case to get the category of the second ticket.
     */
    @Test
    public void getTicket2Category() {
        Assertions.assertEquals(CategoryName.VIP, ticket2.getTicketCategory().getName());
    }

    /**
     * Test case to get the discount of the first ticket.
     */
    @Test
    public void getTicket1Discount() {
        double discount = 0.0;
        Assertions.assertEquals(discount, ticket1.getTicketDiscount().getPercentage());
    }

    /**
     * Test case to get the discount of the second ticket.
     */
    @Test
    public void getTicket2Discount() {
        double discount = 1.0;
        Assertions.assertEquals(discount, ticket2.getTicketDiscount().getPercentage());
    }

    /**
     * Test case to get the price of the first ticket.
     */
    @Test
    public void getTicket1Price() {
        Money testPrice = new Money(new BigDecimal(50).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.getInstance("EUR"));
        Assertions.assertEquals(testPrice.getAmount(), ticket1.getTicketPrice().getAmount());
    }

    /**
     * Test case to get the price of the second ticket.
     */
    @Test
    public void getTicket2Price() {
        Money testPrice = new Money(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.getInstance("EUR"));
        Assertions.assertEquals(testPrice.getAmount(), ticket2.getTicketPrice().getAmount());
    }

    /**
     * Test case to get the price of a ticket.
     */
    @Test
    public void getTicketPrice() {
        Event event = EventTestHelper.initEvent();
        TicketCategory ticketCategory = new TicketCategory(1, CategoryName.GENERAL, new Money(new BigDecimal(20), Currency.getInstance("EUR")), "description", 100);
        TicketDiscount ticketDiscount = new TicketDiscount(1, DiscountType.GENERAL, 20);
        Ticket ticket = new Ticket(1, event, ticketCategory, ticketDiscount);
        Assertions.assertEquals(new Money(new BigDecimal(16), Currency.getInstance("EUR")), ticket.getTicketPrice());
    }

    /**
     * Test case for handling mismatch between ticket category and event category.
     */
    @Test(expected = IllegalArgumentException.class)
    public void notMatchesWithCategory() {
        Ticket ticket3 = TicketTestHelper.initTicket3(event);
        ticket3.categoryMatchWithEvent(ticketCategory3);
    }

    /**
     * Test case for handling mismatch between ticket discount and event discount.
     */
    @Test(expected = IllegalArgumentException.class)
    public void notMatchesWithDiscount() {
        Ticket ticket4 = TicketTestHelper.initTicket4(event);
        ticket4.discountMatchWithEvent(ticketDiscount3);
    }

    /**
     * Test case to ensure that a ticket matches both category and discount with the event.
     */
    @Test
    public void matchesWithBoth() {
        ticket2.categoryMatchWithEvent(ticketCategory2);
        ticket2.discountMatchWithEvent(ticketDiscount2);
        Assertions.assertTrue(ticket2.categoryMatchWithEvent(ticketCategory2));
        Assertions.assertTrue(ticket2.discountMatchWithEvent(ticketDiscount2));
    }

    /**
     * Test case for buying a ticket that is already bought.
     */
    @Test(expected = IllegalArgumentException.class)
    public void buyTicketBought() {
        ticket1.setTicketState(TicketState.BOUGHT);
        ticket1.buyTicket();
    }


    /**
     * Test case for buying a ticket that is already cancelled.
     */
    @Test(expected = IllegalArgumentException.class)
    public void buyTicketCancelled() {
        ticket1.setTicketState(TicketState.CANCELLED);
        ticket1.buyTicket();
    }

    /**
     * Test case for buying an available ticket.
     */
    @Test
    public void buyTicketAvailable() {
        ticket1.buyTicket();
        Assertions.assertEquals(TicketState.BOUGHT, ticket1.getTicketState());
    }

    /**
     * Test case for buying a reserved ticket.
     */
    @Test
    public void buyTicketReserved() {
        ticket1.setTicketState(TicketState.RESERVED);
        ticket1.buyTicket();
        Assertions.assertEquals(TicketState.BOUGHT, ticket1.getTicketState());
    }

    /**
     * Test case for reserving an available ticket.
     */
    @Test
    public void reserveTicketAvailable() {
        ticket1.reserveTicket();
        Assertions.assertEquals(TicketState.RESERVED, ticket1.getTicketState());
    }

    /**
     * Test case for reserving a ticket that is already bought.
     */
    @Test(expected = IllegalArgumentException.class)
    public void reserveTicketBought() {
        ticket1.setTicketState(TicketState.BOUGHT);
        ticket1.reserveTicket();
    }

    /**
     * Test case for reserving a ticket that is already cancelled.
     */
    @Test(expected = IllegalArgumentException.class)
    public void reserveTicketCancelled() {
        ticket1.setTicketState(TicketState.CANCELLED);
        ticket1.reserveTicket();
    }

    /**
     * Test case for cancelling a reserved ticket.
     */
    @Test
    public void cancelTicketReserved() {
        ticket1.setTicketState(TicketState.RESERVED);
        ticket1.cancelTicket();
        Assertions.assertEquals(TicketState.CANCELLED, ticket1.getTicketState());
    }

    /**
     * Test case for cancelling a bought ticket.
     */
    @Test
    public void cancelTicketBought() {
        ticket1.setTicketState(TicketState.BOUGHT);
        ticket1.cancelTicket();
        Assertions.assertEquals(TicketState.CANCELLED, ticket1.getTicketState());
    }

    /**
     * Test case for cancelling a ticket that is neither reserved nor bought.
     */
    @Test(expected = IllegalArgumentException.class)
    public void cancelTicketNotReservedOrBought() {
        ticket1.cancelTicket();
    }

    /**
     * Test case for checking inequality based on ticket categories.
     */
    @Test
    public void notEqualsCategories() {
        Assertions.assertNotEquals(TicketTestHelper.initTicket1(event), TicketTestHelper.initTicket21(event));
    }

    /**
     * Test case for checking inequality based on ticket discounts.
     */
    @Test
    public void notEqualsDiscounts() {
        Assertions.assertNotEquals(TicketTestHelper.initTicket1(event), TicketTestHelper.initTicket12(event));
    }

    /**
     * Test case for checking inequality based on both category and discount.
     */
    @Test
    public void notEqualsBoth() {
        Assertions.assertNotEquals(ticket1, ticket2);
    }

    /**
     * Test case for checking equality and hash code consistency.
     */
    @Test
    public void equalsAndHashCode() {
        Ticket ticket1 = TicketTestHelper.initTicket1(event);
        Assertions.assertEquals(ticket1, ticket1);
        Assertions.assertEquals(ticket1.hashCode(), ticket1.hashCode());
    }
}