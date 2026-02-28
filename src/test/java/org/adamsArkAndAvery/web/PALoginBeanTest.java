package org.adamsArkAndAvery.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PALoginBeanTest {

    private PALoginBean bean;

    @BeforeEach
    void setUp() { bean = new PALoginBean(); }

    @Test
    void validLogin_alex_navigatesAndClearsMessage() {
        bean.setEmail("alex@3a.ie");
        bean.setPassword("alex123");
        String out = bean.login();
        assertEquals("staff?faces-redirect=true", out);
        assertNull(bean.getMessage());
    }

    @Test
    void validLogin_sam_caseInsensitiveEmail() {
        bean.setEmail("SAM@3A.IE");
        bean.setPassword("sam123");
        String out = bean.login();
        assertEquals("staff?faces-redirect=true", out);
        assertNull(bean.getMessage());
    }

    @Test
    void invalidPassword_setsMessage_andStaysOnPage() {
        bean.setEmail("alex@3a.ie");
        bean.setPassword("wrong");
        String out = bean.login();
        assertNull(out);
        assertEquals("Invalid email or password.", bean.getMessage());
    }

    @Test
    void invalidEmail_setsMessage_andStaysOnPage() {
        bean.setEmail("nope@3a.ie");
        bean.setPassword("sam123");
        String out = bean.login();
        assertNull(out);
        assertEquals("Invalid email or password.", bean.getMessage());
    }

    @Test
    void invalidThenValid_clearsOldError() {
        bean.setEmail("alex@3a.ie");
        bean.setPassword("bad");
        bean.login();
        assertNotNull(bean.getMessage());

        bean.setPassword("alex123");
        String out = bean.login();
        assertEquals("staff?faces-redirect=true", out);
        assertNull(bean.getMessage());
    }
}
