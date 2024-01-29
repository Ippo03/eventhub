package com.example.eventhub.view.Event.CreateEditEvent;

import com.example.eventhub.domain.Event;

import java.util.HashMap;

public interface CreateEditEventView {
    Integer getAttachedOrganizerId();

    Integer getAttachedEventId();

    HashMap<String, String> getEventInfo();

    String getEventName();

    String getEventAddress();

    String getEventType();

    String getEventDate();

    String getEventTime();

    String getEventGenre();

    void setEventName(String name);

    void setEventAddress(String address);

    void setEventType(String type);

    void setEventDate(String date);

    void setEventTime(String time);

    void setEventGenre(Integer genre);

    void showErrorMessage(String title, String message);

    void moveToTicketCategorySelection(Event event);

    void moveToCreateEventOverview(Event event);

    void selectFreeTicketCategoryQuantity(Event event);

}
