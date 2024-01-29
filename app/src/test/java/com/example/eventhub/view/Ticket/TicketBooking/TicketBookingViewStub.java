package com.example.eventhub.view.Ticket.TicketBooking;

import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;
import com.example.eventhub.view.TicketBooking.TicketBookingView;

public class TicketBookingViewStub implements TicketBookingView {
    private String eventName, ticketPrice, purchaseTotalCost;
    private String errorTitle, errorMessage, successfulPurchaseMessage;
    private Integer attachedEventId;
    private Integer onChangeCategoryCount, onChangeDiscountCount, showTicketsCount, addMoreTicketsCount, proceedToCheckoutCount, moveToLoginCount, errorCount;
    private TicketCategory selectedCategory;
    private TicketDiscount selectedDiscount;

    public TicketBookingViewStub() {
        eventName = ticketPrice = errorTitle = errorMessage = successfulPurchaseMessage = "";
        onChangeCategoryCount = onChangeDiscountCount = showTicketsCount = addMoreTicketsCount = proceedToCheckoutCount = moveToLoginCount = errorCount = 0;
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
    public void setTicketPrice(String price) {
        this.ticketPrice = price;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public TicketCategory getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(TicketCategory selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    @Override
    public TicketDiscount getSelectedDiscount() {
        return selectedDiscount;
    }

    public void setSelectedDiscount(TicketDiscount selectedDiscount) {
        this.selectedDiscount = selectedDiscount;
    }

    @Override
    public void onChangeCategory() {
        onChangeCategoryCount++;
    }

    @Override
    public void onChangeDiscount() {
        onChangeDiscountCount++;
    }

    @Override
    public void showTickets() {
        showTicketsCount++;
    }

    @Override
    public void setTotalCost(String totalCost) {
        this.purchaseTotalCost = totalCost;
    }

    public String getPurchaseTotalCost() {
        return purchaseTotalCost;
    }

//    @Override
//    public void addMoreTickets() {
//        addMoreTicketsCount++;
//    }
//
//    @Override
//    public void proceedToCheckout() {
//        proceedToCheckoutCount++;
//    }
//
//    @Override
//    public void moveToLogin() {
//        moveToLoginCount++;
//    }
//
//    @Override
//    public void showSuccessfulTicketSelectionMessage() {
//        successMessage = "You have successfully selected your tickets. You will be redirected to the purchase overview page.";
//    }

    @Override
    public void showErrorMessage(String title, String message) {
        errorTitle = title;
        errorMessage = message;
        errorCount++;
    }

    @Override
    public void showPurchaseCompleteMessage() {
        successfulPurchaseMessage = "Your purchase has been completed !\n You will be redirected to your home page.";
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSuccessfulPurchaseMessage() {
        return successfulPurchaseMessage;
    }

    public Integer getOnChangeCategoryCount() {
        return onChangeCategoryCount;
    }

    public Integer getOnChangeDiscountCount() {
        return onChangeDiscountCount;
    }

    public Integer getAddMoreTicketsCount() {
        return addMoreTicketsCount;
    }

    public Integer getProceedToCheckoutCount() {
        return proceedToCheckoutCount;
    }

    public Integer getMoveToLoginCount() {
        return moveToLoginCount;
    }

    public Integer getShowTicketsCount() {
        return showTicketsCount;
    }

    public Integer getErrorCount() {
        return errorCount;
    }
}
