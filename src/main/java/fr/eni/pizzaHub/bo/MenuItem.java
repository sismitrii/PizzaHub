package fr.eni.pizzaHub.bo;

import java.util.List;

public class MenuItem {
	private int menuItemIId;
	private String Name;
	private MenuItemCategory menuItemCategory;
	private int price;
	private List<Product> ingedients;
	
	public MenuItem(int menuItemIId, String name, MenuItemCategory menuItemCategory, int price/*, List<Product> ingredients*/) {
		super();
		this.menuItemIId = menuItemIId;
		Name = name;
		this.menuItemCategory = menuItemCategory;
		this.price = price;
	}
	
	public int getMenuItemIId() {
		return menuItemIId;
	}
	public void setMenuItemIId(int menuItemIId) {
		this.menuItemIId = menuItemIId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public MenuItemCategory getMenuItemCategory() {
		return menuItemCategory;
	}
	public void setMenuItemsCategory(MenuItemCategory menuItemCategory) {
		this.menuItemCategory = menuItemCategory;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public List<Product> getIngedients() {
		return ingedients;
	}
	public void setIngedients(List<Product> ingedients) {
		this.ingedients = ingedients;
	}
	
	
}
