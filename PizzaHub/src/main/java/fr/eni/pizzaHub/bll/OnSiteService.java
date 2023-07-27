package fr.eni.pizzaHub.bll;

import java.util.List;

import fr.eni.pizzaHub.bo.OnSiteOrder;

public interface OnSiteService {
	public List<Integer> getAllTableNumber();
	public OnSiteOrder findOrder(int orderId);
	public boolean setSeatsNumber(int tableNumber, int seatNumber);
}
