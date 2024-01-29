package com.example.eventhub.domain;

import com.example.eventhub.util.Util;

import java.io.Serializable;

/**
 * Represents a user's interest in a particular genre.
 */
public class Interest implements Serializable {
    /** The name of the interest genre. */
    private Genre interestName;

    /**
     * Parameterized constructor that initializes the Interest.
     *
     * @param interestName The name of the interest genre.
     */
    public Interest(Genre interestName) {
        this.interestName = interestName;
    }

    /**
     * Sets the name of the interest genre.
     *
     * @param interestName The name of the interest genre.
     */
    public void setInterestName(Genre interestName) {
        Util.validateNotNull(interestName, "Interest name cannot be null");
        this.interestName = interestName;
    }

    /**
     * Gets the name of the interest genre.
     *
     * @return The name of the interest genre.
     */
    public Genre getInterestName() {
        return interestName;
    }

    /**
     * Checks if two Interest instances are equal.
     *
     * @param o The other object to compare.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interest)) return false;
        Interest interest = (Interest) o;
        return interestName == interest.interestName;
    }

    /**
     * Generates the hash code for the Interest instance.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return interestName.hashCode();
    }
}
