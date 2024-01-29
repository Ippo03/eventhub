package com.example.eventhub.view.Account.Register.ChooseRegistration;

/**
 * The ChooseRegistrationView interface provides methods for handling user interactions
 * related to choosing the type of registration during the registration process.
 */
public interface ChooseRegistrationView {

        /**
         * Initiates the registration process for an organizer when the organizer registration option is selected.
         */
        void organizerRegistration();

        /**
         * Initiates the registration process for a customer when the customer registration option is selected.
         */
        void customerRegistration();
}
