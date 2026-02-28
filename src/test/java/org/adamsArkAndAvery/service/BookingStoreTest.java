package org.adamsArkAndAvery.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.adamsArkAndAvery.model.PetBooking;

import java.util.Date;

public class BookingStoreTest {

    private BookingStore bookingStore;

    @BeforeEach
    public void setUp() {
        bookingStore = new BookingStore();
    }

    @Test
    public void testSuccessfulBookingCreation() {
        PetBooking booking = new PetBooking(
                "John Doe",
                "Buddy",
                "Dog",
                new Date(),
                new Date(),
                "No special notes"
        );

        bookingStore.saveBooking(booking);

        assertEquals(1, bookingStore.getAllBookings().size());
        assertEquals("Buddy", bookingStore.getAllBookings().get(0).getPetName());
    }



    @Test
    public void testGetCurrentBooking_AfterOneBooking() {
        PetBooking booking = new PetBooking(
                "Jane Doe",
                "Milo",
                "Cat",
                new Date(),
                new Date(),
                "Indoor only"
        );

        bookingStore.saveBooking(booking);

        PetBooking current = bookingStore.getCurrentBooking();
        assertNotNull(current);
        assertEquals("Milo", current.getPetName());
    }

    @Test
    public void testMultipleBookings() {
        bookingStore.saveBooking(new PetBooking("Owner1", "Pet1", "Dog", new Date(), new Date(), ""));
        bookingStore.saveBooking(new PetBooking("Owner2", "Pet2", "Cat", new Date(), new Date(), ""));
        assertEquals(2, bookingStore.getAllBookings().size());
    }
    
    @Test
    public void testGetCurrentBooking_MultipleBookings() {
        bookingStore.saveBooking(new PetBooking("Owner1", "Pet1", "Dog", new Date(), new Date(), ""));
        bookingStore.saveBooking(new PetBooking("Owner2", "Pet2", "Cat", new Date(), new Date(), ""));
        PetBooking current = bookingStore.getCurrentBooking();
        assertEquals("Pet2", current.getPetName());
    }

}
