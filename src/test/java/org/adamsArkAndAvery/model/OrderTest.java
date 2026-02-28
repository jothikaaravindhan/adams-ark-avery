package org.adamsArkAndAvery.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OrderTest {

	@Test
	void testConstructorSetsFields() {
		Date checkIn = Date.from(LocalDate.of(2025, 7, 18).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date checkOut = Date.from(LocalDate.of(2025, 7, 19).atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		PetBooking booking = new PetBooking("Alice", "Fluffy", "Dog", checkIn, checkOut, "No special notes");
		
		List<String> services = Arrays.asList("Grooming", "Vaccination");

		Order order = new Order(booking, services);

		assertEquals(booking, order.getBooking(), "Booking should match the constructor input");
		assertEquals(services, order.getSelectedServices(), "Selected services should match the constructor input");
		assertTrue(order.getPods() >= 1 && order.getPods() <= 6, "Pods should be between 1 and 6");
	}

	@Test
	void testSetters() {
		Date checkIn = Date.from(LocalDate.of(2025, 7, 18).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date checkOut = Date.from(LocalDate.of(2025, 7, 20).atStartOfDay(ZoneId.systemDefault()).toInstant());

		PetBooking booking1 = new PetBooking("Alice", "Fluffy", "Dog", checkIn, checkOut, "");
		PetBooking booking2 = new PetBooking("Bob", "Max", "Cat", checkIn, checkOut, "");

		Order order = new Order(booking1, Arrays.asList("Grooming"));

		// Test setBooking
		order.setBooking(booking2);
		assertEquals(booking2, order.getBooking());

		// Test setPods
		order.setPods(5);
		assertEquals(5, order.getPods());

		// Test setSelectedServices
		List<String> newServices = Arrays.asList("Walking", "Training");
		order.setSelectedServices(newServices);
		assertEquals(newServices, order.getSelectedServices());
	}

	@Test
	void testShowServices() {

		Date checkIn = Date.from(LocalDate.of(2025, 7, 18).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date checkOut = Date.from(LocalDate.of(2025, 7, 19).atStartOfDay(ZoneId.systemDefault()).toInstant());

		PetBooking booking = new PetBooking("Alice", "Fluffy", "Dog", checkIn, checkOut, "");
		List<String> services = Arrays.asList("Grooming", "Vaccination");

		Order order = new Order(booking, services);

		assertEquals("Grooming, Vaccination, ", order.showServices());
	}

	@Test
	void testGetPrice() {
		Date checkIn = Date.from(LocalDate.of(2025, 7, 18).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date checkOut = Date.from(LocalDate.of(2025, 7, 21).atStartOfDay(ZoneId.systemDefault()).toInstant());

		PetBooking booking = new PetBooking("Alice", "Fluffy", "Dog", checkIn, checkOut, ""); 
		List<String> services = Arrays.asList("Grooming", "Vaccination");

		Order order = new Order(booking, services);

		// Expected price: 3 days * 20 + 2 services * 20 = 60 + 40 = 100
		assertEquals(100, order.getPrice());
	}
}
