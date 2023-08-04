package fr.eni.pizzaHub.bll;

import org.springframework.stereotype.Service;

import fr.eni.pizzaHub.dal.OrderRepository;
import fr.eni.pizzaHub.dto.OnlineOrderRequest;

@Service
public class OnlineOrderServiceImpl implements OnlineOrderService {
    private OrderRepository orderRepository;

    public OnlineOrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
    }

    @Override
    public boolean createOrder(OnlineOrderRequest order) {
        orderRepository.createOnlineOrder(order);
        return true;
    }
}