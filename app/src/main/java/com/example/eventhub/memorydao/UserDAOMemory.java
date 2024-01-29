package com.example.eventhub.memorydao;

import com.example.eventhub.dao.UserDAO;
import com.example.eventhub.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOMemory implements UserDAO {
    protected static ArrayList<User> users = new ArrayList<User>();

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    @Override
    public List<User> findAll() {
        ArrayList<User> result = new ArrayList<>();
        result.addAll(users);
        return result;
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public User find(int id) {
        for(User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        for(User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public int nextId() {
        return users.size() == 0 ? 1 : users.get(users.size() - 1).getId() + 1;
    }

    @Override
    public void update(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
                break;
            }
        }
    }
}
