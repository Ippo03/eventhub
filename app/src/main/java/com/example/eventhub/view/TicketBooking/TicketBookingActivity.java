package com.example.eventhub.view.TicketBooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventhub.R;
import com.example.eventhub.domain.Ticket;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;
import com.example.eventhub.view.HomePage.HomePageActivity;
import com.example.eventhub.view.Util.CustomSpinnerAdapter;

/**
 * Activity for handling the ticket booking process. Allows users to select tickets, apply discounts,
 * view selected tickets, and complete the purchase.
 */
public class TicketBookingActivity extends AppCompatActivity implements TicketBookingView, TicketBookingRecyclerViewAdapter.OnItemClickListener  {
    int eventId;
    int customerId;
    private Spinner categorySpinner;
    private Spinner discountSpinner;
    RecyclerView recyclerView;
    TicketBookingViewModel viewModel;

    /**
     * Called when the activity is first created. Initializes UI components and sets up listeners.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in
     *                           onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_booking);

        // Retrieve event and customer IDs from Intent extras
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            eventId = extras.getInt("event_id");
            customerId = extras.getInt("customer_id");
        }

        // Initialize ViewModel and attach the view
        viewModel = new ViewModelProvider(this).get(TicketBookingViewModel.class);
        viewModel.getPresenter().setView(this);

        // Set event and customer for the presenter
        viewModel.getPresenter().setEvent(eventId);
        viewModel.getPresenter().setName();
        viewModel.getPresenter().setCustomer(customerId);

        // Set up Category Spinner
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        CustomSpinnerAdapter adapterCategory = new CustomSpinnerAdapter(this,
                android.R.layout.simple_spinner_item, viewModel.getPresenter().getTicketCategories());

        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapterCategory);
        categorySpinner.setSelection(0, true);

        // Set up Discount Spinner
        discountSpinner = (Spinner) findViewById(R.id.discountSpinner);
        CustomSpinnerAdapter adapterDiscount = new CustomSpinnerAdapter(this,
                android.R.layout.simple_spinner_item, viewModel.getPresenter().getTicketDiscounts());

        adapterDiscount.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        discountSpinner.setAdapter(adapterDiscount);
        discountSpinner.setSelection(0, true);

        // Update ticket price based on selected category and discount
        viewModel.getPresenter().onUpdateTicketPrice();

        // Set up RecyclerView for displaying selected tickets
        recyclerView = findViewById(R.id.recyclerTicketsSelected);
        viewModel.getPresenter().onShowTickets();
        viewModel.getPresenter().onStartListener();

        // Add button click listener to add a ticket to the cart
        findViewById(R.id.buttonAddTicket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getPresenter().onAddTicket(viewModel.getPresenter().onUpdateTicketPrice());
                Toast.makeText(TicketBookingActivity.this, "Ticket added to cart !", Toast.LENGTH_SHORT).show();
            }});

        // Add button click listener to complete the purchase
        findViewById(R.id.buttonCompletePurchase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onCompletePurchase();
            }
        });
    }

    /**
     * Retrieves the attached event ID from Intent extras.
     *
     * @return The attached event ID or null if not available.
     */
    @Override
    public int getAttachedEventId() {
        return this.getIntent().hasExtra("event_id") ? this.getIntent().getExtras().getInt("event_id") : null;
    }

    /**
     * Sets the name of the attached event in the UI.
     *
     * @param name The name of the attached event.
     */
    @Override
    public void setEventName(String name) {
        ((TextView) findViewById(R.id.textEventNameTicket)).setText(name);
    }

    /**
     * Sets the displayed ticket price in the UI.
     *
     * @param price The ticket price to be displayed.
     */
    @Override
    public void setTicketPrice(String price) {
        ((TextView) findViewById(R.id.textTicketCost)).setText(price.toString());
    }

    /**
     * Sets the displayed total cost in the UI.
     *
     * @param totalCost The total cost to be displayed.
     */
    @Override
    public void setTotalCost(String totalCost) {
        ((TextView) findViewById(R.id.textPurchaseTotalCost)).setText(totalCost);
    }

    /**
     * Retrieves the selected ticket category from the UI.
     *
     * @return The selected TicketCategory.
     */
    @Override
    public TicketCategory getSelectedCategory() {
        String selectedItem = categorySpinner.getSelectedItem().toString();
        Integer categoryId = viewModel.getPresenter().ticketCategoryMap.get(selectedItem);
        return viewModel.getPresenter().getTicketCategoryById(categoryId);
    }

    /**
     * Retrieves the selected ticket discount from the UI.
     *
     * @return The selected TicketDiscount.
     */
    @Override
    public TicketDiscount getSelectedDiscount() {
        String selectedItem = discountSpinner.getSelectedItem().toString();
        Integer discountId = viewModel.getPresenter().ticketDiscountMap.get(selectedItem);
        return viewModel.getPresenter().getTicketDiscountById(discountId);
    }

    /**
     * Sets up a listener for category changes in the UI.
     */
    @Override
    public void onChangeCategory() {
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                viewModel.getPresenter().onUpdateTicketPrice();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Sets up a listener for discount changes in the UI.
     */
    @Override
    public void onChangeDiscount() {
        discountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                viewModel.getPresenter().onUpdateTicketPrice();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Displays the selected tickets in the RecyclerView.
     */
    @Override
    public void showTickets() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TicketBookingRecyclerViewAdapter adapter = new TicketBookingRecyclerViewAdapter(viewModel.getPresenter().getAggregatedTicketList(), viewModel.getPresenter().getTicketCountMap(), this, true);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Removes a ticket from the cart and displays a notification.
     *
     * @param ticket The ticket to be removed.
     */
    @Override
    public void removeTicket(Ticket ticket) {
        Toast.makeText(TicketBookingActivity.this, "Ticket removed from cart !", Toast.LENGTH_SHORT).show();
        viewModel.getPresenter().onRemoveTicket(ticket);

    }

    /**
     * Displays a completion message after a successful purchase and redirects to the home page.
     */
    @Override
    public void showPurchaseCompleteMessage() {
        new AlertDialog.Builder(TicketBookingActivity.this)
                .setCancelable(true)
                .setTitle("Purchase Complete")
                .setMessage("Your purchase has been completed !\n You will be redirected to your home page.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(TicketBookingActivity.this, HomePageActivity.class);
                        intent.putExtra("customer_id", customerId);
                        startActivity(intent);
                    }
                }).create().show();
    }

    /**
     * Displays an error message in a dialog.
     *
     * @param title   The title of the error dialog.
     * @param message The error message to be displayed.
     */
    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(TicketBookingActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    /**
     * Placeholder method for handling ticket selection (not implemented in this activity).
     *
     * @param ticket The selected ticket.
     */
    @Override
    public void selectTicket(Ticket ticket) {
        // Not implemented in this activity
    }
}