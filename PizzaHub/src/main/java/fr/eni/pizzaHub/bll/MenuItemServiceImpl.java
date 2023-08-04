package fr.eni.pizzaHub.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.pizzaHub.bo.MenuItem;
import fr.eni.pizzaHub.bo.MenuItemCategory;
import fr.eni.pizzaHub.dal.MenuItemRepository;

@Service
public class MenuItemServiceImpl implements MenuItemService {
	private MenuItemRepository  menuItemRepository;

	public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
		this.menuItemRepository = menuItemRepository;
	}

	@Override
	public List<MenuItem> getAll() {
		
		return menuItemRepository.getAll();
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
		return menuItemRepository.findAllItemForCategory(menuItemCategory);
		 
	}
	
	
	
}
