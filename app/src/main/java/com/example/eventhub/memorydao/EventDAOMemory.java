package com.example.eventhub.memorydao;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.EventType;
import com.example.eventhub.domain.Genre;
import com.example.eventhub.domain.Interest;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EventDAOMemory implements EventDAO {
    protected static ArrayList<Event> events = new ArrayList<>();

    @Override
    public void delete(Event event) {
        events.remove(event);
    }

    @Override
    public ArrayList<Event> findAll() {
        ArrayList result = new ArrayList<>();
        result.addAll(events);
        return result;
    }

    @Override
    public void save(Event event) {
        events.add(event);
    }

    @Override
    public Event find(int id) {
        for (Event event : events) {
            if (event.getEventId() == id) {
                return event;
            }
        }
        return null;
    }

    @Override
    public int nextId() {
        return events.size() == 0 ? 1 : events.get(events.size() - 1).getEventId() + 1;
    }

    @Override
    public void update(Event event, HashSet<TicketCategory> ticketCategories, HashSet<TicketDiscount> ticketDiscounts) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getEventId().equals(event.getEventId())) {
                events.get(i).setTicketCategories(new ArrayList<>(ticketCategories));
                events.get(i).setTicketDiscounts(new ArrayList<>(ticketDiscounts));
                events.get(i).setTicketCategoryCountMap();
                events.set(i, event);

                break;
            }
        }
    }

    @SuppressLint("NewApi")
    @Override
    public ArrayList<Event> findAllActive() {
        ArrayList<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (event.getDate().isAfter(LocalDateTime.now())) {
                result.add(event);
            }
        }
        return result;
    }

    @Override
    public Event findAlreadyExisting(Event event) {
        for (Event event1 : events) {
            if (event1.getName().equals(event.getName())
                    && event1.getLocation().equals(event.getLocation())
                    && event1.getDate().equals(event.getDate())
                    && event1.getEventId() != event.getEventId()) {
                return event1;
            }
        }
        return null;
    }

    @SuppressLint("NewApi")
    @Override
    public Event findByName(String name) {
        for (Event event : events) {
            if (event.getName().equals(name) && event.getDate().isAfter(LocalDateTime.now())) {
                return event;
            }
        }
        return null;
    }

    @SuppressLint("NewApi")
    public Set<Event> findByText(String text) {
        Set<Event> result = new HashSet<>();

        for (Event event : events) {
            if (event.getName().contains(text) && event.getDate().isAfter(LocalDateTime.now())) {
                result.add(event);
            }
        }

        return result;
    }

    @SuppressLint("NewApi")
    @Override
    public ArrayList<Event> findByGenre(ArrayList<Genre> genres) {
        ArrayList<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (genres.contains(event.getGenre()) && event.getDate().isAfter(LocalDateTime.now())) {
                result.add(event);
            }
        }
        return result;
    }

    @SuppressLint("NewApi")
    @Override
    public ArrayList<Event> findByGenreFirst(ArrayList<Genre> genres) {
        ArrayList<Event> result = new ArrayList<>();
        result = findByGenre(genres);

        for (Event event : events) {
            if (!result.contains(event) && event.getDate().isAfter(LocalDateTime.now())) {
                result.add(event);
            }
        }
        return result;
    }

    @SuppressLint("NewApi")
    @Override
    public Set<Event> findByType(ArrayList<EventType> types) {
        Set<Event> result = new HashSet<>();
        for (Event event : events) {
            if (types.contains(event.getType()) && event.getDate().isAfter(LocalDateTime.now())) {
                result.add(event);
            }
        }
        return result;
    }

    // find evet inside the [from, to]
    @SuppressLint("NewApi")
    @Override
    public Set<Event> findByDateRange(LocalDate from, LocalDate to) {
        Set<Event> result = new HashSet<>();
        for (Event event : events) {
            if (event.getDate().toLocalDate().isAfter(from) && event.getDate().toLocalDate().isBefore(to)) {
                result.add(event);
            }
        }
        return result;
    }

    // need better implementation
    @SuppressLint("NewApi")
    @Override
    public Set<Event> findByCustomerInterests(Set<Interest> interests) {
        Set<Genre> genres = new HashSet<>();
        for (Interest interest : interests) {
            genres.add(interest.getInterestName());
        }

        Set<Event> result = new HashSet<>();
        for (Event event : events) {
            if (genres.contains(event.getGenre()) && event.getDate().isAfter(LocalDateTime.now())) {
                result.add(event);
            }
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public ArrayList<Event> sortEventsByCapacity(ArrayList<Event> events) {
        events.sort((event1, event2) -> event2.getEventCapacity() - event1.getEventCapacity());
        return events;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public ArrayList<Event> sortEventsByTicketsSold(ArrayList<Event> events) {
        events.sort((event1, event2) -> event2.getTicketsSold() - event1.getTicketsSold());
        return events;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public ArrayList<Event> sortEventsByRating(ArrayList<Event> events) {
        events.sort((event1, event2) ->  Double.compare(event2.getAverageRating(), event1.getAverageRating()));
        return events;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ArrayList<Event> sortEventsByCloserDate(ArrayList<Event> events) {
        events.sort((event1, event2) -> event1.getDate().compareTo(event2.getDate()));
        return events;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ArrayList<Event> sortEventsByFurtherDate(ArrayList<Event> events) {
        events.sort((event1, event2) -> event2.getDate().compareTo(event1.getDate()));
        return events;
    }
}
