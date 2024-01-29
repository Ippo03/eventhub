package com.example.eventhub.view.Account.Register.RegisterEditCustomer;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.domain.Interest;

import java.util.HashMap;
import java.util.Set;

/**
 * The RegisterEditCustomerView interface defines the contract for the view layer when editing or registering a customer account.
 * It includes methods to retrieve and set customer information, handle user interactions, and display messages.
 */
public interface RegisterEditCustomerView {

    /**
     * Sets the page title for the customer registration or edit page.
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
     * Retrieves the ID of the customer being edited.
     *
     * @return The ID of the customer.
     */
    Integer getAttachedCustomerId();

    /**
     * Retrieves customer information as a HashMap.
     *
     * @return HashMap containing customer information.
     */
    HashMap<String, Object> getCustomerInfo();

    /**
     * Retrieves the first name of the customer.
     *
     * @return The first name of the customer.
     */
    String getCustomerFirstName();

    /**
     * Retrieves the last name of the customer.
     *
     * @return The last name of the customer.
     */
    String getCustomerLastName();

    /**
     * Retrieves the gender of the customer.
     *
     * @return The gender of the customer.
     */
    String getCustomerGender();

    /**
     * Retrieves the age of the customer.
     *
     * @return The age of the customer.
     */
    String getCustomerAge();

    /**
     * Retrieves the address of the customer.
     *
     * @return The address of the customer.
     */
    Address getCustomerAddress();

    /**
     * Retrieves the email of the customer.
     *
     * @return The email of the customer.
     */
    String getCustomerEmail();

    /**
     * Retrieves the password of the customer.
     *
     * @return The password of the customer.
     */
    String getCustomerPassword();

    /**
     * Retrieves the interests of the customer.
     *
     * @return Set of interests of the customer.
     */
    Set<Interest> getCustomerInterests();

    /**
     * Sets the first name of the customer.
     *
     * @param firstName The first name to be set.
     */
    void setCustomerFirstName(String firstName);

    /**
     * Sets the last name of the customer.
     *
     * @param lastName The last name to be set.
     */
    void setCustomerLastName(String lastName);

    /**
     * Sets the gender of the customer.
     *
     * @param gender The gender to be set.
     */
    void setCustomerGender(String gender);

    /**
     * Sets the age of the customer.
     *
     * @param age The age to be set.
     */
    void setCustomerAge(Integer age);

    /**
     * Sets the address of the customer.
     *
     * @param address The address to be set.
     */
    void setCustomerAddress(Address address);

    /**
     * Sets the email of the customer.
     *
     * @param email The email to be set.
     */
    void setCustomerEmail(String email);

    /**
     * Sets the password of the customer.
     *
     * @param password The password to be set.
     */
    void setCustomerPassword(String password);

    /**
     * Sets the interests of the customer.
     *
     * @param interests Set of interests to be set.
     */
    void setCustomerInterests(Set<Interest> interests);

    /**
     * Initiates the login process.
     */
    void login();

    /**
     * Navigates to the terms and conditions page.
     */
    void moveToTermsAndConditions();

    /**
     * Displays an error message.
     *
     * @param title   The title of the error message.
     * @param message The content of the error message.
     */
    void showErrorMessage(String title, String message);

    /**
     * Displays a successful update message with the ID of the organizer.
     *
     * @param organizerId The ID of the updated organizer.
     */
    void showSuccessfulUpdateMessage(Integer organizerId);

    /**
     * Hides the login prompt.
     */
    void hideLoginPrompt();

    /**
     * Navigates back to the previous screen.
     */
    void goBack();

    /**
     * Sets up the multi-spinner for selecting customer interests.
     */
    void setUpMultiSpinner();
}
