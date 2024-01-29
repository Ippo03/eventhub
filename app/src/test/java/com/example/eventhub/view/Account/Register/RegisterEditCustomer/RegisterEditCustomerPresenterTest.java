package com.example.eventhub.view.Account.Register.RegisterEditCustomer;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.dao.UserDAO;
import com.example.eventhub.domain.Customer;
import com.example.eventhub.domain.Genre;
import com.example.eventhub.domain.Interest;
import com.example.eventhub.helper.AddressTestHelper;
import com.example.eventhub.helper.InterestTestHelper;
import com.example.eventhub.memorydao.CustomerDAOMemory;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.memorydao.UserDAOMemory;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.Set;

/**
 * Test class for the {@link RegisterEditCustomerPresenter} class.
 */
public class RegisterEditCustomerPresenterTest {
    RegisterEditCustomerPresenter presenter;
    RegisterEditCustomerViewStub view;

    private UserDAO userDAO;
    private CustomerDAO customerDAO;

    private Address address;
    private Set<Interest> interests = new HashSet<>();

    /**
     * Set up method to initialize the test environment.
     */
    @Before
    public void setUp() {
        // Initializing data and setting up the presenter and view
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        view = new RegisterEditCustomerViewStub();

        userDAO = new UserDAOMemory();
        customerDAO = new CustomerDAOMemory();

        address = AddressTestHelper.initAddress1();
        interests = InterestTestHelper.initSetOfTwoInterests(Genre.CINEMA, Genre.BUSINESS);

        presenter = new RegisterEditCustomerPresenter(view, userDAO, customerDAO);
    }

    /**
     * Test case for {@link RegisterEditCustomerPresenter#onSaveCustomer()} with empty field.
     * Tests if the presenter correctly handles registration with an empty field.
     */
    @Test
    public void onRegisterWithEmptyField() {
        // Setting up customer information
        view.setCustomerLastName("Pantelidis");
        view.setCustomerGender("male");
        view.setCustomerAge(20);
        view.setCustomerAddress(address);
        view.setCustomerEmail("ippo@example.com");
        view.setCustomerPassword("12345678");
        view.setCustomerInterests(interests);

        // Performing the registration
        presenter.onSaveCustomer();

        // Assertions for error handling
        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill all the fields.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for {@link RegisterEditCustomerPresenter#onSaveCustomer()} with an invalid first name (not alphabetic).
     * Tests if the presenter correctly handles registration with an invalid first name.
     */
    @Test
    public void onRegisterInvalidFirstName1() {
        // Setting up customer information with an invalid first name
        view.setCustomerFirstName("Ippo1");
        view.setCustomerLastName("Pantelidis");
        view.setCustomerGender("male");
        view.setCustomerAge(20);
        view.setCustomerAddress(address);
        view.setCustomerEmail("ippo@example.com");
        view.setCustomerPassword("12345678");
        view.setCustomerInterests(interests);

        // Performing the registration
        presenter.onSaveCustomer();

        // Assertions for error handling
        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill 4 to 15 characters in firstname.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for {@link RegisterEditCustomerPresenter#onSaveCustomer()} with an invalid first name (short length).
     * Tests if the presenter correctly handles registration with an invalid first name.
     */
    @Test
    public void onRegisterInvalidFirstName2() {
        // Setting up customer information with an invalid first name
        view.setCustomerFirstName("Ip");
        view.setCustomerLastName("Pantelidis");
        view.setCustomerGender("male");
        view.setCustomerAge(20);
        view.setCustomerAddress(address);
        view.setCustomerEmail("ippo@example.com");
        view.setCustomerPassword("12345678");
        view.setCustomerInterests(interests);

        // Performing the registration
        presenter.onSaveCustomer();

        // Assertions for error handling
        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill 4 to 15 characters in firstname.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for {@link RegisterEditCustomerPresenter#onSaveCustomer()} with an invalid last name (not alphabetic).
     * Tests if the presenter correctly handles registration with an invalid last name.
     */
    @Test
    public void onRegisterInvalidLastName1() {
        view.setCustomerFirstName("Ippokratis");
        view.setCustomerLastName("Pan1");
        view.setCustomerGender("male");
        view.setCustomerAge(20);
        view.setCustomerAddress(address);
        view.setCustomerEmail("ippo@example.com");
        view.setCustomerPassword("12345678");
        view.setCustomerInterests(interests);

        presenter.onSaveCustomer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill 4 to 15 characters in lastname.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for {@link RegisterEditCustomerPresenter#onSaveCustomer()} with an invalid last name (short length).
     * Tests if the presenter correctly handles registration with aa invalid last name.
     */
    @Test
    public void onRegisterInvalidLastName2() {
        view.setCustomerFirstName("Ippokratis");
        view.setCustomerLastName("Pa");
        view.setCustomerGender("male");
        view.setCustomerAge(20);
        view.setCustomerAddress(address);
        view.setCustomerEmail("ippo@example.com");
        view.setCustomerPassword("12345678");
        view.setCustomerInterests(interests);

        presenter.onSaveCustomer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill 4 to 15 characters in lastname.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }


    /**
     * Test case for {@link RegisterEditCustomerPresenter#onSaveCustomer()} with an invalid age.
     * Tests if the presenter correctly handles registration with a negative age.
     */
    @Test
    public void onRegisterInvalidAge() {
        view.setCustomerFirstName("Ippokratis");
        view.setCustomerLastName("Pantelidis");
        view.setCustomerGender("male");
        view.setCustomerAge(-1);
        view.setCustomerAddress(address);
        view.setCustomerEmail("ippo@example.com");
        view.setCustomerPassword("12345678");
        view.setCustomerInterests(interests);

        presenter.onSaveCustomer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please select a non-negative age.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for {@link RegisterEditCustomerPresenter#onSaveCustomer()} with an invalid address (invalid street).
     * Tests if the presenter correctly handles registration with an invalid address.
     */
    @Test
    public void onRegisterInvalidAddress1() {
        Address address = new Address("Kifisias10", 100, "Athens", "12345", "Greece");
        view.setCustomerFirstName("Ippokratis");
        view.setCustomerLastName("Pantelidis");
        view.setCustomerGender("male");
        view.setCustomerAge(20);
        view.setCustomerAddress(address);
        view.setCustomerEmail("ippo@example.com");
        view.setCustomerPassword("12345678");
        view.setCustomerInterests(interests);

        presenter.onSaveCustomer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill all the fields correctly in address.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for {@link RegisterEditCustomerPresenter#onSaveCustomer()} with an invalid address (negative street number).
     * Tests if the presenter correctly handles registration with an invalid address.
     */
    @Test
    public void onRegisterInvalidAddress2() {
        Address address = new Address("Kifisias", -1, "Athens", "12345", "Greece");
        view.setCustomerFirstName("Ippokratis");
        view.setCustomerLastName("Pantelidis");
        view.setCustomerGender("male");
        view.setCustomerAge(20);
        view.setCustomerAddress(address);
        view.setCustomerEmail("ippo@example.com");
        view.setCustomerPassword("12345678");
        view.setCustomerInterests(interests);

        presenter.onSaveCustomer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill all the fields correctly in address.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for {@link RegisterEditCustomerPresenter#onSaveCustomer()} with an invalid address (invalid city name).
     * Tests if the presenter correctly handles registration with an invalid address.
     */
    @Test
    public void onRegisterInvalidAddress3() {
        Address address = new Address("Kifisias", 100, "Athens1", "12345", "Greece");
        view.setCustomerFirstName("Ippokratis");
        view.setCustomerLastName("Pantelidis");
        view.setCustomerGender("male");
        view.setCustomerAge(20);
        view.setCustomerAddress(address);
        view.setCustomerEmail("ippo@example.com");
        view.setCustomerPassword("12345678");
        view.setCustomerInterests(interests);

        presenter.onSaveCustomer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill all the fields correctly in address.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for {@link RegisterEditCustomerPresenter#onSaveCustomer()} with an invalid email.
     * Tests if the presenter correctly handles registration with an invalid email format.
     */
    @Test
    public void onRegisterInvalidEmail() {
        view.setCustomerFirstName("Ippokratis");
        view.setCustomerLastName("Pantelidis");
        view.setCustomerGender("male");
        view.setCustomerAge(20);
        view.setCustomerAddress(address);
        view.setCustomerEmail("ippoemail.com");
        view.setCustomerPassword("12345678");
        view.setCustomerInterests(interests);

        presenter.onSaveCustomer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please select a valid email.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippoexample.com"));
    }

    /**
     * Test case for {@link RegisterEditCustomerPresenter#onSaveCustomer()} with an already existing email and null customer.
     * Tests if the presenter correctly handles registration with an email that already exists in the database.
     */
    @Test
    public void onRegisterAlreadyExistingEmailWithNullCustomer() {
        view.setCustomerFirstName("Ippokratis");
        view.setCustomerLastName("Pantelidis");
        view.setCustomerGender("male");
        view.setCustomerAge(20);
        view.setCustomerAddress(address);
        view.setCustomerEmail("bob@example.com");
        view.setCustomerPassword("12345678");
        view.setCustomerInterests(interests);

        presenter.onSaveCustomer();

        Assertions.assertNotNull(customerDAO.findCustomerWithAlreadyExistingEmail(new Customer(view.getCustomerEmail())));
        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("This email already exists. Try again!", view.getErrorMessage());
    }

    /**
     * Test case for {@link RegisterEditCustomerPresenter#onSaveCustomer()} with an invalid password.
     * Tests if the presenter correctly handles registration with an invalid password length.
     */
    @Test
    public void onRegisterInvalidPassword() {
        view.setCustomerFirstName("Ippokratis");
        view.setCustomerLastName("Pantelidis");
        view.setCustomerGender("male");
        view.setCustomerAge(20);
        view.setCustomerAddress(address);
        view.setCustomerEmail("jakesmith@example.com");
        view.setCustomerPassword("1234567");
        view.setCustomerInterests(interests);

        presenter.onSaveCustomer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill up to 8 characters in password.", view.getErrorMessage());
        Assertions.assertNotNull(userDAO.findByEmail("jakesmith@example.com"));
    }

    /**
     * Test case for {@link RegisterEditCustomerPresenter#onSaveCustomer()} with an attempt to update a customer
     * using an email that already exists in the database.
     * Tests if the presenter correctly handles the update with an existing email.
     */
    @Test
    public void onUpdateWithAlreadyExistingEmail() {
        view.setAttachedCustomerId(4);
        presenter = new RegisterEditCustomerPresenter(view, userDAO, customerDAO);

        Set<Interest> interests = new HashSet<>();
        interests.add(new Interest(Genre.MUSIC));

        view.setCustomerFirstName("Ippokratis");
        view.setCustomerLastName("Pantelidis");
        view.setCustomerGender("male");
        view.setCustomerAge(20);
        view.setCustomerAddress(address);
        view.setCustomerEmail("bob@example.com");
        view.setCustomerPassword("12345678");
        view.setCustomerInterests(interests);

        presenter.onSaveCustomer();

        Assertions.assertNotNull(customerDAO.findCustomerWithAlreadyExistingEmail(customerDAO.find(4)));
        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("This email already exists. Try again!", view.getErrorMessage());
    }

    /**
     * Test case for {@link RegisterEditCustomerPresenter#onSaveCustomer()} with a successful update of customer information.
     * Tests if the presenter correctly handles the update and provides the appropriate success message.
     */
    @Test
    public void onUpdateCustomer() {
        view.setAttachedCustomerId(4);
        presenter = new RegisterEditCustomerPresenter(view, userDAO, customerDAO);

        Set<Interest> interests = new HashSet<>();
        interests.add(new Interest(Genre.MUSIC));

        view.setCustomerFirstName("Ippokratis");
        view.setCustomerLastName("Pantelidis");
        view.setCustomerGender("male");
        view.setCustomerAge(20);
        view.setCustomerAddress(address);
        view.setCustomerEmail("ippo@example.com");
        view.setCustomerPassword("12345678");
        view.setCustomerInterests(interests);

        presenter.onSaveCustomer();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals("Your account has been updated successfully!", view.getSuccessfulUpdateMessage());
        Assertions.assertNotNull(userDAO.findByEmail("ippo@example.com"));
        Assertions.assertEquals(1, view.getHideLoginPromptCount());
    }

    /**
     * Test case for {@link RegisterEditCustomerPresenter#onSaveCustomer()} when successfully moving to the Terms and Conditions screen.
     * Tests if the presenter correctly handles the move to the Terms and Conditions screen upon a successful registration.
     */
    @Test
    public void onSuccessfulMoveToTermsAndConditions() {
        Set<Interest> interests = new HashSet<>();
        interests.add(new Interest(Genre.MUSIC));

        view.setCustomerFirstName("Ippokratis");
        view.setCustomerLastName("Pantelidis");
        view.setCustomerGender("male");
        view.setCustomerAge(20);
        view.setCustomerAddress(address);
        view.setCustomerEmail("ippo@example.com");
        view.setCustomerPassword("12345678");
        view.setCustomerInterests(interests);

        presenter.onSaveCustomer();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(1, view.getTermsAndConditionsCount());
        Assertions.assertEquals(1, view.getMultiSpinnerCount());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for {@link RegisterEditCustomerPresenter#onLogin()}.
     * Tests if the presenter correctly handles the login action.
     */
    @Test
    public void onLogin() {
        presenter.onLogin();

        Assertions.assertEquals(1, view.getLoginCount());
    }

    /**
     * Test case for {@link RegisterEditCustomerPresenter#onBack()}.
     * Tests if the presenter correctly handles the back action.
     */
    @Test
    public void onBack() {
        presenter.onBack();

        Assertions.assertEquals(1, view.getBackCount());
    }
}