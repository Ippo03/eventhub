package com.example.eventhub.view.Event.CreateEditEvent;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eventhub.R;
import com.example.eventhub.domain.Event;
import com.example.eventhub.memorydao.EventDAOMemory;
import com.example.eventhub.memorydao.TicketCategoryDAOMemory;
import com.example.eventhub.memorydao.TicketDiscountDAOMemory;
import com.example.eventhub.view.Event.CreateEventOverview.CreateEventOverviewActivity;
import com.example.eventhub.view.Event.TicketCategorySelection.TicketCategorySelectionActivity;

import java.util.HashMap;

/**
 * This activity allows users to create or edit events, providing necessary information such as event name,
 * address, type, date, time, and genre. It implements the CreateEditEventView interface to interact with the
 * presenter and handles user input to navigate to the ticket category selection or event overview.
 */
public class CreateEditEventActivity extends AppCompatActivity implements CreateEditEventView {
    /**
     * The presenter responsible for handling the logic of creating/editing events and interacting with the view.
     */
    CreateEditEventPresenter presenter;

    private ActivityResultLauncher<Intent> startActivityTicketCategorySelection;

    /**
     * Called when the activity is first created. Initializes the UI components, sets up event listeners, and
     * creates the presenter instance.
     *
     * @param savedInstanceState A Bundle containing the activity's previously saved state, if available.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        // Αdd values to the simple spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.genres));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner mySpinner = findViewById(R.id.spinnerNewEventGenre);
        mySpinner.setAdapter(adapter);

        // Initialize the presenter with DAO instances
        presenter = new CreateEditEventPresenter(this, new EventDAOMemory(), new TicketCategoryDAOMemory(), new TicketDiscountDAOMemory());

        startActivityTicketCategorySelection = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        setResult(Activity.RESULT_OK); // Pass the result code back to the OrganizerHomePageActivity
                        // Finish this Activity when the TicketCategorySelectionActivity sends back a result
                        finish();
                    } else if (result.getResultCode() == 1) { // If the user presses the back button in the TicketDiscountSelectionActivity
                        presenter.onSaveEvent(); // Move to TicketCategorySelectionActivity
                    } else if (result.getResultCode() == 3) { // If the user goes to TicketCategorySelection from CreateEventOverview
                        startActivityTicketCategorySelection.launch(result.getData()); // Launch a new TicketCategorySelectionActivity with the data from the CreateEventOverviewActivity
                    }
                });

        // Set up a click listener for the "Next" button to trigger the save event action
        findViewById(R.id.buttonNextToTicketCategorySelection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSaveEvent();
            }
        });
    }

    /**
    * Get the ID of the attached organizer from the intent.
    *
    * @return The ID of the organizer or null if not attached.
    */
    @Override
    public Integer getAttachedOrganizerId() {
        return getIntent().hasExtra("organizer_id") ? getIntent().getIntExtra("organizer_id", -1) : null;
    }

    /**
     * Get the ID of the attached event from the intent.
     *
     * @return The ID of the event or null if not attached.
     */
    @Override
    public Integer getAttachedEventId() {
        return getIntent().hasExtra("event_id") ? getIntent().getIntExtra("event_id", -1) : null;
    }

    /**
     * Get the information about the event from the UI components and return it as a HashMap.
     *
     * @return A HashMap containing various details about the event.
     */
    @Override
    public HashMap<String, String> getEventInfo() {
        HashMap<String, String> eventInfo = new HashMap<>();

        eventInfo.put("name", getEventName());
        eventInfo.put("address", getEventAddress());
        eventInfo.put("type", getEventType());
        eventInfo.put("date", getEventDate());
        eventInfo.put("time", getEventTime());
        eventInfo.put("genre", getEventGenre());

        return eventInfo;
    }

    /**
     * Get the name of the event from the UI component.
     *
     * @return The name of the event.
     */
    @Override
    public String getEventName() {
        return ((EditText)findViewById(R.id.textInputNewEventName)).getText().toString();
    }

    /**
     * Get the address of the event from the UI component.
     *
     * @return The address of the event.
     */
    @Override
    public String getEventAddress() {
        return ((EditText)findViewById(R.id.textInputNewEventAddress)).getText().toString();
    }

    /**
     * Get the date of the event from the UI component.
     *
     * @return The date of the event.
     */
    @Override
    public String getEventDate() {
        return ((EditText)findViewById(R.id.textInputNewEventDate)).getText().toString();
    }

    /**
     * Get the time of the event from the UI component.
     *
     * @return The time of the event.
     */
    @Override
    public String getEventTime() {
        return ((EditText)findViewById(R.id.textInputNewEventTime)).getText().toString();
    }

    /**
     * Get the type of the event from the UI component.
     *
     * @return The type of the event.
     */
    @Override
    public String getEventType() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupNewEventType);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();

        if (radioButtonID == -1) {
            // No radio button is selected, return an empty string
            return null;
        } else {
            return ((RadioButton) radioGroup.findViewById(radioButtonID)).getText().toString().trim();
        }
    }

    /**
     * Get the genre of the event from the UI component.
     *
     * @return The genre of the event.
     */
    @Override
    public String getEventGenre() {
        return ((Spinner)findViewById(R.id.spinnerNewEventGenre)).getSelectedItem().toString().trim();
    }

    /**
     * Sets the name of the event in the UI.
     *
     * @param name The name of the event.
     */
    @Override
    public void setEventName(String name) {
        ((EditText)findViewById(R.id.textInputNewEventName)).setText(name);
    }

    /**
     * Sets the address of the event in the UI.
     *
     * @param address The address of the event.
     */
    @Override
    public void setEventAddress(String address) {
        ((EditText)findViewById(R.id.textInputNewEventAddress)).setText(address);
    }

    /**
     * Sets the type of the event in the UI. It checks the type and selects the appropriate radio button.
     *
     * @param type The type of the event.
     */
    @Override
    public void setEventType(String type) {
        if ("open".equalsIgnoreCase(type)) {
            ((RadioButton) findViewById(R.id.checkBoxOpen)).setChecked(true);
        } else if ("closed".equalsIgnoreCase(type)) {
            ((RadioButton) findViewById(R.id.checkBoxClosed)).setChecked(true);
        } else {
            ((RadioButton) findViewById(R.id.checkBoxFree)).setChecked(true);
        }
    }

    /**
     * Sets the date of the event in the UI.
     *
     * @param date The date of the event.
     */
    @Override
    public void setEventDate(String date) {
        ((EditText) findViewById(R.id.textInputNewEventDate)).setText(date);
    }

    /**
     * Sets the time of the event in the UI.
     *
     * @param time The time of the event.
     */
    @Override
    public void setEventTime(String time) {
        ((EditText) findViewById(R.id.textInputNewEventTime)).setText(time);
    }

    /**
     * Sets the genre of the event in the UI.
     *
     * @param genre The genre of the event.
     */
    @Override
    public void setEventGenre(Integer genre) {
        ((Spinner)findViewById(R.id.spinnerNewEventGenre)).setSelection(genre);
    }

    /**
     * Displays an error message in an alert dialog.
     *
     * @param title   The title of the error dialog.
     * @param message The error message to be displayed.
     */
    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(CreateEditEventActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    /**
     * Moves to the ticket category selection screen for the given event.
     *
     * @param event The event for which ticket categories are being selected.
     */
    public void moveToTicketCategorySelection(Event event) {
        Intent intent = new Intent(this, TicketCategorySelectionActivity.class);
        intent.putExtra("organizer_id", getAttachedOrganizerId());
        intent.putExtra("event", event);
        startActivityTicketCategorySelection.launch(intent);
    }

    /**
     * Moves to the create event overview screen for the given event.
     *
     * @param event The event for which the overview is being created.
     */
    @Override
    public void moveToCreateEventOverview(Event event) {
        Intent intent = new Intent(this, CreateEventOverviewActivity.class);
        intent.putExtra("organizer_id", getAttachedOrganizerId());
        intent.putExtra("event", event);
        intent.putExtra("ticket_categories", event.getTicketCategories());
        intent.putExtra("ticket_discounts", event.getTicketDiscounts());
        startActivity(intent);
    }

    /**
     * Prompts the user to enter the quantity of tickets for a free attendance event.
     *
     * @param event The free attendance event for which the quantity is being entered.
     */
    @Override
    public void selectFreeTicketCategoryQuantity(Event event) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter the quantity of the category");
        builder.setMessage("The event you are creating/editing is free attendance only, so just input the quantity of the tickets you can provide.");

        // Set up the input field
        final EditText input = new EditText(this);
        // Specify the type of input expected
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setHint(String.valueOf(event.getEventCapacity()));
        builder.setView(input);

        // Set up the OK button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onCreateFreeEvent(input.getText().toString(), event);
            }
        });
        // Set up the cancel button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
