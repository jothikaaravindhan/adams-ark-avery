package org.adamsArkAndAvery.web;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.adamsArkAndAvery.model.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServiceBeanTest {
	private ServiceBean serviceBean;
	
	@BeforeEach
    void setUp() {
        serviceBean = new ServiceBean(); 
    }
	
	@Test
    void testDefaultServiceListNotEmpty() {
        List<Service> services = serviceBean.getAllServices();

        assertNotNull(services);
        assertEquals(54, services.size());
    }
	
	@Test
    void testSetAndGetAllServices() {
        List<Service> testServices = new ArrayList<>();
        testServices.add(new Service("Test1", "Description1", 10.0, "Category1", "Animal1"));
        testServices.add(new Service("Test2", "Description2", 20.0, "Category2", "Animal2"));

        serviceBean.setAllServices(testServices);

        List<Service> result = serviceBean.getAllServices();
        assertEquals(2, result.size());
        assertEquals("Test1", result.get(0).getName());
        assertEquals("Category2", result.get(1).getCategory());
    }
	
	@Test
    void testEmptyServiceList() {
        serviceBean.setAllServices(new ArrayList<>());
        assertTrue(serviceBean.getAllServices().isEmpty());
    }

    // --- helpers for the new tests ---
    private List<Service> sampleServices() {
        List<Service> list = new ArrayList<>();
        list.add(new Service("Dog Wash", "Full wash", 15.0, "Grooming", "Dog"));
        list.add(new Service("Dog Checkup", "Basic health", 25.0, "Health", "Dog"));
        list.add(new Service("Claw Trim", "Quick trim", 12.0, "Grooming", "Cat"));
        list.add(new Service("Wing Check", "Feather/wing check", 18.0, "Health", "Bird"));
        return list;
    }

    private int totalItems(java.util.Map<String, List<Service>> map) {
        return map.values().stream().mapToInt(List::size).sum();
    }

    /* ===== NEW TESTS FOR THE PET-TYPE FILTER ===== */

    @Test
    void getAnimalTypes_returnsDistinctSorted_nonBlank() {
        // includes duplicates + blank/null to ensure filtering
        List<Service> src = new ArrayList<>();
        src.add(new Service("A","",10.0,"","Dog"));
        src.add(new Service("B","",10.0,"","Cat"));
        src.add(new Service("C","",10.0,"","Dog"));
        src.add(new Service("D","",10.0,"","Bird"));
        src.add(new Service("E","",10.0,"","  ")); // blank
        src.add(new Service("F","",10.0,"",null)); // null

        serviceBean.setAllServices(src);

        List<String> types = serviceBean.getAnimalTypes();
        assertEquals(List.of("Bird","Cat","Dog"), types); // distinct + sorted + no blanks
    }

    @Test
    void getServicesByAnimal_returnsAllGroups() {
        serviceBean.setAllServices(sampleServices());
        serviceBean.setSelectedAnimal(""); // blank = All

        var map = serviceBean.getServicesByAnimal();

        // groups present
        assertEquals(3, map.keySet().size());
        assertTrue(map.containsKey("Dog"));
        assertTrue(map.containsKey("Cat"));
        assertTrue(map.containsKey("Bird"));

        // item count preserved
        assertEquals(sampleServices().size(), totalItems(map));
    }

    @Test
    void getServicesByAnimal_onlyDogGroupAndItems() {
        serviceBean.setAllServices(sampleServices());
        serviceBean.setSelectedAnimal("Dog");

        var map = serviceBean.getServicesByAnimal();

        assertEquals(1, map.keySet().size());
        assertTrue(map.containsKey("Dog"));
        assertEquals(2, map.get("Dog").size());
        assertTrue(map.get("Dog").stream().allMatch(s -> "Dog".equalsIgnoreCase(s.getAnimal())));
    }

    @Test
    void getServicesByAnimal_unknownSelection_returnsEmptyMap() {
        serviceBean.setAllServices(sampleServices());
        serviceBean.setSelectedAnimal("LizardWizard");

        var map = serviceBean.getServicesByAnimal();
        assertTrue(map.isEmpty());
    }
    
    /* ===== ADMIN DELETE TESTS ===== */
    
    @Test
    void testDeleteServiceRemovesFromList() {
        List<Service> allServices = serviceBean.getAllServices();
        int initialSize = allServices.size();

        Service serviceToDelete = allServices.get(0);
        serviceBean.deleteService(serviceToDelete);
       // Service should be removed from the list
        assertFalse(serviceBean.getAllServices().contains(serviceToDelete));
       // List size should shrink by 1
        assertEquals(initialSize - 1, serviceBean.getAllServices().size());
    }

    @Test
    void testDeleteNonExistingServiceDoesNothing() {
        List<Service> allServices = serviceBean.getAllServices();
        int initialSize = allServices.size();

        Service fakeService = new Service("Fake", "Does not exist", 0.0, "None", "None");
        serviceBean.deleteService(fakeService);
       // List size should remain unchanged when deleting non-existing service
        assertEquals(initialSize, serviceBean.getAllServices().size());
    }

    @Test
    void testDeleteMultipleServices() {
        List<Service> allServices = serviceBean.getAllServices();
        int initialSize = allServices.size();

        Service first = allServices.get(0);
        Service second = allServices.get(1);

        serviceBean.deleteService(first);
        serviceBean.deleteService(second);

        assertFalse(serviceBean.getAllServices().contains(first));
        assertFalse(serviceBean.getAllServices().contains(second));
        // "List size should shrink by 2"
        assertEquals(initialSize - 2, serviceBean.getAllServices().size());
    }

}
