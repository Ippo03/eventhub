package com.example.eventhub.view.Account.Register.RegisterEditCustomer;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.domain.Interest;
import com.example.eventhub.view.Account.Register.RegisterEditCustomer.RegisterEditCustomerView;

import java.util.HashMap;
import java.util.Set;

public class RegisterEditCustomerViewStub implements RegisterEditCustomerView {
    private String firstName, lastName, gender, age, email, password;
    private String errorTitle, errorMessage, successfulUpdateMessage, pageTitle, buttonLabel;
    private Integer attachedCustomerId, errorCount, backCount, loginCount, termsAndConditionsCount, multiSpinnerCount, hideLoginPromptCount;
    private Address address;
    private Set<Interest> interests;

    public RegisterEditCustomerViewStub() {
        firstName = lastName = gender = age = email = password = "";
        errorTitle = errorMessage = successfulUpdateMessage = "";
        errorCount = backCount = loginCount = termsAndConditionsCount = multiSpinnerCount = hideLoginPromptCount = 0;
    }

    @Override
    public Integer getAttachedCustomerId() {
        return attachedCustomerId;
    }

    public void setAttachedCustomerId(Integer attachedCustomerId) {
        this.attachedCustomerId = attachedCustomerId;
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
    public String getCustomerFirstName() {
        return firstName;
    }

    @Override
    public String getCustomerLastName() {
        return lastName;
    }

    @Override
    public String getCustomerGender() {
        return gender;
    }

    @Override
    public String getCustomerAge() {
        return age;
    }

    @Override
    public Address getCustomerAddress() {
        return address;
    }

    @Override
    public String getCustomerEmail() {
        return email;
    }

    @Override
    public String getCustomerPassword() {
        return password;
    }

    @Override
    public Set<Interest> getCustomerInterests() {
        return interests;
    }

    @Override
    public HashMap<String, Object> getCustomerInfo() {
        HashMap<String, Object> info = new HashMap<>();
        info.put("firstname", firstName);
        info.put("lastname", lastName);
        info.put("gender", gender);
        info.put("age", String.valueOf(age));
        info.put("address", address);
        info.put("email", email);
        info.put("password", password);
        info.put("interests", interests);

        return info;
    }

    @Override
    public void setCustomerFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setCustomerLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setCustomerGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void setCustomerAge(Integer age) {
        this.age = String.valueOf(age);
    }

    @Override
    public void setCustomerAddress(Address address) {
        this.address = address;
    }

    @Override
    public void setCustomerEmail(String email) {
        this.email = email;
    }

    @Override
    public void setCustomerPassword(String password) {
        this.password = password;
    }

    @Override
    public void setCustomerInterests(Set<Interest> interests) {
        this.interests = interests;
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
    public void showSuccessfulUpdateMessage(Integer customerId) {
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

    @Override
    public void setUpMultiSpinner() {
        multiSpinnerCount++;
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

    public Integer getMultiSpinnerCount() {
        return multiSpinnerCount;
    }

    public Integer getHideLoginPromptCount() {
        return hideLoginPromptCount;
    }
}