package com.example.eventhub.view.HomePage;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventhub.R;
import com.example.eventhub.domain.Customer;
import com.example.eventhub.domain.Event;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * RecyclerView Adapter for displaying events on the Home Page.
 */
public class HomePageRecyclerViewAdapter extends RecyclerView.Adapter<HomePageRecyclerViewAdapter.ViewHolder> {

    private final List<Event> eventList;
    private final ItemSelectionListener listener;
    private final Customer customer;

    /**
     * Constructor for the adapter.
     *
     * @param eventList List of events to be displayed.
     * @param listener  Item selection listener for handling event selection.
     * @param customer  The customer associated with the adapter.
     */
    public HomePageRecyclerViewAdapter(List<Event> eventList, ItemSelectionListener listener, Customer customer) {
        this.eventList = eventList;
        this.listener = listener;
        this.customer = customer;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_item, parent, false));
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Event currentEvent = eventList.get(position);
        holder.txtEventName.setText(currentEvent.getName());
        holder.txtEventDate.setText(currentEvent.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " " + currentEvent.getDate().toLocalTime().toString());
        holder.txtEventLocation.setText(currentEvent.getLocation().toString());
        holder.txtMatchingEvent.setVisibility(currentEvent.isMatchingWithCustomer(customer) ? View.VISIBLE : View.INVISIBLE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.selectEvent(currentEvent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    /**
     * ViewHolder class for holding views of each event item in the RecyclerView.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtEventName;
        public final TextView txtEventDate;
        public final TextView txtEventLocation;
        public final TextView txtMatchingEvent;

        /**
         * Constructor for the ViewHolder.
         *
         * @param itemView The view for the item.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtEventName = itemView.findViewById(R.id.textEventName);
            txtEventDate = itemView.findViewById(R.id.textEventDate);
            txtEventLocation = itemView.findViewById(R.id.textEventLocation);
            txtMatchingEvent = itemView.findViewById(R.id.textMatchingEvent);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.selectEvent(eventList.get(getAdapterPosition()));
                }
            });
        }
    }

    /**
     * Interface for handling item selection events in the RecyclerView.
     */
    public interface ItemSelectionListener {
        /**
         * Called when an event item is selected.
         *
         * @param event The selected event.
         */
        void selectEvent(Event event);
    }
}

