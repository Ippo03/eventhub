package com.example.eventhub.view.Customer.PurchaseDetails;

import androidx.lifecycle.ViewModel;

import com.example.eventhub.memorydao.PurchaseDAOMemory;

/**
 * ViewModel class for managing the presentation logic of the PurchaseDetailsActivity.
 * Responsible for providing data to the associated Presenter.
 */
public class PurchaseDetailsViewModel extends ViewModel {

    private PurchaseDetailsPresenter presenter;

    /**
     * Constructor for PurchaseDetailsViewModel.
     * Initializes the associated presenter with a PurchaseDAOMemory.
     */
    PurchaseDetailsViewModel() {
        presenter = new PurchaseDetailsPresenter(new PurchaseDAOMemory());
    }

    /**
     * Retrieves the associated presenter.
     *
     * @return The PurchaseDetailsPresenter instance.
     */
    public PurchaseDetailsPresenter getPresenter() {
        return presenter;
    }
}

