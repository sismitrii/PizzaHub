package fr.eni.pizzaHub.bll;

import java.util.List;

import fr.eni.pizzaHub.bo.MenuItem;
import fr.eni.pizzaHub.bo.MenuItemCategory;

public interface MenuItemService {
	
//	public List<MenuItem> getAll();
	public List<String> getAllItemCategory();
	public List<MenuItem> findAllItemForCategory(MenuItemCategory menuItemCategory);
	
}
