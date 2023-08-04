package fr.eni.pizzaHub.bo;

import java.util.Arrays;
import java.util.List;

public class Pizza extends MenuItem{

	private Base base;
	private Size size = Size.L; //L is default value
	private String pictureUrl;
	
	public Pizza(int menuItemIId, String name, double price/*, List<Product> ingedients, String pictureUrl*/) {
		super(menuItemIId, name, MenuItemCategory.PIZZA, price);
		this.setIngedients(Arrays.asList(new Product("Creme", 10))); // while we are not handling  Product
		this.base = Base.valueOf(this.getIngedients().get(0).getName().toUpperCase());
		//add pictureUrl when handling it
	}
	
	public Pizza(int menuItemIId, String name, MenuItemCategory menuItemsCategory, double price, Size size) {
		this(menuItemIId,name, price);
		this.size = size;
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

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	

}
