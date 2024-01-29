package com.example.eventhub.domain;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.domain.Customer;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.EventType;
import com.example.eventhub.domain.Genre;
import com.example.eventhub.domain.Review;
import com.example.eventhub.domain.Ticket;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;
import com.example.eventhub.helper.AddressTestHelper;
import com.example.eventhub.helper.CustomerTestHelper;
import com.example.eventhub.helper.EventTestHelper;
import com.example.eventhub.helper.PurchaseTestHelper;
import com.example.eventhub.helper.ReviewTestHelper;
import com.example.eventhub.helper.TicketCategoryTestHelper;
import com.example.eventhub.helper.TicketDiscountTestHelper;
import com.example.eventhub.helper.TicketTestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Test class for the {@link Event} domain class.
 */
public class EventTest {
    Event event;
    Review review;
    TicketCategory ticketCategory1;
    TicketCategory ticketCategory2;
    TicketDiscount ticketDiscount1;
    TicketDiscount ticketDiscount2;

    /**
     * Set up method to initialize test objects.
     */
    @Before
    public void setUp() {
        review = ReviewTestHelper.initReview();
        event = EventTestHelper.initEvent();
        ticketCategory1 = TicketCategoryTestHelper.initTicketCategory1();
        ticketCategory2 = TicketCategoryTestHelper.initTicketCategory2();
        ticketDiscount1 = TicketDiscountTestHelper.initTicketDiscount1();
        ticketDiscount2 = TicketDiscountTestHelper.initTicketDiscount2();
    }

    /**
     * Test case for setting the name of the event.
     */
    @Test
    public void setName() {
        event.setName("PopParty");
        Assertions.assertEquals("PopParty", event.getName());
    }

    /**
     * Test case for setting the location of the event.
     */
    @Test
    public void setLocation() {
        Address eventAddress = AddressTestHelper.initAddress2();
        event.setLocation(eventAddress);
        Assertions.assertEquals(eventAddress, event.getLocation());
    }

    /**
     * Test case for setting the date and time of the event.
     */
    @Test
    public void setDate() {
        LocalDateTime eventDateTime = LocalDateTime.of(2024, 06, 04, 12, 0);
        event.setDate(eventDateTime);
        Assertions.assertEquals(eventDateTime, event.getDate());
    }

    /**
     * Test case for setting the genre of the event.
     */
    @Test
    public void setGenre() {
        event.setGenre(Genre.EDUCATION);
        Assertions.assertEquals(Genre.EDUCATION, event.getGenre());
    }

    /**
     * Test case for setting the type of the event.
     */
    @Test
    public void setType() {
        event.setType(EventType.FREE);
        Assertions.assertEquals(EventType.FREE, event.getType());
    }

    /**
     * Test case for calculating the average rating of the event with no reviews.
     */
    @Test
    public void calculateAverageRatingWithNoReviews() {
        Assertions.assertEquals(0, event.getAverageRating());
    }

    /**
     * Test case for calculating the average rating of the event with reviews.
     */
    @Test
    public void calculateAverageRating() {
        Event event = EventTestHelper.initEventWithReviews();
        Assertions.assertEquals(7.2, event.getAverageRating());
    }

    /**
     * Test case for checking if the event is matching with a null customer.
     */
    @Test
    public void isMatchingWithNullCustomer() {
        Assertions.assertTrue(!event.isMatchingWithCustomer(null));
    }

    /**
     * Test case for checking if the event is matching with a customer.
     */
    @Test
    public void isMatchingWithCustomer() {
        Customer customer = CustomerTestHelper.initCustomerWithTwoInterests();
        Assertions.assertTrue(event.isMatchingWithCustomer(customer));
    }

    /**
     * Test case for checking if the event is not matching with a customer.
     */
    @Test
    public void isNotMatchingWithCustomer() {
        Customer customer = CustomerTestHelper.initCustomerWithOneInterest();
        Assertions.assertTrue(!event.isMatchingWithCustomer(customer));
    }

    /**
     * Test case for validating tickets of categories and returning the matched category.
     */
    @Test
    public void validateTicketsOfCategories() {
        ArrayList<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < 151; i++) {
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setTicketCategory(TicketCategoryTestHelper.initTicketCategory1());
            ticket.setTicketDiscount(TicketDiscountTestHelper.initTicketDiscount1());
            tickets.add(ticket);
        }

        HashMap<TicketCategory, Integer> categoriesMap = new HashMap<>();
        categoriesMap.put(TicketCategoryTestHelper.initTicketCategory1(), 100);
        event.setTicketCategoryCountMap();

        TicketCategory ticketCategory = event.validateTicketsOfCategories(tickets);

        Assertions.assertEquals(ticketCategory1, ticketCategory);
    }

    /**
     * Test case for validating tickets of categories when no matching category is found.
     */
    @Test
    public void validateTicketsOfCategoriesNotFound() {
        TicketCategory ticketCategory = event.validateTicketsOfCategories(TicketTestHelper.initArrayOfFourTickets(event));

        Assertions.assertNull(ticketCategory);
    }

    /**
     * Test case for adding a review to the event.
     */
    @Test(expected = IllegalArgumentException.class)
    public void addNullForReview() {
        event.addReview(null);
    }

    /**
     * Test case for adding a review to the event.
     */
    @Test
    public void addReview() {
        event.addReview(review);
        Assertions.assertEquals(1, event.getReviews().size());
        Assertions.assertTrue(event.getReviews().contains(review));
    }

    /**
     * Test case for removing a null review from the event.
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeNullForReview() {
        event.removeReview(null);
    }

    /**
     * Test case for attempting to remove a non-existing review from the event.
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeNonExistingReview() {
        event.addReview(review);
        event.removeReview(new Review(3, 10, "Great"));
    }

    /**
     * Test case for removing a review from the event.
     */
    @Test
    public void removeReview() {
        event.addReview(review);
        event.removeReview(review);
        Assertions.assertEquals(0, event.getReviews().size());
    }

    /**
     * Test case for adding a null ticket category to the event.
     */
    @Test(expected = IllegalArgumentException.class)
    public void addNullForTicketCategory() {
        event.addTicketCategory(null);
        Assertions.assertEquals(0, event.getTicketCategories().size());
    }

    /**
     * Test case for adding a ticket category to the event.
     */
    @Test
    public void addTicketCategory() {
        event.addTicketCategory(ticketCategory1);
        Assertions.assertEquals(3, event.getTicketCategories().size());
        Assertions.assertTrue(event.getTicketCategories().contains(ticketCategory1));
    }

    /**
     * Test case for removing a null ticket category from the event.
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeNullForTicketCategory() {
        event.removeTicketCategory(null);
    }

    /**
     * Test case for attempting to remove a non-existing ticket category from the event.
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeNonExistingTicketCategory() {
        TicketCategory ticketCategory3 = TicketCategoryTestHelper.initTicketCategory3();
        event.removeTicketCategory(ticketCategory3);
    }

    /**
     * Test case for removing a ticket category from the event.
     */
    @Test
    public void removeTicketCategory() {
        event.addTicketCategory(ticketCategory1);
        event.removeTicketCategory(ticketCategory1);
        Assertions.assertEquals(2, event.getTicketCategories().size());
    }

    /**
     * Test case for adding a null ticket discount to the event.
     */
    @Test(expected = IllegalArgumentException.class)
    public void addNullForTicketDiscount() {
        event.addTicketDiscount(null);
        Assertions.assertEquals(0, event.getTicketDiscounts().size());
    }

    /**
     * Test case for adding a ticket discount to the event.
     */
    @Test
    public void addTicketDiscount() {
        event.addTicketDiscount(ticketDiscount1);
        Assertions.assertEquals(3, event.getTicketDiscounts().size());
        Assertions.assertTrue(event.getTicketDiscounts().contains(ticketDiscount1));
    }

    /**
     * Test case for removing a null ticket discount from the event.
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeNullForTicketDiscount() {
        event.removeTicketDiscount(null);
    }

    /**
     * Test case for attempting to remove a non-existing ticket discount from the event.
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeNonExistingTicketDiscount() {
        TicketDiscount ticketDiscount3 = TicketDiscountTestHelper.initTicketDiscount3();
        event.removeTicketDiscount(ticketDiscount3);
    }

    /**
     * Test case for removing a ticket discount from the event.
     */
    @Test
    public void removeTicketDiscount() {
        event.addTicketDiscount(ticketDiscount1);
        event.removeTicketDiscount(ticketDiscount1);
        Assertions.assertEquals(2, event.getTicketDiscounts().size());
    }

    /**
     * Test case for calculating the event capacity.
     */
    @Test
    public void calculateEventCapacity() {
        event.setTicketCategories(TicketCategoryTestHelper.initArrayOfTicketCategories());
        event.calculateEventCapacity();
        Assertions.assertEquals(200, event.getEventCapacity());
    }

    /**
     * Test case for calculating the total number of tickets sold for the event.
     */
    @Test
    public void calculateTicketsSold() {
        PurchaseTestHelper.purchaseScenario(event, ticketCategory1, ticketCategory2, ticketDiscount1, ticketDiscount2);
        event.calculateAvailableTickets();
        Assertions.assertEquals(4, event.getTicketsSold());
    }

    /**
     * Test case for calculating the total number of tickets sold for the event with no ticket categories.
     */
    @Test
    public void calculateTicketsSoldWithNoTicketCategories() {
        event.setTicketCategories(new ArrayList<>());
        event.setTicketCategoryCountMap();
        Assertions.assertEquals(0, event.getTicketsSold());
    }

    /**
     * Test case for calculating the number of available tickets for the event.
     */
    @Test
    public void calculateAvailableTickets() {
        PurchaseTestHelper.purchaseScenario(event, ticketCategory1, ticketCategory2, ticketDiscount1, ticketDiscount2);
        event.calculateAvailableTickets();
        Assertions.assertEquals(196, event.getAvailableTickets());
    }

    /**
     * Test case for checking if there is a scheduling conflict between two events.
     */
    @Test
    public void conflictBetweenEvents() {
        Event event1 = EventTestHelper.initEvent();
        Event event2 = EventTestHelper.initEvent();
        Assertions.assertTrue(event1.conflictWith(event2));
    }

    /**
     * Test case for checking if there is no scheduling conflict between two events.
     */
    @Test
    public void noConflictBetweenEvents() {
        Event event2 = EventTestHelper.initEvent2();
        Assertions.assertFalse(event.conflictWith(event2));
    }

    /**
     * Test case for checking if two events are not equal.
     */
    @Test
    public void testNotEquals() {
        Address event2Address = new Address("Voulis", 20, "Athens", "12346", "Greece");
        LocalDateTime event2DateTime = LocalDateTime.of(2024, 06, 04, 12, 12);
        Event event2 = new Event(3, "AI Conference", event2Address, event2DateTime, Genre.EDUCATION, EventType.OPEN);
        Assertions.assertNotEquals(event, event2);
    }

    /**
     * Test case for checking if two events are equal and have the same hash code.
     */
    @Test
    public void testEqualsAndHashCode() {
        Event event2 = EventTestHelper.initEvent();
        Assertions.assertEquals(event, event2);
        Assertions.assertEquals(event.hashCode(), event2.hashCode());
    }
}