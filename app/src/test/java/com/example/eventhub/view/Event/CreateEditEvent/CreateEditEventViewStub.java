package com.example.eventhub.view.Event.CreateEditEvent;

import com.example.eventhub.domain.Event;
import com.example.eventhub.view.Event.CreateEditEvent.CreateEditEventView;

import java.util.HashMap;

public class CreateEditEventViewStub implements CreateEditEventView {
    private String eventName, eventAddress, eventType, eventDate, eventTime, eventGenre;
    private String errorTitle, errorMessage;
    private Integer errorCount, onMoveToTicketCategorySelectionCount, onMoveToCreateEventOverviewCount, onSelectFreeTicketCategoryQuantityCount;
    private Integer attachedOrganizerId, attachedEventId;

    public CreateEditEventViewStub() {
        eventName = eventAddress = eventType = eventDate = eventTime = eventGenre = "";
        errorTitle = errorMessage = "";
        errorCount = onMoveToTicketCategorySelectionCount = onMoveToCreateEventOverviewCount = onSelectFreeTicketCategoryQuantityCount = 0;
    }

    @Override
    public Integer getAttachedOrganizerId() {
        return attachedOrganizerId;
    }

    @Override
    public Integer getAttachedEventId() {
        return attachedEventId;
    }

    public void setAttachedEventId(Integer attachedEventId) {
        this.attachedEventId = attachedEventId;
    }

    @Override
    public HashMap<String, String> getEventInfo() {
        HashMap<String, String> eventInfo = new HashMap<>();
        eventInfo.put("name", eventName);
        eventInfo.put("address", eventAddress);
        eventInfo.put("type", eventType);
        eventInfo.put("date", eventDate);
        eventInfo.put("time", eventTime);
        eventInfo.put("genre", eventGenre);

        return eventInfo;
    }

    @Override
    public String getEventName() {
        return eventName;
    }

    @Override
    public String getEventAddress() {
        return eventAddress;
    }

    @Override
    public String getEventType() {
        return eventType;
    }

    @Override
    public String getEventDate() {
        return eventDate;
    }

    @Override
    public String getEventTime() {
        return eventTime;
    }

    @Override
    public String getEventGenre() {
        return eventGenre;
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
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public void setEventGenre(Integer eventGenre) {
        this.eventGenre = String.valueOf(eventGenre);
    }

    @Override
    public void showErrorMessage(String title, String message) {
        errorTitle = title;
        errorMessage = message;
        errorCount++;
    }

    @Override
    public void moveToTicketCategorySelection(Event event) {
        onMoveToTicketCategorySelectionCount++;
    }

    @Override
    public void moveToCreateEventOverview(Event event) {
        onMoveToCreateEventOverviewCount++;
    }

    @Override
    public void selectFreeTicketCategoryQuantity(Event event) {
        onSelectFreeTicketCategoryQuantityCount++;
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

    public Integer getOnMoveToTicketCategorySelectionCount() {
        return onMoveToTicketCategorySelectionCount;
    }

    public Integer getOnMoveToCreateEventOverviewCount() {
        return onMoveToCreateEventOverviewCount;
    }

    public Integer getOnSelectFreeTicketCategoryQuantityCount() {
        return onSelectFreeTicketCategoryQuantityCount;
    }
}
