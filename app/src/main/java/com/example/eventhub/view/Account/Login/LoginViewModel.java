package com.example.eventhub.view.Account.Login;

import androidx.lifecycle.ViewModel;

import com.example.eventhub.memorydao.CustomerDAOMemory;
import com.example.eventhub.memorydao.OrganizerDAOMemory;
import com.example.eventhub.memorydao.UserDAOMemory;

/**
 * The LoginViewModel class serves as the ViewModel for the login functionality in the application.
 * It encapsulates the interaction between the login view and the underlying data sources.
 */
public class LoginViewModel extends ViewModel {

    private LoginPresenter presenter;

    /**
     * Constructs a new LoginViewModel, initializing the presenter with memory DAO instances.
     */
    public LoginViewModel() {
        presenter = new LoginPresenter(new UserDAOMemory(), new OrganizerDAOMemory(), new CustomerDAOMemory());
    }

    /**
     * Gets the associated LoginPresenter instance.
     *
     * @return The LoginPresenter instance associated with this ViewModel.
     */
    public LoginPresenter getPresenter() {
        return presenter;
    }
}
