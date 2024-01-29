package com.example.eventhub.view.Customer.CustomerPurchases;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventhub.R;
import com.example.eventhub.domain.Purchase;

import java.util.List;

/**
 * RecyclerViewAdapter for displaying a list of customer purchases.
 * Provides a callback listener for item selection and event review actions.
 */
public class CustomerPurchasesRecyclerViewAdapter extends RecyclerView.Adapter<CustomerPurchasesRecyclerViewAdapter.ViewHolder> {
    private final List<Purchase> purchases;
    private final ItemSelectionListener listener;

    /**
     * Constructor to initialize the adapter with a list of purchases and an item selection listener.
     *
     * @param purchases The list of {@link Purchase} objects to be displayed.
     * @param listener  The {@link ItemSelectionListener} for handling item selection events.
     */
    public CustomerPurchasesRecyclerViewAdapter(List<Purchase> purchases, ItemSelectionListener listener) {
        this.purchases = purchases;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.purchase_list_item, parent, false));
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Purchase purchase = purchases.get(position);
        holder.txtPurchaseName.setText(purchase.getEvent().getName());
        int tickets = purchase.getTickets().size();
        String countString = tickets > 1 ? " tickets" : " ticket";
        holder.txtPurchaseNumberOfTickets.setText(tickets + countString);
        holder.txtPurchaseDate.setText(purchase.getEvent().getDate().toLocalDate().toString() + " " + purchase.getEvent().getDate().toLocalTime().toString());
        holder.txtPurchaseTotalCost.setText(purchase.getCost().toString());
        boolean isActive = purchase.getEvent().getDate().isAfter(java.time.LocalDateTime.now());
        holder.txtPurchaseStatus.setText(isActive ? "Active" : "Inactive");
        if (isActive) {
            holder.btnReviewEvent.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.selectPurchase(purchase);
            }
        });

        holder.btnReviewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.reviewEvent(purchase.getEvent().getEventId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return purchases.size();
    }

    /**
     * ViewHolder class for holding views of each purchase item in the RecyclerView.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtPurchaseName;
        public final TextView txtPurchaseDate;
        public final TextView txtPurchaseTotalCost;
        public final TextView txtPurchaseNumberOfTickets;
        public final TextView txtPurchaseStatus;
        public final Button btnReviewEvent;

        /**
         * Constructor to initialize the ViewHolder with the associated views.
         *
         * @param itemView The root view of the purchase item.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPurchaseName = itemView.findViewById(R.id.textEventNameOfPurchase);
            txtPurchaseDate = itemView.findViewById(R.id.textDateOfPurchase);
            txtPurchaseTotalCost = itemView.findViewById(R.id.textTotalCostOfPurchase);
            txtPurchaseNumberOfTickets = itemView.findViewById(R.id.textNumberOfTicketsInPurchase);
            txtPurchaseStatus = itemView.findViewById(R.id.textStateOfPurchase);
            btnReviewEvent = itemView.findViewById(R.id.buttonReviewEventFromPurchase);
        }
    }

    /**
     * Interface for handling item selection and event review actions.
     */
    public interface ItemSelectionListener {
        /**
         * Called when a purchase item is selected.
         *
         * @param purchase The selected {@link Purchase} object.
         */
        void selectPurchase(Purchase purchase);

        /**
         * Called when the "Review Event" button is clicked for a purchase.
         *
         * @param eventId The ID of the event to be reviewed.
         */
        void reviewEvent(Integer eventId);
    }
}

