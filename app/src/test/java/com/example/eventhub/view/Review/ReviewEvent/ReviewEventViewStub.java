package com.example.eventhub.view.Review.ReviewEvent;

import com.example.eventhub.view.ReviewEvent.ReviewEventView;

public class ReviewEventViewStub implements ReviewEventView {
    private String grade, comment, eventName;
    private String errorTitle, errorMessage, successMessage;
    public Integer errorCount;

    public ReviewEventViewStub() {
        grade = comment = eventName = errorTitle = errorMessage = "";
        errorCount = 0;
    }

    @Override
    public void showEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    @Override
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void showReviewSavedMessage() {
        successMessage = "Your review has been saved successfully.";
    }

    @Override
    public void showErrorMessage(String title, String message) {
        errorTitle = title;
        errorMessage = message;
        errorCount++;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public String getSuccessMessage() {
        return successMessage;
    }
}
