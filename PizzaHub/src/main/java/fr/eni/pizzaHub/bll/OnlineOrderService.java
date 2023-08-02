package fr.eni.pizzaHub.bll;

import fr.eni.pizzaHub.bo.OnlineOrder;
import fr.eni.pizzaHub.dto.OnlineOrderRequest;

public interface OnlineOrderService {
    public boolean createOrder(OnlineOrderRequest order);
}