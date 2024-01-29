package com.example.eventhub.view.OrganizerHomePage;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventhub.R;
import com.example.eventhub.domain.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrganizerHomePageRecyclerViewAdapter extends RecyclerView.Adapter<OrganizerHomePageRecyclerViewAdapter.ViewHolder> {
    private final List<Event> organizedEvents;
    private final OrganizerHomePageRecyclerViewAdapter.ItemSelectionListener listener;

    public OrganizerHomePageRecyclerViewAdapter(List<Event> organizedEvents, OrganizerHomePageRecyclerViewAdapter.ItemSelectionListener listener) {
        this.organizedEvents = organizedEvents;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrganizerHomePageRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrganizerHomePageRecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.organized_event_list_item, parent, false));
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull OrganizerHomePageRecyclerViewAdapter.ViewHolder holder, int position) {
        final Event currentEvent = organizedEvents.get(position);
        holder.txtEventName.setText(currentEvent.getName());
        holder.txtEventDate.setText(currentEvent.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " " + currentEvent.getDate().toLocalTime().toString());
        holder.txtEventLocation.setText(currentEvent.getLocation().toString());
        if (currentEvent.getDate().isBefore(LocalDateTime.now())) {
            holder.btnEditEvent.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.selectEvent(currentEvent);
            }
        });

        holder.btnEditEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.editEvent(currentEvent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return organizedEvents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtEventName;
        public final TextView txtEventDate;
        public final TextView txtEventLocation;
        private final Button btnEditEvent;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtEventName = view.findViewById(R.id.textEventName);
            txtEventDate = view.findViewById(R.id.textEventDate);
            txtEventLocation = view.findViewById(R.id.textEventLocation);
            btnEditEvent = view.findViewById(R.id.buttonEditOrganizedEvent);
        }
    }

    public interface ItemSelectionListener {
        void selectEvent(Event event);

        void editEvent(Event event);
    }
}
