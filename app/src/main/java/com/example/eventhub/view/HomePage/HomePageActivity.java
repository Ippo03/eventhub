package com.example.eventhub.view.HomePage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventhub.R;
import com.example.eventhub.dao.Initializer;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.EventType;
import com.example.eventhub.domain.Genre;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.util.Util;
import com.example.eventhub.view.Account.Login.LoginActivity;
import com.example.eventhub.view.Account.Register.ChooseRegistration.ChooseRegistrationActivity;
import com.example.eventhub.view.Account.Register.RegisterEditCustomer.RegisterEditCustomerActivity;
import com.example.eventhub.view.Customer.CustomerPurchases.CustomerPurchasesActivity;
import com.example.eventhub.view.Event.EventDetails.EventDetailsActivity;
import com.example.eventhub.view.Util.CustomSpinnerAdapter;
import com.example.eventhub.view.Util.MultiSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * The main activity for the home page, responsible for displaying events, handling user input,
 * and managing the user interface.
 */
public class HomePageActivity extends AppCompatActivity implements HomePageView,
        HomePageRecyclerViewAdapter.ItemSelectionListener {

    private Spinner spinnerSorting;
    RecyclerView recyclerView;
    HomePageViewModel viewModel;
    Integer customerId;
    Button buttonLogin, buttonRegister, buttonCustomerEditAccount, buttonCustomerPurchases, buttonCustomerLogout;
    TextView emptyEvents;

    private static boolean initialized = false;
    private ActivityResultLauncher<Intent> startActivityChooseRegistration;

    /**
     * Initializes the activity, sets up the UI components, and retrieves necessary data.
     * This method is called when the activity is first created.
     *
     * @param savedInstanceState A Bundle containing the activity's previously saved state, if any.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Check if the data has been initialized to avoid redundant initialization
        if (!initialized) {
            Initializer initializer = new MemoryInitializer();
            initializer.prepareData();
            initialized = true;
        }

        // Retrieve customer ID from the intent
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            customerId = intent.getIntExtra("customer_id", 0);
        }

        // Register for activity result to handle ChooseRegistrationActivity result
        startActivityChooseRegistration = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Finish this Activity when the ChooseRegistrationActivity sends back a result
                        finish();
                    }
                });

        // Handle back button press for signed-in customers
        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                if (customerId != 0) {
                    // Customer is signed in, do nothing
                } else {
                    // Customer is not signed in, allow default behavior
                    setEnabled(false); // disable this callback
                }
            }
        });

        // Initialize UI components
        initializeUI();

        // Set up the Sorting Spinner and MultiSpinner
        setUpSpinners();

        // Set up the RecyclerView and initial event list
        setUpRecyclerView();

        // Set up click listeners for buttons
        setButtonClickListeners();
    }

    /**
     * Initializes UI components such as buttons, TextViews, and the ViewModel.
     */
    private void initializeUI() {
        recyclerView = findViewById(R.id.eventFeed);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonCustomerEditAccount = findViewById(R.id.buttonCustomerEditAccount);
        buttonCustomerPurchases = findViewById(R.id.buttonCustomerPurchases);
        buttonCustomerLogout = findViewById(R.id.buttonCustomerLogout);
        emptyEvents = findViewById(R.id.emptySearchResults);

        viewModel = new ViewModelProvider(this).get(HomePageViewModel.class);
        viewModel.getPresenter().setView(this);

        viewModel.getPresenter().setCustomer(customerId);
        viewModel.getPresenter().onDisplayButtons();
    }

    /**
     * Sets up the Sorting Spinner and the MultiSpinner for genres.
     */
    private void setUpSpinners() {
        ((MultiSpinner)findViewById(R.id.multiSpinnerGenres)).setItems(Arrays.asList(getResources().getStringArray(R.array.genres)), "All Genres", new MultiSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected) {
                // Handle selected genres
            }
        });

        spinnerSorting = findViewById(R.id.spinnerSorting);
        CustomSpinnerAdapter adapterSorting = new CustomSpinnerAdapter(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.sorting));

        adapterSorting.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSorting.setAdapter(adapterSorting);
        spinnerSorting.setSelection(0, true);

        // Image Button for sorting Spinner
        ImageButton btnOpenDialog = findViewById(R.id.buttonOpenSortingDialog);
        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSortingDialog();
            }
        });
    }

    /**
     * Sets up the RecyclerView with an initial list of events.
     */
    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.eventFeed);
        viewModel.getPresenter().onSetInitEventList();
        viewModel.getPresenter().onShowEvents();
    }

    /**
     * Sets click listeners for various buttons in the activity.
     */
    private void setButtonClickListeners() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viewModel.getPresenter().onLogin();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viewModel.getPresenter().onRegister();
            }
        });

        findViewById(R.id.buttonCustomerEditAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onEditAccount();
            }
        });

        findViewById(R.id.buttonCustomerPurchases).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onPurchases();
            }
        });

        findViewById(R.id.buttonCustomerLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onLogout();
            }
        });

        findViewById(R.id.buttonSearchEvent).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {
                viewModel.getPresenter().onSetSearchResultList(search());
            }
        });

        findViewById(R.id.buttonResetFilters).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onResetAllFilters();
            }
        });
    }

    /**
     * Retrieves the current filter settings from the UI components.
     *
     * @return A HashMap containing filter settings such as event name, genres, types, date range, and sorting method.
     */
    @SuppressLint("NewApi")
    public HashMap<String, Object> getFilters() {
        HashMap<String, Object> filters = new HashMap<>();

        filters.put("title", getEventName());
        filters.put("genre", getEventGenres());
        filters.put("type", getEventTypes());
        filters.put("date", getEventDateRange());
        filters.put("sorting", getEventSorting());

        return filters;
    }

    /**
     * Displays buttons for a signed-in customer and hides login and register buttons.
     * Buttons shown: Edit Account, Purchases, Logout.
     */
    @Override
    public void showHomePageButtons() {
        buttonLogin.setVisibility(View.VISIBLE);
        buttonRegister.setVisibility(View.VISIBLE);
        buttonCustomerEditAccount.setVisibility(View.GONE);
        buttonCustomerPurchases.setVisibility(View.GONE);
        buttonCustomerLogout.setVisibility(View.GONE);
    }

    /**
     * Displays buttons for a signed-in customer and hides login and register buttons.
     * Buttons shown: Edit Account, Purchases, Logout.
     */
    @Override
    public void showCustomerHomePageButtons() {
        buttonLogin.setVisibility(View.GONE);
        buttonRegister.setVisibility(View.GONE);
        buttonCustomerEditAccount.setVisibility(View.VISIBLE);
        buttonCustomerPurchases.setVisibility(View.VISIBLE);
        buttonCustomerLogout.setVisibility(View.VISIBLE);
    }

    /**
     * Retrieves the event name from the corresponding UI component.
     *
     * @return The event name entered by the user.
     */
    @Override
    public String getEventName() {
        return ((EditText) findViewById(R.id.textInputEventName)).getText().toString().trim();
    }

    /**
     * Retrieves the selected event genres from the MultiSpinner UI component.
     *
     * @return A list of selected event genres.
     */
    @Override
    public ArrayList<Genre> getEventGenres() {
        String genres =  ((MultiSpinner) findViewById(R.id.multiSpinnerGenres)).getSelectedItems();
        ArrayList<String> genresList = new ArrayList<>(Arrays.asList(genres.split(",")));
        return Util.mapStringsToGenres(genresList);
    }

    /**
     * Retrieves the selected event types from the CheckBox UI components.
     *
     * @return A list of selected event types.
     */
    @Override
    public ArrayList<EventType> getEventTypes() {
        ArrayList<String> typesList = new ArrayList<>();
        if (((CheckBox) findViewById(R.id.checkBoxOpen)).isChecked()) typesList.add("open");
        if (((CheckBox) findViewById(R.id.checkBoxClosed)).isChecked()) typesList.add("closed");
        if (((CheckBox) findViewById(R.id.checkBoxFree)).isChecked()) typesList.add("free");

        if (typesList.isEmpty()) {
            typesList.add("open");
            typesList.add("closed");
            typesList.add("free");
        }

        return Util.mapStringsToEventTypes(typesList);
    }

    /**
     * Retrieves the entered event date range from the corresponding UI components.
     *
     * @return A list containing the start and end dates of the event range.
     */
    @Override
    public ArrayList<String> getEventDateRange() {
        return new ArrayList<>(Arrays.asList(
                ((EditText) findViewById(R.id.textInputEventFromDate)).getText().toString().trim(),
                ((EditText) findViewById(R.id.textInputEventToDate)).getText().toString().trim()
        ));
    }

    /**
     * Retrieves the selected event sorting method from the Spinner UI component.
     *
     * @return The selected event sorting method.
     */
    @Override
    public String getEventSorting() {
        return ((Spinner) findViewById(R.id.spinnerSorting)).getSelectedItem().toString().trim();
    }

    /**
     * Sets the event name UI component to its default state (empty).
     */
    @Override
    public void setDefaultEventName() {
        ((EditText) findViewById(R.id.textInputEventName)).setText("");
    }

    /**
     * Clears the selection of event genres in the MultiSpinner UI component.
     */
    @Override
    public void setDefaultEventGenres() {
        ((MultiSpinner) findViewById(R.id.multiSpinnerGenres)).clearSelection();
    }

    /**
     * Sets the event types UI components to their default state (unchecked).
     */
    @Override
    public void setDefaultEventTypes() {
        ((CheckBox) findViewById(R.id.checkBoxOpen)).setChecked(false);
        ((CheckBox) findViewById(R.id.checkBoxClosed)).setChecked(false);
        ((CheckBox) findViewById(R.id.checkBoxFree)).setChecked(false);
    }

    /**
     * Sets the event from date UI component to its default state (empty).
     */
    @Override
    public void setDefaultEventFromDate() {
        ((EditText) findViewById(R.id.textInputEventFromDate)).setText("");
    }

    /**
     * Sets the event to date UI component to its default state (empty).
     */
    @Override
    public void setDefaultEventToDate() {
        ((EditText) findViewById(R.id.textInputEventToDate)).setText("");
    }

    /**
     * Sets the event sorting Spinner UI component to its default state (initial selection).
     */
    @Override
    public void setDefaultEventSorting() {
        ((Spinner) findViewById(R.id.spinnerSorting)).setSelection(0);
    }

    /**
     * Resets all filters to their default states and notifies the user with a toast message.
     */
    @Override
    public void resetFilters() {
        setDefaultEventName();
        setDefaultEventGenres();
        setDefaultEventTypes();
        setDefaultEventFromDate();
        setDefaultEventToDate();
        setDefaultEventSorting();
        Toast.makeText(this, "Filters have been reset.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Redirects the user to the login page.
     */
    @Override
    public void login() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Redirects the user to the registration page using the ChooseRegistrationActivity.
     */
    @Override
    public void register() {
        Intent intent = new Intent(this, ChooseRegistrationActivity.class);
        startActivityChooseRegistration.launch(intent);
    }

    /**
     * Redirects the user to the account editing page.
     */
    @Override
    public void editAccount() {
        Intent intent = new Intent(this, RegisterEditCustomerActivity.class);
        intent.putExtra("customer_id", customerId);
        startActivity(intent);
    }

    /**
     * Redirects the user to the customer purchases page.
     */
    @Override
    public void purchases() {
        Intent intent = new Intent(this, CustomerPurchasesActivity.class);
        intent.putExtra("customer_id", customerId);
        startActivity(intent);
    }

    /**
     * Logs the user out, redirects to the home page, and ensures the user cannot sign in again via back button press.
     */
    @Override
    public void logout() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
        finish(); // After log out, the user should not be able to press back and be signed in again
    }

    /**
     * Executes a search based on the current filters and displays the search results.
     *
     * @return A list of events matching the search criteria.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ArrayList<Event> search() {
        Toast.makeText(this, "Showing events based on your search filters.", Toast.LENGTH_SHORT).show();
        return viewModel.getPresenter().onSearch();
    }

    /**
     * Selects a specific event and redirects the user to the event details page.
     *
     * @param event The selected event.
     */
    public void selectEvent(Event event) {
        Intent intent = new Intent(this, EventDetailsActivity.class);
        intent.putExtra("event_id", event.getEventId());
        intent.putExtra("customer_id", customerId); // to be able to book a ticket
        startActivity(intent);
    }

    /**
     * Displays the list of events in the RecyclerView.
     */
    @Override
    public void showEvents() {
        emptyEvents.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new HomePageRecyclerViewAdapter(viewModel.getPresenter().getEventList(), this, viewModel.getPresenter().getCustomer()));
    }

    /**
     * Displays a message indicating that no events are available.
     */
    @Override
    public void showEmptyEvents() {
        recyclerView.setVisibility(View.GONE);
        emptyEvents.setVisibility(View.VISIBLE);
    }

    /**
     * Displays an error message in an AlertDialog.
     *
     * @param title   The title of the error dialog.
     * @param message The error message to be displayed.
     */
    @Override
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(HomePageActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    /**
     * Displays a sorting dialog allowing the user to choose from sorting options.
     * The selected sorting option is reflected in the Sorting Spinner on confirmation.
     */
    private void showSortingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomePageActivity.this);
        builder.setTitle("Select Sorting"); // Set the title for the dialog

        // Use the same array you used for the spinner
        String[] sortingOptions = getResources().getStringArray(R.array.sorting);
        final int[] currentSelection = {spinnerSorting.getSelectedItemPosition()}; // Get the checked item index

        // Set up the dialog with the sorting options
        builder.setSingleChoiceItems(sortingOptions, currentSelection[0], (dialog, which) -> { // 'which' is the index of the selected item
            // Handle the selection and dismiss the dialog
            currentSelection[0] = which;
        });

        // Add positive and negative buttons
        builder.setPositiveButton("Confirm", (dialog, which) -> {
            spinnerSorting.setSelection(currentSelection[0], true); // Set the spinner to the selected item
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            // Do nothing, dialog will be dismissed without changing the selection.
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}