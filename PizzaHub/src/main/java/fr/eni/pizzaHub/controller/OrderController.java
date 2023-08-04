package fr.eni.pizzaHub.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.pizzaHub.bll.OrderService;
import fr.eni.pizzaHub.bo.OnSiteOrder;
import fr.eni.pizzaHub.bo.Order;
import fr.eni.pizzaHub.dto.OrderRequest;
import fr.eni.pizzaHub.dto.OrderResponse;

@RestController
@CrossOrigin
public class OrderController {
	
	private OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@PostMapping("/order/byOrderId")
	public Order getOrderById(@RequestBody OrderRequest orderRequest){
		System.out.println(orderRequest.isOnlineOrder());
		return orderService.findOrder(orderRequest.getOrderId(), orderRequest.isOnlineOrder());
	}
	
	@CrossOrigin
	@GetMapping("/order/toPrepare")
	public OrderResponse getOrderById(){
		return orderService.findOrderToPrepare();
	}
	
	@PostMapping("/order/validate")
	public void validateOrderReady(@RequestBody int orderId) {
		orderService.validateOrder(orderId);
	}
}
