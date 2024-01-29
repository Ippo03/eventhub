package com.example.eventhub.view.Organizer.OrganizerHomePage;

import com.example.eventhub.view.OrganizerHomePage.OrganizerHomePageView;

public class OrganizerHomePageViewStub implements OrganizerHomePageView {
    private Integer organizedEventsCount, emptyEventsCount, createdEventCount, editAccountCount, logoutCount;

    public OrganizerHomePageViewStub() {
        organizedEventsCount = emptyEventsCount = createdEventCount = editAccountCount = logoutCount = 0;
    }

    @Override
    public void showOrganizedEvents() {
        organizedEventsCount++;
    }

    @Override
    public void showEmptyEvents() {
        emptyEventsCount++;
    }

    @Override
    public void createEvent() {
        createdEventCount++;
    }

    @Override
    public void editAccount() {
        editAccountCount++;
    }

    @Override
    public void logout() {
        logoutCount++;
    }

    public Integer getOrganizedEventsCount() {
        return organizedEventsCount;
    }

    public Integer getEmptyEventsCount() {
        return emptyEventsCount;
    }

    public Integer getCreatedEventCount() {
        return createdEventCount;
    }

    public Integer getEditAccountCount() {
        return editAccountCount;
    }

    public Integer getLogoutCount() {
        return logoutCount;
    }
}
