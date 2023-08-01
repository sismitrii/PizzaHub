package fr.eni.pizzaHub.bo;

import java.time.LocalTime;

public class OnlineOrder extends Order{
	private String customerName;
	private LocalTime timeSlot;
	
	public OnlineOrder(int orderId, String customerName, LocalTime timeSlot) {
		super(orderId);
		this.customerName = customerName;
		this.timeSlot = timeSlot;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public LocalTime getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(LocalTime timeSlot) {
		this.timeSlot = timeSlot;
	}
}
