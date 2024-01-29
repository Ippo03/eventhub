package com.example.eventhub.helper;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.EventType;
import com.example.eventhub.domain.Genre;
import com.example.eventhub.domain.Review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventTestHelper {
    public static Event initEvent() {
        Address eventAddress = com.example.eventhub.helper.AddressTestHelper.initAddress1();
        LocalDateTime eventDateTime = LocalDateTime.of(2023, 12, 12, 12, 12);
        Event event = new Event(1,"RockNRoll", eventAddress, eventDateTime, Genre.MUSIC, EventType.OPEN);
        event.setTicketCategories(com.example.eventhub.helper.TicketCategoryTestHelper.initArrayOfTicketCategories());
        event.setTicketDiscounts(com.example.eventhub.helper.TicketDiscountTestHelper.initArrayOfTicketDiscounts());
        return event;
    }

    public static Event initEvent2() {
        Address eventAddress = AddressTestHelper.initAddress2();
        LocalDateTime eventDateTime = LocalDateTime.of(2024, 06, 04, 12, 12);
        Event event = new Event(2,"AI Conference", eventAddress, eventDateTime, Genre.EDUCATION, EventType.OPEN);
        event.setTicketCategories(TicketCategoryTestHelper.initArrayOfTicketCategories());
        event.setTicketDiscounts(TicketDiscountTestHelper.initArrayOfTicketDiscounts());
        return event;
    }

    public static Event initEventWithReviews() {
        Event event = initEvent();
        Review[] reviews = ReviewTestHelper.initReviews();
        for (Review review : reviews) {
            event.addReview(review);
        }
        return event;
    }

    public static List<Event> initAllEvents() {
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

        Event event1 = new Event(1, "Taste of the World", address8, LocalDateTime.of(2023, 05, 12, 19, 0),
                Genre.FOOD, EventType.OPEN);
        Event event2 = new Event(2, "House Party", address7, LocalDateTime.of(2023, 06, 04, 12, 12),
                Genre.MUSIC, EventType.OPEN);
        Event event3 = new Event(3, "Rock Festival", address9, LocalDateTime.of(2023, 06, 25, 15, 30),
                Genre.MUSIC, EventType.CLOSED);
        Event event4 = new Event(4, "Football Championship", address10, LocalDateTime.of(2023, 07, 8, 18, 0),
                Genre.SPORTS, EventType.OPEN);
        Event event5 = new Event(5, "Modern Art Exhibition", address11, LocalDateTime.of(2023, 8, 14, 14, 0),
                Genre.ART, EventType.CLOSED);
        Event event6 = new Event(6, "Science Fair", address12, LocalDateTime.of(2023, 9, 30, 10, 0),
                Genre.SCIENCE, EventType.OPEN);
        Event event7 = new Event(7, "Film Festival", address13, LocalDateTime.of(2023, 10, 15, 17, 45),
                Genre.CINEMA, EventType.CLOSED);
        Event event8 = new Event(8, "Educational Workshop", address14, LocalDateTime.of(2023, 11, 22, 13, 30),
                Genre.EDUCATION, EventType.OPEN);
        Event event9 = new Event(9, "Business Networking Event", address15, LocalDateTime.of(2023, 12, 05, 20, 15),
                Genre.BUSINESS, EventType.CLOSED);
        Event event10 = new Event(10, "Gastronomy Masterclass", address16, LocalDateTime.of(2024, 02, 28, 14, 45),
                Genre.FOOD, EventType.CLOSED);
        Event event11 = new Event(11, "Classical Music Concert", address17, LocalDateTime.of(2024, 03, 10, 19, 30),
                Genre.MUSIC, EventType.OPEN);
        Event event12 = new Event(12, "Basketball Tournament", address18, LocalDateTime.of(2024, 04, 22, 17, 0),
                Genre.SPORTS, EventType.CLOSED);
        Event event13 = new Event(13, "Photography Exhibition", address19, LocalDateTime.of(2024, 05, 07, 15, 15),
                Genre.ART, EventType.OPEN);
        Event event14 = new Event(14, "Technology Symposium", address20, LocalDateTime.of(2024, 06, 20, 11, 30),
                Genre.SCIENCE, EventType.CLOSED);
        Event event15 = new Event(15, "Independent Film Showcase", address21, LocalDateTime.of(2024, 07, 04, 16, 45),
                Genre.CINEMA, EventType.OPEN);
        Event event16 = new Event(16, "Language Learning Workshop", address22, LocalDateTime.of(2024, 8, 15, 14, 0),
                Genre.EDUCATION, EventType.CLOSED);
        Event event17 = new Event(17, "Startup Pitch Night", address23, LocalDateTime.of(2024, 9, 28, 20, 30),
                Genre.BUSINESS, EventType.OPEN);
        Event event18 = new Event(18, "Community Gathering", address24, LocalDateTime.of(2024, 10, 12, 18, 15),
                Genre.OTHER, EventType.CLOSED);
        Event event19 = new Event(19, "Street Food Festival", address25, LocalDateTime.of(2024, 11, 25, 17, 0),
                Genre.FOOD, EventType.OPEN);
        Event event20 = new Event(20, "ThinkBiz Academy", address26, LocalDateTime.of(2024, 12, 10, 20, 30),
                Genre.EDUCATION, EventType.CLOSED);

        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);
        events.add(event5);
        events.add(event6);
        events.add(event7);
        events.add(event8);
        events.add(event9);
        events.add(event10);
        events.add(event11);
        events.add(event12);
        events.add(event13);
        events.add(event14);
        events.add(event15);
        events.add(event16);
        events.add(event17);
        events.add(event18);
        events.add(event19);
        events.add(event20);

        return events;
    }

    public static List<Event> initEventsWithTheMostTicketsSold() {
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
//        Address address17 = new Address("Ermoupolis", 65, "Athens", "11128", "Greece");
//        Address address18 = new Address("Monastiraki", 70, "Athens", "11129", "Greece");
//        Address address19 = new Address("Dionysiou Areopagitou", 12, "Athens", "11130", "Greece");
//        Address address20 = new Address("Plaka", 8, "Athens", "11131", "Greece");
//        Address address21 = new Address("Thiseio", 14, "Athens", "11132", "Greece");
//        Address address22 = new Address("Mavili Square", 19, "Athens", "11133", "Greece");
//        Address address23 = new Address("Kolonaki", 24, "Athens", "11134", "Greece");
//        Address address24 = new Address("Exarchia", 29, "Athens", "11135", "Greece");
//        Address address25 = new Address("Koukaki", 34, "Athens", "11136", "Greece");
//        Address address26 = new Address("Kerameikos", 39, "Athens", "11137", "Greece");

        Event event1 = new Event(1, "Taste of the World", address8, LocalDateTime.of(2023, 05, 12, 19, 0),
                Genre.FOOD, EventType.OPEN);
        Event event2 = new Event(2, "House Party", address7, LocalDateTime.of(2023, 06, 04, 12, 12),
                Genre.MUSIC, EventType.OPEN);
        Event event3 = new Event(3, "Rock Festival", address9, LocalDateTime.of(2023, 06, 25, 15, 30),
                Genre.MUSIC, EventType.CLOSED);
        Event event4 = new Event(4, "Football Championship", address10, LocalDateTime.of(2023, 07, 8, 18, 0),
                Genre.SPORTS, EventType.OPEN);
        Event event5 = new Event(5, "Modern Art Exhibition", address11, LocalDateTime.of(2023, 8, 14, 14, 0),
                Genre.ART, EventType.CLOSED);
        Event event6 = new Event(6, "Science Fair", address12, LocalDateTime.of(2023, 9, 30, 10, 0),
                Genre.SCIENCE, EventType.OPEN);
        Event event7 = new Event(7, "Film Festival", address13, LocalDateTime.of(2023, 10, 15, 17, 45),
                Genre.CINEMA, EventType.CLOSED);
        Event event8 = new Event(8, "Educational Workshop", address14, LocalDateTime.of(2023, 11, 22, 13, 30),
                Genre.EDUCATION, EventType.OPEN);
        Event event9 = new Event(9, "Business Networking Event", address15, LocalDateTime.of(2023, 12, 05, 20, 15),
                Genre.BUSINESS, EventType.CLOSED);
        Event event10 = new Event(10, "Gastronomy Masterclass", address16, LocalDateTime.of(2024, 02, 28, 14, 45),
                Genre.FOOD, EventType.CLOSED);
//        Event event11 = new Event(11, "Classical Music Concert", address17, LocalDateTime.of(2025, 03, 10, 19, 30),
//                Genre.MUSIC, EventType.OPEN);
//        Event event12 = new Event(12, "Basketball Tournament", address18, LocalDateTime.of(2025, 04, 22, 17, 0),
//                Genre.SPORTS, EventType.CLOSED);
//        Event event13 = new Event(13, "Photography Exhibition", address19, LocalDateTime.of(2025, 05, 07, 15, 15),
//                Genre.ART, EventType.OPEN);
//        Event event14 = new Event(14, "Technology Symposium", address20, LocalDateTime.of(2025, 06, 20, 11, 30),
//                Genre.SCIENCE, EventType.CLOSED);
//        Event event15 = new Event(15, "Independent Film Showcase", address21, LocalDateTime.of(2025, 07, 04, 16, 45),
//                Genre.CINEMA, EventType.OPEN);
//        Event event16 = new Event(16, "Language Learning Workshop", address22, LocalDateTime.of(2025, 8, 15, 14, 0),
//                Genre.EDUCATION, EventType.CLOSED);
//        Event event17 = new Event(17, "Startup Pitch Night", address23, LocalDateTime.of(2025, 9, 28, 20, 30),
//                Genre.BUSINESS, EventType.OPEN);
//        Event event18 = new Event(18, "Community Gathering", address24, LocalDateTime.of(2025, 10, 12, 18, 15),
//                Genre.OTHER, EventType.CLOSED);
//        Event event19 = new Event(19, "Street Food Festival", address25, LocalDateTime.of(2025, 11, 25, 17, 0),
//                Genre.FOOD, EventType.OPEN);
//        Event event20 = new Event(20, "ThinkBiz Academy", address26, LocalDateTime.of(2025, 12, 10, 20, 30),
//                Genre.EDUCATION, EventType.CLOSED);

        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);
        events.add(event5);
        events.add(event6);
        events.add(event7);
        events.add(event8);

        return events;
    }

    public static List<Event> initEventsWithTheMostEventCapacity() {
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

        Event event1 = new Event(1, "Taste of the World", address8, LocalDateTime.of(2023, 05, 12, 19, 0),
                Genre.FOOD, EventType.OPEN);
        Event event2 = new Event(2, "House Party", address7, LocalDateTime.of(2023, 06, 04, 12, 12),
                Genre.MUSIC, EventType.OPEN);
        Event event3 = new Event(3, "Rock Festival", address9, LocalDateTime.of(2023, 06, 25, 15, 30),
                Genre.MUSIC, EventType.CLOSED);
        Event event4 = new Event(4, "Football Championship", address10, LocalDateTime.of(2023, 07, 8, 18, 0),
                Genre.SPORTS, EventType.OPEN);
        Event event5 = new Event(5, "Modern Art Exhibition", address11, LocalDateTime.of(2023, 8, 14, 14, 0),
                Genre.ART, EventType.CLOSED);
        Event event6 = new Event(6, "Science Fair", address12, LocalDateTime.of(2023, 9, 30, 10, 0),
                Genre.SCIENCE, EventType.OPEN);
        Event event7 = new Event(7, "Film Festival", address13, LocalDateTime.of(2023, 10, 15, 17, 45),
                Genre.CINEMA, EventType.CLOSED);
        Event event8 = new Event(8, "Educational Workshop", address14, LocalDateTime.of(2023, 11, 22, 13, 30),
                Genre.EDUCATION, EventType.OPEN);
        Event event9 = new Event(9, "Business Networking Event", address15, LocalDateTime.of(2023, 12, 05, 20, 15),
                Genre.BUSINESS, EventType.CLOSED);
        Event event10 = new Event(10, "Gastronomy Masterclass", address16, LocalDateTime.of(2024, 02, 28, 14, 45),
                Genre.FOOD, EventType.CLOSED);

        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);
        events.add(event5);
        events.add(event6);
        events.add(event7);
        events.add(event8);

        return events;
    }

    public static List<Event> initEventsWithTheMostAvailableTickets() {
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

        Event event1 = new Event(1, "Taste of the World", address8, LocalDateTime.of(2023, 05, 12, 19, 0),
                Genre.FOOD, EventType.OPEN);
        Event event2 = new Event(2, "House Party", address7, LocalDateTime.of(2023, 06, 04, 12, 12),
                Genre.MUSIC, EventType.OPEN);
        Event event3 = new Event(3, "Rock Festival", address9, LocalDateTime.of(2023, 06, 25, 15, 30),
                Genre.MUSIC, EventType.CLOSED);
        Event event4 = new Event(4, "Football Championship", address10, LocalDateTime.of(2023, 07, 8, 18, 0),
                Genre.SPORTS, EventType.OPEN);
        Event event5 = new Event(5, "Modern Art Exhibition", address11, LocalDateTime.of(2023, 8, 14, 14, 0),
                Genre.ART, EventType.CLOSED);
        Event event6 = new Event(6, "Science Fair", address12, LocalDateTime.of(2023, 9, 30, 10, 0),
                Genre.SCIENCE, EventType.OPEN);
        Event event7 = new Event(7, "Film Festival", address13, LocalDateTime.of(2023, 10, 15, 17, 45),
                Genre.CINEMA, EventType.CLOSED);
        Event event8 = new Event(8, "Educational Workshop", address14, LocalDateTime.of(2023, 11, 22, 13, 30),
                Genre.EDUCATION, EventType.OPEN);
        Event event9 = new Event(9, "Business Networking Event", address15, LocalDateTime.of(2023, 12, 05, 20, 15),
                Genre.BUSINESS, EventType.CLOSED);
        Event event10 = new Event(10, "Gastronomy Masterclass", address16, LocalDateTime.of(2024, 02, 28, 14, 45),
                Genre.FOOD, EventType.CLOSED);

        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);
        events.add(event5);
        events.add(event6);
        events.add(event7);
        events.add(event8);

        return events;
    }

    public static List<Event> initEventsSortedByRating() {
        List<Event> events = EventTestHelper.initAllEvents();

        // reverse the firts with the second
        Event event1 = events.get(0);
        Event event2 = events.get(1);
        events.set(0, event2);
        events.set(1, event1);

        return events;
    }

    public static List<Event> initEventsSortedByCloserDate() {
        List<Event> events = EventTestHelper.initAllEvents();

        return events;
    }

    public static List<Event> initEventsSortedByFartherDate() {
        List<Event> events = EventTestHelper.initAllEvents();

        // reserve the whole list
        for (int i = 0; i < events.size() / 2; i++) {
            Event event1 = events.get(i);
            Event event2 = events.get(events.size() - 1 - i);
            events.set(i, event2);
            events.set(events.size() - 1 - i, event1);
        }

        return events;
    }

    public static List<Event> initEventsSortedByEventCapacity() {
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

        Event event1 = new Event(1, "House Party", address7, LocalDateTime.of(2023, 06, 04, 12, 12),
                Genre.MUSIC, EventType.OPEN);
        Event event2 = new Event(2, "Taste of the World", address8, LocalDateTime.of(2023, 05, 12, 19, 0),
                Genre.FOOD, EventType.OPEN);
        Event event3 = new Event(3, "Rock Festival", address9, LocalDateTime.of(2023, 06, 25, 15, 30),
                Genre.MUSIC, EventType.CLOSED);
        Event event4 = new Event(4, "Football Championship", address10, LocalDateTime.of(2023, 07, 8, 18, 0),
                Genre.SPORTS, EventType.OPEN);
        Event event5 = new Event(5, "Modern Art Exhibition", address11, LocalDateTime.of(2023, 8, 14, 14, 0),
                Genre.ART, EventType.CLOSED);
        Event event6 = new Event(6, "Science Fair", address12, LocalDateTime.of(2023, 9, 30, 10, 0),
                Genre.SCIENCE, EventType.OPEN);
        Event event7 = new Event(7, "Film Festival", address13, LocalDateTime.of(2023, 10, 15, 17, 45),
                Genre.CINEMA, EventType.FREE);
        Event event8 = new Event(8, "Educational Workshop", address14, LocalDateTime.of(2023, 11, 22, 13, 30),
                Genre.EDUCATION, EventType.OPEN);
        Event event9 = new Event(9, "Business Networking Event", address15, LocalDateTime.of(2023, 12, 05, 20, 15),
                Genre.BUSINESS, EventType.FREE);
        Event event10 = new Event(10, "Gastronomy Masterclass", address16, LocalDateTime.of(2024, 02, 28, 14, 45),
                Genre.FOOD, EventType.CLOSED);
        Event event11 = new Event(11, "Classical Music Concert", address17, LocalDateTime.of(2024, 03, 10, 19, 30),
                Genre.MUSIC, EventType.OPEN);
        Event event12 = new Event(12, "Basketball Tournament", address18, LocalDateTime.of(2024, 04, 22, 17, 0),
                Genre.SPORTS, EventType.FREE);
        Event event13 = new Event(13, "Photography Exhibition", address19, LocalDateTime.of(2024, 05, 07, 15, 15),
                Genre.ART, EventType.OPEN);
        Event event14 = new Event(14, "Technology Symposium", address20, LocalDateTime.of(2024, 06, 20, 11, 30),
                Genre.SCIENCE, EventType.CLOSED);
        Event event15 = new Event(15, "Independent Film Showcase", address21, LocalDateTime.of(2024, 07, 04, 16, 45),
                Genre.CINEMA, EventType.OPEN);
        Event event16 = new Event(16, "Language Learning Workshop", address22, LocalDateTime.of(2024, 8, 15, 14, 0),
                Genre.EDUCATION, EventType.CLOSED);
        Event event17 = new Event(17, "Startup Pitch Night", address23, LocalDateTime.of(2024, 9, 28, 20, 30),
                Genre.BUSINESS, EventType.FREE);
        Event event18 = new Event(18, "Community Gathering", address24, LocalDateTime.of(2024, 10, 12, 18, 15),
                Genre.OTHER, EventType.CLOSED);
        Event event19 = new Event(19, "Street Food Festival", address25, LocalDateTime.of(2024, 11, 25, 17, 0),
                Genre.FOOD, EventType.OPEN);
        Event event20 = new Event(20, "ThinkBiz Academy", address26, LocalDateTime.of(2024, 12, 10, 20, 30),
                Genre.EDUCATION, EventType.FREE);

        ArrayList<Event> events = new ArrayList<>();

        events.add(event18);
        events.add(event1);
        events.add(event7);
        events.add(event9);
        events.add(event12);
        events.add(event17);
        events.add(event19);
        events.add(event20);
        events.add(event6);
        events.add(event3);
        events.add(event4);
        events.add(event2);
        events.add(event8);
        events.add(event14);
        events.add(event5);
        events.add(event13);
        events.add(event11);
        events.add(event10);
        events.add(event15);
        events.add(event16);

        return events;

    }

    public static ArrayList<Event> initEventsSortedByTicketsSold() {
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

        Event event1 = new Event(1, "House Party", address7, LocalDateTime.of(2023, 06, 04, 12, 12),
                Genre.MUSIC, EventType.OPEN);
        Event event2 = new Event(2, "Taste of the World", address8, LocalDateTime.of(2023, 05, 12, 19, 0),
                Genre.FOOD, EventType.OPEN);
        Event event3 = new Event(3, "Rock Festival", address9, LocalDateTime.of(2023, 06, 25, 15, 30),
                Genre.MUSIC, EventType.CLOSED);
        Event event4 = new Event(4, "Football Championship", address10, LocalDateTime.of(2023, 07, 8, 18, 0),
                Genre.SPORTS, EventType.OPEN);
        Event event5 = new Event(5, "Modern Art Exhibition", address11, LocalDateTime.of(2023, 8, 14, 14, 0),
                Genre.ART, EventType.CLOSED);
        Event event6 = new Event(6, "Science Fair", address12, LocalDateTime.of(2023, 9, 30, 10, 0),
                Genre.SCIENCE, EventType.OPEN);
        Event event7 = new Event(7, "Film Festival", address13, LocalDateTime.of(2023, 10, 15, 17, 45),
                Genre.CINEMA, EventType.FREE);
        Event event8 = new Event(8, "Educational Workshop", address14, LocalDateTime.of(2023, 11, 22, 13, 30),
                Genre.EDUCATION, EventType.OPEN);
        Event event9 = new Event(9, "Business Networking Event", address15, LocalDateTime.of(2023, 12, 05, 20, 15),
                Genre.BUSINESS, EventType.FREE);
        Event event10 = new Event(10, "Gastronomy Masterclass", address16, LocalDateTime.of(2024, 02, 28, 14, 45),
                Genre.FOOD, EventType.CLOSED);
        Event event11 = new Event(11, "Classical Music Concert", address17, LocalDateTime.of(2024, 03, 10, 19, 30),
                Genre.MUSIC, EventType.OPEN);
        Event event12 = new Event(12, "Basketball Tournament", address18, LocalDateTime.of(2024, 04, 22, 17, 0),
                Genre.SPORTS, EventType.FREE);
        Event event13 = new Event(13, "Photography Exhibition", address19, LocalDateTime.of(2024, 05, 07, 15, 15),
                Genre.ART, EventType.OPEN);
        Event event14 = new Event(14, "Technology Symposium", address20, LocalDateTime.of(2024, 06, 20, 11, 30),
                Genre.SCIENCE, EventType.CLOSED);
        Event event15 = new Event(15, "Independent Film Showcase", address21, LocalDateTime.of(2024, 07, 04, 16, 45),
                Genre.CINEMA, EventType.OPEN);
        Event event16 = new Event(16, "Language Learning Workshop", address22, LocalDateTime.of(2024, 8, 15, 14, 0),
                Genre.EDUCATION, EventType.CLOSED);
        Event event17 = new Event(17, "Startup Pitch Night", address23, LocalDateTime.of(2024, 9, 28, 20, 30),
                Genre.BUSINESS, EventType.FREE);
        Event event18 = new Event(18, "Community Gathering", address24, LocalDateTime.of(2024, 10, 12, 18, 15),
                Genre.OTHER, EventType.CLOSED);
        Event event19 = new Event(19, "Street Food Festival", address25, LocalDateTime.of(2024, 11, 25, 17, 0),
                Genre.FOOD, EventType.OPEN);
        Event event20 = new Event(20, "ThinkBiz Academy", address26, LocalDateTime.of(2024, 12, 10, 20, 30),
                Genre.EDUCATION, EventType.FREE);

        ArrayList<Event> events = new ArrayList<>();
        events.add(event2);
        events.add(event18);
        events.add(event19);
        events.add(event11);
        events.add(event15);
        events.add(event1);
        events.add(event3);
        events.add(event4);
        events.add(event6);
        events.add(event8);
        events.add(event10);
        events.add(event13);
        events.add(event5);
        events.add(event7);
        events.add(event9);
        events.add(event16);
        events.add(event12);
        events.add(event14);
        events.add(event17);
        events.add(event20);

        return events;
    }


}