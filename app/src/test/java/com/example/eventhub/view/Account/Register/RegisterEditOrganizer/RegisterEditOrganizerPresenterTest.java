package com.example.eventhub.view.Account.Register.RegisterEditOrganizer;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.dao.OrganizerDAO;
import com.example.eventhub.dao.UserDAO;
import com.example.eventhub.helper.AddressTestHelper;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.memorydao.OrganizerDAOMemory;
import com.example.eventhub.memorydao.UserDAOMemory;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Unit tests for the RegisterEditOrganizerPresenter class.
 * These tests cover various scenarios related to organizer registration and validation.
 */
public class RegisterEditOrganizerPresenterTest {
    RegisterEditOrganizerPresenter presenter;
    RegisterEditOrganizerViewStub view;

    private UserDAO userDAO;
    private OrganizerDAO organizerDAO;

    private Address address;

    /**
     * Set up method for initializing the test environment.
     * Initializes the view, DAOs, presenter, and address for testing the RegisterEditOrganizerPresenter.
     */
    @Before
    public void setUp() {
        // Prepare test data using MemoryInitializer
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        // Initialize the view with a stub
        view = new RegisterEditOrganizerViewStub();

        // Initialize DAOs with in-memory implementations
        userDAO = new UserDAOMemory();
        organizerDAO = new OrganizerDAOMemory();

        // Initialize the presenter with the initialized DAOs
        address = AddressTestHelper.initAddress1();

        // Set up the presenter with the initialized view and DAOs
        presenter = new RegisterEditOrganizerPresenter(view, userDAO, organizerDAO);
    }

    /**
     * Test case for saving an organizer with empty fields.
     * Verifies that the presenter handles the error and does not save the organizer.
     */
    @Test
    public void onRegisterWithEmptyField() {
        // Set up the view with empty fields
        view.setOrganizerLastName("Pantelidis");
        view.setOrganizerGender("male");
        view.setOrganizerAge(20);
        view.setOrganizerAddress(address);
        view.setOrganizerEmail("ippo@example.com");
        view.setOrganizerPassword("12345678");
        view.setOrganizerSsn(192837465);

        // Trigger the save organizer action
        presenter.onSaveOrganizer();

        // Verify the expected error handling
        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill all the fields.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for saving an organizer with invalid first name (not alphabetic).
     * Verifies that the presenter handles the error and does not save the organizer.
     */
    @Test
    public void onRegisterInvalidFirstName1() {
        view.setOrganizerFirstName("Ippo1");
        view.setOrganizerLastName("Pantelidis");
        view.setOrganizerGender("male");
        view.setOrganizerAge(20);
        view.setOrganizerAddress(address);
        view.setOrganizerEmail("ippo@example.com");
        view.setOrganizerPassword("12345678");
        view.setOrganizerSsn(192837465);

        presenter.onSaveOrganizer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill 4 to 15 characters in firstname.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for saving an organizer with invalid first name (short length).
     * Verifies that the presenter handles the error and does not save the organizer.
     */
    @Test
    public void onRegisterInvalidFirstName2() {
        view.setOrganizerFirstName("Ip");
        view.setOrganizerLastName("Pantelidis");
        view.setOrganizerGender("male");
        view.setOrganizerAge(20);
        view.setOrganizerAddress(address);
        view.setOrganizerEmail("ippo@example.com");
        view.setOrganizerPassword("12345678");
        view.setOrganizerSsn(192837465);

        presenter.onSaveOrganizer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill 4 to 15 characters in firstname.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for saving an organizer with invalid last name (not alphabetic).
     * Verifies that the presenter handles the error and does not save the organizer.
     */
    @Test
    public void onRegisterInvalidLastName1() {
        view.setOrganizerFirstName("Ippokratis");
        view.setOrganizerLastName("Pan1");
        view.setOrganizerGender("male");
        view.setOrganizerAge(20);
        view.setOrganizerAddress(address);
        view.setOrganizerEmail("ippo@example.com");
        view.setOrganizerPassword("12345678");
        view.setOrganizerSsn(192837465);

        presenter.onSaveOrganizer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill 4 to 15 characters in lastname.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for saving an organizer with invalid last name (short length).
     * Verifies that the presenter handles the error and does not save the organizer.
     */
    @Test
    public void onRegisterInvalidLastName2() {
        view.setOrganizerFirstName("Ippokratis");
        view.setOrganizerLastName("Pa");
        view.setOrganizerGender("male");
        view.setOrganizerAge(20);
        view.setOrganizerAddress(address);
        view.setOrganizerEmail("ippo@example.com");
        view.setOrganizerPassword("12345678");
        view.setOrganizerSsn(192837465);

        presenter.onSaveOrganizer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill 4 to 15 characters in lastname.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for saving an organizer with an invalid age (negative age).
     * Verifies that the presenter handles the error and does not save the organizer.
     */
    @Test
    public void onRegisterInvalidAge() {
        view.setOrganizerFirstName("Ippokratis");
        view.setOrganizerLastName("Pantelidis");
        view.setOrganizerGender("male");
        view.setOrganizerAge(-1);
        view.setOrganizerAddress(address);
        view.setOrganizerEmail("ippo@example.com");
        view.setOrganizerPassword("12345678");
        view.setOrganizerSsn(192837465);

        presenter.onSaveOrganizer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please select a non-negative age.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for saving an organizer with an invalid address (invalid street).
     * Verifies that the presenter handles the error and does not save the organizer.
     */
    @Test
    public void onRegisterInvalidAddress1() {
        Address address = new Address("Kifisias10", 100, "Athens", "12345", "Greece");
        view.setOrganizerFirstName("Ippokratis");
        view.setOrganizerLastName("Pantelidis");
        view.setOrganizerGender("male");
        view.setOrganizerAge(20);
        view.setOrganizerAddress(address);
        view.setOrganizerEmail("ippo@example.com");
        view.setOrganizerPassword("12345678");
        view.setOrganizerSsn(192837465);

        presenter.onSaveOrganizer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill all the fields correctly in address.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for saving an organizer with an invalid address (negative street number).
     * Verifies that the presenter handles the error and does not save the organizer.
     */
    @Test
    public void onRegisterInvalidAddress2() {
        Address address = new Address("Kifisias", -1, "Athens", "12345", "Greece");
        view.setOrganizerFirstName("Ippokratis");
        view.setOrganizerLastName("Pantelidis");
        view.setOrganizerGender("male");
        view.setOrganizerAge(20);
        view.setOrganizerAddress(address);
        view.setOrganizerEmail("ippo@example.com");
        view.setOrganizerPassword("12345678");
        view.setOrganizerSsn(192837465);

        presenter.onSaveOrganizer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill all the fields correctly in address.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for saving an organizer with an invalid address (invalid city).
     * Verifies that the presenter handles the error and does not save the organizer.
     */
    @Test
    public void onRegisterInvalidAddress3() {
        Address address = new Address("Kifisias", 100, "Athens1", "12345", "Greece");
        view.setOrganizerFirstName("Ippokratis");
        view.setOrganizerLastName("Pantelidis");
        view.setOrganizerGender("male");
        view.setOrganizerAge(20);
        view.setOrganizerAddress(address);
        view.setOrganizerEmail("ippo@example.com");
        view.setOrganizerPassword("12345678");
        view.setOrganizerSsn(192837465);

        presenter.onSaveOrganizer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill all the fields correctly in address.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for saving an organizer with an invalid email (no @).
     * Verifies that the presenter handles the error and does not save the organizer.
     */
    @Test
    public void onRegisterInvalidEmail() {
        view.setOrganizerFirstName("Ippokratis");
        view.setOrganizerLastName("Pantelidis");
        view.setOrganizerGender("male");
        view.setOrganizerAge(20);
        view.setOrganizerAddress(address);
        view.setOrganizerEmail("ippoemail.com");
        view.setOrganizerPassword("12345678");
        view.setOrganizerSsn(192837465);

        presenter.onSaveOrganizer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please select a valid email.", view.getErrorMessage());
        Assertions.assertNull(userDAO.findByEmail("ippoexample.com"));
    }

    /**
     * Test case for attempting to register an organizer with an email address that already exists.
     * Validates the error message and ensures the user is not registered.
     */
    @Test
    public void onRegisterAlreadyExistingEmail() {
        view.setOrganizerFirstName("Ippokratis");
        view.setOrganizerLastName("Pantelidis");
        view.setOrganizerGender("male");
        view.setOrganizerAge(20);
        view.setOrganizerAddress(address);
        view.setOrganizerEmail("jakesmith@example.com");
        view.setOrganizerPassword("12345678");
        view.setOrganizerSsn(192837465);

        presenter.onSaveOrganizer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("This email already exists. Try again!", view.getErrorMessage());
        Assertions.assertNotNull(userDAO.findByEmail("jakesmith@example.com"));
    }

    /**
     * Test case for attempting to register an organizer with an invalid password.
     * Validates the error message and ensures the user is not registered.
     */
    @Test
    public void onRegisterInvalidPassword() {
        view.setOrganizerFirstName("Ippokratis");
        view.setOrganizerLastName("Pantelidis");
        view.setOrganizerGender("male");
        view.setOrganizerAge(20);
        view.setOrganizerAddress(address);
        view.setOrganizerEmail("jakesmith@example.com");
        view.setOrganizerPassword("1234567");
        view.setOrganizerSsn(192837465);

        presenter.onSaveOrganizer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill up to 8 characters in password.", view.getErrorMessage());
        Assertions.assertNotNull(userDAO.findByEmail("jakesmith@example.com"));
    }

    /**
     * Test case for updating an organizer with an email address that already exists.
     * Validates the error message and ensures the user is not updated.
     */
    @Test
    public void onUpdateWithAlreadyExistingEmail() {
        view.setAttachedOrganizerId(1);
        presenter = new RegisterEditOrganizerPresenter(view, userDAO, organizerDAO);

        view.setOrganizerFirstName("Ippokratis");
        view.setOrganizerLastName("Pantelidis");
        view.setOrganizerGender("male");
        view.setOrganizerAge(20);
        view.setOrganizerAddress(address);
        view.setOrganizerEmail("jakesmith@example.com");
        view.setOrganizerPassword("12345678");
        view.setOrganizerSsn(192837465);

        presenter.onSaveOrganizer();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("This email already exists. Try again!", view.getErrorMessage());
        Assertions.assertNotNull(userDAO.findByEmail("jakesmith@example.com"));
    }

    /**
     * Test case for updating an organizer successfully.
     * Validates the success message and ensures the user is updated in the system.
     */
    @Test
    public void onUpdateOrganizer() {
        view.setAttachedOrganizerId(1);
        presenter = new RegisterEditOrganizerPresenter(view, userDAO, organizerDAO);

        view.setOrganizerFirstName("Ippokratis");
        view.setOrganizerLastName("Pantelidis");
        view.setOrganizerGender("male");
        view.setOrganizerAge(20);
        view.setOrganizerAddress(address);
        view.setOrganizerEmail("ippo@example.com");
        view.setOrganizerPassword("12345678");
        view.setOrganizerSsn(192837465);

        presenter.onSaveOrganizer();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals("Your account has been updated successfully!", view.getSuccessfulUpdateMessage());
        Assertions.assertNotNull(userDAO.findByEmail("ippo@example.com"));
        Assertions.assertEquals(1, view.getHideLoginPromptCount());
    }

    /**
     * Test case for successfully navigating to the terms and conditions screen after organizer registration/update.
     * Validates that no errors occur, and the user is not stored in the system.
     */
    @Test
    public void onSuccessfulMoveToTermsAndConditions() {
        view.setOrganizerFirstName("Ippokratis");
        view.setOrganizerLastName("Pantelidis");
        view.setOrganizerGender("male");
        view.setOrganizerAge(20);
        view.setOrganizerAddress(address);
        view.setOrganizerEmail("ippo@example.com");
        view.setOrganizerPassword("12345678");
        view.setOrganizerSsn(192837465);

        presenter.onSaveOrganizer();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals(1, view.getTermsAndConditionsCount());
        Assertions.assertNull(userDAO.findByEmail("ippo@example.com"));
    }

    /**
     * Test case for the login operation.
     * Validates that the login operation is triggered, ensuring the appropriate method is called.
     */
    @Test
    public void onLogin() {
        presenter.onLogin();

        Assertions.assertEquals(1, view.getLoginCount());
    }

    /**
     * Test case for navigating back within the application.
     * Validates that the back operation is triggered, ensuring the appropriate method is called.
     */
    @Test
    public void onBack() {
        presenter.onBack();

        Assertions.assertEquals(1, view.getBackCount());
    }
}