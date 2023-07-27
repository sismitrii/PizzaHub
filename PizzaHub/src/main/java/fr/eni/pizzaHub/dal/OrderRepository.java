package fr.eni.pizzaHub.dal;

import fr.eni.pizzaHub.bo.OnSiteOrder;

public interface OrderRepository {
	public OnSiteOrder findOnSiteOrderByOrderId(int orderId);
	public OnSiteOrder findOnSiteOrderByTableNumber(int tableNumber);
	public void createRestaurantOrder(int tableNumber, int seatNumber);
	public void updateSeatNumber(int TableNumber, int seatNumber);
}
