package com.example.eventhub.view.Customer.PurchaseDetails;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventhub.R;
import com.example.eventhub.domain.Purchase;
import com.example.eventhub.domain.Ticket;
import com.example.eventhub.view.TicketBooking.TicketBookingRecyclerViewAdapter;

/**
 * Activity class responsible for displaying details of a specific purchase made by a customer.
 * Implements the {@link PurchaseDetailsView} interface to interact with the presenter.
 */
public class PurchaseDetailsActivity extends AppCompatActivity implements PurchaseDetailsView,
        TicketBookingRecyclerViewAdapter.OnItemClickListener {
    int purchaseId;
    RecyclerView recyclerView;
    PurchaseDetailsViewModel viewModel;

    /**
     * Called when the activity is first created. Responsible for initializing the UI and presenter.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this Bundle contains the data it most recently supplied in {@link #onSaveInstanceState}.
     *                           Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_details);

        viewModel = new ViewModelProvider(this).get(PurchaseDetailsViewModel.class);
        viewModel.getPresenter().setView(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            purchaseId = extras.getInt("purchase_id");
        }

        viewModel.getPresenter().setAttachedPurchase(purchaseId);
        viewModel.getPresenter().onSetPurchaseDetails();
        viewModel.getPresenter().onSetUpTickets();

        recyclerView = findViewById(R.id.recyclerViewPurchasedTicketList);
        viewModel.getPresenter().onShowPurchasedTickets();
    }

    /**
     * Sets the name of the purchased event in the UI.
     *
     * @param name The name of the purchased event.
     */
    @Override
    public void setPurchaseName(String name) {
        ((TextView) findViewById(R.id.textViewPurchasedEventName)).setText(name);
    }

    /**
     * Sets the location of the purchased event in the UI.
     *
     * @param location The location of the purchased event.
     */
    @Override
    public void setPurchaseLocation(String location) {
        ((TextView) findViewById(R.id.textViewPurchasedEventLocation)).setText(location);
    }

    /**
     * Sets the date of the purchased event in the UI.
     *
     * @param date The date of the purchased event.
     */
    @Override
    public void setPurchaseDate(String date) {
        ((TextView) findViewById(R.id.textViewPurchasedEventDate)).setText(date);
    }

    /**
     * Sets the total cost of the purchased event in the UI.
     *
     * @param totalCost The total cost of the purchased event.
     */
    @Override
    public void setPurchaseTotalCost(String totalCost) {
        ((TextView) findViewById(R.id.textViewPurchasedEventTotalCost)).setText(totalCost);
    }

    /**
     * Displays the purchased tickets in a recycler view.
     *
     * @param purchase The purchase containing the tickets to be displayed.
     */
    @Override
    public void showPurchasedTickets(Purchase purchase) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TicketBookingRecyclerViewAdapter(viewModel.getPresenter().getAggregatedTicketList(), viewModel.getPresenter().getTicketCountMap(), this, false));
    }

    /**
     * Handles the selection of a ticket in the recycler view.
     *
     * @param ticket The selected ticket.
     */
    @Override
    public void selectTicket(Ticket ticket) {
        // Not implemented in this context
    }

    /**
     * Handles the removal of a ticket in the recycler view.
     *
     * @param ticket The ticket to be removed.
     */
    @Override
    public void removeTicket(Ticket ticket) {
        // Not implemented in this context
    }
}
