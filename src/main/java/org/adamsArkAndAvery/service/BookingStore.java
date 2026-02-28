package org.adamsArkAndAvery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.adamsArkAndAvery.model.PetBooking;

@ManagedBean(name = "bookingStore")
@ApplicationScoped
public class BookingStore {
	private List<PetBooking> bookingList = new ArrayList<>();

	//submit button --> save pet book informations in bookstore
    public void saveBooking(PetBooking booking) {
        bookingList.add(booking);
        System.out.println("Booking saved for: " + booking.getPetName());
    }

   //show all book informations will use for history 
    public List<PetBooking> getAllBookings() {
        return bookingList;
    }
    
    //return current booking information(actually the current one's index is 0)
    public synchronized PetBooking getCurrentBooking() {
        if (bookingList.isEmpty()) return null;
        return bookingList.get(bookingList.size() - 1);
    }
    
    public synchronized void clearAll() {
        bookingList.clear();
    }
    
    //delete booking information for change
    public void deleteBooking() {
    	bookingList.remove(0);
    	
    }


    public List<PetBooking> getActiveOrUpcoming(Date from) {
        List<PetBooking> out = new ArrayList<>();
        for (PetBooking b :  bookingList) {
            Date checkOut = b.getCheckOutDate();

            if (checkOut != null && !checkOut.before(from)) {
                out.add(b);
            }
        }
        return out;
    }



    public int bookingListSize() {
    	return bookingList.size();
    }
    
    public synchronized java.util.List<org.adamsArkAndAvery.model.PetBooking> getCurrentlyBoarding(java.util.Date now) {
        java.util.List<org.adamsArkAndAvery.model.PetBooking> active = new java.util.ArrayList<>();
        if (now == null) now = new java.util.Date();
        for (org.adamsArkAndAvery.model.PetBooking b : bookingList) {
            if (b.getCheckInDate() != null && b.getCheckOutDate() != null
                    && !now.before(b.getCheckInDate())
                    && now.before(b.getCheckOutDate())) {
                active.add(b);
            }
        }
        return active;
    }
}
