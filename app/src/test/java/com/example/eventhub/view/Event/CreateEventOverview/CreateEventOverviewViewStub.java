package com.example.eventhub.view.Event.CreateEventOverview;

import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;
import com.example.eventhub.view.Event.CreateEventOverview.CreateEventOverviewView;

import java.util.ArrayList;

public class CreateEventOverviewViewStub implements CreateEventOverviewView {
    private String eventName, eventAddress, eventDate, eventTime, eventGenre, eventType;
    private String ticketCategoryName, ticketCategoryPrice, ticketCategoryDescription, ticketCategoryQuantity;
    private String ticketDiscountType, ticketDiscountPercentage;
    private String errorTitle, errorMessage, successfulUpdateMessage, successfulCreationMessage;
    private Integer errorCount, showTicketCategoriesCount, showEmptyTicketCategoriesCount, showTicketDiscountsCount, showEmptyTicketDiscountsCount, moveToSelectCategoriesCount, moveToSelectDiscountsCount, navigateToOrganizerHomePageCount;
    private Integer attachedOrganizerId;
    private Event attachedEvent;
    private ArrayList<TicketCategory> attachedTicketCategories;
    private ArrayList<TicketDiscount> attachedTicketDiscounts;

    public CreateEventOverviewViewStub() {
        ticketCategoryName = ticketCategoryPrice = ticketCategoryDescription = ticketCategoryQuantity = "";
        ticketDiscountType = ticketDiscountPercentage = "";
        eventName = eventAddress = eventDate = eventTime = eventGenre = eventType = "";
        errorMessage = errorTitle = "";
        errorCount = showTicketCategoriesCount = showEmptyTicketCategoriesCount = showTicketDiscountsCount = showEmptyTicketDiscountsCount = moveToSelectCategoriesCount = moveToSelectDiscountsCount = navigateToOrganizerHomePageCount = 0;
        attachedTicketCategories = new ArrayList<>();
        attachedTicketDiscounts = new ArrayList<>();
    }

    @Override
    public Integer getAttachedOrganizerId() {
        return attachedOrganizerId;
    }

    public void setAttachedOrganizerId(Integer attachedOrganizerId) {
        this.attachedOrganizerId = attachedOrganizerId;
    }

    @Override
    public Event getAttachedEvent() {
        return attachedEvent;
    }

    public void setAttachedEvent(Event attachedEvent) {
        this.attachedEvent = attachedEvent;
    }

    @Override
    public ArrayList<TicketCategory> getAttachedTicketCategories() {
        return attachedTicketCategories;
    }

    public void setAttachedTicketCategories(ArrayList<TicketCategory> attachedTicketCategories) {
        this.attachedTicketCategories = attachedTicketCategories;
    }

    @Override
    public ArrayList<TicketDiscount> getAttachedTicketDiscounts() {
        return attachedTicketDiscounts;
    }

    public void setAttachedTicketDiscounts(ArrayList<TicketDiscount> attachedTicketDiscounts) {
        this.attachedTicketDiscounts = attachedTicketDiscounts;
    }

    @Override
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    @Override
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public void setEventGenre(String eventGenre) {
        this.eventGenre = eventGenre;
    }

    @Override
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setTicketCategoryName(String ticketCategoryName) {
        this.ticketCategoryName = ticketCategoryName;
    }

    public void setTicketCategoryPrice(String ticketCategoryPrice) {
        this.ticketCategoryPrice = ticketCategoryPrice;
    }

    public void setTicketCategoryDescription(String ticketCategoryDescription) {
        this.ticketCategoryDescription = ticketCategoryDescription;
    }

    public void setTicketCategoryQuantity(String ticketCategoryQuantity) {
        this.ticketCategoryQuantity = ticketCategoryQuantity;
    }

    public void setTicketDiscountType(String ticketDiscountType) {
        this.ticketDiscountType = ticketDiscountType;
    }

    public void setTicketDiscountPercentage(String ticketDiscountPercentage) {
        this.ticketDiscountPercentage = ticketDiscountPercentage;
    }

    @Override
    public void showTicketCategories() {
        showTicketCategoriesCount++;
    }

    @Override
    public void showEmptyTicketCategories() {
        showEmptyTicketCategoriesCount++;
    }

    @Override
    public void showTicketDiscounts() {
        showTicketDiscountsCount++;
    }

    @Override
    public void showEmptyTicketDiscounts() {
        showEmptyTicketDiscountsCount++;
    }

    @Override
    public void moveToSelectCategories() {
        moveToSelectCategoriesCount++;
    }

    @Override
    public void moveToSelectDiscounts() {
        moveToSelectDiscountsCount++;
    }

    @Override
    public void navigateToOrganizerHomePage(Integer organizerId) {
        navigateToOrganizerHomePageCount++;
    }

    @Override
    public void showErrorMessage(String title, String message) {
        errorTitle = title;
        errorMessage = message;
        errorCount++;
    }

    @Override
    public void showEventCreatedMessage() {
        successfulCreationMessage = "Your event has been created successfully!";
    }

    @Override
    public void showEventUpdatedMessage() {
        successfulUpdateMessage = "Your event has been updated successfully!";
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

    public Integer getShowTicketCategoriesCount() {
        return showTicketCategoriesCount;
    }

    public Integer getShowEmptyTicketCategoriesCount() {
        return showEmptyTicketCategoriesCount;
    }

    public Integer getShowTicketDiscountsCount() {
        return showTicketDiscountsCount;
    }

    public Integer getShowEmptyTicketDiscountsCount() {
        return showEmptyTicketDiscountsCount;
    }

    public Integer getMoveToSelectCategoriesCount() {
        return moveToSelectCategoriesCount;
    }

    public Integer getMoveToSelectDiscountsCount() {
        return moveToSelectDiscountsCount;
    }

    public Integer getNavigateToOrganizerHomePageCount() {
        return navigateToOrganizerHomePageCount;
    }

    public String getSuccessfulCreationMessage() {
        return successfulCreationMessage;
    }

    public String getSuccessfulUpdateMessage() {
        return successfulUpdateMessage;
    }
}
