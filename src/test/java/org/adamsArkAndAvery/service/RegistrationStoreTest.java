package org.adamsArkAndAvery.service;

import org.adamsArkAndAvery.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class RegistrationStoreTest {
    private RegistrationStore store;

    @BeforeEach
    void setUp() {
        store = new RegistrationStore();
        store.clearAll();
    }

    @Test
    void registerUserCount() {
        assertEquals(0, store.count());
        store.register(new Customer("tom","passwork","tom@test.com"));
        assertEquals(1, store.count());
    }

    @Test
    void registerWithBlankUsername() {
        assertThrows(IllegalArgumentException.class,
                () -> store.register(new Customer("","password","Test2@email.com")));
    }

    @Test
    void ShortPassword() {
        assertThrows(IllegalArgumentException.class,
                () -> store.register(new Customer("abc","123","test@test.com")));
    }

    @Test
    void duplicateUsername() {
        store.register(new Customer("charlie","pw1234","a@b.com"));
        assertThrows(IllegalStateException.class,
                () -> store.register(new Customer("charlie","pw1234","c@d.com"))
        );
    }
}