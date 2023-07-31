package fr.eni.pizzaHub.bo;

import java.util.ArrayList;
import java.util.List;

public abstract class Order {
	private List<MenuItem> menuItems = new ArrayList<MenuItem>();
	private int orderId;
	private boolean isCompleted;

	public Order(int orderId) {
		super();
		this.orderId = orderId;
		this.isCompleted = false;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public boolean isCompleted() {
		return isCompleted;
	}
	
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
}
