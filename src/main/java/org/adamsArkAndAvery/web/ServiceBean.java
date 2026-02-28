package org.adamsArkAndAvery.web;

import org.adamsArkAndAvery.model.Service;
import org.adamsArkAndAvery.service.serviceService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@ManagedBean(name = "serviceBean")
@ViewScoped
public class ServiceBean implements Serializable {
	//Admin add new service
    private String newName;
    private String newDescription;
    private Double newPrice;     
    private String newCategory;
    private String newAnimal;
    
    private List<Service> allServices;
    private serviceService serviceService;
    private String selectedAnimal = "";

    public ServiceBean() {
        serviceService = new serviceService();
     // Retrieving all available services from the service layer
        allServices = serviceService.getAllAvailableServices();
    }

    public List<Service> getAllServices() {
        return allServices;
    }

    public void setAllServices(List<Service> allServices) {
        this.allServices = allServices;
    }
    
    // Removes the service from the in-memory list
    public void deleteService(Service service) {
        allServices.remove(service);
     // Deletion at service layer
        serviceService.removeService(service);
    }

    //list of animals used for dropdown
    public List<String> getAnimalTypes(){
        return allServices.stream()
                .map(Service::getAnimal)
                .filter(a -> a != null && !a.isBlank())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public String getSelectedAnimal() {return selectedAnimal;}
    public void setSelectedAnimal(String selectedAnimal) {this.selectedAnimal = selectedAnimal;}
    
    //Groups services by the animal type. Returns a map with animal names as keys
    public Map<String, List<Service>> getServicesByAnimal(){
    	List<Service> working =(selectedAnimal == null || selectedAnimal.isBlank())
                ? allServices
                : allServices.stream().filter(s -> s.getAnimal()
                .equals(selectedAnimal))
                .collect(Collectors.toList());

        return  working.stream().collect(Collectors.groupingBy(Service::getAnimal,
        TreeMap::new,
        Collectors.toList()));
    }
    //Admin add new service
    public void addNewService() {
        double price = (newPrice == null ? -1.0 : newPrice);
        Service s = new Service(newName, newDescription, price, newCategory, newAnimal);

        String result = serviceService.addNewServices(s);

        FacesContext context = FacesContext.getCurrentInstance();
        switch (result) {
        	case "CREATED":
        		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Service added"));
	            reloadList();
	            clearForm();
	            break;
	        case "DUPLICATE":
	        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "The service already exists", ""));
	            break;
	        default:
	        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid data", "Check name/price"));
        }
    }
    
    //Only admin can read
    public boolean isAdmin() {
        Object role = javax.faces.context.FacesContext.getCurrentInstance()
            .getExternalContext().getSessionMap().get("userRole");
        return role != null && "ADMIN".equalsIgnoreCase(role.toString().trim());
    }
    
    //reload
    private void reloadList() {
        this.allServices = serviceService.getAllAvailableServices();
    }
    
    //Reset
    private void clearForm() {
        newName = null;
        newDescription = null;
        newPrice = null;
        newCategory = null;
        newAnimal = null;
    }
    
    //getters&setters
	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getNewDescription() {
		return newDescription;
	}

	public void setNewDescription(String newDescription) {
		this.newDescription = newDescription;
	}

	public Double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(Double newPrice) {
		this.newPrice = newPrice;
	}

	public String getNewCategory() {
		return newCategory;
	}

	public void setNewCategory(String newCategory) {
		this.newCategory = newCategory;
	}

	public String getNewAnimal() {
		return newAnimal;
	}

	public void setNewAnimal(String newAnimal) {
		this.newAnimal = newAnimal;
	}
}