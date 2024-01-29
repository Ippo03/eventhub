package com.example.eventhub.view.Event.CreateEventOverview;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventhub.R;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;
import com.example.eventhub.memorydao.EventDAOMemory;
import com.example.eventhub.memorydao.OrganizerDAOMemory;
import com.example.eventhub.memorydao.TicketCategoryDAOMemory;
import com.example.eventhub.memorydao.TicketDiscountDAOMemory;
import com.example.eventhub.view.Event.TicketCategorySelection.TicketCategorySelectionActivity;
import com.example.eventhub.view.Event.TicketCategorySelection.TicketCategorySelectionRecyclerViewAdapter;
import com.example.eventhub.view.Event.TicketDiscountSelection.TicketDiscountSelectionActivity;
import com.example.eventhub.view.Event.TicketDiscountSelection.TicketDiscountSelectionRecyclerViewAdapter;
import com.example.eventhub.view.OrganizerHomePage.OrganizerHomePageActivity;

import java.util.ArrayList;

/**
 * Activity for creating or editing event overview.
 */
public class CreateEventOverviewActivity extends AppCompatActivity implements CreateEventOverviewView,
        TicketCategorySelectionRecyclerViewAdapter.ItemSelectionListener, TicketDiscountSelectionRecyclerViewAdapter.ItemSelectionListener {
    RecyclerView recyclerViewTicketCategories;
    RecyclerView recyclerViewTicketDiscounts;
    TextView emptyTicketCategories;
    TextView emptyTicketDiscounts;
    Button btnMoveToSelectCategories;
    Button btnMoveToSelectDiscounts;
    CreateEventOverviewPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_overview);

        // Initialize the presenter with necessary DAOs
        presenter = new CreateEventOverviewPresenter(this, new EventDAOMemory(), new TicketCategoryDAOMemory(), new TicketDiscountDAOMemory(), new OrganizerDAOMemory());

        // Initialize views
        emptyTicketCategories = findViewById(R.id.emptyTicketCategories);
        btnMoveToSelectCategories = findViewById(R.id.buttonMoveToSelectCategories);
        recyclerViewTicketCategories = findViewById(R.id.recyclerViewEventOverviewCategories);
        presenter.onDisplayTicketCategories();

        emptyTicketDiscounts = findViewById(R.id.emptyTicketDiscounts);
        btnMoveToSelectDiscounts = findViewById(R.id.buttonMoveToSelectDiscounts);
        recyclerViewTicketDiscounts = findViewById(R.id.recyclerViewEventOverviewDiscounts);
        presenter.onDisplayTicketDiscounts();

        // Set click listeners for buttons
        btnMoveToSelectCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onMoveToSelectCategories();
            }
        });

        btnMoveToSelectDiscounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onMoveToSelectDiscounts();
            }
        });

        findViewById(R.id.buttonCompleteCreateEvent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSaveEvent();
            }
        });

        // Handle back button press
        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
//                Log.d("BACK BUTTON PRESSED IN C_E_O - ACTIVITY", "RESULT = 2");
                setResult(2); // Go back to TicketDiscountSelectionActivity and just close the CreateEventOverviewActivity
                finish();
            }
        });
    }

    /**
     * Retrieves the attached organizer ID from the intent.
     *
     * @return The attached organizer ID, or null if not present.
     */
    @Override
    public Integer getAttachedOrganizerId() {
        return getIntent().hasExtra("organizer_id") ? getIntent().getIntExtra("organizer_id", -1) : null;
    }

    /**
     * Retrieves the attached event from the intent.
     *
     * @return The attached event.
     */
    @Override
    public Event getAttachedEvent() {
        return (Event) getIntent().getSerializableExtra("event");
    }

    /**
     * Retrieves the attached ticket categories from the intent.
     *
     * @return The attached list of ticket categories.
     */
    @Override
    public ArrayList<TicketCategory> getAttachedTicketCategories() {
        return (ArrayList<TicketCategory>) getIntent().getSerializableExtra("ticket_categories");
    }

    /**
     * Retrieves the attached ticket discounts from the intent.
     *
     * @return The attached list of ticket discounts.
     */
    @Override
    public ArrayList<TicketDiscount> getAttachedTicketDiscounts() {
        return (ArrayList<TicketDiscount>) getIntent().getSerializableExtra("ticket_discounts");
    }

    /**
     * Sets the event name in the corresponding UI element.
     *
     * @param eventName The name of the event.
     */
    @Override
    public void setEventName(String eventName) {
        ((TextView) findViewById(R.id.textInputEventOverviewName)).setText(eventName);
    }

    /**
     * Sets the event address in the corresponding UI element.
     *
     * @param eventAddress The address of the event.
     */
    @Override
    public void setEventAddress(String eventAddress) {
        ((TextView) findViewById(R.id.textInputEventOverviewAddress)).setText(eventAddress);
    }

    /**
     * Sets the event date in the corresponding UI element.
     *
     * @param eventDate The date of the event.
     */
    @Override
    public void setEventDate(String eventDate) {
        ((TextView) findViewById(R.id.textInputEventOverviewDate)).setText(eventDate);
    }

    /**
     * Sets the event genre in the corresponding UI element.
     *
     * @param eventGenre The genre of the event.
     */
    @Override
    public void setEventGenre(String eventGenre) {
        ((TextView) findViewById(R.id.textInputEventOverviewGenre)).setText(eventGenre);
    }

    /**
     * Sets the event type in the corresponding UI element.
     *
     * @param eventType The type of the event.
     */
    @Override
    public void setEventType(String eventType) {
        ((TextView) findViewById(R.id.textInputEventOverviewType)).setText(eventType);
    }

    /**
     * Displays the list of ticket categories in the UI.
     */
    @Override
    public void showTicketCategories() {
        recyclerViewTicketCategories.setVisibility(View.VISIBLE);
        emptyTicketCategories.setVisibility(View.GONE);
        btnMoveToSelectCategories.setVisibility(View.GONE);

        recyclerViewTicketCategories.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTicketCategories.setAdapter(new TicketCategorySelectionRecyclerViewAdapter(presenter.getTicketCategoryList(), this));
    }

    /**
     * Displays a message indicating that there are no ticket categories.
     */
    @Override
    public void showEmptyTicketCategories() {
        recyclerViewTicketCategories.setVisibility(View.GONE);
        emptyTicketCategories.setVisibility(View.VISIBLE);
        btnMoveToSelectCategories.setVisibility(View.VISIBLE);
    }

    /**
     * Displays the list of ticket discounts in the UI.
     */
    @Override
    public void showTicketDiscounts() {
        recyclerViewTicketDiscounts.setVisibility(View.VISIBLE);
        emptyTicketDiscounts.setVisibility(View.GONE);
        btnMoveToSelectDiscounts.setVisibility(View.GONE);

        recyclerViewTicketDiscounts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTicketDiscounts.setAdapter(new TicketDiscountSelectionRecyclerViewAdapter(presenter.getTicketDiscountList(), this));
    }

    /**
     * Displays a message indicating that there are no ticket discounts.
     */
    @Override
    public void showEmptyTicketDiscounts() {
        recyclerViewTicketDiscounts.setVisibility(View.GONE);
        emptyTicketDiscounts.setVisibility(View.VISIBLE);
        btnMoveToSelectDiscounts.setVisibility(View.VISIBLE);
    }

    /**
     * Navigates to the ticket category selection screen.
     */
    @Override
    public void moveToSelectCategories() {
        Intent intent = new Intent(this, TicketCategorySelectionActivity.class);
        intent.putExtra("organizer_id", getAttachedOrganizerId());
        intent.putExtra("event", getAttachedEvent());
        intent.putExtra("ticket_categories", presenter.getTicketCategoryList()); // Send the modified ticket categories to the TicketCategorySelectionActivity
        intent.putExtra("ticket_discounts",  presenter.getTicketDiscountList()); // Send the modified ticket discounts to the TicketCategorySelectionActivity
        setResult(3, intent); // Go back to TicketCategorySelectionActivity and close the TicketDiscountSelectionActivity and the CreateEventOverviewActivity
        finish();
    }

    /**
     * Navigates to the ticket discount selection screen.
     */
    @Override
    public void moveToSelectDiscounts() {
        Intent intent = new Intent(this, TicketDiscountSelectionActivity.class);
        intent.putExtra("organizer_id", getAttachedOrganizerId());
        intent.putExtra("event", getAttachedEvent());
        intent.putExtra("ticket_categories", presenter.getTicketCategoryList()); // Send the modified ticket categories to the TicketDiscountSelectionActivity
        setResult(4, intent); // Go back to TicketDiscountSelectionActivity and just close the CreateEventOverviewActivity
        finish();
    }

    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    /**
     * Displays a success message for the creation of the event.
     */
    @Override
    public void showEventCreatedMessage() {
        new AlertDialog.Builder(this)
                .setTitle("Success")
                .setMessage("Your event has been created successfully!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        navigateToOrganizerHomePage(getAttachedOrganizerId());
                    }
                })
                .create()
                .show();
    }

    /**
     * Displays a success message for the update of the event.
     */
    @Override
    public void showEventUpdatedMessage() {
        new AlertDialog.Builder(this)
                .setTitle("Success")
                .setMessage("Your event has been updated successfully!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        navigateToOrganizerHomePage(getAttachedOrganizerId());
                    }
                })
                .create()
                .show();
    }

    /**
     * Navigates to the organizer's home page.
     *
     * @param organizerId The ID of the organizer.
     */
    @Override
    public void navigateToOrganizerHomePage(Integer organizerId) {
        Intent intent = new Intent(this, OrganizerHomePageActivity.class);
        intent.putExtra("organizer_id", organizerId);
        startActivity(intent); // Start the activity
        // Set result for CreateEditEventActivity and finish this Activity
        setResult(Activity.RESULT_OK);
        finish();
    }

    // LISTENERS

    /**
     * Handles the selection of a ticket category.
     *
     * @param ticketCategory The selected ticket category.
     */
    @Override
    public void selectTicketCategory(TicketCategory ticketCategory) {
        // Handle the selection of a ticket category
    }

    /**
     * Handles the removal of a ticket category.
     *
     * @param ticketCategory The ticket category to be removed.
     */
    @Override
    public void removeTicketCategory(TicketCategory ticketCategory) {
        presenter.onRemoveTicketCategory(ticketCategory);
    }

    /**
     * Handles the selection of a ticket discount.
     *
     * @param ticketDiscount The selected ticket discount.
     */
    @Override
    public void selectTicketDiscount(TicketDiscount ticketDiscount) {
        // Handle the selection of a ticket discount
    }

    /**
     * Handles the removal of a ticket discount.
     *
     * @param ticketDiscount The ticket discount to be removed.
     */
    @Override
    public void removeTicketDiscount(TicketDiscount ticketDiscount) {
        presenter.onRemoveTicketDiscount(ticketDiscount);
    }
}