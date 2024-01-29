package com.example.eventhub.view.ReviewEvent;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.eventhub.R;
import com.example.eventhub.view.HomePage.HomePageActivity;

/**
 * Activity class responsible for allowing customers to review an event.
 * Implements the {@link ReviewEventView} interface to interact with the presenter.
 */
public class ReviewEventActivity extends AppCompatActivity implements ReviewEventView {
    private Integer eventId;
    private Integer customerId;
    private ReviewEventViewModel viewModel;

    /**
     * Called when the activity is first created. Responsible for initializing the UI and presenter.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this Bundle contains the data it most recently supplied in {@link #onSaveInstanceState}.
     *                           Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_event);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            eventId = intent.getIntExtra("event_id", -1);
            customerId = intent.getIntExtra("customer_id", -1);
        }

        viewModel = new ViewModelProvider(this).get(ReviewEventViewModel.class);
        viewModel.getPresenter().setView(this);

        viewModel.getPresenter().setCustomer(customerId);
        viewModel.getPresenter().setEvent(eventId);

        viewModel.getPresenter().onShowEventName();

        findViewById(R.id.buttonCompleteReview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onReviewEvent();
            }
        });
    }

    /**
     * Displays the name of the event on the UI.
     *
     * @param eventName The name of the event to be displayed.
     */
    @Override
    public void showEventName(String eventName) {
        ((TextView) findViewById(R.id.textReviewEventName)).setText(eventName);
    }

    /**
     * Retrieves the entered grade from the UI.
     *
     * @return The entered grade as a string.
     */
    @Override
    public String getGrade() {
        return ((EditText) findViewById(R.id.textInputEventReviewGrade)).getText().toString().trim();
    }

    /**
     * Retrieves the entered comment from the UI.
     *
     * @return The entered comment as a string.
     */
    @Override
    public String getComment() {
        return ((EditText) findViewById(R.id.textInputEventReviewComment)).getText().toString().trim();
    }

    /**
     * Displays a success message after the review is saved and redirects to the home page.
     */
    @Override
    public void showReviewSavedMessage() {
        new AlertDialog.Builder(ReviewEventActivity.this)
                .setCancelable(true)
                .setTitle("Review Saved")
                .setMessage("Your review has been saved successfully.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(ReviewEventActivity.this, HomePageActivity.class);
                        intent.putExtra("customer_id", customerId);
                        startActivity(intent);
                    }
                }).create().show();
    }

    /**
     * Displays an error message in case of validation or other errors.
     *
     * @param title   The title of the error message.
     * @param message The content of the error message.
     */
    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(ReviewEventActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }
}
