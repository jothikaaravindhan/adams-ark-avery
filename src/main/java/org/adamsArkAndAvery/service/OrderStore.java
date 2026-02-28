package org.adamsArkAndAvery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.adamsArkAndAvery.model.Order;

@ManagedBean(name = "orderStore")
@ApplicationScoped
public class OrderStore {
	private List<Order> orderList = new ArrayList<>();

	// Filters
	private String filterPetType = "";
	private Integer filterPod = null;

	// Filtered orders
	private List<Order> filteredOrders = new ArrayList<>();

	public OrderStore() {
		// initialize filteredOrders
		filteredOrders = new ArrayList<>(orderList);
	}

	public void addOrder(Order order) {
		orderList.add(order);
		applyFilters();
		System.out.println("Order added for: " + order.getBooking().getPetName());
	}

	public List<Order> getAllOrders() {
		return orderList;
	}

	public Order getOrder() {
		return orderList.get(orderList.size() - 1);
	}

	public void deleteOrder() {
		orderList.remove(orderList.size() - 1);
		applyFilters();
	}
	public void applyFilters() {
		System.out.println("Filter type "+filterPetType);
		System.out.println("Filter pod "+filterPod);
		filteredOrders = orderList.stream()
				.filter(o -> (filterPetType == null || filterPetType.isEmpty() || o.getBooking().getPetType().equalsIgnoreCase(filterPetType)))
				.filter(o -> (filterPod == null || o.getPods() == filterPod))
				.collect(Collectors.toList());
	}

	public void clearFilters() {
		filterPetType = "";
		filterPod = null;
		applyFilters();
	}

	public List<Order> getFilteredOrders() {
		return filteredOrders;
	}

	public String getFilterPetType() {
		return filterPetType;
	}

	public void setFilterPetType(String filterPetType) {
		this.filterPetType = filterPetType;
	}

	public Integer getFilterPod() {
		return filterPod;
	}

	public void setFilterPod(Integer filterPod) {
		this.filterPod = filterPod;
	}

	public List<Integer> getAvailablePods() {
		return orderList.stream()
				.map(Order::getPods)
				.distinct()
				.sorted()
				.collect(Collectors.toList());
	}
	public List<String> getAvailablePetTypes() {
		return orderList.stream()
				.map(o -> o.getBooking().getPetType())
				.distinct()
				.sorted()
				.collect(Collectors.toList());
	}
}
