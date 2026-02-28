package org.adamsArkAndAvery.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.adamsArkAndAvery.model.Order;
import org.adamsArkAndAvery.model.PetBooking;
import org.adamsArkAndAvery.service.BookingStore;
import org.adamsArkAndAvery.service.OrderStore;

@ManagedBean(name = "orderBean")
@SessionScoped
public class OrderBean implements Serializable{
	private List<String> selectedServices = new ArrayList<>();
	private PetBooking booking;
	
	@ManagedProperty("#{bookingStore}")
    private BookingStore bookingStore;

    public void setBookingStore(BookingStore bookingStore) {
        this.bookingStore = bookingStore;
    }
	
	@ManagedProperty("#{orderStore}")
	private OrderStore orderStore;
	
	public void setOrderStore(OrderStore orderStore) {
		this.orderStore = orderStore;
	}
	
	//setter and getter
	public void setBPetBooking(PetBooking booking) {
		this.booking = booking;
	}
	
	public PetBooking getbooking() {
		return booking;
	}
	
	public List<String> getSelectedServices() {
        return selectedServices;
    }

    public void setSelectedServices(List<String> selectedServices) {
        this.selectedServices = selectedServices;
    }
    
    //show specific services for specific pet types
    public List<SelectItem> getAvailableServices() {
    	 List<SelectItem> services = new ArrayList<>();
    	
    	if (bookingStore.getAllBookings().size() > 0) {
    		// Get the pet type of the current booking
        if (bookingStore.getCurrentBooking() == null || bookingStore.getCurrentBooking().getPetType() == null) {
            return services;
        }
        
        String petType = bookingStore.getCurrentBooking().getPetType();
     // Add services based on pet type 
        switch (petType.toLowerCase()) {
            case "dog":
            	services.add(new SelectItem("Pods", "Pods"));
            	services.add(new SelectItem("Grooming", "Grooming"));
                services.add(new SelectItem("Photo Updates", "Photo Updates"));
                services.add(new SelectItem("Playtime", "Playtime"));
                services.add(new SelectItem("Customised Diet", "Customised Diet"));
                services.add(new SelectItem("Socialisation Activites", "Socialisation Activites"));
                services.add(new SelectItem("Personalized care packages", "Personalized care packages"));
                break;
            case "cat":
            	services.add(new SelectItem("Pods", "Pods"));
            	services.add(new SelectItem("Grooming", "Grooming"));
                services.add(new SelectItem("Photo Updates", "Photo Updates"));
                services.add(new SelectItem("Playtime", "Playtime"));
                services.add(new SelectItem("Customised Diet", "Customised Diet"));
                services.add(new SelectItem("Socialisation Activites", "Socialisation Activites"));
                services.add(new SelectItem("Personalized care packages", "Personalized care packages"));
                break;
            case "bird":
            	services.add(new SelectItem("Pods", "Pods"));
            	services.add(new SelectItem("Grooming", "Grooming"));
                services.add(new SelectItem("Photo Updates", "Photo Updates"));
                services.add(new SelectItem("Playtime", "Playtime"));
                services.add(new SelectItem("Customised Diet", "Customised Diet"));
                services.add(new SelectItem("Socialisation Activites", "Socialisation Activites"));
                services.add(new SelectItem("Personalized care packages", "Personalized care packages"));
                break;
            case "reptile":
            	services.add(new SelectItem("Pods", "Pods"));
            	services.add(new SelectItem("Grooming", "Grooming"));
                services.add(new SelectItem("Photo Updates", "Photo Updates"));
                services.add(new SelectItem("Playtime", "Playtime"));
                services.add(new SelectItem("Customised Diet", "Customised Diet"));
                services.add(new SelectItem("Socialisation Activites", "Socialisation Activites"));
                services.add(new SelectItem("Personalized care packages", "Personalized care packages"));
                break;
            case "fish":
            	services.add(new SelectItem("Pods", "Pods"));
            	services.add(new SelectItem("Grooming", "Grooming"));
                services.add(new SelectItem("Photo Updates", "Photo Updates"));
                services.add(new SelectItem("Playtime", "Playtime"));
                services.add(new SelectItem("Customised Diet", "Customised Diet"));
                services.add(new SelectItem("Socialisation Activites", "Socialisation Activites"));
                services.add(new SelectItem("Personalized care packages", "Personalized care packages"));
                break;
            case "amphibian":
            	services.add(new SelectItem("Pods", "Pods"));
            	services.add(new SelectItem("Grooming", "Grooming"));
                services.add(new SelectItem("Photo Updates", "Photo Updates"));
                services.add(new SelectItem("Playtime", "Playtime"));
                services.add(new SelectItem("Customised Diet", "Customised Diet"));
                services.add(new SelectItem("Socialisation Activites", "Socialisation Activites"));
                services.add(new SelectItem("Personalized care packages", "Personalized care packages"));
                break;
            case "exotic":
            	services.add(new SelectItem("Pods", "Pods"));
            	services.add(new SelectItem("Grooming", "Grooming"));
                services.add(new SelectItem("Photo Updates", "Photo Updates"));
                services.add(new SelectItem("Playtime", "Playtime"));
                services.add(new SelectItem("Customised Diet", "Customised Diet"));
                services.add(new SelectItem("Socialisation Activites", "Socialisation Activites"));
                services.add(new SelectItem("Personalized care packages", "Personalized care packages"));
                break;
            case "small":
            	services.add(new SelectItem("Pods", "Pods"));
            	services.add(new SelectItem("Grooming", "Grooming"));
                services.add(new SelectItem("Photo Updates", "Photo Updates"));
                services.add(new SelectItem("Playtime", "Playtime"));
                services.add(new SelectItem("Customised Diet", "Customised Diet"));
                services.add(new SelectItem("Socialisation Activites", "Socialisation Activites"));
                services.add(new SelectItem("Personalized care packages", "Personalized care packages"));
                break;
                
            default:
                services.add(new SelectItem("Personalized care packages", "Personalized care packages"));
            
        }} else {
        	System.out.println("Please input Pet Details");
        }
		return services;

        
    }
	
	public String confirm() {
		if (bookingStore.getAllBookings().size() > 0) {
			PetBooking currentBooking = bookingStore.getCurrentBooking();
			Order order = new Order(currentBooking, selectedServices);
			orderStore.addOrder(order);
			bookingStore.deleteBooking();
			return "confirmation.xhtml?faces-redirect=true";
		} else {
			System.out.println("Please input Pet Details");
			return "";
		}
		
	}
	
	public String cancel() {
		orderStore.deleteOrder();
		return "petBooking.xhtml?faces-redirect=true";
	}
	
	//count total price
	public double getPrice() {
		//now services have no price for each service, so just count them as 20 euro, will change in future
		return orderStore.getOrder().getPrice();
	}
	
	
	
}
