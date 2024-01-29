package com.example.eventhub.view.Event.TicketDiscountSelection;

import static com.example.eventhub.util.Util.checkPercentage;

import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.TicketDiscountDAO;
import com.example.eventhub.domain.DiscountType;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.TicketDiscount;
import com.example.eventhub.util.Util;

import java.util.ArrayList;
import java.util.HashMap;

public class TicketDiscountSelectionPresenter {
    private TicketDiscountSelectionView view;

    private TicketDiscountDAO ticketDiscountDAO;
    private EventDAO eventDAO;
    private Integer organizerId;
    private Event event;
    private TicketDiscount currentTicketDiscount;
    private ArrayList<TicketDiscount> ticketDiscountList = new ArrayList<>();
    private boolean editMode = false;

    public TicketDiscountSelectionPresenter(TicketDiscountSelectionView view, TicketDiscountDAO ticketDiscountDAO, EventDAO eventDAO) {
        this.view = view;
        this.ticketDiscountDAO = ticketDiscountDAO;
        this.eventDAO = eventDAO;

        organizerId = view.getAttachedOrganizerId();
        event = view.getAttachedEvent();

        if(!event.getTicketDiscounts().isEmpty()) { // edit mode
            editMode = true;
            view.setPageTitleToEdit("Edit Existing Ticket Discounts");
            view.setButtonLabelToEdit("Edit");
            ticketDiscountList = new ArrayList<>(event.getTicketDiscounts());
            currentTicketDiscount = ticketDiscountList.get(0);
            view.setTicketDiscountType(Util.convertToTitleCase(ticketDiscountList.get(0).getType().toString()));
            view.setTicketDiscountPercentage(String.valueOf((Math.round(currentTicketDiscount.getPercentage() * 100))));
        }
    }

    public ArrayList<TicketDiscount> getTicketDiscountList() {
        return ticketDiscountList;
    }

    public void onShowTicketDiscounts() {
        view.showTicketDiscounts();
    }

    public void onAddTicketDiscount() {
        HashMap<String, String> ticketDiscountInfo = view.getTicketDiscountInfo();

        for (HashMap.Entry<String, String> entry : ticketDiscountInfo.entrySet()) {
            if (entry.getValue() == null || entry.getValue().toString().isEmpty()) {
                view.showErrorMessage("Error", "Please fill all the fields.");
                return;
            }
        }

        String ticketDiscountType = ticketDiscountInfo.get("type");
        String ticketDiscountPercentage = ticketDiscountInfo.get("percentage");

        // preprocessing
        DiscountType ticketDiscountTypeEnum = Util.mapStringToDiscountType(ticketDiscountType);
        double ticketDiscountPercentageDouble = Double.parseDouble(ticketDiscountPercentage);

        if (!checkPercentage(ticketDiscountPercentage)) {
            view.showErrorMessage("Error", "Please enter a valid ticket discount percentage.");
        } else {
            if (!editMode) { // create mode
                TicketDiscount ticketDiscount = new TicketDiscount();
                ticketDiscount.setType(ticketDiscountTypeEnum);
                ticketDiscount.setPercentage(ticketDiscountPercentageDouble);

                if(ticketDiscountList.contains(ticketDiscount)) {
                    view.showErrorMessage("Error", "Ticket discount already exists.");
                    return;
                }
                ticketDiscountList.add(ticketDiscount);
                view.resetInputFields();

            } else { // find a way to edit
                ticketDiscountList.remove(currentTicketDiscount);

                currentTicketDiscount.setType(ticketDiscountTypeEnum);
                currentTicketDiscount.setPercentage(ticketDiscountPercentageDouble);

                if (ticketDiscountList.contains(currentTicketDiscount)) {
                    view.showErrorMessage("Error", "Ticket discount already exists.");
                    return;
                }

                ticketDiscountList.add(currentTicketDiscount);
            }
            onShowTicketDiscounts();
        }
    }

    public void onSelectTicketDiscount(TicketDiscount ticketDiscount) {
        if(editMode) {
            view.setTicketDiscountType(Util.convertToTitleCase(ticketDiscount.getType().toString()));
            view.setTicketDiscountPercentage(String.valueOf((Math.round(ticketDiscount.getPercentage() * 100))));
        }

        currentTicketDiscount = ticketDiscount;
    }

    public void onRemoveTicketDiscount(TicketDiscount ticketDiscount) {
        if(editMode && ticketDiscountList.size() == 1) {
            view.showErrorMessage("Error", "You cannot remove the last ticket discount when editing.");
            return;
        }

        ticketDiscountList.remove(ticketDiscount);
        currentTicketDiscount = editMode ? ticketDiscountList.get(0) : null;

        onSelectTicketDiscount(currentTicketDiscount);
        view.showTicketDiscounts();
    }

    public void onMoveToCreateEventOverview() {
        if (ticketDiscountList.isEmpty()) {
            view.showErrorMessage("Error", "Please add at least one ticket discount.");
            return;
        }
        view.moveToCreateEventOverview(ticketDiscountList);
    }
}
