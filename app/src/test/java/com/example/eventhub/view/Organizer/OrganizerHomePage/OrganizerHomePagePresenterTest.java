package com.example.eventhub.view.Organizer.OrganizerHomePage;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.OrganizerDAO;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.Organizer;
import com.example.eventhub.helper.AddressTestHelper;
import com.example.eventhub.memorydao.EventDAOMemory;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.memorydao.OrganizerDAOMemory;
import com.example.eventhub.view.OrganizerHomePage.OrganizerHomePagePresenter;
import com.example.eventhub.view.OrganizerHomePage.OrganizerHomePageView;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

/**
 * Unit tests for the OrganizerHomePagePresenter class, focusing on organizer home page functionality.
 */
public class OrganizerHomePagePresenterTest {

    private OrganizerHomePagePresenter presenter;
    private OrganizerHomePageViewStub view;
    private OrganizerDAO organizerDAO;
    private EventDAO eventDAO;
    private Address address;

    @Before
    public void setUp() {
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        view = new OrganizerHomePageViewStub();
        organizerDAO = new OrganizerDAOMemory();
        eventDAO = new EventDAOMemory();

        presenter = new OrganizerHomePagePresenter(organizerDAO, eventDAO);
        presenter.setView(view);

        address = AddressTestHelper.initAddress1();
    }

    /**
     * Test case for setting the view of the presenter.
     * Validates that the presenter's view is correctly set.
     */
    @Test
    public void onSetView() {
        OrganizerHomePageView newView = new OrganizerHomePageViewStub();
        presenter.setView(newView);
        Assertions.assertEquals(newView, presenter.getView());
    }

    /**
     * Test case for getting the OrganizerDAO from the presenter.
     * Validates that the correct OrganizerDAO instance is returned.
     */
    @Test
    public void onGetOrganizerDAO() {
        Assertions.assertEquals(presenter.getOrganizerDAO(), organizerDAO);
    }

    /**
     * Test case for getting the EventDAO from the presenter.
     * Validates that the correct EventDAO instance is returned.
     */
    @Test
    public void onGetEventDAO() {
        Assertions.assertEquals(presenter.getEventDAO(), eventDAO);
    }

    /**
     * Test case for setting the organizer in the presenter.
     * Validates that the correct organizer is set in the presenter.
     */
    @Test
    public void onSetOrganizer() {
        Organizer organizer = organizerDAO.find(1);
        presenter.setOrganizer(organizer.getId());
        Assertions.assertEquals(organizer, presenter.getOrganizer());
    }

    /**
     * Test case for setting the list of organized events in the presenter.
     * Validates that the list of organized events is correctly set in the presenter.
     */
    @Test
    public void onSetOrganizedEventList() {
        Organizer organizer = organizerDAO.find(1);
        ArrayList<Event> events = organizer.getEvents();
        events.sort((event1, event2) -> event2.getDate().compareTo(event1.getDate()));
        presenter.setOrganizer(organizer.getId());
        presenter.setOrganizedEventList();
        Assertions.assertEquals(events, presenter.getOrganizedEventList());
    }

    /**
     * Test case for displaying an empty list of events.
     * Validates that the appropriate method is called when there are no organized events.
     */
    @Test
    public void onDisplayEmptyEvents() {
        Organizer organizer = new Organizer(4, "Nikos", "Mitsakis", "nick@example.com", 30, "male", address, "12456789", 123456789);
        organizerDAO.save(organizer);
        presenter.setOrganizer(organizer.getId());
        presenter.setOrganizedEventList();
        presenter.onDisplayEvents();

        Assertions.assertEquals(1, view.getEmptyEventsCount());
        Assertions.assertEquals(0, view.getOrganizedEventsCount());
    }

    /**
     * Test case for displaying the list of organized events.
     * Validates that the appropriate method is called when there are organized events to display.
     */
    @Test
    public void onDisplayEvents() {
        Organizer organizer = organizerDAO.find(1);
        presenter.setOrganizer(organizer.getId());
        presenter.setOrganizedEventList();
        presenter.onDisplayEvents();

        Assertions.assertEquals(0, view.getEmptyEventsCount());
        Assertions.assertEquals(1, view.getOrganizedEventsCount());
    }

    /**
     * Test case for creating a new event.
     * Validates that the appropriate method is called when creating a new event.
     */
    @Test
    public void onCreateEvent() {
        presenter.onCreateEvent();
        Assertions.assertEquals(1, view.getCreatedEventCount());
    }

    /**
     * Test case for editing the organizer's account.
     * Validates that the appropriate method is called when editing the account.
     */
    @Test
    public void onEditAccount() {
        presenter.onEditAccount();
        Assertions.assertEquals(1, view.getEditAccountCount());
    }

    /**
     * Test case for logging out.
     * Validates that the appropriate method is called when logging out.
     */
    @Test
    public void onLogout() {
        presenter.onLogout();
        Assertions.assertEquals(1, view.getLogoutCount());
    }
}
