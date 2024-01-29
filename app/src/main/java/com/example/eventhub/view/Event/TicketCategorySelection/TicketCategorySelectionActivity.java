package com.example.eventhub.view.Event.TicketCategorySelection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
import com.example.eventhub.memorydao.TicketCategoryDAOMemory;
import com.example.eventhub.view.Event.TicketDiscountSelection.TicketDiscountSelectionActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Activity for managing ticket categories, allowing the organizer to add, edit, and remove ticket categories for an event.
 */
public class TicketCategorySelectionActivity extends AppCompatActivity implements TicketCategorySelectionView,
        TicketCategorySelectionRecyclerViewAdapter.ItemSelectionListener {
    private RecyclerView recyclerView;
    TicketCategorySelectionPresenter presenter;
    private ActivityResultLauncher<Intent> startActivityTicketDiscountSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_category_selection);

        presenter = new TicketCategorySelectionPresenter(this, new TicketCategoryDAOMemory(), new EventDAOMemory());

        recyclerView = findViewById(R.id.recyclerAddedTicketCategories);
        presenter.onShowTicketCategories();

        startActivityTicketDiscountSelection = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        setResult(RESULT_OK); // Pass the result code back to the CreateEditEventActivity
                        // Finish this Activity when the TicketDiscountSelectionActivity sends back a result
                        finish();
                    } else if (result.getResultCode() == 1) { // If the user presses the back button in the TicketDiscountSelectionActivity
                        setResult(result.getResultCode(), result.getData()); // Pass the result code back to the CreateEditEventActivity
                    } else if (result.getResultCode() == 2) { // If the user presses the back button in the CreateEventOverviewActivity
//
                        presenter.onMoveToTicketDiscountSelection(); // Launch a new TicketDiscountSelectionActivity
                    } else if (result.getResultCode() == 3) { // If the user goes to TicketCategorySelection from CreateEventOverview
                        setResult(result.getResultCode(), result.getData()); // Pass the result code back to the CreateEditEventActivity
                        finish();
                    } else if (result.getResultCode() == 4){ // If the user goes to TicketDiscountSelection from CreateEventOverview
                        startActivityTicketDiscountSelection.launch(result.getData()); // Launch a new TicketDiscountSelectionActivity with the data from the CreateEventOverviewActivity
                    }
                });
        findViewById(R.id.buttonAddTicketCategory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddTicketCategory();
            }
        });

        findViewById(R.id.buttonNextToTicketDiscountSelection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onMoveToTicketDiscountSelection();
            }
        });
    }

    /**
     * Retrieves the attached organizer ID from the intent.
     *
     * @return The attached organizer ID, or null if not found.
     */
    @Override
    public Integer getAttachedOrganizerId() {
        return getIntent().hasExtra("organizer_id") ? getIntent().getIntExtra("organizer_id", -1) : null;
    }

    /**
     * Retrieves the attached event from the intent.
     *
     * @return The attached event, or null if not found.
     */
    @Override
    public Event getAttachedEvent() {
        return (Event) getIntent().getSerializableExtra("event");
    }

    /**
     * Sets the page title for editing mode.
     *
     * @param title The title to set.
     */
    @Override
    public void setPageTitleToEdit(String title) {
        ((TextView)findViewById(R.id.titleTicketCategoryInsertion)).setText(title);
    }

    /**
     * Sets the button label for editing mode.
     *
     * @param label The label to set.
     */
    @Override
    public void setButtonLabelToEdit(String label) {
        ((TextView)findViewById(R.id.buttonAddTicketCategory)).setText(label);
    }

    /**
     * Sets the text displaying the event's total capacity.
     *
     * @param capacity The capacity to set.
     */
    @Override
    public void setTextEventCapacity(String capacity) {
        ((TextView)findViewById(R.id.textEventTotalCapacity)).setText(capacity);
    }

    /**
     * Retrieves information about the ticket category from input fields.
     *
     * @return A {@link HashMap} containing ticket category information.
     */
    @Override
    public HashMap<String, String> getTicketCategoryInfo() {
        HashMap<String, String> ticketCategoryInfo = new HashMap<>();

        ticketCategoryInfo.put("name", getTicketCategoryName());
        ticketCategoryInfo.put("price", getTicketCategoryPrice());
        ticketCategoryInfo.put("description", getTicketCategoryDescription());
        ticketCategoryInfo.put("quantity", getTicketCategoryQuantity());

        return ticketCategoryInfo;
    }

    /**
     * Retrieves the name of the ticket category from the input field.
     *
     * @return The name of the ticket category.
     */
    @Override
    public String getTicketCategoryName() {
        return ((EditText) findViewById(R.id.textInputCategeryName)).getText().toString().trim();
    }

    /**
     * Retrieves the price of the ticket category from the input field.
     *
     * @return The price of the ticket category.
     */
    @Override
    public String getTicketCategoryPrice() {
        return ((EditText) findViewById(R.id.textInputCategoryPrice)).getText().toString().trim();
    }

    /**
     * Retrieves the description of the ticket category from the input field.
     *
     * @return The description of the ticket category.
     */
    @Override
    public String getTicketCategoryDescription() {
        return ((EditText) findViewById(R.id.textInputCategoryDescription)).getText().toString().trim();
    }

    /**
     * Retrieves the quantity of the ticket category from the input field.
     *
     * @return The quantity of the ticket category.
     */
    @Override
    public String getTicketCategoryQuantity() {
        return ((EditText) findViewById(R.id.textInputCategoryQuantity)).getText().toString().trim();
    }

    /**
     * Sets the name of the ticket category in the input field.
     *
     * @param name The name to set.
     */
    @Override
    public void setTicketCategoryName(String name) {
        ((EditText) findViewById(R.id.textInputCategeryName)).setText(name);
    }

    /**
     * Sets the price of the ticket category in the input field.
     *
     * @param price The price to set.
     */
    @Override
    public void setTicketCategoryPrice(String price) {
        ((EditText) findViewById(R.id.textInputCategoryPrice)).setText(price);
    }

    /**
     * Sets the description of the ticket category in the input field.
     *
     * @param description The description to set.
     */
    @Override
    public void setTicketCategoryDescription(String description) {
        ((EditText) findViewById(R.id.textInputCategoryDescription)).setText(description);
    }

    /**
     * Sets the quantity of the ticket category in the input field.
     *
     * @param quantity The quantity to set.
     */
    @Override
    public void setTicketCategoryQuantity(String quantity) {
        ((EditText) findViewById(R.id.textInputCategoryQuantity)).setText(quantity);
    }

    /**
     * Displays the list of added ticket categories in the RecyclerView.
     */
    @Override
    public void showTicketCategories() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TicketCategorySelectionRecyclerViewAdapter(presenter.getTicketCategoriesList(), this));
    }

    /**
     * Displays an error message in an alert dialog.
     *
     * @param title   The title of the error dialog.
     * @param message The error message to display.
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
     * Resets input fields for a new ticket category entry.
     */
    @Override
    public void resetInputFields() {
        setTicketCategoryName("");
        setTicketCategoryPrice("");
        setTicketCategoryDescription("");
        setTicketCategoryQuantity("");
    }

    /**
     * Moves to the ticket discount selection activity, passing the list of ticket categories.
     *
     * @param ticketCategoriesList The list of ticket categories to pass.
     */
    @Override
    public void moveToTicketDiscountSelection(ArrayList<TicketCategory> ticketCategoriesList) {
        Intent intent = new Intent(this, TicketDiscountSelectionActivity.class);
        // If the user has already inputted ticketDiscounts do not lose them
        if (getIntent().hasExtra("ticket_discounts")){
            ArrayList<TicketDiscount> ticketDiscounts = (ArrayList<TicketDiscount>) getIntent().getSerializableExtra("ticket_discounts");
            getAttachedEvent().setTicketDiscounts(ticketDiscounts);
        }
        intent.putExtra("organizer_id", getAttachedOrganizerId());
        intent.putExtra("event", getAttachedEvent());
        intent.putExtra("ticket_categories", ticketCategoriesList);
        startActivityTicketDiscountSelection.launch(intent);
    }

    // LISTENERS

    /**
     * Listener for selecting a ticket category, delegated to the presenter.
     *
     * @param ticketCategory The selected ticket category.
     */
    @Override
    public void selectTicketCategory(TicketCategory ticketCategory) {
        presenter.onSelectTicketCategory(ticketCategory);
    }

    /**
     * Listener for removing a ticket category, delegated to the presenter.
     *
     * @param ticketCategory The ticket category to remove.
     */
    @Override
    public void removeTicketCategory(TicketCategory ticketCategory) {
        presenter.onRemoveTicketCategory(ticketCategory);
    }

}