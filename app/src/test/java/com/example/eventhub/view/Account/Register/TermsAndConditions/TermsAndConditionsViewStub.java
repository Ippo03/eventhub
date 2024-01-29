package com.example.eventhub.view.Account.Register.TermsAndConditions;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.domain.Interest;
import com.example.eventhub.view.Account.Register.TermsAndConditions.TermsAndConditionsView;

import java.util.HashMap;
import java.util.Set;

public class TermsAndConditionsViewStub implements TermsAndConditionsView {
    private String firstName, lastName, gender, age, email, password, ssn, agreement;
    private Address address;
    private Set<Interest> interests;
    private String errorTitle, errorMessage, successfulRegistrationMessage;
    private Integer errorCount;

    public TermsAndConditionsViewStub() {
        firstName = lastName = gender = age = email = password = ssn = "";
        errorTitle = errorMessage = successfulRegistrationMessage = "";
        errorCount = 0;
    }

    @Override
    public String getAgreement() {
        return agreement;
    }

    @Override
    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    @Override
    public void showErrorMessage(String error, String message) {
        errorTitle = error;
        errorMessage = message;
        errorCount++;
    }

    @Override
    public void showSuccessfulRegistrationMessage(int id, String type) {
        successfulRegistrationMessage = "You have successfully registered as " + type + ". Please login to continue.";
    }

    public HashMap<String, Object> getOrganizerDetails() {
        HashMap<String,Object> details = new HashMap<>();
        details.put("firstname", firstName);
        details.put("lastname",lastName);
        details.put("gender",gender);
        details.put("age",age);
        details.put("address",address);
        details.put("email",email);
        details.put("password",password);
        details.put("ssn",ssn);

        return details;
    }

    public HashMap<String, Object> getCustomerDetails() {
        HashMap<String,Object> details = new HashMap<>();
        details.put("firstname", firstName);
        details.put("lastname",lastName);
        details.put("gender",gender);
        details.put("age",age);
        details.put("address",address);
        details.put("email",email);
        details.put("password",password);
        details.put("interests",interests);

        return details;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName= lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setInterests(Set<Interest> interests) {
        this.interests = interests;
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

    public String getSuccessfulRegistrationMessage() {
        return successfulRegistrationMessage;
    }
}