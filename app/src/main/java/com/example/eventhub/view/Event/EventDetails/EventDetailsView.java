package com.example.eventhub.view.Event.EventDetails;

public interface EventDetailsView {
    int getAttachedEventId();

    void setEventName(String name);

    void setEventAddress(String address);

    void setEventGenre(String genre);

    void setEventType(String eventType);

    void setEventTime(String dateTime);

    void setAverageRating(String averageRating);

    void setEventCapacity(String eventCapacity);

    void setAvailableTickets(String availableTickets);

    void onLoginOrRegister();

    void bookTicket(int eventId);

    void showReviews();

    void showEmptyReviews();

    void showBookTicketButton();

    void hideBookTicketButton();

    void bookFreeTicket();

    void showSuccessfulFreePurchase();
}
