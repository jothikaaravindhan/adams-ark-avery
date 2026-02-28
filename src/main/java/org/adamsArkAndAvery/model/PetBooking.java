package org.adamsArkAndAvery.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PetBooking {
	private String ownerName;
    private String petName;
    private String petType;
    private Date checkInDate;
    private Date checkOutDate;
    private String specialNotes;

    // Constructor
    public PetBooking(String ownerName, String petName, String petType,
                      Date checkInDate, Date checkOutDate, String specialNotes) {
        this.ownerName = ownerName;
        this.petName = petName;
        this.petType = petType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.specialNotes = specialNotes;
 
    }
    
    //Getters and Setters
    
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
    
    public int getDays() {
    	long diff = this.checkOutDate.getTime() - this.checkInDate.getTime();
    	
    	return (int) TimeUnit.MILLISECONDS.toDays(diff);
    }
   
}
