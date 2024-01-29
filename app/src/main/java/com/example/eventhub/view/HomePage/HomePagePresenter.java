package com.example.eventhub.view.HomePage;

import android.annotation.SuppressLint;

import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.OrganizerDAO;
import com.example.eventhub.dao.UserDAO;
import com.example.eventhub.domain.Customer;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.EventType;
import com.example.eventhub.domain.Genre;
import com.example.eventhub.util.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Presenter class for the Home Page, responsible for handling user interactions and coordinating with the view and data access objects.
 */
public class HomePagePresenter {

    private UserDAO userDAO;
    private OrganizerDAO organizerDAO;
    private CustomerDAO customerDAO;
    private EventDAO eventDAO;
    private HomePageView view;
    private Customer customer;
    private Set<Event> searchResult = new HashSet<>();
    private List<Event> eventList = new ArrayList<>();

    /**
     * Constructor for the HomePagePresenter.
     *
     * @param userDAO      Data access object for users.
     * @param organizerDAO Data access object for organizers.
     * @param customerDAO  Data access object for customers.
     * @param eventDAO     Data access object for events.
     */
    public HomePagePresenter(UserDAO userDAO, OrganizerDAO organizerDAO, CustomerDAO customerDAO, EventDAO eventDAO) {
        this.userDAO = userDAO;
        this.organizerDAO = organizerDAO;
        this.customerDAO = customerDAO;
        this.eventDAO = eventDAO;
    }

    /**
     * Sets the associated view for the presenter.
     *
     * @param view The view to be associated with the presenter.
     */
    public void setView(HomePageView view) {
        this.view = view;
    }

    /**
     * Gets the associated view of the presenter.
     *
     * @return The associated view.
     */
    public HomePageView getView() {
        return view;
    }

    /**
     * Sets the customer based on the provided customer ID.
     *
     * @param customerId The ID of the customer to be set.
     */
    public void setCustomer(Integer customerId) {
        if (customerId != null) {
            this.customer = customerDAO.find(customerId);
        }
    }

    /**
     * Gets the current customer associated with the presenter.
     *
     * @return The current customer.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the event list to all active events.
     */
    public void onSetFullList() {
        this.eventList = eventDAO.findAllActive();
    }

    /**
     * Sets the event list to events recommended for the logged-in customer based on their interests.
     */
    public void onSetRecommendedList() {
        ArrayList<Genre> customerInterestsAsGenres = Util.mapInterestsToGenres(customer.getInterests());
        this.eventList = eventDAO.findByGenreFirst(customerInterestsAsGenres);
    }

    /**
     * Sets the search result list based on the provided event list.
     *
     * @param eventList The list of events to set as the search result.
     */
    public void onSetSearchResultList(ArrayList<Event> eventList) {
        this.eventList = eventList;
        onDisplaySearchedEvents();
    }

    /**
     * Sets the initial event list based on the customer's interests or to all active events if not logged in.
     */
    public void onSetInitEventList() {
        if (customer == null) {
            onSetFullList();
        } else {
            onSetRecommendedList();
        }
        onShowEvents();
    }

    /**
     * Gets the current event list associated with the presenter.
     *
     * @return The current event list.
     */
    public List<Event> getEventList() {
        return eventList;
    }

    /**
     * Displays the searched events based on the search result.
     */
    public void onDisplaySearchedEvents() {
        if (searchResult.isEmpty()) {
            view.showEmptyEvents();
        } else {
            onShowEvents();
        }
    }

    /**
     * Shows the events in the view.
     */
    public void onShowEvents() {
        view.showEvents();
    }

    /**
     * Displays the appropriate buttons on the view based on the user's login status.
     */
    public void onDisplayButtons() {
        if (customer == null) {
            view.showHomePageButtons();
        } else {
            view.showCustomerHomePageButtons();
        }
    }

    /**
     * Initiates the login process in the view.
     */
    public void onLogin() {
        view.login();
    }

    /**
     * Initiates the registration process in the view.
     */
    public void onRegister() {
        view.register();
    }

    /**
     * Initiates the account editing process in the view.
     */
    public void onEditAccount() {
        view.editAccount();
    }

    /**
     * Initiates the purchases view in the view.
     */
    public void onPurchases() {
        view.purchases();
    }

    /**
     * Initiates the logout process in the view.
     */
    public void onLogout() {
        view.logout();
    }

    /**
     * Resets all applied filters and displays the initial event list.
     */
    public void onResetAllFilters() {
        searchResult.clear();
        view.resetFilters();
        onSetInitEventList();
    }

    /**
     * Gets the filters from the view and returns a list of criteria for searching events.
     *
     * @return The list of criteria for searching events.
     */
    @SuppressLint("NewApi")
    public List<Map<String, Object>> onGetFilters() {
        HashMap<String, Object> filters = view.getFilters();

        List<Map<String, Object>> criteriaList = new ArrayList<>();

        if (filters.get("title") != null) {
            Map<String, Object> titleCriterion = new HashMap<>();
            titleCriterion.put("type", "title");
            titleCriterion.put("value", filters.get("title"));
            criteriaList.add(titleCriterion);
        }

        if (filters.get("date") != null) {
            String from = ((ArrayList<String>) filters.get("date")).get(0);
            String to = (((ArrayList<String>) filters.get("date")).get(1));

            if ((!Util.checkDateFormat(from) && !from.equals("")) || (!Util.checkDateFormat(to) && !to.equals(""))) {
                view.showErrorMessage("Error", "Search dates must have this format dd-MM-yyyy.");
                return null;
            }

            LocalDate fromDate = from.equals("") ? LocalDate.now() : LocalDate.parse(from, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalDate toDate = to.equals("") ? LocalDate.now().plusYears(100) : LocalDate.parse(to, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            if (!fromDate.isAfter(LocalDate.now().plusDays(1)) && !toDate.isAfter(LocalDate.now())) {
                view.showErrorMessage("Error", "Search dates must be after today.");
                return null;
            }

            if (fromDate.isAfter(toDate)) {
                view.showErrorMessage("Error", "Search filter from cannot be after to.");
                return null;
            }

            ArrayList<LocalDate> dateCriterionValue = new ArrayList<>();
            dateCriterionValue.add(fromDate);
            dateCriterionValue.add(toDate);

            Map<String, Object> dateCriterion = new HashMap<>();
            dateCriterion.put("type", "date");
            dateCriterion.put("value", dateCriterionValue);
            criteriaList.add(dateCriterion);
        }

        Map<String, Object> genreCriterion = new HashMap<>();
        genreCriterion.put("type", "genre");
        genreCriterion.put("value", filters.get("genre"));
        criteriaList.add(genreCriterion);

        if (filters.get("type") != null) {
            Map<String, Object> typeCriterion = new HashMap<>();
            typeCriterion.put("type", "type");
            typeCriterion.put("value", filters.get("type"));
            criteriaList.add(typeCriterion);
        }

        if (filters.get("sorting") != null) {
            Map<String, Object> sortingCriterion = new HashMap<>();
            sortingCriterion.put("type", "sorting");
            sortingCriterion.put("value", filters.get("sorting"));
            criteriaList.add(sortingCriterion);
        }

        return criteriaList;
    }

    /**
     * Initiates the search process in the view.
     *
     * @return The list of events matching the search criteria.
     */
    public ArrayList<Event> onSearch() {
        List<Map<String, Object>> criteriaList = onGetFilters();
        searchResult.clear();

        if (criteriaList == null || Util.noFilterUsed(criteriaList)) {
            searchResult.addAll(eventDAO.findAllActive());
            ArrayList<Event> searchResultList = new ArrayList<>();
            searchResultList.addAll(searchResult);
            return searchResultList;
        }

        Set<Event> titleResult = new HashSet<>();
        Set<Event> dateResult = new HashSet<>();
        Set<Event> genreResult = new HashSet<>();
        Set<Event> typeResult = new HashSet<>();

        String sortingMethod = "";

        Object criterionValue;
        for (Map<String, Object> criterion : criteriaList) {

            String criterionType = (String) criterion.get("type");
            criterionValue = criterion.get("value");

            switch (criterionType.toLowerCase()) {
                case "title":
                    String titleCriterionValue = (String) criterionValue;
                    Set<Event> tempEventsText = eventDAO.findByText(titleCriterionValue);

                    titleResult.addAll(tempEventsText);

                    break;
                case "date":
                    ArrayList<LocalDate> dateCriterionValue = (ArrayList<LocalDate>) criterionValue;
                    Set<Event> tempEventsDate = eventDAO.findByDateRange(dateCriterionValue.get(0), dateCriterionValue.get(1));

                    if (!tempEventsDate.isEmpty()) {
                        dateResult.addAll(tempEventsDate);
                    }

                    break;
                case "genre":
                    ArrayList<Genre> genreCriterionValue = (ArrayList<Genre>) criterionValue;
                    ArrayList<Event> tempEventsGenre = eventDAO.findByGenre(genreCriterionValue);

                    genreResult.addAll(new HashSet<>(tempEventsGenre));

                    break;
                case "type":
                    ArrayList<EventType> typeCriterionValue = (ArrayList<EventType>) criterionValue;
                    Set<Event> tempEventsType = eventDAO.findByType(typeCriterionValue);

                    if (!tempEventsType.isEmpty()) {
                        typeResult.addAll(tempEventsType);
                    }

                    break;
                case "sorting":
                    sortingMethod = (String) criterionValue;
                    break;
                default:
                    break;
            }
        }

        searchResult.addAll(eventDAO.findAll());

        if ((!criteriaList.get(0).get("value").equals("") && titleResult.isEmpty()) || !titleResult.isEmpty()) {
            searchResult.retainAll(titleResult);
        }

        searchResult.retainAll(dateResult);
        searchResult.retainAll(genreResult);
        searchResult.retainAll(typeResult);

        ArrayList<Event> searchResultList = new ArrayList<>();
        searchResultList.addAll(searchResult);

        if (!sortingMethod.equals("")) {
            searchResultList = Util.appropriateSort(searchResultList, sortingMethod, eventDAO);
        }

        return searchResultList;
    }

    /**
     * Gets the current search result associated with the presenter.
     *
     * @return The current search result.
     */
    public Set<Event> getSearchResult() {
        return searchResult;
    }
}
