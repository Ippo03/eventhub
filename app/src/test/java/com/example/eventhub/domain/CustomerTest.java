package com.example.eventhub.domain;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.domain.Customer;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.Genre;
import com.example.eventhub.domain.Interest;
import com.example.eventhub.domain.Purchase;
import com.example.eventhub.helper.AddressTestHelper;
import com.example.eventhub.helper.CustomerTestHelper;
import com.example.eventhub.helper.EventTestHelper;
import com.example.eventhub.helper.InterestTestHelper;
import com.example.eventhub.helper.PurchaseTestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Test class for the {@link Customer} domain class.
 */
public class CustomerTest {
    private Customer customer;
    private Event event1;
    private Event event2;
    private Interest interest;
    private Purchase purchase;

    /**
     * Set up initial objects before each test.
     */
    @Before
    public void setUp() {
        customer = CustomerTestHelper.initCustomerWithTwoInterests();
        event1 = EventTestHelper.initEvent();
        event2 = EventTestHelper.initEvent2();
        interest = InterestTestHelper.initCustomInterest(Genre.ART);
        purchase = PurchaseTestHelper.initPurchaseForEvent1();
    }

    /**
     * Test case for the default constructor of {@link Customer}.
     */
    @Test
    public void testDefaultConstructor() {
        Customer customer = new Customer();

        Assertions.assertEquals(null, customer.getId());
        Assertions.assertEquals(new HashSet<>(), customer.getInterests());
        Assertions.assertEquals(new ArrayList<>(), customer.getPurchases());
    }

    /**
     * Test case to ensure that an {@link IllegalArgumentException} is thrown when attempting to review a null event.
     */
    @Test(expected = IllegalArgumentException.class)
    public void canReviewNullEvent() {
        customer.canReview(null);
    }

    /**
     * Test case to verify that a customer cannot review an event for which they have not made a purchase.
     */
    @Test
    public void cannotReview() {
        customer.addPurchase(purchase);
        Assertions.assertFalse(customer.canReview(event2));
    }

    /**
     * Test case to verify that a customer can review an event for which they have made a purchase.
     */
    @Test
    public void canReview() {
        customer.addPurchase(purchase);
        Assertions.assertTrue(customer.canReview(event1));
    }

    /**
     * Test case to ensure that an {@link IllegalArgumentException} is thrown when attempting to add a null purchase.
     */
    @Test(expected = IllegalArgumentException.class)
    public void addNullForPurchase() {
        customer.addPurchase(null);
    }

    /**
     * Test case to verify that a purchase can be successfully added to a customer's list of purchases.
     */
    @Test
    public void addPurchase() {
        customer.addPurchase(purchase);
        Assertions.assertEquals(1, customer.getPurchases().size());
        Assertions.assertTrue(customer.getPurchases().contains(purchase));
    }

    /**
     * Test case to ensure that an {@link IllegalArgumentException} is thrown when attempting to remove a null purchase.
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeNullForPurchase() {
        customer.removePurchase(null);
    }

    /**
     * Test case to ensure that an {@link IllegalArgumentException} is thrown when attempting to remove a non-existing purchase.
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeNonExistingPurchase() {
        Purchase[] purchases = PurchaseTestHelper.initTwoPurchasesForEvent1();
        Purchase purchase2 = purchases[1];
        customer.addPurchase(purchase);
        customer.removePurchase(purchase2);
    }

    /**
     * Test case to verify that a purchase can be successfully removed from a customer's list of purchases.
     */
    @Test
    public void removePurchase() {
        customer.addPurchase(purchase);
        customer.removePurchase(purchase);
        Assertions.assertEquals(0, customer.getPurchases().size());
    }

    /**
     * Test case to ensure that an {@link IllegalArgumentException} is thrown when attempting to add a null interest.
     */
    @Test(expected = IllegalArgumentException.class)
    public void addNullForInterest() {
        customer.addInterest(null);
    }

    /**
     * Test case to ensure that an {@link IllegalArgumentException} is thrown when attempting to add an existing interest.
     */
    @Test(expected = IllegalArgumentException.class)
    public void addExistingInterest() {
        customer.addInterest(interest);
        customer.addInterest(interest);
    }

    /**
     * Test case to verify that an interest can be successfully added to a customer's list of interests.
     */
    @Test
    public void addInterest() {
        customer.addInterest(interest);
        Assertions.assertEquals(3, customer.getInterests().size());
        Assertions.assertTrue(customer.getInterests().contains(interest));
    }

    /**
     * Test case to ensure that an {@link IllegalArgumentException} is thrown when attempting to remove a null interest.
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeNullForInterest() {
        customer.removeInterest(null);
    }

    /**
     * Test case to ensure that an {@link IllegalArgumentException} is thrown when attempting to remove a non-existing interest.
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeNonExistingInterest() {
        customer.addInterest(interest);
        Interest interest2 = InterestTestHelper.initCustomInterest(Genre.SCIENCE);
        customer.removeInterest(interest2);
    }

    /**
     * Test case to verify that an interest can be successfully removed from a customer's list of interests.
     */
    @Test
    public void removeInterest() {
        customer.addInterest(interest);
        customer.removeInterest(interest);
        Assertions.assertEquals(2, customer.getInterests().size());
    }

    /**
     * Test case to change customer settings and verify the changes.
     */
    @Test
    public void changeCustomerSettings() {
        Address address2 = AddressTestHelper.initAddress2();
        Set<Interest> interests = InterestTestHelper.initSetOfTwoInterests(Genre.ART, Genre.SCIENCE);
        customer.changeCustomerSettings("Nikos", "Mitsakis", 30, "male", address2, "nick@example.com", "12456789", interests);
        Assertions.assertEquals("Nikos", customer.getFirstName());
        Assertions.assertEquals("Mitsakis", customer.getLastName());
        Assertions.assertEquals(30, customer.getAge());
        Assertions.assertEquals("male", customer.getGender());
        Assertions.assertEquals(address2, customer.getAddress());
        Assertions.assertEquals("nick@example.com", customer.getEmail());
        Assertions.assertEquals("12456789", customer.getPassword());
        Assertions.assertEquals(interests, customer.getInterests());
    }
}