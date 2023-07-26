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

	@Override
	public List<String> getAllItemCategory() {
		List<String> menuItemCategories = new ArrayList<>();
		for(MenuItemCategory menuIitemCategory : MenuItemCategory.values()) {
			menuItemCategories.add(menuIitemCategory.toString());
			}
		return menuItemCategories;
	}

	@Override
	public List<MenuItem> findAllItemForCategory(MenuItemCategory menuItemCategory) {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		menuItems.add(new MenuItem(1, "Lasagne", MenuItemCategory.PLAT, 1450));
		menuItems.add(new MenuItem(2, "Pasta al Carbonara", MenuItemCategory.PLAT, 1250));

		// touver tout les menuItem
		//menuItemRepository.findAllItemForCategory();
		return menuItems;
	}
	
	
	
}
