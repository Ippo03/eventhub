package com.example.eventhub.view.Account.Register.ChooseRegistration;

import androidx.lifecycle.ViewModel;

/**
 * The ChooseRegistrationViewModel class is responsible for managing the data
 * related to the ChooseRegistrationActivity and facilitating communication
 * between the view and presenter components.
 */
public class ChooseRegistrationViewModel extends ViewModel {

    private ChooseRegistrationPresenter presenter;

    /**
     * Constructs a new instance of ChooseRegistrationViewModel, initializing the presenter.
     */
    public ChooseRegistrationViewModel() {
        presenter = new ChooseRegistrationPresenter();
    }

    /**
     * Retrieves the presenter associated with this view model.
     *
     * @return The ChooseRegistrationPresenter instance.
     */
    public ChooseRegistrationPresenter getPresenter() {
        return presenter;
    }
}
