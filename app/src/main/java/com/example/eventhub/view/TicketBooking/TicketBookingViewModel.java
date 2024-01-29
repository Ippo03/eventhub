package com.example.eventhub.view.TicketBooking;

import androidx.lifecycle.ViewModel;

import com.example.eventhub.memorydao.CustomerDAOMemory;
import com.example.eventhub.memorydao.EventDAOMemory;
import com.example.eventhub.memorydao.PurchaseDAOMemory;
import com.example.eventhub.memorydao.TicketCategoryDAOMemory;
import com.example.eventhub.memorydao.TicketDAOMemory;
import com.example.eventhub.memorydao.TicketDiscountDAOMemory;

public class TicketBookingViewModel extends ViewModel {
    private TicketBookingPresenter presenter;

    public TicketBookingViewModel() {
        this.presenter = new TicketBookingPresenter(new CustomerDAOMemory(), new EventDAOMemory(), new TicketCategoryDAOMemory(), new TicketDiscountDAOMemory(), new TicketDAOMemory(), new PurchaseDAOMemory());
    }

    public TicketBookingPresenter getPresenter() {
        return presenter;
    }
}
