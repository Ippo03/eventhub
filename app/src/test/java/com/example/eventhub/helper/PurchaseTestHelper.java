package com.example.eventhub.helper;

import com.example.eventhub.domain.CategoryName;
import com.example.eventhub.domain.DiscountType;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.Purchase;
import com.example.eventhub.domain.Ticket;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;
import com.example.eventhub.util.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

public class PurchaseTestHelper {
    public static Purchase initPurchaseForEvent1() {
        Event event = com.example.eventhub.helper.EventTestHelper.initEvent();
        ArrayList<Ticket> tickets = TicketTestHelper.initArrayOfFourTickets(event);
        Purchase purchase = new Purchase(2, tickets);
        return purchase;
    }

    public static Purchase [] initTwoPurchasesForEvent1() {
        Event event = EventTestHelper.initEvent();
        Purchase purchase1 = initPurchaseForEvent1();

        TicketCategory ticketCategory2 = new TicketCategory(4, CategoryName.FRONT, new Money(new BigDecimal(200), Currency.getInstance("EUR")), "Front", 100);
        TicketDiscount ticket1Discount2 = new TicketDiscount(4, DiscountType.DISABILITY, 1.0);

        Ticket ticket1OfPurchase2 = new Ticket(7, event, ticketCategory2, ticket1Discount2);

        ArrayList<Ticket> tickets2 = new ArrayList<>();
        tickets2.add(ticket1OfPurchase2);

        Purchase purchase2 = new Purchase(3, tickets2);
        return new Purchase[]{purchase1, purchase2};
    }

    public static void purchaseScenario(Event event, TicketCategory ticketCategory1, TicketCategory ticketCategory2, TicketDiscount ticketDiscount1, TicketDiscount ticketDiscount2) {
        event.setTicketCategoryCountMap();
        ArrayList<TicketCategory> ticketCategories = new ArrayList<>();
        ticketCategories.add(ticketCategory1);
        ticketCategories.add(ticketCategory2);
        event.setTicketCategories(ticketCategories);

        ArrayList<TicketDiscount> ticketDiscounts = new ArrayList<>();
        ticketDiscounts.add(ticketDiscount1);
        ticketDiscounts.add(ticketDiscount2);
        event.setTicketDiscounts(ticketDiscounts);

        Ticket ticket1 = new Ticket(8, event, ticketCategory1, ticketDiscount1);
        Ticket ticket2 = new Ticket(9, event, ticketCategory1, ticketDiscount2);
        Ticket ticket3 = new Ticket(10, event, ticketCategory2, ticketDiscount1);
        Ticket ticket4 = new Ticket(11, event, ticketCategory2, ticketDiscount2);
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        tickets.add(ticket4);

        for (Ticket ticket : tickets) {
            if(event.getTicketCategoryCountMap().containsKey(ticket.getTicketCategory())) {
                event.getTicketCategoryCountMap().put(ticket.getTicketCategory(), event.getTicketCategoryCountMap().get(ticket.getTicketCategory()) - 1);
            }
            else {
                event.getTicketCategoryCountMap().put(ticket.getTicketCategory(), ticket.getTicketCategory().getQuantity() - 1);

            }
        }

        Purchase purchase = new Purchase(4, tickets);
    }
}