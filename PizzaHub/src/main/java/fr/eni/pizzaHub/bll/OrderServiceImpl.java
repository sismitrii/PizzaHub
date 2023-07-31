package fr.eni.pizzaHub.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.pizzaHub.BusinessException;
import fr.eni.pizzaHub.bo.MenuItem;
import fr.eni.pizzaHub.bo.OnSiteOrder;
import fr.eni.pizzaHub.bo.OnlineOrder;
import fr.eni.pizzaHub.bo.Order;
import fr.eni.pizzaHub.dal.MenuItemRepository;
import fr.eni.pizzaHub.dal.OrderRepository;
import fr.eni.pizzaHub.dto.OrderResponse;

@Service
public class OrderServiceImpl implements OrderService{
	
	private OrderRepository orderRepository;
	private MenuItemRepository menuItemRepository;
	
	public OrderServiceImpl(OrderRepository orderRepository,MenuItemRepository menuItemRepository ) {
		this.orderRepository = orderRepository;
		this.menuItemRepository = menuItemRepository;
	}

	@Override
	public OnSiteOrder findOrder(int orderId){
		OnSiteOrder onSiteOrder = orderRepository.findOnSiteOrderByOrderId(orderId);
		List<MenuItem> menuItems = menuItemRepository.findMenuItemByOrderId(onSiteOrder.getOrderId());
		if ( menuItems != null) {
			onSiteOrder.setMenuItems(menuItems);
		}
		return onSiteOrder;
	}

	@Override
	public OrderResponse findOrderToPrepare() {
		List<Order> orders = orderRepository.getAllOrderToPrepare();
		OrderResponse orderResponse = new OrderResponse();
		for (Order order : orders) {
			List<MenuItem> menuItems = menuItemRepository.findMenuItemByOrderId(order.getOrderId());
			if ( menuItems != null && menuItems.size() > 0) {
				order.setMenuItems(menuItems);
			}
			
			if (order instanceof OnSiteOrder) {
				orderResponse.addOnSiteOrder((OnSiteOrder)order );
			} else if(order instanceof OnlineOrder) {
				orderResponse.addOnlineOrder((OnlineOrder)order);
			}
		}
		return orderResponse;
	}

	@Override
	public void validateOrder(int orderId) {
		orderRepository.setOrderToBePrepared(orderId);
	}

}
