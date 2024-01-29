package com.example.eventhub.domain;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.Organizer;
import com.example.eventhub.helper.AddressTestHelper;
import com.example.eventhub.helper.EventTestHelper;
import com.example.eventhub.helper.ReviewTestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

/**
 * Test class for the {@link Organizer} domain class.
 */
public class OrganizerTest {
    Organizer organizer;
    Event event1;
    Event event2;

    /**
     * Set up method to initialize test objects.
     */
    @Before
    public void setUp() {
        Address organizerAddress = AddressTestHelper.initAddress1();
        organizer = new Organizer(1,"John", "Doe", "em@ail", 20, "Male", organizerAddress, "12345678", 123);
        event1 = EventTestHelper.initEvent();
        event2 = EventTestHelper.initEvent2();
    }

    /**
     * Test case for the default constructor of the {@link Organizer}.
     */
    @Test
    public void testDefaultConstructor() {
        Organizer organizer = new Organizer();
        Assertions.assertEquals(organizer, organizer);
    }

    /**
     * Test case for setting the Social Security Number (SSN) of the organizer.
     */
    @Test
    public void SetSsn() {
        organizer.setSsn(124);
        Assertions.assertEquals(124, organizer.getSsn());
    }

    /**
     * Test case for adding a null event, expecting an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void addNullForEvent() {
        organizer.addEvent(null);
    }

    /**
     * Test case for adding an event to the organizer's events list.
     */
    @Test
    public void addEvent() {
        organizer.addEvent(event1);
        Assertions.assertEquals(1, organizer.getEvents().size());
        Assertions.assertTrue(organizer.getEvents().contains(event1));
    }

    /**
     * Test case for removing a null event, expecting an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeNullForEvent() {
        organizer.removeEvent(null);
    }

    /**
     * Test case for removing a non-existing event, expecting an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeNonExistingEvent() {
        organizer.removeEvent(event1);
    }

    /**
     * Test case for removing an event from the organizer's events list.
     */
    @Test
    public void removeEvent() {
        organizer.addEvent(event1);
        organizer.removeEvent(event1);
        Assertions.assertEquals(0, organizer.getEvents().size());
    }

    /**
     * Test case for calculating the organizer's rating with no events.
     */
    @Test
    public void calculateRatingWithNoEvents() {
        organizer.calculateRating();
        Assertions.assertEquals(BigDecimal.ZERO, organizer.getRating());
    }

    /**
     * Test case for calculating the organizer's rating with events and reviews.
     */
    @Test
    public void calculateOrganizerRating() {
        organizer.addEvent(event1);
        organizer.addEvent(event2);
        ReviewTestHelper.addReviewsToEvents(event1, event2);
        organizer.calculateRating();
        Assertions.assertEquals(new BigDecimal(7.2).setScale(1, BigDecimal.ROUND_HALF_UP), organizer.getRating());
    }

    /**
     * Test case for updating the organizer's settings.
     */
    @Test
    public void updateOrganizerSettings() {
        Address address2 = AddressTestHelper.initAddress2();
        organizer.changeOrganizerSettings("Nikos", "Mitsakis", 30, "male", address2, "nick@example.com", "12456789", 123456789);
        Assertions.assertEquals("Nikos", organizer.getFirstName());
        Assertions.assertEquals("Mitsakis", organizer.getLastName());
        Assertions.assertEquals(30, organizer.getAge());
        Assertions.assertEquals("male", organizer.getGender());
        Assertions.assertEquals(address2, organizer.getAddress());
        Assertions.assertEquals("nick@example.com", organizer.getEmail());
        Assertions.assertEquals("12456789", organizer.getPassword());
        Assertions.assertEquals(123456789, organizer.getSsn());
    }
}