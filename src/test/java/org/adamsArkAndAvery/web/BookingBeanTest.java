package org.adamsArkAndAvery.web;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.adamsArkAndAvery.model.PetBooking;
import org.adamsArkAndAvery.service.BookingStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookingBeanTest {

    private BookingBean bookingBean;
    private BookingStore bookingStore;

    @BeforeEach
    public void setUp() {
        bookingStore = new BookingStore();
        bookingBean = new BookingBean();
        bookingBean.setBookingStore(bookingStore);
    }

    @Test
    public void testSubmitAddsBookingAndReturnsNavigation() {
        bookingBean.setOwnerName("John Doe");
        bookingBean.setPetName("Buddy");
        bookingBean.setPetType("Dog");
        bookingBean.setCheckInDate(new Date());
        bookingBean.setCheckOutDate(new Date());
        bookingBean.setSpecialNotes("No special notes");

        String result = bookingBean.submit();

        assertEquals("petBooking.xhtml?faces-redirect=true", result);
        assertEquals(1, bookingStore.getAllBookings().size());
        PetBooking savedBooking = bookingStore.getAllBookings().get(0);
        assertEquals("Buddy", savedBooking.getPetName());
        assertEquals("Dog", savedBooking.getPetType());
    }

    @Test
    public void testBookingBeanGettersAndSetters() {
        Date checkIn = new Date();
        Date checkOut = new Date();

        bookingBean.setOwnerName("Jane Doe");
        bookingBean.setPetName("Milo");
        bookingBean.setPetType("Cat");
        bookingBean.setCheckInDate(checkIn);
        bookingBean.setCheckOutDate(checkOut);
        bookingBean.setSpecialNotes("Indoor only");

        assertEquals("Jane Doe", bookingBean.getOwnerName());
        assertEquals("Milo", bookingBean.getPetName());
        assertEquals("Cat", bookingBean.getPetType());
        assertEquals(checkIn, bookingBean.getCheckInDate());
        assertEquals(checkOut, bookingBean.getCheckOutDate());
        assertEquals("Indoor only", bookingBean.getSpecialNotes());
    }
}
