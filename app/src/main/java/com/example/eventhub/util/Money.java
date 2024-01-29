package com.example.eventhub.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * Represents a monetary amount with associated currency.
 */
public class Money implements Serializable {
    private static final long serialVersionUID = 2L;
    /** The amount of money. */
    private BigDecimal amount;

    /** The currency of the money. */
    private Currency currency;

    /**
     * Parameterized constructor that initializes the money.
     *
     * @param amount   The amount of the money.
     * @param currency The currency of the money.
     */
    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount == null ? amount : amount.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.currency = currency;
    }

    /**
     * Returns the amount of the money.
     *
     * @return The amount of the money.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the money.
     *
     * @param amount The amount of the money.
     */
    public void setAmount(BigDecimal amount) {
        if (amount != null) {
            this.amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }

    /**
     * Returns the currency of the money.
     *
     * @return The currency of the money.
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Sets the currency of the money.
     *
     * @param currency The currency of the money.
     */
    public void setCurrency(Currency currency) {
        if (currency != null) {
            this.currency = currency;
        }
    }

    /**
     * Creates a Money instance with the specified amount in euros.
     *
     * @param amount The amount in euros.
     * @return A Money instance with the specified amount and currency (EUR).
     */
    public static Money euros(BigDecimal amount) {
        return new Money(amount, Currency.getInstance("EUR"));
    }

    /**
     * Adds another Money instance to this Money instance.
     *
     * @param other The other Money instance to be added.
     * @return A new Money instance representing the sum.
     * @throws IllegalArgumentException if currencies are different.
     */
    public Money plus(Money other) {
        this.checkForSameCurrencies(other);
        return new Money((this.amount.add(other.getAmount())), this.getCurrency());
    }

    /**
     * Subtracts another Money instance from this Money instance.
     *
     * @param other The other Money instance to be subtracted.
     * @return A new Money instance representing the difference.
     * @throws IllegalArgumentException if currencies are different.
     */
    public Money minus(Money other) {
        this.checkForSameCurrencies(other);
        return new Money((this.amount.subtract(other.getAmount())), this.getCurrency());
    }

    /**
     * Multiplies the Money instance by a specified factor.
     *
     * @param factor The factor to multiply the amount by.
     * @return A new Money instance representing the result of the multiplication.
     */
    public Money times(BigDecimal factor) {
        return new Money(this.getAmount().multiply(factor), this.getCurrency());
    }

    /**
     * Multiplies the Money instance by a specified factor.
     *
     * @param factor The factor to multiply the amount by.
     * @return A new Money instance representing the result of the multiplication.
     */
    public Money times(double factor) {
        return new Money(this.getAmount().multiply(BigDecimal.valueOf(factor)), this.getCurrency());
    }

    /**
     * Checks if two Money instances are equal.
     *
     * @param other The other object to compare.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (!(other instanceof Money)) {
            return false;
        }

        Money theMoney = (Money) other;
        if (currency == null) {
            return theMoney.currency == null
                    && amount == null && theMoney.amount == null;
        }

        if (!currency.equals(theMoney.currency)) {
            return false;
        }

        return amount == null ? theMoney.amount == null
                : (amount.compareTo(theMoney.amount) == 0);
    }

    /**
     * Generates the hash code for the Money instance.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return amount == null ? 0 : amount.hashCode();
    }

    /**
     * Returns a string representation of the Money instance.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return amount + " " + currency;
    }

    /**
     * Checks if two Money instances have the same currency.
     *
     * @param other The other Money instance to compare currencies with.
     * @throws IllegalArgumentException if currencies are different.
     */
    private void checkForSameCurrencies(Money other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("Different currencies");
        }
    }
}
