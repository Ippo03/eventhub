package com.example.eventhub.memorydao;

import com.example.eventhub.dao.CustomerDAO;
import com.example.eventhub.domain.Customer;

import java.util.ArrayList;

public class CustomerDAOMemory implements CustomerDAO {
    protected static ArrayList<Customer> customers = new ArrayList();

    @Override
    public void delete(Customer customer) {
        customers.remove(customer);
    }

    @Override
    public ArrayList<Customer> findAll() {
        ArrayList<Customer> result = new ArrayList();
        result.addAll(customers);
        return result;
    }

    @Override
    public void save(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Customer find(int id) {
        for(Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public int nextId() {
        return UserDAOMemory.users.size() == 0 ? 1 : UserDAOMemory.users.get(UserDAOMemory.users.size() - 1).getId() + 1;
    }

    @Override
    public Customer findByCredentials(String email, String password) {
        for(Customer customer : customers) {
            if(customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public void update(Customer customer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == customer.getId()) {
                customers.set(i, customer);
                break;
            }
        }
    }

    @Override
    public Customer findCustomerWithAlreadyExistingEmail(Customer customer) {
        for(Customer customer1 : customers) {
            if(customer1.getEmail().equals(customer.getEmail())
                    && customer1.getId() != customer.getId()) {
                return customer1;
            }
        }
        return null;
    }
}
