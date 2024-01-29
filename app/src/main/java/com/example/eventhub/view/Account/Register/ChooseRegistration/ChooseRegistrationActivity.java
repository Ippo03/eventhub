package com.example.eventhub.view.Account.Register.ChooseRegistration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eventhub.R;
import com.example.eventhub.view.Account.Register.RegisterEditCustomer.RegisterEditCustomerActivity;
import com.example.eventhub.view.Account.Register.RegisterEditOrganizer.RegisterEditOrganizerActivity;

/**
 * The ChooseRegistrationActivity allows users to choose between organizer and customer registration.
 * It serves as the entry point for the registration process, providing options to register as an organizer or a customer.
 */
public class ChooseRegistrationActivity extends AppCompatActivity implements ChooseRegistrationView {

    private ChooseRegistrationViewModel viewModel;
    private ActivityResultLauncher<Intent> startActivityUserRegistration;

    /**
     * Called when the activity is first created. Responsible for initializing the activity,
     * setting up the UI components, and handling user interactions related to registration.
     *
     * @param savedInstanceState A Bundle containing the activity's previously saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_registration);

        viewModel = new ChooseRegistrationViewModel();
        viewModel.getPresenter().setView(this);
        startActivityUserRegistration = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Set the result to OK so that the HomePageActivity can finish as well
                        setResult(Activity.RESULT_OK);
                        // Finish this Activity when either the RegisterEditCustomerActivity or RegisterEditOrganizerActivity sends back a result
                        finish();
                    }
                });

        findViewById(R.id.buttonRegisterOrg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getPresenter().onOrganizerRegistration();
            }
        });

        findViewById(R.id.buttonRegisterCust).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getPresenter().onCustomerRegistration();
            }
        });
    }

    /**
     * Initiates the organizer registration process when the user selects to register as an organizer.
     */
    @Override
    public void organizerRegistration() {
        Intent intent = new Intent(this, RegisterEditOrganizerActivity.class);
        startActivityUserRegistration.launch(intent);
    }

    /**
     * Initiates the customer registration process when the user selects to register as a customer.
     */
    @Override
    public void customerRegistration() {
        Intent intent = new Intent(this, RegisterEditCustomerActivity.class);
        startActivityUserRegistration.launch(intent);
    }
}
