package com.example.eventhub.view.Account.Register.RegisterEditOrganizer;

import static com.example.eventhub.util.Util.checkAddress;
import static com.example.eventhub.util.Util.checkAlphabeticSmallLength;
import static com.example.eventhub.util.Util.checkEmail;
import static com.example.eventhub.util.Util.checkNonNegativeInteger;
import static com.example.eventhub.util.Util.checkPassword;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.dao.OrganizerDAO;
import com.example.eventhub.dao.UserDAO;
import com.example.eventhub.domain.Organizer;
import com.example.eventhub.util.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Presenter class for the Register/Edit Organizer feature. This class handles the communication
 * between the view (RegisterEditOrganizerView) and the data layer (UserDAO, OrganizerDAO).
 */
public class RegisterEditOrganizerPresenter {

    private RegisterEditOrganizerView view;
    private UserDAO userDAO;
    private OrganizerDAO organizerDAO;
    private Organizer organizer;

    /**
     * Constructor for the RegisterEditOrganizerPresenter.
     *
     * @param view         The associated view for the presenter.
     * @param userDAO      The data access object for user-related operations.
     * @param organizerDAO The data access object for organizer-related operations.
     */
    public RegisterEditOrganizerPresenter(RegisterEditOrganizerView view, UserDAO userDAO, OrganizerDAO organizerDAO) {
        this.view = view;
        this.userDAO = userDAO;
        this.organizerDAO = organizerDAO;

        Integer organizerId = view.getAttachedOrganizerId();
        organizer = organizerId == null ? null : organizerDAO.find(organizerId);

        if (organizer != null) { // edit mode
            view.setPageTitleToEdit("Edit Organizer");
            view.setButtonLabelToUpdate("Update");
            view.setOrganizerFirstName(organizer.getFirstName());
            view.setOrganizerLastName(organizer.getLastName());
            view.setOrganizerAge(organizer.getAge());
            view.setOrganizerGender(organizer.getGender());
            view.setOrganizerAddress(organizer.getAddress());
            view.setOrganizerEmail(organizer.getEmail());
            view.setOrganizerPassword(organizer.getPassword());
            view.setOrganizerSsn(organizer.getSsn());
            view.hideLoginPrompt();
        }
    }

    /**
     * Handles the save organizer action. Validates input fields and either initiates the registration
     * process or updates the existing organizer information.
     */
    public void onSaveOrganizer() {
        HashMap<String, Object> organizerInfo = view.getOrganizerInfo();

        for (Map.Entry<String, Object> entry : organizerInfo.entrySet()) {
            if (entry.getValue().toString().isEmpty() || entry.getValue() == null) {
                view.showErrorMessage("Error", "Please fill all the fields.");
                return;
            }
        }

        String organizerFirstName = (String) organizerInfo.get("firstname");
        String organizerLastName = (String) organizerInfo.get("lastname");
        Address organizerAddress = (Address) organizerInfo.get("address");
        String organizerGender = (String) organizerInfo.get("gender");
        Integer organizerAge = Integer.valueOf((String) organizerInfo.get("age"));
        String organizerEmail = (String) organizerInfo.get("email");
        String organizerPassword = (String) organizerInfo.get("password");
        Integer organizerSsn = Integer.valueOf((String) organizerInfo.get("ssn"));

        // all fields are filled, then check if they are valid
        if (!Util.checkAlphabeticSmallLength(organizerFirstName)) {
            view.showErrorMessage("Error", "Please fill 4 to 15 characters in firstname.");
        } else if (!Util.checkAlphabeticSmallLength(organizerLastName)) {
            view.showErrorMessage("Error", "Please fill 4 to 15 characters in lastname.");
        } else if (!Util.checkAddress(organizerAddress)) {
            view.showErrorMessage("Error", "Please fill all the fields correctly in address.");
        } else if (!Util.checkEmail(organizerEmail)) {
            view.showErrorMessage("Error", "Please select a valid email.");
        } else if (!Util.checkNonNegativeInteger(String.valueOf(organizerAge))) {
            view.showErrorMessage("Error", "Please select a non-negative age.");
        } else if (!Util.checkPassword(organizerPassword)) {
            view.showErrorMessage("Error", "Please fill up to 8 characters in password.");
        } else {
            if (organizer == null) {
                if (organizerDAO.findOrganizerWithAlreadyExistingEmail(new Organizer(organizerEmail)) != null) {
                    view.showErrorMessage("Error", "This email already exists. Try again!");
                    return;
                }

                view.moveToTermsAndConditions();
            } else {
                organizer.setFirstName(organizerFirstName);
                organizer.setLastName(organizerLastName);
                organizer.setAge(organizerAge);
                organizer.setGender(organizerGender);
                organizer.setAddress(organizerAddress);
                organizer.setEmail(organizerEmail);
                organizer.setPassword(organizerPassword);
                organizer.setSsn(organizerSsn);

                if (organizerDAO.findOrganizerWithAlreadyExistingEmail(organizer) != null) {
                    view.showErrorMessage("Error", "This email already exists. Try again!");
                    return;
                }

                userDAO.update(organizer);
                organizerDAO.update(organizer);

                view.showSuccessfulUpdateMessage(organizer.getId());
            }
        }
    }

    /**
     * Handles the login action by navigating the user to the login screen.
     */
    public void onLogin() {
        view.login();
    }

    /**
     * Handles the back action by navigating the user back to the previous screen.
     */
    public void onBack() {
        view.goBack();
    }
}
