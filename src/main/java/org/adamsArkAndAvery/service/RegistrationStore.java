package org.adamsArkAndAvery.service;

import org.adamsArkAndAvery.model.Customer;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "registrationStore")
@ApplicationScoped
//used to define a bean as an application-scoped bean, meaning it will be created only once per application and shared among all clients.
public class RegistrationStore {
    private final List<Customer> users = new ArrayList<>();

    public void register(Customer c) {
        if (c.getUsername() == null
                || c.getUsername().isBlank()
                || c.getPassword() == null
                || c.getPassword().length() < 5
                || c.getPassword().isBlank()) {
            throw new IllegalArgumentException("Invalid input");
        }

        //check if the username already exists
        for (Customer existing : users) {
            if (existing.getUsername().equalsIgnoreCase(c.getUsername())) {
                throw new IllegalStateException("Username is already in use");
            }
        }
        //if all is as it should be, store
        users.add(c);
    }

    public int count() {
        return users.size();
    }

    public void clearAll() {
        users.clear();
    }

    //Returns true if there is at least one registered user
    //Whose username and password are correct
    public boolean authenticate(String username, String password) {
        return users.stream().anyMatch(c -> c.getUsername().equalsIgnoreCase(username) && c.getPassword().equals(password));
    }
    
    public List<Customer> getUsers() {
        return new ArrayList<>(users); // Return a copy to prevent external modification
    }
}
