package com.example.eventhub.domain;

import com.example.eventhub.domain.CategoryName;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.helper.TicketCategoryTestHelper;
import com.example.eventhub.util.Money;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Test class for the {@link TicketCategory} domain class.
 */
public class TicketCategoryTest {
    TicketCategory ticketCategory;
    TicketCategory ticketCategory2;

    /**
     * Set up method to initialize test objects.
     */
    @Before
    public void setUp() {
        ticketCategory = TicketCategoryTestHelper.initTicketCategory1();
        ticketCategory2 = TicketCategoryTestHelper.initTicketCategory2();
    }

    /**
     * Test case for setting the name of a ticket category.
     */
    @Test
    public void setName() {
        ticketCategory.setName(CategoryName.VIP);
        Assertions.assertEquals(CategoryName.VIP, ticketCategory.getName());
    }

    /**
     * Test case for setting the price of a ticket category.
     */
    @Test
    public void setPrice() {
        ticketCategory.setPrice(new Money(new BigDecimal(100), Currency.getInstance("EUR")));
        Assertions.assertEquals(new Money(new BigDecimal(100), Currency.getInstance("EUR")), ticketCategory.getPrice());
    }

    /**
     * Test case for setting the description of a ticket category.
     */
    @Test
    public void setDescription() {
        ticketCategory.setDescription("VIP Admission");
        Assertions.assertEquals("VIP Admission", ticketCategory.getDescription());
    }

    /**
     * Test case for setting a negative quantity, expecting an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNegativeQuantity() {
        ticketCategory.setQuantity(-1);
    }

    /**
     * Test case for setting a valid quantity.
     */
    @Test
    public void setQuantity() {
        ticketCategory.setQuantity(100);
        Assertions.assertEquals(100, ticketCategory.getQuantity());
    }

    /**
     * Test case for getting the number of tickets sold in a ticket category.
     */
    @Test
    public void getTicketsSold() {
        Assertions.assertEquals(0, ticketCategory.getTicketsSold());
    }

    /**
     * Test case for checking inequality between two different ticket categories.
     */
    @Test
    public void testNotEquals() {
        Assertions.assertNotEquals(ticketCategory, ticketCategory2);
    }

    /**
     * Test case for checking equality and hash code consistency between two identical ticket categories.
     */
    @Test
    public void testEqualsAndHashCode() {
        TicketCategory ticketCategoryCopy = TicketCategoryTestHelper.initTicketCategory1();
        Assertions.assertEquals(ticketCategory, ticketCategoryCopy);
        Assertions.assertEquals(ticketCategory.hashCode(), ticketCategoryCopy.hashCode());
    }
}