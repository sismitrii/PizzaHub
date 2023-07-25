package fr.eni.pizzaHub.bo;

import java.util.List;

public class OnSiteOrder extends Order{
	
	private List<MenuItem> menuItems;
	private int tableNumber;
	private int seats;
	private  int actualStep;
	
// COnstructor with pprivate method getActualStep to test
	public OnSiteOrder(int orderId, List<MenuItem> menuItems, int tableNumber, int seats) {
		super(orderId);
		this.menuItems = menuItems;
		this.tableNumber = tableNumber;
		this.seats = seats;
		this.actualStep = getActualStep(menuItems);
	}
	
	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
	public int getTableNumber() {
		return tableNumber;
	}
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public int getActualStep() {
		return actualStep;
	}
	public void setActualStep(int actualStep) {
		this.actualStep = actualStep;
	}
	
	///TO TEST
	private int getActualStep(List<MenuItem> menuItems) {
		if( menuItems.stream().filter(x -> x.getMenuItemsCategory() == MenuItemCategory.ENTREE).findFirst().orElse(null) != null){
			return 1; 
		} else if (menuItems.stream().filter(x -> x.getMenuItemsCategory() == MenuItemCategory.PLAT || x.getMenuItemsCategory() ==  MenuItemCategory.PIZZA).findFirst().orElse(null) != null) {
			return 2;
		} else {
			return 3;
		}
	}
	
	
}
