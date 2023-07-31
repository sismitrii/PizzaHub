package fr.eni.pizzaHub.bll;

import fr.eni.pizzaHub.bo.OnSiteOrder;
import fr.eni.pizzaHub.dto.OrderResponse;

public interface OrderService {
	public OnSiteOrder findOrder(int orderId);
	public OrderResponse findOrderToPrepare();
	public void validateOrder(int orderId);

}
