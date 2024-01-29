package com.example.eventhub.view.Event.TicketDiscountSelection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.eventhub.memorydao.TicketDiscountDAOMemory;
import com.example.eventhub.view.Event.CreateEventOverview.CreateEventOverviewActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class TicketDiscountSelectionActivity extends AppCompatActivity implements TicketDiscountSelectionView,
        TicketDiscountSelectionRecyclerViewAdapter.ItemSelectionListener {
    private RecyclerView recyclerView;
    TicketDiscountSelectionPresenter presenter;
    private ActivityResultLauncher<Intent> startActivityCreateEventOverview;

    /**
     * Activity class for the Ticket Discount Selection screen.
     * Implements the TicketDiscountSelectionView interface to interact with the presenter.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_discount_selection);

        presenter = new TicketDiscountSelectionPresenter(this, new TicketDiscountDAOMemory(), new EventDAOMemory());

        recyclerView = findViewById(R.id.recyclerAddedTicketDiscounts);
        presenter.onShowTicketDiscounts();

        startActivityCreateEventOverview = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) { // If the user completes the event creation/edit
                        setResult(RESULT_OK); // Pass the result code back to the TicketCategorySelectionActivity
                        // Finish this Activity when the CreateEventOverviewActivity sends back a result
                        finish();
                    } else if (result.getResultCode() == 3) { // If the user goes to TicketCategorySelection from CreateEventOverview
                        setResult(result.getResultCode(), result.getData()); // Pass the result code back to the TicketCategorySelectionActivity
                        finish();
                    } else if (result.getResultCode() == 4){ // If the user goes to TicketDiscountSelection from CreateEventOverview
                        setResult(result.getResultCode(), result.getData()); // Pass the result code back to the TicketCategorySelectionActivity
                        finish();
                    }
                });

        // Handle back button press
        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                setResult(1); // Pass the result code back to the TicketCategorySelectionActivity and close this Activity
                finish();
            }
        });
        findViewById(R.id.buttonAddTicketDiscount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddTicketDiscount();
            }
        });

        findViewById(R.id.buttonNextToCreateEventOverview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onMoveToCreateEventOverview();
            }
        });
    }

    /**
     * Retrieves the attached organizer ID from the intent.
     *
     * @return Integer representing the organizer ID, or null if not found.
     */
    @Override
    public Integer getAttachedOrganizerId() {
        return getIntent().hasExtra("organizer_id") ? getIntent().getIntExtra("organizer_id", -1) : null;
    }

    /**
     * Retrieves the attached event from the intent.
     *
     * @return Event object representing the attached event.
     */
    @Override
    public Event getAttachedEvent() {
        return (Event) getIntent().getSerializableExtra("event");
    }

    /**
     * Retrieves the attached ticket categories from the intent.
     *
     * @return ArrayList of TicketCategory representing the attached ticket categories.
     */
    @Override
    public ArrayList<TicketCategory> getAttachedTicketCategories() {
        return (ArrayList<TicketCategory>) getIntent().getSerializableExtra("ticket_categories");
    }

    /**
     * Sets the page title for editing.
     *
     * @param title The title to set for editing mode.
     */
    @Override
    public void setPageTitleToEdit(String title) {
        ((TextView)findViewById(R.id.titleTicketDiscountInsertion)).setText(title);
    }

    /**
     * Sets the button label for editing.
     *
     * @param label The label to set for editing mode.
     */
    @Override
    public void setButtonLabelToEdit(String label) {
        ((Button)findViewById(R.id.buttonAddTicketDiscount)).setText(label);
    }

    /**
     * Retrieves the ticket discount information from input fields.
     *
     * @return HashMap containing ticket discount information.
     */
    @Override
    public HashMap<String, String> getTicketDiscountInfo() {
        HashMap<String, String> ticketDiscountInfo = new HashMap<>();

        ticketDiscountInfo.put("type", getTicketDiscountType());
        ticketDiscountInfo.put("percentage", getTicketDiscountPercentage());

        return ticketDiscountInfo;
    }

    /**
     * Retrieves the ticket discount type from the input field.
     *
     * @return String representing the ticket discount type.
     */
    @Override
    public String getTicketDiscountType() {
        return ((EditText) findViewById(R.id.textInputDiscountType)).getText().toString().trim();
    }

    /**
     * Retrieves the ticket discount percentage from the input field.
     *
     * @return String representing the ticket discount percentage.
     */
    @Override
    public String getTicketDiscountPercentage() {
        return ((EditText) findViewById(R.id.textInputDiscountPercentage)).getText().toString().trim();
    }

    /**
     * Sets the ticket discount type in the input field.
     *
     * @param ticketDiscountType The ticket discount type to set.
     */
    @Override
    public void setTicketDiscountType(String ticketDiscountType) {
        ((EditText) findViewById(R.id.textInputDiscountType)).setText(ticketDiscountType);
    }

    /**
     * Sets the ticket discount percentage in the input field.
     *
     * @param ticketDiscountPercentage The ticket discount percentage to set.
     */
    @Override
    public void setTicketDiscountPercentage(String ticketDiscountPercentage) {
        ((EditText) findViewById(R.id.textInputDiscountPercentage)).setText(ticketDiscountPercentage);
    }

    /**
     * Displays the ticket discounts using a RecyclerView.
     */
    @Override
    public void showTicketDiscounts() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TicketDiscountSelectionRecyclerViewAdapter(presenter.getTicketDiscountList(), this));
    }

    /**
     * Displays an error message using an AlertDialog.
     *
     * @param title   The title of the error message.
     * @param message The content of the error message.
     */
    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    /**
     * Resets input fields related to ticket discounts.
     */
    @Override
    public void resetInputFields() {
        setTicketDiscountType("");
        setTicketDiscountPercentage("");
    }

    /**
     * Moves to the Create Event Overview activity with necessary data.
     *
     * @param ticketDiscountList List of TicketDiscount objects to be passed to the activity.
     */
    @Override
    public void moveToCreateEventOverview(ArrayList<TicketDiscount> ticketDiscountList) {
        Intent intent = new Intent(this, CreateEventOverviewActivity.class);
        intent.putExtra("organizer_id", getAttachedOrganizerId());
        intent.putExtra("event", getAttachedEvent());
        intent.putExtra("ticket_categories", getAttachedTicketCategories());
        intent.putExtra("ticket_discounts", ticketDiscountList);
        startActivityCreateEventOverview.launch(intent);
    }

    /**
     * Callback method triggered when a TicketDiscount is selected.
     *
     * @param ticketDiscount The selected TicketDiscount object.
     */
    @Override
        public void selectTicketDiscount(TicketDiscount ticketDiscount) {
            presenter.onSelectTicketDiscount(ticketDiscount);
        }

    /**
     * Callback method triggered when a TicketDiscount is to be removed.
     *
     * @param ticketDiscount The TicketDiscount object to be removed.
     */
    @Override
        public void removeTicketDiscount(TicketDiscount ticketDiscount) {
            presenter.onRemoveTicketDiscount(ticketDiscount);
        }
}