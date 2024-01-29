package com.example.eventhub.view.Account.Login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eventhub.R;
import com.example.eventhub.view.Account.Register.ChooseRegistration.ChooseRegistrationActivity;
import com.example.eventhub.view.HomePage.HomePageActivity;
import com.example.eventhub.view.OrganizerHomePage.OrganizerHomePageActivity;

/**
 * The LoginActivity class represents the user interface for the login functionality.
 * It extends AppCompatActivity and implements the LoginView interface for communication with the presenter.
 */
public class LoginActivity extends AppCompatActivity implements LoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginViewModel viewModel = new LoginViewModel();
        viewModel.getPresenter().setView(this);

        findViewById(R.id.buttonLoginPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onAuthenticateUser();
            }
        });

        findViewById(R.id.linkToRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getPresenter().onSignUp();
            }
        });
    }

    @Override
    public String getEmail() {
        return ((EditText) findViewById(R.id.textInputEmailLogin)).getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return ((EditText) findViewById(R.id.textInputPasswordLogin)).getText().toString().trim();
    }

    @Override
    public void signUp() {
        Intent intent = new Intent(this, ChooseRegistrationActivity.class);
        startActivity(intent);
    }

    @Override
    public void showOrganizerFoundMessage(Integer organizerId) {
        new AlertDialog.Builder(LoginActivity.this)
                .setCancelable(true)
                .setTitle("Successful Organizer Login")
                .setMessage("The credentials you entered are correct. You will be redirected to the organizer's page.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        navigateToOrganizerHomePage(organizerId);
                    }
                })
                .create()
                .show();
    }

    @Override
    public void showCustomerFoundMessage(Integer customerId) {
        new AlertDialog.Builder(LoginActivity.this)
                .setCancelable(true)
                .setTitle("Successful Customer Login")
                .setMessage("The credentials you entered are correct. You will be redirected to the customer's page.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        navigateToCustomerHomePage(customerId);
                    }
                })
                .create()
                .show();
    }

    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(LoginActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    /**
     * Navigates to the organizer's home page after a successful login.
     *
     * @param organizerId The ID of the logged-in organizer.
     */
    public void navigateToOrganizerHomePage(Integer organizerId) {
        Intent intent = new Intent(this, OrganizerHomePageActivity.class);
        intent.putExtra("organizer_id", organizerId);
        startActivity(intent);
        finish();
    }

    /**
     * Navigates to the customer's home page after a successful login.
     *
     * @param customerId The ID of the logged-in customer.
     */
    public void navigateToCustomerHomePage(Integer customerId) {
        Intent intent = new Intent(this, HomePageActivity.class);
        intent.putExtra("customer_id", customerId);
        startActivity(intent);
        finish();
    }
}
