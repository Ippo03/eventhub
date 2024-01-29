package com.example.eventhub.view.TicketBooking;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventhub.R;
import com.example.eventhub.domain.Ticket;
import com.example.eventhub.domain.TicketKey;

import java.util.ArrayList;
import java.util.HashMap;

public class TicketBookingRecyclerViewAdapter extends RecyclerView.Adapter<TicketBookingRecyclerViewAdapter.ViewHolder> {
    private boolean isRemoveButtonVisible;
    private ArrayList<Ticket> ticketList;
    private HashMap<TicketKey, Integer> ticketCountMap;
    private final OnItemClickListener listener;

    public TicketBookingRecyclerViewAdapter(ArrayList<Ticket> ticketList, HashMap<TicketKey, Integer> ticketCountMap, OnItemClickListener listener, boolean isRemoveButtonVisible) {
        this.ticketList = ticketList;
        this.ticketCountMap = ticketCountMap;
        this.listener = listener;
        this.isRemoveButtonVisible = isRemoveButtonVisible;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ticket_list_item, parent, false));
    }

    public void updateTicketCountMap(HashMap<TicketKey, Integer> newMap) {
        this.ticketCountMap = newMap;
        Log.d("NEW TICKET COUNT MAP ", "IN RECYCLER : " + ticketCountMap.size());
        Log.d("ALL TICKETS COUNT ", "IN RECYCLER : " + ticketCountMap.toString());
    }

    public void updateTicketList(ArrayList<Ticket> newTicketList) {
        this.ticketList = newTicketList;
        Log.d("NEW TICKET LIST", "IN RECYCLER : " + ticketList.size());
        Log.d("ALL TICKETS ", "IN RECYCLER : " + ticketList.toString());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket currentTicket = ticketList.get(position);
        TicketKey key = new TicketKey(currentTicket.getTicketDiscount(), currentTicket.getTicketCategory(), currentTicket.getTicketPrice());
        int quantity = ticketCountMap.get(key);
        Log.d("QUANTITY SHOWN", "onBindViewHolder: " + quantity);
        holder.txtTicketQuantity.setText(String.valueOf(quantity));
        holder.txtTicketCategory.setText(currentTicket.getTicketCategory().getName().toString());
        holder.txtTicketDiscount.setText(currentTicket.getTicketDiscount().getType().toString());
        holder.txtTicketPrice.setText(currentTicket.getTicketPrice().toString());
        holder.btnRemoveTicket.setVisibility(isRemoveButtonVisible ? View.VISIBLE : View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.selectTicket(currentTicket);
            }
        });


        holder.btnRemoveTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.removeTicket(currentTicket);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtTicketQuantity;
        public final TextView txtTicketCategory;
        public final TextView txtTicketDiscount;
        public final TextView txtTicketPrice;
        public final ImageButton btnRemoveTicket;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTicketQuantity = itemView.findViewById(R.id.textTicketQuantity);
            txtTicketCategory = itemView.findViewById(R.id.textTicketCategory);
            txtTicketDiscount = itemView.findViewById(R.id.textTicketDiscount);
            txtTicketPrice = itemView.findViewById(R.id.textTicketPrice);
            btnRemoveTicket = itemView.findViewById(R.id.btnRemoveCategory);
        }
    }

    public interface OnItemClickListener {
        void selectTicket(Ticket ticket);

        void removeTicket(Ticket ticket);
    }
}
