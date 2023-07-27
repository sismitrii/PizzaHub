package fr.eni.pizzaHub.dal;

import java.util.List;

import fr.eni.pizzaHub.bo.MenuItem;
import fr.eni.pizzaHub.bo.MenuItemCategory;

public interface MenuItemRepository {
	public List<MenuItem> getAll();
	
	public List<MenuItem> findAllItemForCategory(MenuItemCategory menuItemCategory);
	
	public List<MenuItem> findMenuItemByOrderId(int orderId);
}
