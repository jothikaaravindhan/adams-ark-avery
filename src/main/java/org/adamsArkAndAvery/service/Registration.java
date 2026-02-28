package org.adamsArkAndAvery.service;


import org.adamsArkAndAvery.model.Customer;

import java.util.ArrayList;
import java.util.List;

//import javax.faces.bean.ManagedBean;

public class Registration {
    private final List<Customer> users = new ArrayList<>();


    public void register(Customer c) {
        //Validation code
        if(c.getUsername() == null || c.getUsername().isBlank() || c.getPassword() == null || c.getPassword().length() < 4 || c.getPassword().isBlank()) {
        }
        //enhanced for to check if the username already exists
        for (Customer existing: users) {
            if (existing.getUsername().equalsIgnoreCase(c.getUsername())) {
                throw new IllegalStateException("Username is already in use");
            }
        }
        users.add(c); //Add user to memory
    }
    //Clear all registered users for testing.
    public void clearAll() {
        users.clear();
    }

    //Check how many users have been registered.
    public int count() {
        return users.size();
    }
}
