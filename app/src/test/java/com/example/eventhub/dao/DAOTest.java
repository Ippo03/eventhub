package com.example.eventhub.dao;

import com.example.eventhub.contacts.Address;
import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.dao.EventDAO;
import com.example.eventhub.dao.Initializer;
import com.example.eventhub.dao.OrganizerDAO;
import com.example.eventhub.dao.PurchaseDAO;
import com.example.eventhub.dao.ReviewDAO;
import com.example.eventhub.dao.TicketCategoryDAO;
import com.example.eventhub.dao.TicketDAO;
import com.example.eventhub.dao.TicketDiscountDAO;
import com.example.eventhub.dao.UserDAO;
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
import com.example.eventhub.domain.User;
import com.example.eventhub.helper.EventTestHelper;
import com.example.eventhub.memorydao.CustomerDAOMemory;
import com.example.eventhub.memorydao.EventDAOMemory;
import com.example.eventhub.memorydao.MemoryInitializer;
import com.example.eventhub.memorydao.OrganizerDAOMemory;
import com.example.eventhub.memorydao.PurchaseDAOMemory;
import com.example.eventhub.memorydao.ReviewDAOMemory;
import com.example.eventhub.memorydao.TicketCategoryDAOMemory;
import com.example.eventhub.memorydao.TicketDAOMemory;
import com.example.eventhub.memorydao.TicketDiscountDAOMemory;
import com.example.eventhub.memorydao.UserDAOMemory;
import com.example.eventhub.util.Money;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DAOTest {
    private UserDAO userDAO;
    private OrganizerDAO organizerDAO;
    private CustomerDAO customerDAO;
    private EventDAO eventDAO;
    private ReviewDAO reviewDAO;
    private TicketCategoryDAO ticketCategoryDAO;
    private TicketDiscountDAO ticketDiscountDAO;
    private TicketDAO ticketDAO;
    private PurchaseDAO purchaseDAO;

    private static final Integer INITIAL_USER_COUNT = 6;
    private static final Integer INITIAL_ORGANIZER_COUNT = 3;
    private static final Integer INITIAL_CUSTOMER_COUNT = 3;
    private static final Integer INITIAL_EVENT_COUNT = 20;
    private static final Integer INITIAL_REVIEW_COUNT = 10;
    private static final Integer INITIAL_TICKET_CATEGORY_COUNT = 31;
    private static final Integer INITIAL_TICKET_DISCOUNT_COUNT = 31;
    private static final Integer INITIAL_TICKET_COUNT = 58;
    private static final Integer INITIAL_PURCHASE_COUNT = 28;

    private Address address;
    private Set<Interest> interests = new HashSet<>();
    @Before
    public void setUp() {
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        userDAO = new UserDAOMemory();
        organizerDAO = new OrganizerDAOMemory();
        customerDAO = new CustomerDAOMemory();
        eventDAO = new EventDAOMemory();
        reviewDAO = new ReviewDAOMemory();
        ticketCategoryDAO = new TicketCategoryDAOMemory();
        ticketDiscountDAO = new TicketDiscountDAOMemory();
        ticketDAO = new TicketDAOMemory();
        purchaseDAO = new PurchaseDAOMemory();

        address = new Address("Acharnon", 37, "Athens", "10433", "Greece");
        interests.add(new Interest(Genre.CINEMA));
    }

    /**
     * Test case to ensure that an existing user can be successfully retrieved.
     */
    @Test
    public void findExistingUser() {
        Assertions.assertNotNull(userDAO.find(1));
    }

    /**
     * Test case to verify that attempting to find a non-existing user returns null.
     */
    @Test
    public void findNonExistingUser() {
        Assertions.assertNull(userDAO.find(4000));
    }

    /**
     * Test case to ensure that an existing organizer can be successfully retrieved.
     */
    @Test
    public void findExistingOrganizer() {
        Assertions.assertNotNull(organizerDAO.find(1));
    }

    /**
     * Test case to verify that attempting to find a non-existing organizer returns null.
     */
    @Test
    public void findNonExistingOrganizer() {
        Assertions.assertNull(organizerDAO.find(4000));
    }

    /**
     * Test case to ensure that an existing customer can be successfully retrieved.
     */
    @Test
    public void findExistingCustomer() {
        Assertions.assertNotNull(customerDAO.find(4));
    }

    /**
     * Test case to verify that attempting to find a non-existing customer returns null.
     */
    @Test
    public void findNonExistingCustomer() {
        Assertions.assertNull(customerDAO.find(4000));
    }

    /**
     * Test case to ensure that an existing event can be successfully retrieved.
     */
    @Test
    public void findExistingEvent() {
        Assertions.assertNotNull(eventDAO.find(1));
    }

    /**
     * Test case to verify that attempting to find a non-existing event returns null.
     */
    @Test
    public void findNonExistingEvent() {
        Assertions.assertNull(eventDAO.find(4000));
    }

    /**
     * Test case to ensure that an existing review can be successfully retrieved.
     */
    @Test
    public void findExistingReview() {
        Assertions.assertNotNull(reviewDAO.find(1));
    }

    /**
     * Test case to verify that attempting to find a non-existing review returns null.
     */
    @Test
    public void findNonExistingReview() {
        Assertions.assertNull(reviewDAO.find(4000));
    }

    /**
     * Test case to ensure that an existing ticket category can be successfully retrieved.
     */
    @Test
    public void findExistingTicketCategory() {
        Assertions.assertNotNull(ticketCategoryDAO.find(1));
    }

    /**
     * Test case to verify that attempting to find a non-existing ticket category returns null.
     */
    @Test
    public void findNonExistingTicketCategory() {
        Assertions.assertNull(ticketCategoryDAO.find(4000));
    }

    /**
     * Test case to ensure that an existing ticket discount can be successfully retrieved.
     */
    @Test
    public void findExistingTicketDiscount() {
        Assertions.assertNotNull(ticketDiscountDAO.find(1));
    }

    /**
     * Test case to verify that attempting to find a non-existing ticket discount returns null.
     */
    @Test
    public void findNonExistingTicketDiscount() {
        Assertions.assertNull(ticketDiscountDAO.find(4000));
    }

    /**
     * Test case to ensure that an existing ticket can be successfully retrieved.
     */
    @Test
    public void findExistingTicket() {
        Assertions.assertNotNull(ticketDAO.find(1));
    }

    /**
     * Test case to verify that attempting to find a non-existing ticket returns null.
     */
    @Test
    public void findNonExistingTicket() {
        Assertions.assertNull(ticketDAO.find(4000));
    }

    /**
     * Test case to ensure that an existing purchase can be successfully retrieved.
     */
    @Test
    public void findExistingPurchase() {
        Assertions.assertNotNull(purchaseDAO.find(1));
    }

    /**
     * Test case to verify that attempting to find a non-existing purchase returns null.
     */
    @Test
    public void findNonExistingPurchase() {
        Assertions.assertNull(purchaseDAO.find(4000));
    }

    /**
     * Test case to ensure that an authorized organizer can be successfully found by credentials.
     */
    @Test
    public void findAuthOrganizer() {
        Assertions.assertNotNull(organizerDAO.findByCredentials("jakesmith@example.com", "12435687"));
    }

    /**
     * Test case to verify that attempting to find a non-authorized organizer by credentials returns null.
     */
    @Test
    public void findNonAuthOrganizer() {
        Assertions.assertNull(organizerDAO.findByCredentials("ippo@example.com", "password"));
    }

    /**
     * Test case to ensure that an authorized customer can be successfully found by credentials.
     */
    @Test
    public void findAuthCustomer() {
        Assertions.assertNotNull(customerDAO.findByCredentials("alice@example.com", "45678901"));
    }

    /**
     * Test case to verify that attempting to find a non-authorized customer by credentials returns null.
     */
    @Test
    public void findNonAuthCustomer() {
        Assertions.assertNull(customerDAO.findByCredentials("greg@example.com", "password"));
    }

    /**
     * Test case to ensure that all existing users can be listed, and the count matches the expected initial count.
     */
    @Test
    public void listAllUsers() {
        List<User> allUsers = userDAO.findAll();
        Assertions.assertEquals(INITIAL_USER_COUNT, allUsers.size());
    }

    /**
     * Test case to verify that a new user can be successfully saved, and the count increases accordingly.
     */
    @Test
    public void saveUser() {
        User user = new User(userDAO.nextId(), "Nikos", "Mitsakis", "nick@example.com", 20,
                "male", address, "12345678");
        userDAO.save(user);
        List<User> users = userDAO.findAll();
        Assertions.assertEquals(INITIAL_USER_COUNT + 1, users.size());
        Assertions.assertNotNull(userDAO.find(user.getId()));
        Assertions.assertTrue(users.contains(user));
    }

    /**
     * Test case to verify that an existing user can be successfully deleted, and the count decreases accordingly.
     */
    @Test
    public void deleteUser() {
        User user = userDAO.find(1);
        userDAO.delete(user);
        List<User> users = userDAO.findAll();
        Assertions.assertEquals(INITIAL_USER_COUNT - 1, users.size());
        Assertions.assertNull(userDAO.find(1));
        Assertions.assertFalse(users.contains(user));
    }

    /**
     * Test case to ensure that all existing organizers can be listed, and the count matches the expected initial count.
     */
    @Test
    public void listAllOrganizers() {
        List<Organizer> allOrganizers = organizerDAO.findAll();
        Assertions.assertEquals(INITIAL_ORGANIZER_COUNT, allOrganizers.size());
    }

    /**
     * Test case to verify that a new organizer can be successfully saved, and the count increases accordingly.
     */
    @Test
    public void saveOrganizer() {
        Organizer organizer = new Organizer(organizerDAO.nextId(), "Ippokratis", "Pantelidis", "ippo@example.com", 20,
                "male", address, "12345678", 1234567890);
        organizerDAO.save(organizer);
        List<Organizer> organizers = organizerDAO.findAll();
        Assertions.assertEquals(INITIAL_ORGANIZER_COUNT + 1, organizers.size());
        Assertions.assertNotNull(organizerDAO.find(organizer.getId()));
        Assertions.assertTrue(organizers.contains(organizer));
    }

    /**
     * Test case to verify that an existing organizer can be successfully deleted, and the count decreases accordingly.
     */
    @Test
    public void deleteOrganizer() {
        Organizer organizer = organizerDAO.find(1);
        organizerDAO.delete(organizer);
        List<Organizer> organizers = organizerDAO.findAll();
        Assertions.assertEquals(INITIAL_ORGANIZER_COUNT - 1, organizers.size());
        Assertions.assertNull(organizerDAO.find(1));
        Assertions.assertFalse(organizers.contains(organizer));
    }

    /**
     * Test case to ensure that all existing customers can be listed, and the count matches the expected initial count.
     */
    @Test
    public void listAllCustomers() {
        List<Customer> allCustomers = customerDAO.findAll();
        Assertions.assertEquals(INITIAL_CUSTOMER_COUNT, allCustomers.size());
    }

    /**
     * Test case to verify that a new customer can be successfully saved, and the count increases accordingly.
     */
    @Test
    public void saveCustomer() {
        Customer customer = new Customer(customerDAO.nextId(), "Vaggelis", "Kampouris", "vagg@example.com", 20,
                "male", address, "12345678", interests);
        customerDAO.save(customer);
        List<Customer> customers = customerDAO.findAll();
        Assertions.assertEquals(INITIAL_CUSTOMER_COUNT + 1, customers.size());
        Assertions.assertNotNull(customerDAO.find(customer.getId()));
        Assertions.assertTrue(customers.contains(customer));
    }

    /**
     * Test case to verify that an existing customer can be successfully deleted, and the count decreases accordingly.
     */
    @Test
    public void deleteCustomer() {
        Customer customer = customerDAO.find(4);
        customerDAO.delete(customer);
        List<Customer> customers = customerDAO.findAll();
        Assertions.assertEquals(INITIAL_CUSTOMER_COUNT - 1, customers.size());
        Assertions.assertNull(customerDAO.find(4));
        Assertions.assertFalse(customers.contains(customer));
    }

    /**
     * Test case to ensure that all existing events can be listed, and the count matches the expected initial count.
     */
    @Test
    public void listAllEvents() {
        List<Event> allEvents = eventDAO.findAll();
        Assertions.assertEquals(INITIAL_EVENT_COUNT, allEvents.size());
    }

    /**
     * Test case to verify that a new event can be successfully saved, and the count increases accordingly.
     */
    @Test
    public void saveEvent() {
        Event event = new Event(eventDAO.nextId(), "Event", address, LocalDateTime.of(2023, 8, 10, 20, 30), Genre.FOOD, EventType.FREE);
        eventDAO.save(event);
        List<Event> events = eventDAO.findAll();
        Assertions.assertEquals(INITIAL_EVENT_COUNT + 1, events.size());
        Assertions.assertNotNull(eventDAO.find(event.getEventId()));
        Assertions.assertTrue(events.contains(event));
    }

    /**
     * Test case to verify that an existing event can be successfully deleted, and the count decreases accordingly.
     */
    @Test
    public void deleteEvent() {
        Event event = eventDAO.find(1);
        eventDAO.delete(event);
        List<Event> events = eventDAO.findAll();
        Assertions.assertEquals(INITIAL_EVENT_COUNT - 1, events.size());
        Assertions.assertNull(eventDAO.find(1));
        Assertions.assertFalse(events.contains(event));
    }

    /**
     * Test case to ensure that all existing reviews can be listed, and the count matches the expected initial count.
     */
    @Test
    public void listAllReviews() {
        List<Review> allReviews = reviewDAO.findAll();
        Assertions.assertEquals(INITIAL_REVIEW_COUNT, allReviews.size());
    }

    /**
     * Test case to verify that a new review can be successfully saved, and the count increases accordingly.
     */
    @Test
    public void saveReview() {
        Review review = new Review(reviewDAO.nextId(), 10, "Great");
        reviewDAO.save(review);
        List<Review> reviews = reviewDAO.findAll();
        Assertions.assertEquals(INITIAL_REVIEW_COUNT + 1, reviews.size());
        Assertions.assertNotNull(reviewDAO.find(review.getReviewId()));
        Assertions.assertTrue(reviews.contains(review));
    }

    /**
     * Test case to verify that an existing review can be successfully deleted, and the count decreases accordingly.
     */
    @Test
    public void deleteReview() {
        Review review = reviewDAO.find(1);
        reviewDAO.delete(review);
        List<Review> reviews = reviewDAO.findAll();
        Assertions.assertEquals(INITIAL_REVIEW_COUNT - 1, reviews.size());
        Assertions.assertNull(reviewDAO.find(1));
        Assertions.assertFalse(reviews.contains(review));
    }

    /**
     * Test case to ensure that all existing ticket categories can be listed, and the count matches the expected initial count.
     */
    @Test
    public void listAllTicketCategories() {
        List<TicketCategory> allTicketCategories = ticketCategoryDAO.findAll();
        Assertions.assertEquals(INITIAL_TICKET_CATEGORY_COUNT, allTicketCategories.size());
    }

    /**
     * Test case to verify that a new ticket category can be successfully saved, and the count increases accordingly.
     */
    @Test
    public void saveTicketCategory() {
        TicketCategory ticketCategory = new TicketCategory(ticketCategoryDAO.nextId(), CategoryName.GENERAL, new Money(new BigDecimal(100), Currency.getInstance("EUR")), "General", 100);
        ticketCategoryDAO.save(ticketCategory);
        List<TicketCategory> ticketCategories = ticketCategoryDAO.findAll();
        Assertions.assertEquals(INITIAL_TICKET_CATEGORY_COUNT + 1, ticketCategories.size());
        Assertions.assertNotNull(ticketCategoryDAO.find(ticketCategory.getTicketCategoryId()));
        Assertions.assertTrue(ticketCategories.contains(ticketCategory));
    }

    /**
     * Test case to verify that an existing ticket category can be successfully deleted, and the count decreases accordingly.
     */
    @Test
    public void deleteTicketCategory() {
        TicketCategory ticketCategory = ticketCategoryDAO.find(1);
        ticketCategoryDAO.delete(ticketCategory);
        for (TicketCategory ticketCategory1 : ticketCategoryDAO.findAll()) {
        }
        List<TicketCategory> ticketCategories = ticketCategoryDAO.findAll();
        Assertions.assertEquals(INITIAL_TICKET_CATEGORY_COUNT - 1, ticketCategories.size());
        Assertions.assertNull(ticketCategoryDAO.find(1));
        Assertions.assertFalse(ticketCategories.contains(ticketCategory));
    }

    /**
     * Test case to ensure that all existing ticket discounts can be listed, and the count matches the expected initial count.
     */
    @Test
    public void listAllTicketDiscounts() {
        List<TicketDiscount> allTicketDiscounts = ticketDiscountDAO.findAll();
        Assertions.assertEquals(INITIAL_TICKET_DISCOUNT_COUNT, allTicketDiscounts.size());
    }

    /**
     * Test case to verify that a new ticket discount can be successfully saved, and the count increases accordingly.
     */
    @Test
    public void saveTicketDiscount() {
        TicketDiscount ticketDiscount = new TicketDiscount(ticketDiscountDAO.nextId(), DiscountType.CHILD, 1.0);
        ticketDiscountDAO.save(ticketDiscount);
        List<TicketDiscount> ticketDiscounts = ticketDiscountDAO.findAll();
        Assertions.assertEquals(INITIAL_TICKET_DISCOUNT_COUNT + 1, ticketDiscounts.size());
        Assertions.assertNotNull(ticketDiscountDAO.find(ticketDiscount.getTicketDiscountId()));
        Assertions.assertTrue(ticketDiscounts.contains(ticketDiscount));
    }

    /**
     * Test case to verify that an existing ticket discount can be successfully deleted, and the count decreases accordingly.
     */
    @Test
    public void deleteTicketDiscount() {
        List<TicketDiscount> ticketDiscountss = ticketDiscountDAO.findAll();
        TicketDiscount ticketDiscount = ticketDiscountDAO.find(1);
        ticketDiscountDAO.delete(ticketDiscount);
        List<TicketDiscount> ticketDiscounts = ticketDiscountDAO.findAll();
        Assertions.assertEquals(INITIAL_TICKET_DISCOUNT_COUNT - 1, ticketDiscounts.size());
        Assertions.assertNull(ticketDiscountDAO.find(1));
        Assertions.assertFalse(ticketDiscounts.contains(ticketDiscount));
    }

    /**
     * Test case to ensure that all existing tickets can be listed, and the count matches the expected initial count.
     */
    @Test
    public void listAllTickets() {
        List<Ticket> allTickets = ticketDAO.findAll();
        Assertions.assertEquals(INITIAL_TICKET_COUNT, allTickets.size());
    }

    /**
     * Test case to verify that a new ticket can be successfully saved, and the count increases accordingly.
     */
    @Test
    public void saveTicket() {
        Ticket ticket = new Ticket(ticketDAO.nextId(), eventDAO.find(1), ticketCategoryDAO.find(1), ticketDiscountDAO.find(1));
        ticketDAO.save(ticket);
        List<Ticket> tickets = ticketDAO.findAll();
        Assertions.assertEquals(INITIAL_TICKET_COUNT + 1, tickets.size());
        Assertions.assertNotNull(ticketDAO.find(ticket.getTicketId()));
        Assertions.assertTrue(tickets.contains(ticket));
    }

    /**
     * Test case to verify that an existing ticket can be successfully deleted, and the count decreases accordingly.
     */
    @Test
    public void deleteTicket() {
        Ticket ticket = ticketDAO.find(1);
        ticketDAO.delete(ticket);
        List<Ticket> tickets = ticketDAO.findAll();
        Assertions.assertEquals(INITIAL_TICKET_COUNT - 1, tickets.size());
        Assertions.assertNull(ticketDAO.find(1));
        Assertions.assertFalse(tickets.contains(ticket));
    }

    /**
     * Test case to ensure that all existing purchases can be listed, and the count matches the expected initial count.
     */
    @Test
    public void listAllPurchases() {
        List<Purchase> allPurchases = purchaseDAO.findAll();
        Assertions.assertEquals(INITIAL_PURCHASE_COUNT, allPurchases.size());
    }

    /**
     * Test case to verify that a new purchase can be successfully saved, and the count increases accordingly.
     */
    @Test
    public void savePurchase() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(ticketDAO.find(1));
        Purchase purchase = new Purchase(purchaseDAO.nextId(), tickets);
        purchaseDAO.save(purchase);
        List<Purchase> purchases = purchaseDAO.findAll();
        Assertions.assertEquals(INITIAL_PURCHASE_COUNT + 1, purchases.size());
        Assertions.assertNotNull(purchaseDAO.find(purchase.getPurchaseId()));
        Assertions.assertTrue(purchases.contains(purchase));
    }

    /**
     * Test case to verify that an existing purchase can be successfully deleted, and the count decreases accordingly.
     */
    @Test
    public void deletePurchase() {
        Purchase purchase = purchaseDAO.find(1);
        purchaseDAO.delete(purchase);
        List<Purchase> purchases = purchaseDAO.findAll();
        Assertions.assertEquals(INITIAL_PURCHASE_COUNT - 1, purchases.size());
        Assertions.assertNull(purchaseDAO.find(1));
        Assertions.assertFalse(purchases.contains(purchase));
    }

    /**
     * Test case to verify that attempting to find an event by a non-existing name returns null.
     */
    @Test
    public void findEventByNonExistingName() {
        Assertions.assertNull(eventDAO.findByName("Non Existing Event"));
    }

    /**
     * Test case to ensure that events can be found by their names, and the expected events are retrieved.
     */
    @Test
    public void findEventByName() {
        Assertions.assertNull(eventDAO.findByName("House Party"));
        Assertions.assertNotNull(eventDAO.findByName("ThinkBiz Academy"));
    }

    /**
     * Test case to ensure that events can be found by their description text, and the expected count is returned.
     */
    @Test
    public void findEventByText() {
        Assertions.assertEquals(1, eventDAO.findByText("Festival").size());
    }

    /**
     * Test case to verify that attempting to find events by a non-existing genre returns an empty list.
     */
    @Test
    public void findEventByNonExistingGenre() {
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(Genre.POLITICS);
        Assertions.assertEquals(0, eventDAO.findByGenre(genres).size());
    }

    /**
     * Test case to ensure that events can be found by their genre, and the expected count is returned.
     */
    @Test
    public void findEventByGenre() {
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(Genre.FOOD);
        Assertions.assertEquals(2, eventDAO.findByGenre(genres).size());
    }

    /**
     * Test case to ensure that events can be found by their type, and the expected count is returned.
     */
    @Test
    public void findEventByType() {
        ArrayList<EventType> types = new ArrayList<>();
        types.add(EventType.OPEN);
        Assertions.assertEquals(4, eventDAO.findByType(types).size());
    }

    /**
     * Test case to verify that attempting to find events by a date range with 'from' after 'to' returns an empty set.
     */
    @Test
    public void findEventByDateRangeWithFromAfterTo() {
        LocalDate from = LocalDate.of(2021, 8, 10);
        LocalDate to = LocalDate.of(2020, 8, 10);
        Assertions.assertEquals(new HashSet<>(), eventDAO.findByDateRange(from ,to));
    }

    /**
     * Test case to ensure that events can be found by a valid date range, and the expected count is returned.
     */
    @Test
    public void findEventByDateRange() {
        LocalDate from = LocalDate.of(2024, 05, 10);
        LocalDate to = LocalDate.of(2024, 11, 26);
        Assertions.assertEquals(6, eventDAO.findByDateRange(from, to).size());
    }

    /**
     * Test case to ensure that events can be found based on a customer's interests, and the expected count is returned.
     */
    @Test
    public void findEventByCustomerInterests() {
        Customer customer = customerDAO.find(4);
        Assertions.assertEquals(2, eventDAO.findByCustomerInterests(customer.getInterests()).size());
    }

    /**
     * Test case to ensure that all events are sorted by event capacity, and the sorted list is as expected.
     */
    @Test
    public void sortAllByEventCapacity() {
        ArrayList<Event> events = eventDAO.sortEventsByCapacity(eventDAO.findAll());
        Assertions.assertEquals(20, events.size());
        Assertions.assertTrue(events.containsAll(EventTestHelper.initEventsSortedByEventCapacity()));
        for (int i = 1; i < EventTestHelper.initEventsSortedByEventCapacity().size(); i++) {
            Event expectedEvent = EventTestHelper.initEventsSortedByEventCapacity().get(i);
            Event actualEvent = events.get(i);

            // Check if the events at the current index are the same
            Assertions.assertEquals(expectedEvent, actualEvent,
                    "Events at index " + i + " should be the same");
        }
    }

    /**
     * Test case to ensure that all events are sorted by tickets sold, and the sorted list is as expected.
     */
    @Test
    public void sortAllByTicketsSold() {
        ArrayList<Event> events = eventDAO.sortEventsByTicketsSold(eventDAO.findAll());
        Assertions.assertEquals(20, events.size());
        Assertions.assertTrue(events.containsAll(EventTestHelper.initEventsSortedByTicketsSold()));
        for (int i = 1; i < EventTestHelper.initEventsSortedByTicketsSold().size(); i++) {
            Event expectedEvent = EventTestHelper.initEventsSortedByTicketsSold().get(i);
            Event actualEvent = events.get(i);

            // Check if the events at the current index are the same
            Assertions.assertEquals(expectedEvent, actualEvent,
                    "Events at index " + i + " should be the same");
        }
    }

    /**
     * Test case to ensure that all events are sorted by rating, and the sorted list is as expected.
     */
    @Test
    public void sortAllByRating() {
        ArrayList<Event> events = eventDAO.sortEventsByRating(eventDAO.findAll());
        Assertions.assertEquals(20, events.size());
        Assertions.assertTrue(events.containsAll(EventTestHelper.initEventsSortedByRating()));
        for (int i = 1; i < EventTestHelper.initEventsSortedByRating().size(); i++) {
            Event expectedEvent = EventTestHelper.initEventsSortedByRating().get(i);
            Event actualEvent = events.get(i);

            // Check if the events at the current index are the same
            Assertions.assertEquals(expectedEvent, actualEvent,
                    "Events at index " + i + " should be the same");
        }
    }

    /**
     * Test case to ensure that all events are sorted by a closer date, and the sorted list is as expected.
     */
    @Test
    public void sortAllByCloserDate() {
        ArrayList<Event> events = eventDAO.sortEventsByCloserDate(eventDAO.findAll());
        Assertions.assertEquals(20, events.size());
        Assertions.assertTrue(events.containsAll(EventTestHelper.initEventsSortedByCloserDate()));
        for (int i = 1; i < EventTestHelper.initEventsSortedByCloserDate().size(); i++) {
            Event expectedEvent = EventTestHelper.initEventsSortedByCloserDate().get(i);
            Event actualEvent = events.get(i);

            // Check if the events at the current index are the same
            Assertions.assertEquals(expectedEvent, actualEvent,
                    "Events at index " + i + " should be the same");
        }
    }

    /**
     * Test case to ensure that all events are sorted by a further date, and the sorted list is as expected.
     */
    @Test
    public void sortAllByFurtherDate() {
        ArrayList<Event> events = eventDAO.sortEventsByFurtherDate(eventDAO.findAll());
        Assertions.assertEquals(20, events.size());
        Assertions.assertTrue(events.containsAll(EventTestHelper.initEventsSortedByFartherDate()));
        for (int i = 1; i < EventTestHelper.initEventsSortedByFartherDate().size(); i++) {
            Event expectedEvent = EventTestHelper.initEventsSortedByFartherDate().get(i);
            Event actualEvent = events.get(i);

            // Check if the events at the current index are the same
            Assertions.assertEquals(expectedEvent, actualEvent,
                    "Events at index " + i + " should be the same");
        }
    }





















    
}
