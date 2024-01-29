package com.example.eventhub.view.Account.Register.TermsAndConditions;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eventhub.R;
import com.example.eventhub.view.HomePage.HomePageActivity;
import com.example.eventhub.view.OrganizerHomePage.OrganizerHomePageActivity;

import java.util.HashMap;

/**
 * Activity class for displaying and handling the Terms and Conditions during user registration.
 * Allows users to agree or disagree with the terms before completing the registration process.
 */
public class TermsAndConditionsActivity extends AppCompatActivity implements TermsAndConditionsView {

    HashMap<String, Object> info;
    String type;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);

        // Retrieve information and user type from the intent
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            info = (HashMap<String, Object>) intent.getSerializableExtra("info");
            type = intent.getStringExtra("type");
        }

        // Initialize ViewModel and set the view for presenter
        TermsAndConditionsViewModel viewModel = new TermsAndConditionsViewModel();
        viewModel.getPresenter().setView(this);

        // Set click listener for the registration button
        findViewById(R.id.buttonFinishRegistration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getPresenter().onRegister(info, type);
            }
        });
    }

    /**
     * Retrieves the user's agreement status with the terms.
     *
     * @return The agreement status as a String ("Agree" or "Disagree").
     */
    @Override
    public String getAgreement() {
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        return ((RadioButton) radioGroup.findViewById(radioButtonID)).getText().toString().trim();
    }

    /**
     * Sets the agreement status based on the provided String.
     *
     * @param agreement The agreement status to set ("Agree" or "Disagree").
     */
    @Override
    public void setAgreement(String agreement) {
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        if (agreement.equals("Agree")) {
            radioGroup.check(R.id.checkBoxAgree);
        } else {
            radioGroup.check(R.id.checkBoxDisagree);
        }
    }

    /**
     * Displays a successful registration message with the user's ID and type.
     *
     * @param id   The user's ID.
     * @param type The user's type ("organizer" or "customer").
     */
    @Override
    public void showSuccessfulRegistrationMessage(int id, String type) {
        new AlertDialog.Builder(TermsAndConditionsActivity.this)
                .setCancelable(true)
                .setTitle("Successful Registration")
                .setMessage("You have successfully registered as " + type + ".\nYou will be redirected to your home page.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        login(type, id);
                    }
                }).create().show();
    }

    /**
     * Displays an error message with the provided title and message.
     *
     * @param title   The title of the error message.
     * @param message The content of the error message.
     */
    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(TermsAndConditionsActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    /**
     * Handles the login based on the user type and ID.
     *
     * @param type The user's type ("organizer" or "customer").
     * @param id   The user's ID.
     */
    public void login(String type, int id) {
        Intent intent;
        if (type.equals("organizer")) {
            intent = new Intent(this, OrganizerHomePageActivity.class);
            intent.putExtra("organizer_id", id);
        } else {
            intent = new Intent(this, HomePageActivity.class);
            intent.putExtra("customer_id", id);
        }
        startActivity(intent);
        // Set result for RegisterEditOrganizerActivity and finish this Activity
        setResult(Activity.RESULT_OK);
        finish();
    }
}
