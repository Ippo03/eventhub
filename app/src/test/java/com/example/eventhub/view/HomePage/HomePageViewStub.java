package com.example.eventhub.view.HomePage;

import com.example.eventhub.domain.EventType;
import com.example.eventhub.domain.Genre;
import com.example.eventhub.view.HomePage.HomePageView;

import java.util.ArrayList;
import java.util.HashMap;

public class HomePageViewStub implements HomePageView {
    private String eventName, eventSorting;
    private ArrayList<Genre> eventGenres;
    private ArrayList<EventType> eventTypes;
    private ArrayList<String> eventDateRange;
    private String errorTitle, errorMessage;
    private int errorCount, showHomePageButtonsCount, showCustomerHomePageButtonsCount, loginCount, registerCount, editAccountCount, purchasesCount, logoutCount, searchCount, showEventsCount, showEmptyEventsCount;
    private HashMap<String, Object> filters = new HashMap<>();

    public HomePageViewStub() {
        eventName = eventSorting = "";
        eventGenres = new ArrayList<>();
        eventTypes = new ArrayList<>();
        eventDateRange = new ArrayList<>();
        errorCount = showHomePageButtonsCount = showCustomerHomePageButtonsCount = loginCount = registerCount = editAccountCount = purchasesCount = logoutCount = searchCount = showEventsCount = showEmptyEventsCount = 0;
    }

    @Override
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public ArrayList getEventGenres() {
        return eventGenres;
    }

    public void setEventGenres(ArrayList<Genre> eventGenres) {
        this.eventGenres = eventGenres;
    }

    @Override
    public ArrayList getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(ArrayList<EventType> eventTypes) {
        this.eventTypes = eventTypes;
    }

    @Override
    public ArrayList<String> getEventDateRange() {
        return eventDateRange;
    }

    public void setEventDateRange(ArrayList<String> eventDateRange) {
        this.eventDateRange = eventDateRange;
    }

    @Override
    public String getEventSorting() {
        return eventSorting;
    }

    public void setEventSorting(String eventSorting) {
        this.eventSorting = eventSorting;
    }

    @Override
    public void setDefaultEventName() {
        eventName = "";
    }

    @Override
    public void setDefaultEventGenres() {
        eventGenres = new ArrayList<>();
    }

    @Override
    public void setDefaultEventTypes() {
        eventTypes = new ArrayList<>();
    }

    @Override
    public void setDefaultEventFromDate() {
        eventDateRange = new ArrayList<>();
    }

    @Override
    public void setDefaultEventToDate() {
        eventDateRange = new ArrayList<>();
    }

    @Override
    public void setDefaultEventSorting() {
        eventSorting = "";
    }

    @Override
    public void resetFilters() {
        setDefaultEventName();
        setDefaultEventGenres();
        setDefaultEventTypes();
        setDefaultEventFromDate();
        setDefaultEventSorting();
    }

    @Override
    public HashMap<String, Object> getFilters() {
        HashMap<String, Object> filters = new HashMap<>();

        if (eventGenres.size() == 0) {
            eventGenres.add(Genre.CINEMA);
            eventGenres.add(Genre.FOOD);
            eventGenres.add(Genre.MUSIC);
            eventGenres.add(Genre.SCIENCE);
            eventGenres.add(Genre.SPORTS);
            eventGenres.add(Genre.BUSINESS);
            eventGenres.add(Genre.EDUCATION);
            eventGenres.add(Genre.POLITICS);
            eventGenres.add(Genre.OTHER);
        }

        if (eventTypes.size() == 0) {
            eventTypes.add(EventType.OPEN);
            eventTypes.add(EventType.CLOSED);
            eventTypes.add(EventType.FREE);
        }

//        if (eventDateRange.size() == 0) {
//            eventDateRange.add(LocalDate.now());
//            eventDateRange.add(LocalDate.now().plusYears(100));
//        }

        if (eventSorting.equals("")) {
            eventSorting = "Capacity";
        }

        filters.put("title", eventName);
        filters.put("genre", eventGenres);
        filters.put("type", eventTypes);
        filters.put("date", eventDateRange);
        filters.put("sorting", eventSorting);

        return filters;
    }

    @Override
    public void showHomePageButtons() {
        showHomePageButtonsCount++;
    }

    @Override
    public void showCustomerHomePageButtons() {
        showCustomerHomePageButtonsCount++;
    }

    @Override
    public void login() {
        loginCount++;
    }

    @Override
    public void register() {
        registerCount++;
    }

    @Override
    public void editAccount() {
        editAccountCount++;
    }

    @Override
    public void purchases() {
        purchasesCount++;
    }

    @Override
    public void logout() {
        logoutCount++;
    }

    @Override
    public ArrayList search() {
        searchCount++;
        return new ArrayList();
    }

    @Override
    public void showEvents() {
        showEventsCount++;
    }

    @Override
    public void showEmptyEvents() {
        showEmptyEventsCount++;
    }

    @Override
    public void showErrorMessage(String title, String message) {
        errorCount++;
        errorTitle = title;
        errorMessage = message;
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

    public Integer getShowHomePageButtonsCount() {
        return showHomePageButtonsCount;
    }

    public Integer getShowCustomerHomePageButtonsCount() {
        return showCustomerHomePageButtonsCount;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public Integer getRegisterCount() {
        return registerCount;
    }

    public Integer getEditAccountCount() {
        return editAccountCount;
    }

    public Integer getPurchasesCount() {
        return purchasesCount;
    }

    public Integer getLogoutCount() {
        return logoutCount;
    }

    public Integer getSearchCount() {
        return searchCount;
    }

    public Integer getShowEventsCount() {
        return showEventsCount;
    }

    public Integer getShowEmptyEventsCount() {
        return showEmptyEventsCount;
    }
}
