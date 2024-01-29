package com.example.eventhub.view.Account.Login;

import com.example.eventhub.view.Account.Login.LoginView;

public class LoginViewStub implements LoginView {
    private String email, password, organizerFoundMessage, customerFoundMessage, errorTitle, errorMessage;
    private Integer errorCount, signUpCount;

    public LoginViewStub() {
        email = password = organizerFoundMessage = customerFoundMessage = errorTitle = errorMessage = "";
        errorCount = signUpCount = 0;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void signUp() {
        signUpCount++;
    }

    @Override
    public void showOrganizerFoundMessage(Integer organizerId) {
        organizerFoundMessage = "Redirecting to organizer home page with id " + organizerId + ".";
    }

    @Override
    public void showCustomerFoundMessage(Integer customerId) {
        customerFoundMessage = "Redirecting to customer home page with id " + customerId + ".";
    }

    @Override
    public void showErrorMessage(String title, String message) {
        errorTitle = title;
        errorMessage = message;
        errorCount++;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public Integer getSignUpCount() {
        return signUpCount;
    }

    public String getOrganizerFoundMessage() {
        return organizerFoundMessage;
    }

    public String getCustomerFoundMessage() {
        return customerFoundMessage;
    }
}