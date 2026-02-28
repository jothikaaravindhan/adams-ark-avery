package org.adamsArkAndAvery.model;

import java.util.List;

public class Order {
	// List of selected services
	private List<String> selectedServices;
	// Associated PetBooking
	private PetBooking booking;
	private int pods;

	// Constructor
	public Order(PetBooking booking, List<String> selectedServices) {
		this.booking = booking;
		this.selectedServices = selectedServices;
		//random allocate pod for pet
		this.pods =(int)(Math.random() * 6) + 1;
	}

	//setter and getter
	public int getPods() {
		return pods;
	}

	public void setPods(int pods) {
		this.pods = pods;
	}
	
	public void setBooking(PetBooking booking) {
		this.booking = booking;
	}

	public PetBooking getBooking() {
		return booking;
	}

	public void setSelectedServices(List<String> selectedServices) {
		this.selectedServices = selectedServices;
	}

	public List<String> getSelectedServices() {
		return selectedServices;
	}

	//display selected services in confirmation page
	public String showServices() {
		String showServices = "";
		for(String i : selectedServices) {
			showServices += i + ", ";
		}
		return showServices;
	}

	public double getPrice() {
		return booking.getDays() * 20 + selectedServices.size() * 20;
	}
}
