package fr.eni.pizzaHub.dto;

import java.util.ArrayList;
import java.util.List;

import fr.eni.pizzaHub.bo.OnSiteOrder;
import fr.eni.pizzaHub.bo.OnlineOrder;

public class OrderResponse {
	private List<OnSiteOrder> onSiteOrders = new ArrayList<>();
	private List<OnlineOrder> onlineOrders = new ArrayList<>();
	
	public void addOnSiteOrder(OnSiteOrder onSiteOrder) {
		onSiteOrders.add(onSiteOrder);
	}
	
	public void addOnlineOrder(OnlineOrder onlineOrder) {
		onlineOrders.add(onlineOrder);
	}
	
	public List<OnSiteOrder> getOnSiteOrders() {
		return onSiteOrders;
	}
	public List<OnlineOrder> getOnlineOrders() {
		return onlineOrders;
	}
}
