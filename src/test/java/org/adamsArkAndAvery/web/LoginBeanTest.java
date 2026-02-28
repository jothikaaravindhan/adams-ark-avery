package org.adamsArkAndAvery.web;

import org.adamsArkAndAvery.model.Customer;
import org.adamsArkAndAvery.service.RegistrationStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginBeanTest {

    private LoginBean loginBean;
    private RegistrationStore store;

    @BeforeEach
    void setUp() {
        store = new RegistrationStore();
        loginBean = new LoginBean();
        loginBean.setRegistrationStore(store);
    }

    @Test
    void testLoginWithEmailSuccess() {
        store.register(new Customer("user1", "password123", "user1@test.com"));

        loginBean.setEmail("user1@test.com");
        loginBean.setPassword("password123");

        String outcome = loginBean.login();
        assertEquals("homepage?faces-redirect=true", outcome);
        assertNull(loginBean.getMessage(), "Message should be null on success");
    }

    @Test
    void testLoginWithUsernameSuccess() {
        store.register(new Customer("user2", "password123", "user2@test.com"));

        loginBean.setEmail("user2"); // Using username instead of email
        loginBean.setPassword("password123");

        String outcome = loginBean.login();
        assertEquals("homepage?faces-redirect=true", outcome);
        assertNull(loginBean.getMessage(), "Message should be null on success");
    }

    @Test
    void testLoginWithWrongPassword() {
        store.register(new Customer("user3", "password123", "user3@test.com"));

        loginBean.setEmail("user3@test.com");
        loginBean.setPassword("wrongpass");

        String outcome = loginBean.login();
        assertNull(outcome, "Outcome should be null on failed login");
        assertTrue(loginBean.getMessage().contains("Invalid"), "Message should indicate invalid login");
    }

    @Test
    void testLoginWithEmptyFields() {
        loginBean.setEmail("");
        loginBean.setPassword("");

        String outcome = loginBean.login();
        assertNull(outcome, "Outcome should be null when fields are empty");
        assertTrue(loginBean.getMessage().contains("Please enter"), "Message should indicate missing fields");
    }
}
