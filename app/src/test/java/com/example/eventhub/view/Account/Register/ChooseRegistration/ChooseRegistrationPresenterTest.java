package com.example.eventhub.view.Account.Register.ChooseRegistration;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Test class for the {@link ChooseRegistrationPresenter} class.
 */
public class ChooseRegistrationPresenterTest {
    ChooseRegistrationPresenter presenter;
    ChooseRegistrationViewStub view;

    /**
     * Set up method to initialize the test environment.
     */
    @Before
    public void setUp() {
        presenter = new ChooseRegistrationPresenter();
        view = new ChooseRegistrationViewStub();
        presenter.setView(view);
    }

    /**
     * Test case for {@link ChooseRegistrationPresenter#setView(ChooseRegistrationView)}.
     * Tests if the presenter correctly sets the view.
     */
    @Test
    public void setView() {
        ChooseRegistrationView newView = new ChooseRegistrationViewStub();
        presenter.setView(newView);
        Assertions.assertEquals(newView, presenter.getView());
    }

    /**
     * Test case for {@link ChooseRegistrationPresenter#onOrganizerRegistration()}.
     * Tests if the presenter correctly handles the organizer registration action.
     */
    @Test
    public void onOrganizerRegistration() {
        presenter.onOrganizerRegistration();
        Assertions.assertEquals(1, view.getOrganizerRegistrationCount());
    }

    /**
     * Test case for {@link ChooseRegistrationPresenter#onCustomerRegistration()}.
     * Tests if the presenter correctly handles the customer registration action.
     */
    @Test
    public void onCustomerRegistration() {
        presenter.onCustomerRegistration();
        Assertions.assertEquals(1, view.getCustomerRegistrationCount());
    }
}
