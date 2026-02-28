package org.adamsArkAndAvery.web;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.adamsArkAndAvery.model.PetBooking;
import org.adamsArkAndAvery.service.BookingStore;
import org.adamsArkAndAvery.service.OrderStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderBeanTest {

    private OrderBean orderBean;
    private BookingStore bookingStore;
    private OrderStore orderStore;

    @BeforeEach
    public void setUp() {
        bookingStore = new BookingStore();
        orderStore = new OrderStore();
        orderBean = new OrderBean();
        orderBean.setBookingStore(bookingStore);
        orderBean.setOrderStore(orderStore);
    }

    @Test
    public void testGetAvailableServices_NoBookings() {
        List<SelectItem> services = orderBean.getAvailableServices();
        assertTrue(services.isEmpty(), "Expected no services when there are no bookings");
    }

    @Test
    public void testGetAvailableServices_Dog() {
        bookingStore.saveBooking(new PetBooking("Owner", "Buddy", "Dog", new Date(), new Date(), ""));
        List<SelectItem> services = orderBean.getAvailableServices();
        assertEquals(7, services.size(), "Dog should have 7 available services");
        assertEquals("Pods", services.get(0).getValue());
    }

    @Test
    public void testGetAvailableServices_Cat() {
        bookingStore.saveBooking(new PetBooking("Owner", "Whiskers", "Cat", new Date(), new Date(), ""));
        List<SelectItem> services = orderBean.getAvailableServices();
        assertEquals(7, services.size(), "Cat should have 7 available services");
        assertEquals("Pods", services.get(0).getValue());
    }

    @Test
    public void testGetAvailableServices_Bird() {
        bookingStore.saveBooking(new PetBooking("Owner", "Tweety", "Bird", new Date(), new Date(), ""));
        List<SelectItem> services = orderBean.getAvailableServices();
        assertEquals(7, services.size(), "Bird should have 7 available services");
        assertEquals("Pods", services.get(0).getValue());
    }

    @Test
    public void testGetAvailableServices_Reptile() {
        bookingStore.saveBooking(new PetBooking("Owner", "Slinky", "Reptile", new Date(), new Date(), ""));
        List<SelectItem> services = orderBean.getAvailableServices();
        assertEquals(7, services.size(), "Reptile should have 7 available services");
        assertEquals("Pods", services.get(0).getValue());
    }

    @Test
    public void testGetAvailableServices_Fish() {
        bookingStore.saveBooking(new PetBooking("Owner", "Goldie", "Fish", new Date(), new Date(), ""));
        List<SelectItem> services = orderBean.getAvailableServices();
        assertEquals(7, services.size(), "Fish should have 7 available services");
        assertEquals("Pods", services.get(0).getValue());
    }

    @Test
    public void testGetAvailableServices_Amphibian() {
        bookingStore.saveBooking(new PetBooking("Owner", "Froggy", "Amphibian", new Date(), new Date(), ""));
        List<SelectItem> services = orderBean.getAvailableServices();
        assertEquals(7, services.size(), "Amphibian should have 7 available services");
        assertEquals("Pods", services.get(0).getValue());
    }

    @Test
    public void testGetAvailableServices_Exotic() {
        bookingStore.saveBooking(new PetBooking("Owner", "Parrot", "Exotic", new Date(), new Date(), ""));
        List<SelectItem> services = orderBean.getAvailableServices();
        assertEquals(7, services.size(), "Exotic should have 7 available services");
        assertEquals("Pods", services.get(0).getValue());
    }

    @Test
    public void testGetAvailableServices_Small() {
        bookingStore.saveBooking(new PetBooking("Owner", "Hammy", "Small", new Date(), new Date(), ""));
        List<SelectItem> services = orderBean.getAvailableServices();
        assertEquals(7, services.size(), "Small pets should have 7 available services");
        assertEquals("Pods", services.get(0).getValue());
    }


    @Test
    public void testGetAvailableServices_NullPetType() {
        bookingStore.saveBooking(new PetBooking("Owner", "NoType", null, new Date(), new Date(), ""));
        List<SelectItem> services = orderBean.getAvailableServices();
        assertTrue(services.isEmpty(), "Null pet type should return empty service list");
    }

    @Test
    public void testConfirm_WithBooking() {
        PetBooking booking = new PetBooking("Owner", "Buddy", "Dog", new Date(), new Date(), "");
        bookingStore.saveBooking(booking);
        orderBean.setSelectedServices(Arrays.asList("Playtime", "Grooming"));

        String result = orderBean.confirm();
        assertEquals("confirmation.xhtml?faces-redirect=true", result);
        assertEquals(1, orderStore.getAllOrders().size(), "OrderStore should have 1 order after confirm");
    }

    @Test
    public void testConfirm_NoBooking() {
        String result = orderBean.confirm();
        assertEquals("", result, "No booking should return empty navigation string");
        assertTrue(orderStore.getAllOrders().isEmpty(), "OrderStore should be empty when no booking is present");
    }
}