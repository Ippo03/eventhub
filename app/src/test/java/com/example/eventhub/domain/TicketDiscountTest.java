package com.example.eventhub.domain;

import com.example.eventhub.domain.DiscountType;
import com.example.eventhub.domain.TicketDiscount;
import com.example.eventhub.helper.TicketDiscountTestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Test class for the {@link TicketDiscount} domain class.
 */
public class TicketDiscountTest {
    TicketDiscount ticketDiscount;
    TicketDiscount ticketDiscount2;

    /**
     * Set up method to initialize test objects.
     */
    @Before
    public void setUp() {
        ticketDiscount = TicketDiscountTestHelper.initTicketDiscount1();
        ticketDiscount2 = TicketDiscountTestHelper.initTicketDiscount2();
    }

    /**
     * Test case for setting the type of a ticket discount.
     */
    @Test
    public void setType() {
        ticketDiscount.setType(DiscountType.CHILD);
        Assertions.assertEquals(DiscountType.CHILD, ticketDiscount.getType());
    }

    /**
     * Test case for setting a percentage less than zero, expecting an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setPercentageLessThanZero() {
        ticketDiscount.setPercentage(-1.0);
    }

    /**
     * Test case for setting a percentage greater than one, expecting an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setPercentageGreaterThanOne() {
        ticketDiscount.setPercentage(101.0);
    }

    /**
     * Test case for setting a valid percentage.
     */
    @Test
    public void setPercentage() {
        ticketDiscount.setPercentage(50.0);
        Assertions.assertEquals(0.5, ticketDiscount.getPercentage());
    }

    /**
     * Test case for checking inequality between two different ticket discounts.
     */
    @Test
    public void testNotEquals() {
        Assertions.assertNotEquals(ticketDiscount, ticketDiscount2);
    }

    /**
     * Test case for checking equality and hash code consistency between two identical ticket discounts.
     */
    @Test
    public void testEqualsAndHashCode() {
        TicketDiscount ticketDiscountCopy = TicketDiscountTestHelper.initTicketDiscount1();
        Assertions.assertEquals(ticketDiscount, ticketDiscountCopy);
        Assertions.assertEquals(ticketDiscount.hashCode(), ticketDiscountCopy.hashCode());
    }
}