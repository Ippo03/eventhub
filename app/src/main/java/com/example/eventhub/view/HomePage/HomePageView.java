package com.example.eventhub.view.HomePage;

import com.example.eventhub.domain.Event;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface defining the contract for the Home Page view. Contains methods to interact with the user interface, retrieve filter criteria, and handle various actions.
 */
public interface HomePageView {

    /**
     * Gets the entered event name from the search bar.
     *
     * @return The entered event name.
     */
    String getEventName();

    /**
     * Gets the selected event genres from the filter.
     *
     * @return The selected event genres.
     */
    ArrayList getEventGenres();

    /**
     * Gets the selected event types from the filter.
     *
     * @return The selected event types.
     */
    ArrayList getEventTypes();

    /**
     * Gets the selected date range from the filter.
     *
     * @return The selected date range.
     */
    ArrayList<String> getEventDateRange();

    /**
     * Gets the selected sorting option from the filter.
     *
     * @return The selected sorting option.
     */
    String getEventSorting();

    /**
     * Sets the default value for the event name filter.
     */
    void setDefaultEventName();

    /**
     * Sets the default values for the event genres filter.
     */
    void setDefaultEventGenres();

    /**
     * Sets the default values for the event types filter.
     */
    void setDefaultEventTypes();

    /**
     * Sets the default value for the event from date filter.
     */
    void setDefaultEventFromDate();

    /**
     * Sets the default value for the event to date filter.
     */
    void setDefaultEventToDate();

    /**
     * Sets the default value for the event sorting filter.
     */
    void setDefaultEventSorting();

    /**
     * Resets all filters to their default values.
     */
    void resetFilters();

    /**
     * Retrieves the current filter criteria.
     *
     * @return A HashMap containing the filter criteria.
     */
    HashMap<String, Object> getFilters();

    /**
     * Shows the buttons relevant to the home page.
     */
    void showHomePageButtons();

    /**
     * Shows the buttons relevant to the customer home page.
     */
    void showCustomerHomePageButtons();

    /**
     * Initiates the login process.
     */
    void login();

    /**
     * Initiates the registration process.
     */
    void register();

    /**
     * Initiates the account editing process.
     */
    void editAccount();

    /**
     * Navigates to the purchases screen.
     */
    void purchases();

    /**
     * Initiates the logout process.
     */
    void logout();

    /**
     * Performs a search based on the current filter criteria.
     *
     * @return A list of events matching the search criteria.
     */
    ArrayList<Event> search();

    /**
     * Displays the list of events on the home page.
     */
    void showEvents();

    /**
     * Displays a message indicating that there are no events to display.
     */
    void showEmptyEvents();

    /**
     * Displays an error message to the user.
     *
     * @param title   The title of the error message.
     * @param message The content of the error message.
     */
    void showErrorMessage(String title, String message);
}

