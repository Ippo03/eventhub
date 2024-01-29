package com.example.eventhub.domain;

import com.example.eventhub.domain.Purchase;
import com.example.eventhub.helper.EventTestHelper;
import com.example.eventhub.helper.PurchaseTestHelper;
import com.example.eventhub.helper.TicketTestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Test class for the {@link Purchase} domain class.
 */
public class PurchaseTest {
    Purchase purchase;

    /**
     * Set up method to initialize test objects.
     */
    @Before
    public void setUp() {
        purchase = PurchaseTestHelper.initPurchaseForEvent1();
    }

    /**
     * Test case for the default constructor of the {@link Purchase} class.
     */
    @Test
    public void testDefaultConstructor() {
        Purchase purchase = new Purchase();
        Assertions.assertEquals(purchase, purchase);
    }

    /**
     * Test case for setting a null value for the tickets in the purchase, expecting an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNullForTickets() {
        purchase.setTickets(null);
    }

    /**
     * Test case for setting tickets in the purchase.
     */
    @Test
    public void setTickets() {
        purchase.setTickets(TicketTestHelper.initArrayOfFourTickets(EventTestHelper.initEvent()));
        Assertions.assertEquals(TicketTestHelper.initArrayOfFourTickets(EventTestHelper.initEvent()), purchase.getTickets());
    }

    /**
     * Test case for creating a purchase with no tickets, expecting an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void purchaseWithNoTickets() {
        Purchase purchase = new Purchase(1, null);
    }

    /**
     * Test case for setting the date of the purchase.
     */
    @Test
    public void setDate() {
        LocalDateTime date = LocalDateTime.of(2023, 12, 12, 10, 12);
        purchase.setDate(date);
        Assertions.assertEquals(date, purchase.getDate());
    }

    /**
     * Test case for calculating the total cost of the purchase.
     */
    @Test
    public void calculateTotalCost() {
        Assertions.assertEquals(new BigDecimal(200).setScale(2, BigDecimal.ROUND_HALF_UP), purchase.getCost().getAmount());
    }

    /**
     * Test case for getting the event associated with the purchase.
     */
    @Test
    public void getEvent() {
        Assertions.assertEquals(EventTestHelper.initEvent(), purchase.getEvent());
    }
}