package org.adamsArkAndAvery.service;

import static org.junit.jupiter.api.Assertions.*;

import org.adamsArkAndAvery.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistrationTest {

    private Registration registration;


    @BeforeEach
    void setUp() {
        registration = new Registration();
    }

    @Test
    void register() {
        registration.register(new Customer("user1","password1", "test@email.com"));
        assertEquals(1,registration.count());
    }


    @Test
    void clearAll() {
        registration.register(new Customer("user1","password1", "test@email.com"));
        registration.register(new Customer("user2","password2", "test@email.com"));
        assertEquals(2, registration.count());
        registration.clearAll();
        assertEquals(0, registration.count());
    }

    @Test
    void count() {
        assertEquals(0, registration.count());
        registration.register(new Customer("user1","password1", "test@email.com"));
        assertEquals(1, registration.count());
        registration.register(new Customer("user2","password2", "test@email.com"));
        assertEquals(2, registration.count());
    }
}