package com.example.eventhub.view.Event.EventDetails;

import com.example.eventhub.view.Event.EventDetails.EventDetailsView;

public class EventDetailsViewStub implements EventDetailsView {
    private String eventName, eventAddress, eventGenre, eventType, eventTime;
    private String averageRating, eventCapacity, availableTickets;
    private Integer attachedEventId, showReviewCount, showEmptyReviewCount, bookTicketCount, bookFreeTicketCount, onShowSuccessfulFreePurchaseCount, onLoginOrRegisterCount, onShowBookTicketButtonCount, onHideBookTicketButtonCount;

    public EventDetailsViewStub() {
        eventName = eventAddress = eventGenre = eventType = eventTime = "";
        showReviewCount = showEmptyReviewCount = bookTicketCount = bookFreeTicketCount = onShowSuccessfulFreePurchaseCount = onLoginOrRegisterCount =  onShowBookTicketButtonCount = onHideBookTicketButtonCount = 0;
    }

    @Override
    public int getAttachedEventId() {
        return attachedEventId;
    }

    @Override
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    @Override
    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    @Override
    public void setEventGenre(String eventGenre) {
        this.eventGenre = eventGenre;
    }

    public String getEventGenre() {
        return eventGenre;
    }

    @Override
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    @Override
    public void setEventTime(String eventDateTime) {
        this.eventTime = eventDateTime;
    }

    public String getEventTime() {
        return eventTime;
    }

    @Override
    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public String getAverageRating() {
        return averageRating;
    }

    @Override
    public void setEventCapacity(String eventCapacity) {
        this.eventCapacity = eventCapacity;
    }

    public String getEventCapacity() {
        return eventCapacity;
    }

    @Override
    public void setAvailableTickets(String availableTickets) {
        this.availableTickets = availableTickets;
    }

    public String getAvailableTickets() {
        return availableTickets;
    }

    @Override
    public void onLoginOrRegister() {
        onLoginOrRegisterCount++;
    }

    @Override
    public void bookTicket(int eventId) {
        bookTicketCount++;
    }

    @Override
    public void bookFreeTicket() {
        bookFreeTicketCount++;
    }

    @Override
    public void showSuccessfulFreePurchase() {
        onShowSuccessfulFreePurchaseCount++;
    }

    @Override
    public void showReviews() {
        showReviewCount++;
    }

    @Override
    public void showEmptyReviews() {
        showEmptyReviewCount++;
    }

    @Override
    public void showBookTicketButton() {
        onShowBookTicketButtonCount++;
    }

    @Override
    public void hideBookTicketButton() {
        onHideBookTicketButtonCount++;
    }

    public Integer getOnLoginOrRegisterCount() {
        return onLoginOrRegisterCount;
    }

    public Integer getBookTicketCount() {
        return bookTicketCount;
    }

    public Integer getBookFreeTicketCount() {
        return bookFreeTicketCount;
    }

    public Integer getOnShowSuccessfulFreePurchaseCount() {
        return onShowSuccessfulFreePurchaseCount;
    }

    public Integer getOnShowBookTicketButtonCount() {
        return onShowBookTicketButtonCount;
    }

    public Integer getOnHideBookTicketButtonCount() {
        return onHideBookTicketButtonCount;
    }

    public Integer getShowReviewCount() {
        return showReviewCount;
    }

    public Integer getShowEmptyReviewsCount() {
        return showEmptyReviewCount;
    }
}
