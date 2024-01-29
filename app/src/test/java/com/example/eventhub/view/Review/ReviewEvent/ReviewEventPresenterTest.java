package com.example.eventhub.view.Review.ReviewEvent;

import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.ReviewDAO;
import com.example.eventhub.memorydao.CustomerDAOMemory;
import com.example.eventhub.memorydao.EventDAOMemory;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.memorydao.ReviewDAOMemory;
import com.example.eventhub.view.ReviewEvent.ReviewEventPresenter;
import com.example.eventhub.view.ReviewEvent.ReviewEventView;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Unit tests for the ReviewEventPresenter class, focusing on the interaction
 * between the presenter and the view, as well as specific functionality related
 * to reviewing events.
 */
public class ReviewEventPresenterTest {
    private ReviewEventPresenter presenter;
    private ReviewEventViewStub view;

    private CustomerDAO customerDAO;
    private EventDAO eventDAO;
    private ReviewDAO reviewDAO;

    /**
     * Set up method for initializing the test environment.
     * Prepares test data using MemoryInitializer, initializes the view with a stub,
     * and sets up in-memory implementations for CustomerDAO, EventDAO, and ReviewDAO.
     */
    @Before
    public void setUp() {
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new ReviewEventViewStub();

        customerDAO = new CustomerDAOMemory();
        eventDAO = new EventDAOMemory();
        reviewDAO = new ReviewDAOMemory();

        // Initialize the presenter with the initialized DAOs
        presenter = new ReviewEventPresenter(customerDAO, eventDAO, reviewDAO);
        presenter.setView(view);

        presenter.setCustomer(4);
        presenter.setEvent(1);
    }

    /**
     * Test case for setting a new view for the presenter.
     * Validates that the view is successfully set in the presenter.
     */
    @Test
    public void onSetView() {
        ReviewEventView newView = new ReviewEventViewStub();
        presenter.setView(newView);
        Assertions.assertEquals(newView, presenter.getView());
    }

    /**
     * Test case for setting a customer in the presenter.
     * Validates that the correct customer is set in the presenter.
     */
    @Test
    public void onSetCustomer() {
        Assertions.assertEquals(4, presenter.getCustomer().getId());
    }

    /**
     * Test case for setting an event in the presenter.
     * Validates that the correct event is set in the presenter.
     */
    @Test
    public void onSetEvent() {
        Assertions.assertEquals(1, presenter.getEvent().getEventId());
    }

    /**
     * Test case for showing the event name in the view.
     * Validates that the correct event name is displayed in the view.
     */
    @Test
    public void onShowEventName() {
        presenter.onShowEventName();
        Assertions.assertEquals("House Party", view.getEventName());
    }

    /**
     * Test case for reviewing an event with null grade and comment.
     * Validates that the presenter correctly handles the scenario and displays an error message.
     */
    @Test
    public void onReviewEventWithNullGradeAndComment() {
        presenter.onReviewEvent();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill in all fields", view.getErrorMessage());
    }

    /**
     * Test case for reviewing an event with empty grade and comment.
     * Validates that the presenter correctly handles the scenario and displays an error message.
     */
    @Test
    public void onReviewEventWithEmptyGradeAndComment() {
        view.setGrade("");
        view.setComment("");
        presenter.onReviewEvent();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill in all fields", view.getErrorMessage());
    }

    /**
     * Test case for reviewing an event with null grade.
     * Validates that the presenter correctly handles the scenario and displays an error message.
     */
    @Test
    public void onReviewEventWithNullGrade() {
        view.setComment("Great Event");
        presenter.onReviewEvent();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill in all fields", view.getErrorMessage());
    }

    /**
     * Test case for reviewing an event with null comment.
     * Validates that the presenter correctly handles the scenario and displays an error message.
     */
    @Test
    public void onReviewEventWithNullComment() {
        view.setGrade("5");
        presenter.onReviewEvent();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Please fill in all fields", view.getErrorMessage());
    }

    /**
     * Test case for reviewing an event with an invalid grade (below the valid range).
     * Validates that the presenter correctly handles the scenario and displays an error message.
     */
    @Test
    public void onReviewEventWithInvalidGrade1() {
        view.setGrade("0");
        view.setComment("Great Event");
        presenter.onReviewEvent();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Grade must be between 1 and 10", view.getErrorMessage());
    }

    /**
     * Test case for reviewing an event with an invalid grade (above the valid range).
     * Validates that the presenter correctly handles the scenario and displays an error message.
     */
    @Test
    public void onReviewEventWithInvalidGrade2() {
        view.setGrade("11");
        view.setComment("Great Event");
        presenter.onReviewEvent();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Grade must be between 1 and 10", view.getErrorMessage());
    }

    /**
     * Test case for reviewing an event with a valid grade and comment.
     * Validates that the review is saved successfully.
     */
    @Test
    public void onReviewEvent() {
        view.setGrade("5");
        view.setComment("Great Event");
        presenter.onReviewEvent();

        Assertions.assertEquals(0, view.getErrorCount());
        Assertions.assertEquals("Your review has been saved successfully.", view.getSuccessMessage());
    }
}
