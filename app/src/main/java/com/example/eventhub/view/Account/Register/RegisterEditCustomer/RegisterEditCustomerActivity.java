package com.example.eventhub.view.Account.Register.RegisterEditCustomer;

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
import com.example.eventhub.domain.Genre;
import com.example.eventhub.domain.Interest;
import com.example.eventhub.memorydao.CustomerDAOMemory;
import com.example.eventhub.memorydao.UserDAOMemory;
import com.example.eventhub.util.Util;
import com.example.eventhub.view.Account.Login.LoginActivity;
import com.example.eventhub.view.Account.Register.TermsAndConditions.TermsAndConditionsActivity;
import com.example.eventhub.view.HomePage.HomePageActivity;
import com.example.eventhub.view.Util.MultiSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * The RegisterEditCustomerActivity allows customers to register or edit their account information.
 * It handles user input, validation, and interaction with the RegisterEditCustomerPresenter.
 */
public class RegisterEditCustomerActivity extends AppCompatActivity implements RegisterEditCustomerView {
    private ActivityResultLauncher<Intent> startActivityTermsAndConditionsResult;

    /**
     * Initializes the activity, sets up the presenter, and registers result launcher for Terms and Conditions activity.
     *
     * @param savedInstanceState A saved instance state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_customer);

        final RegisterEditCustomerPresenter presenter = new RegisterEditCustomerPresenter(
                this, new UserDAOMemory(), new CustomerDAOMemory());

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

        findViewById(R.id.buttonCustomerRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSaveCustomer();
            }
        });

        findViewById(R.id.linkCustomerLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onLogin();
            }
        });
    }

    /**
     * Sets the page title for customer registration/editing.
     *
     * @param title The title to be set.
     */
    @Override
    public void setPageTitleToEdit(String title) {
        ((TextView)findViewById(R.id.titleCustomerSignUp)).setText(title);
    }

    /**
     * Sets the label for the registration/update button.
     *
     * @param label The label to be set.
     */
    @Override
    public void setButtonLabelToUpdate(String label) {
        ((TextView)findViewById(R.id.buttonCustomerRegister)).setText(label);
    }

    /**
     * Hides the login prompt UI elements.
     */
    @Override
    public void hideLoginPrompt() {
        findViewById(R.id.linkCustomerLogin).setVisibility(View.GONE);
        findViewById(R.id.textView12).setVisibility(View.GONE);
    }

    /**
     * Retrieves the customer ID attached to the intent.
     *
     * @return The attached customer ID or null if not found.
     */
    @Override
    public Integer getAttachedCustomerId() {
        return getIntent().hasExtra("customer_id") ? getIntent().getIntExtra("customer_id", -1) : null;
    }

    /**
     * Retrieves customer information from UI elements.
     *
     * @return A map containing customer information.
     */
    @Override
    public HashMap<String, Object> getCustomerInfo() {
        HashMap<String, Object> info = new HashMap<>();
        info.put("firstname", getCustomerFirstName());
        info.put("lastname", getCustomerLastName());
        info.put("email", getCustomerEmail());
        info.put("age", getCustomerAge());
        info.put("address", getCustomerAddress());
        info.put("gender", getCustomerGender());
        info.put("password", getCustomerPassword());
        info.put("interests", getCustomerInterests());
        return info;
    }

    /**
     * Retrieves the customer's first name from the UI.
     *
     * @return The customer's first name.
     */
    @Override
    public String getCustomerFirstName() {
        return ((EditText)findViewById(R.id.textInputCustomerFirstName)).getText().toString().trim();
    }

    /**
     * Retrieves the customer's last name from the UI.
     *
     * @return The customer's last name.
     */
    @Override
    public String getCustomerLastName() {
        return ((EditText)findViewById(R.id.textInputCustomerLastName)).getText().toString().trim();
    }

    /**
     * Retrieves the customer's gender from the UI.
     *
     * @return The customer's gender.
     */
    @Override
    public String getCustomerGender() {
        RadioGroup radioGroup = findViewById(R.id.radioGroupCustomerGender);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        if (radioButtonID == -1) {
            return "";
        } else {
            return ((RadioButton) radioGroup.findViewById(radioButtonID)).getText().toString().trim();
        }
    }

    /**
     * Retrieves the customer's age from the UI.
     *
     * @return The customer's age.
     */
    @Override
    public String getCustomerAge() {
        return ((EditText)findViewById(R.id.textInputCustomerAge)).getText().toString().trim();
    }

    /**
     * Retrieves the customer's address from the UI.
     *
     * @return The customer's address.
     */
    @Override
    public Address getCustomerAddress() {
        String country = "Greece"; // default
        String city = ((EditText)findViewById(R.id.textInputCustomerCity)).getText().toString().trim();
        String street = ((EditText)findViewById(R.id.textInputCustomerStreet)).getText().toString().trim();
        String number = ((EditText)findViewById(R.id.textInputCustomerStreetNumber)).getText().toString().trim();
        Integer streetNumber = number.isEmpty() ? 0 : Integer.valueOf(number);
        String zip = ((EditText)findViewById(R.id.textInputCustomerZipCode)).getText().toString().trim();
        return new Address(street, streetNumber, city, zip, country);
    }

    /**
     * Retrieves the customer's email from the UI.
     *
     * @return The customer's email.
     */
    @Override
    public String getCustomerEmail() {
        return ((EditText)findViewById(R.id.textInputCustomerEmail)).getText().toString().trim();
    }

    /**
     * Retrieves the customer's password from the UI.
     *
     * @return The customer's password.
     */
    @Override
    public String getCustomerPassword() {
        return ((EditText)findViewById(R.id.textInputCustomerPassword)).getText().toString().trim();
    }

    /**
     * Retrieves the customer's interests from the UI.
     *
     * @return A set of customer interests.
     */
    @Override
    public Set<Interest> getCustomerInterests() {
        if (((MultiSpinner)findViewById(R.id.multiSpinnerCustomerInterests)).getSelectedItems().isEmpty()) {
            return new HashSet<>();
        }
        String interests =  ((MultiSpinner)findViewById(R.id.multiSpinnerCustomerInterests)).getSelectedItems();
        ArrayList<String> interestsStringList = new ArrayList<>(Arrays.asList(interests.split(",")));
        Set<Interest> interestsList = new HashSet<>();
        for (Genre genre : Util.mapStringsToGenres(interestsStringList)) {
            interestsList.add(new Interest(genre));
        }
        return interestsList;
    }

    /**
     * Sets the customer's first name in the UI.
     *
     * @param firstName The first name to be set.
     */
    @Override
    public void setCustomerFirstName(String firstName) {
        ((EditText)findViewById(R.id.textInputCustomerFirstName)).setText(firstName);
    }

    /**
     * Sets the customer's last name in the UI.
     *
     * @param lastName The last name to be set.
     */
    @Override
    public void setCustomerLastName(String lastName) {
        ((EditText)findViewById(R.id.textInputCustomerLastName)).setText(lastName);
    }

    /**
     * Sets the customer's age in the UI.
     *
     * @param age The age to be set.
     */
    @Override
    public void setCustomerAge(Integer age) {
        ((EditText)findViewById(R.id.textInputCustomerAge)).setText(String.valueOf(age));
    }

    /**
     * Sets the customer's gender in the UI.
     *
     * @param gender The gender to be set.
     */
    @Override
    public void setCustomerGender(String gender) {
        if ("male".equalsIgnoreCase(gender)) {
            ((RadioButton)findViewById(R.id.checkBoxCustomerMale)).setChecked(true);
        } else if ("female".equalsIgnoreCase(gender)) {
            ((RadioButton)findViewById(R.id.checkBoxCustomerFemale)).setChecked(true);
        }
    }

    /**
     * Sets the customer's address in the UI.
     *
     * @param address The address to be set.
     */
    @Override
    public void setCustomerAddress(Address address) {
        ((EditText)findViewById(R.id.textInputCustomerCity)).setText(address.getCity());
        ((EditText)findViewById(R.id.textInputCustomerStreet)).setText(address.getStreet());
        ((EditText)findViewById(R.id.textInputCustomerStreetNumber)).setText(String.valueOf(address.getNumber()));
        ((EditText)findViewById(R.id.textInputCustomerZipCode)).setText(address.getZipCode());
    }

    /**
     * Sets the customer's email in the UI.
     *
     * @param email The email to be set.
     */
    @Override
    public void setCustomerEmail(String email) {
        ((EditText)findViewById(R.id.textInputCustomerEmail)).setText(email);
    }

    /**
     * Sets the customer's password in the UI.
     *
     * @param password The password to be set.
     */
    @Override
    public void setCustomerPassword(String password) {
        ((EditText)findViewById(R.id.textInputCustomerPassword)).setText(password);
    }

    /**
     * Sets the customer's interests in the UI.
     *
     * @param interests The interests to be set.
     */
    @Override
    public void setCustomerInterests(Set<Interest> interests) {
        Set<String> interestsStringList = new HashSet<>();
        for (Interest interest : interests) {
            interestsStringList.add(Util.convertToTitleCase(interest.getInterestName().toString()));
        }
        setUpMultiSpinner();
        ((MultiSpinner)findViewById(R.id.multiSpinnerCustomerInterests)).setSelectedItems(interestsStringList);
    }
    /**
     * Navigates to the login activity.
     */
    @Override
    public void login() {
        Intent intent = new Intent(RegisterEditCustomerActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to the Terms and Conditions activity for customer registration.
     */
    @Override
    public void moveToTermsAndConditions() {
        Intent intent = new Intent(this, TermsAndConditionsActivity.class);
        intent.putExtra("type", "customer");
        intent.putExtra("info", getCustomerInfo());
        startActivityTermsAndConditionsResult.launch(intent);
    }

    /**
     * Displays an error message dialog with the provided title and message.
     *
     * @param title   The title of the error message.
     * @param message The content of the error message.
     */
    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(RegisterEditCustomerActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    /**
     * Displays a success message dialog for a successful account update.
     *
     * @param customerId The ID of the updated customer account.
     */
    @Override
    public void showSuccessfulUpdateMessage(Integer customerId) {
        new AlertDialog.Builder(RegisterEditCustomerActivity.this)
                .setCancelable(true)
                .setTitle("Success")
                .setMessage("Your account has been updated successfully!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveToCustomerHomePage(customerId);
                    }
                })
                .create()
                .show();
    }

    /**
     * Navigates back to the previous activity.
     */
    @Override
    public void goBack() {
        finish();
    }

    /**
     * Navigates to the customer's home page with the provided customer ID.
     *
     * @param customerId The ID of the customer.
     */
    public void moveToCustomerHomePage(Integer customerId) {
        Intent intent = new Intent(RegisterEditCustomerActivity.this, HomePageActivity.class);
        intent.putExtra("customer_id", customerId);
        startActivity(intent);
        finish();
    }

    /**
     * Sets up the MultiSpinner for customer interests with predefined genres.
     */
    @Override
    public void setUpMultiSpinner() {
        // Set up the Interest MultiSpinner
        ((MultiSpinner)findViewById(R.id.multiSpinnerCustomerInterests)).setItems(Arrays.asList(getResources().getStringArray(R.array.genres)), "All Interests", new MultiSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected) {
                // Do something here with the selected items
            }
        });
    }
}