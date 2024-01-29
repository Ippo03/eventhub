package com.example.eventhub.view.Account.Login;

/**
 * The LoginView interface represents the view for the login functionality in the application.
 * It defines methods to retrieve email and password input, trigger the sign-up process,
 * and display messages based on the outcome of login attempts.
 */
public interface LoginView {

    /**
     * Gets the email entered by the user.
     *
     * @return The email entered by the user.
     */
    String getEmail();

    /**
     * Gets the password entered by the user.
     *
     * @return The password entered by the user.
     */
    String getPassword();

    /**
     * Initiates the sign-up process.
     */
    void signUp();

    /**
     * Displays a message indicating that an organizer with the specified ID was found.
     *
     * @param organizerId The ID of the organizer found.
     */
    void showOrganizerFoundMessage(Integer organizerId);

    /**
     * Displays a message indicating that a customer with the specified ID was found.
     *
     * @param customerId The ID of the customer found.
     */
    void showCustomerFoundMessage(Integer customerId);

    /**
     * Displays an error message with the specified title and message.
     *
     * @param title   The title of the error message.
     * @param message The detailed error message.
     */
    void showErrorMessage(String title, String message);
}
