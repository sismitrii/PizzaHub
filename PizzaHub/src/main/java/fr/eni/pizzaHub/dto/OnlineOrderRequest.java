package fr.eni.pizzaHub.dto;

import java.time.LocalDateTime;
import java.util.List;

import fr.eni.pizzaHub.dto.PizzaRequest;

public class OnlineOrderRequest {
    private List<PizzaRequest> pizzas;
    private String customerName;
    private LocalDateTime timeSlot;

    public List<PizzaRequest> getPizzas() {
		return pizzas;
	}
	public void setPizzas(List<PizzaRequest> pizzas) {
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