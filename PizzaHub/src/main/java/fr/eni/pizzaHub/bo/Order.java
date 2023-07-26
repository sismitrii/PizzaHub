package fr.eni.pizzaHub.bo;

public abstract class Order {
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
	
	
}
