package fr.eni.pizzaHub.bo;

public class Product {
	private int productId;
	private String name;
	private int stock;

	public Product(String name, int stock) {
		this.name = name;
		this.stock = stock;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
