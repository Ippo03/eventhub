package com.example.eventhub.domain;

import com.example.eventhub.util.Money;

import java.util.Objects;

public class TicketKey {
    private final TicketDiscount discount;
    private final TicketCategory category;
    private final Money price;

    public TicketKey(TicketDiscount discount, TicketCategory category, Money price) {
        this.discount = discount;
        this.category = category;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketKey ticketKey = (TicketKey) o;
        return Objects.equals(discount, ticketKey.discount) &&
                Objects.equals(category, ticketKey.category) &&
                Objects.equals(price, ticketKey.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discount, category, price);
    }
}
