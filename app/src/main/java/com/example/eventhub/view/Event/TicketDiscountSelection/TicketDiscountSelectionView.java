package com.example.eventhub.view.Event.TicketDiscountSelection;

import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;

import java.util.ArrayList;
import java.util.HashMap;

public interface TicketDiscountSelectionView {
    Integer getAttachedOrganizerId();

    Event getAttachedEvent();

    ArrayList<TicketCategory> getAttachedTicketCategories();

    void setPageTitleToEdit(String title);

    void setButtonLabelToEdit(String label);

    HashMap<String, String> getTicketDiscountInfo();

    String getTicketDiscountType();

    String getTicketDiscountPercentage();

    void setTicketDiscountType(String ticketDiscountType);

    void setTicketDiscountPercentage(String ticketDiscountPercentage);

    void showTicketDiscounts();

    void showErrorMessage(String title, String message);

    void resetInputFields();

    void moveToCreateEventOverview(ArrayList<TicketDiscount> ticketDiscountList);
}
