package fr.eni.pizzaHub.dal;

import fr.eni.pizzaHub.DALEXception;
import fr.eni.pizzaHub.bo.OnSiteOrder;
import fr.eni.pizzaHub.dto.OnlineOrderRequest;

public interface OrderRepository {
	public OnSiteOrder findOnSiteOrderByOrderId(int orderId);
	public OnSiteOrder findOnSiteOrderByTableNumber(int tableNumber);
	public void createRestaurantOrder(int tableNumber, int seatNumber);
	public void updateSeatNumber(int TableNumber, int seatNumber);
	public void addMenuItemToOrder(int orderId, int menuItemToAddId);
	public void deleteOrder(int orderId) throws DALEXception;
	public void setOrderToBePrepared(int orderId);
	public void createOnlineOrder(OnlineOrderRequest order);
}
