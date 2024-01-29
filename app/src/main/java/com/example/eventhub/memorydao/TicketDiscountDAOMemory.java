package com.example.eventhub.memorydao;

import com.example.eventhub.dao.TicketDiscountDAO;
import com.example.eventhub.domain.DiscountType;
import com.example.eventhub.domain.TicketDiscount;

import java.util.ArrayList;
import java.util.List;

public class TicketDiscountDAOMemory implements TicketDiscountDAO {
    protected static List<TicketDiscount> ticketDiscounts = new ArrayList<TicketDiscount>();

    @Override
    public void delete(TicketDiscount ticketDiscount) {
        ticketDiscounts.remove(ticketDiscount);
    }

    @Override
    public List<TicketDiscount> findAll() {
        ArrayList<TicketDiscount> result = new ArrayList<TicketDiscount>();
        result.addAll(ticketDiscounts);
        return result;
    }

    @Override
    public void save(TicketDiscount ticketDiscount) {
        ticketDiscounts.add(ticketDiscount);
    }

    @Override
    public TicketDiscount find(int id) {
        for(TicketDiscount ticketDiscount : ticketDiscounts) {
            if (ticketDiscount.getTicketDiscountId() == id) {
                return ticketDiscount;
            }
        }
        return null;
    }

    @Override
    public int nextId() {
        return ticketDiscounts.size() == 0 ? 1 : ticketDiscounts.get(ticketDiscounts.size() - 1).getTicketDiscountId() + 1;
    }

    @Override
    public void update(TicketDiscount ticketDiscount) {
        for (int i = 0; i < ticketDiscounts.size(); i++) {
            if (ticketDiscounts.get(i).getTicketDiscountId() == ticketDiscount.getTicketDiscountId()) {
                ticketDiscounts.set(i, ticketDiscount);
                break;
            }
        }
    }
}
