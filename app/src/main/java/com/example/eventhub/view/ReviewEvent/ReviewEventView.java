package com.example.eventhub.view.ReviewEvent;

/**
 * Interface defining the contract between the ReviewEventView (UI) and its corresponding presenter.
 */
public interface ReviewEventView {

    /**
     * Displays the name of the event in the UI.
     *
     * @param eventName The name of the event.
     */
    void showEventName(String eventName);

    /**
     * Retrieves the user's grade input from the UI.
     *
     * @return The user's grade input.
     */
    String getGrade();

    /**
     * Retrieves the user's comment input from the UI.
     *
     * @return The user's comment input.
     */
    String getComment();

    /**
     * Displays a message indicating that the review has been successfully saved.
     */
    void showReviewSavedMessage();

    /**
     * Displays an error message in the UI.
     *
     * @param title   The title of the error message.
     * @param message The content of the error message.
     */
    void showErrorMessage(String title, String message);
}

