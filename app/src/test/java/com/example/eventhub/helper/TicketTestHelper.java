package com.example.eventhub.helper;

import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.Ticket;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;

import java.util.ArrayList;

public class TicketTestHelper {
    public static Ticket initTicket1(Event event) {
        TicketCategory ticketCategory = com.example.eventhub.helper.TicketCategoryTestHelper.initTicketCategory1();
        TicketDiscount ticketDiscount = com.example.eventhub.helper.TicketDiscountTestHelper.initTicketDiscount1();
        Ticket ticket = new Ticket(1, event, ticketCategory, ticketDiscount);
        return ticket;
    }

    public static Ticket initTicket2(Event event) {
        TicketCategory ticketCategory = com.example.eventhub.helper.TicketCategoryTestHelper.initTicketCategory2();
        TicketDiscount ticketDiscount = com.example.eventhub.helper.TicketDiscountTestHelper.initTicketDiscount2();
        Ticket ticket = new Ticket(2, event, ticketCategory, ticketDiscount);
        return ticket;
    }

    public  static Ticket initTicket12(Event event) {
        TicketCategory ticketCategory = com.example.eventhub.helper.TicketCategoryTestHelper.initTicketCategory1();
        TicketDiscount ticketDiscount = com.example.eventhub.helper.TicketDiscountTestHelper.initTicketDiscount2();
        Ticket ticket = new Ticket(3, event, ticketCategory, ticketDiscount);
        return ticket;
    }

    public static Ticket initTicket21(Event event) {
        TicketCategory ticketCategory = com.example.eventhub.helper.TicketCategoryTestHelper.initTicketCategory2();
        TicketDiscount ticketDiscount = com.example.eventhub.helper.TicketDiscountTestHelper.initTicketDiscount1();
        Ticket ticket = new Ticket(4, event, ticketCategory, ticketDiscount);
        return ticket;
    }

    public static Ticket initTicket3(Event event) {
        TicketCategory ticketCategory = com.example.eventhub.helper.TicketCategoryTestHelper.initTicketCategory3();
        TicketDiscount ticketDiscount = com.example.eventhub.helper.TicketDiscountTestHelper.initTicketDiscount2();
        Ticket ticket = new Ticket(5, event, ticketCategory, ticketDiscount);
        return ticket;
    }

    public static Ticket initTicket4(Event event) {
        TicketCategory ticketCategory = TicketCategoryTestHelper.initTicketCategory2();
        TicketDiscount ticketDiscount = TicketDiscountTestHelper.initTicketDiscount3();
        Ticket ticket = new Ticket(6, event, ticketCategory, ticketDiscount);
        return ticket;
    }

    public static ArrayList<Ticket> initArrayOfFourTickets(Event event) {

        Ticket ticket1 = TicketTestHelper.initTicket1(event);
        Ticket ticket2 = TicketTestHelper.initTicket2(event);
        Ticket ticket3 = TicketTestHelper.initTicket12(event);
        Ticket ticket4 = TicketTestHelper.initTicket21(event);

        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        tickets.add(ticket4);
        return tickets;
    }
}
