package fr.eni.pizzaHub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.pizzaHub.bll.MenuItemService;
import fr.eni.pizzaHub.bo.MenuItem;

@RestController
@RequestMapping("test")
public class menuItemController {
	private final MenuItemService menuItemService;
	
	public menuItemController(MenuItemService menuItemService) {
		this.menuItemService = menuItemService;
	}

	@GetMapping("/all")
	public List<MenuItem> getAllMenuItem(){
		 return menuItemService.getAll();
	}
}
