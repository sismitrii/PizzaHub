package fr.eni.pizzaHub.dto;

public class OnSiteOrderRequest {
	private int tableNumber;
	private int seatNumber;
	private int menuItemToAdd;
	
	public int getTableNumber() {
		return tableNumber;
	}
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public int getMenuItemToAdd() {
		return menuItemToAdd;
	}
	public void setMenuItemToAdd(int menuItemToAdd) {
		this.menuItemToAdd = menuItemToAdd;
	}
}
