package com.example.eventhub.view.Customer.CustomerPurchases;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventhub.R;
import com.example.eventhub.domain.Purchase;
import com.example.eventhub.view.Customer.PurchaseDetails.PurchaseDetailsActivity;
import com.example.eventhub.view.ReviewEvent.ReviewEventActivity;

/**
 * Activity class responsible for displaying a list of purchases made by a customer.
 * Implements the {@link CustomerPurchasesView} interface to interact with the presenter.
 */
public class CustomerPurchasesActivity extends AppCompatActivity implements CustomerPurchasesView,
        CustomerPurchasesRecyclerViewAdapter.ItemSelectionListener {
    private Integer customerId;
    RecyclerView recyclerView;
    CustomerPurchasesViewModel viewModel;

    /**
     * Called when the activity is first created. Responsible for initializing the UI and presenter.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this Bundle contains the data it most recently supplied in {@link #onSaveInstanceState}.
     *                           Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_purchases);

        viewModel = new ViewModelProvider(this).get(CustomerPurchasesViewModel.class);
        viewModel.getPresenter().setView(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            customerId = extras.getInt("customer_id");
        }

        viewModel.getPresenter().setCustomer(customerId);
        viewModel.getPresenter().setPurchaseList();

        recyclerView = findViewById(R.id.recyclerPurchases);

        viewModel.getPresenter().onShowPurchasesMade();

        viewModel.getPresenter().setCountOfPurchases();
    }

    /**
     * Shows the list of purchases made by the customer in the recycler view.
     */
    @Override
    public void showPurchasesMade() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CustomerPurchasesRecyclerViewAdapter(viewModel.getPresenter().getPurchaseList(), this));
    }

    /**
     * Sets the count of purchases made by the customer in the UI.
     *
     * @param count The count of purchases.
     */
    @Override
    public void setCountOfPurchases(Integer count) {
        String countString = count > 1 ? " purchases" : " purchase";
        ((TextView) findViewById(R.id.textNumberOfPurchases)).setText(count.toString() + countString);
    }

    /**
     * Opens the review event activity when the user selects to review an event associated with a purchase.
     *
     * @param eventId The ID of the event to be reviewed.
     */
    @Override
    public void reviewEvent(Integer eventId) {
        Intent intent = new Intent(this, ReviewEventActivity.class);
        intent.putExtra("event_id", eventId);
        intent.putExtra("customer_id", customerId);
        startActivity(intent);
    }

    /**
     * Opens the purchase details activity when the user selects a specific purchase.
     *
     * @param purchase The selected purchase.
     */
    @Override
    public void selectPurchase(Purchase purchase) {
        Intent intent = new Intent(this, PurchaseDetailsActivity.class);
        intent.putExtra("purchase_id", purchase.getPurchaseId());
        startActivity(intent);
    }
}