package com.example.eventhub.dao;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.domain.CategoryName;
import com.example.eventhub.domain.Customer;
import com.example.eventhub.domain.DiscountType;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.EventType;
import com.example.eventhub.domain.Genre;
import com.example.eventhub.domain.Interest;
import com.example.eventhub.domain.Organizer;
import com.example.eventhub.domain.Purchase;
import com.example.eventhub.domain.Review;
import com.example.eventhub.domain.Ticket;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;
import com.example.eventhub.util.Money;
import com.example.eventhub.util.Util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

public abstract class Initializer {
    protected abstract void eraseAll();

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void prepareData() {
        eraseAll();

        LocalDateTime now = LocalDateTime.now();
        UserDAO userDAO = getUserDAO();

        // ORGANIZER ADDRESSES
        Address address1 = new Address("Ermou", 10, "Athens", "11111", "Greece");
        Address address2 = new Address("Nikhs", 15, "Athens", "11112", "Greece");
        Address address3 = new Address("Voulis", 20, "Athens", "11113", "Greece");

        // CUSTOMER ADDRESSES
        Address address4 = new Address("Patision", 25, "Athens", "11114", "Greece");
        Address address5 = new Address("Kifisias", 30, "Athens", "11115", "Greece");
        Address address6 = new Address("Aiolou", 35, "Athens", "11121", "Greece");

        // EVENT ADDRESSES
        Address address7 = new Address("Acharnon", 40, "Athens", "11122", "Greece");
        Address address8 = new Address("Praxitelous", 50, "Athens", "11123", "Greece");
        Address address9 = new Address("Stadiou", 18, "Athens", "11116", "Greece");
        Address address10 = new Address("Panepistimiou", 22, "Athens", "11117", "Greece");
        Address address11 = new Address("Syntagma", 27, "Athens", "11118", "Greece");
        Address address12 = new Address("Omonia", 32, "Athens", "11119", "Greece");
        Address address13 = new Address("Solonos", 38, "Athens", "11120", "Greece");
        Address address14 = new Address("Aristotelous", 42, "Athens", "11124", "Greece");
        Address address15 = new Address("Mitropoleos", 55, "Athens", "11126", "Greece");
        Address address16 = new Address("Pireos", 60, "Athens", "11127", "Greece");
        Address address17 = new Address("Ermoupolis", 65, "Athens", "11128", "Greece");
        Address address18 = new Address("Monastiraki", 70, "Athens", "11129", "Greece");
        Address address19 = new Address("Dionysiou Areopagitou", 12, "Athens", "11130", "Greece");
        Address address20 = new Address("Plaka", 8, "Athens", "11131", "Greece");
        Address address21 = new Address("Thiseio", 14, "Athens", "11132", "Greece");
        Address address22 = new Address("Mavili Square", 19, "Athens", "11133", "Greece");
        Address address23 = new Address("Kolonaki", 24, "Athens", "11134", "Greece");
        Address address24 = new Address("Exarchia", 29, "Athens", "11135", "Greece");
        Address address25 = new Address("Koukaki", 34, "Athens", "11136", "Greece");
        Address address26 = new Address("Kerameikos", 39, "Athens", "11137", "Greece");

        // CUSTOMER INTERESTS
        Interest interest1 = new Interest(Genre.ART);
        Interest interest2 = new Interest(Genre.CINEMA);
        Interest interest3 = new Interest(Genre.MUSIC);
        Interest interest4 = new Interest(Genre.EDUCATION);
        Interest interest5 = new Interest(Genre.SPORTS);
        Interest interest6 = new Interest(Genre.SCIENCE);
        Interest interest7 = new Interest(Genre.FOOD);
        Interest interest8 = new Interest(Genre.BUSINESS);

        // CUSTOMER 1 INTERESTS
        Set<Interest> interests1 = new HashSet<>();
        interests1.add(interest1);
        interests1.add(interest2);

        // CUSTOMER 2 INTERESTS
        Set<Interest> interests2 = new HashSet<>();
        interests2.add(interest3);
        interests2.add(interest4);
        interests2.add(interest5);

        // CUSTOMER 3 INTERESTS
        Set<Interest> interests3 = new HashSet<>();
        interests3.add(interest6);
        interests3.add(interest7);
        interests3.add(interest8);

        // INITIALIZE ORGANIZERS
        OrganizerDAO organizerDAO = getOrganizerDAO();

        Organizer organizer1 = new Organizer(organizerDAO.nextId(), "John", "Doe", "johndoe@example.com", 25,
                "male", address1, "12345678", 192837465);
        userDAO.save(organizer1);
        organizerDAO.save(organizer1);

        Organizer organizer2 = new Organizer(organizerDAO.nextId(), "Jane", "Doe", "janedoe@example.com", 22,
                "female", address2, "87654321", 123456789);
        userDAO.save(organizer2);
        organizerDAO.save(organizer2);

        Organizer organizer3 = new Organizer(organizerDAO.nextId(), "Jake", "Smith", "jakesmith@example.com", 40,
                "male", address3, "12435687", 987654321);
        userDAO.save(organizer3);
        organizerDAO.save(organizer3);

        // INITIALIZE CUSTOMERS
        CustomerDAO customerDAO = getCustomerDAO();

        Customer customer1 = new Customer(customerDAO.nextId(), "Alice", "Johnson", "alice@example.com", 21,
                "female", address4, "45678901", interests1);
        userDAO.save(customer1);
        customerDAO.save(customer1);

        Customer customer2 = new Customer(customerDAO.nextId(), "Bob", "Williams", "bob@example.com", 56,
                "male", address5, "78901234", interests2);
        userDAO.save(customer2);
        customerDAO.save(customer2);

        Customer customer3 = new Customer(customerDAO.nextId(), "Charlie", "Brown", "charlie@example.com", 34,
                "female", address6, "90123456", interests3);
        userDAO.save(customer3);
        customerDAO.save(customer3);

        // INITIALIZE EVENTS
        EventDAO eventDAO = getEventDAO();

        eventDAO.save(new Event(eventDAO.nextId(), "House Party", address7, LocalDateTime.of(2023, 06, 04, 12, 12),
                Genre.MUSIC, EventType.OPEN));
        eventDAO.save(new Event(eventDAO.nextId(), "Taste of the World", address8, LocalDateTime.of(2023, 05, 12, 19, 0),
                Genre.FOOD, EventType.OPEN));
        eventDAO.save(new Event(eventDAO.nextId(), "Rock Festival", address9, LocalDateTime.of(2023, 06, 25, 15, 30),
                Genre.MUSIC, EventType.CLOSED));
        eventDAO.save(new Event(eventDAO.nextId(), "Football Championship", address10, LocalDateTime.of(2023, 07, 8, 18, 0),
                Genre.SPORTS, EventType.OPEN));
        eventDAO.save(new Event(eventDAO.nextId(), "Modern Art Exhibition", address11, LocalDateTime.of(2023, 8, 14, 14, 0),
                Genre.ART, EventType.CLOSED));
        eventDAO.save(new Event(eventDAO.nextId(), "Science Fair", address12, LocalDateTime.of(2023, 9, 30, 10, 0),
                Genre.SCIENCE, EventType.OPEN));
        eventDAO.save(new Event(eventDAO.nextId(), "Film Festival", address13, LocalDateTime.of(2023, 10, 15, 17, 45),
                Genre.CINEMA, EventType.FREE));
        eventDAO.save(new Event(eventDAO.nextId(), "Educational Workshop", address14, LocalDateTime.of(2023, 11, 22, 13, 30),
                Genre.EDUCATION, EventType.OPEN));
        eventDAO.save(new Event(eventDAO.nextId(), "Business Networking Event", address15, LocalDateTime.of(2023, 12, 05, 20, 15),
                Genre.BUSINESS, EventType.FREE));
        eventDAO.save(new Event(eventDAO.nextId(), "Gastronomy Masterclass", address16, LocalDateTime.of(2024, 02, 28, 14, 45),
                Genre.FOOD, EventType.CLOSED));
        eventDAO.save(new Event(eventDAO.nextId(), "Classical Music Concert", address17, LocalDateTime.of(2024, 03, 10, 19, 30),
                Genre.MUSIC, EventType.OPEN));
        eventDAO.save(new Event(eventDAO.nextId(), "Basketball Tournament", address18, LocalDateTime.of(2024, 04, 22, 17, 0),
                Genre.SPORTS, EventType.FREE));
        eventDAO.save(new Event(eventDAO.nextId(), "Photography Exhibition", address19, LocalDateTime.of(2024, 05, 07, 15, 15),
                Genre.ART, EventType.OPEN));
        eventDAO.save(new Event(eventDAO.nextId(), "Technology Symposium", address20, LocalDateTime.of(2024, 06, 20, 11, 30),
                Genre.SCIENCE, EventType.CLOSED));
        eventDAO.save(new Event(eventDAO.nextId(), "Independent Film Showcase", address21, LocalDateTime.of(2024, 07, 04, 16, 45),
                Genre.CINEMA, EventType.OPEN));
        eventDAO.save(new Event(eventDAO.nextId(), "Language Learning Workshop", address22, LocalDateTime.of(2024, 8, 15, 14, 0),
                Genre.EDUCATION, EventType.CLOSED));
        eventDAO.save(new Event(eventDAO.nextId(), "Startup Pitch Night", address23, LocalDateTime.of(2024, 9, 28, 20, 30),
                Genre.BUSINESS, EventType.FREE));
        eventDAO.save(new Event(eventDAO.nextId(), "Community Gathering", address24, LocalDateTime.of(2024, 10, 12, 18, 15),
                Genre.OTHER, EventType.CLOSED));
        eventDAO.save(new Event(eventDAO.nextId(), "Street Food Festival", address25, LocalDateTime.of(2024, 11, 25, 17, 0),
                Genre.FOOD, EventType.OPEN));
        eventDAO.save(new Event(eventDAO.nextId(), "ThinkBiz Academy", address26, LocalDateTime.of(2024, 12, 10, 20, 30),
                Genre.EDUCATION, EventType.FREE));

        // ATTACH ORGANIZERS TO EVENTS
        organizerDAO.find(1).addEvent(eventDAO.find(1));
        organizerDAO.find(1).addEvent(eventDAO.find(2));
        organizerDAO.find(1).addEvent(eventDAO.find(3));
        organizerDAO.find(1).addEvent(eventDAO.find(4));
        organizerDAO.find(1).addEvent(eventDAO.find(5));
        organizerDAO.find(1).addEvent(eventDAO.find(6));
        organizerDAO.find(1).addEvent(eventDAO.find(7));
        organizerDAO.find(1).addEvent(eventDAO.find(8));
        organizerDAO.find(2).addEvent(eventDAO.find(9));
        organizerDAO.find(2).addEvent(eventDAO.find(10));
        organizerDAO.find(2).addEvent(eventDAO.find(11));
        organizerDAO.find(2).addEvent(eventDAO.find(12));
        organizerDAO.find(2).addEvent(eventDAO.find(13));
        organizerDAO.find(2).addEvent(eventDAO.find(14));
        organizerDAO.find(3).addEvent(eventDAO.find(15));
        organizerDAO.find(3).addEvent(eventDAO.find(16));
        organizerDAO.find(3).addEvent(eventDAO.find(17));
        organizerDAO.find(3).addEvent(eventDAO.find(18));
        organizerDAO.find(3).addEvent(eventDAO.find(19));
        organizerDAO.find(3).addEvent(eventDAO.find(20));

        // INITIALIZE TICKET CATEGORIES
        Currency euroCurrency = Currency.getInstance("EUR");
        TicketCategoryDAO ticketCategoryDAO = getTicketCategoryDAO();

        TicketCategory ticketCategory1 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.GENERAL, new Money(new BigDecimal(20), euroCurrency),
                "General Admission", 150);
        ticketCategoryDAO.save(ticketCategory1);

        TicketCategory ticketCategory2 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.GENERAL, new Money(new BigDecimal(30), euroCurrency),
                "General Admission", 100);
        ticketCategoryDAO.save(ticketCategory2);

        TicketCategory ticketCategory3 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.GENERAL, new Money(new BigDecimal(40), euroCurrency),
                "General Admission", 100);
        ticketCategoryDAO.save(ticketCategory3);

        TicketCategory ticketCategory4 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.GENERAL, new Money(new BigDecimal(50), euroCurrency),
                "General Admission", 50);
        ticketCategoryDAO.save(ticketCategory4);

        TicketCategory ticketCategory5 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.GENERAL, new Money(new BigDecimal(60), euroCurrency),
                "General Admission", 50);
        ticketCategoryDAO.save(ticketCategory5);

        TicketCategory ticketCategory6 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.STANDING, new Money(new BigDecimal(30), euroCurrency),
                "Standing", 80);
        ticketCategoryDAO.save(ticketCategory6);

        TicketCategory ticketCategory7 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.STANDING, new Money(new BigDecimal(40), euroCurrency),
                "Standing", 50);
        ticketCategoryDAO.save(ticketCategory7);

        TicketCategory ticketCategory8 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.STANDING, new Money(new BigDecimal(50), euroCurrency),
                "Standing", 30);
        ticketCategoryDAO.save(ticketCategory8);

        TicketCategory ticketCategory9 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.STANDING, new Money(new BigDecimal(60), euroCurrency),
                "Standing", 20);
        ticketCategoryDAO.save(ticketCategory9);

        TicketCategory ticketCategory10 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.STANDING, new Money(new BigDecimal(70), euroCurrency),
                "Standing", 10);
        ticketCategoryDAO.save(ticketCategory10);

        TicketCategory ticketCategory11 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.FRONT, new Money(new BigDecimal(50), euroCurrency),
                "Front Row", 20);
        ticketCategoryDAO.save(ticketCategory11);

        TicketCategory ticketCategory12 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.FRONT, new Money(new BigDecimal(60), euroCurrency),
                "Front Row", 30);
        ticketCategoryDAO.save(ticketCategory12);

        TicketCategory ticketCategory13 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.FRONT, new Money(new BigDecimal(70), euroCurrency),
                "Front Row", 40);
        ticketCategoryDAO.save(ticketCategory13);

        TicketCategory ticketCategory14 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.FRONT, new Money(new BigDecimal(80), euroCurrency),
                "Front Row", 50);
        ticketCategoryDAO.save(ticketCategory14);

        TicketCategory ticketCategory15 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.FRONT, new Money(new BigDecimal(90), euroCurrency),
                "Front Row", 60);
        ticketCategoryDAO.save(ticketCategory15);

        TicketCategory ticketCategory16 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.SIDE, new Money(new BigDecimal(40), euroCurrency),
                "Side", 20);
        ticketCategoryDAO.save(ticketCategory16);

        TicketCategory ticketCategory17 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.SIDE, new Money(new BigDecimal(50), euroCurrency),
                "Side", 30);
        ticketCategoryDAO.save(ticketCategory17);

        TicketCategory ticketCategory18 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.SIDE, new Money(new BigDecimal(60), euroCurrency),
                "Side", 40);
        ticketCategoryDAO.save(ticketCategory18);

        TicketCategory ticketCategory19 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.SIDE, new Money(new BigDecimal(70), euroCurrency),
                "Side", 50);
        ticketCategoryDAO.save(ticketCategory19);

        TicketCategory ticketCategory20 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.SIDE, new Money(new BigDecimal(80), euroCurrency),
                "Side", 60);
        ticketCategoryDAO.save(ticketCategory20);

        TicketCategory ticketCategory21 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.VIP, new Money(new BigDecimal(100), euroCurrency),
                "VIP", 20);
        ticketCategoryDAO.save(ticketCategory21);

        TicketCategory ticketCategory22 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.VIP, new Money(new BigDecimal(110), euroCurrency),
                "VIP", 20);
        ticketCategoryDAO.save(ticketCategory22);

        TicketCategory ticketCategory23 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.VIP, new Money(new BigDecimal(120), euroCurrency),
                "VIP", 20);
        ticketCategoryDAO.save(ticketCategory23);

        TicketCategory ticketCategory24 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.VIP, new Money(new BigDecimal(130), euroCurrency),
                "VIP", 15);
        ticketCategoryDAO.save(ticketCategory24);

        TicketCategory ticketCategory25 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.VIP, new Money(new BigDecimal(150), euroCurrency),
                "VIP", 10);
        ticketCategoryDAO.save(ticketCategory25);

        TicketCategory ticketCategory26 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.VIP_PLUS, new Money(new BigDecimal(200), euroCurrency),
                "VIP+", 15);
        ticketCategoryDAO.save(ticketCategory26);

        TicketCategory ticketCategory27 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.VIP_PLUS, new Money(new BigDecimal(250), euroCurrency),
                "VIP+", 10);
        ticketCategoryDAO.save(ticketCategory27);

        TicketCategory ticketCategory28 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.VIP_PLUS, new Money(new BigDecimal(300), euroCurrency),
                "VIP+", 5);
        ticketCategoryDAO.save(ticketCategory28);

        TicketCategory ticketCategory29 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.VIP_PLUS, new Money(new BigDecimal(350), euroCurrency),
                "VIP+", 5);
        ticketCategoryDAO.save(ticketCategory29);

        TicketCategory ticketCategory30 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.VIP_PLUS, new Money(new BigDecimal(500), euroCurrency),
                "VIP+", 3);
        ticketCategoryDAO.save(ticketCategory30);

        // TICKET CATEGORY OF FREE EVENTS
        TicketCategory ticketCategory0 = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.GENERAL, new Money(new BigDecimal(0), euroCurrency),
                "Free Admission", 150);
        ticketCategoryDAO.save(ticketCategory0);

        // INITIALIZE TICKET DISCOUNTS
        TicketDiscountDAO ticketDiscountDAO = getTicketDiscountDAO();

        TicketDiscount ticketDiscount1 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.GENERAL, 0.0);
        ticketDiscountDAO.save(ticketDiscount1);

        TicketDiscount ticketDiscount2 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.GENERAL, 10.0);
        ticketDiscountDAO.save(ticketDiscount2);

        TicketDiscount ticketDiscount3 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.GENERAL, 20.0);
        ticketDiscountDAO.save(ticketDiscount3);

        TicketDiscount ticketDiscount4 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.GENERAL, 30.0);
        ticketDiscountDAO.save(ticketDiscount4);

        TicketDiscount ticketDiscount5 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.GENERAL, 40.0);
        ticketDiscountDAO.save(ticketDiscount5);

        TicketDiscount ticketDiscount6 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.GENERAL, 50.0);
        ticketDiscountDAO.save(ticketDiscount6);

        TicketDiscount ticketDiscount7 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.STUDENT, 20.0);
        ticketDiscountDAO.save(ticketDiscount7);

        TicketDiscount ticketDiscount8 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.STUDENT, 30.0);
        ticketDiscountDAO.save(ticketDiscount8);

        TicketDiscount ticketDiscount9 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.STUDENT, 40.0);
        ticketDiscountDAO.save(ticketDiscount9);

        TicketDiscount ticketDiscount10 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.STUDENT, 50.0);
        ticketDiscountDAO.save(ticketDiscount10);

        TicketDiscount ticketDiscount11 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.STUDENT, 60.0);
        ticketDiscountDAO.save(ticketDiscount11);

        TicketDiscount ticketDiscount12 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.STUDENT, 70.0);
        ticketDiscountDAO.save(ticketDiscount12);

        TicketDiscount ticketDiscount13 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.SENIOR, 30.0);
        ticketDiscountDAO.save(ticketDiscount13);

        TicketDiscount ticketDiscount14 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.SENIOR, 40.0);
        ticketDiscountDAO.save(ticketDiscount14);

        TicketDiscount ticketDiscount15 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.SENIOR, 50.0);
        ticketDiscountDAO.save(ticketDiscount15);

        TicketDiscount ticketDiscount16 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.SENIOR, 60.0);
        ticketDiscountDAO.save(ticketDiscount16);

        TicketDiscount ticketDiscount17 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.SENIOR, 70.0);
        ticketDiscountDAO.save(ticketDiscount17);

        TicketDiscount ticketDiscount18 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.SENIOR, 80.0);
        ticketDiscountDAO.save(ticketDiscount18);

        TicketDiscount ticketDiscount19 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.CHILD, 20.0);
        ticketDiscountDAO.save(ticketDiscount19);

        TicketDiscount ticketDiscount20 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.CHILD, 30.0);
        ticketDiscountDAO.save(ticketDiscount20);

        TicketDiscount ticketDiscount21 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.CHILD, 50.0);
        ticketDiscountDAO.save(ticketDiscount21);

        TicketDiscount ticketDiscount22 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.CHILD, 60.0);
        ticketDiscountDAO.save(ticketDiscount22);

        TicketDiscount ticketDiscount23 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.CHILD, 80.0);
        ticketDiscountDAO.save(ticketDiscount23);

        TicketDiscount ticketDiscount24 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.CHILD, 100.0);
        ticketDiscountDAO.save(ticketDiscount24);

        TicketDiscount ticketDiscount25 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.DISABILITY, 40.0);
        ticketDiscountDAO.save(ticketDiscount25);

        TicketDiscount ticketDiscount26 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.DISABILITY, 50.0);
        ticketDiscountDAO.save(ticketDiscount26);

        TicketDiscount ticketDiscount27 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.DISABILITY, 60.0);
        ticketDiscountDAO.save(ticketDiscount27);

        TicketDiscount ticketDiscount28 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.DISABILITY, 70.0);
        ticketDiscountDAO.save(ticketDiscount28);

        TicketDiscount ticketDiscount29 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.DISABILITY, 80.0);
        ticketDiscountDAO.save(ticketDiscount29);

        TicketDiscount ticketDiscount30 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.DISABILITY, 90.0);
        ticketDiscountDAO.save(ticketDiscount30);

        // TICKET DISCOUNT OF FREE EVENTS
        TicketDiscount ticketDiscount0 = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.GENERAL, 100.0);
        ticketDiscountDAO.save(ticketDiscount0);

        // ATTACH TICKET CATEGORIES AND DISCOUNTS TO EVENTS
        TicketDAO ticketDAO = getTicketDAO();

        eventDAO.find(1).addTicketCategory(ticketCategory1);
        eventDAO.find(1).addTicketCategory(ticketCategory17);
        eventDAO.find(1).addTicketDiscount(ticketDiscount3);
        eventDAO.find(1).addTicketDiscount(ticketDiscount11);
        eventDAO.find(1).getTicketCategoryCountMap().put(ticketCategory1, ticketCategory1.getQuantity());
        eventDAO.find(1).getTicketCategoryCountMap().put(ticketCategory17, ticketCategory17.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(1), ticketCategory1, ticketDiscount3));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(1), ticketCategory17, ticketDiscount11));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(1), ticketCategory17, ticketDiscount3));

        eventDAO.find(2).addTicketCategory(ticketCategory4);
        eventDAO.find(2).addTicketCategory(ticketCategory12);
        eventDAO.find(2).addTicketCategory(ticketCategory26);
        eventDAO.find(2).addTicketDiscount(ticketDiscount2);
        eventDAO.find(2).addTicketDiscount(ticketDiscount13);
        eventDAO.find(2).addTicketDiscount(ticketDiscount21);
        eventDAO.find(2).getTicketCategoryCountMap().put(ticketCategory4, ticketCategory4.getQuantity());
        eventDAO.find(2).getTicketCategoryCountMap().put(ticketCategory12, ticketCategory12.getQuantity());
        eventDAO.find(2).getTicketCategoryCountMap().put(ticketCategory26, ticketCategory26.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(2), ticketCategory4, ticketDiscount2));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(2), ticketCategory12, ticketDiscount13));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(2), ticketCategory26, ticketDiscount21));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(2), ticketCategory26, ticketDiscount2));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(2), ticketCategory12, ticketDiscount13));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(2), ticketCategory4, ticketDiscount21));

        eventDAO.find(3).addTicketCategory(ticketCategory3);
        eventDAO.find(3).addTicketCategory(ticketCategory8);
        eventDAO.find(3).addTicketDiscount(ticketDiscount1);
        eventDAO.find(3).addTicketDiscount(ticketDiscount27);
        eventDAO.find(3).getTicketCategoryCountMap().put(ticketCategory3, ticketCategory3.getQuantity());
        eventDAO.find(3).getTicketCategoryCountMap().put(ticketCategory8, ticketCategory8.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(3), ticketCategory3, ticketDiscount1));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(3), ticketCategory8, ticketDiscount27));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(3), ticketCategory8, ticketDiscount1));

        eventDAO.find(4).addTicketCategory(ticketCategory4);
        eventDAO.find(4).addTicketCategory(ticketCategory19);
        eventDAO.find(4).addTicketDiscount(ticketDiscount8);
        eventDAO.find(4).addTicketDiscount(ticketDiscount24);
        eventDAO.find(4).getTicketCategoryCountMap().put(ticketCategory4, ticketCategory4.getQuantity());
        eventDAO.find(4).getTicketCategoryCountMap().put(ticketCategory19, ticketCategory19.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(4), ticketCategory4, ticketDiscount8));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(4), ticketCategory19, ticketDiscount24));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(4), ticketCategory19, ticketDiscount8));

        eventDAO.find(5).addTicketCategory(ticketCategory5);
        eventDAO.find(5).addTicketDiscount(ticketDiscount10);
        eventDAO.find(5).getTicketCategoryCountMap().put(ticketCategory5, ticketCategory5.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(5), ticketCategory5, ticketDiscount10));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(5), ticketCategory5, ticketDiscount10));

        eventDAO.find(6).addTicketCategory(ticketCategory6);
        eventDAO.find(6).addTicketCategory(ticketCategory15);
        eventDAO.find(6).addTicketDiscount(ticketDiscount7);
        eventDAO.find(6).addTicketDiscount(ticketDiscount23);
        eventDAO.find(6).getTicketCategoryCountMap().put(ticketCategory6, ticketCategory6.getQuantity());
        eventDAO.find(6).getTicketCategoryCountMap().put(ticketCategory15, ticketCategory15.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(6), ticketCategory6, ticketDiscount23));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(6), ticketCategory15, ticketDiscount7));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(6), ticketCategory6, ticketDiscount7));

        eventDAO.find(7).addTicketCategory(ticketCategory0);
        eventDAO.find(7).addTicketDiscount(ticketDiscount0);
        eventDAO.find(7).getTicketCategoryCountMap().put(ticketCategory0, ticketCategory0.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(7), ticketCategory0, ticketDiscount0));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(7), ticketCategory0, ticketDiscount0));

        eventDAO.find(8).addTicketCategory(ticketCategory8);
        eventDAO.find(8).addTicketCategory(ticketCategory18);
        eventDAO.find(8).addTicketDiscount(ticketDiscount6);
        eventDAO.find(8).addTicketDiscount(ticketDiscount13);
        eventDAO.find(8).getTicketCategoryCountMap().put(ticketCategory8, ticketCategory8.getQuantity());
        eventDAO.find(8).getTicketCategoryCountMap().put(ticketCategory18, ticketCategory18.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(8), ticketCategory8, ticketDiscount6));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(8), ticketCategory18, ticketDiscount13));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(8), ticketCategory18, ticketDiscount6));

        eventDAO.find(9).addTicketCategory(ticketCategory0);
        eventDAO.find(9).addTicketDiscount(ticketDiscount0);
        eventDAO.find(9).getTicketCategoryCountMap().put(ticketCategory0, ticketCategory0.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(9), ticketCategory0, ticketDiscount0));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(9), ticketCategory0, ticketDiscount0));

        eventDAO.find(10).addTicketCategory(ticketCategory12);
        eventDAO.find(10).addTicketDiscount(ticketDiscount20);
        eventDAO.find(10).getTicketCategoryCountMap().put(ticketCategory12, ticketCategory12.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(10), ticketCategory12, ticketDiscount20));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(10), ticketCategory12, ticketDiscount20));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(10), ticketCategory12, ticketDiscount20));

        eventDAO.find(11).addTicketCategory(ticketCategory11);
        eventDAO.find(11).addTicketCategory(ticketCategory22);
        eventDAO.find(11).addTicketDiscount(ticketDiscount22);
        eventDAO.find(11).addTicketDiscount(ticketDiscount11);
        eventDAO.find(11).getTicketCategoryCountMap().put(ticketCategory11, ticketCategory11.getQuantity());
        eventDAO.find(11).getTicketCategoryCountMap().put(ticketCategory22, ticketCategory22.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(11), ticketCategory11, ticketDiscount11));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(11), ticketCategory22, ticketDiscount22));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(11), ticketCategory22, ticketDiscount11));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(11), ticketCategory11, ticketDiscount22));

        eventDAO.find(12).addTicketCategory(ticketCategory0);
        eventDAO.find(12).addTicketDiscount(ticketDiscount0);
        eventDAO.find(12).getTicketCategoryCountMap().put(ticketCategory0, ticketCategory0.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(12), ticketCategory0, ticketDiscount0));

        eventDAO.find(13).addTicketCategory(ticketCategory13);
        eventDAO.find(13).addTicketCategory(ticketCategory25);
        eventDAO.find(13).addTicketDiscount(ticketDiscount12);
        eventDAO.find(13).addTicketDiscount(ticketDiscount26);
        eventDAO.find(13).getTicketCategoryCountMap().put(ticketCategory13, ticketCategory13.getQuantity());
        eventDAO.find(13).getTicketCategoryCountMap().put(ticketCategory25, ticketCategory25.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(13), ticketCategory13, ticketDiscount12));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(13), ticketCategory25, ticketDiscount26));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(13), ticketCategory25, ticketDiscount12));

        eventDAO.find(14).addTicketCategory(ticketCategory15);
        eventDAO.find(14).addTicketDiscount(ticketDiscount15);
        eventDAO.find(14).getTicketCategoryCountMap().put(ticketCategory15, ticketCategory15.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(14), ticketCategory15, ticketDiscount15));

        eventDAO.find(15).addTicketCategory(ticketCategory16);
        eventDAO.find(15).addTicketCategory(ticketCategory29);
        eventDAO.find(15).addTicketDiscount(ticketDiscount9);
        eventDAO.find(15).addTicketDiscount(ticketDiscount30);
        eventDAO.find(15).getTicketCategoryCountMap().put(ticketCategory16, ticketCategory16.getQuantity());
        eventDAO.find(15).getTicketCategoryCountMap().put(ticketCategory29, ticketCategory29.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(15), ticketCategory16, ticketDiscount9));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(15), ticketCategory29, ticketDiscount30));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(15), ticketCategory29, ticketDiscount9));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(15), ticketCategory16, ticketDiscount30));

        eventDAO.find(16).addTicketCategory(ticketCategory9);
        eventDAO.find(16).addTicketDiscount(ticketDiscount17);
        eventDAO.find(16).getTicketCategoryCountMap().put(ticketCategory9, ticketCategory9.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(16), ticketCategory9, ticketDiscount17));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(16), ticketCategory9, ticketDiscount17));

        eventDAO.find(17).addTicketCategory(ticketCategory0);
        eventDAO.find(17).addTicketDiscount(ticketDiscount0);
        eventDAO.find(17).getTicketCategoryCountMap().put(ticketCategory0, ticketCategory0.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(17), ticketCategory0, ticketDiscount0));

        eventDAO.find(18).addTicketCategory(ticketCategory1);
        eventDAO.find(18).addTicketCategory(ticketCategory16);
        eventDAO.find(18).addTicketCategory(ticketCategory21);
        eventDAO.find(18).addTicketDiscount(ticketDiscount3);
        eventDAO.find(18).addTicketDiscount(ticketDiscount10);
        eventDAO.find(18).addTicketDiscount(ticketDiscount23);
        eventDAO.find(18).getTicketCategoryCountMap().put(ticketCategory1, ticketCategory1.getQuantity());
        eventDAO.find(18).getTicketCategoryCountMap().put(ticketCategory16, ticketCategory16.getQuantity());
        eventDAO.find(18).getTicketCategoryCountMap().put(ticketCategory21, ticketCategory21.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(18), ticketCategory1, ticketDiscount3));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(18), ticketCategory16, ticketDiscount10));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(18), ticketCategory21, ticketDiscount23));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(18), ticketCategory21, ticketDiscount3));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(18), ticketCategory16, ticketDiscount10));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(18), ticketCategory1, ticketDiscount23));

        eventDAO.find(19).addTicketCategory(ticketCategory2);
        eventDAO.find(19).addTicketCategory(ticketCategory14);
        eventDAO.find(19).addTicketDiscount(ticketDiscount4);
        eventDAO.find(19).addTicketDiscount(ticketDiscount14);
        eventDAO.find(19).addTicketDiscount(ticketDiscount25);
        eventDAO.find(19).getTicketCategoryCountMap().put(ticketCategory2, ticketCategory2.getQuantity());
        eventDAO.find(19).getTicketCategoryCountMap().put(ticketCategory14, ticketCategory14.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(19), ticketCategory2, ticketDiscount4));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(19), ticketCategory14, ticketDiscount14));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(19), ticketCategory14, ticketDiscount4));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(19), ticketCategory2, ticketDiscount25));
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(19), ticketCategory14, ticketDiscount14));

        eventDAO.find(20).addTicketCategory(ticketCategory0);
        eventDAO.find(20).addTicketDiscount(ticketDiscount0);
        eventDAO.find(20).getTicketCategoryCountMap().put(ticketCategory0, ticketCategory0.getQuantity());
        ticketDAO.save(new Ticket(ticketDAO.nextId(), eventDAO.find(20), ticketCategory0, ticketDiscount0));

        // INITIALIZE PURCHASES
        PurchaseDAO purchaseDAO = getPurchaseDAO();

        ArrayList<Ticket> tickets1 = new ArrayList<>();
        tickets1.add(ticketDAO.find(1));
        tickets1.add(ticketDAO.find(2));

        Event attachedEvent1 = tickets1.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent1, tickets1);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets1));

        ArrayList<Ticket> tickets2 = new ArrayList<>();
        tickets2.add(ticketDAO.find(3));

        Event attachedEvent2 = tickets2.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent2, tickets2);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets2));

        ArrayList<Ticket> tickets3 = new ArrayList<>();
        tickets3.add(ticketDAO.find(4));
        tickets3.add(ticketDAO.find(5));
        tickets3.add(ticketDAO.find(6));
        tickets3.add(ticketDAO.find(7));

        Event attachedEvent3 = tickets3.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent3, tickets3);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets3));

        ArrayList<Ticket> tickets4 = new ArrayList<>();
        tickets4.add(ticketDAO.find(8));
        tickets4.add(ticketDAO.find(9));

        Event attachedEvent4 = tickets4.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent4, tickets4);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets4));

        ArrayList<Ticket> tickets5 = new ArrayList<>();
        tickets5.add(ticketDAO.find(10));
        tickets5.add(ticketDAO.find(11));
        tickets5.add(ticketDAO.find(12));

        Event attachedEvent5 = tickets5.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent5, tickets5);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets5));

        ArrayList<Ticket> tickets6 = new ArrayList<>();
        tickets6.add(ticketDAO.find(13));

        Event attachedEvent6 = tickets6.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent6, tickets6);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets6));

        ArrayList<Ticket> tickets7 = new ArrayList<>();
        tickets7.add(ticketDAO.find(14));
        tickets7.add(ticketDAO.find(15));

        Event attachedEvent7 = tickets7.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent7, tickets7);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets7));

        ArrayList<Ticket> tickets8 = new ArrayList<>();
        tickets8.add(ticketDAO.find(16));
        tickets8.add(ticketDAO.find(17));

        Event attachedEvent8 = tickets8.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent8, tickets8);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets8));

        ArrayList<Ticket> tickets9 = new ArrayList<>();
        tickets9.add(ticketDAO.find(18));
        tickets9.add(ticketDAO.find(19));
        tickets9.add(ticketDAO.find(20));

        Event attachedEvent9 = tickets9.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent9, tickets9);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets9));

        ArrayList<Ticket> tickets10 = new ArrayList<>();
        tickets10.add(ticketDAO.find(21));
        tickets10.add(ticketDAO.find(22));

        Event attachedEvent10 = tickets10.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent10, tickets10);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets10));

        ArrayList<Ticket> tickets11 = new ArrayList<>();
        tickets11.add(ticketDAO.find(23));
        tickets11.add(ticketDAO.find(24));

        Event attachedEvent11 = tickets11.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent11, tickets11);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets11));

        ArrayList<Ticket> tickets12 = new ArrayList<>();
        tickets12.add(ticketDAO.find(25));

        Event attachedEvent12 = tickets12.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent12, tickets12);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets12));

        ArrayList<Ticket> tickets13 = new ArrayList<>();
        tickets13.add(ticketDAO.find(26));
        tickets13.add(ticketDAO.find(27));

        Event attachedEvent13 = tickets13.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent13, tickets13);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets13));

        ArrayList<Ticket> tickets14 = new ArrayList<>();
        tickets14.add(ticketDAO.find(28));
        tickets14.add(ticketDAO.find(29));
        tickets14.add(ticketDAO.find(30));

        Event attachedEvent14 = tickets14.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent14, tickets14);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets14));

        ArrayList<Ticket> tickets15 = new ArrayList<>();
        tickets15.add(ticketDAO.find(31));
        tickets15.add(ticketDAO.find(32));
        tickets15.add(ticketDAO.find(33));

        Event attachedEvent15 = tickets15.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent15, tickets15);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets15));

        ArrayList<Ticket> tickets16 = new ArrayList<>();
        tickets16.add(ticketDAO.find(34));

        Event attachedEvent16 = tickets16.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent16, tickets16);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets16));

        ArrayList<Ticket> tickets17 = new ArrayList<>();
        tickets17.add(ticketDAO.find(35));

        Event attachedEvent17 = tickets17.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent17, tickets17);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets17));

        ArrayList<Ticket> tickets18 = new ArrayList<>();
        tickets18.add(ticketDAO.find(36));

        Event attachedEvent18 = tickets18.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent18, tickets18);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets18));

        ArrayList<Ticket> tickets19 = new ArrayList<>();
        tickets19.add(ticketDAO.find(37));
        tickets19.add(ticketDAO.find(38));

        Event attachedEvent19 = tickets19.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent19, tickets19);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets19));

        ArrayList<Ticket> tickets20 = new ArrayList<>();
        tickets20.add(ticketDAO.find(39));

        Event attachedEvent20 = tickets20.get(0).getEvent();
        Util.initTicketCategoryCountMap(attachedEvent20, tickets20);

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets20));

        ArrayList<Ticket> tickets21 = new ArrayList<>();
        tickets21.add(ticketDAO.find(40));
        tickets21.add(ticketDAO.find(41));
        tickets21.add(ticketDAO.find(42));
        tickets21.add(ticketDAO.find(43));

        Event attachedEvent21 = tickets21.get(0).getEvent();
        for (Ticket ticket : tickets21) {
            attachedEvent21.getTicketCategoryCountMap().put(ticket.getTicketCategory(), attachedEvent21.getTicketCategoryCountMap().get(ticket.getTicketCategory()) - 1);
        }

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets21));

        ArrayList<Ticket> tickets22 = new ArrayList<>();
        tickets22.add(ticketDAO.find(44));
        tickets22.add(ticketDAO.find(45));

        Event attachedEvent22 = tickets22.get(0).getEvent();
        for (Ticket ticket : tickets22) {
            attachedEvent22.getTicketCategoryCountMap().put(ticket.getTicketCategory(), attachedEvent22.getTicketCategoryCountMap().get(ticket.getTicketCategory()) - 1);
        }

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets22));

        ArrayList<Ticket> tickets23 = new ArrayList<>();
        tickets23.add(ticketDAO.find(46));

        Event attachedEvent23 = tickets23.get(0).getEvent();
        for (Ticket ticket : tickets23) {
            attachedEvent23.getTicketCategoryCountMap().put(ticket.getTicketCategory(), attachedEvent23.getTicketCategoryCountMap().get(ticket.getTicketCategory()) - 1);
        }

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets23));

        ArrayList<Ticket> tickets24 = new ArrayList<>();
        tickets24.add(ticketDAO.find(47));
        tickets24.add(ticketDAO.find(48));

        Event attachedEvent24 = tickets24.get(0).getEvent();
        for (Ticket ticket : tickets24) {
            attachedEvent24.getTicketCategoryCountMap().put(ticket.getTicketCategory(), attachedEvent24.getTicketCategoryCountMap().get(ticket.getTicketCategory()) - 1);
        }

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets24));

        ArrayList<Ticket> tickets25 = new ArrayList<>();
        tickets25.add(ticketDAO.find(49));
        tickets25.add(ticketDAO.find(50));
        tickets25.add(ticketDAO.find(51));
        tickets25.add(ticketDAO.find(52));

        Event attachedEvent25 = tickets25.get(0).getEvent();
        for (Ticket ticket : tickets25) {
            attachedEvent25.getTicketCategoryCountMap().put(ticket.getTicketCategory(), attachedEvent25.getTicketCategoryCountMap().get(ticket.getTicketCategory()) - 1);
        }

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets25));

        ArrayList<Ticket> tickets26 = new ArrayList<>();
        tickets26.add(ticketDAO.find(53));
        tickets26.add(ticketDAO.find(54));
        tickets26.add(ticketDAO.find(55));

        Event attachedEvent26 = tickets26.get(0).getEvent();
        for (Ticket ticket : tickets26) {
            attachedEvent26.getTicketCategoryCountMap().put(ticket.getTicketCategory(), attachedEvent26.getTicketCategoryCountMap().get(ticket.getTicketCategory()) - 1);
        }

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets26));

        ArrayList<Ticket> tickets27 = new ArrayList<>();
        tickets27.add(ticketDAO.find(56));
        tickets27.add(ticketDAO.find(57));

        Event attachedEvent27 = tickets27.get(0).getEvent();
        for (Ticket ticket : tickets27) {
            attachedEvent27.getTicketCategoryCountMap().put(ticket.getTicketCategory(), attachedEvent27.getTicketCategoryCountMap().get(ticket.getTicketCategory()) - 1);
        }

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets27));

        ArrayList<Ticket> tickets28 = new ArrayList<>();
        tickets28.add(ticketDAO.find(58));

        Event attachedEvent28 = tickets28.get(0).getEvent();
        for (Ticket ticket : tickets28) {
            attachedEvent28.getTicketCategoryCountMap().put(ticket.getTicketCategory(), attachedEvent28.getTicketCategoryCountMap().get(ticket.getTicketCategory()) - 1);
        }

        purchaseDAO.save(new Purchase(purchaseDAO.nextId(), tickets28));

        // ATTACH PURCHASES TO CUSTOMERS
        customerDAO.find(4).addPurchase(purchaseDAO.find(1));
        customerDAO.find(4).addPurchase(purchaseDAO.find(2));
        customerDAO.find(4).addPurchase(purchaseDAO.find(3));
        customerDAO.find(4).addPurchase(purchaseDAO.find(4));
        customerDAO.find(4).addPurchase(purchaseDAO.find(5));
        customerDAO.find(4).addPurchase(purchaseDAO.find(6));
        customerDAO.find(4).addPurchase(purchaseDAO.find(7));
        customerDAO.find(4).addPurchase(purchaseDAO.find(8));
        customerDAO.find(4).addPurchase(purchaseDAO.find(9));
        customerDAO.find(4).addPurchase(purchaseDAO.find(10));
        customerDAO.find(5).addPurchase(purchaseDAO.find(11));
        customerDAO.find(5).addPurchase(purchaseDAO.find(12));
        customerDAO.find(5).addPurchase(purchaseDAO.find(13));
        customerDAO.find(5).addPurchase(purchaseDAO.find(14));
        customerDAO.find(5).addPurchase(purchaseDAO.find(15));
        customerDAO.find(5).addPurchase(purchaseDAO.find(16));
        customerDAO.find(5).addPurchase(purchaseDAO.find(17));
        customerDAO.find(5).addPurchase(purchaseDAO.find(18));
        customerDAO.find(5).addPurchase(purchaseDAO.find(19));
        customerDAO.find(6).addPurchase(purchaseDAO.find(20));
        customerDAO.find(6).addPurchase(purchaseDAO.find(21));
        customerDAO.find(6).addPurchase(purchaseDAO.find(22));
        customerDAO.find(6).addPurchase(purchaseDAO.find(23));
        customerDAO.find(6).addPurchase(purchaseDAO.find(24));
        customerDAO.find(6).addPurchase(purchaseDAO.find(25));
        customerDAO.find(6).addPurchase(purchaseDAO.find(26));
        customerDAO.find(6).addPurchase(purchaseDAO.find(27));
        customerDAO.find(6).addPurchase(purchaseDAO.find(28));

        // INITIALIZE REVIEWS
        ReviewDAO reviewDAO = getReviewDAO();

        reviewDAO.save(new Review(reviewDAO.nextId(), 10, "Great event!"));
        reviewDAO.save(new Review(reviewDAO.nextId(), 9, "Very very good event!"));
        reviewDAO.save(new Review(reviewDAO.nextId(), 8, "Very good event!"));
        reviewDAO.save(new Review(reviewDAO.nextId(), 7, "Good event!"));
        reviewDAO.save(new Review(reviewDAO.nextId(), 6, "Average event!"));
        reviewDAO.save(new Review(reviewDAO.nextId(), 5, "Below average event!"));
        reviewDAO.save(new Review(reviewDAO.nextId(), 4, "Bad event!"));
        reviewDAO.save(new Review(reviewDAO.nextId(), 3, "Very bad event!"));
        reviewDAO.save(new Review(reviewDAO.nextId(), 2, "Terrible event!"));
        reviewDAO.save(new Review(reviewDAO.nextId(), 1, "Worst event ever!"));

        // ATTACH REVIEWS TO EVENTS
        eventDAO.find(1).addReview(reviewDAO.find(1));
        eventDAO.find(1).addReview(reviewDAO.find(2));
        eventDAO.find(2).addReview(reviewDAO.find(3));
        eventDAO.find(2).addReview(reviewDAO.find(4));
        eventDAO.find(3).addReview(reviewDAO.find(5));
        eventDAO.find(4).addReview(reviewDAO.find(6));
        eventDAO.find(5).addReview(reviewDAO.find(7));
        eventDAO.find(6).addReview(reviewDAO.find(8));
        eventDAO.find(7).addReview(reviewDAO.find(9));
        eventDAO.find(8).addReview(reviewDAO.find(10));

        reviewDAO.find(1).setCustomer(customerDAO.find(4));
        reviewDAO.find(2).setCustomer(customerDAO.find(5));
        reviewDAO.find(3).setCustomer(customerDAO.find(4));
        reviewDAO.find(4).setCustomer(customerDAO.find(5));
        reviewDAO.find(5).setCustomer(customerDAO.find(4));
        reviewDAO.find(6).setCustomer(customerDAO.find(5));
        reviewDAO.find(7).setCustomer(customerDAO.find(4));
        reviewDAO.find(8).setCustomer(customerDAO.find(4));
        reviewDAO.find(9).setCustomer(customerDAO.find(5));
        reviewDAO.find(10).setCustomer(customerDAO.find(4));
    }

    // DAO FACTORY
    public abstract UserDAO getUserDAO();

    public abstract OrganizerDAO getOrganizerDAO();

    public abstract CustomerDAO getCustomerDAO();

    public abstract EventDAO getEventDAO();

    public abstract ReviewDAO getReviewDAO();

    public abstract TicketDAO getTicketDAO();

    public abstract PurchaseDAO getPurchaseDAO();

    public abstract TicketCategoryDAO getTicketCategoryDAO();

    public abstract TicketDiscountDAO getTicketDiscountDAO();
}
