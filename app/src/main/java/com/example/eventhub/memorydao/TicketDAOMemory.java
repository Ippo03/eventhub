package com.example.eventhub.memorydao;

import com.example.eventhub.dao.TicketDAO;
import com.example.eventhub.domain.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketDAOMemory implements TicketDAO {
    protected static List<Ticket> tickets = new ArrayList<Ticket>();

    @Override
    public void delete(Ticket ticket) {
        tickets.remove(ticket);
    }

    @Override
    public List<Ticket> findAll() {
        ArrayList<Ticket> result = new ArrayList<Ticket>();
        result.addAll(tickets);
        return result;
    }

    @Override
    public void save(Ticket ticket) {
        tickets.add(ticket);
    }

    @Override
    public Ticket find(int id) {
        for(Ticket ticket : tickets) {
            if (ticket.getTicketId() == id) {
                return ticket;
            }
        }
        return null;
    }

    @Override
    public int nextId() {
        return tickets.size() == 0 ? 1 : tickets.get(tickets.size() - 1).getTicketId() + 1;
    }
}
