package fr.eni.pizzaHub.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.eni.pizzaHub.bo.MenuItem;
import fr.eni.pizzaHub.bo.MenuItemCategory;
import fr.eni.pizzaHub.bo.OnSiteOrder;
import fr.eni.pizzaHub.dal.MenuItemRepository;
import fr.eni.pizzaHub.dal.OrderRepository;


@Service
public class OnSiteServiceImpl implements OnSiteService {

	@Value("${tableNumber.first}")
	private int firstTableNumber;
	@Value("${tableNumber.last}")
	private int lastTableNumber;
	@Value("${tableNumber.excluded}")
	private List<Integer> excludedTableNumber;
	
	private MenuItemRepository menuItemRepository;
	private OrderRepository orderRepository;
	
	
	public OnSiteServiceImpl(MenuItemRepository menuItemRepository, OrderRepository orderRepository) {
		this.menuItemRepository = menuItemRepository;
		this.orderRepository = orderRepository;;
	}

	
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
		OnSiteOrder onSiteOrder = orderRepository.findOnSiteOrderByOrderId(orderId);
		List<MenuItem> menuItems = menuItemRepository.findMenuItemByOrderId(onSiteOrder.getOrderId());
		if ( menuItems != null) {
			onSiteOrder.setMenuItems(menuItems);
		}
		return onSiteOrder;
	}


	@Override
	public boolean setSeatsNumber(int tableNumber, int seatNumber) {
		OnSiteOrder onSiteOrder = orderRepository.findOnSiteOrderByTableNumber(tableNumber);
		if (onSiteOrder == null) {
			orderRepository.createRestaurantOrder(tableNumber, seatNumber);
			return true;
		} else {
			orderRepository.updateSeatNumber(tableNumber, seatNumber);
			return false;
		}
		// faire une requete pour voir s'il existe une order avec cette table en BDD 
		//si oui on fait une requete pour update son nombre de seat
		//si non on fait une requete pour créer la restaurantOrder en BDD
	}
}
