package fr.eni.pizzaHub.bo;

import java.time.LocalDateTime;
import java.util.List;

public class OnlineOrder extends Order{
	private List<Pizza> pizzas;
	private String customerName;
	private LocalDateTime timeSlot;
	
	public OnlineOrder(int orderId, List<Pizza> pizzas, String customerName, LocalDateTime timeSlot) {
		super(orderId);
		this.pizzas = pizzas;
		this.customerName = customerName;
		this.timeSlot = timeSlot;
	}
	
	public List<Pizza> getPizzas() {
		return pizzas;
	}
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public LocalDateTime getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(LocalDateTime timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	
	
}
