package org.adamsArkAndAvery.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.adamsArkAndAvery.model.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class serviceServiceTest {

	private serviceService serviceService;

    @BeforeEach
    void setUp() {
        serviceService = new serviceService();
    }
    
    @Test
    void testGetAllAvailableServicesNotNullAndSize() {
        List<Service> services = serviceService.getAllAvailableServices();
        assertNotNull(services);
        assertTrue(services.size()>=56);
    }
    
    @Test
    void testFirstServiceDetails() {
        List<Service> services = serviceService.getAllAvailableServices();
        Service first = services.get(0);

        assertEquals("Pods", first.getName());
        assertEquals("Private kennel pod for comfort and security", first.getDescription());
        assertEquals(50.0, first.getPrice());
        assertEquals("Housing", first.getCategory());
    }
    
    @Test
    void testContainsSpecificService() {
        List<Service> services = serviceService.getAllAvailableServices();

        boolean found = false;
        for (Service s : services) {
            if (s.getName().equals("Customised Diet") &&
                s.getCategory().equals("Diet") &&
                s.getPrice() == 25.0) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }
    @Test
    void testDuplicatedNameWithSameAnimal() {
    	serviceService s= new serviceService();
    	String output = s.addNewServices(new Service("Pods","Private kennel pod for comfort and security",50,"Housing","Dog"));
    	assertEquals("DUPLICATE",output);
    }
    
    @Test
    void testCreatedWithSameNameDifferentAnimal() {
    	serviceService s= new serviceService();
    	String output = s.addNewServices(new Service("Grooming", "A", 12.0, "Beauty", "Horse"));
    	assertEquals("CREATED",output);
    }
    
    @Test
    void testInvalidWithEmptyNameOrNegativePrice() {
    	serviceService s= new serviceService();
    	assertEquals("INVALID", s.addNewServices(new Service("   ", "x", 10.0, "Housing", "Dog")));
        assertEquals("INVALID", s.addNewServices(new Service("Bath", "x", -1.0, "Housing", "Dog")));
    }

}
