package fr.eni.pizzaHub.dto;

public class OrderRequest {
	private int orderId;
	private Boolean isOnlineOrder;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Boolean isOnlineOrder() {
		return isOnlineOrder;
	}
	public void setisOnlineOrder(Boolean isOnlineOrder) {
		this.isOnlineOrder = isOnlineOrder;
	}
}
