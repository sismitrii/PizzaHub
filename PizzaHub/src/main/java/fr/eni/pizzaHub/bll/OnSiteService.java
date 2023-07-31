package fr.eni.pizzaHub.bll;

import java.util.List;

import fr.eni.pizzaHub.BusinessException;
import fr.eni.pizzaHub.DALEXception;
import fr.eni.pizzaHub.bo.OnSiteOrder;

public interface OnSiteService {
	public List<Integer> getAllTableNumber();
	public OnSiteOrder findOrder(int orderId);
	public OnSiteOrder findOrderByTableNumber(int tableNumber);
	public boolean setSeatsNumber(int tableNumber, int seatNumber);
	public boolean addMenuItem(int tableNumber, int menuItemToAddId) throws BusinessException;
	public void deleteOrder(int tableNumber) throws BusinessException, DALEXception;
	public void setOrderHaveToBePrepared(int tableNumber)throws BusinessException;
}
