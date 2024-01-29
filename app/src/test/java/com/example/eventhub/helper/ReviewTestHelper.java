package com.example.eventhub.helper;

import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.Review;

public class ReviewTestHelper {
    public static Review initReview() {
        return new Review(4, 10, "Great event");
    }

    public static void addReviewsToEvents(Event event1, Event event2) {
        Review review1 = new Review(5, 10, "Perfect");
        Review review2 = new Review(6, 8, "Super");
        Review review3 = new Review(7, 6, "Good");
        Review review4 = new Review(8, 7, "Very Good");
        Review review5 = new Review(9, 3, "Bad");
        Review review6 = new Review(10, 9, "Excellent");

        event1.addReview(review1);
        event1.addReview(review2);
        event1.addReview(review3);
        event2.addReview(review4);
        event2.addReview(review5);
        event2.addReview(review6);
    }
    public static Review[] initReviews() {
        Review review1 = new Review(11,10, "Perfect");
        Review review2 = new Review(12, 8, "Super");
        Review review3 = new Review(13, 6, "Good");
        Review review4 = new Review(14, 7, "Very Good");
        Review review5 = new Review(15,3, "Bad");
        Review review6 = new Review(16, 9, "Excellent");

        return new Review[]{review1, review2, review3, review4, review5, review6};
    }
}