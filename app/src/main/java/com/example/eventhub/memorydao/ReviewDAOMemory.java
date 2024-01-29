package com.example.eventhub.memorydao;

import com.example.eventhub.dao.ReviewDAO;
import com.example.eventhub.domain.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewDAOMemory implements ReviewDAO {
    protected static List<Review> reviews = new ArrayList<Review>();

    @Override
    public void delete(Review review) {
        reviews.remove(review);
    }

    @Override
    public List<Review> findAll() {
        ArrayList result = new ArrayList<>();
        result.addAll(reviews);
        return result;
    }

    @Override
    public void save(Review review) {
        reviews.add(review);
    }

    @Override
    public Review find(int id) {
        for(Review review : reviews) {
            if (review.getReviewId() == id) {
                return review;
            }
        }
        return null;
    }

    @Override
    public int nextId() {
        return reviews.size() == 0 ? 1 : reviews.get(reviews.size() - 1).getReviewId() + 1;
    }
}
