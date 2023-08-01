package fr.eni.pizzaHub.dto;

public class OrderRequest {
	private int orderId;
	private boolean isOnlineOrder;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public boolean isOnlineOrder() {
		return isOnlineOrder;
	}
	public void setOnlineOrder(boolean isOnlineOrder) {
		this.isOnlineOrder = isOnlineOrder;
	}
}
