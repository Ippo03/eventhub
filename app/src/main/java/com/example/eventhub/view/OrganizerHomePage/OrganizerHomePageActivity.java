package com.example.eventhub.view.OrganizerHomePage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventhub.R;
import com.example.eventhub.domain.Event;
import com.example.eventhub.view.Account.Register.RegisterEditOrganizer.RegisterEditOrganizerActivity;
import com.example.eventhub.view.Event.CreateEditEvent.CreateEditEventActivity;
import com.example.eventhub.view.Event.EventDetails.EventDetailsActivity;
import com.example.eventhub.view.HomePage.HomePageActivity;

/**
 * Activity class representing the home page for organizers. Displays a list of organized events and provides actions like creating/editing events, editing the organizer's account, and logging out.
 */
public class OrganizerHomePageActivity extends AppCompatActivity implements OrganizerHomePageView,
    OrganizerHomePageRecyclerViewAdapter.ItemSelectionListener {

    public int organizerId;
    RecyclerView recyclerView;
    TextView emptyEvents;
    OrganizerHomePageViewModel viewModel;
    private ActivityResultLauncher<Intent> startActivityCreateEditEvent;

    /**
     * Called when the activity is first created. Responsible for initializing the UI, setting up the ViewModel, and handling user interactions.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this Bundle contains the data it most recently supplied in {@link #onSaveInstanceState}.
     *                           Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_home_page);

        viewModel = new ViewModelProvider(this).get(OrganizerHomePageViewModel.class);
        viewModel.getPresenter().setView(this);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            organizerId = extras.getInt("organizer_id");
        }

        Log.d("ORGANIZER ID", String.valueOf(organizerId));
        viewModel.getPresenter().setOrganizer(organizerId);
        viewModel.getPresenter().setOrganizedEventList();

        startActivityCreateEditEvent = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Finish this Activity when the CreateEditEventActivity sends back a result
                        finish();
                    }
                });
        // Handle back button press
        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                if (organizerId != 0) {
                    // Organizer is signed in, do nothing
                }
            }
        });

        recyclerView = findViewById(R.id.recyclerOrganizedEvents);
        emptyEvents = findViewById(R.id.textEmptyEvents);
        viewModel.getPresenter().onDisplayEvents();

        findViewById(R.id.buttonCreateEvent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onCreateEvent();
            }
        });

        findViewById(R.id.buttonEditAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onEditAccount();
            }
        });
        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onLogout();
            }
        });
    }

    /**
     * Called when the activity is resumed. Updates the organized event list and displays events accordingly.
     */
    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getPresenter().setOrganizedEventList();
        viewModel.getPresenter().onDisplayEvents();
    }

    /**
     * Handles the selection of an event from the organized events list. Navigates to the Event Details screen.
     *
     * @param event The selected event.
     */
    @Override
    public void selectEvent(Event event) {
        Intent intent = new Intent(OrganizerHomePageActivity.this, EventDetailsActivity.class);
        intent.putExtra("event_id", event.getEventId());
        intent.putExtra("organizer_id", organizerId);
        startActivity(intent);
    }

    /**
     * Handles the request to edit an event. Navigates to the Create/Edit Event screen for the selected event.
     *
     * @param event The event to be edited.
     */
    @Override
    public void editEvent(Event event) {
        Intent intent = new Intent(OrganizerHomePageActivity.this, CreateEditEventActivity.class);
        intent.putExtra("event_id", event.getEventId());
        intent.putExtra("organizer_id", organizerId);
        startActivityCreateEditEvent.launch(intent);
    }

    /**
     * Displays a message indicating that there are no organized events.
     */
    @Override
    public void showEmptyEvents() {
        recyclerView.setVisibility(View.GONE);
        emptyEvents.setVisibility(View.VISIBLE);
    }

    /**
     * Displays the organized events in a RecyclerView.
     */
    @Override
    public void showOrganizedEvents() {
        recyclerView.setVisibility(View.VISIBLE);
        emptyEvents.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OrganizerHomePageRecyclerViewAdapter(viewModel.getPresenter().getOrganizedEventList(), this));
    }

    /**
     * Handles the request to create a new event. Navigates to the Create/Edit Event screen.
     */
    @Override
    public void createEvent() {
        Intent intent = new Intent(OrganizerHomePageActivity.this, CreateEditEventActivity.class);
        intent.putExtra("organizer_id", organizerId);
        startActivity(intent);
    }

    /**
     * Handles the request to edit the organizer's account. Navigates to the Register/Edit Organizer screen.
     */
    @Override
    public void editAccount() {
        Intent intent = new Intent(OrganizerHomePageActivity.this, RegisterEditOrganizerActivity.class);
        intent.putExtra("organizer_id", organizerId);
        startActivity(intent);
    }

    /**
     * Handles the request to logout. Finishes the current activity and navigates to the Home Page screen.
     */
    @Override
    public void logout() {
        finish();
        Intent intent = new Intent(OrganizerHomePageActivity.this, HomePageActivity.class);
        startActivity(intent);
    }
}