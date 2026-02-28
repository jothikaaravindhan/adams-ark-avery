package org.adamsArkAndAvery.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerTest {

	@Test
    void testConstructorAndGetters() {
        Customer customer = new Customer("alice", "1234", "alice@example.com");

        assertEquals("alice", customer.getUsername());
        assertEquals("1234", customer.getPassword());
        assertEquals("alice@example.com", customer.getEmail());
    }

    @Test
    void testSetters() {
        Customer customer = new Customer("oldUser", "oldPass", "old@example.com");

        customer.setUsername("newUser");
        customer.setPassword("newPass");
        customer.setEmail("new@example.com");

        assertEquals("newUser", customer.getUsername());
        assertEquals("newPass", customer.getPassword());
        assertEquals("new@example.com", customer.getEmail());
    }

}
