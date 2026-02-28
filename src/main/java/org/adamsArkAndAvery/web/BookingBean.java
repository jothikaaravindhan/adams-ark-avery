package org.adamsArkAndAvery.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Date;

import org.adamsArkAndAvery.model.PetBooking;
import org.adamsArkAndAvery.service.BookingStore;

@ManagedBean(name = "bookingBean")
@SessionScoped
public class BookingBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String ownerName;
    private String petName;
    private String petType;
    private Date checkInDate;
    private Date checkOutDate;
    private String specialNotes;
    
    @ManagedProperty("#{bookingStore}")
    private BookingStore bookingStore;

    public void setBookingStore(BookingStore bookingStore) {
        this.bookingStore = bookingStore;
    }

    // getter and setter
    
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
    }

    // submit function
    public String submit() {
    	
    	PetBooking booking = new PetBooking(ownerName, petName, petType, checkInDate, checkOutDate, specialNotes);
    	
    	bookingStore.saveBooking(booking);
        System.out.println("Booking received for: " + petName + " (" + petType + ")");
        return "petBooking.xhtml?faces-redirect=true";
    }
    
    public String change() {
    	bookingStore.deleteBooking();
    	return "petDetails.xhtml?faces-redirect=true";
    }
    
    public String add() {
    	if (bookingStore.bookingListSize() == 0) {
    		return "petDetails.xhtml?faces-redirect=true";
    	} else {
    		System.out.println("Just can input one pet details!");
    		return null;
    	}
    }
    
    }
