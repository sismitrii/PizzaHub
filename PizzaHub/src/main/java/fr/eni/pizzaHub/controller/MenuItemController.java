package fr.eni.pizzaHub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.pizzaHub.bll.MenuItemService;
import fr.eni.pizzaHub.bo.MenuItem;
import fr.eni.pizzaHub.bo.MenuItemCategory;

@RestController
@RequestMapping("menuItem")
public class MenuItemController {
	private final MenuItemService menuItemService;
	
	public MenuItemController(MenuItemService menuItemService) {
		this.menuItemService = menuItemService;
	}

	@GetMapping("/allCategory")
	public List<String> getAllItemCategory(){
		 return menuItemService.getAllItemCategory();
	}

	@GetMapping("/test")
	public List<MenuItem> getAll(){
		 return menuItemService.getAll();
	}
	
	@GetMapping("/{menuItemCategory}")
	@ResponseBody
	public List<MenuItem> getAllMenuIItemByCategory(@PathVariable("menuItemCategory") MenuItemCategory menuItemCategory ){
		return menuItemService.findAllItemForCategory(menuItemCategory);
	}
}
