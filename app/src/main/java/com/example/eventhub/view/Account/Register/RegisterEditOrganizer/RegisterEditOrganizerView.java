package com.example.eventhub.view.Account.Register.RegisterEditOrganizer;

import com.example.eventhub.contacts.Address;

import java.util.HashMap;

/**
 * This interface defines the contract for the view layer of the Register/Edit Organizer feature.
 * It includes methods to retrieve and set information related to organizer registration and editing.
 */
public interface RegisterEditOrganizerView {

    /**
     * Retrieves the attached organizer ID from the intent.
     *
     * @return The attached organizer ID or null if not found.
     */
    Integer getAttachedOrganizerId();

    /**
     * Sets the page title for organizer registration/editing.
     *
     * @param title The title to be set.
     */
    void setPageTitleToEdit(String title);

    /**
     * Sets the label for the registration/update button.
     *
     * @param label The label to be set.
     */
    void setButtonLabelToUpdate(String label);

    /**
     * Retrieves the organizer's first name from the UI.
     *
     * @return The organizer's first name.
     */
    String getOrganizerFirstName();

    /**
     * Retrieves the organizer's last name from the UI.
     *
     * @return The organizer's last name.
     */
    String getOrganizerLastName();

    /**
     * Retrieves the organizer's gender from the UI.
     *
     * @return The organizer's gender.
     */
    String getOrganizerGender();

    /**
     * Retrieves the organizer's age from the UI.
     *
     * @return The organizer's age.
     */
    String getOrganizerAge();

    /**
     * Retrieves the organizer's address from the UI.
     *
     * @return The organizer's address.
     */
    Address getOrganizerAddress();

    /**
     * Retrieves the organizer's email from the UI.
     *
     * @return The organizer's email.
     */
    String getOrganizerEmail();

    /**
     * Retrieves the organizer's password from the UI.
     *
     * @return The organizer's password.
     */
    String getOrganizerPassword();

    /**
     * Retrieves the organizer's social security number from the UI.
     *
     * @return The organizer's social security number.
     */
    String getOrganizerSsn();

    /**
     * Retrieves the organizer's information from the UI.
     *
     * @return A map containing organizer information.
     */
    HashMap<String, Object> getOrganizerInfo();

    /**
     * Sets the organizer's first name in the UI.
     *
     * @param firstName The first name to be set.
     */
    void setOrganizerFirstName(String firstName);

    /**
     * Sets the organizer's last name in the UI.
     *
     * @param lastName The last name to be set.
     */
    void setOrganizerLastName(String lastName);

    /**
     * Sets the organizer's gender in the UI.
     *
     * @param gender The gender to be set.
     */
    void setOrganizerGender(String gender);

    /**
     * Sets the organizer's age in the UI.
     *
     * @param age The age to be set.
     */
    void setOrganizerAge(Integer age);

    /**
     * Sets the organizer's address in the UI.
     *
     * @param address The address to be set.
     */
    void setOrganizerAddress(Address address);

    /**
     * Sets the organizer's email in the UI.
     *
     * @param email The email to be set.
     */
    void setOrganizerEmail(String email);

    /**
     * Sets the organizer's password in the UI.
     *
     * @param password The password to be set.
     */
    void setOrganizerPassword(String password);

    /**
     * Sets the organizer's social security number in the UI.
     *
     * @param ssn The social security number to be set.
     */
    void setOrganizerSsn(Integer ssn);

    /**
     * Initiates the login process.
     */
    void login();

    /**
     * Navigates to the Terms and Conditions screen for organizer registration.
     */
    void moveToTermsAndConditions();

    /**
     * Displays an error message dialog with the provided title and message.
     *
     * @param title   The title of the error message.
     * @param message The content of the error message.
     */
    void showErrorMessage(String title, String message);

    /**
     * Displays a success message dialog for a successful account update.
     *
     * @param organizerId The ID of the updated organizer account.
     */
    void showSuccessfulUpdateMessage(Integer organizerId);

    /**
     * Hides the login prompt UI elements.
     */
    void hideLoginPrompt();

    /**
     * Navigates back to the previous screen.
     */
    void goBack();
}
