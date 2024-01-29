package com.example.eventhub.view.Event.TicketDiscountSelection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventhub.R;
import com.example.eventhub.domain.TicketDiscount;


import java.util.List;

public class TicketDiscountSelectionRecyclerViewAdapter extends RecyclerView.Adapter<TicketDiscountSelectionRecyclerViewAdapter.ViewHolder>{
    private final List<TicketDiscount> ticketDiscounts;
    private final TicketDiscountSelectionRecyclerViewAdapter.ItemSelectionListener listener;

    public TicketDiscountSelectionRecyclerViewAdapter(List<TicketDiscount> ticketDiscounts, TicketDiscountSelectionRecyclerViewAdapter.ItemSelectionListener listener) {
        this.ticketDiscounts = ticketDiscounts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TicketDiscountSelectionRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TicketDiscountSelectionRecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ticket_discount_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TicketDiscount currentTicketDiscount = ticketDiscounts.get(position);

        holder.txtTicketDiscountType.setText(currentTicketDiscount.getType().toString());
        holder.txtTicketDiscountPercentage.setText(String.valueOf(currentTicketDiscount.getPercentage() * 100) + "%");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.selectTicketDiscount(currentTicketDiscount);
            }
        });

        holder.btnRemoveTicketDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.removeTicketDiscount(currentTicketDiscount);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ticketDiscounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtTicketDiscountType;
        private final TextView txtTicketDiscountPercentage;
        private final ImageButton btnRemoveTicketDiscount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTicketDiscountType = itemView.findViewById(R.id.txtDiscountType);
            txtTicketDiscountPercentage = itemView.findViewById(R.id.txtDiscountPercentage);
            btnRemoveTicketDiscount = itemView.findViewById(R.id.btnRemoveDiscount);
        }
    }

    public interface ItemSelectionListener {
        void selectTicketDiscount(TicketDiscount ticketDiscount);

        void removeTicketDiscount(TicketDiscount ticketDiscount);
    }
}
