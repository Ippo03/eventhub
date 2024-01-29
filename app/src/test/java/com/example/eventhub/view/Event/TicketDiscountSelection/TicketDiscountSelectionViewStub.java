package com.example.eventhub.view.Event.TicketDiscountSelection;

import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;
import com.example.eventhub.view.Event.TicketDiscountSelection.TicketDiscountSelectionView;

import java.util.ArrayList;
import java.util.HashMap;

public class TicketDiscountSelectionViewStub implements TicketDiscountSelectionView {
    private String pageTitle, buttonLabel, eventCapacity;
    private String ticketDiscountType, ticketDiscountPercentage;
    private String eventName, eventAddress, eventDate, eventTime, eventGenre, eventType;
    private String errorTitle, errorMessage;
    private Integer errorCount, moveToCreateOverviewCount, showTicketDiscountsCount, resetInputFieldsCount;
    private Integer attachedOrganizerId;
    private Event attachedEvent;
    private ArrayList<TicketCategory> attachedTicketCategories;

    public TicketDiscountSelectionViewStub() {
        ticketDiscountType = ticketDiscountPercentage = "";
        eventName = eventAddress = eventDate = eventTime = eventGenre = eventType = "";
        errorMessage = errorTitle = "";
        errorCount = moveToCreateOverviewCount = showTicketDiscountsCount = resetInputFieldsCount = 0;
    }

    @Override
    public Integer getAttachedOrganizerId() {
        return attachedOrganizerId;
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

    @Override
    public void setPageTitleToEdit(String title) {
        this.pageTitle = title;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    @Override
    public void setButtonLabelToEdit(String label) {
        this.buttonLabel = label;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    @Override
    public HashMap<String, String> getTicketDiscountInfo() {
        HashMap<String, String> ticketDiscountInfo = new HashMap<>();
        ticketDiscountInfo.put("type", ticketDiscountType);
        ticketDiscountInfo.put("percentage", ticketDiscountPercentage);

        return ticketDiscountInfo;
    }

    @Override
    public String getTicketDiscountType() {
        return ticketDiscountType;
    }

    @Override
    public String getTicketDiscountPercentage() {
        return ticketDiscountPercentage;
    }

    @Override
    public void setTicketDiscountType(String ticketDiscountType) {
        this.ticketDiscountType = ticketDiscountType;
    }

    @Override
    public void setTicketDiscountPercentage(String ticketDiscountPercentage) {
        this.ticketDiscountPercentage = ticketDiscountPercentage;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public void setEventGenre(Integer eventGenre) {
        this.eventGenre = String.valueOf(eventGenre);
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public void showTicketDiscounts() {
        showTicketDiscountsCount++;
    }

    @Override
    public void showErrorMessage(String title, String message) {
        errorTitle = title;
        errorMessage = message;
        errorCount++;
    }

    @Override
    public void resetInputFields() {
        resetInputFieldsCount++;
    }

    @Override
    public void moveToCreateEventOverview(ArrayList<TicketDiscount> ticketDiscountList) {
        moveToCreateOverviewCount++;
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

    public Integer getMoveToCreateOverviewCount() {
        return moveToCreateOverviewCount;
    }

    public Integer getShowTicketDiscountsCount() {
        return showTicketDiscountsCount;
    }

    public Integer getResetInputFieldsCount() {
        return resetInputFieldsCount;
    }
}
