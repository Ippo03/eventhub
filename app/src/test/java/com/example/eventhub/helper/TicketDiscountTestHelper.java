package com.example.eventhub.helper;

import com.example.eventhub.domain.DiscountType;
import com.example.eventhub.domain.TicketDiscount;

import java.util.ArrayList;

public class TicketDiscountTestHelper {
    public static TicketDiscount initTicketDiscount1() {
        return new TicketDiscount(1, DiscountType.GENERAL, 0.0);
    }

    public static TicketDiscount initTicketDiscount2() {
        return new TicketDiscount(2, DiscountType.CHILD, 100.0);
    }

    public static TicketDiscount initTicketDiscount3() {
        return new TicketDiscount(3, DiscountType.STUDENT, 50.0);
    }

    public static ArrayList<TicketDiscount> initArrayOfTicketDiscounts() {
        ArrayList<TicketDiscount> ticketDiscounts = new ArrayList<>();
        TicketDiscount ticketDiscount1 = TicketDiscountTestHelper.initTicketDiscount1();
        TicketDiscount ticketDiscount2 = TicketDiscountTestHelper.initTicketDiscount2();
        ticketDiscounts.add(ticketDiscount1);
        ticketDiscounts.add(ticketDiscount2);
        return ticketDiscounts;
    }
}
