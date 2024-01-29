package com.example.eventhub.view.Event.TicketCategorySelection;

import static com.example.eventhub.util.Util.checkPositiveInteger;

import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.TicketCategoryDAO;
import com.example.eventhub.domain.CategoryName;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.util.Money;
import com.example.eventhub.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;

public class TicketCategorySelectionPresenter {
    private TicketCategorySelectionView view;

    private TicketCategoryDAO ticketCategoryDAO;
    private EventDAO eventDAO;

    private Integer organizerId;
    private Event event;
    private TicketCategory currentTicketCategory;
    private ArrayList<TicketCategory> ticketCategoriesList = new ArrayList<>();
    private boolean editMode = false;

    public TicketCategorySelectionPresenter(TicketCategorySelectionView view, TicketCategoryDAO ticketCategoryDAO, EventDAO eventDAO) {
        this.view = view;
        this.ticketCategoryDAO = ticketCategoryDAO;
        this.eventDAO = eventDAO;

        organizerId = view.getAttachedOrganizerId();
        event = view.getAttachedEvent();

        view.setTextEventCapacity("0");

        if(!event.getTicketCategories().isEmpty()) { // edit mode
            editMode = true;
            view.setPageTitleToEdit("Edit Existing Ticket Categories");
            view.setButtonLabelToEdit("Edit");
            ticketCategoriesList = new ArrayList<>(event.getTicketCategories());

            currentTicketCategory = ticketCategoriesList.get(0);
            view.setTicketCategoryName(Util.convertToTitleCase(ticketCategoriesList.get(0).getName().toString()));
            view.setTicketCategoryPrice(String.valueOf(ticketCategoriesList.get(0).getPrice().getAmount().intValue()));
            view.setTicketCategoryDescription(ticketCategoriesList.get(0).getDescription());
            view.setTicketCategoryQuantity(String.valueOf(ticketCategoriesList.get(0).getQuantity()));
            view.setTextEventCapacity(String.valueOf(event.getEventCapacity()));
        }
    }

    public ArrayList<TicketCategory> getTicketCategoriesList() {
        return ticketCategoriesList;
    }

    public void onShowTicketCategories() {
        Event tempEvent = new Event();
        tempEvent.setTicketCategories(new ArrayList<>(ticketCategoriesList));
        view.setTextEventCapacity(String.valueOf(tempEvent.getEventCapacity()));

        view.showTicketCategories();
    }

    public void onAddTicketCategory() {
        HashMap<String, String> ticketCategoryInfo = view.getTicketCategoryInfo();

        for (HashMap.Entry<String, String> entry : ticketCategoryInfo.entrySet()) {
            if (entry.getValue() == null || entry.getValue().toString().isEmpty()) {
                view.showErrorMessage("Error", "Please fill all the fields.");
                return;
            }
        }

        String ticketCategoryName = ticketCategoryInfo.get("name");
        String ticketCategoryPrice = ticketCategoryInfo.get("price");
        String ticketCategoryDescription = ticketCategoryInfo.get("description");
        String ticketCategoryQuantity = ticketCategoryInfo.get("quantity");

        // preprocessing
        CategoryName ticketCategoryNameCat = Util.mapStringToCategoryName(ticketCategoryName);
        Money ticketCategoryPriceMoney = new Money(new BigDecimal(Integer.parseInt(ticketCategoryPrice)), Currency.getInstance("EUR"));
        Integer ticketCategoryQuantityInteger = Integer.parseInt(ticketCategoryQuantity);

        if(!checkPositiveInteger(ticketCategoryPrice)) {
            view.showErrorMessage("Error", "Ticket category price must be a positive number.");
        } else if(!checkPositiveInteger(ticketCategoryQuantity)) {
            view.showErrorMessage("Error", "Ticket category quantity must be a positive number.");
        } else {
            if (!editMode) { // create mode
                TicketCategory ticketCategory = new TicketCategory();
                ticketCategory.setName(ticketCategoryNameCat);
                ticketCategory.setPrice(ticketCategoryPriceMoney);
                ticketCategory.setDescription(ticketCategoryDescription);
                ticketCategory.setQuantity(ticketCategoryQuantityInteger);

                if(ticketCategoriesList.contains(ticketCategory)) {
                    view.showErrorMessage("Error", "Ticket category already exists.");
                    return;
                }
                ticketCategoriesList.add(ticketCategory);
                view.resetInputFields();

            } else { // find a way to edit
                boolean isQuantityChanged = false;
                Integer boughtTickets = 0;

                if (currentTicketCategory.getQuantity() != ticketCategoryQuantityInteger) {
                    isQuantityChanged = true;
                    boughtTickets = currentTicketCategory.getQuantity() - event.getTicketCategoryCountMap().get(currentTicketCategory);

                    if(ticketCategoryQuantityInteger < boughtTickets) {
                        view.showErrorMessage("Error", "You cannot reduce the quantity of a ticket category that has already been bought.");
                        return;
                    }
                }

                if (isQuantityChanged) { event.getTicketCategoryCountMap().remove(currentTicketCategory); }
                ticketCategoriesList.remove(currentTicketCategory);

                // how to get the selected event from select event listener
                currentTicketCategory.setName(ticketCategoryNameCat);
                currentTicketCategory.setPrice(ticketCategoryPriceMoney);
                currentTicketCategory.setDescription(ticketCategoryDescription);
                currentTicketCategory.setQuantity(ticketCategoryQuantityInteger);

                if (isQuantityChanged) {
                    event.setSingleTicketCategoryMap(currentTicketCategory, ticketCategoryQuantityInteger - boughtTickets);
                };

                if(ticketCategoriesList.contains(currentTicketCategory)) {
                    view.showErrorMessage("Error", "Ticket category already exists.");
                    return;
                }

                ticketCategoriesList.add(currentTicketCategory);
            }
            onShowTicketCategories();
        }
    }

    public void onSelectTicketCategory(TicketCategory ticketCategory) {
        if (editMode) {
            view.setTicketCategoryName(Util.convertToTitleCase(ticketCategory.getName().toString()));
            view.setTicketCategoryPrice(String.valueOf(ticketCategory.getPrice().getAmount().intValue()));
            view.setTicketCategoryDescription(ticketCategory.getDescription());
            view.setTicketCategoryQuantity(String.valueOf(ticketCategory.getQuantity()));
        }

        currentTicketCategory = ticketCategory;
    }

    public void onRemoveTicketCategory(TicketCategory ticketCategory) {
        if (editMode && ticketCategoriesList.size() == 1) {
            view.showErrorMessage("Error", "You cannot remove the last ticket category when editing.");
            return;
        }

        ticketCategoriesList.remove(ticketCategory);
        currentTicketCategory = editMode ? ticketCategoriesList.get(0) : null;
        if(editMode) { event.getTicketCategoryCountMap().remove(ticketCategory); }

        onSelectTicketCategory(currentTicketCategory);
        onShowTicketCategories();
    }

    public void onMoveToTicketDiscountSelection() {
        if (ticketCategoriesList.isEmpty()) {
            view.showErrorMessage("Error", "Please add at least one ticket category.");
            return;
        }
        view.moveToTicketDiscountSelection(ticketCategoriesList);
    }
}
