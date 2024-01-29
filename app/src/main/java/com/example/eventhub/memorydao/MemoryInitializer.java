package com.example.eventhub.memorydao;

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
import com.example.eventhub.domain.Customer;
import com.example.eventhub.domain.Event;
import com.example.eventhub.domain.Organizer;
import com.example.eventhub.domain.Purchase;
import com.example.eventhub.domain.Review;
import com.example.eventhub.domain.Ticket;
import com.example.eventhub.domain.TicketCategory;
import com.example.eventhub.domain.TicketDiscount;
import com.example.eventhub.domain.User;

public class MemoryInitializer extends Initializer {
    @Override
    public void eraseAll() {
        for(User user : getUserDAO().findAll()) {
            getUserDAO().delete(user);
        }

        for(Organizer organizer : getOrganizerDAO().findAll()) {
            getOrganizerDAO().delete(organizer);
        }

        for(Customer customer : getCustomerDAO().findAll()) {
            getCustomerDAO().delete(customer);
        }

        for(Event event : getEventDAO().findAll()) {
            getEventDAO().delete(event);
        }

        for(Review review : getReviewDAO().findAll()) {
            getReviewDAO().delete(review);
        }

        for(TicketCategory ticketCategory : getTicketCategoryDAO().findAll()) {
            getTicketCategoryDAO().delete(ticketCategory);
        }

        for(TicketDiscount ticketDiscount : getTicketDiscountDAO().findAll()) {
            getTicketDiscountDAO().delete(ticketDiscount);
        }

        for(Ticket ticket : getTicketDAO().findAll()) {
            getTicketDAO().delete(ticket);
        }

        for(Purchase purchase : getPurchaseDAO().findAll()) {
            getPurchaseDAO().delete(purchase);
        }
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOMemory();
    }

    @Override
    public OrganizerDAO getOrganizerDAO() {
        return new OrganizerDAOMemory();
    }

    @Override
    public CustomerDAO getCustomerDAO() {
        return new CustomerDAOMemory();
    }

    @Override
    public EventDAO getEventDAO() {
        return new EventDAOMemory();
    }

    @Override
    public ReviewDAO getReviewDAO() {
        return new ReviewDAOMemory();
    }

    @Override
    public TicketDAO getTicketDAO() {
        return new TicketDAOMemory();
    }

    @Override
    public PurchaseDAO getPurchaseDAO() {
        return new PurchaseDAOMemory();
    }

    @Override
    public TicketCategoryDAO getTicketCategoryDAO() {
        return new TicketCategoryDAOMemory();
    }

    @Override
    public TicketDiscountDAO getTicketDiscountDAO() {
        return new TicketDiscountDAOMemory();
    }
}
