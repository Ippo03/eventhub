package com.example.eventhub.view.Account.Login;

import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.dao.OrganizerDAO;
import com.example.eventhub.dao.UserDAO;
import com.example.eventhub.domain.Customer;
import com.example.eventhub.domain.Organizer;

/**
 * The LoginPresenter class serves as the presenter for the login functionality.
 * It interacts with the associated view and data access objects (DAOs) to handle user authentication.
 */
public class LoginPresenter {
    private LoginView view;
    private UserDAO userDAO;
    private OrganizerDAO organizerDAO;
    private CustomerDAO customerDAO;

    private String emailInput;
    private String passwordInput;

    /**
     * Constructs a new LoginPresenter with the specified UserDAO, OrganizerDAO, and CustomerDAO.
     *
     * @param userDAO      The data access object for general user information.
     * @param organizerDAO The data access object for organizer information.
     * @param customerDAO  The data access object for customer information.
     */
    public LoginPresenter(UserDAO userDAO, OrganizerDAO organizerDAO, CustomerDAO customerDAO) {
        this.userDAO = userDAO;
        this.organizerDAO = organizerDAO;
        this.customerDAO = customerDAO;
    }

    /**
     * Gets the associated view with which the presenter communicates.
     *
     * @return The associated LoginView.
     */
    public LoginView getView() {
        return view;
    }

    /**
     * Sets the view for the presenter to communicate with.
     *
     * @param view The LoginView to be associated with the presenter.
     */
    public void setView(LoginView view) {
        this.view = view;
    }

    /**
     * Handles the authentication process when the user clicks the login button.
     * Retrieves user input, validates the fields, and checks for matching credentials in the DAOs.
     * Displays appropriate messages based on the authentication result.
     */
    public void onAuthenticateUser() {
        emailInput = view.getEmail();
        passwordInput = view.getPassword();

        if (emailInput.isEmpty() || passwordInput.isEmpty()) {
            view.showErrorMessage("Error", "Please fill in all fields");
            return;
        }

        Organizer organizer = organizerDAO.findByCredentials(emailInput, passwordInput);
        Customer customer = customerDAO.findByCredentials(emailInput, passwordInput);

        if (organizer != null) {
            view.showOrganizerFoundMessage(organizer.getId());
        } else if (customer != null) {
            view.showCustomerFoundMessage(customer.getId());
        } else {
            view.showErrorMessage("Error", "No user found with these credentials");
        }
    }

    /**
     * Handles the action when the user clicks the sign-up link.
     * Initiates the sign-up process by navigating to the registration activity.
     */
    public void onSignUp() {
        view.signUp();
    }
}
