package com.example.eventhub.view.Event.EventDetails;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventhub.R;
import com.example.eventhub.domain.Review;
import com.example.eventhub.view.Account.Login.LoginActivity;
import com.example.eventhub.view.Account.Register.RegisterEditCustomer.RegisterEditCustomerActivity;
import com.example.eventhub.view.HomePage.HomePageActivity;
import com.example.eventhub.view.TicketBooking.TicketBookingActivity;

/**
 * Activity for displaying detailed information about an event, including reviews and ticket booking options.
 */
public class EventDetailsActivity extends AppCompatActivity implements EventDetailsView,
    EventDetailsRecyclerViewAdapter.ItemSelectionListener {
    Integer customerId;
    Integer organizerId;
    TextView emptyReviews;
    Button buttonBookTicket;
    RecyclerView recyclerView;
    EventDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            customerId = extras.getInt("customer_id");
            organizerId = extras.getInt("organizer_id");
        }

        viewModel = new ViewModelProvider(this).get(EventDetailsViewModel.class);
        viewModel.getPresenter().setView(this);

        viewModel.getPresenter().setEvent(getAttachedEventId());
        viewModel.getPresenter().setCustomer(customerId); // null if not logged in
        viewModel.getPresenter().setOrganizer(organizerId);
        viewModel.getPresenter().setReviewList();
        viewModel.getPresenter().setEventDetails();

        // Set up the RecyclerView
        recyclerView = findViewById(R.id.recyclerEventReviews);
        emptyReviews = findViewById(R.id.textEmptyReviews);
        buttonBookTicket = findViewById(R.id.buttonBookTicket);
        viewModel.getPresenter().onDisplayBookTicketButton(organizerId);
        viewModel.getPresenter().onDisplayReviews();

        buttonBookTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onBookTicket();
            }
        });
    }

    /**
     * Retrieves the ID of the attached event from the intent extras.
     *
     * @return The ID of the attached event.
     */
    @Override
    public int getAttachedEventId() {
        return this.getIntent().hasExtra("event_id") ? this.getIntent().getExtras().getInt("event_id") : null;
    }

    /**
     * Sets the name of the event in the corresponding view.
     *
     * @param name The name of the event.
     */
    @Override
    public void setEventName(String name) {
        ((TextView) findViewById(R.id.textEventDetailsName)).setText(name);
    }

    /**
     * Sets the address of the event in the corresponding view.
     *
     * @param address The address of the event.
     */
    @Override
    public void setEventAddress(String address) {
        ((TextView) findViewById(R.id.textEventDetailsAddress)).setText(address);
    }

    /**
     * Sets the genre of the event in the corresponding view.
     *
     * @param genre The genre of the event.
     */
    @Override
    public void setEventGenre(String genre) {
        ((TextView) findViewById(R.id.textEventDetailsGenre)).setText(genre);
    }

    /**
     * Sets the type of the event in the corresponding view.
     *
     * @param eventType The type of the event.
     */
    @Override
    public void setEventType(String eventType) {
        ((TextView) findViewById(R.id.textEventDetailsType)).setText(eventType);
    }

    /**
     * Sets the date and time of the event in the corresponding view.
     *
     * @param dateTime The date and time of the event.
     */
    @Override
    public void setEventTime(String dateTime) {
        ((TextView) findViewById(R.id.textEventDetailsTime)).setText(dateTime);
    }

    /**
     * Sets the average rating of the event in the corresponding view.
     *
     * @param averageRating The average rating of the event.
     */
    @Override
    public void setAverageRating(String averageRating) {
        ((TextView) findViewById(R.id.textEventDetailsRating)).setText(averageRating);
    }

    /**
     * Sets the capacity of the event in the corresponding view.
     *
     * @param eventCapacity The capacity of the event.
     */
    @Override
    public void setEventCapacity(String eventCapacity) {
        ((TextView) findViewById(R.id.textEventDetailsCapacity)).setText(eventCapacity);
    }

    /**
     * Sets the available tickets information in the corresponding view.
     *
     * @param availableTickets The available tickets information.
     */
    @Override
    public void setAvailableTickets(String availableTickets) {
        ((TextView) findViewById(R.id.textEventDetailsAvailableTickets)).setText(availableTickets);
    }

    /**
     * Handles the "Login or Register" action by displaying an alert dialog with login and register options.
     */
    @Override
    public void onLoginOrRegister() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cannot complete purchase");
        builder.setMessage("You need to first Log In or Register as a Customer to make a purchase.");

        // Set up the Log In button
        builder.setPositiveButton("Log In", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                Intent intent = new Intent(EventDetailsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Set up the Register button
        builder.setNegativeButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(EventDetailsActivity.this, RegisterEditCustomerActivity.class);
                startActivity(intent);
            }
        });
        builder.show();
    }

    /**
     * Opens the TicketBookingActivity for booking a paid ticket to the specified event.
     *
     * @param eventId The ID of the event for which the ticket is being booked.
     */
    @Override
    public void bookTicket(int eventId) {
        Intent intent = new Intent(EventDetailsActivity.this, TicketBookingActivity.class);
        intent.putExtra("event_id", eventId);
        intent.putExtra("customer_id", customerId);
        startActivity(intent);
    }

    /**
     * Displays an alert dialog for booking free tickets by inputting the number of tickets to be booked.
     */
    @Override
    public void bookFreeTicket(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Number of tickets");
        builder.setMessage("The event you selected is free attendance only, so just input the number of free tickets you would like to book.");

        // Set up the input field
        final EditText input = new EditText(this);
        // Specify the type of input expected
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        // Set up the OK button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewModel.getPresenter().onMakeFreePurchase(input.getText().toString());
            }
        });

        // Set up the Cancel button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    /**
     * Displays a success message after booking free tickets and navigates to the home page.
     */
    @Override
    public void showSuccessfulFreePurchase() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Successful Purchase");
        builder.setMessage("You have successfully booked your free tickets. Enjoy the event!");

        // Set up the OK button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                Intent intent = new Intent(EventDetailsActivity.this, HomePageActivity.class);
                intent.putExtra("customer_id", customerId);
                startActivity(intent);
            }
        });
        builder.show();
    }

    /**
     * Shows a message when there are no reviews available for the event.
     */
    @Override
    public void showEmptyReviews() {
        recyclerView.setVisibility(View.GONE);
        emptyReviews.setVisibility(View.VISIBLE);
    }

    /**
     * Displays the book ticket button.
     */
    @Override
    public void showBookTicketButton() {
        buttonBookTicket.setVisibility(View.VISIBLE);
    }

    /**
     * Hides the book ticket button.
     */
    @Override
    public void hideBookTicketButton() {
        buttonBookTicket.setVisibility(View.GONE);
    }

    /**
     * Shows the list of reviews in a RecyclerView.
     */
    @Override
    public void showReviews() {
        emptyReviews.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        // Set up the RecyclerView with the adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new EventDetailsRecyclerViewAdapter(viewModel.getPresenter().getReviewList(),this));
    }

    /**
     * Handles the selection of a review in the RecyclerView.
     *
     * @param review The selected review.
     */
    @Override
    public void selectReview(Review review) {
    }
}