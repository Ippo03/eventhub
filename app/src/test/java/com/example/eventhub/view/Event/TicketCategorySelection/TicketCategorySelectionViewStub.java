package com.example.eventhub.view.Event.TicketCategorySelection;

import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.view.Event.TicketCategorySelection.TicketCategorySelectionView;

import java.util.ArrayList;
import java.util.HashMap;

public class TicketCategorySelectionViewStub implements TicketCategorySelectionView {
    private String pageTitle, buttonLabel, eventCapacity;
    private String ticketCategoryName, ticketCategoryPrice, ticketCategoryDescription, ticketCategoryQuantity;
    private String eventName, eventAddress, eventDate, eventTime, eventGenre, eventType;
    private String errorTitle, errorMessage;
    private Integer errorCount, onMoveToTicketDiscountSelectionCount, showTicketCategoriesCount, resetInputFieldsCount;
    private Integer attachedOrganizerId;
    private Event attachedEvent;

    public TicketCategorySelectionViewStub() {
        ticketCategoryName = ticketCategoryPrice = ticketCategoryDescription = ticketCategoryQuantity = "";
        eventName = eventAddress = eventDate = eventTime = eventGenre = eventType = "";
        errorMessage = errorTitle = "";
        errorCount = onMoveToTicketDiscountSelectionCount = showTicketCategoriesCount = resetInputFieldsCount = 0;
        eventCapacity = "0";
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
    public void setTextEventCapacity(String capacity) {
        this.eventCapacity = capacity;
    }

    public String getEventCapacity() {
        return eventCapacity;
    }

    @Override
    public HashMap<String, String> getTicketCategoryInfo() {
        HashMap<String, String> ticketCategoryInfo = new HashMap<>();
        ticketCategoryInfo.put("name", ticketCategoryName);
        ticketCategoryInfo.put("price", ticketCategoryPrice);
        ticketCategoryInfo.put("description", ticketCategoryDescription);
        ticketCategoryInfo.put("quantity", ticketCategoryQuantity);

        return ticketCategoryInfo;
    }

    @Override
    public String getTicketCategoryName() {
        return ticketCategoryName;
    }

    @Override
    public String getTicketCategoryPrice() {
        return ticketCategoryPrice;
    }

    @Override
    public String getTicketCategoryDescription() {
        return ticketCategoryDescription;
    }

    @Override
    public String getTicketCategoryQuantity() {
        return ticketCategoryQuantity;
    }

    @Override
    public void setTicketCategoryName(String ticketCategoryName) {
        this.ticketCategoryName = ticketCategoryName;
    }

    @Override
    public void setTicketCategoryPrice(String ticketCategoryPrice) {
        this.ticketCategoryPrice = ticketCategoryPrice;
    }

    @Override
    public void setTicketCategoryDescription(String ticketCategoryDescription) {
        this.ticketCategoryDescription = ticketCategoryDescription;
    }

    @Override
    public void setTicketCategoryQuantity(String ticketCategoryQuantity) {
        this.ticketCategoryQuantity = ticketCategoryQuantity;
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
    public void showTicketCategories() {
        showTicketCategoriesCount++;
    }

    @Override
    public void resetInputFields() {
        resetInputFieldsCount++;
    }

    @Override
    public void moveToTicketDiscountSelection(ArrayList<TicketCategory> ticketCategoriesList) {
        onMoveToTicketDiscountSelectionCount++;
    }

    @Override
    public void showErrorMessage(String title, String message) {
        errorTitle = title;
        errorMessage = message;
        errorCount++;
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

    public Integer getOnMoveToTicketDiscountSelectionCount() {
        return onMoveToTicketDiscountSelectionCount;
    }

    public Integer getShowTicketCategoriesCount() {
        return showTicketCategoriesCount;
    }

    public Integer getResetInputFieldsCount() {
        return resetInputFieldsCount;
    }
}
