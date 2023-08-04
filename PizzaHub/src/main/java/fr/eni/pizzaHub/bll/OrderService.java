package fr.eni.pizzaHub.bll;

import fr.eni.pizzaHub.bo.Order;
import fr.eni.pizzaHub.dto.OrderResponse;

public interface OrderService {
	public Order findOrder(int orderId, boolean isOnlineOrder);
	public OrderResponse findOrderToPrepare();
	public void validateOrder(int orderId);

}
