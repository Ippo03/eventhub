package com.example.eventhub.domain;

import com.example.eventhub.domain.Genre;
import com.example.eventhub.domain.Interest;
import com.example.eventhub.helper.InterestTestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Test class for the {@link Interest} domain class.
 */
public class InterestTest {
    Interest interest;
    Interest interest2;

    /**
     * Set up method to initialize test objects.
     */
    @Before
    public void setUp() {
        interest = InterestTestHelper.initCustomInterest(Genre.ART);
        interest2 = InterestTestHelper.initCustomInterest(Genre.EDUCATION);
    }

    /**
     * Test case for setting a null value for the interest name, expecting an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNullForName() {
        interest.setInterestName(null);
    }

    /**
     * Test case for setting the interest name.
     */
    @Test
    public void setInterestName() {
        interest.setInterestName(Genre.EDUCATION);
        Assertions.assertEquals(Genre.EDUCATION, interest.getInterestName());
    }

    /**
     * Test case for checking non-equality between two interests.
     */
    @Test
    public void testNotEquals() {
        Assertions.assertNotEquals(interest, interest2);
        Assertions.assertNotEquals(interest.hashCode(), interest2.hashCode());
    }

    /**
     * Test case for checking equality and hashCode consistency between two interests.
     */
    @Test
    public void testEqualsAndHashCode() {
        Interest interestCopy = InterestTestHelper.initCustomInterest(Genre.ART);
        Assertions.assertEquals(interestCopy, interest);
        Assertions.assertEquals(interestCopy.hashCode(), interest.hashCode());
    }
}