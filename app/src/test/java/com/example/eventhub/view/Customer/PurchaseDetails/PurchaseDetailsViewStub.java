package com.example.eventhub.view.Customer.PurchaseDetails;

import com.example.eventhub.domain.Purchase;
import com.example.eventhub.view.Customer.PurchaseDetails.PurchaseDetailsView;

public class PurchaseDetailsViewStub implements PurchaseDetailsView {
    private String purchaseName, purchaseLocation, purchaseDate, purchaseTotalCost;
    private Integer attachedPurchaseId, showPurchasedTicketsCount;

    public PurchaseDetailsViewStub() {
        purchaseName = purchaseLocation = purchaseDate = purchaseTotalCost = "";
        showPurchasedTicketsCount = 0;
    }

    @Override
    public void setPurchaseName(String name) {
        this.purchaseName = name;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    @Override
    public void setPurchaseLocation(String location) {
        this.purchaseLocation = location;
    }

    public String getPurchaseLocation() {
        return purchaseLocation;
    }

    @Override
    public void setPurchaseDate(String date) {
        this.purchaseDate = date;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    @Override
    public void setPurchaseTotalCost(String totalCost) {
        this.purchaseTotalCost = totalCost;
    }

    public String getPurchaseTotalCost() {
        return purchaseTotalCost;
    }

    @Override
    public void showPurchasedTickets(Purchase purchase) {
        showPurchasedTicketsCount++;
    }

    public void setAttachedPurchaseId(Integer attachedPurchaseId) {
        this.attachedPurchaseId = attachedPurchaseId;
    }

    public Integer getShowPurchasedTicketsCount() {
        return showPurchasedTicketsCount;
    }
}
