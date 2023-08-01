package fr.eni.pizzaHub.dal;

import java.util.List;

import fr.eni.pizzaHub.DALEXception;
import fr.eni.pizzaHub.bo.OnSiteOrder;
import fr.eni.pizzaHub.bo.OnlineOrder;
import fr.eni.pizzaHub.bo.Order;

public interface OrderRepository {
	public OnSiteOrder findOnSiteOrderByOrderId(int orderId);
	public OnSiteOrder findOnSiteOrderByTableNumber(int tableNumber);
	public OnlineOrder findOnlineOrderById(int orderId);
	public void createRestaurantOrder(int tableNumber, int seatNumber);
	public void updateSeatNumber(int TableNumber, int seatNumber);
	public void addMenuItemToOrder(int orderId, int menuItemToAddId);
	public void deleteOrder(int orderId) throws DALEXception;
	public void setOrderToBePrepared(int orderId);
	public List<Order> getAllOrderToPrepare();
	
}
