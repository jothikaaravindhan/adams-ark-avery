package org.adamsArkAndAvery.model;

public class Service {
    private String name;
    private String description;
    private double price;
    private String category;
    private String animal;

    public Service() {}

    public Service( String name, String description, double price, String category, String animal) {
    	this.animal = animal;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
    
    
 
    public String getAnimal() { return animal; }
	public void setAnimal(String animal) { this.animal = animal;}

	public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getformattedPrice() {
    	return String.format("%.2f", price);
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
