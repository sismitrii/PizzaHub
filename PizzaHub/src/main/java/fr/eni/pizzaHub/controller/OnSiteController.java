package fr.eni.pizzaHub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.pizzaHub.BusinessException;
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
	
	@GetMapping("/order/table/{tableNumber}")
	public ResponseEntity<OnSiteOrder> getOrderByTableNumber(@PathVariable("tableNumber") int tableNumber ){
		//il faudrait mettre le status à 204
		//@ResponseStatus(HttpStatus.NO_CONTENT);
		OnSiteOrder onSiteOrder = onSiteService.findOrderByTableNumber(tableNumber);
		if (onSiteOrder == null) {
			System.out.println("test");
			return new ResponseEntity<OnSiteOrder>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<OnSiteOrder>(onSiteOrder, HttpStatus.OK);
	}
	
	@PostMapping("/order/setSeatsNumber")
	public ResponseEntity<Object>  setSeatsNumber(@RequestBody OnSiteOrderRequest request){
		if (onSiteService.setSeatsNumber(request.getTableNumber(), request.getSeatNumber())) {
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Object>(HttpStatus.OK);

		}
	}
	
	@PostMapping("/order/addMenuItem")
	public ResponseEntity<Object> addMenuItem(@RequestBody OnSiteOrderRequest request){
		try {
			onSiteService.addMenuItem(request.getTableNumber(), request.getMenuItemToAdd());
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
