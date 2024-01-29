package com.example.eventhub.view.Event.TicketCategorySelection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventhub.R;
import com.example.eventhub.domain.TicketCategory;

import java.util.List;

public class TicketCategorySelectionRecyclerViewAdapter extends RecyclerView.Adapter<TicketCategorySelectionRecyclerViewAdapter.ViewHolder>{
    private final List<TicketCategory> ticketCategories;
    private final TicketCategorySelectionRecyclerViewAdapter.ItemSelectionListener listener;

    public TicketCategorySelectionRecyclerViewAdapter(List<TicketCategory> ticketCategories, TicketCategorySelectionRecyclerViewAdapter.ItemSelectionListener listener) {
        this.ticketCategories = ticketCategories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TicketCategorySelectionRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TicketCategorySelectionRecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ticket_category_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TicketCategorySelectionRecyclerViewAdapter.ViewHolder holder, int position) {
        final TicketCategory currentTicketCategory = ticketCategories.get(position);

        holder.txtTicketCategoryName.setText(currentTicketCategory.getName().toString());
        holder.txtTicketCategoryPrice.setText(currentTicketCategory.getPrice().toString());
        holder.txtTicketCategoryDescription.setText(currentTicketCategory.getDescription());
        holder.txtTicketCategoryQuantity.setText(String.valueOf(currentTicketCategory.getQuantity()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.selectTicketCategory(currentTicketCategory);
            }
        });

        holder.btnRemoveTicketCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.removeTicketCategory(currentTicketCategory);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ticketCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtTicketCategoryName;
        private final TextView txtTicketCategoryPrice;
        private final TextView txtTicketCategoryDescription;
        private final TextView txtTicketCategoryQuantity;
        private final ImageButton btnRemoveTicketCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTicketCategoryName = itemView.findViewById(R.id.txtCategoryName);
            txtTicketCategoryPrice = itemView.findViewById(R.id.txtCategoryPrice);
            txtTicketCategoryDescription = itemView.findViewById(R.id.txtCategoryDescription);
            txtTicketCategoryQuantity = itemView.findViewById(R.id.txtCategoryQuantity);
            btnRemoveTicketCategory = itemView.findViewById(R.id.btnRemoveCategory);
        }
    }

    public interface ItemSelectionListener {
        void selectTicketCategory(TicketCategory ticketCategory);

        void removeTicketCategory(TicketCategory ticketCategory);
    }
}
