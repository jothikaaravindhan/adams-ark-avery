package org.adamsArkAndAvery.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PetAttendantTest {

	@Test
	void testConstructorAndGetters() {
		PetAttendant attendant = new PetAttendant("myPassword", "email@example.com");

		assertEquals("myPassword", attendant.getPassword(), "Password should match the constructor value");
		assertEquals("email@example.com", attendant.getEmail(), "Email should match the constructor value");
	}

	@Test
	void testSetPassword() {
		PetAttendant attendant = new PetAttendant("initialPass", "email@example.com");
		attendant.setPassword("newPassword");

		assertEquals("newPassword", attendant.getPassword(), "Password should update after setPassword");
	}

	@Test
	void testSetEmail() {
		PetAttendant attendant = new PetAttendant("password123", "oldEmail@example.com");
		attendant.setEmail("newEmail@example.com");

		assertEquals("newEmail@example.com", attendant.getEmail(), "Email should update after setEmail");
	}
}

