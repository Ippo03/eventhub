package com.example.eventhub.view.Event.EventDetails;

import androidx.lifecycle.ViewModel;

import com.example.eventhub.memorydao.CustomerDAOMemory;
import com.example.eventhub.memorydao.EventDAOMemory;
import com.example.eventhub.memorydao.OrganizerDAOMemory;
import com.example.eventhub.memorydao.PurchaseDAOMemory;
import com.example.eventhub.memorydao.TicketDAOMemory;

public class EventDetailsViewModel extends ViewModel {
    private EventDetailsPresenter presenter;

    public EventDetailsViewModel() {
        presenter = new EventDetailsPresenter(new CustomerDAOMemory(), new OrganizerDAOMemory(), new EventDAOMemory(), new PurchaseDAOMemory(), new TicketDAOMemory());
    }

    public EventDetailsPresenter getPresenter() {
        return presenter;
    }
}
