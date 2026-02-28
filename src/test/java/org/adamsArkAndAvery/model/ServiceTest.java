package org.adamsArkAndAvery.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ServiceTest {

	@Test
    void testDefaultConstructorAndSetters() {
        Service service = new Service();

        service.setName("Grooming");
        service.setDescription("Full grooming package");
        service.setPrice(50.0);
        service.setCategory("Beauty");

        assertEquals("Grooming", service.getName());
        assertEquals("Full grooming package", service.getDescription());
        assertEquals(50.0, service.getPrice());
        assertEquals("Beauty", service.getCategory());
    }

    @Test
    void testParameterizedConstructorAndGetters() {
        Service service = new Service("Boarding", "Overnight stay", 70.0, "Care", "Dog");

        assertEquals("Boarding", service.getName());
        assertEquals("Overnight stay", service.getDescription());
        assertEquals(70.0, service.getPrice());
        assertEquals("Care", service.getCategory());
    }

}
