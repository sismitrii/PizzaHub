package fr.eni.pizzaHub.bo;

import java.util.List;

public class MenuItem {
	private int menuItemIId;
	private String Name;
	private MenuItemCategory menuItemsCategory;
	private int price;
	private List<Product> ingedients;
	
	public MenuItem(int menuItemIId, String name, MenuItemCategory menuItemsCategory, int price,
			List<Product> ingedients) {
		super();
		this.menuItemIId = menuItemIId;
		Name = name;
		this.menuItemsCategory = menuItemsCategory;
		this.price = price;
		this.ingedients = ingedients;
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
	public MenuItemCategory getMenuItemsCategory() {
		return menuItemsCategory;
	}
	public void setMenuItemsCategory(MenuItemCategory menuItemsCategory) {
		this.menuItemsCategory = menuItemsCategory;
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
