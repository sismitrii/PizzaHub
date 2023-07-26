package fr.eni.pizzaHub.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.pizzaHub.bo.MenuItem;
import fr.eni.pizzaHub.bo.MenuItemCategory;
import fr.eni.pizzaHub.dal.MenuItemRepository;

@Service
public class MenuItemServiceImpl implements MenuItemService {
//	private MenuItemRepository  menuItemRepository;
//
//	public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
//		this.menuItemRepository = menuItemRepository;
//	}

	@Override
	public List<MenuItem> getAll() {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		menuItems.add(new MenuItem(1,"Margarita", MenuItemCategory.PIZZA, 990));
		return menuItems;
		
		//return menuItemRepository.getAll();
	}
}
