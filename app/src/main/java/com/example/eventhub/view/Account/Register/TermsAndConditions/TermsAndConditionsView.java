package com.example.eventhub.view.Account.Register.TermsAndConditions;

/**
 * Interface for the view of the Terms and Conditions screen during user registration.
 */
public interface TermsAndConditionsView {

    /**
     * Get the user agreement text.
     *
     * @return The user agreement text.
     */
    String getAgreement();

    /**
     * Set the user agreement text on the view.
     *
     * @param agreement The user agreement text to set.
     */
    void setAgreement(String agreement);

    /**
     * Display a successful registration message on the view.
     *
     * @param id   The ID of the registered user.
     * @param type The type of the registered user (e.g., "organizer").
     */
    void showSuccessfulRegistrationMessage(int id, String type);

    /**
     * Display an error message on the view.
     *
     * @param title   The title of the error message.
     * @param message The error message to display.
     */
    void showErrorMessage(String title, String message);
}

