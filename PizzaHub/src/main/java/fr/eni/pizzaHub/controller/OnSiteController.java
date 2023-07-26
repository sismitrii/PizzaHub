package fr.eni.pizzaHub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.pizzaHub.bll.MenuItemService;
import fr.eni.pizzaHub.bll.OnSiteService;
import fr.eni.pizzaHub.bo.MenuItem;
import fr.eni.pizzaHub.bo.MenuItemCategory;
import fr.eni.pizzaHub.bo.OnSiteOrder;

@RestController
@RequestMapping("onsite")
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
	@ResponseBody
	public OnSiteOrder getOrderById(@PathVariable("orderId") int orderId ){
		return onSiteService.findOrder(orderId);
	}
}
