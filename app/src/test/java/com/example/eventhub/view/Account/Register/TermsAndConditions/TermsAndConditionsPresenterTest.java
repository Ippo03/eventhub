package com.example.eventhub.view.Account.Register.TermsAndConditions;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.dao.OrganizerDAO;
import com.example.eventhub.dao.UserDAO;
import com.example.eventhub.domain.Genre;
import com.example.eventhub.domain.Interest;
import com.example.eventhub.helper.AddressTestHelper;
import com.example.eventhub.helper.InterestTestHelper;
import com.example.eventhub.memorydao.CustomerDAOMemory;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.memorydao.OrganizerDAOMemory;
import com.example.eventhub.memorydao.UserDAOMemory;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Unit tests for the TermsAndConditionsPresenter class.
 * These tests cover various scenarios related to user registration and agreement to terms and conditions.
 */
public class TermsAndConditionsPresenterTest {
    private TermsAndConditionsViewStub view;
    private TermsAndConditionsPresenter presenter;

    private UserDAO userDAO;
    private OrganizerDAO organizerDAO;
    private CustomerDAO customerDAO;

    private Address address;
    private Set<Interest> interests = new HashSet<>();

    /**
     * Set up method for initializing the test environment.
     * Initializes the view, DAOs, presenter, address, and interests for testing the TermsAndConditionsPresenter.
     */
    @Before
    public void setUp() {
        // Prepare test data using MemoryInitializer
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        // Initialize the view with a stub
        view = new TermsAndConditionsViewStub();

        // Initialize DAOs with in-memory implementations
        userDAO = new UserDAOMemory();
        organizerDAO = new OrganizerDAOMemory();
        customerDAO = new CustomerDAOMemory();

        // Initialize the presenter with the initialized DAOs
        presenter = new TermsAndConditionsPresenter(userDAO, customerDAO, organizerDAO);

        // Set the view for the presenter
        presenter.setView(view);

        // Initialize an address for testing
        address = AddressTestHelper.initAddress1();

        // Initialize a set of interests for testing
        interests = InterestTestHelper.initSetOfTwoInterests(Genre.CINEMA, Genre.MUSIC);
    }

    /**
     * Test case for {@link TermsAndConditionsPresenter#setView(TermsAndConditionsView)}.
     * Tests if the presenter correctly sets the view.
     */
    @Test
    public void onSetView() {
        TermsAndConditionsView newView = new TermsAndConditionsViewStub();
        presenter.setView(newView);
        Assertions.assertEquals(newView, presenter.getView());
    }

    /**
     * Test case for onRegister method when attempting to register
     * an organizer with a disagreement to the terms and conditions.
     * Tests if the presenter correctly handles the disagreement and provides the appropriate error message.
     */
    @Test
    public void onRegisterOrganizerWithDisagreement() {
        view.setFirstName("Ippokratis");
        view.setLastName("Pantelidis");
        view.setAge("21");
        view.setGender("male");
        view.setAddress(address);
        view.setEmail("ippo@example.com");
        view.setPassword("12345678");
        view.setSsn("123456789");

        view.setAgreement("Disagree");

        presenter.onRegister(view.getOrganizerDetails(), "organizer");

        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please agree to the terms and conditions.", view.getErrorMessage());
        Assertions.assertEquals(1, view.getErrorCount());
    }

    /**
     * Test case for onRegister method when attempting to register
     * a customer with a disagreement to the terms and conditions.
     * Tests if the presenter correctly handles the disagreement and provides the appropriate error message.
     */
    @Test
    public void onRegisterCustomerWithDisagreement() {
        view.setFirstName("Nikos");
        view.setLastName("Mitsakis");
        view.setAge("21");
        view.setGender("male");
        view.setAddress(address);
        view.setEmail("nick@example.com");
        view.setPassword("12345678");
        view.setInterests(interests);

        view.setAgreement("Disagree");

        presenter.onRegister(view.getCustomerDetails(), "customer");

        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please agree to the terms and conditions.", view.getErrorMessage());
        Assertions.assertEquals(1, view.getErrorCount());
    }

    /**
     * Test case for onRegister method when successfully registering an organizer.
     * Tests if the presenter correctly handles the registration and provides the appropriate success message.
     */
    @Test
    public void onSuccessfulRegisterOrganizer() {
        view.setFirstName("Ippokratis");
        view.setLastName("Pantelidis");
        view.setAge("21");
        view.setGender("male");
        view.setAddress(address);
        view.setEmail("ippo@example.com");
        view.setPassword("12345678");
        view.setSsn("123456789");

        view.setAgreement("Agree");

        presenter.onRegister(view.getOrganizerDetails(), "organizer");

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals("You have successfully registered as organizer. Please login to continue.", view.getSuccessfulRegistrationMessage());
        Assertions.assertNotNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for onRegister method when successfully registering a customer.
     * Tests if the presenter correctly handles the registration and provides the appropriate success message.
     */
    @Test
    public void onSuccessfulRegisterCustomer() {
        view.setFirstName("Nikos");
        view.setLastName("Mitsakis");
        view.setAge("21");
        view.setGender("male");
        view.setAddress(address);
        view.setEmail("nick@example.com");
        view.setPassword("12345678");
        view.setInterests(interests);

        view.setAgreement("Agree");

        presenter.onRegister(view.getCustomerDetails(), "customer");

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals("You have successfully registered as customer. Please login to continue.", view.getSuccessfulRegistrationMessage());
        Assertions.assertNotNull(userDAO.findByEmail("nick@example.com"));
    }
}