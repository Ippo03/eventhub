package com.example.eventhub.view.HomePage;

import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.Initializer;
import com.example.eventhub.dao.OrganizerDAO;
import com.example.eventhub.dao.UserDAO;
import com.example.eventhub.domain.EventType;
import com.example.eventhub.domain.Genre;
import com.example.eventhub.memorydao.CustomerDAOMemory;
import com.example.eventhub.memorydao.EventDAOMemory;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.memorydao.OrganizerDAOMemory;
import com.example.eventhub.memorydao.UserDAOMemory;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Unit tests for the HomePagePresenter class, covering various functionalities related to the home page.
 */
public class HomePagePresenterTest {
    private HomePageViewStub view;
    private HomePagePresenter presenter;

    private EventDAO eventDAO;
    private UserDAO userDAO;
    private OrganizerDAO organizerDAO;
    private CustomerDAO customerDAO;

    @Before
    public void setUp() {
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        view = new HomePageViewStub();

        eventDAO = new EventDAOMemory();
        userDAO = new UserDAOMemory();
        organizerDAO = new OrganizerDAOMemory();
        customerDAO = new CustomerDAOMemory();

        presenter = new HomePagePresenter(userDAO, organizerDAO, customerDAO, eventDAO);
        presenter.setView(view);
    }

    /**
     * Test case for setting the view of the presenter.
     * Validates that the presenter's view is correctly set.
     */
    @Test
    public void onSetView() {
        HomePageView newView = new HomePageViewStub();
        presenter.setView(newView);
        Assertions.assertEquals(newView, presenter.getView());
    }

    /**
     * Test case for setting a null customer in the presenter.
     * Validates that the customer in the presenter is correctly set to null.
     */
    @Test
    public void onSetNullCustomer() {
        presenter.setCustomer(null);
        Assertions.assertNull(presenter.getCustomer());
    }

    /**
     * Test case for setting a customer in the presenter.
     * Validates that the customer in the presenter is correctly set.
     */
    @Test
    public void onSetCustomer() {
        presenter.setCustomer(4);
        Assertions.assertEquals(4, presenter.getCustomer().getId());
    }

    /**
     * Test case for setting the full list of events in the presenter.
     * Validates that the full list of events is correctly set in the presenter.
     */
    @Test
    public void onSetFullList() {
        presenter.onSetFullList();
        Assertions.assertEquals(11, presenter.getEventList().size());
    }

    /**
     * Test case for setting the recommended list of events with a customer in the presenter.
     * Validates that the recommended list of events is correctly set in the presenter with a customer.
     */
    @Test
    public void onSetRecommendedList() {
        presenter.setCustomer(4);
        presenter.onSetInitEventList();
        Assertions.assertEquals(11, presenter.getEventList().size());
        Assertions.assertEquals(1, view.getShowEventsCount());
    }

    /**
     * Test case for setting the initial list of events with a null customer in the presenter.
     * Validates that the initial list of events is correctly set in the presenter with a null customer.
     */
    @Test
    public void onSetEventListWithNullCustomer() {
        presenter.setCustomer(null);
        presenter.onSetInitEventList();
        Assertions.assertEquals(11, presenter.getEventList().size());
        Assertions.assertEquals(1, view.getShowEventsCount());
    }

    /**
     * Test case for setting the list of recommended events with a customer in the presenter.
     * Validates that the list of recommended events is correctly set in the presenter with a customer.
     */
    @Test
    public void onSetEventListWithCustomer() {
        presenter.setCustomer(4);
        presenter.onSetRecommendedList();
        Assertions.assertEquals(11, presenter.getEventList().size());
    }

    /**
     * Test case for displaying buttons with a null customer in the presenter.
     * Validates that the appropriate method is called for displaying home page buttons with a null customer.
     */
    @Test
    public void onDisplayButtonsWithNullCustomer() {
        presenter.setCustomer(null);
        presenter.onDisplayButtons();
        Assertions.assertEquals(1, view.getShowHomePageButtonsCount());
        Assertions.assertEquals(0, view.getShowCustomerHomePageButtonsCount());
    }

    /**
     * Test case for displaying buttons with a customer in the presenter.
     * Validates that the appropriate method is called for displaying home page buttons with a customer.
     */
    @Test
    public void onDisplayButtonsWithCustomer() {
        presenter.setCustomer(4);
        presenter.onDisplayButtons();
        Assertions.assertEquals(0, view.getShowHomePageButtonsCount());
        Assertions.assertEquals(1, view.getShowCustomerHomePageButtonsCount());
    }

    /**
     * Test case for handling the login action.
     * Validates that the appropriate method is called for handling the login action.
     */
    @Test
    public void onLogin() {
        presenter.setCustomer(null);
        presenter.onLogin();
        Assertions.assertEquals(1, view.getLoginCount());
    }

    /**
     * Test case for handling the registration action.
     * Validates that the appropriate method is called for handling the registration action.
     */
    @Test
    public void onRegister() {
        presenter.setCustomer(null);
        presenter.onRegister();
        Assertions.assertEquals(1, view.getRegisterCount());
    }

    /**
     * Test case for handling the account editing action.
     * Validates that the appropriate method is called for handling the account editing action.
     */
    @Test
    public void onEditAccount() {
        presenter.setCustomer(4);
        presenter.onEditAccount();
        Assertions.assertEquals(1, view.getEditAccountCount());
    }

    /**
     * Test case for handling the purchases action.
     * Validates that the appropriate method is called for handling the purchases action.
     */
    @Test
    public void onPurchases() {
        presenter.setCustomer(4);
        presenter.onPurchases();
        Assertions.assertEquals(1, view.getPurchasesCount());
    }

    /**
     * Test case for handling the logout action.
     * Validates that the appropriate method is called for handling the logout action.
     */
    @Test
    public void onLogout() {
        presenter.setCustomer(4);
        presenter.onLogout();
        Assertions.assertEquals(1, view.getLogoutCount());
    }

    /**
     * Test case for resetting all filters.
     * Validates that the filters and search results are reset, and the view is updated accordingly.
     */
    @Test
    public void onResetAllFilters() {
        presenter.onResetAllFilters();
        Assertions.assertEquals(0, presenter.getSearchResult().size());
        Assertions.assertEquals("", view.getEventName());
        Assertions.assertEquals(1, view.getShowEventsCount());
    }

    /**
     * Test case for getting filters with an invalid date format.
     * Validates that an error is displayed when the date format is invalid.
     */
    @Test
    public void onGetFiltersWithInvalidFormatDate() {
        ArrayList<String> dateRange = new ArrayList<>();
        dateRange.add("01/01/2023");
        dateRange.add("01/01/2023");
        view.setEventDateRange(dateRange);

        presenter.onGetFilters();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Search dates must have this format dd-MM-yyyy.", view.getErrorMessage());
    }

    /**
     * Test case for getting filters with an invalid date range.
     * Validates that an error is displayed when the date range is invalid.
     */
    @Test
    public void onGetFiltersWithInvalidRange() {
        ArrayList<String> dateRange = new ArrayList<>();
        dateRange.add("01-01-2025");
        dateRange.add("01-01-2024");
        view.setEventDateRange(dateRange);

        presenter.onGetFilters();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Search filter from cannot be after to.", view.getErrorMessage());
    }

    /**
     * Test case for getting filters with a date before today.
     * Validates that an error is displayed when the date is before today.
     */
    @Test
    public void onGetFiltersWithDateBeforeToday() {
        ArrayList<String> dateRange = new ArrayList<>();
        dateRange.add("01-01-2020");
        dateRange.add("01-01-2021");
        view.setEventDateRange(dateRange);

        presenter.onGetFilters();

        Assertions.assertEquals(1, view.getErrorCount());
        Assertions.assertEquals("Error", view.getErrorTitle());
        Assertions.assertEquals("Search dates must be after today.", view.getErrorMessage());
    }

    /**
     * Test case for getting filters from the view.
     * Validates that the filters are retrieved correctly from the view.
     */
    @Test
    public void onGetFilters() {
        view.setEventName("House Party");

        ArrayList<Genre> genreList = new ArrayList<>();
        genreList.add(Genre.MUSIC);
        genreList.add(Genre.SCIENCE);
        view.setEventGenres(genreList);

        ArrayList<EventType> eventTypeList = new ArrayList<>();
        eventTypeList.add(EventType.OPEN);
        eventTypeList.add(EventType.CLOSED);
        view.setEventTypes(eventTypeList);

        ArrayList<String> dateRange = new ArrayList<>();
        dateRange.add("01-01-2024");
        dateRange.add("01-01-2025");
        view.setEventDateRange(dateRange);

        view.setEventSorting("Capacity");

        presenter.onGetFilters();

        Assertions.assertEquals("House Party", presenter.onGetFilters().get(0).get("value"));
        Assertions.assertEquals(2, ((ArrayList<LocalDate>) presenter.onGetFilters().get(1).get("value")).size());
        Assertions.assertEquals(2, ((ArrayList<Genre>) presenter.onGetFilters().get(2).get("value")).size());
        Assertions.assertEquals(2, ((ArrayList<EventType>) presenter.onGetFilters().get(3).get("value")).size());
        Assertions.assertEquals("Capacity", presenter.onGetFilters().get(4).get("value"));
    }

    /**
     * Test case for searching with no filters.
     * Validates that the search result is the full event list when no filters are applied.
     */
    @Test
    public void onSearchWithNoFilters() {
       view.setDefaultEventName();

        ArrayList<Genre> genreList = new ArrayList<>();
        genreList.add(Genre.ART);
        genreList.add(Genre.MUSIC);
        genreList.add(Genre.CINEMA);
        genreList.add(Genre.EDUCATION);
        genreList.add(Genre.POLITICS);
        genreList.add(Genre.SCIENCE);
        genreList.add(Genre.SPORTS);
        genreList.add(Genre.FOOD);
        genreList.add(Genre.BUSINESS);

        view.setEventGenres(genreList);

        ArrayList<EventType> eventTypeList = new ArrayList<>();
        eventTypeList.add(EventType.CLOSED);
        eventTypeList.add(EventType.OPEN);
        eventTypeList.add(EventType.FREE);
        view.setEventTypes(eventTypeList);

        ArrayList<String> dateRange = new ArrayList<>();
        dateRange.add("");
        dateRange.add("");
        view.setEventDateRange(dateRange);

        view.setEventSorting("Capacity");

        presenter.onSetSearchResultList(presenter.onSearch());

        Assertions.assertEquals(11, presenter.getSearchResult().size());
    }

    /**
     * Test case for searching with filters.
     * Validates that the search result is filtered based on the specified criteria.
     */
    @Test
    public void onSearch() {
        view.setEventName("Language Learning Workshop");

        ArrayList<Genre> genreList = new ArrayList<>();
        genreList.add(Genre.MUSIC);
        genreList.add(Genre.EDUCATION);
        view.setEventGenres(genreList);

        ArrayList<EventType> eventTypeList = new ArrayList<>();
        eventTypeList.add(EventType.CLOSED);
        view.setEventTypes(eventTypeList);

        ArrayList<String> dateRange = new ArrayList<>();
        dateRange.add("01-01-2024");
        dateRange.add("01-01-2025");
        view.setEventDateRange(dateRange);

        view.setEventSorting("Capacity");

        presenter.onSetSearchResultList(presenter.onSearch());

        Assertions.assertEquals(1, presenter.getSearchResult().size());
        Assertions.assertEquals(1, view.getShowEventsCount());
    }

    /**
     * Test case for displaying empty search results.
     * Validates that the presenter handles and displays empty search results appropriately.
     */
    @Test
    public void onDisplayEmptySearchResult() {
        view.setEventName("");

        ArrayList<Genre> genreList = new ArrayList<>();
        genreList.add(Genre.POLITICS);
        view.setEventGenres(genreList);

        ArrayList<EventType> eventTypeList = new ArrayList<>();
        eventTypeList.add(EventType.CLOSED);
        view.setEventTypes(eventTypeList);

        ArrayList<String> dateRange = new ArrayList<>();
        dateRange.add("01-01-2024");
        dateRange.add("01-01-2025");
        view.setEventDateRange(dateRange);

        view.setEventSorting("Capacity");

        presenter.onSetSearchResultList(presenter.onSearch());

        Assertions.assertEquals(0, presenter.getSearchResult().size());
        Assertions.assertEquals(1, view.getShowEmptyEventsCount());
    }
}
