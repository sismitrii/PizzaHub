package fr.eni.pizzaHub.dal;

import java.util.List;

import fr.eni.pizzaHub.bo.MenuItem;

public interface MenuItemRepository {
	public List<MenuItem> getAll();
}
