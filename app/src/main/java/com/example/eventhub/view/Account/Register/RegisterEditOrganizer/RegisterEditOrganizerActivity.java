package com.example.eventhub.view.Account.Register.RegisterEditOrganizer;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eventhub.R;
import com.example.eventhub.contacts.Address;
import com.example.eventhub.memorydao.OrganizerDAOMemory;
import com.example.eventhub.memorydao.UserDAOMemory;
import com.example.eventhub.view.Account.Login.LoginActivity;
import com.example.eventhub.view.Account.Register.TermsAndConditions.TermsAndConditionsActivity;
import com.example.eventhub.view.OrganizerHomePage.OrganizerHomePageActivity;

import java.util.HashMap;

/**
 * The activity class for registering or editing an organizer's account information.
 */
public class RegisterEditOrganizerActivity extends AppCompatActivity implements RegisterEditOrganizerView {

    // Activity result launcher for starting the TermsAndConditionsActivity
    private ActivityResultLauncher<Intent> startActivityTermsAndConditionsResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_organizer);

        // Initialize the presenter and set up the activity result launcher for Terms and Conditions
        final RegisterEditOrganizerPresenter presenter = new RegisterEditOrganizerPresenter(this, new UserDAOMemory(), new OrganizerDAOMemory());
        startActivityTermsAndConditionsResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Set the result to OK so that the ChooseRegistrationActivity can finish as well
                        setResult(Activity.RESULT_OK);
                        // Finish this Activity when the TermsAndConditionsActivity sends back a result
                        finish();
                    }
                });

        // Set up onClickListeners for the register button and login link
        findViewById(R.id.buttonOrganizerRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSaveOrganizer();
            }
        });

        findViewById(R.id.linkOrganizerLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onLogin();
            }
        });
    }

    // Implementing methods from RegisterEditOrganizerView interface

    /**
     * Set the page title for editing based on the provided title.
     *
     * @param title The title to set for the page.
     */
    @Override
    public void setPageTitleToEdit(String title) {
        ((TextView)findViewById(R.id.titleOrganizerSignUp)).setText(title);
    }

    /**
     * Set the label for the update button based on the provided label.
     *
     * @param label The label to set for the update button.
     */
    @Override
    public void setButtonLabelToUpdate(String label) {
        ((TextView)findViewById(R.id.buttonOrganizerRegister)).setText(label);
    }

    /**
     * Hide the login prompt views.
     */
    @Override
    public void hideLoginPrompt() {
        findViewById(R.id.linkOrganizerLogin).setVisibility(View.GONE);
        findViewById(R.id.textView12).setVisibility(View.GONE);
    }

    /**
     * Get the organizer information as a HashMap.
     *
     * @return A HashMap containing the organizer information.
     */
    @Override
    public HashMap<String,Object> getOrganizerInfo(){
        HashMap<String,Object> info = new HashMap<>();
        info.put("firstname", getOrganizerFirstName());
        info.put("lastname", getOrganizerLastName());
        info.put("age", String.valueOf(getOrganizerAge()));
        info.put("gender", getOrganizerGender());
        info.put("address", getOrganizerAddress());
        info.put("email", getOrganizerEmail());
        info.put("password", getOrganizerPassword());
        info.put("ssn", getOrganizerSsn());
        return info;
    }

    /**
     * Get the attached organizer ID from the Intent.
     *
     * @return The attached organizer ID, or null if not provided.
     */
    @Override
    public Integer getAttachedOrganizerId() {
        return getIntent().hasExtra("organizer_id") ? getIntent().getIntExtra("organizer_id", -1) : null;
    }

    /**
     * Get the organizer's first name from the input field.
     *
     * @return The organizer's first name.
     */
    @Override
    public String getOrganizerFirstName() {
        return ((EditText)findViewById(R.id.textInputOrganizerFirstName)).getText().toString().trim();
    }

    /**
     * Get the organizer's last name from the input field.
     *
     * @return The organizer's last name.
     */
    @Override
    public String getOrganizerLastName() {
        return ((EditText)findViewById(R.id.textInputOrganizerLastName)).getText().toString().trim();
    }

    /**
     * Get the organizer's gender from the selected radio button.
     *
     * @return The organizer's gender.
     */
    @Override
    public String getOrganizerGender() {
        RadioGroup radioGroup = findViewById(R.id.radioGroupOrganizerGender);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();

        if (radioButtonID == -1) {
            // No radio button is selected, return an empty string
            return "";
        } else {
            return ((RadioButton) radioGroup.findViewById(radioButtonID)).getText().toString().trim();
        }
    }

    /**
     * Get the organizer's age from the input field.
     *
     * @return The organizer's age.
     */
    @Override
    public String getOrganizerAge() {
        return ((EditText)findViewById(R.id.textInputOrganizerAge)).getText().toString().trim();
    }

    /**
     * Get the organizer's address from the input fields.
     *
     * @return The organizer's address.
     */
    @Override
    public Address getOrganizerAddress() {
        String country = "Greece"; // by default
        String city = ((EditText)findViewById(R.id.textInputOrganizerCity)).getText().toString().trim();
        String street = ((EditText)findViewById(R.id.textInputOrganizerStreet)).getText().toString().trim();
        String number = ((EditText)findViewById(R.id.textInputOrganizerStreetNumber)).getText().toString().trim();
        Integer streetNumber = number.isEmpty() ? 0 : Integer.valueOf(number);
        String zip = ((EditText)findViewById(R.id.textInputOrganizerZipCode)).getText().toString().trim();
        return new Address(street, streetNumber, city, zip, country);
    }

    /**
     * Get the organizer's email from the input field.
     *
     * @return The organizer's email.
     */
    @Override
    public String getOrganizerEmail() {
        return ((EditText)findViewById(R.id.textInputOrganizerEmail)).getText().toString().trim();
    }

    /**
     * Get the organizer's password from the input field.
     *
     * @return The organizer's password.
     */
    @Override
    public String getOrganizerPassword() {
        return ((EditText)findViewById(R.id.textInputOrganizerPassword)).getText().toString().trim();
    }

    /**
     * Get the organizer's SSN from the input field.
     *
     * @return The organizer's SSN.
     */
    @Override
    public String getOrganizerSsn() {
        return ((EditText)findViewById(R.id.textInputOrganizerSsn)).getText().toString().trim();
    }

    /**
     * Set the organizer's first name in the input field.
     *
     * @param firstName The organizer's first name to set.
     */
    @Override
    public void setOrganizerFirstName(String firstName) {
        ((EditText) findViewById(R.id.textInputOrganizerFirstName)).setText(firstName);
    }

    /**
     * Set the organizer's last name in the input field.
     *
     * @param lastName The organizer's last name to set.
     */
    @Override
    public void setOrganizerLastName(String lastName) {
        ((EditText) findViewById(R.id.textInputOrganizerLastName)).setText(lastName);
    }

    /**
     * Set the organizer's age in the input field.
     *
     * @param age The organizer's age to set.
     */
    @Override
    public void setOrganizerAge(Integer age) {
        ((EditText) findViewById(R.id.textInputOrganizerAge)).setText(age.toString());
    }

    /**
     * Set the organizer's gender based on the provided gender.
     *
     * @param gender The organizer's gender to set.
     */
    @Override
    public void setOrganizerGender(String gender) {
        if ("male".equalsIgnoreCase(gender)) {
            ((RadioButton) findViewById(R.id.checkBoxOrganizerMale)).setChecked(true);
        } else if ("female".equalsIgnoreCase(gender)) {
            ((RadioButton) findViewById(R.id.checkBoxOrganizerFemale)).setChecked(true);
        }
    }

    /**
     * Set the organizer's address in the input fields.
     *
     * @param address The organizer's address to set.
     */
    @Override
    public void setOrganizerAddress(Address address) {
        ((EditText) findViewById(R.id.textInputOrganizerCity)).setText(address.getCity());
        ((EditText) findViewById(R.id.textInputOrganizerStreet)).setText(address.getStreet());
        ((EditText) findViewById(R.id.textInputOrganizerStreetNumber)).setText(address.getNumber().toString());
        ((EditText) findViewById(R.id.textInputOrganizerZipCode)).setText(address.getZipCode());
    }

    /**
     * Set the organizer's email in the input field.
     *
     * @param email The organizer's email to set.
     */
    @Override
    public void setOrganizerEmail(String email) {
        ((EditText) findViewById(R.id.textInputOrganizerEmail)).setText(email);
    }

    /**
     * Set the organizer's password in the input field.
     *
     * @param password The organizer's password to set.
     */
    @Override
    public void setOrganizerPassword(String password) {
        ((EditText) findViewById(R.id.textInputOrganizerPassword)).setText(password);
    }

    /**
     * Set the organizer's SSN in the input field.
     *
     * @param ssn The organizer's SSN to set.
     */
    @Override
    public void setOrganizerSsn(Integer ssn) {
        ((EditText) findViewById(R.id.textInputOrganizerSsn)).setText(ssn.toString());
    }

    /**
     * Navigate to the login activity.
     */
    @Override
    public void login() {
        Intent intent = new Intent(RegisterEditOrganizerActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Navigate to the Terms and Conditions activity.
     */
    @Override
    public void moveToTermsAndConditions() {
        Intent intent = new Intent(RegisterEditOrganizerActivity.this, TermsAndConditionsActivity.class);
        intent.putExtra("type", "organizer");
        intent.putExtra("info", getOrganizerInfo());
        startActivityTermsAndConditionsResult.launch(intent);
    }

    /**
     * Display an error message in an AlertDialog.
     *
     * @param title   The title of the AlertDialog.
     * @param message The message to display in the AlertDialog.
     */
    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(RegisterEditOrganizerActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    /**
     * Display a successful update message in an AlertDialog and navigate to the organizer home page.
     *
     * @param organizerId The ID of the updated organizer.
     */
    @Override
    public void showSuccessfulUpdateMessage(Integer organizerId) {
        new AlertDialog.Builder(RegisterEditOrganizerActivity.this)
                .setCancelable(true)
                .setTitle("Success")
                .setMessage("Your account has been updated successfully!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveToOrganizerHomePage(organizerId);
                    }
                })
                .create()
                .show();
    }

    /**
     * Finish the activity, effectively going back.
     */
    @Override
    public void goBack() {
        finish();
    }

    /**
     * Navigate to the organizer's home page using the provided organizer ID.
     *
     * @param organizerId The ID of the organizer.
     */
    public void moveToOrganizerHomePage(Integer organizerId) {
        Intent intent = new Intent(RegisterEditOrganizerActivity.this, OrganizerHomePageActivity.class);
        intent.putExtra("organizer_id", organizerId);
        startActivity(intent);
        finish();
    }
}