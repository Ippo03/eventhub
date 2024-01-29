package com.example.eventhub.view.Event.EventDetails;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventhub.R;
import com.example.eventhub.domain.Review;

import java.util.List;

public class EventDetailsRecyclerViewAdapter extends RecyclerView.Adapter<EventDetailsRecyclerViewAdapter.ViewHolder> {
    private final List<Review> reviewList;
    private final ItemSelectionListener listener;

    public EventDetailsRecyclerViewAdapter(List<Review> reviewList, ItemSelectionListener listener) {
        this.reviewList = reviewList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EventDetailsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventDetailsRecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventDetailsRecyclerViewAdapter.ViewHolder holder, int position) {
        final Review currentReview = reviewList.get(position);
        Log.d("Review", currentReview.toString());
        Log.d("Customer of the Review", currentReview.getCustomer().getFirstName() + " " + currentReview.getCustomer().getLastName());
        holder.txtReviewCounter.setText("1");
        holder.txtReviewer.setText(currentReview.getCustomer().getFirstName() + " " + currentReview.getCustomer().getLastName());
        holder.txtComment.setText(currentReview.getComment());
        holder.txtGrade.setText(String.valueOf(currentReview.getGrade()));
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtReviewCounter;
        private final TextView txtReviewer;
        private final TextView txtComment;
        private final TextView txtGrade;
        public ViewHolder(@NonNull View parent) {
            super(parent);
            txtReviewCounter = parent.findViewById(R.id.txtReviewCount);
            txtReviewer = parent.findViewById(R.id.txtReviewer);
            txtComment = parent.findViewById(R.id.txtReviewComment);
            txtGrade = parent.findViewById(R.id.txtReviewGrade);
        }
    }

    public interface ItemSelectionListener {
        void selectReview(Review review);
    }
}
