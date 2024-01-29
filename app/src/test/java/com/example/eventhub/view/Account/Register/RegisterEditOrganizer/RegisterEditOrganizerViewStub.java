package com.example.eventhub.view.Account.Register.RegisterEditOrganizer;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.view.Account.Register.RegisterEditOrganizer.RegisterEditOrganizerView;

import java.util.HashMap;

public class RegisterEditOrganizerViewStub implements RegisterEditOrganizerView {
    private String firstName, lastName, gender, age, email, password, ssn;
    private String errorTitle, errorMessage, successfulUpdateMessage, pageTitle, buttonLabel;
    private Integer attachedOrganizerId, errorCount, backCount, loginCount, termsAndConditionsCount, hideLoginPromptCount;
    private Address address;

    public RegisterEditOrganizerViewStub() {
        firstName = lastName = gender = age = email = password = ssn = "";
        errorTitle = errorMessage = successfulUpdateMessage = "";
        errorCount = backCount = loginCount = termsAndConditionsCount = hideLoginPromptCount = 0;
    }

    @Override
    public Integer getAttachedOrganizerId() {
        return attachedOrganizerId;
    }

    public void setAttachedOrganizerId(Integer attachedOrganizerId) {
        this.attachedOrganizerId = attachedOrganizerId;
    }

    @Override
    public void setPageTitleToEdit(String title) {
        pageTitle = title;
    }

    @Override
    public void setButtonLabelToUpdate(String label) {
        buttonLabel = label;
    }

    @Override
    public String getOrganizerFirstName() {
        return firstName;
    }

    @Override
    public String getOrganizerLastName() {
        return lastName;
    }

    @Override
    public String getOrganizerGender() {
        return gender;
    }

    @Override
    public String getOrganizerAge() {
        return age;
    }

    @Override
    public Address getOrganizerAddress() {
        return address;
    }

    @Override
    public String getOrganizerEmail() {
        return email;
    }

    @Override
    public String getOrganizerPassword() {
        return password;
    }

    @Override
    public String getOrganizerSsn() {
        return ssn;
    }

    @Override
    public HashMap<String, Object> getOrganizerInfo() {
        HashMap<String, Object> info = new HashMap<>();
        info.put("firstname", firstName);
        info.put("lastname", lastName);
        info.put("gender", gender);
        info.put("age", String.valueOf(age));
        info.put("address", address);
        info.put("email", email);
        info.put("password", password);
        info.put("ssn", String.valueOf(ssn));

        return info;
    }

    @Override
    public void setOrganizerFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setOrganizerLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setOrganizerAge(Integer age) {
        this.age = String.valueOf(age);
    }

    @Override
    public void setOrganizerGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void setOrganizerAddress(Address address) {
        this.address = address;
    }

    @Override
    public void setOrganizerEmail(String email) {
        this.email = email;
    }

    @Override
    public void setOrganizerPassword(String password) {
        this.password = password;
    }

    @Override
    public void setOrganizerSsn(Integer ssn) {
        this.ssn = String.valueOf(ssn);
    }

    @Override
    public void showErrorMessage(String title, String message) {
        errorTitle = title;
        errorMessage = message;
        errorCount++;
    }

    @Override
    public void moveToTermsAndConditions() {
        termsAndConditionsCount++;
    }

    @Override
    public void showSuccessfulUpdateMessage(Integer organizerId) {
        successfulUpdateMessage = "Your account has been updated successfully!";
    }

    @Override
    public void hideLoginPrompt() {
        hideLoginPromptCount++;
    }

    @Override
    public void login() {
        loginCount++;
    }

    @Override
    public void goBack() {
        backCount++;
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

    public String getSuccessfulUpdateMessage() {
        return successfulUpdateMessage;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public Integer getBackCount() {
        return backCount;
    }

    public Integer getTermsAndConditionsCount() {
        return termsAndConditionsCount;
    }

    public Integer getHideLoginPromptCount() {
        return hideLoginPromptCount;
    }
}