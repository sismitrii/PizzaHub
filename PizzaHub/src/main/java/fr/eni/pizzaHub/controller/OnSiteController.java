package fr.eni.pizzaHub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.pizzaHub.bll.MenuItemService;
import fr.eni.pizzaHub.bll.OnSiteService;
import fr.eni.pizzaHub.bo.MenuItem;
import fr.eni.pizzaHub.bo.MenuItemCategory;
import fr.eni.pizzaHub.bo.OnSiteOrder;
import fr.eni.pizzaHub.dto.OnSiteOrderRequest;

@RestController
@RequestMapping("/onsite")
public class OnSiteController {
	
	private final OnSiteService onSiteService;
	
	public OnSiteController(OnSiteService onSiteService) {
		this.onSiteService = onSiteService;
	}
	
	@GetMapping("/allTableNumber")
	public List<Integer> getAllTableNumber(){
		return onSiteService.getAllTableNumber();
	}
	
	@GetMapping("/order/{orderId}")
	public OnSiteOrder getOrderById(@PathVariable("orderId") int orderId ){
		return onSiteService.findOrder(orderId);
	}
	
	@PostMapping("/order/setSeatsNumber")
	public boolean setSeatsNumber(@RequestBody OnSiteOrderRequest request){
		System.out.println(request.getSeatNumber());
		return onSiteService.setSeatsNumber(request.getTableNumber(), request.getSeatNumber());
	}
	
}
