package com.example.eventhub.view.Account.Login;

import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.dao.OrganizerDAO;
import com.example.eventhub.dao.UserDAO;
import com.example.eventhub.memorydao.CustomerDAOMemory;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.memorydao.OrganizerDAOMemory;
import com.example.eventhub.memorydao.UserDAOMemory;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Test class for the {@link LoginPresenter} class.
 */
public class LoginPresenterTest {
    LoginPresenter presenter;
    LoginViewStub view;

    private UserDAO userDAO;
    private OrganizerDAO organizerDAO;
    private CustomerDAO customerDAO;

    /**
     * Set up method to initialize the test environment.
     */
    @Before
    public void setUp() {
        // Initializing data and setting up the presenter and view
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new LoginViewStub();

        userDAO = new UserDAOMemory();
        organizerDAO = new OrganizerDAOMemory();
        customerDAO = new CustomerDAOMemory();

        presenter = new LoginPresenter(userDAO, organizerDAO, customerDAO);
        presenter.setView(view);
    }

    /**
     * Test case for {@link LoginPresenter#setView(LoginView)}.
     * Tests if the presenter correctly sets the view.
     */
    @Test
    public void setView() {
        LoginView newView = new LoginViewStub();
        presenter.setView(newView);
        Assertions.assertEquals(newView, presenter.getView());
    }

    /**
     * Test case for {@link LoginPresenter#onSignUp()}.
     * Tests if the presenter correctly handles the sign-up action.
     */
    @Test
    public void onSignUp() {
        presenter.onSignUp();
        Assertions.assertEquals(1, view.getSignUpCount());
    }

    /**
     * Test case for {@link LoginPresenter#onAuthenticateUser()} with no input.
     * Tests if the presenter correctly handles authentication with no input.
     */
    @Test
    public void authenticateUserWithNoInput() {
        presenter.onAuthenticateUser();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill in all fields", view.getErrorMessage());
    }

    /**
     * Test case for {@link LoginPresenter#onAuthenticateUser()} with no email.
     * Tests if the presenter correctly handles authentication with no email input.
     */
    @Test
    public void authenticateUserWithNoEmail() {
        view.setEmail("");
        view.setPassword("password");

        presenter.onAuthenticateUser();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill in all fields", view.getErrorMessage());
    }

    /**
     * Test case for {@link LoginPresenter#onAuthenticateUser()} with no password.
     * Tests if the presenter correctly handles authentication with no password input.
     */
    @Test
    public void authenticateUserWithNoPassword() {
        view.setEmail("email@example.com");
        view.setPassword("");

        presenter.onAuthenticateUser();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill in all fields", view.getErrorMessage());
    }

    /**
     * Test case for {@link LoginPresenter#onAuthenticateUser()} with wrong credentials.
     * Tests if the presenter correctly handles authentication with wrong credentials.
     */
    @Test
    public void authenticateUserWithWrongCredentials() {
        view.setEmail("ippo@example.com");
        view.setPassword("12345678");

        presenter.onAuthenticateUser();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("No user found with these credentials", view.getErrorMessage());
    }

    /**
     * Test case for {@link LoginPresenter#onAuthenticateUser()} authenticating an organizer.
     * Tests if the presenter correctly handles authenticating an organizer.
     */
    @Test
    public void authenticateOrganizer() {
        // Initializing lines 95-96
        view.setEmail("johndoe@example.com");
        view.setPassword("12345678");

        presenter.onAuthenticateUser();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals("Redirecting to organizer home page with id 1.", view.getOrganizerFoundMessage());
    }

    /**
     * Test case for {@link LoginPresenter#onAuthenticateUser()} authenticating a customer.
     * Tests if the presenter correctly handles authenticating a customer.
     */
    @Test
    public void authenticateCustomer() {
        // Initializing lines 113-114
        view.setEmail("alice@example.com");
        view.setPassword("45678901");

        presenter.onAuthenticateUser();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals("Redirecting to customer home page with id 4.", view.getCustomerFoundMessage());
    }
}