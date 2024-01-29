package com.example.eventhub.view.Account.Register.TermsAndConditions;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.dao.OrganizerDAO;
import com.example.eventhub.dao.UserDAO;
import com.example.eventhub.domain.Customer;
import com.example.eventhub.domain.Interest;
import com.example.eventhub.domain.Organizer;

import java.util.HashMap;
import java.util.Set;

/**
 * Presenter class for handling the Terms and Conditions screen during user registration.
 * It manages the interaction between the view and the data access objects.
 */
public class TermsAndConditionsPresenter {

    private TermsAndConditionsView view;
    private UserDAO userDAO;
    private CustomerDAO customerDAO;
    private OrganizerDAO organizerDAO;

    /**
     * Constructs a new TermsAndConditionsPresenter with the specified DAOs.
     *
     * @param userDAO      The User Data Access Object.
     * @param customersDAO The Customer Data Access Object.
     * @param organizerDAO The Organizer Data Access Object.
     */
    public TermsAndConditionsPresenter(UserDAO userDAO, CustomerDAO customersDAO, OrganizerDAO organizerDAO) {
        this.userDAO = userDAO;
        this.customerDAO = customersDAO;
        this.organizerDAO = organizerDAO;
    }

    /**
     * Sets the view associated with this presenter.
     *
     * @param view The TermsAndConditionsView instance.
     */
    public void setView(TermsAndConditionsView view) {
        this.view = view;
    }

    /**
     * Gets the view associated with this presenter.
     *
     * @return The TermsAndConditionsView instance.
     */
    public TermsAndConditionsView getView() {
        return view;
    }

    /**
     * Handles the user registration based on the provided information and user type.
     *
     * @param info The user information.
     * @param type The type of user (organizer or customer).
     */
    public void onRegister(HashMap<String, Object> info, String type) {
        if (view.getAgreement().equals("Agree")) {
            if (type.equals("organizer")) {
                Organizer organizer = new Organizer(organizerDAO.nextId(), (String) info.get("firstname"), (String) info.get("lastname"), (String) info.get("email"), Integer.valueOf((String) info.get("age")),
                        (String) info.get("gender"), (Address) info.get("address"), (String) info.get("password"), Integer.valueOf((String) info.get("ssn")));

                userDAO.save(organizer);
                organizerDAO.save(organizer);

                view.showSuccessfulRegistrationMessage(organizer.getId(), "organizer");
            } else {
                Customer customer = new Customer(customerDAO.nextId(), (String) info.get("firstname"), (String) info.get("lastname"), (String) info.get("email"), Integer.valueOf((String) info.get("age")),
                        (String) info.get("gender"), (Address) info.get("address"), (String) info.get("password"), (Set<Interest>) info.get("interests"));

                userDAO.save(customer);
                customerDAO.save(customer);

                view.showSuccessfulRegistrationMessage(customer.getId(), "customer");
            }
        } else {
            view.showErrorMessage("Error", "Please agree to the terms and conditions.");
        }
    }
}
