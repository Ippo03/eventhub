package com.example.eventhub.view.ReviewEvent;

import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.ReviewDAO;
import com.example.eventhub.domain.Customer;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.Review;

/**
 * Presenter class responsible for managing the review process of an event.
 * Acts as an intermediary between the ReviewEventView and the data sources (CustomerDAO, EventDAO, ReviewDAO).
 */
public class ReviewEventPresenter {

    private ReviewEventView view;
    private CustomerDAO customerDAO;
    private EventDAO eventDAO;
    private ReviewDAO reviewDAO;
    private Customer customer;
    private Event event;

    /**
     * Initializes the ReviewEventPresenter with the provided DAO instances.
     *
     * @param customerDAO The data access object for Customer entities.
     * @param eventDAO    The data access object for Event entities.
     * @param reviewDAO   The data access object for Review entities.
     */
    public ReviewEventPresenter(CustomerDAO customerDAO, EventDAO eventDAO, ReviewDAO reviewDAO) {
        this.customerDAO = customerDAO;
        this.eventDAO = eventDAO;
        this.reviewDAO = reviewDAO;
    }

    /**
     * Sets the associated ReviewEventView.
     *
     * @param view The ReviewEventView to be set.
     */
    public void setView(ReviewEventView view) {
        this.view = view;
    }

    /**
     * Retrieves the associated ReviewEventView instance.
     *
     * @return The ReviewEventView instance.
     */
    public ReviewEventView getView() {
        return view;
    }

    /**
     * Sets the customer for whom the event is being reviewed.
     *
     * @param customerId The ID of the customer.
     */
    public void setCustomer(Integer customerId) {
        this.customer = customerDAO.find(customerId);
    }

    /**
     * Retrieves the associated Customer instance.
     *
     * @return The Customer instance.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the event that is being reviewed.
     *
     * @param eventId The ID of the event.
     */
    public void setEvent(Integer eventId) {
        this.event = eventDAO.find(eventId);
    }

    /**
     * Retrieves the associated Event instance.
     *
     * @return The Event instance.
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Displays the name of the event on the associated ReviewEventView.
     */
    public void onShowEventName() {
        view.showEventName(event.getName());
    }

    /**
     * Handles the process of reviewing an event, including validating input data and saving the review.
     */
    public void onReviewEvent() {
        String grade = view.getGrade();
        String comment = view.getComment();

        if (grade.isEmpty() || comment.isEmpty()) {
            view.showErrorMessage("Error", "Please fill in all fields");
            return;
        }

        int numericGrade = Integer.parseInt(grade);

        if (numericGrade < 1 || numericGrade > 10) {
            view.showErrorMessage("Error", "Grade must be between 1 and 10");
            return;
        }

        Review review = new Review(reviewDAO.nextId(), numericGrade, comment);
        reviewDAO.save(review);
        event.addReview(review);
        review.setCustomer(customer);
        view.showReviewSavedMessage();
    }
}

