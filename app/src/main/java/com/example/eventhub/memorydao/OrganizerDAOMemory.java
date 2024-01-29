package com.example.eventhub.memorydao;

import com.example.eventhub.dao.OrganizerDAO;
import com.example.eventhub.domain.Organizer;

import java.util.ArrayList;
import java.util.List;

public class OrganizerDAOMemory implements OrganizerDAO {
    protected static ArrayList<Organizer> organizers = new ArrayList<>();

    @Override
    public void delete(Organizer organizer) {
        organizers.remove(organizer);
    }

    @Override
    public List<Organizer> findAll() {
        ArrayList<Organizer> result = new ArrayList<>();
        result.addAll(organizers);
        return result;
    }

    @Override
    public void save(Organizer organizer) {
        organizers.add(organizer);
    }

    @Override
    public Organizer find(int id) {
        for(Organizer organizer : organizers) {
            if (organizer.getId() == id) {
                return organizer;
            }
        }
        return null;
    }

    @Override
    public int nextId() {
        return UserDAOMemory.users.size() == 0 ? 1 : UserDAOMemory.users.get(UserDAOMemory.users.size() - 1).getId() + 1;
    }

    @Override
    public Organizer findByCredentials(String email, String password) {
        for(Organizer organizer : organizers) {
            if (organizer.getEmail().equals(email) && organizer.getPassword().equals(password)) {
                return organizer;
            }
        }
        return null;
    }

    @Override
    public void update(Organizer organizer) {
        for (int i = 0; i < organizers.size(); i++) {
            if (organizers.get(i).getId() == organizer.getId()) {
                organizers.set(i, organizer);
                break;
            }
        }
    }

    @Override
    public Organizer findOrganizerWithAlreadyExistingEmail(Organizer organizer) {
        for(Organizer organizer1 : organizers) {
            if (organizer1.getEmail().equals(organizer.getEmail())
                    && organizer1.getId() != organizer.getId()) {
                return organizer1;
            }
        }
        return null;
    }
}
