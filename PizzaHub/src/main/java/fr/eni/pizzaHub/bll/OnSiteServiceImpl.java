package fr.eni.pizzaHub.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.eni.pizzaHub.bo.MenuItem;
import fr.eni.pizzaHub.bo.MenuItemCategory;
import fr.eni.pizzaHub.bo.OnSiteOrder;
import fr.eni.pizzaHub.dal.MenuItemRepository;


@Service
public class OnSiteServiceImpl implements OnSiteService {

	@Value("${tableNumber.first}")
	private int firstTableNumber;
	@Value("${tableNumber.last}")
	private int lastTableNumber;
	@Value("${tableNumber.excluded}")
	private List<Integer> excludedTableNumber;
	
//	private MenuItemRepository menuItemRepository;
//	
//	
//	public OnSiteServiceImpl(MenuItemRepository menuItemRepository) {
//		this.menuItemRepository = menuItemRepository;
//	}

	
	@Override
	public List<Integer> getAllTableNumber() {
		List<Integer> tableNumbers = new ArrayList<Integer>();
		for (int i= firstTableNumber; i <= lastTableNumber; i++ ) {
			if (!excludedTableNumber.contains(i)) {
				tableNumbers.add(i);
			}
		}
		return tableNumbers;
	}

	@Override
	public OnSiteOrder findOrder(int orderId){
		
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		menuItems.add(new MenuItem(1,"Salade de chèvre  chaud" , MenuItemCategory.ENTREE, 9.90));
		menuItems.add(new MenuItem(2,"Burrata" , MenuItemCategory.ENTREE, 10.90));
		menuItems.add(new MenuItem(3,"Jambon" , MenuItemCategory.ENTREE, 8.90));
		menuItems.add(new MenuItem(4,"Lasagne" , MenuItemCategory.PLAT, 14.90));
		menuItems.add(new MenuItem(5,"calzone" , MenuItemCategory.PIZZA, 14.90));
		menuItems.add(new MenuItem(6,"Linguine al pesto" , MenuItemCategory.PLAT, 18.90));
		menuItems.add(new MenuItem(7,"Panna Cotta" , MenuItemCategory.DESSERT, 9.90));
		menuItems.add(new MenuItem(7,"Panna Cotta" , MenuItemCategory.DESSERT, 9.90));
		menuItems.add(new MenuItem(8,"Tiramisu" , MenuItemCategory.DESSERT, 9.90));
		
		OnSiteOrder onSiteOrder = new OnSiteOrder(orderId, menuItems, 12, 3);
		
		return onSiteOrder;  
		//return menuItemRepository.findMenuItemsByOrderId(orderId);
	}
}
