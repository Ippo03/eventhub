package com.example.eventhub.view.Account.Register.ChooseRegistration;

/**
 * The ChooseRegistrationPresenter is responsible for handling user interactions related to the choice of registration.
 * It communicates with the ChooseRegistrationView to initiate either organizer or customer registration based on user selections.
 */
public class ChooseRegistrationPresenter {

    private ChooseRegistrationView view;

    /**
     * Constructs a ChooseRegistrationPresenter.
     */
    public ChooseRegistrationPresenter() {}

    /**
     * Sets the view associated with this presenter.
     *
     * @param view The ChooseRegistrationView to be set.
     */
    public void setView(ChooseRegistrationView view) {
        this.view = view;
    }

    /**
     * Retrieves the current ChooseRegistrationView associated with this presenter.
     *
     * @return The ChooseRegistrationView associated with this presenter.
     */
    public ChooseRegistrationView getView() {
        return view;
    }

    /**
     * Initiates the process of organizer registration by notifying the associated view.
     */
    public void onOrganizerRegistration() {
        view.organizerRegistration();
    }

    /**
     * Initiates the process of customer registration by notifying the associated view.
     */
    public void onCustomerRegistration() {
        view.customerRegistration();
    }
}
