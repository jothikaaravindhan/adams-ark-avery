package org.adamsArkAndAvery.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.SessionScoped;
import org.adamsArkAndAvery.model.Service;

@SessionScoped
public class serviceService {

    // Static list to allow for editing
    private static List<Service> services = new ArrayList<>();

    static {
     // ===================== DOG SERVICES =====================
        services.add(new Service("Pods", "Private kennel pod for comfort and security", 50.0, "Housing", "Dog")); 
        services.add(new Service("Grooming", "Complete grooming session", 45.0, "Beauty", "Dog")); 
        services.add(new Service("Photo Updates", "Regular photo updates sent to owner", 20.0, "Photography", "Dog")); 
        services.add(new Service("Playtime", "Supervised play with toys and staff", 30.0, "Recreation", "Dog")); 
        services.add(new Service("Customised Diet", "Tailored food based on breed and health", 35.0, "Diet", "Dog")); 
        services.add(new Service("Socialisation Activities", "Interaction with other dogs in controlled environment", 40.0, "Training", "Dog")); 
        services.add(new Service("Personalised Care Package", "Combination of premium services", 100.0, "Care", "Dog")); 

          

     // ===================== CAT SERVICES =====================
        services.add(new Service("Pods", "Private pod with soft bedding and vertical space", 50.0, "Housing", "Cat")); 
        services.add(new Service("Grooming", "Gentle brushing and hygiene care", 40.0, "Beauty", "Cat")); 
        services.add(new Service("Photo Updates", "Cute moments sent to owner", 20.0, "Photography", "Cat")); 
        services.add(new Service("Playtime", "Feather toys and climbing time", 25.0, "Recreation", "Cat")); 
        services.add(new Service("Customised Diet", "Food tailored to age and medical needs", 30.0, "Diet", "Cat")); 
        services.add(new Service("Socialisation Activities", "Interaction with staff or cat playgroups", 35.0, "Recreation", "Cat")); 
        services.add(new Service("Personalised Care Package", "Deluxe care with add-ons", 90.0, "Care", "Cat")); 

          

        // ===================== BIRD SERVICES =====================
        services.add(new Service("Pods", "Spacious aviary pod with perches", 45.0, "Housing", "Bird")); 
        services.add(new Service("Grooming", "Feather care and beak trim", 30.0, "Health", "Bird")); 
        services.add(new Service("Photo Updates", "Photos sent to owner", 15.0, "Photography", "Bird")); 
        services.add(new Service("Playtime", "Out-of-cage interaction time", 20.0, "Recreation", "Bird")); 
        services.add(new Service("Customised Diet", "Seed, pellet, and fruit mix per species", 25.0, "Diet", "Bird")); 
        services.add(new Service("Socialisation Activities", "Whistle training or interaction with other birds", 30.0, "Training", "Bird")); 
        services.add(new Service("Personalised Care Package", "Custom mix of care elements", 80.0, "Care", "Bird")); 

          

     // ===================== REPTILE SERVICES =====================
        services.add(new Service("Pods", "Heated and humidified enclosure", 55.0, "Housing", "Reptile")); 
        services.add(new Service("Grooming", "Shedding assistance and enclosure cleaning", 35.0, "Health", "Reptile")); 
        services.add(new Service("Photo Updates", "Updates with feeding or basking photos", 15.0, "Photography", "Reptile")); 
        services.add(new Service("Playtime", "Safe handling and movement time", 20.0, "Recreation", "Reptile")); 
        services.add(new Service("Customised Diet", "Live or prepped food tailored to species", 30.0, "Diet", "Reptile")); 
        services.add(new Service("Socialisation Activities", "Handling training and exposure to new settings", 25.0, "Recreation", "Reptile")); 
        services.add(new Service("Personalised Care Package", "Temperature, diet, and care combined", 85.0, "Care", "Reptile")); 

          

     // ===================== EXOTIC PET SERVICES =====================
        services.add(new Service("Pods", "Custom enclosure for exotic species", 60.0, "Housing", "Exotic Pet")); 
        services.add(new Service("Grooming", "Care based on species needs", 35.0, "Health", "Exotic Pet")); 
        services.add(new Service("Photo Updates", "Photos during feeding or rest", 15.0, "Photography", "Exotic Pet")); 
        services.add(new Service("Playtime", "Enrichment suited to species", 25.0, "Recreation", "Exotic Pet")); 
        services.add(new Service("Customised Diet", "Exotic diet plan", 40.0, "Diet", "Exotic Pet")); 
        services.add(new Service("Socialisation Activities", "Interaction and light training if suitable", 30.0, "Recreation", "Exotic Pet")); 
        services.add(new Service("Personalised Care Package", "Tailored daily care routine", 95.0, "Care", "Exotic Pet")); 

          

     // ===================== FISH SERVICES =====================
        services.add(new Service("Pods", "Private tank with optimal filtration", 40.0, "Housing", "Fish")); 
        services.add(new Service("Grooming", "Tank cleaning and water testing", 30.0, "Care", "Fish")); 
        services.add(new Service("Photo Updates", "Photos of fish in habitat", 10.0, "Photography", "Fish")); 
        services.add(new Service("Playtime", "Bubble streams and stimulation time", 15.0, "Recreation", "Fish")); 
        services.add(new Service("Customised Diet", "Special flakes, pellets or live food", 20.0, "Diet", "Fish")); 
        services.add(new Service("Socialisation Activities", "Observation of compatible species", 15.0, "Recreation", "Fish")); 
        services.add(new Service("Personalised Care Package", "Full care service including habitat and diet", 70.0, "Care", "Fish")); 

          

     // ===================== AMPHIBIAN SERVICES =====================
        services.add(new Service("Pods", "Moist pod with live plants and misting", 50.0, "Housing", "Amphibian")); 
        services.add(new Service("Grooming", "Misting, moisture and cleaning", 30.0, "Health", "Amphibian")); 
        services.add(new Service("Photo Updates", "Photos while active or feeding", 10.0, "Photography", "Amphibian")); 
        services.add(new Service("Playtime", "Stimulated movement time", 15.0, "Recreation", "Amphibian")); 
        services.add(new Service("Customised Diet", "Live insect and moisture diet", 25.0, "Diet", "Amphibian")); 
        services.add(new Service("Socialisation Activities", "Gentle handling or group housing", 20.0, "Recreation", "Amphibian")); 
        services.add(new Service("Personalised Care Package", "Routine tailored to species", 75.0, "Care", "Amphibian")); 

          

     // ===================== SMALL MAMMAL SERVICES =====================
        services.add(new Service("Pods", "Spacious enclosure with tunnels and bedding", 45.0, "Housing", "Small Mammal")); 
        services.add(new Service("Grooming", "Brushing and hygiene cleaning", 30.0, "Beauty", "Small Mammal")); 
        services.add(new Service("Photo Updates", "Adorable moment photos", 15.0, "Photography", "Small Mammal")); 
        services.add(new Service("Playtime", "Playpen and interactive toys", 25.0, "Recreation", "Small Mammal")); 
        services.add(new Service("Customised Diet", "Veggie, hay and supplement planning", 20.0, "Diet", "Small Mammal")); 
        services.add(new Service("Socialisation Activities", "Interaction with staff and other pets", 30.0, "Recreation", "Small Mammal")); 
        services.add(new Service("Personalised Care Package", "Custom mix of grooming, play, and diet", 85.0, "Care", "Small Mammal")); 

       
    }
    
    // Returns the shared list
    public List<Service> getAllAvailableServices() {
        return services;
    }

    // Removes from shared list
    public void removeService(Service service) {
        services.remove(service);
    }
    //Admin add new services
    public String addNewServices(Service service) {
    	if(service==null) return "INVALID";
    	String name = lowerCase(service.getName());
    	String animal = lowerCase(service.getAnimal());
    	if(name.equals("")||animal.equals("")||service.getPrice()<0) return "INVALID";
    	for(Service s:services) {
    		if(lowerCase(s.getName()).equals(name)&&lowerCase(s.getAnimal()).equals(animal)) {
    			return "DUPLICATE";
    		}
    	}
    	services.add(service);
    	return "CREATED";
    }
    public String lowerCase(String input) {
    	return input == null ? "" : input.trim().toLowerCase();
    }

}
