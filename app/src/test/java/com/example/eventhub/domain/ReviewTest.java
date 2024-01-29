package com.example.eventhub.domain;

import com.example.eventhub.domain.Customer;
import com.example.eventhub.domain.Review;
import com.example.eventhub.helper.CustomerTestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Test class for the {@link Review} domain class.
 */
public class ReviewTest {
    Review review;
    Customer customer;

    /**
     * Set up method to initialize test objects.
     */
    @Before
    public void setUp() {
        review = new Review(1, 5, "Good");
        customer = CustomerTestHelper.initCustomerWithTwoInterests();
    }

    /**
     * Test case for setting a null value for the review grade, expecting an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNullForGrade() {
        review.setGrade(null);
    }

    /**
     * Test case for setting a negative value for the review grade, expecting an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNegativeForGrade() {
        review.setGrade(-1);
    }

    /**
     * Test case for setting a value greater than ten for the review grade, expecting an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setMoreThanTenForGrade() {
        review.setGrade(11);
    }

    /**
     * Test case for setting a valid value for the review grade.
     */
    @Test
    public void setGrade() {
        review.setGrade(10);
        Assertions.assertEquals(10, review.getGrade());
    }

    /**
     * Test case for setting a null value for the review comment, expecting an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNullForComment() {
        review.setComment(null);
    }

    /**
     * Test case for setting an empty value for the review comment, expecting an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setEmptyForComment() {
        review.setComment("");
    }

    /**
     * Test case for setting a valid value for the review comment.
     */
    @Test
    public void setComment() {
        review.setComment("Excellent");
        Assertions.assertEquals("Excellent", review.getComment());
    }

    /**
     * Test case for setting a null value for the review's associated customer, expecting an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNullForCustomer() {
        review.setCustomer(null);
    }

    /**
     * Test case for setting a valid value for the review's associated customer.
     */
    @Test
    public void setCustomer() {
        review.setCustomer(customer);
        Assertions.assertEquals(customer, review.getCustomer());
    }
}