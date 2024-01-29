package com.example.eventhub.view.Account.Register.RegisterEditCustomer;

import static com.example.eventhub.util.Util.checkAddress;
import static com.example.eventhub.util.Util.checkAlphabeticSmallLength;
import static com.example.eventhub.util.Util.checkEmail;
import static com.example.eventhub.util.Util.checkNonNegativeInteger;
import static com.example.eventhub.util.Util.checkPassword;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.dao.UserDAO;
import com.example.eventhub.domain.Customer;
import com.example.eventhub.domain.Interest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The RegisterEditCustomerPresenter class handles the presentation logic for customer registration and editing.
 * It interacts with the view and data access objects to perform operations related to customer accounts.
 */
public class RegisterEditCustomerPresenter {
    private RegisterEditCustomerView view;
    private UserDAO userDAO;
    private CustomerDAO customerDAO;
    private Customer customer;

    /**
     * Constructs a RegisterEditCustomerPresenter with the specified view and data access objects.
     *
     * @param view         The view associated with the presenter.
     * @param usersDAO     The User Data Access Object.
     * @param customersDAO The Customer Data Access Object.
     */
    public RegisterEditCustomerPresenter(RegisterEditCustomerView view, UserDAO usersDAO, CustomerDAO customersDAO) {
        this.view = view;
        this.userDAO = usersDAO;
        this.customerDAO = customersDAO;

        Integer customerId = view.getAttachedCustomerId();
        customer = customerId == null ? null : customerDAO.find(customerId);

        if (customer != null) { // edit mode
            view.setPageTitleToEdit("Edit Customer");
            view.setButtonLabelToUpdate("Update");
            view.setCustomerFirstName(customer.getFirstName());
            view.setCustomerLastName(customer.getLastName());
            view.setCustomerAge(customer.getAge());
            view.setCustomerGender(customer.getGender());
            view.setCustomerAddress(customer.getAddress());
            view.setCustomerEmail(customer.getEmail());
            view.setCustomerPassword(customer.getPassword());
            view.setCustomerInterests(customer.getInterests());
            view.hideLoginPrompt();
        } else {
            view.setUpMultiSpinner();
        }
    }

    /**
     * Handles the save customer action. Validates input and performs save or update accordingly.
     */
    public void onSaveCustomer() {
        HashMap<String, Object> customerInfo = view.getCustomerInfo();

        for (Map.Entry<String, Object> entry : customerInfo.entrySet()) {
            if (entry.getValue().toString().isEmpty() || entry.getValue() == null) {
                view.showErrorMessage("Error", "Please fill all the fields.");
                return;
            }
        }

        String customerFirstName = (String) customerInfo.get("firstname");
        String customerLastName = (String) customerInfo.get("lastname");
        Address customerAddress = (Address) customerInfo.get("address");
        String customerGender = (String) customerInfo.get("gender");
        Integer customerAge = Integer.valueOf((String) customerInfo.get("age"));
        String customerEmail = (String) customerInfo.get("email");
        String customerPassword = (String) customerInfo.get("password");
        Set<Interest> customerInterests = new HashSet<>((Set<Interest>) customerInfo.get("interests"));

        if (!checkAlphabeticSmallLength(customerFirstName)) {
            view.showErrorMessage("Error", "Please fill 4 to 15 characters in firstname.");
        } else if (!checkAlphabeticSmallLength(customerLastName)) {
            view.showErrorMessage("Error", "Please fill 4 to 15 characters in lastname.");
        } else if (!checkAddress(customerAddress)) {
            view.showErrorMessage("Error", "Please fill all the fields correctly in address.");
        } else if (!checkEmail(customerEmail)) {
            view.showErrorMessage("Error", "Please select a valid email.");
        } else if (!checkNonNegativeInteger(String.valueOf(customerAge))) {
            view.showErrorMessage("Error", "Please select a non-negative age.");
        } else if (!checkPassword(customerPassword)) {
            view.showErrorMessage("Error", "Please fill up to 8 characters in password.");
        } else {
            if (customer == null) {
                if (customerDAO.findCustomerWithAlreadyExistingEmail(new Customer(customerEmail)) != null) {
                    view.showErrorMessage("Error", "This email already exists. Try again!");
                    return;
                }

                view.moveToTermsAndConditions();
            } else {
                customer.setFirstName(customerFirstName);
                customer.setLastName(customerLastName);
                customer.setAge(customerAge);
                customer.setGender(customerGender);
                customer.setAddress(customerAddress);
                customer.setEmail(customerEmail);
                customer.setPassword(customerPassword);
                customer.setInterests(customerInterests);

                if (customerDAO.findCustomerWithAlreadyExistingEmail(customer) != null) {
                    view.showErrorMessage("Error", "This email already exists. Try again!");
                    return;
                }

                userDAO.update(customer);
                customerDAO.update(customer);

                view.showSuccessfulUpdateMessage(customer.getId());
            }
        }
    }

    /**
     * Handles the login action.
     */
    public void onLogin() {
        view.login();
    }

    /**
     * Handles the back action.
     */
    public void onBack() {
        view.goBack();
    }
}
