package fr.eni.pizzaHub.bo;

import java.util.List;

public class Pizza extends MenuItem{

	private Base base;
	private String pictureUrl;
	
	public Pizza(int menuItemIId, String name, MenuItemCategory menuItemsCategory, int price/*, List<Product> ingedients*/, Base base, String pictureUrl) {
		super(menuItemIId, name, MenuItemCategory.PIZZA, price);
		this.base = base;
		this.pictureUrl = pictureUrl;
	}
	
	
	public Base getBase() {
		return base;
	}
	public void setBase(Base base) {
		this.base = base;
	}
	public String getPicture() {
		return pictureUrl;
	}
	public void setPicture(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

}
