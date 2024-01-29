package com.example.eventhub.domain;

import com.example.eventhub.util.Util;

import java.io.Serializable;

/**
 * Represents a review with details such as grade, comment, and customer.
 */
public class Review implements Serializable {
    private Integer id;
    /** The grade of the review. */
    private Integer grade;
    /** The comment associated with the review. */
    private String comment;
    /** The customer who provided the review. */
    protected Customer customer;

    /**
     * Parameterized constructor that initializes the review.
     *
     * @param grade   The grade assigned to the review.
     * @param comment The comment associated with the review.
     */
    public Review(Integer id, Integer grade, String comment) {
        this.id = id;
        this.setGrade(grade);
        this.setComment(comment);
    }

    public Integer getReviewId() {
        return id;
    }

    /**
     * Sets the grade of the review.
     *
     * @param grade The grade of the review.
     */
    public void setGrade(Integer grade) {
        Util.validateNotNull(grade, "Grade");
        Util.validateIntegerInRange(grade, "Grade", 0, 10);
        this.grade = grade;
    }

    /**
     * Retrieves the grade of the review.
     *
     * @return The grade of the review.
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * Sets the comment associated with the review.
     *
     * @param comment The comment of the review.
     */
    public void setComment(String comment) {
        Util.validateNonNullAndNonEmpty(comment, "Comment");
        this.comment = comment;
    }

    /**
     * Retrieves the comment associated with the review.
     *
     * @return The comment of the review.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the customer who provided the review.
     *
     * @param customer The customer associated with the review.
     */
    public void setCustomer(Customer customer) {
        Util.validateNotNull(customer, "Customer");
        this.customer = customer;
    }

    /**
     * Retrieves the customer who provided the review.
     *
     * @return The customer associated with the review.
     */
    public Customer getCustomer() {
        return customer;
    }
}
