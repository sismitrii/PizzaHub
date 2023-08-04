package fr.eni.pizzaHub.bo;

import java.util.ArrayList;
import java.util.List;

public class OnSiteOrder extends Order{
	
	private int tableNumber;
	private int seats;
	private  int actualStep;
	
// COnstructor with private method getActualStep to test
	public OnSiteOrder(int orderId, int tableNumber, int seats) {
		super(orderId);
		this.tableNumber = tableNumber;
		this.seats = seats;
		if (this.getMenuItems() != null ) {
			this.actualStep = getActualStep(this.getMenuItems());
		}
	}

	public OnSiteOrder(int orderId, int tableNumber, int seats, int actualStep) {
		this(orderId, tableNumber, seats);
		this.actualStep = actualStep;
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
		if( menuItems.stream().filter(x -> x.getMenuItemCategory() == MenuItemCategory.ENTREE).findFirst().orElse(null) != null){
			return 1; 
		} else if (menuItems.stream().filter(x -> x.getMenuItemCategory() == MenuItemCategory.PLAT || x.getMenuItemCategory() ==  MenuItemCategory.PIZZA).findFirst().orElse(null) != null) {
			return 2;
		} else {
			return 3;
		}
	}
	
	
}
