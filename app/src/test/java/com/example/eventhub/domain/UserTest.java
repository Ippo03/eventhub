package com.example.eventhub.domain;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.domain.User;
import com.example.eventhub.helper.AddressTestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Test class for the {@link User} domain class.
 */
public class UserTest {
    private User user;
    private Address address1;
    private Address address2;

    /**
     * Set up initial objects before each test.
     */
    @Before
    public void setUp() {
        address1 = AddressTestHelper.initAddress1();
        address2 = AddressTestHelper.initAddress2();
        user = new User(1, "Ippokratis", "Pantelidis", "ippo@example.com", 20, "Male", address1, "1234567890");
    }

    /**
     * Test case for the default constructor of {@link User}.
     */
    @Test
    public void testDefaultConstructor() {
        User user = new User("ippo@example.com");

        Assertions.assertEquals(null, user.getId());
        Assertions.assertEquals("ippo@example.com", user.getEmail());
    }

    /**
     * Test case to ensure that setting a null value for the first name throws an {@link IllegalArgumentException}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNullForName() {
        user.setFirstName(null);
    }

    /**
     * Test case to ensure that setting an empty value for the first name throws an {@link IllegalArgumentException}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setEmptyForName() {
        user.setFirstName("");
    }

    /**
     * Test case to set a valid first name for the user.
     */
    @Test
    public void setFirstName() {
        user.setFirstName("Nikos");
        Assertions.assertEquals("Nikos", user.getFirstName());
    }

    /**
     * Test case to ensure that setting a null value for the last name throws an {@link IllegalArgumentException}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNullForLastName() {
        user.setLastName(null);
    }

    /**
     * Test case to ensure that setting an empty value for the last name throws an {@link IllegalArgumentException}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setEmptyForLastName() {
        user.setLastName("");
    }

    /**
     * Test case to set a valid last name for the user.
     */
    @Test
    public void setLastName() {
        user.setLastName("Mitsakis");
        Assertions.assertEquals("Mitsakis", user.getLastName());
    }

    /**
     * Test case to ensure that setting a null value for the email throws an {@link IllegalArgumentException}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNullForEmail() {
        user.setEmail(null);
    }

    /**
     * Test case to ensure that setting an empty value for the email throws an {@link IllegalArgumentException}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setEmptyForEmail() {
        user.setEmail("");
    }

    /**
     * Test case to ensure that setting an invalid email throws an {@link IllegalArgumentException}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setInvalidEmail() {
        user.setEmail("nick");
    }

    /**
     * Test case to set a valid email for the user.
     */
    @Test()
    public void setEmail() {
        user.setEmail("nick@example.com");
        Assertions.assertEquals("nick@example.com", user.getEmail());
    }

    /**
     * Test case to ensure that setting a negative value for the age throws an {@link IllegalArgumentException}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNegativeForAge() {
        user.setAge(-1);
    }

    /**
     * Test case to set a valid age for the user.
     */
    @Test
    public void setAge() {
        user.setAge(30);
        Assertions.assertEquals(30, user.getAge());
    }

    /**
     * Test case to set a valid gender for the user.
     */
    @Test
    public void setGender() {
        user.setGender("Female");
        Assertions.assertEquals("Female", user.getGender());
    }

    /**
     * Test case to ensure that setting a null value for the address throws a {@link NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void setNullForAddress() {
        user.setAddress(null);
    }

    /**
     * Test case to ensure that setting an empty value for the address throws an {@link IllegalArgumentException}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setEmptyForAddress() {
        user.setAddress(new Address());
    }

    /**
     * Test case to set a valid address for the user.
     */
    @Test
    public void setAddress() {
        user.setAddress(address2);
        Assertions.assertEquals(address2, user.getAddress());
    }

    /**
     * Test case to ensure that setting a null value for the password throws an {@link IllegalArgumentException}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNullForPassword() {
        user.setPassword(null);
    }

    /**
     * Test case to ensure that setting an empty value for the password throws an {@link IllegalArgumentException}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setEmptyForPassword() {
        user.setPassword("");
    }

    /**
     * Test case to set a valid password for the user.
     */
    @Test
    public void setPassword() {
        user.setPassword("9876543210");
        Assertions.assertEquals("9876543210", user.getPassword());
    }

    /**
     * Test case to change user settings and verify the changes.
     */
    @Test
    public void changeUserSettings() {
        user.changeUserSettings("Nikos", "Mitsakis", 30, "male", address2, "nick@example.com", "12456789");
        Assertions.assertEquals("Nikos", user.getFirstName());
        Assertions.assertEquals("Mitsakis", user.getLastName());
        Assertions.assertEquals(30, user.getAge());
        Assertions.assertEquals("male", user.getGender());
        Assertions.assertEquals(address2, user.getAddress());
        Assertions.assertEquals("nick@example.com", user.getEmail());
        Assertions.assertEquals("12456789", user.getPassword());
    }
}
